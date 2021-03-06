<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
			<a class="navbar-brand" href="<c:url value="/dashboard" />">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<c:if test="${!empty name}">
									<p>${name}</p>
								</c:if>

								<label for="name">Computer name</label> <input type="text"
									class="form-control" id="name" name="name"
									placeholder="Computer name" />
							</div>
							<div class="form-group">
								<c:if test="${!empty introduced}">
									<div>${introduced}</div>
								</c:if>
								<label for="introduced">Introduced date "<spring:message
										code="datePatternShow" />"
								</label> <input type="text" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date" />
							</div>
							<div class="form-group">
								<c:if test="${!empty discontinued}">
									<p>${discontinued}</p>
								</c:if>
								<label for="discontinued">Discontinued date "<spring:message
										code="datePatternShow" />"
								</label> <input type="text" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date" />
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="c" items="${companies}">
										<option value="${c.id}">${c.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary" /> or <a
								class="btn btn-default" href="<c:url value="/dashboard" />">Cancel</a>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
