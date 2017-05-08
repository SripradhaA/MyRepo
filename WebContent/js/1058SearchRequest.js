function keyHandler(e)
{
    var asciiValue = e ? e.which : window.event.keyCode;
    if (asciiValue == 13) {
        //Fix for flag unknown jscript errors on Modal Popups
        if (document.forms[0].flag != undefined) {
            if (document.forms[0].flag.value == "Y") {
                fnSearchRequest();
                return false;
            }
        }
    }

}
document.onkeypress = keyHandler;

		
		
$(document).ready(function() {
		
	//To change the background color of selected row.
	$(function() {
	    $('.seqNoChkbox').change(function() {
	        if ($(this).is(':checked'))
	            $(this).closest('tr').addClass("bg-info");
	        else
	            $(this).closest('tr').removeClass("bg-info");
	    });
	});
	
	// Check all/ clear all.
	
	$(function() {
	    $("#chk").click(function() {
	        $('.seqNoChkbox').attr("checked", true);
	        $('.seqNoChkbox').closest('tr').addClass("bg-info");
	        return false;
	    });
	});
	
	$(function() {
	    $("#unchk").click(function() {
	        $('.seqNoChkbox').attr("checked", false);
	        $('.seqNoChkbox').closest('tr').removeClass("bg-info");
	        return false;
	    });
	});

	//For mouse over tool tip on select box item
	$(function() {
	
	    jQuery('select option').each(function() {
	
	        this.setAttribute('title', this.text)
	
	    });
	
	});
	
	//Added for Cr-134
	if($("#1058FrmDate").length > 0 || $("#1058ToDate").length > 0){
	$(".input-group.date").datepicker({
		endDate: '+0d',
    	autoclose: true,
    	clearBtn: true
    });
	}
	//$('#showTable').hide();
	
});


function fnSearchRequest()
{
    var SearchForm = document.forms[0];
    
       
    var len  = document.all["modelSeqNos"].options.length;
	var mdlNames="";
	for(i=0;i<len;i++)
	{
	if(document.all["modelSeqNos"].options[i].selected==true)
	{
	if(mdlNames=="")
	{
	mdlNames=document.all["modelSeqNos"].options[i].text;
	}
	else
	{
	mdlNames=mdlNames+", "+document.all["modelSeqNos"].options[i].text;
	}
	
	}
	}
	
    SearchForm.hdnSelectedModel.value = mdlNames;
    
    /*Added for CR-126 */
    var statusLen  = document.all["statusSeqNos"].options.length;
	var statusSeqNos="";
	for(j=0;j<statusLen;j++)
	{
	if(document.all["statusSeqNos"].options[j].selected==true)
	{
	if(statusSeqNos=="")
	{
	statusSeqNos=document.all["statusSeqNos"].options[j].text;
	}
	else
	{
	statusSeqNos=statusSeqNos+", "+document.all["statusSeqNos"].options[j].text;
	}
	
	}
	}
    SearchForm.hdnSelectedStatus.value = statusSeqNos;
    
    /*Added for CR-126 Ends here*/    
    
    if ((trim(SearchForm.custSeqNo.value) == -1)
            && (mdlNames == "")
            && (statusSeqNos == "")
            && (trim(SearchForm.awApproval.value) == -1)
            && (trim(SearchForm.orderNo.value).length == 0))
    {
        var id = '830';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sltCustomer",-1);
        return false;
    } 
    
    if(trim(SearchForm.orderNo.value)!="*")
	{
	    if (SpecialCharacterCheck((trim(SearchForm.orderNo.value))))
	    {
	        var id = '304';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("orderNo",-1);
	        return false;
	    }
    }

    SearchForm.flag.value = "N";
    SearchForm.hdnSelectedCustomer.value = SearchForm.custSeqNo.options[SearchForm.custSeqNo.selectedIndex].text;
    SearchForm.hdnSelectedAwApproval.value = SearchForm.awApproval.options[SearchForm.awApproval.selectedIndex].text;
    SearchForm.hdnorderNo.value = SearchForm.orderNo.value;

    SearchForm.action = "SearchRequest1058Action.do?method=fetchDetails";
    SearchForm.submit();

}


function fn1058Page(seqNo)
{
    document.forms[0].action = "ChangeRequest1058Action.do?method=fetch1058Details&seqNo=" + seqNo;
    document.forms[0].submit();
}

