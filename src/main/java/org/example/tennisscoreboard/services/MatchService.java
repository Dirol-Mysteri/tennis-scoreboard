package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.repositories.PlayerRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {
    private final PlayerRepository playerRepository;
    private final OngoingMatchesService ongoingMatchesService;

    public MatchService() {
        this.playerRepository = new PlayerRepository();
        this.ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    public MatchScoreModel addNewMatch(String playerOneName, String playerTwoName) throws Exception {
        Player playerOne;
        Player playerTwo;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();

            playerOne = playerRepository.findByName(playerOneName).orElseGet(() -> playerRepository.save(new Player(playerOneName)));
            playerTwo = playerRepository.findByName(playerTwoName).orElseGet(() -> playerRepository.save(new Player(playerTwoName)));

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error while adding new match", e);
        }

        MatchScoreModel matchScoreModel = new MatchScoreModel(playerOne.getId(), playerTwo.getId());

        ongoingMatchesService.addMatch(matchScoreModel);

        return matchScoreModel;
    }
}