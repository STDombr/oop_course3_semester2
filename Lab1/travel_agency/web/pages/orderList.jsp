<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>

    <style>
        <%@include file="../css/bootstrap.min.css" %>
        <%@include file="../css/style-1.css" %>
        <%@include file="../css/styles.css" %>
    </style>

    <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/jquery.min.js" type="text/javascript"></script>
    <script src="../js/button-script.js" type="text/javascript"></script>
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

<div class="ui-1">
    <div class="container">

        <c:choose>
            <c:when test="${orders.size() != 0}">
                <c:forEach items="${orders}" var="order">
                    <div class="ui-item br-blue">
                        <div class="ui-details">
                            <!-- NAME -->
                            <h2><c:out value="${order.getName()}"/></h2>
                        </div>
                        <div class="clearfix"></div>
                        <h3><c:out value="Tickets: ${order.getCount()}"/></h3>
                        <div class="ui-details" style="margin-right: 10px">
                            <!-- DATE -->
                            <h2><c:out value="${order.getDate()}"/></h2>
                        </div>

                        <div class="clearfix"></div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h1>Order list is empty!</h1>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
