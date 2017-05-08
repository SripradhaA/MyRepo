//Modified functions to include empty hidden values for hidden form elements
function fnRefreshDropdowns(){
    	var specType=$('select#specTypeSeqNo').val();
		document.forms[0].hdnSelSpecTypeNo.value=specType;
		document.forms[0].hdnSelSpecTypeName.value=$('#specTypeSeqNo option:selected').text();
		if ($('#modelSeqNo option:selected').val() != '-1')
			document.forms[0].hdnSelectedMdlNames.value=$('#modelSeqNo option:selected').text();
		else
			document.forms[0].hdnSelectedMdlNames.value="";
		if ($('#compGroupSeqNo option:selected').val() != '-1')
			document.forms[0].hdncompgrpName.value=$('#compGroupSeqNo option:selected').text();
		else
			document.forms[0].hdncompgrpName.value="";
		if ($('#componentSeqNo option:selected').val() != '-1')
			document.forms[0].hdncompName.value=$('#componentSeqNo option:selected').text();
		else
			document.forms[0].hdncompName.value="";
		document.forms[0].action="CompGroupAction.do?method=initLoadCompInOrder";
		document.forms[0].submit();
	}
	
function fnSearchCompInOrdReport(){
    	var specType=$('select#specTypeSeqNo').val();
		document.forms[0].hdnSelSpecTypeNo.value=specType;
		document.forms[0].hdnSelSpecTypeName.value=$('#specTypeSeqNo option:selected').text();
		if ($('#modelSeqNo option:selected').val() != '-1')
			document.forms[0].hdnSelectedMdlNames.value=$('#modelSeqNo option:selected').text();
		else
			document.forms[0].hdnSelectedMdlNames.value="";
		if ($('#compGroupSeqNo option:selected').val() != '-1')
			document.forms[0].hdncompgrpName.value=$('#compGroupSeqNo option:selected').text();
		else
			document.forms[0].hdncompgrpName.value="";
		if ($('#componentSeqNo option:selected').val() != '-1')
			document.forms[0].hdncompName.value=$('#componentSeqNo option:selected').text();
		else
			document.forms[0].hdncompName.value="";
		document.forms[0].action="CompGroupAction.do?method=fetchCompInOrderReport&Excel=N";
		document.forms[0].submit();
	}
	
function fnSearchCompInOrdReportExcel(){   
	   if(!((document.forms[0].hdncompName.value == document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].text
	   					&& document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].value != "-1")
	   			|| ((document.forms[0].hdncompName.value == "" || (document.forms[0].hdncompName.value == document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].text)) && document.forms[0].componentSeqNo.options[document.forms[0].componentSeqNo.selectedIndex].value == "-1")))
	   {
			var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
	   }          
	   else if(!((document.forms[0].showLatSpecFlag.checked && document.forms[0].hdnShowLatSpecFlag.value == "Yes")
	   			|| (!(document.forms[0].showLatSpecFlag.checked) && document.forms[0].hdnShowLatSpecFlag.value == "No")))
	   {
	   		var id = '207';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("searchButton",-1);
			return false;
	   } 
	   else	{
	    	var specType=$('select#specTypeSeqNo').val();
			document.forms[0].hdnSelSpecTypeNo.value=specType;
			document.forms[0].hdnSelSpecTypeName.value=$('#specTypeSeqNo option:selected').text();
			if ($('#modelSeqNo option:selected').val() != '-1')
				document.forms[0].hdnSelectedMdlNames.value=$('#modelSeqNo option:selected').text();
			else
				document.forms[0].hdnSelectedMdlNames.value="";
			if ($('#compGroupSeqNo option:selected').val() != '-1')
				document.forms[0].hdncompgrpName.value=$('#compGroupSeqNo option:selected').text();
			else
				document.forms[0].hdncompgrpName.value="";
			if ($('#componentSeqNo option:selected').val() != '-1')
				document.forms[0].hdncompName.value=$('#componentSeqNo option:selected').text();
			else
				document.forms[0].hdncompName.value="";
			document.forms[0].action="CompGroupAction.do?method=fetchCompInOrderReport&Excel=Y";
			document.forms[0].submit();			
	   }
	}

// Added for CR-121 for sorting by Vb106565 starts here 
function fnSortCompGroup(){
		
		var sortKey=document.forms[0].orderbyCompGroupFlag.value;
		
		if(sortKey == 1){
		document.forms[0].orderbyCompGroupFlag.value=2;
		}else if(sortKey == 2){
		document.forms[0].orderbyCompGroupFlag.value=1;
		}else{
	    document.forms[0].orderbyCompGroupFlag.value=1;
		}
		document.forms[0].columnheader.value="CompGroup";
		document.forms[0].action="CompGroupAction.do?method=fetchCompInOrderReport&Excel=N#tbCompInOrdersReport";
		document.forms[0].submit();
}

function fnSortComponent(){
	
		var sortKey=document.forms[0].orderbyCompFlag.value;
		if(sortKey == 1){
		document.forms[0].orderbyCompFlag.value=2;
		}else if(sortKey == 2){
		document.forms[0].orderbyCompFlag.value=1;
		}else{
	    document.forms[0].orderbyCompFlag.value=1;
		}
		document.forms[0].columnheader.value="Component";
		document.forms[0].action="CompGroupAction.do?method=fetchCompInOrderReport&Excel=N#tbCompInOrdersReport";
		document.forms[0].submit();
}

// Added for CR-121 for sorting by Vb106565 ends here 	
	