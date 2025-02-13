<%@ page import="org.example.tennisscoreboard.models.MatchScoreModel" %>
<%@ page import="java.util.UUID" %>
<%@ page import="org.example.tennisscoreboard.models.Score" %><%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 25.12.2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MatchScoreModel matchScoreModel = (MatchScoreModel) request.getAttribute("currentMatch");
    Score score = matchScoreModel.getScore();
    String playerOneName = (String) request.getAttribute("playerOneName");
    Long playerOneId = matchScoreModel.getPlayerOneId();
    int playerOnePoints = score.getPlayerOnePoints();
    int playerOneGames = score.getPlayerOneGames();
    int playerOneSets = score.getPlayerOneSets();
    int playerTwoPoints = score.getPlayerTwoPoints();
    int playerTwoGames = score.getPlayerTwoGames();
    int playerTwoSets = score.getPlayerTwoSets();
    String playerTwoName = (String) request.getAttribute("playerTwoName");
    Long playerTwoId = matchScoreModel.getPlayerTwoId();
    UUID uuid = matchScoreModel.getMatchId();

    boolean deuce = playerOnePoints == playerTwoPoints;
    boolean tieBreakMode = playerOneGames == 6 && playerOneGames == playerTwoGames;
    boolean deuceMode = playerOnePoints >= 40 && playerTwoPoints >= 40;
    boolean playerOneAdvantage = playerOnePoints > playerTwoPoints;
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/match-score.css">
</head>
<body>
<div id="match-score-wrapper">
    <main id="match-score-main">
        <div class="table">
            <div class="column">
                <p>Player Name</p>
                <p><%=playerOneName%>
                </p>
                <p><%=playerTwoName%>
                </p>
            </div>
            <div class="column">
                <p>Sets</p>
                <p><%=playerOneSets%>
                </p>
                <p><%=playerTwoSets%>
                </p>
            </div>
            <div class="column">
                <p>Games</p>

                <% if (tieBreakMode) {
                %>
                <p>Tiebreak
                </p>
                <p>Tiebreak
                </p>
                <%
                } else {
                %>
                <p><%=playerOneGames%>
                </p>
                <p><%=playerTwoGames%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="column">
                <p>Points</p>
                <%
                    if (deuceMode) {
                        if (deuce) {
                            // deuce output
                %>
                <p>deuce</p>
                <p>deuce</p>
                <%
                } else if (playerOneAdvantage) {
                    // deuce mode output player one advantage
                %>
                <p>more</p>
                <p>less</p>
                <%
                } else {
                    // deuce mode output player two advantage
                %>
                <p>less</p>
                <p>more</p>
                <%
                    }
                } else {
                    // standard points output
                %>
                <p><%= playerOnePoints %>
                </p>
                <p><%= playerTwoPoints %>
                </p>
                <%
                    }
                %>
            </div>

            <div class="button-column">
                <button class="score-button" id="player-one-score-btn" onclick="handleScoreAdd(event)"
                        data-uuid="<%=uuid%>" data-player-id="<%=playerOneId%>">Add Score
                </button>
                <button class="score-button" id="player-two-score-btn" onclick="handleScoreAdd(event)"
                        data-uuid="<%=uuid%>" data-player-id="<%=playerTwoId%>">Add Score
                </button>
            </div>
        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/match-score.js"></script>
</body>
</html>
