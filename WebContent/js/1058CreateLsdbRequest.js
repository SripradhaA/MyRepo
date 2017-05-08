/*Added few changes for CR-116 for set disabled button background color and roadnumbers*/

function fnsaveandsendreplist(reassignrepflg) {

    var sysEngg = document.getElementById("sysEngg");
    var opRep = document.getElementById("opRep");
    var finRep = document.getElementById("finRep");
    var pgmMgr = document.getElementById("pgmMgr");
    var propMgr = document.getElementById("propMgr");

   if(reassignrepflg == "N"){
    if (document.forms[0].hdnNonLsdbFlag.value == "Y")
    {
        if (trim(document.forms[0].custName.value) == "")
        {

            var id = '156';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("custName",-1);
            return false;

        }
        if (trim(document.forms[0].mdlName.value) == "")
        {

            var id = '923';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("mdlName",-1);
            return false;

        }
        if (trim(document.forms[0].orderQty.value) == "")
        {

            var id = '106';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("orderQty",-1);
            return false;

        }

        if (!(isNumeric(document.forms[0].orderQty.value)))
        {

            var id = '307';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("orderQty",-1);
            return false;

        }
    }

    if (!$("input[name='requestFrom']").is(':checked')) {
        var id = '953';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reqForm",-1);
        return false;
    } else {
        $('#categorySeqNo').val($("input[name='requestFrom']:checked").val());
    }

    if (!$("input[name='requestType']").is(':checked')) {
        var id = '954';
        hideErrors();
        addMsgNum(id);
         showScrollErrors("reqType",-1);
        return false;
    } else {
        $('#reqTypeSeqNo').val($("input[name='requestType']:checked").val());
    }

    if ($("#genDesc").val() == "") {
        var id = '955';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("genDesc",-1);
        return false;
    }

    if ($("#UnitNumber").val() == "") {
        var id = '956';
        hideErrors();
        addMsgNum(id);
       	showScrollErrors("UnitNumber",-1);
        return false;
    }

   /* if ($("#UnitNumber").val() != "") {
        if (!$.isNumeric($("#UnitNumber").val())) {
            var id = '958';
            hideErrors();
            addMsgNum(id);
            showErrors();
            return false;
        }
    }*/

    if ($("#RoadNumber").val() == "") {
        var id = '957';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("RoadNumber",-1);
        return false;
    }

/*    if ($("#RoadNumber").val() != "") {
        if (!$.isNumeric($("#RoadNumber").val())) {
            var id = '959';
            hideErrors();
            addMsgNum(id);
            showErrors();
            return false;
        }
    }*/

    if (!$("input[name='mcrnumberReq']").is(':checked')) {
        var id = '1007';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("mcrnumReq",-1);
        return false;
    } else {
        $('#mcrReq').val($("input[name='mcrnumberReq']:checked").val());
    }

    if ($("input[name='mcrnumberReq'][value='1']").is(':checked')) {

        if ($("#MCRNumber").val() == "")
        {
            var id = '960';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("MCRNumber",-1);
            return false;
        }
		/* Commented for CR-117 QA-Fixes
        if ($("#MCRNumber").val() != "") {
            if (!$.isNumeric($("#MCRNumber").val()))
            {
                var id = '961';
                hideErrors();
                addMsgNum(id);
                showErrors();
                return false;
            }
        }*/
    }
  }
// alert("RequestType: "+ $("input[name='requestType']:checked").val());
	if (sysEngg.options[sysEngg.selectedIndex].value == "-1")
    {

        var id = '940';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sysEngg",-1);
        return false;
    }
    
   if($("input[name='requestType']:checked").val() != "1"){ //Added for CR-117 
   /* Sys.Engg Section validation commented for CR-128
    if (sysEngg.options[sysEngg.selectedIndex].value == "-1")
    {

        var id = '940';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sysEngg",-1);
        return false;
    }*/

    if (opRep.options[opRep.selectedIndex].value == "-1")
    {

        var id = '941';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("opRep",-1);
        return false;
    }

    if (finRep.options[finRep.selectedIndex].value == "-1")
    {

        var id = '942';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("finRep",-1);
        return false;
    }

    if (pgmMgr.options[pgmMgr.selectedIndex].value == "-1")
    {

        var id = '943';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("pgmMgr",-1);
        return false;
    }
    
     
   }
   if (propMgr.options[propMgr.selectedIndex].value == "-1")
    {

        var id = '944';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("propMgr",-1);
        return false;
    }
    fnDisableButtons("");
    //Added for CR-117 to enable representative list fix
    $("#sysEngg").prop("disabled", false);
	$("#opRep").prop("disabled", false);
	$("#finRep").prop("disabled", false);
	$("#pgmMgr").prop("disabled", false);
    document.forms[0].action = "ChangeRequest1058Action.do?method=insertRepresentative";
    document.forms[0].submit();
}

