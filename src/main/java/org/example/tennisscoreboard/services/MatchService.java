package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.Game;
import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.exceptions.UniqueConstraintException;
import org.example.tennisscoreboard.models.CurrentMatch;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.repositories.PlayerRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;

public class MatchService {
    private final PlayerRepository playerRepository;

    public MatchService() {
        this.playerRepository = new PlayerRepository();
    }

    public CurrentMatch addNewMatch(String playerOneName, String playerTwoName) {
        Player playerOne;
        Player playerTwo;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            playerOne = (Player) playerRepository.findByName(playerOneName)
                    .map(player -> {
                        throw new UniqueConstraintException("Player with name " + player.getName() + " already exists in the database");
                    })
                    .orElseGet(() -> playerRepository.save(new Player(playerOneName)));


            playerTwo = (Player) playerRepository.findByName(playerTwoName)
                    .map(player -> {
                        throw new UniqueConstraintException("Player with name " + player.getName() + " already exists in the database");
                    })
                    .orElseGet(() -> playerRepository.save(new Player(playerTwoName)));
            var players = playerRepository.findAll();
            System.out.println(Arrays.toString(players.toArray()));
            tx.commit();
        } catch (UniqueConstraintException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new UniqueConstraintException(e.getMessage());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while adding new match", e);
        }

        CurrentMatch currentMatch = new CurrentMatch(playerOne.getId(), playerTwo.getId());
        Game.addMatch(currentMatch);

        return currentMatch;
    }
}

// Скормить AI код Proxy, попросить объяснить. И пока не поймёшь, дальше не идём.