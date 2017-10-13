<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ include file="/WEB-INF/views/deleteConfirm2.jsp"%>

<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>Sub Category List
		</h3>
	</div>
	<div class="panel-body">
		<div class="portlet-body">
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-6">
						<div class="btn-group">
							<a href="createSubCategory" class="btn btn-primary"> Add New
								<i class="fa fa-plus"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover table-bordered"
				id="sample_editable_1">
				<thead>
					<tr>
						<th>Sub Category</th>
						<th>Category</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${subCategories}" var="subCategory">
						<tr id="subCategory${subCategory.id}">
							<td>${subCategory.name}</td>
							<td>${subCategory.category.name}</td>
							<td>
								<button data-value="${subCategory.id}" type="button" data-toggle="modal" data-target="#delModal2"
									class="deleteButton2 btn btn-xs btn-danger pull-right">Delete</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