function fnsave()
{
    var flag = true;
    
    if (document.forms[0].hdnNonLsdbFlag.value == "Y")
    {
        if (!(isNumeric(document.forms[0].orderQty.value)))
        {

            var id = '307';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("orderQty",-1);
            return false;

        }
    }
    
    /*if ($("#UnitNumber").val() != "") {
        if (!$.isNumeric($("#UnitNumber").val())) {
            var id = '958';
            hideErrors();
            addMsgNum(id);
            showErrors();
            return false;
        }
    }*/
    
 /*   if ($("#RoadNumber").val() != "") {
        if (!$.isNumeric($("#RoadNumber").val())) {
            var id = '959';
            hideErrors();
            addMsgNum(id);
            showErrors();
            return false;
        }
    }*/
    
   /* if ($("#MCRNumber").val() != "") {
        if (!$.isNumeric($("#MCRNumber").val()))
        {
            var id = '961';
            hideErrors();
            addMsgNum(id);
            showErrors();
            return false;
        }
    }*/
    
    if (!$("input[name='mcrnumberReq']").is(':checked')) {
        var id = '1007';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("mcrnumReq",-1);
        return false;
    } else {
        $('#mcrReq').val($("input[name='mcrnumberReq']:checked").val());
    }

    if ($("input[name='mcrnumberReq'][value='1']").is(':checked')) {

        if ($("#MCRNumber").val() == "")
        {
            var id = '960';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("MCRNumber",-1);
            return false;
        }
   /* Commented for CR-117 QA-Fixes
        if ($("#MCRNumber").val() != "") {
            if (!$.isNumeric($("#MCRNumber").val()))
            {
                var id = '961';
                hideErrors();
                addMsgNum(id);
                showErrors();
                return false;
            }
        }*/
    }
        
    if (document.forms[0].statusSeqNo.value > 1) {  
      
    	/*if (document.forms[0].partNoAdded.value != "") {
			if (SpecialCharacterCheck(trim(document.forms[0].partNoAdded.value)))
			    {
			
			        var id = '967';
			        hideErrors();
			        addMsgNum(id);
			        showErrors();
			        return false;
			    }
		}*/
		
		/*Commented for CR-126
		 if (document.forms[0].designHrs.value != "") {
			if (!(isNumericHours(trim(document.forms[0].designHrs.value))))
			    {
			        var id = '992';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("designHrs",-1);
			        return false;
			    }
		}*/
		
		/*if (document.forms[0].partNoDeleted.value != "") {
			if (SpecialCharacterCheck(trim(document.forms[0].partNoDeleted.value)))
			    {
			        var id = '969';
			        hideErrors();
			        addMsgNum(id);
			        showErrors();
			        return false;
			    }
		}*/
		
		/*Commented for CR-126
		 if (document.forms[0].draftingHrs.value != "") {
		    if (!(isNumericHours(trim(document.forms[0].draftingHrs.value))))
			    {
			
			        var id = '993';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("draftingHrs",-1);
			        return false;
			
			    }
		}*/
		
		if (document.forms[0].workOrderUSD.value != "") {
		    if (!(isNumericDecimal(document.forms[0].workOrderUSD.value)))
		    {
		
		        var id = '994';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("workOrderUSD",-1);
		        return false;
		
		    }
		}
		
		/*if (document.forms[0].disposExcessMaterial.value != "") {
		    var isdisposExcessMaterial = SpecialCharacterCheck(trim(document.forms[0].disposExcessMaterial.value));
		    if (isdisposExcessMaterial)
		    {
		        var id = '975';
		        hideErrors();
		        addMsgNum(id);
		        showErrors();
		        return false;
		    }
		}*/
		/*Commented for CR-126
		if (document.forms[0].laborImpact.value != "") {
			var islaborImpact = isNumericHours(trim(document.forms[0].laborImpact.value));
		    if (!islaborImpact)
		    {
		        var id = '1008';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("laborImpact",-1);
		        return false;
		    }
		}*/
		
		if (document.forms[0].recEffectivityDel.value != "") {
			var isrecEffectivityDel = SpecialCharacterCheck(trim(document.forms[0].recEffectivityDel.value));
		    if (isrecEffectivityDel)
		    {
		        var id = '977';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("recEffectivityDel",-1);
		        return false;
		    }
		}
		    
		if (document.forms[0].toolingCostUSD.value != "") {
		    var istoolingCostUSD = isNumericDecimal(document.forms[0].toolingCostUSD.value);
		    if (!istoolingCostUSD)
		    {
		        var id = '1004';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("toolingCostUSD",-1);
		        return false;
		    }
		}
		
		if (document.forms[0].scrapCostUSD.value != "") {
		    var isscrapCostUSD = isNumericDecimal(document.forms[0].scrapCostUSD.value);
		    if (!isscrapCostUSD)
		    {
		        var id = '1005';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("scrapCostUSD",-1);
		        return false;
		    }
		}
		
		if (document.forms[0].reworkCost.value != "") {
		    var isreworkCost = isNumericDecimal(document.forms[0].reworkCost.value);
		    if (!isreworkCost)
		    {
		        var id = '1006';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("reworkCost",-1);
		        return false;
		    }
		}
		
		if (document.forms[0].sellpriceCustomer.value != "") {
		    var issellpriceCustomer = isNumericDecimal(document.forms[0].sellpriceCustomer.value);
		    if (!issellpriceCustomer) {
		
		        var id = '1002';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("sellpriceCustomer",-1);
		        return false;
		
		    }
		}
		
		if (document.forms[0].markUp.value != "") {
		    var ismarkUp = isNumericDecimal(document.forms[0].markUp.value);
		    if (!ismarkUp) {
		
		        var id = '1003';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("markUp",-1);
		        return false;
		
		    }
		}
		
		if (document.forms[0].actualSellPrice.value != "") {
		    if (!(isNumericDecimal(document.forms[0].actualSellPrice.value))) {
		
		        var id = '1001';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("actualSellPrice",-1);
		        return false;
		
		    }
		}
    }
    if (flag) {
        fnCheckReqDetRadios();
        fnDisableButtons("");        
	    $("#reviseClauseCheck").val(0);
		$("#checkCompChangeClauses").val(0);
		$("#checkVersionClause").val(0);
        document.forms[0].action = "ChangeRequest1058Action.do?method=save";
        document.forms[0].submit();
    }
}

function fnresetlastsave()
{
	fnDisableButtons("");
    $("#reviseClauseCheck").val(0);
	$("#checkCompChangeClauses").val(0);
	$("#checkVersionClause").val(0);
	
    var seqNo = document.forms[0].seqNo1058.value;
    document.forms[0].action = "ChangeRequest1058Action.do?method=fetch1058Details&seqNo=" + seqNo;
    document.forms[0].submit();
}

function fncomplete(statusSeqNo)
{
    var flag = true;
     
    if (statusSeqNo == 1) {
        var confirmflag = confirm("Are you sure to reset the 1058 status to 'Draft'?");
        if (!confirmflag)
            flag = false;
	}else if(statusSeqNo == 10){ //Added for CR-117
		var id1058 = document.forms[0].id1058.value;
		var confirmflag = confirm("Are you sure to CANCEL the 1058 Request "+id1058+" ?");
        if (!confirmflag)
            flag = false;
	}  else{ 
	    var updateSpecChkRadio;
	    updateSpecChkRadio = $('[name=reqSpecChkRadio]').length;
	    
        if (document.ChangeRequest1058Form.hdnSpecRev.value !=
            document.ChangeRequest1058Form.specRev.value)
	    {
	        var id = '1021';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("saveButton",-1);	        
	        return false;
    	}
    	
	    if(trim(document.forms[0].specRev.value) == "" && updateSpecChkRadio > 0){
	    	var multiChkflag = confirm("Do you wish to complete the 1058 WITHOUT adding 1058 clauses to spec and indicating the Specification Revision?");
	        if (!multiChkflag)
	            flag = false;
	    }else{
	    
		if(trim(document.forms[0].specRev.value) == "" ){
			var revisionflag = confirm("Do you wish to complete the 1058 WITHOUT indicating the Specification Revision?");
	        if (!revisionflag)
	            flag = false;
		}
		
		
	    if(updateSpecChkRadio > 0){
	    	var updateSpecChkflag = confirm("Do you wish to complete the 1058 WITHOUT adding 1058 clauses to spec?");
	        if (!updateSpecChkflag)
	            flag = false;
	    }
	    }
	    
	    
	}//Added for CR-117 Ends Here
	
    if (flag) {
	    fnDisableButtons("");
	    $("#reviseClauseCheck").val(0);
		$("#checkCompChangeClauses").val(0);
		$("#checkVersionClause").val(0);
	    document.forms[0].action = "ChangeRequest1058Action.do?method=complete&statusSeqNo=" + statusSeqNo;
	    document.forms[0].submit();
    }
}

