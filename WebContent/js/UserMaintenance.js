
function fnDeleteUser(){

	    var UserForm=document.forms[0];
	    user=false;
	    if(UserForm.userSeqNo.length > 0 ){
		      var cnt = UserForm.userSeqNo.length;
			  for(var i=0;i<cnt;i++){
				   if(UserForm.userSeqNo[i].checked){
						user = true;
		           		break;
				   }
			  }
		}
		else{
				if(UserForm.userSeqNo.checked){
					user = true;
				}
		     }if (!user)
		      {
					var id= '152';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("userSeqNo",-1);					
					return false;
		      }
	   
	   
	   
	     var del=confirm("Are you sure to delete the user");
		if(del == true){
	   
	   
	  if(UserForm.userSeqNo.length > 0){
	     var cnt = UserForm.userSeqNo.length;
	       for(var i=0;i<cnt;i++){
	                    if(UserForm.userSeqNo[i].checked){
				        UserForm.hdnUserID.value=UserForm.userrId[i].value;
	 					
	 					break;
				   }
			 }
			} else{
			 if(UserForm.userSeqNo.checked){
			   
	 					UserForm.hdnUserID.value=UserForm.userrId.value;
	 					
	 					}
		}
		
		document.forms[0].action="UserMaintenanceAction.do?method=deleteUser";
	    document.forms[0].submit(); 
	    
	    }else{
	    
	    }
	      
	
	   }
	   
	 function fnAddUser(){
	   
	   var UserForm=document.forms[0];
	   
	  if(trim(UserForm.userId.value).length==0 || trim(UserForm.userId.value)=="" ){
						var id = '138';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("userId",-1);	
						return false;
					}
	  var isuser = SpecialCharacterCheck(trim(UserForm.userId.value));
	if(isuser){
	
	                    var id = '138';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("userId",-1);	
	                    return false;
	
	                  }
	                  
	    if(trim(UserForm.firstName.value).length==0 || trim(UserForm.firstName.value)=="" ){
						var id = '140';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("firstName",-1);	
						return false;
					}
	   var isfirstname = SpecialCharacterCheck(trim(UserForm.firstName.value));
	if(isfirstname){
	
	                    var id = '140';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("firstName",-1);	
	                    return false;
	
	                  }
	                  
	     var ifFirstname=isName(trim(UserForm.firstName.value));
	     if(!ifFirstname){
	        
	                    var id = '140';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("firstName",-1);	
	                    return false;
	     
	     
	     }
	     
	     
	   if(trim(UserForm.lastName.value).length==0 || trim(UserForm.lastName.value)=="" ){
						var id = '141';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("lastName",-1);	
						return false;
					}
		var islastname = SpecialCharacterCheck(trim(UserForm.lastName.value));
	if(islastname){
	
	                    var id = '141';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lastName",-1);
		                return false;
	
	                  }
	                  
	    var ifLastname=isName(trim(UserForm.lastName.value));
	     if(!ifLastname){
	        
	                    var id = '141';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lastName",-1);
	                    return false;
	     
	     
	     }
	    if(trim(UserForm.password.value).length==0 || trim(UserForm.password.value)=="" ){
						var id = '142';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("password",-1);
						UserForm.password.value="";
						return false;
					}
		if(trim(UserForm.password.value).length < 8 || trim(UserForm.password.value).length > 12){
		                var id = '225';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("password",-1);
						return false;
		}
	  if(trim(UserForm.emailId.value).length==0 || trim(UserForm.emailId.value)=="" ){
						var id = '143';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("emailId",-1);
						UserForm.emailId.value="";
						return false;
					}
		      
		var isEmail=validateEmail(trim(document.forms[0].emailId.value));
	     
	     if(isEmail){
	                    var id = '143';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("emailId",-1);
	                    return false;
	                  }
	  
	                  
		if(trim(UserForm.locationName.value).length==0 || trim(UserForm.locationName.value)=="" ){
						var id = '144';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("locationName",-1);
						UserForm.locationName.value="";
						return false;
					}	
				
		if(!(isNaN(trim(UserForm.locationName.value)))){
	  					var id = '144';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("locationName",-1);
						return false;
			}  	    
	    
		if(UserForm.contactNo.value.length==0 || trim(UserForm.contactNo.value)=="" ){
						var id = '145';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("contactNo",-1);
						return false;
					}
					
		
	  if (UserForm.roleid.options[UserForm.roleid.selectedIndex].value =="-1")
	   {
		 
			var id = '146';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltRoleID",-1);
			return false;
	   
			}   
	     
	      document.forms[0].action="UserMaintenanceAction.do?method=insertUser";
	      document.forms[0].submit(); 
	      } 
	      
	 function fnModifyUser(){
	       
	  UserForm=document.forms[0];
	        user=false;
	        var index;
	        if(UserForm.userSeqNo.length > 0 ){
		      var cnt = UserForm.userSeqNo.length;
			  for(var i=0;i<cnt;i++){
				   if(UserForm.userSeqNo[i].checked){
						user = true;
						index=i;
		           		break;
				   }
			  }
		}
		else{
				if(UserForm.userSeqNo.checked){
					user = true;
				}
		     }if (!user)
		      {
					var id= '152';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("userSeqNo",-1);
					return false;
		      }
	          
	         if(UserForm.userSeqNo.length > 0 ){
	          
	           if(trim(UserForm.firsttName[index].value)=="" || trim(UserForm.firsttName[index].value).length==0){
	           
	                    var id = '140';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("firsttName",index);
						return false;
	         }
	         var isfirstname = SpecialCharacterCheck(trim(UserForm.firsttName[index].value));
	         if(isfirstname){
	
				          var id = '140';
				          hideErrors();
				          addMsgNum(id);
				          showScrollErrors("firsttName",index);
				          return false;
	           }
	          
	          var ifFirstname=isName(trim(UserForm.firsttName[index].value));
	          if(!ifFirstname){
	        
	                    var id = '140';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("firsttName",index);
	                    return false;
	            }
	          
	         if(trim(UserForm.lasttName[index].value)=="" || trim(UserForm.lasttName[index].value).length==0){
	           
	                    var id = '141';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("lasttName",index);
						return false;
	         }
	         var islastname = SpecialCharacterCheck(trim(UserForm.lasttName[index].value));
	         if(islastname){
	
	                    var id = '141';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lasttName",index);
	                    return false;
	
	                  }
	         var ifLastname=isName(trim(UserForm.lasttName[index].value));
	          if(!ifLastname){
	        
	                    var id = '141';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lasttName",index);
		                return false;
	            }
	         if(trim(UserForm.location[index].value)=="" || trim(UserForm.location[index].value).length==0){           
	                    var id = '144';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("location",index);
						return false;
	         }           
	         if(!(isNaN(trim(UserForm.location[index].value)))){
	           
	                    var id = '144';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("location",index);
						return false;
	         }         
	         if(trim(UserForm.emaillId[index].value).length==0 || trim(UserForm.emaillId[index].value)=="" ){
						var id = '143';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("emaillId",index);
						return false;
					}
			 var isEmail=validateEmail(trim(UserForm.emaillId[index].value));
	     
	     if(isEmail){
	                    var id = '143';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("emaillId",index);
	                    return false;
	                  }
	         if(trim(UserForm.contacttNo[index].value)=="" || trim(UserForm.contacttNo[index].value).length==0){
	           
	 		          
	                    var id = '145';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("contacttNo",index);
						return false;
	         } 
	             
	         
	       if (UserForm.role[index].options[UserForm.role[index].selectedIndex].value =="-1")
	       {
		 
			var id = '146';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("role",index);
			return false;
	   
		   }
	     
	       }
	       
	       else{
	      	index=-1;
	       if(trim(UserForm.firsttName.value).length==0 || trim(UserForm.firsttName.value)==""){
	                    var id = '140';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("firsttName",index);
						return false;
	         }
	        var isfirstname = SpecialCharacterCheck(trim(UserForm.firsttName.value));
	         if(isfirstname){
	
				          var id = '140';
				          hideErrors();
				          addMsgNum(id);
				          showScrollErrors("firsttName",index);
				          return false;
	           }
	           
	          var ifFirstname=isName(trim(UserForm.firsttName.value));
	          if(!ifFirstname){
	        
	                    var id = '140';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("firsttName",index);
		                return false;
	            }
	         
	         if(trim(UserForm.lasttName.value)=="" || trim(UserForm.lasttName.value).length==0){
	           
	                    var id = '141';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("lasttName",index);
						return false;
	         }
	         var islastname = SpecialCharacterCheck(trim(UserForm.lasttName.value));
	         if(islastname){
	
	                    var id = '141';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lasttName",index);
		                return false;
	
	                  }
	         var ifLastname=isName(trim(UserForm.lasttName.value));
	          if(!ifLastname){
	        
	                    var id = '140';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("lasttName",index);
		                return false;
	            }
	         if(trim(UserForm.location.value)=="" || trim(UserForm.location.value).length==0){
	           
	                    var id = '144';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("location",index);
						return false;
	         }      
	         
	         if(trim(UserForm.emaillId.value).length==0 || trim(UserForm.emaillId.value)=="" ){
						var id = '143';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("emaillId",index);
						return false;
					}
			 var isEmail=validateEmail(trim(UserForm.emaillId.value));
	     
	     if(isEmail){
	                    var id = '143';
	                    hideErrors();
	                    addMsgNum(id);
	                    showScrollErrors("emaillId",index);
	                    return false;
	                  }
	         if(trim(UserForm.contacttNo.value)=="" || trim(UserForm.contacttNo.value).length==0){
	           
	                    var id = '145';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("contacttNo",index);
						return false;
	         }  
	        
	       if (UserForm.role.options[UserForm.role.selectedIndex].value =="-1")
	       {
		 
			var id = '146';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("role",index);
			return false;
	   
		   } 
	       
	       }
	 
		  if(UserForm.userSeqNo.length > 0){
	     var cnt = UserForm.userSeqNo.length;
	       for(var i=0;i<cnt;i++){
	                    if(UserForm.userSeqNo[i].checked){
				        UserForm.hdnUserID.value=UserForm.userrId[i].value;
				        UserForm.hdnFirstName.value=UserForm.firsttName[i].value;
				        UserForm.hdnLastName.value=UserForm.lasttName[i].value;
				        UserForm.hdnLocation.value=UserForm.location[i].value;
				        UserForm.hdnEmail.value=UserForm.emaillId[i].value;
				        UserForm.hdnContactNo.value=UserForm.contacttNo[i].value;
				        UserForm.hdnUserRole.value=UserForm.role[i].value;
				        break;
				   }
			 }
			} else{
			 if(UserForm.userSeqNo.checked){
			   
	 					UserForm.hdnUserID.value=UserForm.userrId.value;
				        UserForm.hdnFirstName.value=UserForm.firsttName.value;
				        UserForm.hdnLastName.value=UserForm.lasttName.value;
				        UserForm.hdnLocation.value=UserForm.location.value;
				        UserForm.hdnEmail.value=UserForm.emaillId.value;
				        UserForm.hdnContactNo.value=UserForm.contacttNo.value;
				        UserForm.hdnUserRole.value=UserForm.role.value;
	 					
	 					
				        
			}
			}
		document.forms[0].action="UserMaintenanceAction.do?method=updateUser";
	    document.forms[0].submit(); 
	      
	 
	 
	 
	 }
	 
	 function validateEmail(fieldVal){
	            email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	            if(!email.test(fieldVal)){
	              return true;
	              }
	             }
	             
	             
	
	function isName(val)
{
   
if (val.match(/^[a-zA-Z\s]+$/))
{
return true;
}
else
{
return false;
} 
}


