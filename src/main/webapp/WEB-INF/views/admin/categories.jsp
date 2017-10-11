<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>Category List
		</h3>
	</div>
	<div class="panel-body">
		<div class="portlet-body">
			<div class="table-toolbar">
				<div class="row">
					<div class="col-md-6">
						<div class="btn-group">
							<a href="createCategory" class="btn btn-primary"> Add New <i
								class="fa fa-plus"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover table-bordered"
				id="sample_editable_1">
				<thead>
					<tr>
						<th>Category Name</th>
						<th>Sub Category count</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="category">
						<tr id="category${category.id}">
							<td>${category.name}</td>
							<td>${category.subCategoryCount}</td>
							<td><c:if test="${category.subCategoryCount == 0}">
									<button value="${category.id}" type="button"
										class="btnDelCat btn btn-xs btn-default pull-right">Delete</button>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
