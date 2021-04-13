<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

    <!-- Styles -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <!-- Style-1 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-1.css"/>
    <!-- Main CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Welcome ${user.getNickname()}!</a>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Features</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Pricing</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
    </div>
</nav>

<div class="ui-1">
    <div class="container">
        <c:forEach items="${tours}" var="tour">
            <div class="ui-item br-red">
                <!-- UI Details -->
                <div class="ui-details">
                    <!-- Heading -->
                    <h2><c:out value="${tour.getName()}"/></h2>
                    <!-- Button -->
                    <a href="ui-1.html#" class="btn btn-red">$<c:out value="${tour.getPrice()}"/></a>
                </div>
                <div class="clearfix"></div>
                <!-- Paragraph -->
                <p><c:out value="${tour.getInfo()}"/></p>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>
