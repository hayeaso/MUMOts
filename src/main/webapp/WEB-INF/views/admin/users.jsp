<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="panel panel-success">
  <div class="panel-heading">
    <h3 class="panel-title"><i class="fa fa-edit"></i>User List</h3>
  </div>
  <div class="panel-body">
  <div class="portlet-body">

		<table class="table table-striped table-hover table-bordered"
			id="sample_editable_1">
			<thead>
				<tr>
					<th>Username</th>
					<th>Full Name</th>
					<th>Email</th>
					<th>Role</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<c:if test="${user.authorities[0].authority != null}">
						<tr id="user${user.userId}">
							<td>${user.username}</td>
							<td>${user.firstName} ${user.lastName}</td>
							<td>${user.email}</td>
							<td class="center">${user.authorities[0].authority}</td>
							<td><a href="editUser/${user.userId}"><i class="fa fa-edit"></i> Edit </a></td>
							<td><button value="${user.userId}" type="button"
									class="btnDelUser btn btn-xs btn-danger pull-right">Delete</button></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

