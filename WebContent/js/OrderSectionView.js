function fnChangeRevisionView()
{   
	document.forms[0].hdnRevViewCode.value=document.forms[0].revCode.options[document.forms[0].revCode.selectedIndex].value;

	document.forms[0].action="OrderSection.do?method=fetchSectionDetails";
	document.forms[0].submit();
}
function fnSaveComponent(subSecSeqNo)
{
			
	for(var j = 0; j < document.forms[0].btnSaveComponent.length; j++){
		
		document.forms[0].btnSaveComponent[j].disabled = true;
	}
	document.forms[0].action="OrderSection.do?method=saveComponent&subsecno="+subSecSeqNo;
	document.forms[0].submit();
}
function fnPublish(confirmBox)//Modified for CR_90
{
	
	publish = document.forms[0].specStatusCode;

	//Added for CR
	//var modSeqNo = document.forms[0].hdnmodseqno.value;
	//Ends
	
	if(publish.options[publish.selectedIndex].value=="-1"){
				document.getElementById("PublishSpecButton").disabled = false;
				var id = '222';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("spcStatus",-1);
				return false;
	}

	if(publish.options[publish.selectedIndex].text=='Final'){

		document.forms[0].revFlag.value='N';
		document.forms[0].finalFlag.value='Y';
	}else{

		document.forms[0].revFlag.value='Y';
		document.forms[0].finalFlag.value='N';
	}
	
	if(confirmBox == 'Y')	{
	
		var conf = window.confirm("Are you sure to Publish Spec?");
	
		if(conf){
			/* commented for CR-116 Fix
			 if(publish.options[publish.selectedIndex].value=="3")
				fnShowModal('ThankYou');
			else*/
			fnPublishAction();
		}else{
			document.getElementById("PublishSpecButton").disabled = false;
		}
	}
	else{
		/* commented for CR-116 Fix
		 if(publish.options[publish.selectedIndex].value=="3")
				fnShowModal('ThankYou');
			else*/
			fnPublishAction();
	
	}

}
function fnOkay()
{
//Added Fix for disabling the button
document.getElementById("okaybutton").disabled = true;
fnPublishAction();
}
function fnPublishAction()
{
			var modSeqNo = document.forms[0].hdnmodseqno.value;
			document.forms[0].actionType.value ="Publish";
			document.getElementById("PublishSpecButton").disabled = true;
			document.forms[0].action="OrderSection.do?method=publish&modSeqNo="+modSeqNo;
			document.forms[0].submit();
}
function fnPublishSubLevel(confirmBox)//Modified for CR_90
{
	//Final is not to be selected here
	publish = document.forms[0].specStatusCode;

	//Added for CR
	var modSeqNo = document.forms[0].hdnmodseqno.value;
	//Ends

	document.forms[0].specStatusCode.value="-1";
	document.forms[0].revFlag.value='N';
	
	if(confirmBox == 'Y')	{
	
	var conf = window.confirm("Are you sure to Publish Sub Level Revision?");

	if(conf){
		document.forms[0].actionType.value ="PublishSub";
		document.getElementById("PublishSubLevelButton").disabled = true;
		document.forms[0].action="OrderSection.do?method=publish&modSeqNo="+modSeqNo;
		document.forms[0].submit();
	}else{
		document.getElementById("PublishSubLevelButton").disabled = false;
	}
	}
	else{
		document.forms[0].actionType.value ="PublishSub";
		document.getElementById("PublishSubLevelButton").disabled = true;
		document.forms[0].action="OrderSection.do?method=publish&modSeqNo="+modSeqNo;
		document.forms[0].submit();
	}
	
}


function funAddNewClause(secseqno,revcode,subsecseqno,orderkey)
{
	
	orderno = document.forms[0].hdnorderno.value;
	cusseqno = document.forms[0].hdncusseqno.value;
	modseqno = document.forms[0].hdnmodseqno.value;

	document.forms[0].action="orderAddClauseAction.do?method=initLoad&secSeq="+secseqno+"&revCode="+revcode+"&orderkey="+orderkey+"&subsecseqno="+subsecseqno;
	
	document.forms[0].submit();

	
}

/* 
 * subsecno, parameter is passed for landing to the visited subsection
 * The subsecno is passed to the rev clause screen and then carried back
 * to the visited subsection in Modify Spec screen
*/

function fnSelectClauseRevSpec( secseqno,revcode, orderkey,clauseseqno,custseqno,deleteInd, deleteFlag ,subsecno)
{
	
	var specCode=document.forms[0].currentSpecStatus.value;

	//Added for CR No 49 
	if(deleteInd=='Y'){
		//document.location.href="#"+clauseseqno;
		var id = '812';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("ClaRevSpecLink"+clauseseqno,-1);
		//showScrollerrors("currentSpecstatus",-1)
		
	}else if(deleteFlag=='D'){
	
		var id = '815';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("ClaRevSpecLink"+clauseseqno,-1);
		
	}
	else{
		document.forms[0].action="OrderSelClauseRevAction.do?method=fetchClauseVersions&secSeq="+secseqno+"&revCode="+revcode+"&orderkey="+orderkey+"&clauseSeq="+clauseseqno+"&custSeqNo="+custseqno+"&specCode="+specCode+"&subsecno="+subsecno;
		document.forms[0].submit();
	}
}

