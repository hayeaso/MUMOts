<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="content">
	
		<!-- BEGIN REGISTRATION FORM -->
			<c:if test="${not empty success}">
			<div class="alert alert-success" >
				Email sent!
			</div>
			</c:if>
			<c:if test="${not empty error}">
			<div class="alert alert-warning" >
				Can not send the email right now, please try again
			</div>
			</c:if>
		<form:form method="POST" class="register-form" action="contactus"
			modelAttribute="contactus">
			<h3>Comment Below</h3>
			<p class="hint">Enter your details below:</p>
			<div class="form-group">
				<form:label path="name"
					class="control-label visible-ie8 visible-ie9"> Full Name</form:label>
				<form:input path="name" class="form-control placeholder-no-fix"
					type="text" placeholder="First Name" name="name" value="${contactus.name}"/>
				 
			</div>
			

			<div class="form-group">
				<form:label path="email"
					class="control-label visible-ie8 visible-ie9">Your Email</form:label>
				<form:input path="email" class="form-control placeholder-no-fix"
					type="text" placeholder="Email" name="email" value="${contactus.email}"/>
				
			</div>

			<p class="hint">Enter your  details below:</p>
			<div class="form-group">
			
				<form:label path="comment"
					class="control-label visible-ie8 visible-ie9"></form:label>
				<form:textarea path="comment" class="form-control placeholder-no-fix"
					type="textarea" cols="50" rows="10"  autocomplete="off" placeholder="comment" value="${contactus.comment}"
					name="comment" />
				
			</div>
			
			<div class="form-actions">
				<button type="submit" id="submit-btn"
					class="btn btn-success uppercase pull-right">Submit</button>
			</div>
			<br/><br/>
		</form:form>
	</div>

