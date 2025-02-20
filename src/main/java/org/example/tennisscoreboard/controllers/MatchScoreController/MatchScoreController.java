package org.example.tennisscoreboard.controllers.MatchScoreController;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.commons.Utils;
import org.example.tennisscoreboard.exceptions.ThereIsNoSuchCurrentMatchException;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.services.FinishedMatchesPersistenceService;
import org.example.tennisscoreboard.services.MatchScoreCalculationService;
import org.example.tennisscoreboard.services.OngoingMatchesService;
import org.example.tennisscoreboard.services.ScoreService.MatchScore;
import org.example.tennisscoreboard.services.ScoreService.State;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ongoingMatchesService = OngoingMatchesService.getInstance();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UUID uuid = UUID.fromString(req.getParameter("uuid"));
            MatchScoreModel matchScoreModel = ongoingMatchesService.getCurrentMatchScore(uuid);
            MatchScore matchScore = matchScoreModel.getMatchScore();
            Player playerOne = matchScoreModel.getPlayerOne();
            Player playerTwo = matchScoreModel.getPlayerTwo();
            State matchCurrentState = matchScoreCalculationService.getMatchState();
            if (matchCurrentState != State.ONGOING) {
                Player winner = matchScore.getPlayerScore(0).getPlayerSets() == 2 ? playerOne : playerTwo;
                ongoingMatchesService.deleteMatch(matchScoreModel);
                finishedMatchesPersistenceService.saveFinishedMatch(new Match(playerOne, playerTwo, winner));
                req.setAttribute("winner", winner);
                req.getRequestDispatcher("/views/finished-match.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("currentMatch", matchScoreModel);
            req.getRequestDispatcher("/views/match-score.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("errorMessage", "There Is No Such Match");
            req.getRequestDispatcher("/views/error404.jsp").forward(req, resp);
        } catch (ThereIsNoSuchCurrentMatchException e) {
            req.setAttribute("errorMessage", "There Is No Such Match");
            req.getRequestDispatcher("/views/error404.jsp").forward(req, resp);
        } catch (Exception e) {
            Utils.sendJsonMessageResponse(resp,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Internal Server Error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String jsonFromRequest = Utils.jsonRequestHandler(req);
        MatchScoreRequestDTO matchScoreRequestDTO = gson.fromJson(jsonFromRequest, MatchScoreRequestDTO.class);
        String winnerOfThePoint = matchScoreRequestDTO.winner();
        try {
            MatchScoreModel matchScoreModel = ongoingMatchesService.getCurrentMatchScore(uuid);
            matchScoreCalculationService.addScoreToTheCurrentMatch(matchScoreModel, winnerOfThePoint);
        } catch (Exception e) {
            Utils.sendJsonMessageResponse(resp, HttpServletResponse.SC_CONFLICT, e.getMessage());
        }
    }

}