//Added for CR No 49
function fnLoadSubClause(clauseseqno,orderkey,secseqno,revcode,clauseno,custseqno,deleteInd)
{

	if(deleteInd=='Y'){
		//document.location.href="#"+clauseseqno;
		var id = '812';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("addSubClaLink"+clauseseqno,-1);

	}else{
		
		document.forms[0].action="AddSubClause.do?method=fetchClauses&clauseSeq="+clauseseqno+"&orderKey="+orderkey+"&secSeq="+secseqno+"&revCode="+revcode+"&clauseNum="+clauseno+"&custSeqNo="+custseqno;
		document.forms[0].submit();
	}

}


//Added for CR No 49

function fnDisplayModelIndicator(clauseSeq, orderKey, secSeq, revCode, specCode, deleteInd,subsecseqno)
{
	if(deleteInd=='Y'){
		//document.location.href="#"+clauseseqno;
		var id = '812';
		hideErrors();
		addMsgNum(id);
		//showScrollErrors("dispModelIndicatorLink"+clauseSeq,-1); edited for CR-131
		showScrollErrors();

	}else{
	
		document.forms[0].action="ModelIndAcceptRejectClauseAction.do?method=fetchClauseVersions&clauseSeq="+clauseSeq+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&specCode="+specCode+"&subsecseqno="+subsecseqno;
		document.forms[0].submit();
	}
	

}

function fnDisplayCopyIndicator(clauseSeq, orderKey, secSeq, revCode, specCode, deleteInd,subsecseqno)
{
	
	if(deleteInd=='Y'){
		//document.location.href="#"+clauseseqno;
		var id = '812';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("dispCopyIndicatorLink"+clauseSeq,-1);

	}else{
	
		document.forms[0].action="CopyIndAcceptRejectClauseAction.do?method=fetchClauseVersions&clauseSeq="+clauseSeq+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&specCode="+specCode+"&subsecseqno="+subsecseqno;
		document.forms[0].submit();
	}
	

}

//Ends

function fnAddSubClause( secseqno,secname,seccode,revcode,subseccode,subsecname,subsecseqno,orderkey,clauseseqno)
{
	
	orderno = document.forms[0].hdnorderno.value;
	cusseqno = document.forms[0].hdncusseqno.value;
	modseqno = document.forms[0].hdnmodseqno.value;
	document.forms[0].action="AddSubClause.do?method=fetchClauses&secSeq="+secseqno+"&secName="+secname+"&secCode="+seccode+"&revCode="+revcode+"&subSecCode="+subseccode+"&subSecName="+subsecname+"&orderkey="+orderkey+"&orderno="+orderno+"&clauseSeq="+clauseseqno+"&modseqno="+modseqno+"&subsecseqno="+subsecseqno;
	
	document.forms[0].submit();
}



function fnViewSpec(){
	
	 var revCode = document.forms[0].revCode.options[document.forms[0].revCode.selectedIndex].value;
	 window.open("ViewSpecByOrder.do?method=initLoad&orderKey="+document.forms[0].orderKey.value+"&revCode="+revCode+"","Clause","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=950,height=500");
}


//Added for LSDB_CR-74 
function fnPopUpClose()
{
	var success = parseInt(document.forms[0].messageID.value);
	if(success == 6){//Success
		
		var orderKey = document.forms[0].orderKey.value;
		var secSeq =   document.forms[0].orderSecSeqNo.value;
		var secCode = document.forms[0].orderSecCode.value;
		var secName = document.forms[0].orderSecName.value;
		var revCode = document.forms[0].revCode.value;
		var subsecseqno=document.forms[0].subSecSeqNo.value;
		self.close();
		window.opener.document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&secCode="+secCode+"&secName="+secName+"&revCode="+revCode+"&subsecno="+subsecseqno;
		window.opener.document.forms[0].submit();

	}
	
}
function displayClauseReason(secseqno,revcode, orderkey,clauseseqno,subsecseqno,clauseVersionNo,flag)
{

		var specCode=document.forms[0].currentSpecStatus.value;
		window.open("OrderSection.do?method=displayClauseReason&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&flag="+flag+"&clauseVersionNo="+clauseVersionNo+"&specCode="+specCode,"Clause","location=0,resizable=yes ,status=1,scrollbars=1,width=850,height=300");

}

//added for CR_81 0n 08/01/2010

