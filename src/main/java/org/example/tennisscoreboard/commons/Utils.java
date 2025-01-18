package org.example.tennisscoreboard.commons;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.tennisscoreboard.controllers.NewMatchController.MatchRequest;

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

    public static MatchRequest jsonNewMatchRequestHandler(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String jsonString = sb.toString();

        Gson gson = new Gson();
        MatchRequest matchRequest = gson.fromJson(jsonString, MatchRequest.class);

        return matchRequest;
    }
}
