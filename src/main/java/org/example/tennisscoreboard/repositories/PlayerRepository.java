package org.example.tennisscoreboard.repositories;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository() {
        super(Player.class);
    }

    public Optional<Player> findByName(String name) {
        try {
            Session session = HibernateUtil.getCurrentSession();
            String hql = "FROM Player p WHERE p.name = :name";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("name", name);
            return Optional.ofNullable(query.uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
