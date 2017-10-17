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

			eventsHandler : function() {
				this.clicksHandler();
				this.changesHandler();
			},

			clicksHandler : function() {
				var _this = this;
				_this.click($("input[id='Checkboxes_SelectAll']"), function(e) {
					// e.preventDefault();
					var $this = $(this);
					_this.debugBtn(this);
					_this.toggleCheckbox_SelectAll($this, this.checked);
				});
			},

			changesHandler : function() {
				var _self = this;
			},
			
			toggleCheckbox_SelectAll: function($checkboxesID, status){
				$.each($checkboxesID.find("input"), function(index, el){
					$(el).prop("checked", status);
				});
			},

			ajaxErrorMsg: function(xhr, textStatus, errorThrown) {		
				//return " code: " + xhr.status +" Unexpected error: "+errorThrown+" textStatus: "+textStatus;
				console.log (" code: " + xhr+" Unexpected error: "+errorThrown+" textStatus: "+textStatus);
			}
		}
		
		window.MUMOTS = MUMOTS;
		$(document).ready(function(){
			MUMOTS.init();
		});

});