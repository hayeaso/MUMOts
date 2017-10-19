<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

	<div class="panel panel-success">

		<div class="panel-heading">
			<h3 class="panel-title">
				<i class="fa fa-gift"></i> Student Assignment Details
			</h3>
		</div>
		<div class="panel-body form">
			<div id="errorMessage" class="alert alert-warning"
				style="display: none;"></div>
			<div id="successMessage" class="alert alert-success"
				style="display: none;"></div>
			<input type="hidden" value="${assignment.accesscode}" id="sentEmail" />
			<form action="#" class="form-horizontal">
				<div class="form-body">
					<div class="form-group">
						<label class="col-md-3 control-label">Student</label>
						<div class="col-md-3">
							<p class="form-control-static">${student.studentId}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Student Name:</label>
						<div class="col-md-3">
							<p class="form-control-static">${student.firstName}
								${student.lastName}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Student Email:</label>
						<div class="col-md-4">
							<p class="form-control-static" id="email" name="email" >${student.email}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Access Link</label>
						<div class="col-md-4">
							<div class="input-icon">
								<input type="text" class="form-control" id="accessLink"
									name="accessLink" readonly="readonly"
									value="http://localhost:8080/onlinetest/test">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Access Code</label>
						<div class="col-md-4">
							<div class="input-icon">
								<input type="text" class="form-control"
									value="${assignment.accesscode}" readonly="readonly"
									id="accessCode" name="accessCode">
							</div>
						</div>
					</div>
					
					<!-- Select day / time -->
					<div class="form-group">
						<label class="col-md-3 control-label">Date & Time</label>
						<div class="col-md-4">
							<div class="input-icon">
								<input type="text" class="form-control" id="dateTime"
									name="dateTime" value="${emailScheduler.sendEmailDateTime}" required />
							</div>
						</div>
					</div>
					
<img src="<c:url value="/resources/images/ajax-loader.gif" />" alt="Loading..." id="loading-indicator" style="display:none" />


					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="button" id="generate-test-btn"
									onclick="generateAccessCode()" name="generate-test-btn"
									class="btn btn-circle btn-primary">Generate Test</button>
								<button type="button" id="send-test-btn"
									onclick="assignmentDone(${student.userId})" name="send-test-btn"
									class="btn btn-circle btn-success" >Send Email & Save</button>
								<button type="button" id="student-assignment-cancelbtn"
									onclick="assignmentCancel()" class="btn btn-circle btn-default">Cancel</button>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		
<!-- SHOW MODAL -->

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Success!</h4>
				</div>
				<div class="modal-body">
					<p>Test is generated and data will be send to the student as scheduled!</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" OnClick="closeModalCoachAssignment()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- END OF MODAL -->

<script src="<c:url value="/metronic/assets/coach/scripts/assignment.js" />" type="text/javascript"></script>
<!-- JS for Date-Picker -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/jquery.datetimepicker.css"/ >
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/build/jquery.datetimepicker.full.min.js"></script>

<style>
	#loading-indicator {
	  position: absolute;
	  left: 10px;
	  top: 10px;
	}
</style>
<script type="text/javascript">

	jQuery('#dateTime').datetimepicker({
		timepicker: true,
		closeOnDateSelect: true,
		startDate: new Date(),
		defaultDate: new Date(),
		scrollMonth : false,
		todayButton: true,
		defaultSelect: true,
		minDate: new Date(),
		format: 'm/d/y H:00'
		//format: 'm/d/Y h:m A' /* M d y will give a different format that will be displayed in the form*/
	});
   
/* this will display am/pm format
 * format: 'm/d/y H:i A'
 format: 'Y/m/d A g:i',
 formatTime: 'A g:i',
 */

</script>