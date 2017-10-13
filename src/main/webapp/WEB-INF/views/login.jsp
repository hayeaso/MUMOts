<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please sign in</h3>
				</div>
				<div class="panel-body">
					<c:if test="${not empty error}">
						<div class="alert alert-danger">
							<spring:message
								code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
							<br />
						</div>
					</c:if>
					<form action="<spring:url value="/postLogin"></spring:url>"
						method="post">
						<fieldset>
							<div class="form-group">
								<input class="form:input-large" placeholder="User Name"
									name='username' type="text">
							</div>
							<div class="form-group">
								<input class=" form:input-large" placeholder="Password"
									name='password' type="password" value="">
							</div>
							<div class="form-group login-group-checkbox">
								<input id="lg_remember" name="lg_remember" type="checkbox">
								<label for="lg_remember">Remember Me</label>
							</div>
							<input class="btn btn-success btn-mini" type="submit"
								value="Login">
							<div class="form-group">
								<a data-toggle="modal" data-target="#myModalForForgetPass">Forgot
									Password? </a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL FOR FORGET PASSS -->
<div class="modal fade" id="myModalForForgetPass" role="dialog">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Reset password</h4>
			</div>
			<form action="<spring:url value="/postResetPassword"></spring:url>"
				method="post">
				<div class="modal-body">

					<fieldset>
						<div class="form-group">
							<input class="form:input-large" placeholder="Email" name='email'
								type="text">
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
				<button type="submit" class="btn btn-primary">Reset</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
				</div>
			</form>
		</div>
	</div>
</div>