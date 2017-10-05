<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="currentPage" value="${requestScope['javax.servlet.forward.request_uri']}" />
<%-- 
<c:set var="rooot" value="${pageContext.request.contextPath}" /> --%>

<div class="hor-menu">
	<ul class="nav navbar-nav">
		<li><a href="<c:url value="/admin/register" />"  ${('/onlinetest/admin/register' == currentPage) ? ' style="color: antiquewhite; background: green;"' : ''}>User Register</a></li>
		<li><a href="<c:url value="/admin/users" />" ${('/onlinetest/admin/users' == currentPage) ? ' style="color: antiquewhite; background: green;"' : ''}>User List</a></li>
		<li><a href="<c:url value="/admin/students" />" ${('/onlinetest/admin/students' == currentPage) ? ' style="color: antiquewhite; background: green;"' : ''}>Student List</a></li>
		<li><a href="<c:url value="/admin/categories" />" ${('/onlinetest/admin/categories' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Category</a></li>
		<li><a href="<c:url value="/admin/subCategories" />" ${('/onlinetest/admin/subCategories' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Sub Category</a></li>
		<li><a href="<c:url value="/admin/addquestion" />" ${('/onlinetest/admin/addquestion' == currentPage) ? ' style="color: antiquewhite; background: green;"' : ''}>Add Question</a></li>
		<li><a href="<c:url value="/admin/viewquestions" />" ${('/onlinetest/admin/viewquestions' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Question List</a></li>
		<li><a href="<c:url value="/admin/importData" />" ${('/onlinetest/admin/importData' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Import Data</a></li>
		<li><a href="<c:url value="/admin/assignments" />" ${('/onlinetest/admin/assignments' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Assignments</a></li>
		<li><a href="<c:url value="/admin/resultlist" />" ${('/onlinetest/admin/resultlist' == currentPage) ? ' style="color: antiquewhite; background: green;"'  : ''}>Test Results</a></li>
	</ul>
</div>
