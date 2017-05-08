function fnAcceptClauseChange(){
       
    
	/** Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 **/ 
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
    
    if(trim(document.forms[0].reason.value).length>2000){
                    var id = '518';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("reason",-1);
					return false;
      
      
      }
	/* } */
    
       
              
              document.forms[0].flag.value="Y"; 
              /*var del=confirm("Are you sure to Accept the Model Default Clause");
	          if(del == true){*///commented for CR_100 removing the alerts
	          /*added for CR 73 by cm68219 starts*/
	          //Added for CR_114
	          var appendixFlag = false;
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
			    	//Modified for CR_114
			    	//alert("Appendix image mapping has been broken." +"\n"+ "Kindly re-map the clause from Appendix Image Screen.");
			    	
			    	//Updated for CR-118
			    	var appendixMapCheck = confirm("Appendix image mapping will be removed. " +
			    			"Do you want to re-map the appendix image to this new clause version?"
			    			+"\n"+"\n"+"Ok - Changes the version of the clause and re-maps the appendix image"
			    			+"\n"+"Cancel - Doesn't re-map the appendix image to the new version");
				    if(appendixMapCheck){
				    	document.forms[0].mapAppendixImg.value=1;
				    }
				    //Modified for CR_114 Ends Here
			    }
	          
	          
	          var clauseSeqNo = document.forms[0].clauseSeqNo.value;
	          var orderKey = document.forms[0].orderKey.value;
	          var subsecseqno = document.forms[0].subSecSeqNo.value;
              document.forms[0].action="CopyIndAcceptRejectClauseAction.do?method=updateClauseChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&subsecno="+subsecseqno;
              /*added for CR 73 by cm68219 ends*/
              document.forms[0].submit();
              /*}else{
              }*/
    
  }
  
  
  
  function fnRejectClauseChange(){
       
    /*if(trim(document.forms[0].reason.value).length==0 || trim(document.forms[0].reason.value)==""){
            var id= '129';
			hideErrors();
			addMsgNum(id);
			showErrors();
			document.forms[0].reason.value="";
            document.forms[0].reason.focus();
		    return false;
    
    }*/
    /** Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 **/

	/* if(document.forms[0].specStatusCode.value >= 3){ */
    if(trim(document.forms[0].reason.value).length>2000){
                    var id = '518';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("reason",-1);
					return false;
      
      
      }
    
	/* } */
              
              document.forms[0].flag.value="N"; 
              
              
                 /* var del=confirm("Are you sure to Reject the Model Default Clause");
	          if(del == true){*///commented for CR_100 removing alerts
	          /*added for CR 73 by cm68219 starts*/
	          var clauseSeqNo = document.forms[0].clauseSeqNo.value;
	          var orderKey = document.forms[0].orderKey.value;
	         var subsecseqno = document.forms[0].subSecSeqNo.value;
                 document.forms[0].action="CopyIndAcceptRejectClauseAction.do?method=updateClauseChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&subsecno="+subsecseqno;
              /*added for CR 73 by cm68219 ends*/
              document.forms[0].submit();
             /* }else{
              }*/
    
  }
  
  function fnModifySpec(){
  
  var orderKey = document.forms[0].orderKey.value;
  var secSeq = document.forms[0].secSeq.value;
  var revCode = document.forms[0].revCode.value;
  var subsecseqno = document.forms[0].subSecSeqNo.value;
  document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
  document.forms[0].submit();
  }
