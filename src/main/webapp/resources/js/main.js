"use strict;"

$(function() {

	var $txtCategoryName = $("form[id=Category] input#name");
	// check if user in create category page
	if ($txtCategoryName.length > 0) {
		$.get("/onlinetest/admin/list/category", function(data) {
			$("form[id=Category] input#name").autocomplete({
				source : data,
				response : function(event, ui) {
					// ui.content is the array that's about to be sent to the response callback.
					if (ui.content.length === 0) {
						//alert("The category can be added!");
					}
					closeAlertError();
				}
			})
		});
	}
	
	function closeAlertError() {
		var $closeAlert = $("div.alertContainer > a");
		if ($closeAlert.length > 0) {
			$closeAlert.click();
		}
	}
	
	var MUMOTS = {			
			init : function() {
				this.eventsHandler();
			},

			buttonDebug : true,

			debugBtn : function(btnObj) {
				if (!this.buttonDebug)	return;
				console.info("#".concat($(btnObj).attr("id")), ".".concat($(btnObj).attr("class")), "pressed");
			},

			click : function($node, func) {
				if ($node.length) {
					$node.unbind("click");
					$node.click(func);
				}
			},
			
			change: function($node, func){
				if($node.length){
					$node.unbind("change");
					$node.change(func);
				}
			},
			
			eventsHandler : function() {
				this.clicksHandler();
				this.changesHandler();
			},

			clicksHandler : function() {
				var _this = this;

				$("#exportAssignment").click(function (e) {	
					var values = [];					
					$("td.assignmentChkGroup").find("span.checked").each( function(index, el) {					
						values.push($(el).parents("td").data('id'));
					});
					console.log(values.toString());

		    		if (values.length > 0) {
			    		var	url = "/onlinetest/assignments/export";
			    		$.ajax({
			    			type:"get",
			        		url:url,
			        		data: {"ids":values.toString()},
			        		success:function(data, textStatus, xhr) {
			        		//url = CONTEXT_ROOT + "/DownloadFileServlet?fullPathFile=" + data;
			        		window.location.href = "/onlinetest//assignments/download?ids="+values.toString();
			        		}
			        	});
		    		} else {
		    			alert("Please select at least 1 record to proceed!");
		    		}
		    	});				

				_this.click($("#exportAssignmentDetail"), function(e) {
					var values = [];	
					values.push($("input[id='assignmentId']").val());
					console.log(window.location.href);
					
					$.ajax({
		    			type:"get",
		        		url:'/onlinetest/assignments/export',
		        		data: {"ids":values.toString()},
		        		success:function(data, textStatus, xhr) {		        		
		        		window.location.href = "/onlinetest/assignments/download?ids="+values.toString();
		        		}
		        	});					
	
				});
				
			},

			changesHandler : function() {
				var _this = this;
				
				_this.change($("input[id='assignments_SelectAll']"), function(e){					
					var $this = $(this);
					_this.debugBtn(this);						
					_this.toggleCheckbox_SelectAll($this.data('chks'), this.checked);
				});
			},
			
			toggleCheckbox_SelectAll: function($checkboxesID, status){				
				$.each($($checkboxesID).find("input"), function(index, el){					
					$(el).prop("checked", status);
					if (status) { // update bootStrap chk class
						$(el).parent("span").addClass("checked");
					} else {
						$(el).parent("span").removeClass("checked");
					}
				});
			},
			
			ajaxErrorMsg: function(xhr, textStatus, errorThrown) {		
				//return " code: " + xhr.status +" Unexpected error: "+errorThrown+" textStatus: "+textStatus;
				console.log (" code: " + xhr+" Unexpected error: "+errorThrown+" textStatus: "+textStatus);
			}
			
		};
		
		// check if Bootstrap modal is open for Bootstrap <= 3
		$("#delModal").on('shown.bs.modal', function(){			
			// This function to update the content popup when button is undelete
			var uid = $("button#userId").prop('value');			
			var $btnClick = $("button.deleteButton[data-value='"+uid+"']");	

			$(this).find('.modal-title').text($btnClick.text()+ " Confirmation"); // change title
			if ($btnClick.text() == "Undelete") { // if undelete button
				$(this).find('.modal-body p').text("Are you sure, you want to undelete?");								
			}					
		});
		
		window.MUMOTS = MUMOTS;
		$(document).ready(function(){
			MUMOTS.init();
		});

});