<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>

    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <!-- Style-1 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-1.css"/>
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
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
            <a class="nav-link" href="${pageContext.request.contextPath}/topup">
                Balance:
                <c:out value="${user.getMoney()}$"/>
            </a>
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out!</a>
        </ul>
    </div>
</nav>

<div class="ui-1">
    <div class="container">
        <c:forEach items="${tours}" var="tour">
            <c:choose>
                <c:when test="${tour.getSale() == 0}">
                    <div class="ui-item br-blue">
                        <div class="ui-details">
                            <!-- NAME -->
                            <h2><c:out value="${tour.getTourType()} in ${tour.getName()}"/></h2>
                            <!-- BUTTON -->
                            <a href="${pageContext.request.contextPath}/order?id=${tour.getId()}" class="btn btn-blue">
                                <c:out value="${tour.getPrice()}$"/>
                            </a>
                        </div>
                        <div class="clearfix"></div>

                        <!-- INFO -->
                        <div>
                            <c:out value="${tour.getInfo()}"/>
                        </div>
                        <c:choose>
                            <c:when test="${user.isAdmin()}">
                                <div class="settings">
                                    <a href="${pageContext.request.contextPath}/admin?id=${tour.getId()}">&#128736;</a>
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="clearfix"></div>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="ui-item br-red">
                        <div class="ui-details">
                            <!-- NAME -->
                            <h2><c:out value="${tour.getTourType()} in ${tour.getName()}"/></h2>
                            <!-- SALE -->
                            <h2>, <span style="color: red; font-weight: bold"><c:out
                                    value="${tour.getSale()}% "/></span>discount!</h2>
                            <!-- BUTTON -->
                            <a href="${pageContext.request.contextPath}/order?id=${tour.getId()}" class="btn btn-blue">
                                <c:out value="${tour.getPrice()}$"/>
                            </a>
                        </div>
                        <div class="clearfix"></div>

                        <!-- INFO -->
                        <div>
                            <c:out value="${tour.getInfo()}"/>
                        </div>
                        <c:choose>
                            <c:when test="${user.isAdmin()}">
                                <a href="${pageContext.request.contextPath}/admin?id=${tour.getId()}" class="settings">&#128736;</a>
                            </c:when>
                        </c:choose>
                        <div class="clearfix"></div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

</div>

</body>
</html>
