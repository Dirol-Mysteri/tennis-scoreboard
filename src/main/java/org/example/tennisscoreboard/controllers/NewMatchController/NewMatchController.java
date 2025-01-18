package org.example.tennisscoreboard.controllers.NewMatchController;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.commons.Utils;
import org.example.tennisscoreboard.exceptions.UniqueConstraintException;
import org.example.tennisscoreboard.models.CurrentMatch;
import org.example.tennisscoreboard.services.MatchService;

import java.io.IOException;
import java.util.UUID;

import static org.example.tennisscoreboard.commons.Utils.jsonNewMatchRequestHandler;

@WebServlet(name = "NewMatch", value = "/new-match")
public class NewMatchController extends HttpServlet {
    private MatchService matchService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.matchService = new MatchService();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MatchRequest formData = jsonNewMatchRequestHandler(req);

        String playerOneName = formData.getPlayerOne();
        String playerTwoName = formData.getPlayerTwo();

        try {
            CurrentMatch currentMatch = matchService.addNewMatch(playerOneName, playerTwoName);
            String json = gson.toJson(currentMatch);
            Utils.sendJsonResponse(resp, HttpServletResponse.SC_OK, json);
        } catch (UniqueConstraintException e) {
            Utils.sendJsonMessageResponse(resp, HttpServletResponse.SC_CONFLICT, e.getMessage());
        }
    }
}

// Нужно переписать в сервисе логику сохранения игроков, в том числе на уровне репозитория, т.к. сейчас у меня они
// сохраняются сразу, а нужно сделать так, чтобы они сохранялись только в том случае, если оба игрока не существую в базе данных

