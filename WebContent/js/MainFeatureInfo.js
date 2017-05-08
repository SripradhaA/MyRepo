
function fnModify(){

var MainInfo=document.forms[0];

flag = false;
//hideErrors();
var index;
secchecked = false;
var desc=MainInfo.compinfoSeqNo;
var len=MainInfo.compinfoSeqNo.length;
var description;
if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(MainInfo.compinfoSeqNo[i].checked){
			MainInfo.hdCompDesc.value =trim(MainInfo.componentDesc[i].value);
			description=MainInfo.hdCompDesc.value;
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){

		MainInfo.hdCompDesc.value = trim(MainInfo.componentDesc.value);
		description=MainInfo.hdCompDesc.value;
		flag=true;
	}
}



if(flag){
	
		if(description=="" || description==0)
			{
									var id = '30';
                                    //hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("componentDesc",index);
                                    return false;

			}
	}
	else{
									var id = '29';
                                    //hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("compinfoSeqNo",-1);
                                    return false;
	
	}



document.forms[0].action="MainFeatureInfo.do?method=updateComponentDesc";
document.forms[0].submit();

}





function fnAdd(){

	
document.forms[0].action="MainFeatureInfo.do?method=loadAddCompInfo";
document.forms[0].submit();

}

function fnSave(){
var AddCompInfo=document.forms[0];
var CompInfo=trim(AddCompInfo.compDesc.value);
if(CompInfo.length==0 || CompInfo.value==""){

		var id = '30';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compDesc",-1);
		return false;
}


document.forms[0].action="MainFeatureInfo.do?method=insertComponentDesc";
document.forms[0].submit();
}

