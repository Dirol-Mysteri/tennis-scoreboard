<%--
  Created by IntelliJ IDEA.
  User: aliev008
  Date: 24.12.2024
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("errorMessage");
    String errorMessage = error != null ? error : "HTTP Status 404 – There Is No Such Page";

%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error404.css">
</head>

<body>
<header>
    <nav class="nav-links">
        <a class="nav-link" href="/">Home</a>
        <a class="nav-link" href="/matches">Matches</a>
    </nav>
</header>
<div id="error-page-wrapper">
    <main id="error-page-main">
        <h1><%= errorMessage%>
        </h1>
    </main>
</div>
</body>

</html>
