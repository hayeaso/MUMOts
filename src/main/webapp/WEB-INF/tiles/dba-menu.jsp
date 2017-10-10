<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="hor-menu ">
	<ul class="nav navbar-nav">
		<li><a href="<c:url value="/dba/addquestion" />">Add Question</a></li>
		<li><a href="<c:url value="/dba/viewquestions" />">Question
				List</a></li>
		<li><a href="<c:url value="/dba/importData" />">Import Data</a></li>
		<li><a href="<c:url value="/help" />">Help</a></li>


	</ul>
	<button type="button" class="btn btn-default dropdown-toggle"
		data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
	</button>
	<ul class="dropdown-menu">

		<td><a href="editUser/${user.userId}"><i class="fa fa-edit"></i>
				Edit Profile </a></td>
		<li><a href="<c:url value="/logout" />">Logout</a></li>

	</ul>
</div>