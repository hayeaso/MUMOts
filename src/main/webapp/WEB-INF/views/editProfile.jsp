<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="content">
	<div class="portlet light">
		<c:if test="${not empty success}">
		<div class="alert alert-success" >
			<strong>Success!</strong> User updated!
		</div>
		</c:if>
		<c:if test="${not empty error}">
		<div class="alert alert-warning" >
			<strong>Warning!</strong> Username is duplicated!
		</div>
		</c:if>
		<!-- BEGIN REGISTRATION FORM -->
		<form:form method="POST" class="register-form" action="editProfile/submit"
			modelAttribute="loginUser">
			<h3>Edit User</h3>
			<p class="hint">Enter user details below:</p>
			<form:hidden path="userId" name="userId" value="${user.userId}" />
			<div class="form-group">
				<form:label path="firstName"
					class="control-label visible-ie8 visible-ie9">First Name</form:label>
				<form:input path="firstName" class="form-control placeholder-no-fix"
					type="text" placeholder="First Name" name="firstName" value="${user.firstName}"/>
				<form:errors path="firstName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName"
					class="control-label visible-ie8 visible-ie9">Last Name</form:label>
				<form:input path="lastName" class="form-control placeholder-no-fix"
					type="text" placeholder="Last Name" name="lastName" value="${user.lastName}"/>
				<form:errors path="lastName" cssClass="text-danger" />
			</div>

			<div class="form-group">
				<form:label path="email"
					class="control-label visible-ie8 visible-ie9">Email</form:label>
				<form:input path="email" class="form-control placeholder-no-fix"
					type="text" placeholder="Email" name="email" value="${user.email}"/>
				<form:errors path="email" cssClass="text-danger" />
			</div>

			<p class="hint">Edit user details below:</p>
			<div class="form-group">
				<form:label path="username"
					class="control-label visible-ie8 visible-ie9">Username</form:label>
				<form:input path="username" class="form-control placeholder-no-fix"
					type="text" autocomplete="off" placeholder="User Name" value="${user.username}"
					name="username" />
				<form:errors path="username" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="password"
					class="control-label visible-ie8 visible-ie9">Password</form:label>
				<form:input path="password" class="form-control placeholder-no-fix"
					id="register_password" type="password" autocomplete="off"
					placeholder="Password" name="password" />
				<form:errors path="password" cssClass="text-danger" />
			</div>		
			<div class="form-group">
				<label class="control-label col-lg-2" for="status">Status</label>
				<form:select path="status"
					name="status" class="form-control">
					<c:choose>
						<c:when test="${user.status}">						
							<form:option value="False">Inactive</form:option>
							<form:option value="True" selected="selected">Active</form:option>
						</c:when>
						<c:otherwise>							
							<form:option value="False" selected="selected">Inactive</form:option>
							<form:option value="True">Active</form:option>
						</c:otherwise>
					</c:choose>
				</form:select>
				<form:errors path="status" cssClass="text-danger" />
			</div>
			<form:hidden path="enabled" value="TRUE" />
			<div class="form-actions">
				<button type="submit" id="register-submit-btn"
					class="btn btn-success uppercase pull-right">Submit</button>
			</div>
			<div class="form-actions">
				<a href="<c:url value='/admin/users'/>">
 				<input style="margin-right:30px;" type="button" id="register-cancel-btn" class="btn btn-success uppercase pull-right" value="Cancel"/>
 			</div>
			<br/><br/>
		</form:form>
	</div>
</div>
