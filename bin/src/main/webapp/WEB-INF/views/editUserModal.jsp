<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- EDIT USER MODAL -->
<div id="editModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Success!</h4>
			</div>
			<div class="modal-body">
				<p>Edit completed successfully!</p>
			</div>
			<div class="modal-footer">

				<button type="button" class="btn btn-default" data-dismiss="modal" OnClick="closeEditModal()" >Cancel</button>
			</div>
		</div>
	</div>
</div>

<!-- END OF MODAL -->
<script>

$(window).on('load', function() {
	$('#editModal').modal('show');
});

 function closeEditModal(){
	window.location.href = "../../../onlinetest/coach/students";	
    self.close();
 }
</script>