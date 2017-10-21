<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<c:if test="${not empty success}">
<%@ include file="/WEB-INF/views/LogPopUp.jsp"%>
</c:if>
<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>Import Data 
		</h3>
	</div>
	<div class="panel-body">
		<div class="portlet-body form">
			<c:if test="${not empty error}">
				<div class="alert alert-warning">
					<strong>Warning!</strong> ${error1}
					<br /> ${error2}
				</div>
			</c:if>
			<c:if test="${not empty success}">
				<div class="alert alert-success">
					<strong>Success!</strong> Successfully imported Data!
				</div>
			</c:if>
			<form:form method="POST" class="register-form" action="importStudentData"
				enctype="multipart/form-data">
				<div class="form-body">
					<div class="form-group last">
						<label class="col-md-3 control-label">Excel file</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon input-circle-left"> <i
									class="fa fa-file-excel-o"></i>
								</span> <input type="file" name="ExcelFile"
									class="form-control input-circle-right"
									placeholder="Email Address">
							</div>
						</div>
					</div>
				</div>
				<br />
				<div class="form-actions">
					<div class="row">
						<div class="col-md-offset-3 col-md-9">
							<button type="submit" class="btn btn-circle btn-primary"><span class="glyphicon glyphicon-upload"></span> Import</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