function fnCreatePDF()
{
    var i;
    var hdnIDArray = new Array();
    var cnt = 0;
    var check = false;
    var leagcycheck = false;//Added for CR-118
    var index =0 ; // Added for CR_121
    if (document.forms[0].chk1058SeqNo.length > 1) {

        for (i = 0; i < document.forms[0].chk1058SeqNo.length; i++) {

            if (document.forms[0].chk1058SeqNo[i].checked) {

             check = true;
             //Added for CR-118
             if(document.forms[0].lagacy1058Flag[i].value == "Y"){
			             leagcycheck = true;
			             index=i;   // Added for CR_121           	
             }

            }


        }

    } else {
        if (document.forms[0].chk1058SeqNo.checked) {
            check = true;
              
        }
        //Added for CR-118
        if(document.forms[0].lagacy1058Flag.value == "Y"){
			             leagcycheck = true;
			             index=i;   // Added for CR_121         	             	
        }
    }
    
    var len  = document.all["modelSeqNos"].options.length;
	var mdlNames="";
	for(i=0;i<len;i++)
	{
	if(document.all["modelSeqNos"].options[i].selected==true)
	{
	if(mdlNames=="")
	{
	mdlNames=document.all["modelSeqNos"].options[i].text;
	}
	else
	{
	mdlNames=mdlNames+", "+document.all["modelSeqNos"].options[i].text;
	}
	
	}
	}
    document.forms[0].hdnSelectedModel.value = mdlNames;
    
    /*Added for CR-126 */
    var statusLen  = document.all["statusSeqNos"].options.length;
	var statusSeqNos="";
	for(j=0;j<statusLen;j++)
	{
	if(document.all["statusSeqNos"].options[j].selected==true)
	{
	if(statusSeqNos=="")
	{
	statusSeqNos=document.all["statusSeqNos"].options[j].text;
	}
	else
	{
	statusSeqNos=statusSeqNos+", "+document.all["statusSeqNos"].options[j].text;
	}
	
	}
	}
    document.forms[0].hdnSelectedStatus.value = statusSeqNos;
    
    /*Added for CR-126 Ends here*/  
    
    //Added for CR-118
    if(leagcycheck){
    	var id = '1024';
	    hideErrors();
        addMsgNum(id);
        showScrollErrors("chk1058SeqNo",index);
        return false;
    }
    
    if (document.SearchRequest1058Form.hdnSelectedCustomer.value !=
            document.SearchRequest1058Form.custSeqNo.options
            [document.SearchRequest1058Form.custSeqNo.selectedIndex].text)
    {
        var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sltCustomer",-1);
        return false;

    } else if(mdlNames != document.SearchRequest1058Form.hdnSelectedModel.value)
   {
		var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("modelSeqNos",-1);
        return false;
   }  else if (statusSeqNos != document.SearchRequest1058Form.hdnSelectedStatus.value)
    {

        var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("statusSeqNos",-1);
        return false;

    } else if (document.SearchRequest1058Form.hdnSelectedAwApproval.value !=
            document.SearchRequest1058Form.awApproval.options
            [document.SearchRequest1058Form.awApproval.selectedIndex].text)
    {

        var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sltAwaitingOn",-1);
        return false;

    } else if (document.SearchRequest1058Form.hdnorderNo.value !=
            document.SearchRequest1058Form.orderNo.value)
    {

        var id = '207';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("orderNumber",-1);
        return false;
    }
    
    

    if (!check) {
        var id = '945';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("chk1058SeqNo",-1);
        return false;
    }

    document.forms[0].action = "SearchRequest1058Action.do?method=createPDF";
    document.forms[0].submit();
}

//Added for CR-117
function fnDelete()
{
    var i;
    var check = false;
    if (document.forms[0].chk1058SeqNo.length > 1) {
        for (i = 0; i < document.forms[0].chk1058SeqNo.length; i++) {
            if (document.forms[0].chk1058SeqNo[i].checked) {
                check = true;
            }
        }
    } else {
        if (document.forms[0].chk1058SeqNo.checked) {
            check = true;
        }
    }
	if (!check) {
        var id = '945';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("chk1058SeqNo",-1);
        return false;
    }
    
    var check=confirm("Are you sure to delete the selected 1058 Request(s)?" +"\n\n"+
    				   "NOTE: The deleted 1058 request(s) can never be retrieved.");

    if(check == true){
 	   document.forms[0].action = "SearchRequest1058Action.do?method=delete1058Request";
	   document.forms[0].submit();
    }
}


//Added for CR-117 Ends Here

