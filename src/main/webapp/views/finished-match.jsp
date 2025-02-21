<%@ page import="org.example.tennisscoreboard.models.Player" %><%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 25.12.2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Player winner = (Player) request.getAttribute("winner");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/finished-match.css">
</head>
<body>
<header>
    <nav class="nav-links">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
    </nav>
</header>
<div id="match-score-wrapper">
    <main id="match-score-main">
        <div class="table">
            <p>The winner of the match is <%=winner.getName()%>
            </p>
        </div>
        <div id="menu">
            <ul>
                <a href="${pageContext.request.contextPath}/new-match">New Match</a>
                <a href="${pageContext.request.contextPath}/matches">Played Matches</a>
            </ul>
        </div>
    </main>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/match-score.js"></script>
</body>
</html>
