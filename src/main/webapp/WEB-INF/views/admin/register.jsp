<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<div class="content">
	<!-- <div class="portlet light"> -->
	<div class="jumbotron">
		<c:if test="${not empty error}">
			<div class="alert alert-dismissible alert-warning">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Oh snap!</strong> <a href="#" class="alert-link">Username
					already exists. </a> Choose different username.
			</div>
		</c:if>
		
		<!-- BEGIN REGISTRATION FORM -->
		<form:form method="POST" class="register-form" action="register"
			modelAttribute="loginUser">
			<h3>Add New User</h3>
			<p class="hint">Enter personal details below:</p>
			<div class="form-group">
				<form:label path="firstName"
					class="control-label visible-ie8 visible-ie9">First Name</form:label>
				<form:input path="firstName" class="form-control placeholder-no-fix"
					type="text" placeholder="First Name" name="firstName" value="${model.getFirstName() }"/>
				<form:errors path="firstName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName"
					class="control-label visible-ie8 visible-ie9">Last Name</form:label>
				<form:input path="lastName" class="form-control placeholder-no-fix"
					type="text" placeholder="Last Name" name="lastName" value="${model.getLastName() }" />
				<form:errors path="lastName" cssClass="text-danger" />
			</div>

			<div class="form-group">
				<form:label path="email"
					class="control-label visible-ie8 visible-ie9">Email</form:label>
				<form:input path="email" class="form-control placeholder-no-fix"
					type="text" placeholder="Email" name="email" value="${model.getEmail() }"/>
				<form:errors path="email" cssClass="text-danger" />
			</div>

			<p class="hint">Enter your account details below:</p>
			<div class="form-group">
				<form:label path="username"
					class="control-label visible-ie8 visible-ie9">Username</form:label>
				<form:input path="username" class="form-control placeholder-no-fix"
					type="text" autocomplete="off" placeholder="User Name" value="${model.getUsername() }"
					name="username" />
				<form:errors path="username" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="password"
					class="control-label visible-ie8 visible-ie9">Password</form:label>
				<form:input path="password" class="form-control placeholder-no-fix"
					id="register_password" type="password" autocomplete="off"
					placeholder="Password" name="password" value="${model.getPassword() }"/>
				<form:errors path="password" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="authority">Role</label>
				<form:select path="authorities[0].authority"
					name="authorities[0].authority" class="form-control">
					<form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>
					<form:option value="ROLE_COACH">ROLE_COACH</form:option>
					<form:option value="ROLE_DBA">ROLE_DBA</form:option>
				</form:select>
				<form:errors path="authorities[0].authority" cssClass="text-danger" />
			</div>
			<form:hidden path="enabled" value="TRUE" />
			<div class="form-actions">
				<button type="submit" id="register-submit-btn"
					class="btn btn-success uppercase pull-right">Submit</button>
			</div>
			<div class="form-actions">
				<a href="<c:url value='/admin/users'/>"> <input
					style="margin-right: 30px;" type="button" id="register-cancel-btn"
					class="btn btn-success uppercase pull-right" value="Cancel" /></a>
			</div>
			<br />
			<br />
		</form:form>
	</div>
</div>