// added for cr-110 @108447---starts---

function fnAddClaEntry() {
	
    //CR-130--Added to include TinyMCe for NLSDB-1058
    fnTinymceCall();
    $('#add').slideDown();
    $('#addBtn').show();
    $('#updateBtn').hide();
    
	//CR-130--Added to include TinyMCe for NLSDB-1058 
  	setTimeout(function() {
		showTinyMce("changeFromClaDesc");		
		showTinyMce("changeToClaDesc");
	}, 800);
	
    document.forms[0].changeFromSpec.value = "";
    $("#changeFromClaDesc").val("");//Edited for CR-130
    document.forms[0].changeFromEngData.value = "";
    document.forms[0].changeToSpec.value = "";
    $("#changeToClaDesc").val("");//Edited for CR-130
    document.forms[0].changeToEngData.value = "";
    document.forms[0].changeClaReason.value = "";
	
}

function fnAddClause() {
	//Edited for CR-130
    if ((trim(document.forms[0].changeFromSpec.value) == "") &&
            ($("#changeFromClaDesc").val() == "") &&
            (trim(document.forms[0].changeFromEngData.value) == "") &&
            (trim(document.forms[0].changeToSpec.value) == "") &&
            ($("#changeToClaDesc").val() == "") &&
            (trim(document.forms[0].changeToEngData.value) == "")) 
    {
        var id = '950';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeFromSpec",-1);
        return false;
    }

    if (trim((document.forms[0].changeClaReason.value)) == "")
    {
        var id = '507';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeClaReason",-1);
        return false;
    }
    fnCheckReqDetRadios();
    fnDisableButtons("");
    document.forms[0].action = "ChangeRequest1058Action.do?method=addClauseNonLsdb";
    document.forms[0].submit();

}

function fnDeleteClause() {

    if (document.forms[0].hdnSelectedChangeReqSeqNo.value < 1)
    {
        var id = '949';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("clChRqSqNoRad",-1);
        return false;
    }

    if (confirm("Are you sure to delete clause details?")) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        document.forms[0].action = "ChangeRequest1058Action.do?method=deleteClauseNonLsdb";
        document.forms[0].submit();
        document.forms[0].hdnSelectedChangeReqSeqNo.value = 0;
    }
}

function fnReviseClause() {

    //CR-130--Added to include TinyMCe for NLSDB-1058
    fnTinymceCall();
    
    if (document.forms[0].hdnSelectedChangeReqSeqNo.value < 1)
    {
        var id = '949';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("clChRqSqNoRad",-1);
        return false;
    }

    $('#add').slideDown();
    $('#addBtn').hide();
    $('#updateBtn').show();
    
	//CR-130--Added to include TinyMCe for NLSDB-1058    
	setTimeout(function() {
		showTinyMce("changeFromClaDesc");		
		showTinyMce("changeToClaDesc");
	}, 800);
	
  	
    document.forms[0].changeFromSpec.value = claFrmSpec;
    $("#changeFromClaDesc").val(claFrmDesc) ;//Edited for CR-130
    document.forms[0].changeFromEngData.value = claFrmEng;
    document.forms[0].changeToSpec.value = claToSpec;
    $("#changeToClaDesc").val(claToDesc) ;//Edited for CR-130
    document.forms[0].changeToEngData.value = claToEng;
    document.forms[0].changeClaReason.value = claReason;

}

/* Moved to document.ready for better placement


function fnSetclChRqSqNo(claChangeSeqNo) {

    document.forms[0].hdnSelectedChangeReqSeqNo.value = claChangeSeqNo;

}*/


function fnUpdateClause() {

	//Edited for CR-130
    if ((trim(document.forms[0].changeFromSpec.value) == "") &&
            ($("#changeFromClaDesc").val() == "") &&
            (trim(document.forms[0].changeFromEngData.value) == "") &&
            (trim(document.forms[0].changeToSpec.value) == "") &&
            ($("#changeToClaDesc").val() == "") &&
            (trim(document.forms[0].changeToEngData.value) == ""))
    {
        var id = '950';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeFromSpec",-1);
        return false;
    }

    if (trim((document.forms[0].changeClaReason.value)) == "")
    {
        var id = '507';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeClaReason",-1);
        return false;
    }


    if (confirm("Are you sure to update clause details?")) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        document.forms[0].action = "ChangeRequest1058Action.do?method=updateClauseNonLsdb";
        document.forms[0].submit();
        document.forms[0].hdnSelectedChangeReqSeqNo.value = 0;
    }
}
function fnCheckLength(field, maxlen) {
    if (field.value.length > maxlen) {
        field.value = field.value.substring(0, maxlen);
        return false;
    }
    return true;
}

function trimfield(str)
{
    return str.replace(/^\s+|\s+$/g, '');
}


// @cr110-----@108447---end-----

