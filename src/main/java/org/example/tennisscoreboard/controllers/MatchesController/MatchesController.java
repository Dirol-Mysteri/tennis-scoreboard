package org.example.tennisscoreboard.controllers.MatchesController;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.commons.Utils;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.repositories.PlayerRepository;
import org.example.tennisscoreboard.services.FinishedMatchesPersistenceService;
import org.example.tennisscoreboard.services.MatchScoreCalculationService;
import org.example.tennisscoreboard.services.OngoingMatchesService;
import org.example.tennisscoreboard.services.PlayerService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Matches", value = "/matches")
public class MatchesController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private PlayerRepository playerRepository;
    private PlayerService playerService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ongoingMatchesService = OngoingMatchesService.getInstance();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.playerService = new PlayerService();
        this.playerRepository = new PlayerRepository();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        String playerName = req.getParameter("filter_by_player_name");
        try {
            List<Match> matches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber, playerName);
            req.setAttribute("matches", matches);
            req.getRequestDispatcher("/views/matches.jsp").forward(req, resp);
        } catch (Exception e) {
            Utils.sendJsonMessageResponse(resp, HttpServletResponse.SC_CONFLICT, e.getMessage());
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

// Сохрани в гит текущую версию проекта
// А теперь реализуй пагинацию через JSP, ограничив количество получаемых данных из базы данных для быстродействия приложения.
// И ещё раз посмотри алгоритм пагинации через JavaScript.



