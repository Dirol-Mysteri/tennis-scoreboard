package org.example.tennisscoreboard.repositories;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchRepository extends BaseRepository<Long, Match> {
    private final int ITEMS_PER_PAGE = 5;

    public MatchRepository() {
        super(Match.class);
    }

    public Optional<List<Match>> findByPlayerName(int pageNumber, String playerName) {
        try {
            Session session = HibernateUtil.getCurrentSession();

            String hql;
            Query<Match> query;
            if (playerName == null || playerName.isEmpty()) {
                hql = "FROM Match";
                query = session.createQuery(hql, Match.class);
            } else {
                hql = "FROM Match m WHERE m.playerOne.name LIKE :name OR m.playerTwo.name LIKE :name";
                query = session.createQuery(hql, Match.class);
                query.setParameter("name", playerName);
            }

            int offset = (pageNumber - 1) * ITEMS_PER_PAGE;

            query.setFirstResult(offset); // Смещение (например: offset)
            query.setMaxResults(ITEMS_PER_PAGE); // Количество элементов на странице
            return Optional.ofNullable(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Long getTotalMatchesCount(String playerName) {
        try {
            Session session = HibernateUtil.getCurrentSession();
            String hql;
            Query<Long> query;
            if (playerName == null || playerName.isEmpty()) {
                hql = "SELECT COUNT(*) FROM Match";
                query = session.createQuery(hql, Long.class);
            } else {
                hql = "SELECT COUNT(*) FROM Match m WHERE m.playerOne.name LIKE :name OR m.playerTwo.name LIKE :name";
                query = session.createQuery(hql, Long.class);
                query.setParameter("name", playerName);
            }
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
