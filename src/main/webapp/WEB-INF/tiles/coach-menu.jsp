<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:set var="currentPage"
	value="${requestScope['javax.servlet.forward.request_uri']}" />

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

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-2">
			<ul class="nav navbar-nav">

				<li><a href="<c:url value="/coach/home" />"
					${('/onlinetest/coach/home' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Home</a></li>
				<li><a href="<c:url value="/coach/assignments" />"
					${('/onlinetest/coach/assignments' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Assignments</a></li>
				<li><a href="<c:url value="/coach/resultlist" />"
					${('/onlinetest/coach/resultlist' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Result
						List</a></li>
				<li><a href="<c:url value="/coach/students" />"
					${('/onlinetest/coach/students' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Students</a></li>
				<li><a href="<c:url value="/coach/addquestion" />"
					${('/onlinetest/coach/addquestion' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Add
						Question</a></li>
				<li><a href="<c:url value="/coach/viewquestions" />"
					${('/onlinetest/coach/viewquestions' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>View
						Questions</a></li>
				<li><a href="<c:url value="/coach/importData" />"
					${('/onlinetest/coach/importData' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Import
						Data</a></li>
				<li><a href="<c:url value="/help" />"
					${('/onlinetest/help' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Help</a></li>

				<%-- <li><a href="<c:url value="/coach/grading" />">Grading</a></li> --%>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown btn btn-default dropdown-toggle"><a href="#"
			class="dropdown-toggle" data-toggle="dropdown" role="button"
			aria-haspopup="true" aria-expanded="false"> <span
				class="glyphicon glyphicon-cog" aria-hidden="true"></span></a>
			<ul class="dropdown-menu">
				<li><a href="editUser/${user.userId}"><i class="fa fa-edit"></i>
						Edit Profile </a></li>
				<li><a href="<c:url value="/logout" />">Logout</a></li>
			</ul>
		</li>

			</ul>
		</div>
	</div>
</nav>
