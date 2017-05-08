function fnAcceptClauseChange(counter,clauseSeqNo){

		if(document.forms[0].coreClaReason.length>0)	
		    	document.forms[0].reason.value=document.forms[0].coreClaReason[counter].value;
		    else
		    	document.forms[0].reason.value=document.forms[0].coreClaReason.value;
		    	
		if(document.forms[0].specStatusCode.value >=3)
		{ 
		
		if(document.forms[0].reason.length==0 || document.forms[0].reason.value==""){
		var id= '129';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("coreClaReason",counter);
		if(document.forms[0].coreClaReason.length>0)	{
			document.forms[0].coreClaReason[counter].value="";
			document.forms[0].coreClaReason[counter].focus();
			}
			else{
			document.forms[0].coreClaReason.value="";
			document.forms[0].coreClaReason.focus();
			}
		return false;
		
		}
		}
		
		if(trim(document.forms[0].reason.value).length>2000){
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("coreClaReason",counter);
		return false;
		}
		
		var claReasonArray = [];
		var claSeqArray = [];
		
		if(document.forms[0].coreClaReason.length>0)	{			    	
	    	for (i=0;i<document.forms[0].coreClaReason.length;i++){  	
	    		claSeqArray[i] = document.forms[0].coreClaSeqNo[i].value;
	    		claReasonArray[i] =	document.forms[0].coreClaReason[i].value;
			}
		}
			
		document.forms[0].claReasonArray.value = claReasonArray;
		document.forms[0].claSeqArray.value = claSeqArray;
		
		var accept=confirm("Are you sure you want to accept the selected New Clause and it's SubClauses(if any) ?");
		if(accept == true)
		{
		var flag=document.forms[0].flag.value="Y";
		var orderKey = document.forms[0].orderKey.value;
		/*added for CR 73 by cm68219 starts*/
		var subsecseqno = document.forms[0].subSecSeqNo.value;
		document.forms[0].action="AccRejNewClauseAction.do?method=updateNewClauseChange&subsecno="+subsecseqno+"&clauseSeqNo="+clauseSeqNo+"&flag="+flag;
		/*added for CR 73 by cm68219 ends*/
		document.forms[0].submit();
		}
		
		else{
		
		}

}



function fnRejectClauseChange(counter,clauseSeqNo){


	/**
	* Reason TextArea in AcceptrejectNewClause screen is visible only for 
	* the spec status opening and above
	* Added on 05-June-08
	* by ps57222  
	*/
	/** Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 **/ 
	
	if(document.forms[0].coreClaReason.length>0)	
	    	document.forms[0].reason.value=document.forms[0].coreClaReason[counter].value;
	    else
	    	document.forms[0].reason.value=document.forms[0].coreClaReason.value;
	
	if(document.forms[0].reason.length>2000){
	var id = '518';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("coreClaReason",counter);
	return false;
	}
	
	/* } */
	
		var claReasonArray = [];
		var claSeqArray = [];
		
		if(document.forms[0].coreClaReason.length>0)	{			    	
	    	for (i=0;i<document.forms[0].coreClaReason.length;i++){  	
	    		claSeqArray[i] = document.forms[0].coreClaSeqNo[i].value;
	    		claReasonArray[i] =	document.forms[0].coreClaReason[i].value;
			}
		}
			
		document.forms[0].claReasonArray.value = claReasonArray;
		document.forms[0].claSeqArray.value = claSeqArray;		
	
	var reject=confirm("Are you sure you want to reject the selected New Clause and it's SubClauses(if any) ?");
	if(reject == true)
	{
	var flag=document.forms[0].flag.value="N";
	var orderKey = document.forms[0].orderKey.value;
	var secSeq = document.forms[0].secSeq.value;
	/*added for CR 73 by cm68219 starts*/
	var subsecseqno = document.forms[0].subSecSeqNo.value;
	document.forms[0].action="AccRejNewClauseAction.do?method=updateNewClauseChange&subsecno="+subsecseqno+"&clauseSeqNo="+clauseSeqNo+"&flag="+flag;
	/*added for CR 73 by cm68219 ends*/
	document.forms[0].submit();
	}
	else{
	
	}

}


//CR_90




function fnAcknowledgeAllClause(){

	var clauseSeqNo='0';
	if(document.forms[0].clauseSeqNo.length>0)	{
	
	for(var i = 0 ; i < document.forms[0].clauseSeqNo.length ; i++){
		clauseSeqNo = document.forms[0].clauseSeqNo[1].value;
	}
	
	}
	else
		clauseSeqNo = document.forms[0].clauseSeqNo.value;
	
	var reject=confirm("Are you sure you want to Acknowledge all the New Clause(s) tied to Component ?");
	if(reject == true)
	{
		var flag=document.forms[0].flag.value="N";
		var orderKey = document.forms[0].orderKey.value;
		var secSeq = document.forms[0].secSeq.value;
		var subsecseqno = document.forms[0].subSecSeqNo.value;
		document.forms[0].action="AccRejNewClauseAction.do?method=updateNewClauseChange&subsecno="+subsecseqno+"&clauseSeqNo="+clauseSeqNo+"&flag="+flag;
		document.forms[0].submit();
	}

}

function fnModifySpec(){

var orderKey = document.forms[0].orderKey.value;
var secSeq = document.forms[0].secSeq.value;
var subsecseqno = document.forms[0].subSecSeqNo.value;


var revCode = document.forms[0].revCode.value;
document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
document.forms[0].submit();
}

