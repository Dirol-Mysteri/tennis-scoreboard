package org.example.tennisscoreboard.controllers.MatchScoreController;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.commons.Utils;
import org.example.tennisscoreboard.models.Match;
import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Player;
import org.example.tennisscoreboard.models.Score;
import org.example.tennisscoreboard.services.FinishedMatchesPersistenceService;
import org.example.tennisscoreboard.services.MatchScoreCalculationService;
import org.example.tennisscoreboard.services.OngoingMatchesService;
import org.example.tennisscoreboard.services.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScore", value = "/match-score")
public class MatchScoreController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private PlayerService playerService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.ongoingMatchesService = OngoingMatchesService.getInstance();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.playerService = new PlayerService();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScoreModel matchScoreModel = ongoingMatchesService.getCurrentMatchScore(uuid);
        Score score = matchScoreModel.getScore();
        Long playerOneId = matchScoreModel.getPlayerOneId();
        Long playerTwoId = matchScoreModel.getPlayerTwoId();
        try {
            Player playerOne = playerService.getPlayerById(playerOneId);
            Player playerTwo = playerService.getPlayerById(playerTwoId);
            if (Utils.isMatchFinished(matchScoreModel)) {
                Player winner = score.getPlayerOneSets() == 2 ? playerOne : playerTwo;
                ongoingMatchesService.deleteMatch(matchScoreModel);
                finishedMatchesPersistenceService.saveFinishedMatch(new Match(playerOne, playerTwo, winner));
                req.setAttribute("winner", winner);
                req.getRequestDispatcher("/views/finished-match.jsp").forward(req, resp);
                return;
            }

            String playerOneName = playerService.getPlayerNameById(playerOneId);
            String playerTwoName = playerService.getPlayerNameById(playerTwoId);
            req.setAttribute("currentMatch", matchScoreModel);
            req.setAttribute("playerOneName", playerOneName);
            req.setAttribute("playerTwoName", playerTwoName);
            System.out.println("THIS IS FUCKING TEST");
            req.getRequestDispatcher("/views/match-score.jsp").forward(req, resp);
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
        Long winnerOfThePointID = matchScoreRequestDTO.winnerID();
        try {
            MatchScoreModel matchScoreModel = ongoingMatchesService.getCurrentMatchScore(uuid);

            Long playerOneId = matchScoreModel.getPlayerOneId();
            Long playerTwoId = matchScoreModel.getPlayerTwoId();
            Player playerOne = playerService.getPlayerById(playerOneId);
            Player playerTwo = playerService.getPlayerById(playerTwoId);
            String playerOneName = playerOne.getName();
            String playerTwoName = playerTwo.getName();

            matchScoreCalculationService.addScoreToTheCurrentMatchWinner(matchScoreModel, winnerOfThePointID);

            req.setAttribute("currentMatch", matchScoreModel);
            req.setAttribute("playerOneName", playerOneName);
            req.setAttribute("playerTwoName", playerTwoName);
            req.getRequestDispatcher("/views/match-score.jsp").forward(req, resp);
        } catch (Exception e) {
            Utils.sendJsonMessageResponse(resp, HttpServletResponse.SC_CONFLICT, e.getMessage());
        }
    }
}