function fnModifySpec(){

document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

/***	Function fnDeleteCompDesc is added based on the requirement of LSDB_CR-32***
**		This Function is used to delete the Component Description which are added in Order level ***
**		Function Added on 07-April-08 ***
**/

function fnDeleteCompDesc(){

var MainInfo=document.forms[0];

flag = false;
//hideErrors();
var index;
secchecked = false;
var desc=MainInfo.compinfoSeqNo;
var len=MainInfo.compinfoSeqNo.length;
var description;
if(desc.length!=undefined){

for(var i=0; i<len; i++){

		if(MainInfo.compinfoSeqNo[i].checked){
			
			index = i;
			flag = true;
			break;
		}
		
	}
}
else{

	if(desc.checked){
		flag=true;
	}
}

if(!flag){

	var id = '29';
    //hideErrors();
    addMsgNum(id);
	showScrollErrors("compinfoSeqNo",-1);
	return false;
	
}
var del=confirm("Are you sure to delete the Component Description ?");

	if(del)
	{
		document.forms[0].action="MainFeatureInfo.do?method=deleteComponentDesc";
		document.forms[0].submit();
	}else{

	}

}


/**
	* Added for LSDB_CR-62
	* This method is used for Changing the Apply Flag
**/
function fnModifyApplyModelComponent(){


 	var compForm = document.forms[0];
	var index;
	compSeqNochecked = false;
	
	if(compForm.compSeqNo.length>0){
	      var cnt = compForm.compSeqNo.length;
		  for(var i=0;i<cnt;i++){
			   if(compForm.compSeqNo[i].checked){
						compSeqNochecked = true;
						index = i;
						if(compForm.displayInSpec[i].checked){
      					 compForm.hdnDispSpec.value="true";
						}else{
							 compForm.hdnDispSpec.value="false";
    					}

						break;
			   }
		  }
	}
	else{
			if(compForm.compSeqNo.checked){
				if(compForm.displayInSpec.checked){
      				 compForm.hdnDispSpec.value="true";
				}else{
					compForm.hdnDispSpec.value="false";
    			}
				compSeqNochecked = true;
			}
	     }

	if (!compSeqNochecked)
	{
		var id= '29';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compSeqNo",-1);
		return false;
	

	}

	document.forms[0].action="MainFeatureInfo.do?method=updateDisplayInSpec";
	document.forms[0].submit();
}

function fnViewSpec(){
	
	 var revCode = document.forms[0].hdnRevViewCode.value;
	 window.open("ViewSpecByOrder.do?method=initLoad&orderKey="+document.forms[0].orderKey.value+"&revCode="+revCode+"","Clause","location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=950,height=500");
}


function fnLoadSection(orderKey,secSeqNo,secCode,secName){

var revCode=document.forms[0].hdnRevViewCode.value;

document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeqNo+"&secCode="+secCode+"&secName="+secName+"&revCode="+revCode;

	document.forms[0].submit();


}

function fnLoadAppendix(){

  
  document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnLoadPerfCurve(){

document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnMainFeature(){

  document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnShowSpecification(){

document.forms[0].action="OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnShowGeneralArrangement(){

document.forms[0].action="orderGenArrangeAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

//Added for CR-72 create ProofReading PDF Draft
function fnGenerateProofReadingEnggDraft(){


document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=Engg";
document.forms[0].submit();

}


//Added for CR 91 View ProofReading Spec Supplement
//Modified function name and URL for CR_106 Sales Supplement Changes
function fnGenerateProofReadingEngrSupplement(){


document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=EngrSupp";
document.forms[0].submit();
}


function fnGenerateProofReadingSalesDraft(){

document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=Sales";
document.forms[0].submit();                            

}
//Added for CR-74 01-06-09
function fnChangeRevisionView() {
    var revCode = document.forms[0].revCode.options[document.forms[0].revCode.selectedIndex].value;
    document.forms[0].action = "MainFeatureInfo.do?method=fetchComponentDesc&orderKey=" + document.forms[0].orderKey.value + "&revCode=" + revCode;
    document.forms[0].submit();
}

//Added for CR_79 to add PDF Header Image on 30-OCT-09 by RR68151 
/*
*	Name: fnTurnPDFHeaderFlag
*	Purpose:Used to Turn ON/OFF Header image on the PDFs.
*/

function fnTurnPDFHeaderFlag(pdfHeaderFlag){

		document.forms[0].action="MainFeatureInfo.do?method=turnPDFHeaderImage&flag="+pdfHeaderFlag;
		document.forms[0].submit();
}
//CR_91 To view Current EDL number(s) from the working spec  in the html formate
function fnViewCurrentEDLNumbers(){

    var dataLocationType="W"; //for workingspec("W") order in modiy screen  only
	var orderkey = document.forms['MainFeatureForm'].orderKey.value;
	var orderNo = document.forms['MainFeatureForm'].orderNo.value;
	var modelName = document.forms['MainFeatureForm'].modelName.value;
	var customerName = document.forms['MainFeatureForm'].customerName.value;
	var statusDesc = document.forms['MainFeatureForm'].statusDesc.value;
   window.open("historyEdlAction.do?method=fetchEdlNo&orderKey="+orderkey+"&OrderNum="+orderNo+"&CustomerName="+customerName+"&SpecStatus="+statusDesc+"&modelName="+modelName+"&dataLocationType="+dataLocationType+"",'OrderClause','location=0,resizable=yes ,status=0,scrollbars=1,WIDTH=900,height=500');
}
//CR_93 added for adding a component desc to order
function fnReaarangeActivate(){
document.getElementById('compDetails').style.border="5px solid RED";
document.getElementById('rearrInfo').style.display="none";
document.getElementById('rearrInfo1').style.display="block";
document.getElementById('btnRearrange').style.display="none";
document.getElementById('btnSave').style.display="block";
document.getElementById('btnModify').style.display="none";
document.getElementById('btnDelete').style.display="none";
	$('#compDetails tr').each(function() 
	{
		if (!this.rowIndex) return;
		$(this).find("td:first").html("&nbsp;");   
		
	}); 
// Initialise the table;
$('#compDetails').tableDnD({
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
          document.forms['MainFeatureForm'].rowIndexList.value = rowIDList;
          $(row).contents('td:first').html("");
        }
	});
}
//CR_93 for Adding the component from one order to another
function fnAddToOrder(compSeqNo)
{
var flag="N";
document.forms[0].action="MainFeatureInfo.do?method=insertComponentDesc&insFlag="+flag+"&compSeqNo="+compSeqNo;
document.forms[0].submit();
}
//CR_93 added for saving the rearanged component desc in order
function fnSaveRearrangeClauses()
{
var MainForm=document.forms['MainFeatureForm'];

	if(MainForm.rowIndexList.value !=null && MainForm.rowIndexList.value !="")
	{
		MainForm.action="MainFeatureInfo.do?method=rearrangeCompDesc";
		MainForm.submit();
	}
	else
	{
		var id = '905';
		//hideErrors();
		addMsgNum(id);
		showScrollErrors("compSeqNo",-1);
	}
}

//Added For CR_100


/*
*	Name: fnValidatePublishComponent by satish
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
    		/* MOdified above for CR_131
        var conMsg=window.confirm("The following required component groups are not selected in this order:"+ values + 
			                      '\n\n' + "By selecting OK,the Spec will be published even though the Required Component Groups shown above are not selected ")
      document.forms[0].orderKey.value=orderKey;
	  document.forms[0].modelSeqNo.value=modelSeq;
		if(conMsg == true){
			fnPublish('N');
			}else{
				document.getElementById("PublishSpecButton").disabled = false;
			}*/
		}
		else	{
			fnPublish('Y');
		}	
			
		})
		document.forms[0].custMdlName.value=custMdlName;
}
/*
*	Name: fnValidatePublishSubLevelComp by satish
*	Purpose: Used to validate for Required Component Groups using Ajax
*
*/

