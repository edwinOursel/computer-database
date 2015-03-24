<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/dashboard" />"> Application - Computer Database </a>
    </div>
</header>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: <c:out value="${computer.id}"/>
                </div>
                <h1>Edit Computer</h1>
                <c:if test="${!empty message}">
                    <div class="has-error">${message}</div>
                </c:if>
                <form action="editComputer" method="POST">
                    <input type="hidden" name="id" value="${computer.id}"/>
                    <fieldset>
                        <div class="form-group">
                            <label for="name">Computer name</label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Computer name"
                                   value="<c:out value="${computer.name}"/>"/>
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date</label>
                            <input type="date" class="form-control" id="introduced" name="introduced"
                                   placeholder="Introduced date"
                                   value="<fmt:formatDate value="${computer.introducedDate}" pattern="yyyy-MM-dd" />"/>
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date</label>
                            <input type="date" class="form-control" id="discontinued" name="discontinued"
                                   placeholder="Discontinued date"
                                   value="<fmt:formatDate value="${computer.discontinuedDate}" pattern="yyyy-MM-dd" />"/>
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label>
                            <select class="form-control" id="companyId" name="companyId">
                                <c:forEach var="i" items="${companiesId}">
                                    <c:choose>
                                        <c:when test="${(!empty computer.company != null) && (computer.company.id == i)}">
                                            <option selected>${computer.company.id}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${i}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="actions pull-right">
                        <input type="submit" value="Edit" class="btn btn-primary">
                        or
                        <a href="<c:url value="/dashboard" />" class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>