//Added for CR-112 for Sorting 

function fnSortUserId(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 4){
		document.forms[0].orderbyFlag.value=5;
		}else if(sortKey == 5){
		document.forms[0].orderbyFlag.value=4;
		}else{
	    document.forms[0].orderbyFlag.value=4;
		}
		document.forms[0].columnheader.value="UserId";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

function fnSortFirstName(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
				if(sortKey == 0){
		document.forms[0].orderbyFlag.value=3;
		}else if(sortKey == 3){
		document.forms[0].orderbyFlag.value=0;
		}else{
	    document.forms[0].orderbyFlag.value=0;
		}
		document.forms[0].columnheader.value="FirstName";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

function fnSortLastName(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 1){
		document.forms[0].orderbyFlag.value=2;
		}else if(sortKey == 2){
		document.forms[0].orderbyFlag.value=1;
		}else{
	    document.forms[0].orderbyFlag.value=1;
		}
		document.forms[0].columnheader.value="LastName";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

function fnSortLocation(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 6){
		document.forms[0].orderbyFlag.value=7;
		}else if(sortKey == 7){
		document.forms[0].orderbyFlag.value=6;
		}else{
	    document.forms[0].orderbyFlag.value=6;
		}
		document.forms[0].columnheader.value="Location";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

function fnSortUserRole(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 8){
		document.forms[0].orderbyFlag.value=9;
		}else if(sortKey == 9){
		document.forms[0].orderbyFlag.value=8;
		}else{
	    document.forms[0].orderbyFlag.value=8;
		}
		document.forms[0].columnheader.value="UserRole";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

function fnSortLastLoggedIn(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 10){
		document.forms[0].orderbyFlag.value=11;
		}else if(sortKey == 11){
		document.forms[0].orderbyFlag.value=10;
		}else{
	    document.forms[0].orderbyFlag.value=10;
		}
		document.forms[0].columnheader.value="LastLoggedIn";
		document.forms[0].action="UserMaintenanceAction.do?method=fetchUsers";
		document.forms[0].submit();
}