function fncharGrpCombntnList(clauseseqno)
{
		window.open("OrderSection.do?method=fetchCharCombntnEdlMap&clauseSeq="+clauseseqno+"","CharGrpCombtn","location=0,resizable=yes ,status=1,scrollbars=1,width=680,height=500");

}

function updateReason(flag)
{

	var orderkey = document.forms[0].orderKey.value;
	var secseqno =   document.forms[0].orderSecSeqNo.value;
	var subsecseqno=document.forms[0].subSecSeqNo.value;
	var clauseseqno = document.forms[0].clauseSeqNo.value;
	var clauseVersionNo = document.forms[0].clauseVersionNo.value;
	var secCode = document.forms[0].orderSecCode.value;
	var secName = document.forms[0].orderSecName.value;
	var revcode = document.forms[0].revCode.value;
		
	//This check is for delete clause link check
	//Modified for CR_79 removing validation before Opening Status on 28-Oct-09 by RR68151 
	var reason = trim(document.forms[0].reason.value);
	if(document.forms[0].currentSpecStatus.value >= 3){ 
	
	if(reason.length==0){
		var id = '128';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}
	}
	if(reason.length > 2000 ){
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}
	
	if(flag == 'D'){//from Delete Clause link
		var conMsg=window.confirm("Are you sure you want to Delete the selected Clause and all its Sub Clauses(if any) ?");
		if(conMsg == true){
			document.forms['OrderSectionForm'].action="OrderSection.do?method=deleteClauseDetails&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&flag="+flag+"&clauseVersionNo="+clauseVersionNo;
			document.forms['OrderSectionForm'].submit();
		}
	}else if(flag == 'R'){// from Enter Reason link
		
		document.forms['OrderSectionForm'].action="OrderSection.do?method=updateReason&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&flag="+flag+"&clauseVersionNo="+clauseVersionNo;
		document.forms['OrderSectionForm'].submit();
	}
	
}

//Ends

function fnDeleteClause(secseqno,revcode, orderkey,clauseseqno,subsecseqno)
{
    	 var conMsg=confirm("Are you sure you want to Delete the selected Clause and all its Sub Clauses(if any) ?");
		 if(conMsg == true)
		 {
		 	document.forms[0].action="OrderSection.do?method=deleteClauseDetails&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno;
			document.forms[0].submit();
		 }
		 else
		 {
		 }
}

/*
 * Added for LSDB_CR-74 by ka57588
 * setting user marker flag as 'Y' or 'N'
*/
function fnSetUserMarker(secseqno,revcode, orderkey,clauseseqno,subsecseqno,usermarkerflag){


//Modified for CR_99 to remove the user marker alerts (False ---> True). 
	var conMasg = true;
//Commented for CR_99
	/*if(usermarkerflag == 'Y'){
		conMasg = confirm("Are you sure to Mark the Clause ?");
	}else{
		conMasg = confirm("Are you sure to Unmark the Clause ?");
	}*/
// CR_99 Changes Ends Here.
    //Added for CR_109
	if(usermarkerflag == "N"){
	document.forms[0].markClaReason.value = "";
	}
    //Added for CR_109 Ends here


	if(conMasg){
		document.forms[0].action="OrderSection.do?method=updateUserMarker&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&usermarkflag="+usermarkerflag;
		document.forms[0].submit();
		
	}else{
		return;
	}
	
}

function fnMainFeature(){

  document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value;//+"&revCode="+document.forms[0].revCode.value;
  document.forms[0].submit();
}


function fnLoadAppendix(){


  document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].orderKey.value;//+"&revCode="+document.forms[0].revCode.value;
  document.forms[0].submit();
}


function fnLoadPerfCurve(){

document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value;//+"&revCode="+document.forms[0].revCode.value;
document.forms[0].submit();

}
//Added for CR-72 Proof Reading Draft PDF
function fnGenerateProofReadingEnggDraft(){


document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value
											/*"&revCode="+document.forms[0].revCode.value*/+"&pdfType=Engg";
document.forms[0].submit();

}


function fnGenerateProofReadingSalesDraft(){

document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
	                              /*"&revCode="+document.forms[0].revCode.value+*/"&pdfType=Sales";
document.forms[0].submit();                            

}

//Added for CR_79 to add PDF Header Image on 30-OCT-09 by RR68151 
/*
*	Name: fnTurnPDFHeaderFlag
*	Purpose:Used to Turn ON/OFF Header image on the PDFs.
*/

function fnTurnPDFHeaderFlag(pdfHeaderFlag){

	
		document.forms[0].action="OrderSection.do?method=turnPDFHeaderImage&flag="+pdfHeaderFlag;
		document.forms[0].submit();
}

//Added for CR_81 to bring Edl Changes for the Order Level Clause
/*
*	Name: fnDisplayEdlIndicator
*	Purpose: Used to display the Clause EDL Changes at the Model Level.
*/

