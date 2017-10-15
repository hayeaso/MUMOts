<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>


<c:if test="${changeSuccess}">
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
									name='newpassword' id="newPass1" type="password">
							</div>
							<div class="form-group">
								<input class=" form:input-large" placeholder="Confirm Password"
									name='confirmpassword' id="newPass2" type="password">
							</div>
							<div id="divCheckPasswordMatch"></div>
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


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$( document ).ready(function() {
	console.log("INSIDE HERE");
   $("#newPass1, #newPass2").keyup(function(){
	   var password = $("#newPass1").val();
	    var confirmPassword = $("#newPass2").val();

	    if (password != confirmPassword)
	        $("#divCheckPasswordMatch").html("Passwords do not match!");
	    else
	        $("#divCheckPasswordMatch").html("Passwords match.");
   });
 
});
</script>