function fnValidatePublishSubLevelComp() {

	var orderKey = document.forms[0].orderKey.value;
	var modelSeq = document.forms[0].modelSeqNo.value;
	var revCode = document.forms[0].revCode.value;
	//Added For CR_104
	var custMdlName = document.forms[0].custMdlName.text;
	var cnt = 0;
	
	document.getElementById("PublishSubLevelButton").disabled = true;
	var values = "";
	 $.getJSON("OrderSection.do?method=validatePublish",{orderKey: orderKey, modelSeqNo: modelSeq, ajax: 'true', type: 'post', cache: 'false'}, function(j){
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
			                      
			document.forms[0].orderKey.value=orderKey;  
			 document.forms[0].modelSeqNo.value=modelSeq;   
			 document.forms[0].revCode.value=revCode;      
		if(conMsg == true){
			fnPublishSubLevel('N');
			}else{
				document.getElementById("PublishSubLevelButton").disabled = false;
			}*/
		}
		else	{
			fnPublishSubLevel('Y');
		}	
			
		})
		document.forms[0].custMdlName.value=custMdlName;
		
}

function fnPublish(confirmBox)
{
	
	publish = document.forms[0].specStatusCode;

//var modSeqNo = document.forms[0].modelSeqNo.value;
	
	
	if(publish.options[publish.selectedIndex].value=="-1"){
				document.getElementById("PublishSpecButton").disabled = false;
				var id = '222';
				//hideErrors();
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
function fnPublishAction(){
            var modSeqNo=document.forms[0].modelSeqNo.value;
			document.forms[0].actionType.value ="Publish";
			document.getElementById("PublishSpecButton").disabled = true;
			document.forms[0].action="MainFeatureInfo.do?method=publish&modSeqNo="+modSeqNo;
			document.forms[0].submit();
}
function fnOkay()
{
	//Added Fix for disabling the button for CR_104
	document.getElementById("okaybutton").disabled = true;
	var bodyContent=null;
	var subject=null;
	var modSeqNo=document.forms[0].modelSeqNo.value;
	bodyContent=trim($('#bodycontentid').val());
	//Added linefeed check after CR_105 test
	bodyContent=trim((bodyContent.replace(/^\n*/, "")).replace(/\n*$/, ""));
	document.forms[0].actionType.value ="Publish";
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
		}else {
			document.forms[0].bodyCont.value=bodyContent;
			document.getElementById("PublishSpecButton").disabled = true;
			document.forms[0].action="MainFeatureInfo.do?method=publishAndNotification&modSeqNo="+modSeqNo;
			document.forms[0].submit();
		}
	}
 }