function fnDisplayEdlIndicator(clauseSeq, orderKey, secSeq, revCode, specCode, deleteInd,subsecseqno)
{
	
	if(deleteInd=='Y'){
		var id = '812';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("dispEdlIndicatorlink"+clauseSeq,-1);

	}else{
	
		document.forms[0].action="EdlIndAcceptRejectAction.do?method=fetchClauseEdlChanges&clauseSeq="+clauseSeq+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&specCode="+specCode+"&subsecseqno="+subsecseqno;
		document.forms[0].submit();
	}
	
	
	

}

//Added for CR_86 to add dynamic Numbeing on and off by SD41630 (adding and removing the reserved clause in the that order)
/*
*	Name: fnTurnDynamicNoFlag
*	Purpose:Used to Turn ON/OFF Dynamic numbering.
*/

function fnTurnDynamicNumOnOffFlag(dynamicNoFlag){
if(dynamicNoFlag =='Y')
{
	var conMsg=window.confirm("Turning On/Off will change the clause numbering. Are you sure want to turn On Dynamic Clause Numbering? Clause numbers which display 'RESERVED' next to the clause number will be removed from the working spec. These clause numbers will be brought back if Dynamic Numbering is turned back Off.");
	}else{
	var conMsg=window.confirm("Turning On/Off will change the clause numbering. Are you sure want to turn Off Dynamic clause Numbering? Clause numbers which are not used will display 'RESERVED' next to the clause number.");
	}
		if(conMsg == true){
		document.forms[0].actionType.value ="TurnDynamic";
		document.getElementById("TurnDynamicOnOffButton").disabled = true;
		document.forms[0].action="OrderSection.do?method=turnDynamicNumOnOff&flag="+dynamicNoFlag;
		document.forms[0].submit();
		}
}
//Added fnRemoveReserved  for CR_86
/*
*	Name: fnRemoveReserved
*	Purpose:Used to delete permanently the reserved clause  in perticular order.
*/

function fnRemoveReserved(secseqno,revcode, orderkey,clauseseqno,subsecseqno,flag)
{
    	 var conMsg=confirm("Are you sure you want to Delete the selected Clause and all its Sub Clauses(if any) ?");
		 if(conMsg == true)
		 {
		 	document.forms[0].action="OrderSection.do?method=deleteClauseDetails&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&flag="+flag;
			document.forms[0].submit();
		 }
		 else
		 {
		 }
		 }
		 
/*
*	Name: fnValidateSaveComponent by Rahul
*	Purpose:Used to validate for Required Component Groups using Ajax
*
*/


function fnValidateSaveComponent(subSecSeqNo,orderKeyNo,orderSecCode,orderSecName) {
	var SecCode = document.forms[0].orderSecCode.value;
	var SecName = document.forms[0].orderSecName.value;
	var values = "";
	var cnt = 0;
		
	for(var j = 0; j < document.forms[0].btnSaveComponent.length; j++){
		
		document.forms[0].btnSaveComponent[j].disabled = true;
	}
	
	var compGrpSeqNos = $.map($('[name="componentGroupSeqNo"]'), function (e) {
                 return $(e).val();
             });
	
	var compSeqNos = $.map($('[name="compSeqNo"]'), function (e) {
                 return $(e).val();
             });
             
	 $.getJSON("OrderSection.do?method=validateSaveComponent",{subsecno: subSecSeqNo, orderKey: orderKeyNo, componentGroupSeqNo: compGrpSeqNos, compSeqNo: compSeqNos, ajax: 'true', type: 'post', cache: 'false'}, function(j){
    	if (j != null) {
	    	if (j.Components != null){
	    			
	    		$.each(j.Components.split(','),function(i,item){
	    			if (item != null && item.length > 0){
	    					cnt++;	
		        			values = values + '<br/>' + cnt + ") " + item;
		        		}
		        	})
		        	
		        bootbox.confirm("The following required component groups are not selected in this order:<br/><b>"+ values + 
				                      '</b><br/><br/>' + "By selecting OK, the Spec will be saved even though the Required Component Groups shown above are not selected", function(result) {
			    	if (result)	{
			    		document.forms[0].action="OrderSection.do?method=saveComponent&subsecno="+subSecSeqNo;
						document.forms[0].submit();
				  	}else{
				  		for(var j = 0; j < document.forms[0].btnSaveComponent.length; j++){
							document.forms[0].btnSaveComponent[j].disabled = false;
						}
					}
				}); 
		        
	    		/*	Modified for CR_131      	
		        var conMsg=window.confirm("The following required component groups are not selected in this order:"+ values +  
					                      '\n\n' + "By selecting OK,the Spec will be saved even though the Required Component Groups shown above are not selected ");
				if(conMsg == true){
					document.forms[0].action="OrderSection.do?method=saveComponent&subsecno="+subSecSeqNo;
					document.forms[0].submit();
				}else{
					for(var j = 0; j < document.forms[0].btnSaveComponent.length; j++){
						document.forms[0].btnSaveComponent[j].disabled = false;
					}
				}*/
			
			}
			else	{
				document.forms[0].action="OrderSection.do?method=saveComponent&subsecno="+subSecSeqNo;
				document.forms[0].submit();
			}
		}
		else {
			window.location = "AjaxException.do";
		}
    })
}

