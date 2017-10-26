<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="content">
	<div class="jumbotron">
		
		<%-- <c:if test ="${not empty alertErrorMsg}">
			<div class="alert alert-error" >
				{alertErrorMsg} 
			</div>
		</c:if>	 --%>
		<!-- BEGIN REGISTRATION FORM -->
		<form:form method="POST" class="register-form"
			action="createCategory" modelAttribute="Category">
			<h3>Create new Category</h3>
			<p class="hint">Enter information below:</p>
			<div class="form-group">
				<form:label path="name"
					class="control-label visible-ie8 visible-ie9">Category Name</form:label>
				<form:input path="name" class="form-control placeholder-no-fix"
					type="text" placeholder="Category Name" name="name" />
				<form:errors path="name" cssClass="text-danger" />
			</div>
			<form:hidden path="enabled" value="TRUE" />
	
		<div class="form-group">
			<div class="form-actions">
				<button type="submit" id="register-submit-btn"
					class="btn btn-success uppercase pull-right">Submit</button>
			</div>
			
			<div class="form-actions">
				<a href="<c:url value='/admin/categories'/>"> <input
				 style="margin-right: 20px;" type="button" id="register-cancel-btn"
					class="btn btn-primary uppercase alignright" value="Cancel" /></a>
			</div>

			</div>
			<br />
			<br />
		</form:form>
	</div>
</div>