//CR-112 for Sorting Ends Here
//Added for CR-113 for Broadcast Email
function fnbroadcastEmail(){
          var UserForm=document.forms[0];
          var i;
			var cnt=0;
			var check=false;
			if(document.forms[0].userID.length>1){
			
			   for(i=0;i<document.forms[0].userID.length;i++){
			     
				 if(document.forms[0].userID[i].checked){
			            check=true;
			      }
			   }
			
			}else{
			      if(document.forms[0].userID.checked){
			     check=true;
				  }
			}
			if(!check){
			var id='938';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("userID",-1);
			return false;
			}
	      if(trim(UserForm.subject.value).length==0 || trim(UserForm.subject.value)=="" ){
						var id = '936';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("subject",-1);
						UserForm.subject.value="";
						return false;
					}
		 
	       if($("#clauseDesc_id").val() == "" ){
						var id = '937';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("clauseDesc_id_ifr",-1);//Updated for broadcast email issue
						
						return false;
					}
					
			/*if(trim(UserForm.body.value).length>4000){
						var id = '939';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("clauseDesc_id_ifr",-1);//Updated for broadcast email issue
						
						return false;
					}*/
							
		 	document.forms[0].action="UserMaintenanceAction.do?method=broadcastEmail";
			document.forms[0].submit();
}

