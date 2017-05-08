function fnApplySelected(){
    var orderKey = document.forms[0].orderKey.value;
  	var secSeq = document.forms[0].secSeq.value;
   	var revCode = document.forms[0].revCode.value;
  
   	
  	 var subsecseqno = document.forms[0].subsecseqno.value;
  	var clauseSeqNo=document.forms[0].clauseSeqNo.value;
  	var selectFlag=false;
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
    }/*else{*/
    
      /* var isSpecChar = SpecialCharacterCheck(document.forms[0].reason.value);
       
       if(isSpecChar){
                                    var id = '129';
                                    hideErrors();
                                    addMsgNum(id);
                                    showErrors();
									document.forms[0].reason.value="";
                                    document.forms[0].reason.focus();
                                    return false;

                      }*/
                      if(trim(document.forms[0].reason.value).length>2000){
                    var id = '518';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("reason",-1);
					return false;
      
      
      }
                      
     /*  }*/
   /* } */
     if(document.forms[0].defaultFlag.length > 0){
        var cnt=document.forms[0].defaultFlag.length;
        for(var i=0;i<cnt;i++){
        if(document.forms[0].defaultFlag[i].checked){
         document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
         //Added for CR-74
         selectFlag=true;
          break;
         
         }
        }
        }else{
            if(document.forms[0].defaultFlag.checked){
              document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
              //Added for CR-74
              selectFlag=true;
               }
              }
              
             if(!selectFlag){
                 var id = '520';
				 hideErrors();
				 addMsgNum(id);
				 showScrollErrors("defaultFlag",-1);
				 return false;
             } 
			 var applySel=confirm("Are you sure to Apply the selected version");
	         if(applySel == true){              
              document.forms[0].flag.value="Y"; 
              
              
              var orderKey = document.forms[0].orderKey.value;
              var clauseSeqNo=document.forms[0].clauseSeqNo.value;
			  //Added for CR-109
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
			  //Added for CR-109 Ends here  
             // document.forms[0].action="OrderSelClauseRevAction.do?method=updateClauseChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"&mapAppendixImg="+mapAppendixImg+"&appendixImgSeqNo="+appendixImgSeqNo;
             document.forms[0].action="OrderSelClauseRevAction.do?method=updateClauseChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
             
              document.forms[0].submit();}
			  else{}
    
  }
  
  function fnModifySpec(){
  //var orderno = document.forms[0].orderNo.value;
  //var modseqno = document.forms[0].modelSeqNo.value;
  var orderKey = document.forms[0].orderKey.value;
 //var clauseSeqNo=document.forms[0].clauseSeqNo.value;
  var secSeq = document.forms[0].secSeq.value;
  //var secCode = document.forms[0].secCode.value;
 // var secName = document.forms[0].secName.value;
  var revCode = document.forms[0].revCode.value;
  //var subSecName = document.forms[0].subSecName.value;
  //var subSecCode = document.forms[0].subSecCode.value;
  //var subSecSeqNo = document.forms[0].subsecseqno.value;
  //document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&clauseSeq="+clauseSeqNo+"&secSeq="+secSeq+"&secCode="+secCode+"&secName="+secName+"&revCode="+revCode+"&subSecName="+subSecName+"&subSecCode="+subSecCode+"&subsecseqno="+subSecSeqNo+"&modseqno="+modseqno+"&orderno="+orderno;
  //document.forms[0].submit();
  //Added for landing to the subsection
  var subsecseqno = document.forms[0].subsecseqno.value;
  var clauseSeqNo=document.forms[0].clauseSeqNo.value;

  document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"&clauseSeq="+clauseSeqNo;
  document.forms[0].submit();
  }
  
  function fnSelectClauseRevSpec()
  {
  //var orderno = document.forms[0].orderNo.value;
  //var modseqno = document.forms[0].modelSeqNo.value;
  var orderKey = document.forms[0].orderKey.value;
  //var clauseSeqNo=document.forms[0].clauseSeqNo.value;
  var secSeq = document.forms[0].secSeq.value;
  //var secCode = document.forms[0].secCode.value;
  //var secName = document.forms[0].secName.value;
  var revCode = document.forms[0].revCode.value;
  //var subSecName = document.forms[0].subSecName.value;
  //var subSecCode = document.forms[0].subSecCode.value;
  //var subSecSeqNo = document.forms[0].subsecseqno.value;
  document.forms[0].action="OrderSelClauseRevAction.do?method=fetchClauseVersions&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode;
  document.forms[0].submit();
  
  }
  
  function fnAddClauseRev(){
  //orderno = document.forms[0].orderNo.value;
  var custseqno = document.forms[0].customerSeqNo.value;
  var orderKey = document.forms[0].orderKey.value;
  //var clauseSeqNo=document.forms[0].clauseSeqNo.value;
  var secSeq = document.forms[0].secSeq.value;
  //var secCode = document.forms[0].secCode.value;
  //var secName = document.forms[0].secName.value;
  var revCode = document.forms[0].revCode.value;
  //var subSecName = document.forms[0].subSecName.value;
  //var subSecCode = document.forms[0].subSecCode.value;
  //var subSecSeqNo = document.forms[0].subsecseqno.value;
  document.forms[0].action="AddClauseRev.do?method=fetchClauses&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&custSeqNo="+custseqno;
  document.forms[0].submit();
  }