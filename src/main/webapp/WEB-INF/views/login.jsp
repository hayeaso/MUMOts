<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<style>
input:checked + label { color: green; }
</style>
<div class="container">
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<c:if test="${not empty error}">
			<div class="panel panel-success animated shake"
				style="border-color: red;">
		</c:if>
		<c:if test="${empty error}">
			<div class="panel panel-success">
		</c:if>

		<div class="panel-heading">
			<div class="panel-title">Sign In</div>
		</div>


		<div class="panel panel-body" style="padding-top: 30px">
			<div style="display: none" id="login-alert"
				class="alert alert-danger col-sm-12"></div>

			<div class="panel-body">
				<c:if test="${not empty foundEmail}">
					<div class="alert alert-warning">
						<h4>Link Sent to your email id - ${foundEmail }</h4>
					</div>
				</c:if>
				<c:if test="${not empty changeSuccess}">
					<div class="alert alert-warning">
						<h4>Password Successfully Changed.</h4>
					</div>
				</c:if>
				<c:if test="${not empty notFoundEmail}">
					<div class="alert alert-warning">
						<h4>Email - ${ notFoundEmail } - is not found in the
							database.</h4>
					</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message
							code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
						<br />
					</div>
				</c:if>

				<form action="<spring:url value="/postLogin"></spring:url>"
					method="post" class="form-horizontal" role="form">
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-user"></i></span> <input id="login-username"
							type="text" class="form-control" name="username" value=""
							placeholder="username">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i
							class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
							type="password" class="form-control" name="password"
							placeholder="password">
					</div>



					<!-- <div class="input-group"> -->
						<!-- <div class="checkbox">  -->
							
							<input id="login-remember"
								type="checkbox" name="keepMe" />
								<label for="lg_remember">  Remember me
							</label>
						<!-- </div> -->
					<!-- </div>  -->


					<div style="margin-top: 10px" class="form-group">
						<!-- Button -->

						<div class="col-sm-12 controls">
							<input class="btn btn-success btn-mini" type="submit"
								value="Login">

						</div>
					</div>


					<div class="form-group">
						<div class="col-md-12 control">
							<div
								style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
								Forget Password ! <a data-toggle="modal"
									data-target="#myModalForForgetPass"> Click Here </a>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

</div>
</div>
<%--  
<div class="container">

	<div class="row">
		<div class="col-md-4 col-md-offset-4">
		<c:if test="${not empty error}">
		<div class="panel panel-default animated shake" style="border-color:red;">
		</c:if>
		<c:if test="${empty error}">
			<div class="panel panel-default">
			</c:if>
				<div class="panel-heading">
					<h3 class="panel-title">Please sign in</h3>
				</div>
				<div class="panel-body">
				<c:if test="${not empty foundEmail}">
						<div class="alert alert-warning">
							<h4> Link Sent to your email id  - ${ FoundEmail }.</h4>
						</div>
					</c:if>
				<c:if test="${not empty changeSuccess}">
						<div class="alert alert-warning">
							<h4>Password Successfully Changed.</h4>
						</div>
					</c:if>
					<c:if test="${not empty notFoundEmail}">
						<div class="alert alert-warning">
							<h4>Email - ${ notFoundEmail } - is not found in the database.</h4>
						</div>
					</c:if>
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
								<input name="keepMe" type="checkbox">
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
--%>
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
							<input class="form:input-large form-control" placeholder="Email" name='email'
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