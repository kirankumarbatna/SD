<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h4>Following are the suggested components based on your submitted
	requirement.</h4>

<table>
	<c:forEach items="${rec}" var="r">

		<tr>
			<td>Component Name:</td>
			<td><c:out value="${r.name}"></c:out></td>
		</tr>
		<tr>
			<td>Component URL</td>
			<td><c:out value="${r.url}"></c:out></td>
		</tr>

	</c:forEach>
</table>