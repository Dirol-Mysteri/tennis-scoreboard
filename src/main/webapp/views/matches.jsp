<%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 25.12.2024
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<main id="matches-wrapper">
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" placeholder="Filter by name" type="text"/>
            <div>
                <a href="/matches?filter_by_player_name=${NAME}">
                    <button class="btn-filter">Find</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
        </table>

        <div class="pagination">
            <a class="prev" href="#"> < </a>
            <a class="num-page current" href="#">1</a>
            <a class="num-page" href="#">2</a>
            <a class="num-page" href="#">3</a>
            <a class="next" href="#"> > </a>
        </div>
    </div>
</main>
</body>
</html>
