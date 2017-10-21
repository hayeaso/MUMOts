<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/deleteConfirm.jsp"%>

<c:if test="${not empty success}">
	<%@ include file="/WEB-INF/views/popUp.jsp"%>
</c:if>

<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-users fa-fw"></i>Student List
		</h3>
	</div>
	<div class="panel-body">
		<div class="portlet-body">
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-10">
						<div class="form-group">

							<a href="registerStudent" class="btn btn-primary"><i
								class="fa fa-plus"></i> Add New </a> <a href="importStudentData"
								class="btn btn-success"><i class="fa fa-plus"></i> Import
								Student </a>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover table-bordered"
				id="sample_editable_1">
				<thead>
					<tr>
						<th>Student ID</th>
						<th>Full Name</th>
						<th>Email</th>
						<th>Year</th>
						<th class="tblStatusCol">Job Search Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="student">
						<tr id="user${student.userId}">
							<td>${student.studentId}</td>
							<td>${student.firstName} ${student.lastName}</td>
							<td>${student.email}</td>
							<td class="center">${student.entry}</td>
							<td class="center"><c:if test="${student.jobSearchStatus}">
								Active
							</c:if> <c:if test="${student.jobSearchStatus ne true}">
								Inactive
							</c:if></td>
							<td><a href="editStudent/${student.userId}"><i
									class="fa fa-edit"></i> Edit </a></td>
							<td><button data-value="${student.userId}" type="button"
									data-toggle="modal" data-target="#delModal"
									class="deleteButton btn btn-xs <c:out value="${student.enabled? 'btn-danger' : 'btn-primary'}"/> pull-right">
									<c:out value="${student.enabled? 'Delete' : 'Undelete'}" />
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

