<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<c:set var="user_role" value="${sessionScope.role}"/>
<div class="content">
	<div class="jumbotron">
		<c:if test="${not empty success}">
		<div class="alert alert-success" >
			<strong>Success!</strong> Successfully added new Student!
		</div>
		</c:if>
		<c:if test="${not empty error}">
		<div class="alert alert-warning" >
			<strong>Warning!</strong> Student ID is duplicated!
		</div>
		</c:if>
		<!-- BEGIN REGISTRATION FORM -->
		<form:form method="POST" class="register-form"
			action="registerStudent" modelAttribute="student">
			
			<h3>Register New Student</h3>
			<p class="hint">Enter student information below:</p>
			<div class="form-group">
				<form:label path="firstName"
					class="control-label visible-ie8 visible-ie9">First Name</form:label>
				<form:input path="firstName" class="form-control placeholder-no-fix"
					type="text" placeholder="First Name" name="firstName" />
				<form:errors path="firstName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName"
					class="control-label visible-ie8 visible-ie9">Last Name</form:label>
				<form:input path="lastName" class="form-control placeholder-no-fix"
					type="text" placeholder="Last Name" name="lastName" />
				<form:errors path="lastName" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="email"
					class="control-label visible-ie8 visible-ie9">Email</form:label>
				<form:input path="email" class="form-control placeholder-no-fix"
					type="text" placeholder="Email" name="email" />
				<form:errors path="email" cssClass="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="studentId"
					class="control-label visible-ie8 visible-ie9">Student ID</form:label>
				<form:input path="studentId" class="form-control placeholder-no-fix"
					type="text" placeholder="Student ID" name="studentId" />
				<form:errors path="studentId" cssClass="text-danger" />
			</div>
			
			<div class="form-group">
				<form:label path="entry" class="control-label visible-ie8 visible-ie9">Entry Year</form:label>
				<form:input path="entry" class="date-picker form-control placeholder-no-fix"
					type="text" placeholder="Entry year" name="entry" id="datepick" />
				<form:errors path="entry" cssClass="text-danger" />
			</div>
			
			<div class="form-group">
				<form:label class="control-label" path="jobSearchStatus">Job search status</form:label> 
				<form:select
					class="select2_category form-control category"
					path="jobSearchStatus" name="jobSearchStatus"
					data-placeholder="Choose a status" tabindex="1">
						<form:option value="True">Active</form:option>
						<form:option value="False">Inactive</form:option>
				</form:select>
				<form:errors path="jobSearchStatus" cssClass="text-danger" />
			</div>			
			<form:hidden path="enabled" value="TRUE" />

			<div class="form-actions">
				<button type="submit" id="register-submit-btn"
					class="btn btn-success uppercase pull-right">Submit</button>
			</div>
						
			<div class="form-actions">
				<c:if test="${user_role == 'admin'}">
					<a href="<c:url value='/admin/students'/>">
 				</c:if>
 				<c:if test="${user_role == 'coach'}">
					<a href="<c:url value='/coach/students'/>">
 				</c:if>
 				<input style="margin-right:30px;" type="button" id="register-cancel-btn" class="btn btn-success uppercase pull-right" value="Cancel"/>
 			</div>			
			<br/><br/>
		</form:form>
	</div>
</div>

<script>
	$(function() {
		$('#datepick').datepicker(
				{
					changeMonth : true,
					changeYear : true,
					showButtonPanel : true,
					showAnim : 'fadeIn',
					dateFormat : 'MM yy',
					monthNamesShort : [ "January", "February", "March",
							"April", "May", "June", "July", "August",
							"September", "October", "November", "December" ],
					/* display selected date when btn 'done' is clicked */
					onClose : function(dateText, inst) {
						$(this).datepicker(
								'setDate',
								new Date(inst.selectedYear, inst.selectedMonth,
										1));
					}
				});/* to prepopulate with the default date .datepicker("setDate", new Date())*/

		/* display today's date when it's clicked */
		$('button.ui-datepicker-current').live(
				'click',
				function() {
					$.datepicker._curInst.input.datepicker('setDate',
							new Date()).datepicker('hide');
				});
	});
</script>
