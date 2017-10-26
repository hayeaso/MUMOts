<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page session="true"%>
<div class="content">
	<div class="jumbotron">
		<h1>${description}</h1>
		<c:if test="${not empty success}">

			<c:out value="${success}" />
			Do you want to add more? <h1>${question.description}</h1>
			<div class="btn-group" role="group" aria-label="...">
				<a href="addquestion">
					<button type="button" class="btn btn-default">YES</button>
				</a> <a href="/onlinetest/${sessionScope.role}/viewquestions">
					<button type="button" class="btn btn-default">NO</button>
				</a>

			</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-warning">
				<h4>Sorry!! The question could not be added !</h4>
				<p>
					<c:out value="${error}" />
					<b>${question.description}</b>
				</p>
			</div>
		</c:if>
		<c:if test="${empty success}">
			<form:form modelAttribute="question">
				<h3>Add Question</h3>
				<p class="hint">Enter Question and its choices below:</p>


				<div class="form-group">
					<form:label path="category"
						class="control-label visible-ie8 visible-ie9">Category Name</form:label>
					<form:select id="idCategory" path="category"
						class="form-control placeholder-no-fix"
						placeholder="Choice Category" multiple="true">

						<form:option value="" label="Select Category" />
						<form:options items="${categories}" itemLabel="name"
							itemValue="id" />

					</form:select>
				</div>
				<div class="form-group">
					<form:label path="subcategory.id"
						class="control-label visible-ie8 visible-ie9">Sub category</form:label>
					<div id="subCat">
						<form:select path="subcategory.id"
							class="form-control placeholder-no-fix" multiple="true"
							itemValue="id" itemLabel="name">
							<form:option value="" label="Sub Categories" />
						</form:select>
					</div>

				</div>

				<div class="form-group">
					<form:label path="description"
						class="control-label visible-ie8 visible-ie9">Question</form:label>
					<form:input path="description" id="description"
						class="form-control placeholder-no-fix" type="text"
						placeholder="Type the Question" />
					<form:errors path="description" cssClass="text-danger" />

				</div>


				<ol type="A">
					<p class="text-right" id="input">Correct Answer(select Only
						one)</p>
					<c:forEach items="${question.choices}" var="choice" varStatus="i">

						<div class="form-group">
							<div class="row">
								<div class="col-md-1 ">
									<span class="col-md-2 col-md-offset-5"><li></li></span>
								</div>
								<div class="col-md-10">

									<form:label path="choices[${i.index}].description"
										class="control-label visible-ie8 visible-ie9">Question</form:label>
									<form:input path="choices[${i.index}].description"
										class="form-control placeholder-no-fix"
										placeholder="Type the Choice" />


									<form:errors path="choices[${i.index}].description"
										cssClass="text-danger" />

								</div>
								<div class="col-md-1 " id="checkbox">
									<span class="col-md-2 col-md-offset-1"> <form:checkbox
											class="icheck" path="choices[${i.index}].answer"
											onchange="disableSubmit()" />
									</span>
								</div>

							</div>
						</div>

					</c:forEach>
				</ol>

				<div class="form-actions alignright">
					<button type="submit" class="btn btn-primary btn-mini"
						id="BtnForSubmit" class="btn btn-success uppercase pull-right">Submit</button>
					<a href="<c:url value='/admin/viewquestions'/>"> <input
						style="margin-right: 20px;" type="button" id="register-cancel-btn"
						class="btn btn-primary  alignleft" value="Cancel" /></a>
				</div>
			</form:form>
		</c:if>
	</div>
	<div id="result"></div>

</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
	var disableSubmit = function myFunction() {

		var numClicked = $("input:checkbox:checked").length;
		if (numClicked != 1) {
			$('#BtnForSubmit').attr('disabled', 'disabled');
		} else {

			$('#BtnForSubmit').removeAttr('disabled');
		}
	};
	
	$(document).ready(function() {
		$('#BtnForSubmit').attr('disabled', 'disabled');
		$('#idCategory').change(function(event) {
			var id = $('#idCategory').val();

			$.ajax({
				type : 'POST',
				url : '/onlinetest/${sessionScope.role}/subcategories/' + id,
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				success : function(data) {
					$('#subCat').empty();
					$('#subCat').append(data.subcat);
				},
				error : function(jqXHR, status, err) {

				}
			});

		});
	});
</script>