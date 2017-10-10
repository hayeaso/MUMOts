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
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
			</button>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-2">
			<ul class="nav navbar-nav">

				<li><a href="<c:url value="/dba/addquestion" />"
					${('/onlinetest/dba/addquestion' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Add Question
				</a></li>

				<li><a href="<c:url value="/dba/viewquestions" />"
					${('/onlinetest/dba/viewquestions' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Question List
				</a></li>
				<li><a href="<c:url value="/dba/importData" />"
					${('/onlinetest/dba/importData' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Import Data
				</a></li>
				<li><a href="<c:url value="/help" />"
					${('/onlinetest/help' == currentPage) ? ' style="color: #fdfdfd; background: #28bb9c;"' : ''}>Help
				</a></li>
				
				
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout" />">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>