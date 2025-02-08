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
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        String filteredPlayerName = req.getParameter("filter_by_player_name") == null ? "" : req.getParameter("filter_by_player_name");
        try {
            List<Match> matches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber, filteredPlayerName);
            Long totalMatchesCount = finishedMatchesPersistenceService.getTotalMatchesCount(filteredPlayerName);
            req.setAttribute("matches", matches);
            req.setAttribute("totalMatchesCount", totalMatchesCount);
            req.setAttribute("page", pageNumber);
            req.setAttribute("filteredPlayerName", filteredPlayerName);
            req.getRequestDispatcher("/views/matches.jsp").forward(req, resp);
        } catch (Exception e) {
            Utils.sendJsonMessageResponse(resp, HttpServletResponse.SC_CONFLICT, e.getMessage());
        }
    }
}

// В пагинации нужно разобраться с тем, что при клике на страницу у меня прогружается предыдущая страница а не та, что нужна мне.



