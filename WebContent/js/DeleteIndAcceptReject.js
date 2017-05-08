function fnModifySpec(){
  
	var orderKey = document.forms[0].orderKey.value;
	var secSeq = document.forms[0].secSeq.value;
	var revCode = document.forms[0].revCode.value;
	var subsecseqno = document.forms[0].subSecSeqNo.value;
	document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
	document.forms[0].submit();
  }


  function fnAcceptDeleteClause(){

	//Change for LSDB_CR-74
	//Modified for CR_79 removing validation before Opening Status on 28-Oct-09 by RR68151
	if(document.forms[0].specStatusCode.value >= 3){
	if(trim(document.forms[0].reason.value).length==0 || trim(document.forms[0].reason.value)==""){

		var id= '129';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;

	}
	}

	if(trim(document.forms[0].reason.value).length > 2000){
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}

	var accept=confirm("Are you sure to accept Clause deletion at Order level ?");
	if(accept == true)
	{
		/*added for CR 73 by cm68219 starts*/
		var orderKey = document.forms[0].orderKey.value;
		var secSeq = document.forms[0].secSeq.value;
		var revCode = document.forms[0].revCode.value;
		var subsecseqno = document.forms[0].subSecSeqNo.value;
		var clauseSeqNo=document.forms[0].clauseSeqNo.value;
		/*added for CR 73 by cm68219 ends*/

		document.forms[0].flag.value="Y";
		/*added for CR 73 by cm68219 starts*/
		document.forms[0].action="DeleteIndAcceptRejectClauseAction.do?method=updateDeleteClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"&clauseSeq="+clauseSeqNo;
		/*added for CR 73 by cm68219 ends*/
		document.forms[0].submit();

	}else{
		}
	}


  function fnRejectDeleteClause(){

	//Modified for CR_79 removing validation before Opening Status on 28-Oct-09 by RR68151

	if(trim(document.forms[0].reason.value).length > 2000){
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}
	
	    var accept=confirm("Are you sure to reject Clause deletion at Order level ?");
		if(accept == true)
		{
			/*added for CR 73 by cm68219 starts*/
			var orderKey = document.forms[0].orderKey.value;
			var secSeq = document.forms[0].secSeq.value;
			var revCode = document.forms[0].revCode.value;
			var subsecseqno = document.forms[0].subSecSeqNo.value;
			var clauseSeqNo=document.forms[0].clauseSeqNo.value;
			/*added for CR 73 by cm68219 ends*/
			document.forms[0].flag.value="N";
			/*added for CR 73 by cm68219 starts*/
			document.forms[0].action="DeleteIndAcceptRejectClauseAction.do?method=updateDeleteClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"&clauseSeq="+clauseSeqNo;
			/*added for CR 73 by cm68219 ends*/
			document.forms[0].submit();

		}else{
		 }
		}
//Added for CR-134 starts here		
function fnAcceptApplyModDefClause(){

	if(document.forms[0].specStatusCode.value >= 3){
	if(trim(document.forms[0].reason.value).length==0 || trim(document.forms[0].reason.value)==""){

		var id= '129';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;

	}
	}

	if(trim(document.forms[0].reason.value).length > 2000){
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("reason",-1);
		return false;
	}

	var accept=confirm("Are you sure to accept Clause deletion at Order level and Apply the Model default Clause Version ?");
	if(accept == true)
	{
		var orderKey = document.forms[0].orderKey.value;
		var secSeq = document.forms[0].secSeq.value;
		var revCode = document.forms[0].revCode.value;
		var subsecseqno = document.forms[0].subSecSeqNo.value;
		var clauseSeqNo=document.forms[0].clauseSeqNo.value;
		/*var appendixFlag = false;
			  if(document.forms[0].appendixExitsFlag.length > 0){
		        	var cnt=document.forms[0].appendixExitsFlag.length;
		        	for(var i=0;i<cnt;i++){
				        if(document.forms[0].appendixExitsFlag[i].value == "Y"){
				          appendixFlag=true;
				          break;		         
				        }
		        	}
		        }else{
		            if(document.forms[0].appendixExitsFlag.value == "Y")
		              	appendixFlag=true;
		        }
			    if(appendixFlag){
			    	var appendixMapCheck = confirm("Appendix image mapping will be removed. " +
			    			"Do you want to re-map the appendix image to this new clause version?"
			    			+"\n"+"\n"+"Ok - Changes the version of the clause and re-maps the appendix image"
			    			+"\n"+"Cancel - Doesn't re-map the appendix image to the new version");
				    if(appendixMapCheck){
				    	document.forms[0].mapAppendixImg.value=1;
				    }
				}*/
		document.forms[0].flag.value="Y";
		document.forms[0].action="ModelIndAcceptRejectClauseAction.do?method=updateClauseChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&subsecno="+subsecseqno;
		document.forms[0].submit();

	}else{
		}
	}
//Added for CR-134 ends here