//Added for CR-110 by vb106565
function fnApproveSysEng() {
    if (trim(document.forms[0].systemEngComment.value) == "" || trim(document.forms[0].systemEngComment.value).length == 0)
    {
        var id = '966';
        hideErrors();
        addMsgNum(id);
       	showScrollErrors("systemEngComment",-1);
        return false;
    }
    
     if (!$("input[name='changeAffectsWeight']").is(':checked'))
    {
        var id = '971';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeAffectsWeight",-1);
        return false;
    }
    
     if (trim(document.forms[0].designHrs.value).length == 0 || trim(document.forms[0].designHrs.value) == "")
    {
        var id = '968';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("designHrs",-1);
        return false;
    }
    
    if (!$("input[name='changeAffectsClear']").is(':checked'))
    {
        var id = '972';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("changeAffectsClear",-1);
        return false;
    }
    
    if (trim(document.forms[0].draftingHrs.value).length == 0 || trim(document.forms[0].draftingHrs.value) == "")
    {
        var id = '970';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("draftingHrs",-1);
        return false;
    }
    
     if (trim(document.forms[0].workOrderUSD.value).length == 0 || trim(document.forms[0].workOrderUSD.value) == "")
    {
        var id = '973';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("workOrderUSD",-1);
        return false;
    }

    if (!(isNumericDecimal(document.forms[0].workOrderUSD.value)))
    {

        var id = '994';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("workOrderUSD",-1);
        return false;

    }
    //Added for CR-120
    /*if (trim(document.forms[0].partNoAdded.value).length == 0 || trim(document.forms[0].partNoAdded.value) == "")
    {
        document.forms[0].partNoAdded.value = "";
        var id = '967';
        hideErrors();
        addMsgNum(id);
        showErrors();
        return false;
    }*/
    if (trim(document.getElementById("partAdded").value) == "" || trim(document.getElementById("partAdded").value).length == 0)
    {
        var id = '967';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("partAdded",-1);
        return false;
    }
    

   /*if (SpecialCharacterCheck(trim(document.forms[0].partNoAdded.value)))
    {

        var id = '967';
        hideErrors();
        addMsgNum(id);
        showErrors();
        return false;
    }*/

   
	/*Commented for CR-126
    if (!(isNumericHours(trim(document.forms[0].designHrs.value))))
    {
        var id = '992';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("designHrs",-1);
        return false;
    }*/
     //Added for CR-120
    /*if (trim(document.forms[0].partNoDeleted.value).length == 0 || trim(document.forms[0].partNoDeleted.value) == "")
    {
        var id = '969';
        hideErrors();
        addMsgNum(id);
        showErrors();
        document.forms[0].partNoDeleted.value = "";
        return false;
    }*/
     if (trim(document.getElementById("partDeleted").value) == "" || trim(document.getElementById("partDeleted").value).length == 0)
    {
        var id = '969';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("partDeleted",-1);
        return false;
    }

   /* if (SpecialCharacterCheck(trim(document.forms[0].partNoDeleted.value)))
    {
        var id = '969';
        hideErrors();
        addMsgNum(id);
        showErrors();
        return false;
    }*/


    
	/*Commented for CR-126
    if (!(isNumericHours(trim(document.forms[0].draftingHrs.value))))
    {

        var id = '993';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("draftingHrs",-1);
        return false;

    }*/


    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 3;
    document.forms[0].action = "ChangeRequest1058Action.do?method=sysEngrSecDetails";
    document.forms[0].submit();
}

function fnRejectSysEng() {
    if (trim(document.forms[0].systemEngComment.value) == "" || trim(document.forms[0].systemEngComment.value).length == 0)
    {
        var id = '966';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("systemEngComment",-1);
        return false;
    }
    
    /*if (document.forms[0].partNoAdded.value != "") {
			if (SpecialCharacterCheck(trim(document.forms[0].partNoAdded.value)))
			    {
			
			        var id = '967';
			        hideErrors();
			        addMsgNum(id);
			        showErrors();
			        return false;
			    }
		}*/
		/*Commented for CR-126
		if (document.forms[0].designHrs.value != "") {
			if (!(isNumericHours(trim(document.forms[0].designHrs.value))))
			    {
			        var id = '992';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("designHrs",-1);
			        return false;
			    }
		}*/
		
		/*if (document.forms[0].partNoDeleted.value != "") {
			if (SpecialCharacterCheck(trim(document.forms[0].partNoDeleted.value)))
			    {
			        var id = '969';
			        hideErrors();
			        addMsgNum(id);
			        showErrors();
			        return false;
			    }
		}*/
		
		/*Commented for CR-126
		if (document.forms[0].draftingHrs.value != "") {
		    if (!(isNumericHours(trim(document.forms[0].draftingHrs.value))))
			    {
			
			        var id = '993';
			        hideErrors();
			        addMsgNum(id);
			        showScrollErrors("draftingHrs",-1);
			        return false;
			
			    }
		}*/
		
		if (document.forms[0].workOrderUSD.value != "") {
		    if (!(isNumericDecimal(document.forms[0].workOrderUSD.value)))
		    {
		
		        var id = '994';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("workOrderUSD",-1);
		        return false;
		
		    }
		}

    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 4;
    document.forms[0].action = "ChangeRequest1058Action.do?method=sysEngrSecDetails";
    document.forms[0].submit();

}

function fnApproveOprSec() {
    if (trim(document.forms[0].operationComments.value) == "" || trim(document.forms[0].operationComments.value).length == 0)
    {
        var id = '974';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("operationComments",-1);
        return false;
    }
    //Added for CR-120
    /*if (trim(document.forms[0].disposExcessMaterial.value).length == 0 || trim(document.forms[0].disposExcessMaterial.value) == "")
    {
        var id = '975';
        hideErrors();
        addMsgNum(id);
        showErrors();
        return false;
    }*/
    
    if (trim(document.getElementById("dispositionSM").value) == "" || trim(document.getElementById("dispositionSM").value).length == 0)
    {
        var id = '975';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("dispositionSM",-1);
        return false;
    }
    
   /* var isdisposExcessMaterial = SpecialCharacterCheck(trim(document.forms[0].disposExcessMaterial.value));
    if (isdisposExcessMaterial)
    {
        var id = '975';
        hideErrors();
        addMsgNum(id);
        showErrors();
        return false;
    }*/
    if (trim(document.forms[0].laborImpact.value).length == 0 || trim(document.forms[0].laborImpact.value) == "")
    {
        var id = '976';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("laborImpact",-1);
        return false;
    }
    /*Commented for CR-126
    var islaborImpact = isNumericHours(trim(document.forms[0].laborImpact.value));
    if (!islaborImpact)
    {
        var id = '1008';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("laborImpact",-1);
        return false;
    }*/
    if (trim(document.forms[0].recEffectivityDel.value).length == 0 || trim(document.forms[0].recEffectivityDel.value) == "")
    {
        var id = '977';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("recEffectivityDel",-1);
        return false;
    }
    var isrecEffectivityDel = SpecialCharacterCheck(trim(document.forms[0].recEffectivityDel.value));
    if (isrecEffectivityDel)
    {
        var id = '977';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("recEffectivityDel",-1);
        return false;
    }
    if (trim(document.forms[0].toolingCostUSD.value).length == 0 || trim(document.forms[0].toolingCostUSD.value) == "")
    {
        var id = '978';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("toolingCostUSD",-1);
        return false;
    }
    var istoolingCostUSD = isNumericDecimal(document.forms[0].toolingCostUSD.value);
    if (!istoolingCostUSD)
    {
        var id = '1004';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("toolingCostUSD",-1);
        return false;
    }

    if (trim(document.forms[0].scrapCostUSD.value).length == 0 || trim(document.forms[0].scrapCostUSD.value) == "")
    {
        var id = '979';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("scrapCostUSD",-1);
        return false;
    }
    var isscrapCostUSD = isNumericDecimal(document.forms[0].scrapCostUSD.value);
    if (!isscrapCostUSD)
    {
        var id = '1005';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("scrapCostUSD",-1);
        return false;
    }
    if (trim(document.forms[0].reworkCost.value).length == 0 || trim(document.forms[0].reworkCost.value) == "")
    {
        var id = '980';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reworkCost",-1);
        return false;
    }

    var isreworkCost = isNumericDecimal(document.forms[0].reworkCost.value);
    if (!isreworkCost)
    {
        var id = '1006';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reworkCost",-1);
        return false;
    }
	fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 3;
    document.forms[0].action = "ChangeRequest1058Action.do?method=operationsSecDetails";
    document.forms[0].submit();
}



