package org.example.tennisscoreboard.services;

import org.example.tennisscoreboard.commons.HibernateUtil;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.repositories.MatchRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository;

    public FinishedMatchesPersistenceService() {
        this.matchRepository = new MatchRepository();
    }

    public void saveFinishedMatch(Match finishedMatch) {
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            matchRepository.save(finishedMatch);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while retrieving player", e);
        }
    }

    public List<Match> getFinishedMatches(int pageNumber, String playerName) {
        List<Match> finishedMatches;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            finishedMatches = matchRepository.findByPlayerName(pageNumber, playerName).get();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while retrieving player", e);
        }
        return finishedMatches;
    }

    public Long getTotalMatchesCount(String playerName) {
        Long totalMatchesCount;
        Transaction tx = null;
        try {
            Session session = HibernateUtil.getCurrentSession();
            tx = session.beginTransaction();
            totalMatchesCount = matchRepository.getTotalMatchesCount(playerName);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error while retrieving player", e);
        }
        return totalMatchesCount;
    }


}
