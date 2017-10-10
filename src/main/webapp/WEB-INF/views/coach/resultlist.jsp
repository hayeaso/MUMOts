
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page session="false"%>


<div class="panel panel-success">

	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-edit"></i>Student Test Score
		
		</h3>
	</div>
	<div class="panel-body">

	<div class="portlet-body">


	<button id="exportResult"
				class="btn btn-circle btn-primary alignright">Export</button>
		<table class="table table-sm" id="sample_editable_1">
			<thead>
				<tr>
					<th>No</th>
					<th>Coach</th>
					<th>StudentID</th>
					<th>Full Name</th>
					<th>Entry</th>
					<th>Email</th>
					<th>Date Finished</th>
					<th>Score</th>


				</tr>
			</thead>

			<tbody>
				<c:forEach items="${reports}" var="report" varStatus="status">
					<tr>

						<td>${status.count}</td>
						<td>${report.key.coachId.firstName}</td>
						<td>${report.key.studentId.studentId}</td>
						<td>${report.key.studentId.firstName}
							${report.key.studentId.lastName}</td>
						<td>${report.key.studentId.entry}</td>
						<td>${report.key.studentId.email}</td>
						<td>${report.key.end_date}</td>
						<td><c:choose>
								<c:when test="${report.value>75}">
									<p class="text-success">${report.value}</p>
								</c:when>
								<c:when test="${report.value < 75}">
									<p class="text-danger">${report.value}</p>
								</c:when>

								<c:otherwise>

								</c:otherwise>
							</c:choose></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</div>
</div>
<script src="<c:url value="/metronic/assets/global/plugins/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/metronic/assets/global/plugins/jquery-migrate.min.js" />" type="text/javascript"></script>
<%-- <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"/>"></script> --%>
<%-- <script  src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value="https://code.jquery.com/ui/1.12.0-beta.1/jquery-ui.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.1.135/jspdf.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="http://cdn.uriit.ru/jsPDF/libs/adler32cs.js/adler32cs.js"/>"></script>
<script type="text/javascript" src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2014-11-29/FileSaver.min.js"/>"></script>
<%-- <script type="text/javascript" src="<c:url value="libs/Blob.js/BlobBuilder.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.addimage.js"/>"></script>
<script type="text/javascript" src="<c:url value="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.standard_fonts_metrics.js"/>"></script>
<script type="text/javascript" src="<c:url value="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.split_text_to_size.js"/>"></script>
<script type="text/javascript" src="<c:url value="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.from_html.js"/>"></script>
<script type="text/javascript" src="<c:url value="/metronic/assets/admin/pages/scripts/jquery.table2excel.js"/>"></script>

