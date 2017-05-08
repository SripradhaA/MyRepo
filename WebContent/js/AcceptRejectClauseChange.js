function fnAcceptClauseChange(){
       
    if(document.forms[0].reason.length==0 || document.forms[0].reason.value==""){
            var id= '129';
			hideErrors();
			addMsgNum(id);
			showErrors();
			document.forms[0].reason.value="";
            document.forms[0].reason.focus();
		    return false;
    
    }else{
    
       var isSpecChar = SpecialCharacterCheck(document.forms[0].reason.value);
       
       if(isSpecChar){
                                    var id = '129';
                                    hideErrors();
                                    addMsgNum(id);
                                    showErrors();
									document.forms[0].reason.value="";
                                    document.forms[0].reason.focus();
                                    return false;

                      }
                      
         }
    
     if(document.forms[0].defaultFlag.length > 0){
        var cnt=document.forms[0].defaultFlag.length;
        for(var i=0;i<cnt;i++){
        if(document.forms[0].defaultFlag[i].checked){
         document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
          break;
         
         }
        }
        }else{
            if(document.forms[0].defaultFlag.checked){
              document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
               }
              }   
              
              document.forms[0].flag.value="Y"; 
              
              
              
              document.forms[0].action="AcceptRejectClauseAction.do?method=updateClauseChange";
              document.forms[0].submit();
    
  }
  
  
  
  function fnRejectClauseChange(){
       
    if(document.forms[0].reason.length==0 || document.forms[0].reason.value==""){
            var id= '129';
			hideErrors();
			addMsgNum(id);
			showErrors();
			document.forms[0].reason.value="";
            document.forms[0].reason.focus();
		    return false;
    
    }else{
    
       var isSpecChar = SpecialCharacterCheck(document.forms[0].reason.value);
       
       if(isSpecChar){
                                    var id = '129';
                                    hideErrors();
                                    addMsgNum(id);
                                    showErrors();
									document.forms[0].reason.value="";
                                    document.forms[0].reason.focus();
                                    return false;

                      }
                      
         }
    
     
              
              document.forms[0].flag.value="N"; 
              
              
              
              document.forms[0].action="AcceptRejectClauseAction.do?method=updateClauseChange";
              document.forms[0].submit();
    
  }
