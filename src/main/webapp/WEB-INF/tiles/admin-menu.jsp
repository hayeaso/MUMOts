<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="currentPage"
	value="${requestScope['javax.servlet.forward.request_uri']}" />
<%-- 
<c:set var="rooot" value="${pageContext.request.contextPath}" /> --%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/admin/users" />"
					${('/onlinetest/admin/users' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>User
						List</a></li>

				<li><a href="<c:url value="/admin/students" />"
					${('/onlinetest/admin/students' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Student
						List</a></li>
				<li><a href="<c:url value="/admin/categories" />"
					${('/onlinetest/admin/categories' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Category</a></li>
				<li><a href="<c:url value="/admin/subCategories" />"
					${('/onlinetest/admin/subCategories' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Sub
						Category</a></li>
				<li><a href="<c:url value="/admin/addquestion" />"
					${('/onlinetest/admin/addquestion' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Add
						Question</a></li>
				<li><a href="<c:url value="/admin/viewquestions" />"
					${('/onlinetest/admin/viewquestions' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Question
						List</a></li>
				<li><a href="<c:url value="/admin/importData" />"
					${('/onlinetest/admin/importData' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Import
						Data</a></li>
				<li><a href="<c:url value="/admin/assignments" />"
					${('/onlinetest/admin/assignments' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Assignments</a></li>
				<li><a href="<c:url value="/admin/resultlist" />"
					${('/onlinetest/admin/resultlist' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Test
						Results</a></li>

			</ul>
        
        <!-- NAVBAR ITEMS ON THE RIGHT -->
		<ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="editUser/${user.userId}"><i class="fa fa-edit"></i> Edit Profile </a></li>
            <li role="separator" class="divider"></li>
            <li><a href="<c:url value="/logout" />">Logout</a></li>
          </ul>
        </li>
      </ul>
      
      </div>
      </div>
</nav>
<%-- 
<li class="dropdown btn btn-default dropdown-toggle"><a
					href="#" class="dropdown-toggle" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false"> <span
						class="glyphicon glyphicon-cog" aria-hidden="true"></span></a>
					<ul class="dropdown-menu">
						
						
					</ul>
				</li>
 --%>