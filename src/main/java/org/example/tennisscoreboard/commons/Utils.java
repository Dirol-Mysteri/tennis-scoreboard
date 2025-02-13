package org.example.tennisscoreboard.commons;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.models.MatchScoreModel;
import org.example.tennisscoreboard.models.Score;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Utils {

    public static void sendJsonResponse(HttpServletResponse response, int status, String responseJson) throws IOException {
        response.setStatus(status);
        try (PrintWriter out = response.getWriter()) {
            out.print(responseJson);
        }
    }

    public static void sendJsonMessageResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"message\":\"" + message + "\"}");
        }
    }

    public static String jsonRequestHandler(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String jsonString = sb.toString();

        return jsonString;
    }

    public static boolean isMatchFinished(MatchScoreModel matchScoreModel) {
        Score score = matchScoreModel.getScore();
        return score.getPlayerOneSets() == 2 || score.getPlayerTwoSets() == 2;
    }
}
