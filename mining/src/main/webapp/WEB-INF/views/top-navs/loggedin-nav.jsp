<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="currentPage" type="java.lang.String" scope="request" />
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Nav -->
<nav id="nav">
	<ul>

		<li><a href="${pageContext.servletContext.contextPath}/auth/home">Home</a></li>


		<sec:authorize access="hasAnyRole('MINING_ADMIN','TENANT')">
			<li><a
				href="${pageContext.servletContext.contextPath}/auth/requirements"><span>Tenant
						Management</span></a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('MINING_ADMIN','SAS_PROVIDER')">
			<li><a
				href="${pageContext.servletContext.contextPath}/auth/provider"><span>SAAS Provider
						Management</span></a>
			</li>
		</sec:authorize>
		
		<sec:authorize access="hasAnyRole('MINING_ADMIN','SAS_PROVIDER','TENANT')">
			<li><a
				href="${pageContext.servletContext.contextPath}/auth/search"><span>Search
						Management</span></a>
			</li>
		</sec:authorize>
		
	

	</ul>
</nav>
