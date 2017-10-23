<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>


<div class="content">
	<div class="portlet light">
		<h2 class="margin-bottom-20">Please select 3 to 4 sub categories
		</h2>
		<form:form modelAttribute="categoryDto" method="POST"
			action="setcategories/">
			<c:forEach items="${categoryDto.categories}" var="cats">
		        ${cats.name}<br />
				<c:forEach items="${cats.subcategories}" var="subcats">
					<c:if test="${subcats.enabled}">
						<form:checkbox name="selectedSubCategories"
							path="selectedSubCategories" value="${subcats.id}" />
						${subcats.name}<br />
					</c:if>
				</c:forEach>

				<hr />

			</c:forEach>
			<button class="btn btn-success btnSubmitCat" style="display: none;"
				type="submit">Submit</button>
		</form:form>
	</div>
</div>
<script>
	jQuery(document).ready(function() {
		$("input[type='checkbox']").change(function() {
			var count = $("[type='checkbox']:checked").length;
			if (count >= 3 && count <= 4) {
				$(".btnSubmitCat").show();
			} else {
				$(".btnSubmitCat").hide();
			}
		});
	});
</script>