/*
*	Name: fnValidatePublishComponent by Rahul
*	Purpose: Used to validate for Required Component Groups using Ajax
*
*/

function fnValidatePublishComponent() {
	var orderKey = document.forms[0].orderKey.value;
	var modelSeq = document.forms[0].modelSeqNo.value;
	//Added For CR_104 
	var custMdlName = document.forms[0].custMdlName.text;
	var cnt = 0;
	
	document.getElementById("PublishSpecButton").disabled = true;
	var values = "";
	 $.getJSON("OrderSection.do?method=validatePublish",{orderKey: orderKey, modelSeqNo: modelSeq, ajax: 'true', type: 'post', cache: 'false'}, function(j){
    	if (j != null) {
	    	if (j.Components != null){	
	    		$.each(j.Components.split(','),function(i,item){
	    			if (item != null && item.length > 0){	
	    					cnt++;
		        			values = values + '<br/>' + cnt + ") " + item;
		        		}
		        	})
		        	
		    bootbox.confirm("The following required component groups are not selected in this order:<br/><b>"+ values + 
				                      '</b><br/><br/>' + "By selecting OK, the Spec will be published even though the Required Component Groups shown above are not selected", function(result) {
			    	if (result)	{
					  fnPublish('N');
				  	}else{
				  		clearAlerts();
						document.getElementById("PublishSpecButton").disabled = false;
					}
				}); 
			}
			else	{
				fnPublish('Y');
			}	
		}
		else {
			window.location = "AjaxException.do";
		}	
	})
	document.forms[0].custMdlName.value=custMdlName;
}

/*
*	Name: fnValidatePublishSubLevelComp by Rahul
*	Purpose: Used to validate for Required Component Groups using Ajax
*
*/

function fnValidatePublishSubLevelComp() {
	var orderKey = document.forms[0].orderKey.value;
	var modelSeq = document.forms[0].modelSeqNo.value;
	//Added For CR_104 
	var custMdlName = document.forms[0].custMdlName.text;
	var cnt = 0;
	
	document.getElementById("PublishSubLevelButton").disabled = true;
	var values = "";
	 $.getJSON("OrderSection.do?method=validatePublish",{orderKey: orderKey, modelSeqNo: modelSeq, ajax: 'true', type: 'post', cache: 'false'}, function(j){
	    if (j != null) {
	    	if (j.Components != null){	
	    		$.each(j.Components.split(','),function(i,item){
	    			if (item != null && item.length > 0){	
		        			cnt++;
		        			values = values + '<br/>' + cnt + ") " + item;
		        		}
		        	})
		        	
		    bootbox.confirm("The following required component groups are not selected in this order:<br/><b>"+ values + 
				                      '</b><br/><br/>' + "By selecting OK, the Spec will be published even though the Required Component Groups shown above are not selected", function(result) {
			    	if (result)	{
			    		fnPublishSubLevel('N');
				  	}else{
				  		clearAlerts();
						document.getElementById("PublishSubLevelButton").disabled = false;
					}
				}); 
		        			      	
	        /* Modified for CR_131 
	         var conMsg=window.confirm("The following required component groups are not selected in this order:"+ values + 
				                      '\n\n' + "By selecting OK,the Spec will be published even though the Required Component Groups shown above are not selected ");
			if(conMsg == true){
				fnPublishSubLevel('N');
				}else{
					document.getElementById("PublishSubLevelButton").disabled = false;
				}*/
			}
			else	{
				fnPublishSubLevel('Y');
			}
		}
		else {
			window.location = "AjaxException.do";
		}	
			
		})
		document.forms[0].custMdlName.value=custMdlName;
}

//CR_91 To view Current EDL number(s) from the working spec  in the html formate
function fnViewCurrentEDLNumbers(){

	var dataLocationType="W"; ////for workingspec("W") order in modiy screen  only
	
	var orderkey = document.forms['OrderSectionForm'].orderKey.value;
	
	var orderNo = document.forms['OrderSectionForm'].hdnorderno.value;
	
	var modelName = document.forms['OrderSectionForm'].modelName.value;
	
	var customerName = document.forms['OrderSectionForm'].customerName.value;
	
	var statusDesc = document.forms['OrderSectionForm'].statusDesc.value;
   	
	window.open("historyEdlAction.do?method=fetchEdlNo&orderKey="+orderkey+"&OrderNum="+orderNo+"&CustomerName="+customerName+"&SpecStatus="+statusDesc+"&modelName="+modelName+"&dataLocationType="+dataLocationType+"",'OrderClause','location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=900,height=500');
}

