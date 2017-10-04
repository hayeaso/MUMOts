<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="content">
	<div class="portlet light">
		<c:if test="${not empty success}">
		<div class="alert alert-success" >
			<strong>Success!</strong> Student edited!
		</div>
		</c:if>
		<c:if test="${not empty error}">
		<div class="alert alert-warning" >
			<strong>Warning!</strong> Student ID is duplicated!
		</div>
		</c:if>
		<!-- BEGIN REGISTRATION FORM -->
		<form:form method="POST" class="register-form"
			action="../../${sessionScope.role}/editStudent" modelAttribute="student">
			<h3>Sign Up</h3>
			<p class="hint">Enter student information below:</p>
			<form:hidden path="userId" name="userId" value="${student.userId}" />
			<div class="form-group">
				<form:label path="firstName"
					class="control-label visible-ie8 visible-ie9">First Name</form:label>
				<form:input path="firstName" class="form-control placeholder-no-fix"
					type="text" placeholder="First Name" name="firstName" value="${student.firstName}" />
				<form:errors path="firstName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName"
					class="control-label visible-ie8 visible-ie9">Last Name</form:label>
				<form:input path="lastName" class="form-control placeholder-no-fix"
					type="text" placeholder="Last Name" name="lastName" value="${student.lastName}"/>
				<form:errors path="lastName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="email"
					class="control-label visible-ie8 visible-ie9">Email</form:label>
				<form:input path="email" class="form-control placeholder-no-fix"
					type="text" placeholder="Email" name="email" value="${student.email}"/>
				<form:errors path="email" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="studentId"
					class="control-label visible-ie8 visible-ie9">Student ID</form:label>
				<form:input path="studentId" class="form-control placeholder-no-fix"
					type="text" placeholder="Student ID" name="studentId" value="${student.studentId}"/>
				<form:errors path="studentId" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="entry"
					class="control-label visible-ie8 visible-ie9">Entry Year</form:label>
				<form:input path="entry" class="form-control placeholder-no-fix"
					type="number" placeholder="Entry year" name="entry" value="${student.entry}" />
				<form:errors path="entry" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label class="control-label" path="jobSearchStatus">Job search status</form:label> 
				<form:select
					class="select2_category form-control category"
					path="jobSearchStatus" name="jobSearchStatus"
					data-placeholder="Choose a status" tabindex="1">
					<c:if test="${student.jobSearchStatus}">
						<form:option value="True" selected="selected">Active</form:option>
						<form:option value="False">Inactive</form:option>
					</c:if>					
					<c:if test="${student.jobSearchStatus ne true}">
						<form:option value="True">Active</form:option>
						<form:option value="False" selected="selected">Inactive</form:option>
					</c:if>					
				</form:select>
				<form:errors path="jobSearchStatus" cssClass="text-danger" />
			</div>			
			<form:hidden path="enabled" value="TRUE" />
			<div class="form-actions">
				<button type="submit" id="register-submit-btn"
					class="btn btn-success uppercase pull-right">Update</button>
			</div>
			<br/><br/>
		</form:form>
	</div>
</div>
