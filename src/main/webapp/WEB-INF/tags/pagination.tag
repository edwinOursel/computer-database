<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="pageCount" required="true" type="java.lang.Integer" %>
<%@ attribute name="pageSize" required="true" type="java.lang.Integer" %>
<%@ attribute name="url" required="true" %>
<%@ attribute name="previous" required="true" %>
<%@ attribute name="totalPages" required="true" type="java.lang.Integer" %>

<div class="container text-center">
	<ul class="pagination">
		<li>
			<a href="<c:url value="${url}?page=1&size=${pageSize}" />" aria-label="Next">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
		<li>
			<c:choose>
				<c:when test="${page > 1}">
					<a href="<c:url value="${url}?page=${page - 1}&size=${pageSize}" />" aria-label="Next">
						<span aria-hidden="true">&lsaquo;</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" aria-label="Previous"> <span aria-hidden="true">&lsaquo;</span></a>
				</c:otherwise>
			</c:choose>
		</li>
		<c:forEach var="i" begin="${((page - 3) > 1)? (page - 3) : 1}" end="${((page + 3) < pageCount)? (page + 3) : pageCount}">
			<c:choose>
			<c:when test="${i == page}">
			<li class="active"><a href="#">${i}</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="<c:url value="${url}?page=${i}&size=${pageSize}" />">${i}</a></li>
		</c:otherwise>
		</c:choose>
		</c:forEach>
		<li>
			<c:choose>
				<c:when test="${page < totalPages}">
					<a href="<c:url value="${url}?page=${page + 1}&size=${pageSize}" />" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="#" aria-label="Next">
						<span aria-hidden="true">&rsaquo;</span>
					</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li>
			<a href="<c:url value="${url}?page=${totalPages}&size=${pageSize}" />" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=20'">20</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?page=1&size=100'">100</button>
	</div>
</div>