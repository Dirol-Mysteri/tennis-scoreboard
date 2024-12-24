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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<div id="new-match-wrapper">
    <header id="new-match-header">
        <h1>New Match</h1>
    </header>
    <main id="new-match-main">
        <form method="post">
            <label for="playerOne">Player one</label>
            <input id="playerOne" name="playerOne" placeholder="Name" type="text"  required title="Enter a name">
            <label for="playerTwo">Player two</label>
            <input id="playerTwo" name="playerTwo" placeholder="Name" type="text" required title="Enter a name">
            <input class="form-button" type="submit" value="Start">
        </form>
    </main>
</div>
</body>

</html>
