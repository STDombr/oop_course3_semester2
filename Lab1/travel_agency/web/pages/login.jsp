<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
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
