<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>

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
            <a class="nav-link" href="${pageContext.request.contextPath}/balance">
                Balance:
                <c:out value="${user.getMoney()}$"/>
            </a>
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out!</a>
        </ul>
    </div>
</nav>

<div class="admin-page ui-1">
    <div class="container">
        <div class="ui-item br-blue">
            <form class="form" method="post" action="${pageContext.request.contextPath}/admin/update?id=${tour.getId()}">

                <div>
                    <h2>Tour name</h2>
                    <input type="text" name="name" value="${tour.getName()}" required>
                </div>
                <div>
                    <h2>Info</h2>
                    <textarea name="info" required>${tour.getInfo()}</textarea>
                </div>
                <div>
                    <h2>Price</h2>
                    <input type="number" name="price" step="0.1" min="0" value="${tour.getPrice()}" required>
                </div>
                <div>
                    <h2>Days</h2>
                    <input type="number" name="days" value="${tour.getDays()}" required>
                </div>
                <div>
                    <h2>Sale</h2>
                    <input type="number" name="sale" step="1" min="0" max="100" value="${tour.getSale()}" required>
                </div>
                <!-- BUTTON -->
                <button type="submit" class="btn btn-blue" value="update">Update</button>
            </form>
        </div>

    </div>

</div>

</body>
</html>