function fnRejectOprSec() {
    if (trim(document.forms[0].operationComments.value) == "" || trim(document.forms[0].operationComments.value).length == 0)
    {
        var id = '974';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("operationComments",-1);
        return false;
    }
    
    /*if (document.forms[0].disposExcessMaterial.value != "") {
		    var isdisposExcessMaterial = SpecialCharacterCheck(trim(document.forms[0].disposExcessMaterial.value));
		    if (isdisposExcessMaterial)
		    {
		        var id = '975';
		        hideErrors();
		        addMsgNum(id);
		        showErrors();
		        return false;
		    }
		}*/
		/*Commented for CR-126
		if (document.forms[0].laborImpact.value != "") {
			var islaborImpact = isNumericHours(trim(document.forms[0].laborImpact.value));
		    if (!islaborImpact)
		    {
		        var id = '1008';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("laborImpact",-1);
		        return false;
		    }
		}*/
		
		if (document.forms[0].recEffectivityDel.value != "") {
			var isrecEffectivityDel = SpecialCharacterCheck(trim(document.forms[0].recEffectivityDel.value));
		    if (isrecEffectivityDel)
		    {
		        var id = '977';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("recEffectivityDel",-1);
		        return false;
		    }
		}
		    
		if (document.forms[0].toolingCostUSD.value != "") {
		    var istoolingCostUSD = isNumericDecimal(document.forms[0].toolingCostUSD.value);
		    if (!istoolingCostUSD)
		    {
		        var id = '1004';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("toolingCostUSD",-1);
		        return false;
		    }
		}
		
		if (document.forms[0].scrapCostUSD.value != "") {
		    var isscrapCostUSD = isNumericDecimal(document.forms[0].scrapCostUSD.value);
		    if (!isscrapCostUSD)
		    {
		        var id = '1005';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("scrapCostUSD",-1);
		        return false;
		    }
		}
		
		if (document.forms[0].reworkCost.value != "") {
		    var isreworkCost = isNumericDecimal(document.forms[0].reworkCost.value);
		    if (!isreworkCost)
		    {
		        var id = '1006';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("reworkCost",-1);
		        return false;
		    }
		}
    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 4;
    document.forms[0].action = "ChangeRequest1058Action.do?method=operationsSecDetails";
    document.forms[0].submit();
}



function fnApproveFinSec() {
    if (trim(document.forms[0].financeComments.value) == "" || trim(document.forms[0].financeComments.value).length == 0)
    {
        var id = '981';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("financeComments",-1);
        return false;
    }


    if ((trim(document.forms[0].prodCostChange.value) == "") || (trim(document.forms[0].prodCostChange.value).length == 0))
    {
        var id = '982';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("prodCostChange",-1);
        return false;
    }


    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 3;
    document.forms[0].action = "ChangeRequest1058Action.do?method=financeSecDetails";
    document.forms[0].submit();
}


function fnRejectFinSec() {
    if (trim(document.forms[0].financeComments.value) == "" || trim(document.forms[0].financeComments.value).length == 0)
    {
        var id = '981';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("financeComments",-1);
        return false;
    }
	fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 4;
    document.forms[0].action = "ChangeRequest1058Action.do?method=financeSecDetails";
    document.forms[0].submit();

}


function  fnApproveProgMgrSec() {
    if (trim(document.forms[0].progManagerComments.value) == "" || trim(document.forms[0].progManagerComments.value).length == 0)
    {
        var id = '983';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("progManagerComments",-1);
        return false;
    }

    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 3;
    document.forms[0].action = "ChangeRequest1058Action.do?method=programMgrSecDetails";
    document.forms[0].submit();


}


function fnRejectProgMgrSec() {

    if (trim(document.forms[0].progManagerComments.value) == "" || trim(document.forms[0].progManagerComments.value).length == 0)
    {
        var id = '983';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("progManagerComments",-1);
        return false;
    }
    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 4;
    document.forms[0].action = "ChangeRequest1058Action.do?method=programMgrSecDetails";
    document.forms[0].submit();

}
function  fnApprovePropMgrSec() {
    if (trim(document.forms[0].propManagerComments.value) == "" || trim(document.forms[0].propManagerComments.value).length == 0)
    {
        var id = '984';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("propManagerComments",-1);
        return false;
    }

    if (trim(document.forms[0].sellpriceCustomer.value).length == 0 || trim(document.forms[0].sellpriceCustomer.value) == "") {
        var id = '985';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sellpriceCustomer",-1);
        return false;
    }

    var issellpriceCustomer = isNumericDecimal(document.forms[0].sellpriceCustomer.value);
    if (!issellpriceCustomer) {

        var id = '1002';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sellpriceCustomer",-1);
        return false;

    }

    if (trim(document.forms[0].markUp.value).length == 0 || trim(document.forms[0].markUp.value) == "") {
        var id = '986';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("markUp",-1);
        return false;
    }

    var ismarkUp = isNumericDecimal(document.forms[0].markUp.value);
    if (!ismarkUp) {

        var id = '1003';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("markUp",-1);
        return false;

    }

    if (!$("input[name='customerApprovalReq']").is(':checked')) {
        var id = '987';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("customerApprovalReq",-1);
        return false;
    }

    if ($('#customerApprovalReq').is(':checked'))
    {
        if (!$("input[name='customerDecision']").is(':checked')) {

            var id = '988';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("customerDecision",-1);
            return false;
        }

        if (!($('#customerDecPen').is(':checked'))) {

            if (trim(document.forms[0].customerDecisionDate.value).length == 0 || trim(document.forms[0].customerDecisionDate.value) == "") {
                var id = '989';
                hideErrors();
                addMsgNum(id);
                showScrollErrors("customerDecisionDate",-1);
                return false;
            }
        }
    }
    if (trim(document.forms[0].actualSellPrice.value).length == 0 || trim(document.forms[0].actualSellPrice.value) == "") {
        var id = '990';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("actualSellPrice",-1);
        return false;
    }

    if (!(isNumericDecimal(document.forms[0].actualSellPrice.value))) {

        var id = '1001';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("actualSellPrice",-1);
        return false;

    }
    fnDisableButtons("");
    document.forms[0].sectionStatusSeq.value = 3;
    document.forms[0].action = "ChangeRequest1058Action.do?method=proposalMgrSecDetails";
    document.forms[0].submit();

}

