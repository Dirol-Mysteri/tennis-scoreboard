<%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 24.12.2024
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>

<body>
<header>
    <nav class="nav-links">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
    </nav>
</header>
<div id="wrapper">
    <main id="main">
        <h1>Hello To The Tennis Scoreboard App!</h1>
        <ul>
            <a href="${pageContext.request.contextPath}/new-match">New Match</a>
            <a href="${pageContext.request.contextPath}/matches">Finished Matches</a>
        </ul>
    </main>

</div>
</body>

</html>
