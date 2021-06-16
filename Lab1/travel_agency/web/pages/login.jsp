<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <style>
        <%@include file="../css/bootstrap.min.css" %>
        <%@include file="../css/style-1.css" %>
        <%@include file="../css/styles.css" %>
    </style>

    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/jquery.min.js" type="text/javascript"></script>
    <script src="../js/button-script.js" type="text/javascript"></script>
</head>
<body class="body-green">
<div class="login-page">
    <form class="form" method="post" action="${pageContext.request.contextPath}/login">
        <input type="text" name="nickname" placeholder="nickname" required>
        <input type="password" name="password" placeholder="password" required>
        <p class="message">
            ${loginErrorMessage}
        </p>
        <button type="submit" value="Login">Login</button>
        <p class="info">
            Not registered?
            <a href='${pageContext.request.contextPath}/registration'>
                Create an account
            </a>
        </p>
    </form>
</div>
</body>
</html>
