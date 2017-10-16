$(function() {
	$('#datepick')
			.datepicker(
					{
						changeMonth : true,
						changeYear : true,
						showButtonPanel : true,
						showAnim : 'fadeIn',
						dateFormat : 'MM yy',
						monthNamesShort : [ "January", "February", "March",
								"April", "May", "June", "July", "August",
								"September", "October", "November", "December" ],
						/* display selected date when btn 'done' is clicked */
						onClose : function(dateText, inst) {
							$(this).datepicker(
									'setDate',
									new Date(inst.selectedYear,
											inst.selectedMonth, 1));
						}
					});/* to prepopulate with the default date .datepicker("setDate", new Date())*/

	/* display today's date when it's clicked */
	$('button.ui-datepicker-current').live(
			'click',
			function() {
				$.datepicker._curInst.input.datepicker('setDate', new Date())
						.datepicker('hide');
			});

});