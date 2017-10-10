<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="panel panel-success">

  <div class="panel-heading">
    <h3 class="panel-title"><i class="fa fa-edit"></i>User List</h3>
  </div>
  <div class="panel-body">
  <div class="portlet-body">
<div class="table-toolbar">
			<div class="row">
				<div class="col-md-6">
					<div class="btn-group">
						<a href="register" class="btn btn-primary"><i class="fa fa-plus"></i> Add New
						</a>
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
							
							<!-- edit user profile button--->
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

<!-- SHOW MODAL -->

<c:if test="${not empty success}">
			
<!-- Modal -->
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Data Saved To Database</h4>
						</div>
						<div class="modal-body">
							<p>Successfully data saved to database.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

<!-- END OF MODAL -->
		</c:if>

<script type="text/javascript">
    $(window).on('load',function(){
        $('#myModal').modal('show');
    });
</script>