//Added for CR 91 View ProofReading Spec Supplement
//Modified function name and URL for CR_106 Sales Supplement Changes
function fnGenerateProofReadingEngrSupplement(){

	document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
	                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=EngrSupp";
	document.forms[0].submit();

}
//Added for CR_92 for enabling/disabling sys markers
function fnSysEnDisable(secseqno,revcode, orderkey,clauseseqno,subsecseqno,sysEnDisableFlag)
{
	var msg=false;

	if(sysEnDisableFlag=='H')
	{
		msg=window.confirm("Are you sure you want to disable the System marker ?");
	}
	else if(sysEnDisableFlag=='Y')
	{
		msg=window.confirm("Are you sure you want to enable the System marker ?");
	}

	if(msg == true && (sysEnDisableFlag=='Y' ||sysEnDisableFlag == 'H'))
	{
		document.forms[0].action="OrderSection.do?method=updateSysMarker&secSeq="+secseqno
					+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno
					+"&subsecseqno="+subsecseqno+"&sysEnDisableFlag="+sysEnDisableFlag;
		document.forms[0].submit();
	}
	else
	{
		return;
	}
}
//Added for CR_92 for Deleted Clauses history
function fnDeletedClausesHistory()
{
	var orderkey=document.forms[0].orderKey.value;
	var secSeqNo=document.forms[0].orderSecSeqNo.value;

	document.forms[0].action="OrderSection.do?method=deletedClausesHistoy&secSeqNo="+secSeqNo;
	document.forms[0].submit();
	
}
//Added for CR_92 for retrieving the deleted clause.
function fnRetrieve(currRevFlag,secCode,secName,secSeqNo,subSecSeqNo)
{
	var delForm=document.forms[0];
	var orderKey=delForm.orderKey.value;
	var checkedFlag=false;

	if(document.forms[0].clauseSeqNo.length>1)
	{
		for(i=0;i<document.forms[0].clauseSeqNo.length;i++)
		{
     		if(document.forms[0].clauseSeqNo[i].checked)
     		{
				delForm.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo[i].value;
				checkedFlag=true;		
			}
		}
	}
	else
	{
	    if(document.forms[0].clauseSeqNo.checked)
	    {
			delForm.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo.value;
			checkedFlag=true;
		}
	}
	if(!checkedFlag)
	{
		var id= '20';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);
		return false;
	}
	var clauseSeqNo=delForm.hdnClauseSeqNo.value;
	
	delForm.action="OrderSection.do?method=retrieveDeletedClause&orderKey="+orderKey
					+"&clauseSeqNo="+clauseSeqNo+"&currRevFlag="+currRevFlag+"&secCode="+secCode
					+"&secName="+secName+"&secSeqNo="+secSeqNo+"&subSecSeqNo="+subSecSeqNo;
	delForm.submit();
}
//Added for returning to modify Spec from View Deleted Clause(s) History
function fnModifySpec()
{
	var orderKey = document.forms[0].orderKey.value;
	var secSeq = document.forms[0].orderSecSeqNo.value;
	
	document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey
							+"&secSeq="+secSeq+"&revCode=1";
	document.forms[0].submit();
}
//ENDs here CR_92
//Added for CR_93
function fnConfirmNewComp(compSeqNo) 
{ 
		if ($('#newcomptoord'+compSeqNo).length > 0)
		{
		    $('#newcomptoord'+compSeqNo).modal();
		}
} 
function fnAddNewComp(compSeqNo,newOrderNo,secSeq,subsecseqno,orderKey)
{
document.forms[0].newOrderNo.value=newOrderNo;
document.forms[0].action="OrderSection.do?method=saveNewOrderComp&orderKey="+orderKey
                           +"&secSeq="+secSeq+"&revCode=1"+"&subsecno="+subsecseqno+"&compSeqNo="+compSeqNo;
document.forms[0].submit();
}
function fnReloadSpec(secSeq,subsecseqno,orderKey)
{
$('.modal').modal('hide');//Hide the modal that is currently opened
document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey
                           +"&secSeq="+secSeq+"&revCode=1"+"&subsecno="+subsecseqno;
document.forms[0].submit();
}
//Added for CR_97
$(document).ready(function() {
	//Added code to fix the copyright notice and Section hyperlinks
	//$(window).scroll(fnFooterFixer).resize(fnFooterFixer); 
	if ($('#hiddenReqID').length > 0)
		{
		    $('#hiddenReqID').modal();
		}
	
	$(".fadeClass").animate({opacity: "0.6"}, 'normal');
	
	$(".fadeClass").hover(function() {
	  $(this).stop().animate({opacity: "1"}, 'normal');
	},
	function() {
	  $(this).stop().animate({opacity: "0.6"}, 'normal');
	});
});
//Added CR_100 fix for the copyright notice and Section hyperlinks
function fnFooterFixer()
{     
	if($(document).height() <= ( $(window).height() + $(window).scrollTop()))
		$('#footer').css("margin-top","-23px");
	else
		$('#footer').css("margin-top","-1px");
}
			
