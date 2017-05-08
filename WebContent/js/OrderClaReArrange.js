/*
*  Name: fnReloadClaforRearrange
*  Purpose: Used to fetch clauses for rearranging screen
*/
function fnReloadClaforRearrange()
{	
	var orderkey = document.forms[0].orderKey.value;
	var secseqno = document.forms[0].orderSecSeqNo.value;
	var subsecseqno = document.forms[0].subSecSeqNo.value;
	var	revcode = 1;//By default No Revision Markers

	document.forms[0].action="OrderClaReArrangeAction.do?method=initLoad&secSeq="+secseqno+"&revCode="+revcode+"&orderkey="+orderkey+"&subsecseqno="+subsecseqno;	
	document.forms[0].submit();	
}

/*
*  Name: fnModifySpec
*  Purpose: Used to return to Modify Spec Screen
*/
function fnModifySpec()
{
	var orderKey = document.forms[0].orderKey.value;
	var secSeq = document.forms[0].orderSecSeqNo.value;
	var subsecseqno = document.forms[0].subSecSeqNo.value;
	
	document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey
							+"&secSeq="+secSeq+"&revCode=1"+"&subsecno="+subsecseqno;
	document.forms[0].submit();
}

/*
*  Name: fnReArrangeClauses
*  Purpose: Used to return to Modify Spec Screen
*/
function fnReArrangeClauses(){

	if(document.forms[0].rowIndexList.value !=null && document.forms[0].rowIndexList.value !=""){
		var orderKey = document.forms[0].orderKey.value;
		var secseqno = document.forms[0].orderSecSeqNo.value;
		var subsecseqno = document.forms[0].subSecSeqNo.value;
		var	revcode = 1;//By default No Revision Markers
		document.forms[0].action="OrderClaReArrangeAction.do?method=saveOrderClaReArrange&secSeq="+secseqno+"&revCode="+revcode+"&orderkey="+orderKey+"&subsecseqno="+subsecseqno;	
		document.forms[0].submit();
	}else{
	
		var id = '899';
		hideErrors();
		addMsgNum(id);
		if ($('.modal').hasClass('in')) //If child clause window is open, push error to the popup only
			showScrollErrors("clauseNumField",-1);
		else
		showScrollErrors("clauseNo",-1);
	}
}
//Added for CR-127

function fnChildClauseRearranging(hrchyLevel,parentClaSeq){
	var divHeight = $("#clausesChild"+parentClaSeq).height();
	if ($("#clausesChild"+parentClaSeq).length > 0)
		{
		$("#clauseDetails"+parentClaSeq).tableDnD({
		onDragStart: function(table, row) {
			$(row).css('background-color', '#D8F2FF');
            $(row).contents('td').css({'border': '1px solid red', 'border-left': '1px dotted red', 'border-right': '1px dotted red'});
            $(row).contents('td:first').css('border-left', '1px solid red');
            $(row).contents('td:last').css('border-right', '1px solid red');
			},
		 onDrop: function(table, row) {
	          var rows = table.rows;
    	      var result = "";
        	  for (var i=0; i<rows.length; i++) {
            	if (result.length > 0) result += ",";
	            var rowId = rows[i].id;
	            if (rowId && rowId && table.tableDnDConfig && table.tableDnDConfig.serializeRegexp) {
                rowId = rowId.match(table.tableDnDConfig.serializeRegexp)[0];
    	        	}
            	result += rowId;
            	}
    		  var rowIDList=result;
	          document.forms['OrderSectionForm'].rowIndexList.value = rowIDList;
	          $(row).contents('td:first').html("");
          }
		});

		    $("#clausesChild"+parentClaSeq).modal(); 
		}	
}