function fnRejectPropMgrSec() {
    if (trim(document.forms[0].propManagerComments.value) == "" || trim(document.forms[0].propManagerComments.value).length == 0)
    {
        var id = '984';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("propManagerComments",-1);
        return false;
    }

	
		
		if (document.forms[0].sellpriceCustomer.value != "") {
		    var issellpriceCustomer = isNumericDecimal(document.forms[0].sellpriceCustomer.value);
		    if (!issellpriceCustomer) {
		
		        var id = '1002';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("sellpriceCustomer",-1);
		        return false;
		
		    }
		}
		
		if (document.forms[0].markUp.value != "") {
		    var ismarkUp = isNumericDecimal(document.forms[0].markUp.value);
		    if (!ismarkUp) {
		
		        var id = '1003';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("markUp",-1);
		        return false;
		
		    }
		}
		
		if (document.forms[0].actualSellPrice.value != "") {
		    if (!(isNumericDecimal(document.forms[0].actualSellPrice.value))) {
		
		        var id = '1001';
		        hideErrors();
		        addMsgNum(id);
		        showScrollErrors("actualSellPrice",-1);
		        return false;
		
		    }
		}

    document.forms[0].sectionStatusSeq.value = 4;
    fnDisableButtons("");
    document.forms[0].action = "ChangeRequest1058Action.do?method=proposalMgrSecDetails";
    document.forms[0].submit();

}
//Ends here

function checkRadios() {

    if ($('#categorySeqNo').val() == 1) {
        $("input[name='requestFrom']").eq(0).prop("checked", true);
    }
    if ($('#categorySeqNo').val() == 2) {
        $("input[name='requestFrom']").eq(1).prop("checked", true);
    }
    if ($('#reqTypeSeqNo').val() == 1) {
        $("input[name='requestType']").eq(0).prop("checked", true);
    }
    if ($('#reqTypeSeqNo').val() == 2) {
        $("input[name='requestType']").eq(1).prop("checked", true);
    }
    if ($('#reqTypeSeqNo').val() == 3) {
        $("input[name='requestType']").eq(2).prop("checked", true);
    }

    if ($('#mcrReq').val() == 1) {
        $("input[name='mcrnumberReq']").eq(0).prop("checked", true);
    }
    if ($('#mcrReq').val() == 2) {
        $("input[name='mcrnumberReq']").eq(1).prop("checked", true);
    }
}

function validate(file) {
    var check = true;
    var flag = true;

    if (file.length == 0 || file.value == "")
    {

        var id = '1000';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("attachmentSourceId",-1);
        return false;

    }

    if (file == "")
    {
        return false;
    }

    var code = file.split("\\");
    var temp = "";
    for (j = 1; j < code.length; j++)
    {
        temp = code[j];
    }
    if (temp != "")
    {
        var checkParam = temp.substring(temp.indexOf('.') + 1, temp.length);
        //alert(checkParam);
        var flag = true;
        var arr = new Array();



        arr[0] = "mdw";
        arr[1] = "msc";
        arr[2] = "msi";
        arr[3] = "msp";
        arr[4] = "mst";
        arr[5] = "ops";
        arr[6] = "pcd";

        arr[7] = "prg";
        arr[8] = "reg";
        arr[9] = "scr";
        arr[10] = "sct";
        arr[11] = "shs";
        arr[12] = "vbe";
        arr[13] = "wsc";

        arr[14] = "wsf";
        arr[15] = "wsh";
        arr[16] = "csh";
        arr[17] = "fxp";
        arr[18] = "hlp";
        arr[19] = "hta";
        arr[20] = "inf";

        arr[21] = "ins";
        arr[22] = "isp";
        arr[23] = "jse";
        arr[24] = "htaccess";
        arr[25] = "htpasswd";
        arr[26] = "ksh";
        arr[27] = "lnk";

        arr[28] = "mdb";
        arr[29] = "mde";
        arr[30] = "mdt";
        arr[31] = "app";
        arr[32] = "pif";
        arr[33] = "vb";
        arr[34] = "vbscript";

        arr[35] = "asp";
        arr[36] = "cer";
        arr[37] = "csr";
        arr[38] = "jsp";
        arr[39] = "sys";
        arr[40] = "ade";
        arr[41] = "adp";

        arr[42] = "bas";
        arr[43] = "chm";
        arr[44] = "cpl";
        arr[45] = "exe";
        arr[46] = "cmd";
        arr[47] = "sh";
        arr[48] = "php";

        arr[49] = "pl";
        arr[50] = "cgi";
        arr[51] = "386";
        arr[52] = "dll";
        arr[53] = "com";
        arr[54] = "torrent";
        arr[55] = "js";

        for (var j = 0; j < arr.length; j++) {

            if (trim(checkParam.toLowerCase()) == arr[j]) {
                flag = false;
            }
        }

        if (!flag) {
            var id = '998';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("attachmentSourceId",-1);
            check = false;
            return check;
        }
    }
    return check;
}

