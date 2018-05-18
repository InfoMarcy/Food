/// Jquery Pop Up Windows
$(document).ready(function(){
	
	// check if the checkbox has been checked
	document.getElementById('addProductOptionsChk').onchange = function() {
	    if ( document.getElementById('addProductOptionsChk').checked === true ) {
	    	refresh();
	    }
	};

	

});
 





function refresh(){
	
	 $("#productAddOptionsDialog").dialog("open");
	
}

//Open Delivery instructions window
$("#productAddOptionsDialog").dialog({
autoOpen: false,
closeOnEscape: false,
draggable: false,
modal: true,
width: 500,
height: 400,
buttons:  [{text  : 'Save', 
          click : function() {
        	  
        	  $('#addProductOptionsChk').prop("checked", false);
        	  getTableData();
              $(this).dialog('close')
          }, 
  class : 'btn btn-primary btn-lg'
}]
});

// call a function when the dialog is close using the exit [X] buttom
$('#productAddOptionsDialog').dialog({
	   beforeClose: function(event, ui) {
	       //call functions 
		   $('#addProductOptionsChk').prop("checked", false);
	   }
	});


// add new row to table
function addRow() {
	var table = document.getElementById("productChoicesTb");

	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var colCount = table.rows[0].cells.length;

	for(var i=0; i<colCount; i++) {

		var newcell	= row.insertCell(i);

		newcell.innerHTML = table.rows[0].cells[i].innerHTML;
		//alert(newcell.childNodes);
		switch(newcell.childNodes[0].type) {
			case "text":
					newcell.childNodes[0].value = "";
					break;
			case "checkbox":
					newcell.childNodes[0].checked = false;
					break;
//			case "button":
//					newcell.childNodes[0].selectedIndex = 0;
//					break;
		}
	}
	  
	}
//delete row from table
function deleteRow(){
	
	try {
		var table = document.getElementById("productChoicesTb");
		var rowCount = table.rows.length;

		for(var i=0; i<rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if(null != chkbox && true == chkbox.checked) {
				if(rowCount <= 1) {
					alert("Cannot delete all the rows.");
					break;
				}
				table.deleteRow(i);
				rowCount--;
				i--;
			}


		}
		}catch(e) {
			alert(e);
		}
	
}
// get the data from the table
function getTableData(){
	var table = $("#productChoicesTb");
	
	// create a json object with the data from the table
	 var product_options = {				 
			 options: []				 
	 };
	
	
	// find the values of the input fields inside the table
	 table.find('tr').each(function (i, el) {
		 
	
		 
	            var $tds = $(this).find(":text");
	            
	            
	            product_options.options.push({ 
	                "choice" :$tds.eq(0).val(),
	                "price"  : $tds.eq(1).val()
	            });
	          
	       
	    });
	 
	 
	 //print json data
	 alert(JSON.stringify(product_options));
	
}	

