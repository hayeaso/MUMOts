<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="currentPage"
	value="${requestScope['javax.servlet.forward.request_uri']}" />

<c:set var="pageParts" value="${fn:split(currentPage, '/')}" />
<c:set var="pageWithoutParam"
	value="/${pageParts[0]}/${pageParts[1]}/${pageParts[2] }" />
<%-- 
<c:set var="rooot" value="${pageContext.request.contextPath}" /> --%>

<nav class="navbar navbar-default">

	<div class="container-fluid">

		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse" aria-expanded="true">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>


		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="navbar-collapse collapse in"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/admin/users" />"
					${('/onlinetest/admin/users' == currentPage || '/onlinetest/admin/register' == currentPage || '/onlinetest/admin/editUser' == pageWithoutParam) ? ' class="activeNavItem"' : ''}>User
						List</a></li>

				<li><a href="<c:url value="/admin/students" />"
					${('/onlinetest/admin/students' == currentPage || '/onlinetest/admin/registerStudent' == currentPage|| '/onlinetest/admin/importStudentData' == currentPage || '/onlinetest/admin/editStudent' == pageWithoutParam) ? ' class="activeNavItem"' : ''}>Student
						List</a></li>
				<li><a href="<c:url value="/admin/categories" />"
					${('/onlinetest/admin/categories' == currentPage || '/onlinetest/admin/createCategory' == currentPage) ? ' class="activeNavItem"'  : ''}>Category</a></li>
				<li><a href="<c:url value="/admin/subCategories" />"
					${('/onlinetest/admin/subCategories' == currentPage || '/onlinetest/admin/createSubCategory' == currentPage) ? ' class="activeNavItem"'  : ''}>Sub
						Category</a></li>

				<li><a href="<c:url value="/admin/viewquestions" />"
					${('/onlinetest/admin/viewquestions' == currentPage|| '/onlinetest/admin/addquestion' == currentPage || '/onlinetest/admin/importData' == currentPage) ? ' class="activeNavItem"'  : ''}>Questions
				</a></li>

				<li><a href="<c:url value="/admin/assignments" />"
					${('/onlinetest/admin/assignments' == currentPage || '/onlinetest/admin/result' == pageWithoutParam || '/onlinetest/admin/resultDetail' == pageWithoutParam) ? ' class="activeNavItem"'  : ''}>Assignments</a></li>
				<li><a href="<c:url value="/admin/resultlist" />"
					${('/onlinetest/admin/resultlist' == currentPage) ? ' class="activeNavItem"'  : ''}>Test
						Results</a></li>
				<li><a href="<c:url value="/help" />"
					${('/onlinetest/help' == currentPage) ? ' class="activeNavItem"' : ''}>Help</a></li>
			</ul>

			<!-- NAVBAR ITEMS ON THE RIGHT -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <span class="bold"><i class="fa fa-user fa-fw"></i>${sessionScope.role}</span>
						<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="editProfile"><i class="fa fa-edit"></i> Edit
								Profile </a></li>
						<li role="separator" class="divider"></li>
						<li><a href="<c:url value="/logout" />"><i
								class="glyphicon glyphicon-log-out"></i>Logout</a></li>
					</ul></li>
			</ul>
		</div>



	</div>

</nav>

