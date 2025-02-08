<%@ page import="org.example.tennisscoreboard.models.Match" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 25.12.2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Match> matches = (List<Match>) request.getAttribute("matches");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/matches.css">
</head>
<body>
<main id="matches-wrapper">
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" placeholder="Filter by name" type="text" value="${filteredPlayerName}">
            <div>
                <button class="btn-filter">Find</button>
            </div>
        </div>
        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <% for (Match match : matches) { %>
            <tr class="match">
                <td><%=match.getPlayerOne().getName()%>
                </td>
                <td><%=match.getPlayerTwo().getName()%>
                </td>
                <td><span class="winner-name-td"><%=match.getWinner().getName()%></span></td>
            </tr>
            <%}%>
        </table>
        <div class="pagination">
        </div>
    </div>
</main>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/matches.js"></script>
<script type='application/json' id='data'>
    {
      "totalMatchesCount": ${totalMatchesCount},
      "page": ${page},
      "filteredPlayerName": "${filteredPlayerName}"
    }
</script>
</body>
</html>
