<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Reset Password</h3>
			 	</div>
			  	<div class="panel-body">
			  	<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br />
					</div>
				</c:if>
			    	<form action="<spring:url value="/postResetPassword"></spring:url>" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form:input-large" placeholder="Email" name='email' type="text">
			    		</div>
			    		
			    		<input class="btn btn-lg btn-success btn-mini" type="submit" onclick="sendEmail(${users.userId})" value="Reset">
			    		
						<input class="btn btn-lg btn-success btn-mini" type="submit" onclick="ResetPasswordCancel()" value="Cancel">
		
			    		
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>