function fnPublishSubLevel(confirmBox)
{

	//Final is not to be selected here
	publish = document.forms[0].specStatusCode;
	var modSeqNo = document.forms[0].modelSeqNo.value;
	var orderKey = document.forms[0].orderKey.value;
	document.forms[0].finalFlag.value='';
	

var	specStatusCode=document.forms[0].specStatusCode.value="-1";
var	revFlag=document.forms[0].revFlag.value='N';
//var revCode=document.forms[0].revCode.value='1';
var revCode=document.forms[0].revCode.value;
	if(confirmBox == 'Y')	{
	var conf = window.confirm("Are you sure to Publish Sub Level Revision?");

	if(conf){
		document.forms[0].actionType.value ="PublishSub";
		document.getElementById("PublishSubLevelButton").disabled = true;
		document.forms[0].action="MainFeatureInfo.do?method=publish&modSeqNo="+modSeqNo;
		document.forms[0].submit();
	}else{
		document.getElementById("PublishSubLevelButton").disabled = false;
	}
	}
	else{
		document.forms[0].actionType.value ="PublishSub";
		document.getElementById("PublishSubLevelButton").disabled = true;
		document.forms[0].action="MainFeatureInfo.do?method=publish&modSeqNo="+modSeqNo;
		document.forms[0].submit();
	}
}
//Added for CR_100 for reusability of code.
//Modified For CR_104
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
//Added For CR_104 to Edit General Info Text
function fnSaveGenInfoText(){
	var SaveGenInfo=document.forms[0];	
	var generalInfoText=trim(SaveGenInfo.genInfoText.value);
	
	if(generalInfoText.length==0 || generalInfoText.value==""){
			var id = '920';
			addMsgNum(id);
			showScrollErrors("genInfoText",-1);
			return false;
	}
	if(generalInfoText.length>4000){
	
			var id = '921';
			//hideErrors();
			addMsgNum(id);
			showScrollErrors("genInfoText",-1);
			return false;
	}
	//Added for CR_104 to preserve General Information text
	if (SaveGenInfo.presrveGenFlag!=undefined)	{
		if (SaveGenInfo.presrveGenFlag.checked)
			SaveGenInfo.presrveGenInfoFlag.value = "Y";
		else
			SaveGenInfo.presrveGenInfoFlag.value = "N";
	}
	
	document.forms[0].action="MainFeatureInfo.do?method=saveGenInfoText";
	document.forms[0].submit();
}

//Added for CR_104 - Preserve User Markers
function fnTurnUserMarkerFlag(userMarkerFlag){
	
		document.forms[0].action="MainFeatureInfo.do?method=turnUserMarker&flag="+userMarkerFlag;
		document.forms[0].submit();
}
//CR_104 Ends here
//Added for CR_106 - customer and distributor logo on and off
function fnTurnCustomerLogoFlag(custLogoFlag){
	
		document.forms[0].action="MainFeatureInfo.do?method=turnLogoONOFF&custLogoFlag="+custLogoFlag;
		document.forms[0].submit();
}

function fnTurnDistributorLogoFlag(distriLogoFlag){
	
		document.forms[0].action="MainFeatureInfo.do?method=turnLogoONOFF&distriLogoFlag="+distriLogoFlag;
		document.forms[0].submit();
}
//Added for CR_106 Sales Supplement Changes
function fnGenerateProofReadingSalesSupplement(){


document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=SalesSupp";
document.forms[0].submit();
}
//CR_106 Ends here

//Added for CR_109 Marked Clause Changes
function fnGenerateProofReadingMarkedClauses(){

	document.forms[0].action="GenerateProofReadingDraftAction.do?method=createProofReadingPDF&orderKey="+document.forms[0].orderKey.value+
	                                "&revCode="+document.forms[0].hdnRevViewCode.value+"&pdfType=MarkedClauses";
	document.forms[0].submit();

}
//CR_109 Ends here

//Added for CR_113 to add dynamic Numbeing on and off.
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
		document.forms[0].action="MainFeatureInfo.do?method=turnDynamicNumOnOff&flag="+dynamicNoFlag;
		document.forms[0].submit();
		}
}

function fnDeletedClausesHistory()
{
	var orderkey=document.forms[0].orderKey.value;
	var secSeqNo=document.forms[0].orderSecSeqNo.value;

	document.forms[0].action="MainFeatureInfo.do?method=deletedClausesHistoy&secSeqNo="+secSeqNo;
	document.forms[0].submit();
	
}

function fnClauseswithIndicators()
{
	var orderkey=document.forms[0].orderKey.value;
	var secSeqNo=document.forms[0].orderSecSeqNo.value;

	document.forms[0].action="MainFeatureInfo.do?method=clausesWithIndicators&secSeqNo="+secSeqNo;
	document.forms[0].submit();
	
}

//Added for CR-113 QA- Fix ends here.

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

