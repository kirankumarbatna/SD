<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results</title>
</head>
<body>
<c:choose>
	<c:when test="${not empty keywordSearchResults}">
		<table style="width: 100%" cellpadding="0" cellspacing="0"
						border="0" class="display dataTable" id="listrequirement">			
			<thead>
				<tr>
					<th >Document Name</th>
					<th >Name</th>
					<th >Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="searchResultDocument" items="${keywordSearchResults}">
					<tr>
						<td width="10%" align="left"><c:out value="${searchResultDocument.documentName}"></c:out></td>
						<td width="10%" align="left"><c:out value="${searchResultDocument.name}"></c:out></td>
						<td width="10%" align="left"><c:out value="${searchResultDocument.description}"></c:out></td>
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
</body>
</html>