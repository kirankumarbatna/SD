<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<style type="text/css">
.response {
	color: red;
	margin-left: -60px;
}

.info {
	color: green;
	font-weight: 500;
	margin-top: -30px;
	margin-left: 170px;
}

#form h1 {
	margin-left: 80px;
}

#form table {
	margin-left: 30%;
	width: 70%;
}

#cssTable td {
	text-align: left;
	vertical-align: middle;
	padding-top: 7px;
}
</style>


<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery-ui.js"
	type="text/javascript">
	
</script>

<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.multiselect.min.js"
	type="text/javascript">
	
</script>




<script type="text/javascript">
	$(document).ready(function() {

		$("#adminSelect").multiselect({
			header : "Select Project Admins"

		});
		// 		$("#iolousSelect").multiselect({
		// 			header : "Select Iolous Instances"

		// 		});

	});
</script>


<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/jquery.multiselect.css" />

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/form.css" />

<div id="form">
	<form:form name="addrequirementForm" class="form" autocomplete="off"
		action="${pageContext.servletContext.contextPath}/auth/tenant/addrequirement"
		modelAttribute="requirement" method="POST" enctype="multipart/form-data">
		<table id="cssTable">


			<tr>
				<td colspan="3" align="left"><h1>Add a Requirement</h1></td>
			</tr>

			<tr>
				<td><label>Name:</label></td>
				<td><form:input class="text" type="text" name="name"
						path="name" value='' /></td>
			</tr>

			<tr>
				<td><label>Description:</label></td>
				<td><form:textarea class="description" name="description"
						path="description"></form:textarea></td>
			</tr>


<tr>
				<td><label>Please select a file to upload:</label></td>
				<td><form:input type="file" class="file" name="file"
						path="file"></form:input></td>
			</tr>



			

			<tr>
				<td><label>Keywords:</label></td>
				<td><form:input type="text" class="key-words" name="keyword"
						path="keyword"></form:input></td>
			</tr>

			<tr>
				<td colspan="2" align="left"><input class="submit"
					type="submit" value="Add" /> <input type=button class="cancel"
					onClick="location.href='${pageContext.servletContext.contextPath}/auth/home'"
					value='Cancel' /></td>
			</tr>
		</table>
	</form:form>
</div>