function msgLimit(field, maxlen) {
if (field.value.length > maxlen + 1)
if (field.value.length > maxlen)
field.value  = field.value.substring(0, maxlen);
}
//Added for CR-113 for Broadcast Email Ends Here

//Added for CR-124 Starts 

function fnpurgeEmail(){
	var UserForm=document.forms[0];
	var i;
			var cnt=0;
			var check=false;
			
			if(document.forms[0].emailID.length>1){
			
			   for(i=0;i<document.forms[0].emailID.length;i++){
			     
				 if(document.forms[0].emailID[i].checked){
			            check=true;
			      }
			   }
			
			}else{
			      if(document.forms[0].emailID.checked){
			     check=true;
				  }
			}
			if(!check){
			var id='1028';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("emailID",-1);
			return false;
			}
			
			bootbox.confirm("Are you sure to purge selected emails?", function(result) {
				  if (result)	{
					  document.forms[0].action="UserMaintenanceAction.do?method=purgeEmail";
					  document.forms[0].submit();
				  }
				}); 
			
}

			$(function() {
   				$("#chk").click(function() {

	        		$('.emailIDChkbox').prop("checked", true);
	        		$('.emailIDChkbox').closest('tr').addClass("selected");
	        		return false;
    			});
			});

			$(function() {
   				 $("#unchk").click(function() {

	        		$('.emailIDChkbox').prop("checked", false);
	       			$('.emailIDChkbox').closest('tr').removeClass("selected");
	        		return false;
   				});
});
//Added for CR-124 Ends

