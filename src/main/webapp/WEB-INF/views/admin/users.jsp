<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/deleteConfirm.jsp"%>
<!-- SHOW MODAL -->

<c:if test="${not empty success}">
<%@ include file="/WEB-INF/views/popUp.jsp"%>
</c:if>

<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>User List
		</h3>
	</div>
	<div class="panel-body">
		<div class="portlet-body">
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-6">
						<div class="btn-group">
							<a href="register" class="btn btn-primary"><i
								class="fa fa-plus"></i> Add New </a>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover table-bordered"
				id="sample_editable_1">
				<thead>
					<tr>
						<th>Username</th>
						<th>Full Name</th>
						<th>Email</th>
						<th>Role</th>
						<th>Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<c:if test="${user.authorities[0].authority != null}">
							<tr id="user${user.userId}">
								<td>${user.username}</td>
								<td>${user.firstName}${user.lastName}</td>
								<td>${user.email}</td>
								<td class="center">${user.authorities[0].authority}</td>

								<td class="center"><c:if test="${user.status}">
									Active
								</c:if> <c:if test="${user.status ne true}">
									Inactive
								</c:if></td>
								<!-- edit user profile button-->
								<td><a href="editUser/${user.userId}"><i
										class="fa fa-edit"></i> Edit </a></td>

								<td><button data-value="${user.userId}" type="button" data-toggle="modal" data-target="#delModal"
										class="deleteButton btn btn-xs btn-danger pull-right">Delete</button></td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>




