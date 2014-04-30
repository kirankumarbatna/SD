<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Keyword Search</title>
<script type="text/javascript">
	function checkAndSubmit()
	{
		var searchText = document.form1.keyword.value;
		if(searchText==null || searchText.length==0)
		{
			alert("Please enter the search text !!");
			return false;
		}
		form1.submit();
	}
</script>
</head>
<body>
<div id="search">
	<form id="form1" action="${pageContext.servletContext.contextPath}/auth/keywordsearch" class="form" method="POST" enctype="multipart/form-data">
		<table id="cssTable">
			<tr>
				<td colspan="3" align="left"><h1>Enter search keywords</h1></td>
			</tr>
			<tr>
				<td><input type="radio" name="searchType" value="requirement">Requirements
				    <input type="radio" name="searchType" value="component">Components
				</td>
			</tr>
			<tr>
				<td><label>Search:</label></td>
				<td><input class="text" type="text" name="keyword"
						id="keyword" value='' /></td>
			</tr>
			<tr>
				<td colspan="2" align="left">
				<input class="submit" type="submit" value="Search" onclick="checkAndSubmit()"/> 
				<input type=button class="cancel" onClick="location.href='${pageContext.servletContext.contextPath}/auth/home'"
					value='Cancel'/></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>