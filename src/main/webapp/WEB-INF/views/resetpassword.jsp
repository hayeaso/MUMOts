<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<c:if test="${not empty success}">
<%@ include file="/WEB-INF/views/popUp.jsp"%>
</c:if>

<div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Reset Password</h3>
			 	</div>
			  	<div class="panel-body">
			  	<c:if test="${not empty error}">
			<div class="alert alert-warning">
				Password do not match
			</div>
		</c:if>
			  	
			  	
			  	
			  	
			  	<c:if test="${not empty error}">
					<div class="alert alert-danger">
						<spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br />
					</div>
				</c:if>
			    	<form action="<spring:url value="/resetPassword"></spring:url>" method="post">
                    <fieldset>
                    
                    		<div class="form-group">
								<input class=" form:input-large"
									name='userAccessCode' type="hidden" value="${accessCode}">
							</div>
			    	  		<div class="form-group">
								<input class=" form:input-large" placeholder="New Password"
									name='newpassword' type="password" value="">
							</div>
							<div class="form-group">
								<input class=" form:input-large" placeholder="Confirm Password"
									name='confirmpassword' type="password" value="">
							</div>
			    			<div class="form-actions">
								<button type="submit" id="register-submit-btn"
									class="btn btn-success">Reset Password</button>
							</div>
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>