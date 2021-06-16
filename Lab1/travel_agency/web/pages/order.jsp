<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>

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

<div class="order-page ui-1">
    <div class="container">
        <c:choose>
            <c:when test="${tour.getSale() == 0}">
                <div class="ui-item br-blue">
                    <form class="form" method="post"
                          action="${pageContext.request.contextPath}/order/submit?id=${tour.getId()}">
                        <!-- NAME -->
                        <div>
                            <h1><c:out value="${tour.getTourType()} in ${tour.getName()}"/></h1>
                        </div>
                        <!-- DAYS -->
                        <h2>
                            <c:out value="${tour.getDays()} day"/>
                        </h2>
                        <!-- INFO -->
                        <c:out value="${tour.getInfo()}"/>
                        <!-- PRICE -->
                        <h2>
                            Price
                            <c:out value="${tour.getPrice()}$"/>
                        </h2>

                        <h3>Count</h3>
                        <div class="form-outline" style="width: 200px; margin: 0 auto">
                            <input type="number" name="count" class="form-control" step="1" min="1" value="1" style="text-align: center"/>
                        </div>

                        <!-- BUTTON -->
                        <button type="submit" class="btn btn-blue" value="Order">
                            <h2 style="color: white">Complete</h2>
                        </button>

                        <h3 style="color: red"><c:out value="Ordered ${ordersCount} times in the last week!"/></h3>
                    </form>
                </div>
            </c:when>

            <c:otherwise>
                <div class="ui-item br-red">
                    <form class="form" method="post"
                          action="${pageContext.request.contextPath}/order/submit?id=${tour.getId()}">
                        <!-- NAME -->
                        <div>
                            <h1>
                                <c:out value="${tour.getTourType()} in ${tour.getName()}"/>
                                , <span style="color: red; font-weight: bold"><c:out
                                    value="${tour.getSale()}% "/></span>discount!
                            </h1>
                        </div>
                        <!-- DAYS -->
                        <h2>
                            <c:out value="${tour.getDays()} day"/>
                        </h2>
                        <!-- INFO -->
                        <c:out value="${tour.getInfo()}"/>
                        <!-- PRICE -->
                        <h2>
                            Price
                            <del><c:out value="${tour.getPrice()}$"/></del>
                            <c:out value=" ${tour.getPrice() * (100 - tour.getSale()) / 100}$"/>
                        </h2>

                        <h3>Count</h3>
                        <div class="form-outline" style="width: 200px; margin: 0 auto">
                            <input type="number" name="count" class="form-control" step="1" min="1" value="1" style="text-align: center"/>
                        </div>

                        <!-- BUTTON -->
                        <button type="submit" class="btn btn-blue" value="Order">
                            <h2 style="color: white">Complete</h2>
                        </button>

                        <h3 style="color: red"><c:out value="Ordered ${ordersCount} times in the last week!"/></h3>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
