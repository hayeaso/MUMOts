<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:set var="currentPage"
	value="${requestScope['javax.servlet.forward.request_uri']}" />

<c:set var="pageParts" value="${fn:split(currentPage, '/')}" />
<c:set var="pageWithoutParam"
	value="/${pageParts[0]}/${pageParts[1]}/${pageParts[2] }" />
	
<nav class="navbar navbar-default">
	<div class="container-fluid">



		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>




		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="navbar-collapse collapse in"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<li><a href="<c:url value="/coach/home" />"
					${('/onlinetest/coach/home' == currentPage || '/onlinetest/coach/studentAssignmentDetail' == pageWithoutParam ) ? ' class="activeNavItem"' : ''}>Home</a></li>
				<li><a href="<c:url value="/coach/students" />"
					${('/onlinetest/coach/students' == currentPage || '/onlinetest/coach/registerStudent' == currentPage || '/onlinetest/coach/importStudentData' == currentPage || '/onlinetest/coach/editStudent' == pageWithoutParam) ? ' class="activeNavItem"' : ''}>Students</a></li>
				<li><a href="<c:url value="/coach/assignments" />"
					${('/onlinetest/coach/assignments' == currentPage || '/onlinetest/coach/result' == pageWithoutParam || '/onlinetest/coach/resultDetail' == pageWithoutParam) ? ' class="activeNavItem"' : ''}>Assignments</a></li>
				<li><a href="<c:url value="/coach/resultlist" />"
					${('/onlinetest/coach/resultlist' == currentPage) ? ' class="activeNavItem"' : ''}>Result
						List</a></li>


				<li><a href="<c:url value="/coach/viewquestions" />"
					${('/onlinetest/coach/viewquestions' == currentPage ||'/onlinetest/coach/addquestion' == currentPage||'/onlinetest/coach/importData' == currentPage) ? ' class="activeNavItem"' : ''}>
						Questions</a></li>

				<li><a href="<c:url value="/help" />"
					${('/onlinetest/help' == currentPage) ? ' class="activeNavItem"' : ''}>Help</a></li>

				<%-- <li><a href="<c:url value="/coach/grading" />">Grading</a></li> --%>
			</ul>
			<!-- NAVBAR ITEMS ON THE RIGHT -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <span class="bold"><i class="fa fa-user fa-fw"></i>${sessionScope.role}</span></span>
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
