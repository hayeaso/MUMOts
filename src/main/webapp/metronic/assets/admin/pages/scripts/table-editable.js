var TableEditable = function () {

    var handleTable = function () {

        function restoreRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);

            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                oTable.fnUpdate(aData[i], nRow, i, false);
            }

            oTable.fnDraw();
        }

        function editRow(oTable, nRow) {
            var aData = oTable.fnGetData(nRow);
            var jqTds = $('>td', nRow);
            jqTds[0].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[0] + '">';
            jqTds[1].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[1] + '">';
            jqTds[2].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[2] + '">';
            jqTds[3].innerHTML = '<input type="text" class="form-control input-small" value="' + aData[3] + '">';
            jqTds[4].innerHTML = '<a class="edit" href="">Save</a>';
            jqTds[5].innerHTML = '<a class="cancel" href="">Cancel</a>';
        }

        function saveRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
            oTable.fnUpdate('<a class="delete" href="">Delete</a>', nRow, 5, false);
            oTable.fnDraw();
        }

        function cancelEditRow(oTable, nRow) {
            var jqInputs = $('input', nRow);
            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
            oTable.fnUpdate('<a class="edit" href="">Edit</a>', nRow, 4, false);
            oTable.fnDraw();
        }

        var table = $('#sample_editable_1');

        var oTable = table.dataTable({

            // Uncomment below line("dom" parameter) to fix the dropdown overflow issue in the datatable cells. The default datatable layout
            // setup uses scrollable div(table-scrollable) with overflow:auto to enable vertical scroll(see: assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js). 
            // So when dropdowns used the scrollable div should be removed. 
            //"dom": "<'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r>t<'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>",

            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,

            "language": {
                "lengthMenu": " _MENU_ records"
            },
            "columnDefs": [{ // set default column settings
                'orderable': true,
                'targets': [0]
            }, {
                "searchable": true,
                "targets": [0]
            }],
            "order": [
                [0, "asc"]
            ], // set first column as a default sort by asc
            initComplete: function () {
            	// student status dropdown filter
            	var mtable = this;
            	//check if table have column status
            	var statusCol = $("th.tblStatusCol");
         	
            	if (statusCol.length > 0) {
	            	
	            	var $labelStatus = $("<label>");
	            	var $selectStatus = $("<select>");
	            	$selectStatus.attr({
	            		"id": "statusDropdown",
	            		"class": "form-control"
	            	});
	            	
	            	$selectStatus.append(new Option('All', ""));
	            	$selectStatus.append(new Option('Active', "Active"));
	            	$selectStatus.append(new Option('Inactive', "Inactive"));
	            	
	            	$labelStatus.append($selectStatus);
	            	$labelStatus.append(new Text("status"));
	            	$labelStatus.addClass("lblStatus");
	            	
	            	var $divRecord  = $("div#sample_editable_1_length");
	                if ($divRecord.length > 0) {	                	
	                	$divRecord.prepend($labelStatus);
	                	$selectStatus.on('change',function(){
		            	   var selectedValue = $(this).val();	            	   
		            	   //("^"+selectedValue+"$", 0, true); //searchValue, column, reg
		            	   mtable.api().columns('.tblStatusCol')
		                    .search( selectedValue ? '^'+selectedValue+'$' : '', true, false )
		                	.draw();
		            	});
		            }
            	}
     	
            	//var mtable2 = this;
            	//check if table have column status
            	var statusCol2 = $("th.tblStatusCol2");
            	if (statusCol2.length > 0) {
	            	
	            	var $labelStatus2 = $("<label>");
	            	var $selectStatus2 = $("<select>");
	            	$selectStatus2.attr({
	            		"id": "statusDropdown",
	            		"class": "form-control"
	            	});
	            	
	            	$selectStatus2.append(new Option('ALL', ""));
	            	$selectStatus2.append(new Option('Started', "S"));
	            	$selectStatus2.append(new Option('Finished', "F"));
	            	$selectStatus2.append(new Option('Not Started', "A"));
	            	
	            	$labelStatus2.append($selectStatus2);
	            	$labelStatus2.append(new Text("status"));
	            	$labelStatus2.addClass("lblStatus");
	            	
	            	var $divRecord2  = $("div#sample_editable_1_length");
	                if ($divRecord2.length > 0) {	                	
	                	$divRecord2.prepend($labelStatus2);
	                	$selectStatus2.on('change',function(){
		            	   var selectedValue2 = $(this).val();	            	   
		            	   //("^"+selectedValue+"$", 0, true); //searchValue, column, reg
		            	   mtable.api().columns('.tblStatusCol2')
		                    .search( selectedValue2 ? '^'+selectedValue2+'$' : '', true, false )
		                	.draw();
		            	});
		            }
            	}
      	
            }
        });

        
        var tableWrapper = $("#sample_editable_1_wrapper");

        tableWrapper.find(".dataTables_length select").select2({
            showSearchInput: false //hide search box with special css class
        }); // initialize select2 dropdown

        var nEditing = null;
        var nNew = false;

        $('#sample_editable_1_new').click(function (e) {
            e.preventDefault();

            if (nNew && nEditing) {
                if (confirm("Previose row not saved. Do you want to save it ?")) {
                    saveRow(oTable, nEditing); // save
                    $(nEditing).find("td:first").html("Untitled");
                    nEditing = null;
                    nNew = false;

                } else {
                    oTable.fnDeleteRow(nEditing); // cancel
                    nEditing = null;
                    nNew = false;
                    
                    return;
                }
            }

            var aiNew = oTable.fnAddData(['', '', '', '', '', '']);
            var nRow = oTable.fnGetNodes(aiNew[0]);
            editRow(oTable, nRow);
            nEditing = nRow;
            nNew = true;
        });

        table.on('click', '.delete', function (e) {        	
            e.preventDefault();            
            if (confirm("Are you sure to delete this row ?") == false) {
                return;
            }

            var nRow = $(this).parents('tr')[0];
            oTable.fnDeleteRow(nRow);
            alert("Deleted! Do not forget to do some ajax to sync with backend :)");
        });

        table.on('click', '.cancel', function (e) {
            e.preventDefault();
            if (nNew) {
                oTable.fnDeleteRow(nEditing);
                nEditing = null;
                nNew = false;
            } else {
                restoreRow(oTable, nEditing);
                nEditing = null;
            }
        });

        table.on('click', '.edit', function (e) {
            e.preventDefault();

            /* Get the row as a parent of the link that was clicked on */
            var nRow = $(this).parents('tr')[0];

            if (nEditing !== null && nEditing != nRow) {
                /* Currently editing - but not this row - restore the old before continuing to edit mode */
                restoreRow(oTable, nEditing);
                editRow(oTable, nRow);
                nEditing = nRow;
            } else if (nEditing == nRow && this.innerHTML == "Save") {
                /* Editing this row and want to save it */
                saveRow(oTable, nEditing);
                nEditing = null;
                alert("Updated! Do not forget to do some ajax to sync with backend :)");
            } else {
                /* No edit in progress - let's start one */
                editRow(oTable, nRow);
                nEditing = nRow;
            }
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            handleTable();
        }

    };

}();