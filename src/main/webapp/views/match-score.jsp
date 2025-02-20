<%@ page import="java.util.UUID" %>
<%@ page import="org.example.tennisscoreboard.services.ScoreService.MatchScore" %>
<%@ page import="org.example.tennisscoreboard.models.*" %><%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 25.12.2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MatchScoreModel matchScoreModel = (MatchScoreModel) request.getAttribute("currentMatch");
    MatchScore matchScore = matchScoreModel.getMatchScore();
    String playerOneName = matchScoreModel.getPlayerOne().getName();
    String playerTwoName = matchScoreModel.getPlayerTwo().getName();
    PlayerScore playerOneScore = matchScore.getPlayerScore(0);
    PlayerScore playerTwoScore = matchScore.getPlayerScore(1);
    UUID uuid = matchScoreModel.getMatchId();
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/match-score.css">
</head>
<body>
<header>
    <nav class="nav-links">
        <a class="nav-link" href="/">Home</a>
        <a class="nav-link" href="/matches">Matches</a>
    </nav>
</header>
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
                <p><%=playerOneScore.getPlayerSets()%>
                </p>
                <p><%=playerTwoScore.getPlayerSets()%>
                </p>
            </div>
            <div class="column">
                <p>Games</p>
                <p><%=playerOneScore.getPlayerGames()%>
                </p>
                <p><%=playerTwoScore.getPlayerGames()%>
                </p>
            </div>
            <div class="column">
                <p>Points</p>
                <p><%= playerOneScore.getPlayerPoints()%>
                </p>
                <p><%= playerTwoScore.getPlayerPoints()%>
                </p>
            </div>

            <div class="button-column">
                <button class="score-button" id="player-one-score-btn" onclick="handleScoreAdd(event)"
                        data-uuid="<%=uuid%>" data-winner="playerOne">Add Score
                </button>
                <button class="score-button" id="player-two-score-btn" onclick="handleScoreAdd(event)"
                        data-uuid="<%=uuid%>" data-winner="playerTwo">Add Score
                </button>
            </div>
        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/match-score.js"></script>
</body>
</html>
