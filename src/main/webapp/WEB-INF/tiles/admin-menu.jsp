<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" />
<%-- 
<c:set var="rooot" value="${pageContext.request.contextPath}" /> --%>

 <nav class="navbar navbar-default">
  <div class="container-fluid"> 
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
      <ul class="nav navbar-nav">
      	 <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" ${('/onlinetest/admin/register' == currentPage || '/onlinetest/admin/users' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Users <span class="caret"></span></a>
          <ul class="dropdown-menu">
           		<li><a href="<c:url value="/admin/register" />" >Add User</a></li>
				  <li role="separator" class="divider"></li>
				<li><a href="<c:url value="/admin/users" />" >User List</a></li>
		   </ul>
        </li>
      	<li><a href="<c:url value="/admin/students" />" ${('/onlinetest/admin/students' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Student List</a></li>
		<li><a href="<c:url value="/admin/categories" />" ${('/onlinetest/admin/categories' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Category</a></li>
		<li><a href="<c:url value="/admin/subCategories" />" ${('/onlinetest/admin/subCategories' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Sub Category</a></li>
		<li><a href="<c:url value="/admin/addquestion" />" ${('/onlinetest/admin/addquestion' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Add Question</a></li>
		<li><a href="<c:url value="/admin/viewquestions" />" ${('/onlinetest/admin/viewquestions' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Question List</a></li>
		<li><a href="<c:url value="/admin/importData" />" ${('/onlinetest/admin/importData' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Import Data</a></li>
		<li><a href="<c:url value="/admin/assignments" />" ${('/onlinetest/admin/assignments' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Assignments</a></li>
		<li><a href="<c:url value="/admin/resultlist" />" ${('/onlinetest/admin/resultlist' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"'  : ''}>Test Results</a></li>
       </ul>
     
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<c:url value="/logout" />">Logout</a></li>
      </ul>
    </div>
  </div> 
</nav> 
	
