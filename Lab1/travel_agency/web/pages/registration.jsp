<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

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
