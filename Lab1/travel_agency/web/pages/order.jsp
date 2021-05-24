<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>

    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <!-- Style-1 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-1.css"/>
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <script src="${pageContext.request.contextPath}js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/button-script.js"></script>
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
            <a class="nav-link" href="#">Features</a>
            <a class="nav-link" href="#">Pricing</a>
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
                    <form class="form" method="post" action="${pageContext.request.contextPath}/order/submit?id=${tour.getId()}">
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

                        <div class="qty mt-5" style="padding-bottom: 30px;">
                            <span class="minus bg-dark">-</span>
                            <input type="number" class="count" name="count" value="1" style="background: #ffffff" disabled>
                            <span class="plus bg-dark">+</span>
                        </div>
                        <div class="clearfix"></div>

                        <!-- BUTTON -->
                        <button type="submit" class="btn btn-blue" value="Order">
                            <!-- PRICE -->
                            <input type="text" class="price" name="price" value="${tour.getPrice()}" disabled>
                            $
                        </button>
                    </form>
                </div>
            </c:when>

            <c:otherwise>
                <div class="ui-item br-red">
                    <form class="form" method="post" action="${pageContext.request.contextPath}/order/submit?id=${tour.getId()}">
                        <!-- NAME -->
                        <div>
                            <h1>
                                <c:out value="${tour.getTourType()} in ${tour.getName()}"/>
                                , <span style="color: red; font-weight: bold"><c:out value="${tour.getSale()}% "/></span>discount!
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

                        <div class="qty mt-5" style="padding-bottom: 30px;">
                            <span class="minus bg-dark">-</span>
                            <input type="number" class="count" name="count" value="1" style="background: #ffffff" disabled>
                            <span class="plus bg-dark">+</span>
                        </div>
                        <div class="clearfix"></div>

                        <!-- BUTTON -->
                        <button type="submit" class="btn btn-blue" value="Order">
                            <!-- PRICE -->
                            <input type="text" class="price" name="price" value="${tour.getPrice() * (100 - tour.getSale()) / 100}" disabled style="font-weight: bold; font-size: 16px">
                            $
                        </button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>
