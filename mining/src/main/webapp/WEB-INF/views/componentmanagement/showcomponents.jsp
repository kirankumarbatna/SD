
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<header>
	<h2 class="dataTableHeading">component Management</h2>
</header>

<script>
	$(document).ready(function() {
		$("#dlgConfirm").hide();
	});

	$(function() {

		$("input[name='deletecomponent']").button().click(function(event) {
			if ($("form input:checkbox").is(":checked")) {
				event.preventDefault();
				$("#dlgConfirm").dialog({
					resizable : false,
					height : 'auto',
					width : 350,
					modal : true,
					buttons : {
						Submit : function() {
							$(this).dialog("close");
							$("#componentform")[0].submit();
						},
						Cancel : function() {
							$(this).dialog("close");
						}
					}
				});
			}
		});

	});
</script>



<style type="text/css">
.submit {
	background-color: #808080;
	color: #FFFFFF;
	width: 150px;
	height: 30px;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	margin-bottom: 3px;
}
</style>

<script type="text/javascript" charset="utf8">
	$(document).ready(function() {
		$('#selectall').click(function() {
			$('.selected').prop('checked', isChecked('selectall'));
		});
	});
	function isChecked(checkboxId) {
		var id = '#' + checkboxId;
		return $(id).is(":checked");
	}
	function resetSelectAll() {
		// if all checkbox are selected, check the selectall checkbox
		// and viceversa
		if ($(".selected").length == $(".selected:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}

		if ($(".selected:checked").length > 0) {
			$('#edit').attr("disabled", false);
		} else {
			$('#edit').attr("disabled", true);
		}
	}
	$(document).ready(function() {
		$("input[type=submit]").button().click(function(event) {

		});
	});
	$(document).ready(function() {
		$("input[type=a]").button().click(function(event) {
			event.preventDefault();
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("ul.pagination1").quickPagination({
			pageSize : "10"
		});
		$("ul.pagination2").quickPagination({
			pageSize : "10"
		});

	});
</script>
<script type="text/javascript" charset="utf8">
	$(document).ready(function() {
		activeTable = $('.dataTable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bAutoWidth" : false
		});
	});
	$(document).ready(function() {
		$("input[type=button]").button().click(function(event) {
			event.preventDefault();
		});
	});
</script>




<br />



<div class="container">
	<c:choose>
		<c:when test="${not empty components}">

			<form method="POST" id="componentform">

				<input type=button
					onClick="location.href='${pageContext.servletContext.contextPath}/auth/component/addcomponent'"
					value='Add component'
					class="ui-button ui-widget ui-state-default ui-corner-all">
				<input type="submit" name="deletecomponent" value="Delete component"
					onclick="this.form.action='${pageContext.servletContext.contextPath}/auth/component/deletecomponent'" />
				<hr />
				<table style="width: 100%" cellpadding="0" cellspacing="0"
					border="0" class="display dataTable" id="listcomponent">
					<thead>
						<tr>
							<th align="left"><input type="checkbox" id="selectall">All</th>
							<th align="left">Name</th>
							<th>Description file name</th>
							<th>Domain</th>
							<th>url</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach var="component" items="${components}">
							<tr>
								<td width="5%"><input type="checkbox" class="selected"
									name="selected" value='<c:out value="${component.id}"></c:out>' /></td>
								<td width="10%" align="left"><c:out
										value="${component.name}"></c:out></td>
								<td width="15%" align="center"><c:out
										value="${component.filename}"></c:out></td>
								<td width="15%" align="center">
								<c:forEach
										items="${component.domains}" var="domain">
										<c:out value="${domain},"></c:out>
									</c:forEach></td>

								<td width="15%" align="center"><c:out
										value="${component.url}"></c:out></td>


							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div id="dlgConfirm" title="Confirmation">Do you want to
					delete the selected components?</div>
			</form>
		</c:when>
		<c:otherwise>
			<input type=button class="submit"
				onClick="location.href='${pageContext.servletContext.contextPath}/auth/component/addcomponent'"
				value='Add component'>
			<hr />
			<br />
			No components
		</c:otherwise>
	</c:choose>
</div>
<br />

