<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<div class="content">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-gift"></i>Student Assignment Detail
			</div>
		</div>
		<div class="portlet-body form">
		<div id="errorMessage" class="alert alert-warning" style="display:none;"></div>
		<div id="successMessage" class="alert alert-success" style="display:none;"></div>
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
					<p class="form-control-static">${student.firstName} ${student.lastName}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Student Email:</label>
				<div class="col-md-4">
					<p class="form-control-static" id="email" name="email">${student.email}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Access Link</label>
				<div class="col-md-4">
					<div class="input-icon">
						<input type="text" class="form-control" id="accessLink" name="accessLink" readonly="readonly" 
						value="http://localhost:8080/onlinetest/test">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label">Access Code</label>
				<div class="col-md-4">
					<div class="input-icon">
						<input type="text" class="form-control" value="${assignment.accesscode}" readonly="readonly"
							id="accessCode" name="accessCode" >
					</div>
				</div>
			</div>

					<!-- Select day / time -->
					<div class="form-group">
						<label class="col-md-3 control-label">Date & Time</label>
						<div class="col-md-4">
							<div class="input-icon">
								<div id="datetimepicker" class="input-group input-append date">
									<input type="text" class="form-control" id="dateTime" name="dateTime" ></input> 
									<span class="input-group-addon add-on">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
				
							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" id="generate-test-btn"
											onclick="generateAccessCode()" name="generate-test-btn"
											class="btn btn-circle blue">Generate Test</button>
										<button type="button" id="send-test-btn"
											onclick="sendEmail(${student.userId})" name="send-test-btn"
											class="btn btn-circle blue">Send Email & Save</button>
										<button type="button" id="student-assignment-cancelbtn"
											onclick="assignmentCancel()" class="btn btn-circle blue">Cancel</button>
									</div>
								</div>
							</div></form>
	</div>
	</div>
</div>
<script src="<c:url value="/metronic/assets/coach/scripts/assignment.js" />" type="text/javascript"></script>
<!-- JS for Date-Picker -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"> </script>
<script type="text/javascript" src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js"></script>


<script type="text/javascript">
$(function () {     
	$('#datetimepicker').datetimepicker({
        format: 'MM/dd/yyyy hh:mm',
        language: 'en',
        autoclose: true, 
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-arrow-up",
            down: "fa fa-arrow-down"
        }
      })
});

</script>
      
      
      
      