function fnDwnLdAttachment(imgSeq, imgName) {
    var URL = "DownLoadImage.do?method=downloadImage&ImageSeqNo=" + imgSeq + "&download=Y" + "&ImageName=" + imgName + "";
    window.open(URL, 'AppendixImage', "location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=" + screen.width + ",height=600");
}

function fnDeleteAttachment(attachment) {
	$('#delete'+attachment).tooltip('hide');
	$('#delete'+attachment).parent().addClass('list-group-item-danger');
    var a = confirm("Are you sure to delete this attachment?");    
    if (a) {
        var seqNo = $('#seqNo1058').val();
        $.ajax({
            url: 'ChangeRequest1058Action.do?method=deleteAttachment',
            data: {"param": attachment, "seqNo": seqNo},
            dataType: "json",
            cache: false,
            success: function(result) {
                if (result != null)
                {
                	try{
                		var table='<ul class="list-group list-group-horizontal">';
	                	$('#attachmentTable').empty();
                    	$.each(result.attachment, function(i, item)//Get all 
                   		 {
                    		table = table + '<li class="list-group-item" id="upload' + i + '"><a title="Click to View/Download Attachment" data-toggle="tooltip" href="javascript:fnDwnLdAttachment('
		 	 				 		+item.imgSeqNo+
		 	 				 		','+"'"
		 	 				 		+item.imgName
		 	 			  			+"'"+')">'
			 	 			  		+item.imgName+
				 			  			'</a>&nbsp; <a href="javascript:fnDeleteAttachment('
						  			+item.imgSeqNo+
						  			')"  data-toggle="tooltip" id="delete' + item.imgSeqNo +'" class="delattach" title="Click to delete attachment"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a></li>';									
		   	 	 			});
                    	
                	table = table + '</ul>';
                    $('#attachmentTable').append(table);
                    $('[data-toggle="tooltip"]').tooltip('destroy');
                	$('[data-toggle="tooltip"]').tooltip();
			 				
				} catch(err) {
		   	 	 	window.location = "AjaxException.do";	
		   	 	 	}
                }
                else {
                    window.location = "AjaxException.do";
                }
            }
        });
    }
    
    $('#delete'+attachment).parent().removeClass('list-group-item-danger');
}

function fnCheckReqDetRadios() {

    if ($("input[name='requestFrom']:checked").val() != undefined)
        $('#categorySeqNo').val($("input[name='requestFrom']:checked").val());
    if ($("input[name='requestType']:checked").val() != undefined)
        $('#reqTypeSeqNo').val($("input[name='requestType']:checked").val());
    if ($("input[name='mcrnumberReq']:checked").val() != undefined)
        $('#mcrReq').val($("input[name='mcrnumberReq']:checked").val());

}

function completeCallback1058(result){
	clearAlerts();
	var checkAttachmentReturned=true;
	if(result!=null && result!=""){
		if(result.indexOf("filesizetoolarge") !=-1){
			var id='999';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("attachmentSourceId",-1);
			checkRadios();
			checkAttachmentReturned=false;
			$('.gly-spin').remove();
		}else{
			try{
				result = $.parseJSON(result);
			
				var table='<ul class="list-group list-group-horizontal">';
			
				$('#attachmentTable').empty();
				
				$.each(result.attachment, function(i,item) { //Get All elements
					
 	 				 table = table + '<li class="list-group-item" id="upload' + i + '"><a title="Click to View/Download Attachment" data-toggle="tooltip" href="javascript:fnDwnLdAttachment('
 	 				 		+item.imgSeqNo+
 	 				 		','+"'"
 	 				 		+item.imgName
 	 			  			+"'"+')">'
	 	 			  		+item.imgName+
		 			  			'</a>&nbsp; <a href="javascript:fnDeleteAttachment('
				  			+item.imgSeqNo+
				  			')"  data-toggle="tooltip" id="delete' + item.imgSeqNo +'" class="delattach" title="Click to delete attachment"><span class="glyphicon glyphicon-remove text-red" aria-hidden="true"></span></a></li>';

	   	 	 			});
				       
					table = table + '</ul>';
				    $('.gly-spin').remove();
	 				$('#attachmentTable').append(table);
	 				checkRadios();
	 				$('[data-toggle="tooltip"]').tooltip('destroy');
	 				$('[data-toggle="tooltip"]').tooltip();
	 				$( "#upload0" ).fadeTo( "slow" , 1, function() {
	 					$(this).find(".glyphicon").remove();
	 					$(this).append('<span class="glyphicon glyphicon glyphicon-ok text-green" aria-hidden="true"></span>');
	 					$(this).addClass('list-group-item-success').fadeTo( "slow" , 1, function() {
	 						$(this).find(".glyphicon").remove();
	 						$(this).find(".delattach").append('<span class="glyphicon glyphicon glyphicon-remove text-red" aria-hidden="true"></span>');	 						
	 						$(this).removeClass('list-group-item-success');
		 				});
	 				});
   	 	 				
   	 	 		}catch(err){
   	 	 			window.location = "AjaxException.do";	
   	 	 		}		
       	 	 }
		}
		else{			
			window.location = "AjaxException.do";	
		}
				
	
	$('#attachmentName').val("");
	$('.gly-spin').remove();  
	$("#form").removeAttr("target");

}