function fnGetRequest(requestId)
{
	document.forms[0].action="searchRequestComponentGroup.do?method=fetchComponentGroupDetails&reqID="+requestId;
	document.forms[0].submit();
}
//Added For CR_99
function fnReplaceClause(secseqno,revcode,orderkey,clauseseqno,subsecseqno,salesverflag){
	
$('#SaveSalesVer').unbind('click');

$("#SaveSalesVer").click(function() {
		//Modified for CR_111 by ER91220 (.text ---> .val) to bring the same format as entered
		var salesVerDesc = trim($("#Add_Sales_Version_id").val());
		document.forms[0].salesVerDescription.value=salesVerDesc;
		if (salesVerDesc=="" || salesVerDesc.length==0)	
			alert ("Please enter a valid Sales Clause description");
		else	{
			$.modal.close();
			document.forms[0].action="OrderSection.do?method=addDelSalesVerEngg&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&salesVerFLAG="+salesverflag;
			document.forms[0].submit();	
		}	
		});
		
if (salesverflag =='Y')	{
	fnShowModal('replaceClause');
}
else
{
document.forms[0].action="OrderSection.do?method=addDelSalesVerEngg&secSeq="+secseqno+"&revCode="+revcode+"&orderKey="+orderkey+"&clauseSeq="+clauseseqno+"&subsecseqno="+subsecseqno+"&salesVerFLAG="+salesverflag;
document.forms[0].submit();	
}
}
//Added for CR_100 for reusability of code.
function fnShowModal(id)
{
$('#'+id).modal({
		minHeight:370,
		minWidth:400,
		escClose:false,
		onOpen: function (dialog) {
			dialog.overlay.fadeIn('fast', function () {
			dialog.data.hide();
			dialog.container.fadeIn('fast', function () {
			dialog.data.slideDown('fast');
				});
			});
	}}); 
}
//Added For CR_104
function fnOkayPublish()
{
	//Added Fix for disabling the button
	document.getElementById("okaybutton").disabled = true;
	var bodyContent=null;
	var subject=null;
	var actionType=null;

	var modSeqNo=document.forms[0].modelSeqNo.value;
	bodyContent=trim($('#bodycontentid').val());
	//Added linefeed check after CR_105 test
	bodyContent=trim((bodyContent.replace(/^\n*/, "")).replace(/\n*$/, ""));
	actionType=document.forms[0].actionType.value ="Publish";
	
	if(subject!=null)
	{
		subject="";
	}else{
		subject=document.getElementById("subjectid").value;
	}
	document.forms[0].subject.value=subject;
	if(bodyContent==null || bodyContent==""){
		document.getElementById("okaybutton").disabled = false;
		var id = '922';
	    hideSuggestErrors(mailError);
		addMsgNum(id);
		showSuggestErrors(mailError,'mailError');	
	    $('#bodycontentid').val("");
	    $('#bodycontentid').focus();
	    return false;
	}else {
		if(bodyContent.length>4000){
		 	document.getElementById("okaybutton").disabled = false;
			var id = '924';
	        hideSuggestErrors(mailError);
		    addMsgNum(id);
		    showSuggestErrors(mailError,'mailError');	
		    $('#bodycontentid').focus();
			return false;
		}
		else{
			document.forms[0].bodyCont.value=bodyContent;
			document.getElementById("PublishSpecButton").disabled = true;
			document.forms[0].action="OrderSection.do?method=ordSecPublishAndNotification&modSeqNo="+modSeqNo+"&actionType="+actionType;
			document.forms[0].submit();
		}
	}
 }

//Added for CR_104 - Preserve User Markers
function fnTurnUserMarkerFlag(userMarkerFlag){
	
		document.forms[0].action="OrderSection.do?method=turnUserMarker&flag="+userMarkerFlag;
		document.forms[0].submit();
}
//CR_104 Ends here
//Added for CR_106 - customer and distributor logo on and off
function fnTurnCustomerLogoFlag(custLogoFlag){
	
		document.forms[0].action="OrderSection.do?method=turnLogoONOFF&custLogoFlag="+custLogoFlag;
		document.forms[0].submit();
}

function fnTurnDistributorLogoFlag(distriLogoFlag){
	
		document.forms[0].action="OrderSection.do?method=turnLogoONOFF&distriLogoFlag="+distriLogoFlag;
		document.forms[0].submit();
}

//Added for CR_106 Sales Supplement Changes
function fnGenerateProofReadingSalesSupplement(){

	document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
	                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=SalesSupp";
	document.forms[0].submit();

}
//CR_106 Ends here

/*
*  Added For CR_109
*  Name: fnGenerateProofReadingMarkedClauses
*  Purpose: Used to Generate Marked Clause PDF
*/
function fnGenerateProofReadingMarkedClauses(){

	document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
	                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=MarkedClauses";
	document.forms[0].submit();

}

