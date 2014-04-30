<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="container">
	<c:choose>
		<c:when test="${not empty keywordSearchResults}">
			<header>
				<h2>Search Results</h2>
			</header>
			<table style="width: 100%" class="display dataTable"
				id="searchresults">
				<thead>
					<tr>
						<th align="left"  style="font-weight: bold;">Document Name</th>
						<th align="left"  style="font-weight: bold;">Name</th>
						<th align="left"  style="font-weight: bold;">Description</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="searchResultDocument"
						items="${keywordSearchResults}">
						<tr>
							<td width="15%" align="left"><c:out
									value="${searchResultDocument.documentName}"></c:out></td>
							<td width="10%" align="left"><c:out
									value="${searchResultDocument.name}"></c:out></td>
							<td width="10%" align="left"><c:out
									value="${searchResultDocument.description}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/keywordsearchform'"
				value='Go Back to Search'>
			<hr />
		</c:when>
		<c:otherwise>
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/keywordsearchform'"
				value='Go Back to Search'>
			<hr />
			<br />
			No results exist with given search keywords
		</c:otherwise>
	</c:choose>
</div>
