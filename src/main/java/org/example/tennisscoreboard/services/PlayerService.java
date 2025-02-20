package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.repositories.PlayerRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepository();
    }

    public Player addNewPlayer(Player player) {
        Player savedPlayer;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            savedPlayer = playerRepository.save(player);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while adding new player", e);
        }
        return savedPlayer;
    }
}