//Added for CR-118 
function fnViewSelectedSummaryReport(action){
	
	var i;
	var URL = "";
	var formObject = document.forms[0];
    var check = false;
    var seqNos="";
    if (formObject.chk1058SeqNo.length > 1) {

        for (i = 0; i < formObject.chk1058SeqNo.length; i++) {

            if(formObject.chk1058SeqNo[i].checked){
						check = true;
			}
        }

    } else {
        if (formObject.chk1058SeqNo.checked) {
            check = true;

        }
    }
    
   
    
    if (!check) {
        var id = '945';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("chk1058SeqNo",-1);
        return false;
    }
    

    
  	if(action =="report"){ 
		formObject.action = "SearchRequest1058Action.do?method=viewSelecetedSummaryReport";
        formObject.target = "_blank";
        formObject.submit();
        formObject.target = "_self";
	}else{
  		formObject.action = "SearchRequest1058Action.do?method=exportSummaryReport";
	    formObject.submit();
    }

}

//Added for CR-118 Ends Here

//Added for CR-126 Starts here
function fnViewUnapproved1058Report(){
		document.forms[0].action = "SearchRequest1058Action.do?method=viewUnapproved1058withClausesApplied";
        document.forms[0].target = "_blank";
        document.forms[0].submit();
        document.forms[0].target = "_self";
			
}

function fnExportUnapproved1058Report(){
		document.forms[0].action="SearchRequest1058Action.do?method=exportUnapproved1058withClausesApplied";
		document.forms[0].submit();
}

function fnView1058ClauseAppliedtoSpec(){
		document.forms[0].action = "SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
        document.forms[0].target = "_blank";
        document.forms[0].submit();
        document.forms[0].target = "_self";
}
function fnExport1058ClauseAppliedtoSpec (){
		document.forms[0].action="SearchRequest1058Action.do?method=export1058ClauseAppliedtoSpec";
		document.forms[0].submit();
	
} 

function fnEditLegacy(screenID,seqNo){

	document.forms[0].editLegacyFlag.value = "Y";
	document.forms[0].action = "SearchRequest1058Action.do?method=initLoad&screenid=" + screenID+"&1058SeqNo="+seqNo;
    document.forms[0].submit();
}
/*Added for CR-126 Ends here*/    

/*Added for CR-127 Starts here*/
function fnViewPending1058s(){
		document.forms[0].action = "SearchRequest1058Action.do?method=viewPending1058s";
        document.forms[0].target = "_blank";
        document.forms[0].submit();
        document.forms[0].target = "_self";
}
function fnExportPending1058s(){
		document.forms[0].action="SearchRequest1058Action.do?method=exportPending1058s";
		document.forms[0].submit();
	
} 


/*Added for Cr-127 Ends Here*/
/*Added for CR-128 Starts here*/
function fnResetFields()
{
	//var custSeqNo 	  = document.getElementById("sltCustomer");
	//var awApproval 	  = document.getElementById("sltAwaitingOn");
	//var custSeqNo = $( "#sltCustomer" ).val();
	//var awApproval = $("#sltAwaitingOn").val();
	$("select").val("-1").trigger("change");
	$("select").val("-1").trigger("change");
	//$("#sltCustomer").select2("val", "-1");
	//$("#sltAwaitingOn").select2("val", "-1");
	//$( "#sltCustomer" ).val(-1);
	//$("#sltAwaitingOn").val(-1);
	//alert(custSeqNo);
	//alert(awApproval);

	$('select option').removeProp('selected');
	$("#orderNumber").val("");
}
/*Added for CR-128 Ends Here*/ 

/*Added for CR-134*/
function fnShow1058Transitioned()
{
	var reportsForm = document.forms[0];
	
	if(trim(reportsForm.fromDate.value)== null || trim(reportsForm.fromDate.value)== ""){
		var id= '1040';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("1058FrmDate",-1);
		return false;
	}
	if(trim(reportsForm.toDate.value)== null || trim(reportsForm.toDate.value)== ""){
		var id= '1041';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("1058ToDate",-1);
		return false;
	}
	$('#showTable').show();
	document.forms[0].action="SearchRequest1058Action.do?method=showSummary1058Transitioned#1058TransitionedSummary";
	document.forms[0].submit();
}
function fnHide1058Transitioned()
{
	$('#showTable').hide();
	$( "#1058ToDate" ).val("");
	$( "#1058FrmDate" ).val("");
	
}
	