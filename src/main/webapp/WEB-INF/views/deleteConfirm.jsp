<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!-- DELETE CONFIRMATION MODAL -->
<div id="delModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Delete Confirmation</h4>
			</div>
			<div class="modal-body">
				<p>Are you Sure, You want to delete ??</p>
			</div>
			<div class="modal-footer">
				<button value="" type="button" class="btn btn-primary"
					id="userId">Yes</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			</div>
		</div>
	</div>
</div>

<!-- DELETE SUCCESS MODAL -->
<div id="deleteSuccess" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Delete Success</h4>
			</div>
			<div class="modal-body">
				<p>SuccessFully Deleted</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>
<!-- END OF MODAL -->
<script>
	$(document).on("click", ".deleteButton", function() {
		var myId = $(this).data('value'); // from data-value
		console.log("delete button is clicked--- data balue is " + myId);
		$(".modal-footer #userId").val(myId);
		$(".modal-footer #userId").addClass("btnDelUser");
	});
	
	$(document).on("click", ".deleteButtonSubCat", function() {
		var myId = $(this).data('value'); // from data-value
		console.log("delete button is clicked--- data balue is " + myId);
		$(".modal-footer #userId").val(myId);
		$(".modal-footer #userId").addClass("btnDelSubCat");
	});
	$(document).on("click", ".deleteButtonCat", function() {
		var myId = $(this).data('value'); // from data-value
		console.log("delete button is clicked--- data balue is " + myId);
		$(".modal-footer #userId").val(myId);
		$(".modal-footer #userId").addClass("btnDelCat");
	});
	
	$("#userId").click(function() {
		$('#delModal').modal('hide');
		$('#deleteSuccess').modal('show');
	});
</script>