/*
*  Added For CR_109
*  Name: fnShowMarkClause
*  Purpose: Used to show the MarkClause Overlay
*/
function fnShowMarkClause(secseqno,revcode, orderkey,clauseseqno,subsecseqno,usermarkerflag)
{
		
$('#markClaReason').val("");
/*$("#MarkClause").modal({
	persist: true,
	escClose: true,
	onOpen: function (dialog) {
	dialog.overlay.fadeIn('fast', function () {
		dialog.data.hide();
		dialog.container.fadeIn('fast', function () {
			dialog.data.slideDown('fast');
		});
	});
}}); */
$('#MarkClause').on('shown.bs.modal', function () {
  $('#markClaReason').focus();
})
$('#MarkClause').modal();
$('#saveClaReason').unbind('click');
$("#saveClaReason").click(function () { 
      fnSetUserMarker(secseqno,revcode, orderkey,clauseseqno,subsecseqno,usermarkerflag); 
    });
}

/*
*  Added For CR_109
*  Name: fnConfirmSubSecAdd
*  Purpose: Used to Add New Subsection to Order
*/

function fnConfirmSubSecAdd(secseqno,subsecseqno,orderkey) {
	
	bootbox.confirm("Are you sure to add the selected subsection and its respective clauses to this order?", function(result) {
		  if (result)	{
			  document.forms[0].action="OrderSection.do?method=addNewSubsecToOrder&subsecseqno="+subsecseqno+"&orderKey="+orderkey+"&secSeq="+secseqno;
			  document.forms[0].submit();
		  }
		}); 
			
	
	
	
	/*$('#fadenewsubsec').hide();
	$('#confirm').modal({
		position: ["20%",],
		escClose:false,
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
		var modal = this;
			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				modal.close(); 
				document.forms[0].action="OrderSection.do?method=addNewSubsecToOrder&subsecseqno="+subsecseqno+"&orderKey="+orderkey+"&secSeq="+secseqno;
				document.forms[0].submit();
			});
			// if the user clicks "no"
			$('.no', dialog.data[0]).click(function () {
				modal.close();
				$('#fadenewsubsec').show();
			});
		}
	});*/
}

/*
*  Added for CR_111 Clause rearranging at Order Level
*  Name: fnRearrangeClauses
*  Purpose: Used to fetch clauses for rearranging screen
*/
function fnFetchClaforRearrange(secseqno,revcode,subsecseqno,orderkey)
{	
	orderno = document.forms[0].hdnorderno.value;
	cusseqno = document.forms[0].hdncusseqno.value;
	modseqno = document.forms[0].hdnmodseqno.value;

	document.forms[0].action="OrderClaReArrangeAction.do?method=initLoad&secSeq="+secseqno+"&revCode="+revcode+"&orderkey="+orderkey+"&subsecseqno="+subsecseqno;
	
	document.forms[0].submit();	
}


//Added for CR_113 for view Clauses with Indicators
function fnClauseswithIndicators()
{
	var orderkey=document.forms[0].orderKey.value;
	var secSeqNo=document.forms[0].orderSecSeqNo.value;

	document.forms[0].action="OrderSection.do?method=clausesWithIndicators&secSeqNo="+secSeqNo;
	document.forms[0].submit();
	
}
//Added for CR_113 for view Clauses with Indicators Ends here

//Added for CR-113 QA Fix
function fnReturnGenInfo(){
document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value;
document.forms[0].submit();
}
//Added for CR-113 QA Fix Ends Here

//Added for CR_121 for remove optional clauses
function fnRemoveOptionalClauses()
{
	var conMsg=confirm("Are you sure to remove all RESERVED optional component clauses and renumber the spec?");
		 if(conMsg == true)
		 {
		 	document.forms[0].action="OrderSection.do?method=removeOptionalClauses&orderKey="+document.forms[0].orderKey.value;
			document.forms[0].submit();
		 }
		 else
		 {
		 }
}
//Added for CR_121 for remove optional clauses Ends here
//Added for CR-135 starts here
function fnOrderVsModelDelta(modelName,modelSeqNo,statusDesc,orderKey,orderNo,customerName,dataLocFlag)
{	
	var orderKeys = orderKey;
	var sectionIds="-2";
	var customerName= customerName;
	var selectedModels = modelName;
	var selectedSpecType = statusDesc;
	var orderNumbers = orderNo;
	var dataLocType=dataLocFlag;
	
	var URL="compareSpecAction.do?method=fetchOrderVsModelDeltaDetails&orderKeys="+orderKeys+"&sectionIds="+sectionIds+"&selectedModels="+escape(selectedModels)+"&orderNumbers="+orderNumbers+"&selectedSpecType="+escape(selectedSpecType)+"&customerName="+escape(customerName)+"&dataLocType="+dataLocType;
	window.open(URL,'Clause',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}
//Added for Cr-135 ends here