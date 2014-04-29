<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Keyword Search</title>
</head>
<body>
<div id="search">
<table id="cssTable">


			<tr>
				<td colspan="3" align="left"><h1>Add a Requirement</h1></td>
			</tr>

			<tr>
				<td><label>Name:</label></td>
				<td><input class="text" type="text" name="name"
						id="name" value='' /></td>
			</tr>
			<tr>
				<td colspan="2" align="left"><input class="submit"
					type="submit" value="Search" /> <input type=button class="cancel"
					onClick="location.href='${pageContext.servletContext.contextPath}/auth/home'"
					value='Cancel' /></td>
			</tr>
		</table>
</div>
</body>
</html>