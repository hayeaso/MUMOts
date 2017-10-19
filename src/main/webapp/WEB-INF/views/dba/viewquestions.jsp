

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-book fa-fw"></i>Question List
		</h3>
	</div>
	<div class="panel-body">
	<div class="portlet-body">

		<table class="table table-striped table-hover table-bordered"
			id="sample_editable_1">
			<thead>
				<tr>
					<th>Question Id</th>
					<th>Category</th>
						<th>Sub Category</th>
					<th>Question</th>
					<th>Choices</th>
					<!-- 	<th>Category</th>-->
					<!-- <th>Edit</th> -->
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${questions}" var="question">
					<c:if test="${question!= null}">
						<tr id="${question.id}">
							<td>${question.id}</td>
							<td>${question.subcategory.category.name}</td>
							<td>${question.subcategory.name}</td>
							<td>${question.description}</td>
							<td><ol type="A">
									<c:forEach items="${question.choices}" var="choice"
										varStatus="i">
										<li><c:choose>
												<c:when test="${choice.answer== true}">
													<p class="text-success">${choice.description}</p>
													<br />
												</c:when>
												<c:otherwise>
													<p>${choice.description}</p>
													<br />
												</c:otherwise>
											</c:choose></li>
									</c:forEach>
								</ol>
								</td>
								<%-- <td><a href="<c:url value='/dba/editquestion/${question.id}' />">${question.id}</a></td>
						 
								<td><button value="${question.id}" type="button"
									class="btnDelUser btn btn-xs btn-default pull-right">Delete</button></td> --%>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>

