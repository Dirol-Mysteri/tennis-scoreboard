package org.example.tennisscoreboard.repositories;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MatchRepository extends BaseRepository<Long, Match> {
    public MatchRepository() {
        super(Match.class);
    }

    public Optional<List<Match>> findByPlayerName(String playerName) {
        try {
            Session session = HibernateUtil.getCurrentSession();
            String hql = "FROM Match m WHERE m.playerOne.name = :name OR m.playerTwo.name = :name";
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", playerName);
            return Optional.ofNullable(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
