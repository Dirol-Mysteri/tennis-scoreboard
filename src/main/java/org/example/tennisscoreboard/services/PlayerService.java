package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.repositories.PlayerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepository();
    }

    public String getPlayerNameById(Long id) {
        String playerName;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            playerName = playerRepository.findById(id).get().getName();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while retrieving player name", e);
        }
        return playerName;
    }

    public Player getPlayerById(Long id) {
        Player player;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            player = playerRepository.findById(id).get();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while retrieving player", e);
        }
        return player;
    }

    public Player addNewPlayer(Player player) {
        Player savedPlayer;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            savedPlayer = playerRepository.save(player);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while adding new player", e);
        }
        return savedPlayer;
    }
}