//Added for CR-126
 function checkAllAction()
		{
			var action = document.forms[0].actionNoticeFlag;
			for(var i = 0; i < action.length; i++){
			action[i].checked = true;
			}
		}
function clearAllAction()
		{
			var action = document.forms[0].actionNoticeFlag;

			 for(var i = 0; i < action.length; i++){

				if(action[i].checked)
					action[i].checked = false;

			 }
		 }		

function checkAllInformation()
		{
			var information = document.forms[0].informationNoticeFlag;

			for(var i = 0; i < information.length; i++){

			information[i].checked = true;

			}
		}
function clearAllInformation()
		{
			var information = document.forms[0].informationNoticeFlag;

			 for(var i = 0; i < information.length; i++){

				if(information[i].checked)
					information[i].checked = false;

			 }
		 }
	

		
 function checkAllCCNotice()
		{
			var ccNotice = document.forms[0].ccNoticeFlag;

			for(var i = 0; i < ccNotice.length; i++){

			ccNotice[i].checked = true;

			}
		}

function clearAllCCNotice()
		{
			var ccNotice = document.forms[0].ccNoticeFlag;

			 for(var i = 0; i < ccNotice.length; i++){

				if(ccNotice[i].checked)
					ccNotice[i].checked = false;

			 }
 }
 
