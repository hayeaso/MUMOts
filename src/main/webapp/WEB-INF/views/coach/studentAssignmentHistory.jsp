<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>Category List
		</h3>
	</div>
	<div class="panel-body">
		<div class="caption">
			<b> Student ID: ${student.studentId} </b>
		</div>

		<div class="caption">
			<b> Student Name: ${student.firstName} ${student.lastName} </b>
		</div>
		<div class="caption">
			<b> Entry: ${student.entry} </b>
		</div>

		<table class="table table-striped table-hover table-bordered"
			id="sample_editable_1">
			<thead>
				<tr>
					<th>Assignment ID</th>
					<th>Coach Name</th>
					<th>Access Code</th>
					<th>Start Time</th>
					<th>End Time</th>
					<th>Finished</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${assignments}" var="assignment">
					<tr id="user${assignment.id}">
						<td>${assignment.id}</td>
						<td>${assignment.coachId.firstName}
							${assignment.coachId.lastName}</td>
						<td>${assignment.accesscode}</td>
						<td>${assignment.start_date}</td>
						<td>${assignment.end_date}</td>
						<td>${assignment.finished}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

