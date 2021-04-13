<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<div class="register-page">
    <form class="form" method="post" action="${pageContext.request.contextPath}/registration">
        <input type="text" name="nickname" placeholder="nickname" required>
        <input type="password" name="password" placeholder="password" required>
        <p class="message">
            ${registrationErrorMessage}
        </p>
        <button type="submit" value="Register">Create</button>
        <p class="info">
            Already registered?
            <a href='${pageContext.request.contextPath}/login'>
                Sign In
            </a>
        </p>
    </form>
</div>
</body>
</html>
