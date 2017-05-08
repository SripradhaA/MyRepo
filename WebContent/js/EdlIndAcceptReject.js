function fnAcceptEdlChange(){
       
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
              
              document.forms[0].flag.value="Y"; 
              
              
              var del=confirm("Are you sure to accept the model Clause Edl change ?");
	          if(del == true){
	          var clauseSeqNo = document.forms[0].clauseSeqNo.value;
	          var orderKey = document.forms[0].orderKey.value;
	          var subsecseqno = document.forms[0].subSecSeqNo.value;
			  var secSeq = document.forms[0].secSeq.value;
			  var revCode = document.forms[0].revCode.value;
              document.forms[0].action="EdlIndAcceptRejectAction.do?method=updateClauseEdlChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&subsecno="+subsecseqno+"&secSeq="+secSeq+"&revCode="+revCode;
              document.forms[0].submit();
              }else{
              }
    
  }
  
  
  
  function fnRejectEdlChange(){
       
    
	    if(trim(document.forms[0].reason.value).length>2000){
	                    var id = '518';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("reason",-1);
						return false;
	      
	      
	      }

      document.forms[0].flag.value="N"; 
      
      
      var del=confirm("Are you sure to reject the model Clause Edl change ?");
      if(del == true){
      var clauseSeqNo = document.forms[0].clauseSeqNo.value;
      var orderKey = document.forms[0].orderKey.value;
      var subsecseqno = document.forms[0].subSecSeqNo.value;
	  var secSeq = document.forms[0].secSeq.value;
	  var revCode = document.forms[0].revCode.value;
      document.forms[0].action="EdlIndAcceptRejectAction.do?method=updateClauseEdlChange&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&subsecno="+subsecseqno+"&secSeq="+secSeq+"&revCode="+revCode;
      document.forms[0].submit();
      }else{
      }
    
  }
  
  function fnModifySpec(){
  
  var orderKey = document.forms[0].orderKey.value;
  var secSeq = document.forms[0].secSeq.value;
  var revCode = document.forms[0].revCode.value;
  var subsecseqno = document.forms[0].subSecSeqNo.value;
  document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
  document.forms[0].submit();
  }
