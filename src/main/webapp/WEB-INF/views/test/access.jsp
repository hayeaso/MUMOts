<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>


<div class="container">
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

		<c:if test="${ empty errormessage }">
			<div class="panel panel-success">


				<div class="panel-heading">

					<div class="panel-title">Access Code</div>
				</div>


				<div class="panel panel-body" style="padding-top: 30px">
					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<div class="panel-body">
		</c:if>

		<c:if test="${ not empty errormessage }">
			<div class="panel panel-danger animated shake	">


				<div class="panel-heading">

					<div class="panel-title">Access Code</div>
				</div>


				<div class="panel panel-body" style="padding-top: 30px">
					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<div class="panel-body">
						<h3>${errormessage}</h3>
		</c:if>

		<form action="<c:url value="access"></c:url>" method="post">
			<fieldset>
				<div class="form-group">
					<label>Enter Access Code:</label><br /> <input class="form-control"
						type="text" name="access_code" />
				</div>

				<div class="form-group">
					<button class="btn btn-primary btn-mini" type="submit">Submit</button>
				</div>
			</fieldset>
		</form>
	</div>
</div>
</div>
</div>
</div>