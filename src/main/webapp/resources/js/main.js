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
	
});