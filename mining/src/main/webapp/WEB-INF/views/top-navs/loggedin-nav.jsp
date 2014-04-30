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
				href="${pageContext.servletContext.contextPath}/auth/tenant/showrequirements"><span>Tenant
						Management</span></a>
				<ul>

					<li><a
						href="${pageContext.servletContext.contextPath}/auth/tenant/addrequirement">Add
							Requirement</a></li>

				</ul></li>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('MINING_ADMIN','SAS_PROVIDER')">
			<li><a
				href="${pageContext.servletContext.contextPath}/auth/component/showcomponents"><span>SAAS
						Provider Management</span></a></li>

			<ul>

				<li><a
					href="${pageContext.servletContext.contextPath}/auth/component/addcomponent">Add
						Component</a></li>

			</ul>
		</sec:authorize>

		<sec:authorize
			access="hasAnyRole('MINING_ADMIN','SAS_PROVIDER','TENANT')">
			<li><a
				href="${pageContext.servletContext.contextPath}/auth/keywordsearchform"><span>Search
						Management</span></a></li>
		</sec:authorize>



	</ul>
</nav>
