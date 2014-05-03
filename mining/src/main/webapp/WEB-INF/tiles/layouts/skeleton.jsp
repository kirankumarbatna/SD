<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML>

<html>
<head>
<title><tiles:insertAttribute name="title" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="viewport" content="width=1040" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/html5shiv.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.dataTables.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.dropotron.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/config.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel-panels.min.js"></script>
	
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery-ui.js"></script>
	
	

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/skel-noscript.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style-desktop.css" />
	<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/jquery.dataTables_themeroller.css" />
<script	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.dropotron.min.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/config.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery-ui.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.quick.pagination.min.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel.min.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel-panels.min.js"></script>
<%-- <script src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery-alert.js"></script> --%>
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/skel-noscript.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style.css" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/jquery-ui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style-desktop.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/demo_table_jui.css" />
<link rel="stylesheet"	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/ul-pagination-styles.css" />


</head>
<body class="homepage">
	<tiles:importAttribute name="currentPage" scope="request" />
	<!-- Header Wrapper -->
	<div id="header-wrapper">

		<!-- Header -->
		<div id="header" class="container">

			<!-- Logo -->
			<h1>
				<a href="#">EASYSaaS Platform</a>
			</h1>
			<tiles:insertAttribute name="navigation" />
		</div>
	</div>

	<!-- Main Wrapper -->
	<div id="main-wrapper">
		<!-- Main -->
		<div id="main" class="container">
			<sec:authorize access="isAuthenticated()">
				<div>
					<div class="loggedInMsg">
						Welcome <span class="user" style="margin-left: 5px;"><sec:authentication
								property="principal.username" /></span>! 
					</div>
					<div class="loggedOutLink">
						<a style="font-weight: bold;" href="<c:url value='/j_spring_security_logout' />">Logout</a>
					</div>
					<br class="clearLoggedIn" />
					<hr class="loginbar"/>
				</div>
			</sec:authorize>
			<!-- Content -->
			<div id="content" class="8u skel-cell-important">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>

	<!-- Footer -->
	<!-- Copyright -->
	<div id="copyright" class="container">
		<ul class="links">
			<li>&copy;2014 Software Design, GROUP 2, 6, 32</li>
		</ul>
	</div>
</body>
</html>