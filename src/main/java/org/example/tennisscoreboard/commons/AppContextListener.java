package org.example.tennisscoreboard.commons;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.repositories.MatchRepository;
import org.example.tennisscoreboard.services.FinishedMatchesPersistenceService;
import org.example.tennisscoreboard.services.PlayerService;

import java.util.Random;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PlayerService playerService = new PlayerService();
        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        for (int i = 0; i < 10; i++) {
            int j = new Random().nextInt();
            Player player1 = playerService.addNewPlayer(new Player("Player1"));
            Player player2 = playerService.addNewPlayer(new Player("Player" + j));
            finishedMatchesPersistenceService.saveFinishedMatch(new Match(player1, player2, player1));
        }
        for (int i = 0; i < 10; i++) {
            int j = new Random().nextInt();
            Player player1 = playerService.addNewPlayer(new Player("Player2"));
            Player player2 = playerService.addNewPlayer(new Player("Player" + j));
            finishedMatchesPersistenceService.saveFinishedMatch(new Match(player1, player2, player1));
        }
        for (int i = 0; i < 10; i++) {
            int j = new Random().nextInt();
            Player player1 = playerService.addNewPlayer(new Player("Player3"));
            Player player2 = playerService.addNewPlayer(new Player("Player" + j));
            finishedMatchesPersistenceService.saveFinishedMatch(new Match(player1, player2, player1));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.closeSessionFactory();
    }
}
