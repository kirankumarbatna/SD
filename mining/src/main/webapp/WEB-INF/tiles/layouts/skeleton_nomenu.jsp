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
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600|Arvo:700"
	rel="stylesheet" type="text/css" />
<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/jquery.dropotron.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/config.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/txt-layout/js/skel-panels.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/skel-noscript.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style.css" />
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/txt-layout/css/style-desktop.css" />

</head>
<body class="homepage">
<tiles:importAttribute name="currentPage" scope="request" />
	<!-- Header Wrapper -->
	<div id="header-wrapper">

		<!-- Header -->
		<div id="header" class="container">

			<!-- Logo -->
			<h1>
				<a href="#">EASYSAAS PLATFORM</a>
			</h1>
		</div>
	</div>

	<!-- Main Wrapper -->
	<div id="main-wrapper">
		<!-- Main -->
		<div id="main" class="container">
			<div class="row">
				<section>
					<ul class="divided">
						<tiles:insertAttribute name="sub-navigation" />
					</ul>
				</section>
				<!-- Content -->
				<div id="content" class="8u skel-cell-important">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<!-- Copyright -->
	<div id="copyright" class="container">
		<ul class="links">
			<li>&copy;2014 Software Design, GROUP 2, 6, 32</li>
			<li>Design: <a href="http://html5up.net/">HTML5 UP</a></li>
			<li>Demo Images: <a href="http://mdomaradzki.deviantart.com/">Michael
					Domaradzki</a></li>
		</ul>
	</div>
</body>
</html>