$(document).ready(function() {
	
		$.ajaxSetup({ cache: false }); 

	    $('.nonLsdbClaSeq').click(function() {
	    	
			document.forms[0].hdnSelectedChangeReqSeqNo.value = $('.nonLsdbClaSeq:checked').val();
				    	
	        var row = [];
	        $(this).closest('table').map(function(i) {
	            $(this).find('td').each(function(i) {
	                row.push(trim($(this).html().replace('&nbsp;', '')));
	                console.log(row);
	            });
	        });
	        
	        claFrmSpec = row[2];
	        claFrmDesc = row[3];
	        claFrmEng = row[4];
	        claToSpec = row[6];
	        claToDesc = row[7];
	        claToEng = row[8];
	        claReason = row[10];
	        
	    });
	    
		checkRadios();
		var attachmentSource=$('#attachmentSource');

		$('#attachmentSource').on('change',function() {
			
			var check = validate($('#attachmentSource').val());
			
			if(check){
				var	label = $('#attachmentSource').val().replace(/\\/g, '/').replace(/.*\//, '');
				$('#attachmentName').val(label);
				var form = document.forms[0];
				
				$("#form").on('submit', uploadFileFor1058);
				$("#form").trigger('submit');
				
				attachmentSource.replaceWith( attachmentSource = attachmentSource.clone( true ) );
			    
			  }
			  else{
				attachmentSource.replaceWith( attachmentSource = attachmentSource.clone( true ) );
				checkRadios();
			}
			
			$('#form').unbind('submit');
		
	});
});
	


function uploadFileFor1058()	{
	 
		$('.btn-file').append('<i class="glyphicon glyphicon-refresh gly-spin"></i>');
 
	     if(!isAjaxUploadSupported()){ //IE fallback
	    	
	    	var d = document.createElement('DIV');
	    	
	    	var iframeID = "iframe" + Math.floor(Math.random() * 99999);
	    	 
	 		d.innerHTML = '<iframe  '
	 			    + 'src="about:blank" style="display:none;" id="'
	 			    + iframeID + '" name="'
	 			    + iframeID + '"></iframe>';
	 		
	        $('#form').prop("target", iframeID);
	        
	 		document.body.appendChild(d);
		 		
	 		iframeIdmyFile = document.getElementById(iframeID);
			
	         var eventHandlermyFile = function () {
	             
	        	 if (iframeIdmyFile.detachEvent) 
	                 iframeIdmyFile.detachEvent("onload", eventHandlermyFile);
	
	        	 completeCallback1058(getIframeContent(iframeIdmyFile))
	
	         }
	
	         if (iframeIdmyFile.attachEvent) 
	             iframeIdmyFile.attachEvent("onload", eventHandlermyFile);
		 		
			document.forms[0].action="ChangeRequest1058Action.do?method=uploadAttachment";
	         
	     } else {
	    	
	    	var formData = new FormData($(this)[0]);
	    	
	 	    $.ajax({
	 	        url: 'ChangeRequest1058Action.do?method=uploadAttachment',
	 	        type: 'POST',
	 	        data: formData,
	 	        cache: false,
	 	        dataType: 'text',
	 	        processData: false, // Don't process the files
	 	        contentType: false, // Set content type to false as jQuery will tell the server its a query string request
	 	        success: function(data)
	 	        {
	 	        	completeCallback1058(data);
	 	        },
	 	        error: function(jqXHR, textStatus, errorThrown)
	 	        {
	 	        	window.location = "AjaxException.do";
	 	        }
	 	    });
	 	    
	 	    return false;
	     }
 
}
//Added for Upload attachment fix end here

//Added for CR-117
function fnDisableRepList(){
if($("input[name='requestType']:checked").val()=="1"){
//$("#sysEngg").prop("disabled", true); //Commented for CR-128
$("#opRep").prop("disabled", true);
$("#finRep").prop("disabled", true);
$("#pgmMgr").prop("disabled", true);
//Fix for IE9 & 10 where the select2 does not get disabled
if(navigator.userAgent.indexOf('MSIE 10') !== -1 || navigator.userAgent.indexOf('MSIE 9') !== -1){
	$('#opRep').select2('destroy');
	$('#opRep').prop('disabled', true);
	$('#opRep').select2({theme:'bootstrap'});	
	$('#finRep').select2('destroy');
	$('#finRep').prop('disabled', true);
	$('#finRep').select2({theme:'bootstrap'});
	$('#pgmMgr').select2('destroy');
	$('#pgmMgr').prop('disabled', true);
	$('#pgmMgr').select2({theme:'bootstrap'});
	}
//Ends fix for IE9 & 10
}else{
$("#sysEngg").prop('disabled', false);
$("#opRep").prop('disabled', false);
$("#finRep").prop('disabled', false);
$("#pgmMgr").prop('disabled', false);
$("#propMgr").prop('disabled', false);
}	
}
function fnEnableRepList(){
if($("input[name='requestType']:checked").val()=="1"){
$("#propMgr").prop("disabled", false);	
$("#sysEngg").prop("disabled", false);//Added for CR-128
}else{
$("#sysEngg").prop("disabled", false);
$("#opRep").prop("disabled", false);
$("#finRep").prop("disabled", false);
$("#pgmMgr").prop("disabled", false);
$("#propMgr").prop("disabled", false);	
}	
}
//Added for CR-117 Ends here

//Added for CR-120 Resizable TextArea
function adjustHeight(textAreatID){
if($.trim($("#"+textAreatID).val()) != ""){
	var initHeight = 0;
	try{
	initHeight =document.getElementById(textAreatID).value.match(/\n/g).length+1;
	}
	catch(err){
		initHeight = 1;
	}
	
	if(initHeight >15);
	{
		$("#"+textAreatID).css("overflow","auto");
		$("#"+textAreatID).css("height","235px");
	}
	if(initHeight >3 && initHeight <15)
	{
		var afterHeight = 52+(16 * (initHeight -3));
		$("#"+textAreatID).css("height",afterHeight+"px");
	}
	if(initHeight <=3){
		$("#"+textAreatID).css("overflow","hidden");
		$("#"+textAreatID).css("height","52px");
		
	}
}

}

// Function remove all instances of tinymce
function fnTinymceCall() {
	for (var i = tinymce.editors.length - 1 ; i > -1 ; i--) {
        var ed_id = tinymce.editors[i].id;
        tinyMCE.execCommand("mceRemoveEditor", true, ed_id);
    }
}
/* Modified for CR_131
$(document).ready(function(){
adjustHeight("earliestSA");
adjustHeight("partAdded");
adjustHeight("partDeleted");
adjustHeight("dispositionSM");
adjustHeight("supplierACFE");

});

$(document).on('input keyup change', '.resizeStationAffected , .resizePartAddDelete ,.resizeSupplierAffected', function() { // keyup, change to get IE
    var ch, copy, sh;
    copy = $(this).data('tacopy');
	
    if (!copy) {
      copy = $(this).clone();
      copy.css('position', 'absolute');
      copy.css('left', '-99999999px');
      copy.width($(this).width());
      copy.height($(this).height());
	  
      copy.prop('disabled', true);
      copy.insertAfter($(this));
      $(this).data('tacopy', copy);
    }
    copy.val($(this).val());
    copy.height(0);
    sh = copy.get(0).scrollHeight;
    ch = $(this).height();
    if (sh !== ch) {
			if(sh <=52 ){
				$(this).css("overflow","hidden");
				$(this).height(52);
			}
			
			else if(sh >= 235)
			{
				$(this).css("overflow","auto");
			}
			else{
				$(this).css("overflow","hidden");
				$(this).height(sh);
			}
					
    }
  });
//Added for CR-120 Resizable TextArea Ends here*/