function fnSave(){
          var UserForm=document.forms[0];
          var nActionNotice,actionNoticeFlag="";
          var nInformationNotice,informationNoticeFlag="";
          var nCCNotice,ccNoticeFlag="";
		  if(document.forms[0].actionNoticeFlag.length>1){
			   for(nActionNotice=0;nActionNotice<document.forms[0].actionNoticeFlag.length;nActionNotice++){
			     
				 if(document.forms[0].actionNoticeFlag[nActionNotice].checked){
			    	   document.forms[0].actionNoticeFlag.value="Y";
			           if(actionNoticeFlag ==""){
							actionNoticeFlag="Y";			           	
			           }else{
							actionNoticeFlag=actionNoticeFlag+","+"Y";			           	
			           } 
			      }else{
			      	   document.forms[0].actionNoticeFlag.value="N";
			      	   if(actionNoticeFlag ==""){
							actionNoticeFlag="N";			           	
			           }else{
							actionNoticeFlag=actionNoticeFlag+","+"N";			           	
			           } 
			      }
			}
		  }	

		document.forms[0].hdnActionNoticeFlag.value = trim(actionNoticeFlag);	
		
		
		if(document.forms[0].informationNoticeFlag.length>1){
			
			   for(nInformationNotice=0;nInformationNotice<document.forms[0].informationNoticeFlag.length;nInformationNotice++){
			     
				 if(document.forms[0].informationNoticeFlag[nInformationNotice].checked){
				 	   document.forms[0].informationNoticeFlag.value="Y";
			           if(informationNoticeFlag ==""){
							informationNoticeFlag="Y";			           	
			           }else{
							informationNoticeFlag=informationNoticeFlag+","+"Y";			           	
			           }
			      }else{
				       document.forms[0].informationNoticeFlag.value="N";
			      	   if(informationNoticeFlag ==""){
							informationNoticeFlag="N";			           	
			           }else{
							informationNoticeFlag=informationNoticeFlag+","+"N";			           	
			           }
			      }	
			   }
			
			}
		
		document.forms[0].hdnInformationNoticeFlag.value = trim(informationNoticeFlag);			
		
	    if(document.forms[0].ccNoticeFlag.length>1){
			
			   for(nCCNotice=0;nCCNotice<document.forms[0].ccNoticeFlag.length;nCCNotice++){
			     
				 if(document.forms[0].ccNoticeFlag[nCCNotice].checked){
			           document.forms[0].ccNoticeFlag.value="Y";
			           if(ccNoticeFlag ==""){
							ccNoticeFlag="Y";			           	
			           }else{
							ccNoticeFlag=ccNoticeFlag+","+"Y";			           	
			           }
			      }else{
			      	   document.forms[0].ccNoticeFlag.value="N";
	      	           if(ccNoticeFlag ==""){
							ccNoticeFlag="N";			           	
			           }else{
							ccNoticeFlag=ccNoticeFlag+","+"N";			           	
			           }
			      }	
			   }
			
		}
			
		document.forms[0].hdnCCNoticeFlag.value = trim(ccNoticeFlag);		
		
		/*alert("actionNoticeFlag:"+actionNoticeFlag);
		alert("informationNoticeFlag:"+informationNoticeFlag);
		alert("ccNoticeFlag:"+ccNoticeFlag);
		alert("document.forms[0].actionNoticeFlag.length:"+document.forms[0].actionNoticeFlag.length);
		alert("document.forms[0].informationNoticeFlag.length:"+document.forms[0].informationNoticeFlag.length);
		alert("document.forms[0].ccNoticeFlag.length:"+document.forms[0].ccNoticeFlag.length);*/
		
		var isChecked = $('#chkScheduletime').prop('checked');	
			if(isChecked)
			{
				var isSchdeuletime = SpecialCharacterCheck(trim(document.forms[0].emailPeriod.value));
				if(isSchdeuletime){
					
		                    var id = '1030';
		                    hideErrors();
		                    addMsgNum(id);
		                    showScrollErrors("emailPeriod",-1);	
		                    return false;
		
		         }
		         if(trim(document.forms[0].emailPeriod.value)=="" || 
				   trim(document.forms[0].emailPeriod.value).length==0){
			     		 	var id = '1030';
							hideErrors();
							addMsgNum(id);
							showScrollErrors("emailPeriod",-1);
							return false;
				}
				if(document.forms[0].emailPeriod.value !=""){
					if (!(isNumericDecimal(document.forms[0].emailPeriod.value))) {
				
				        var id = '1030';
				        hideErrors();
				        addMsgNum(id);
				        showScrollErrors("emailPeriod",-1);
				        return false;
				
					}
				}
			  	
			}else{
				document.forms[0].emailPeriod.value = "0";
			}								
			document.getElementById("SaveEmailSubscr").disabled = true;
		 	document.forms[0].action="UserMaintenanceAction.do?method=save";
			document.forms[0].submit();
}

$(document).ready(function () {

			$('.userRadio').click(function () {
				var parentTr = $(this).closest('tr');
				parentTr.siblings().css('background-color', 'white');
				parentTr.css('background-color', '#d9edf7');
			});
			
			$('#chkScheduletime').click(function(){
				var isChecked = $('#chkScheduletime').prop('checked');	
				if(isChecked)
				{
					$("#emailPeriod").removeAttr("disabled").removeClass("disabled"); 
				}else{
					$("#emailPeriod").attr("disabled", "disabled").addClass("disabled"); 					
				}
			});
		

});
		
		
function fnExportDetailsToExcel(){
			document.forms[0].action="UserMaintenanceAction.do?method=exportUserDetails";
			document.forms[0].submit();
	
}

//Added for CR-126 Ends here