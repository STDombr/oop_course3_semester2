<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Top Up</title>

    <style>
        <%@include file="../css/bootstrap.min.css" %>
        <%@include file="../css/style-1.css" %>
        <%@include file="../css/styles.css" %>
    </style>

    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/jquery.min.js" type="text/javascript"></script>
    <script src="../js/button-script.js" type="text/javascript"></script>
    <script>
        $("input[type='number']").inputSpinner();
    </script>
</head>
<body
        <c:choose>
            <c:when test="${user.isAdmin()}">
                class="body-red"
            </c:when>
            <c:otherwise>
                class="body-green"
            </c:otherwise>
        </c:choose>
>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="padding-left:40px; padding-right: 40px">
    <c:choose>
        <c:when test="${user.isAdmin() == true}">
            <p class="navbar-brand">Welcome ${user.getNickname()}(Admin)!</p>
        </c:when>
        <c:otherwise>
            <p class="navbar-brand">Welcome ${user.getNickname()}!</p>
        </c:otherwise>
    </c:choose>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/list">List</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/orderList">Orders</a>
        </ul>
        <ul class="navbar-nav" style="width: 100%; justify-content: flex-end">
            <a class="nav-link" href="${pageContext.request.contextPath}/balance">
                Balance:
                <c:out value="${user.getMoney()}$"/>
            </a>
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out!</a>
        </ul>
    </div>
</nav>

<div class="top-up-page">
    <form class="form" method="post" action="${pageContext.request.contextPath}/balance/topup">
        <h2>Top up your balance</h2>

        <input type="number" name="money" step="0.1" min="0" value="0" required>
        <p class="message">
            ${topUpErrorMessage}
        </p>
        <button type="submit" value="topup">Top up</button>
    </form>
</div>

</body>
</html>