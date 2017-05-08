function fnChangePwd()
{
if(trim(document.forms[0].newPassword.value).length==0 || trim(document.forms[0].newPassword.value)=="" )
	{
					var id = '148';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("newPassword",-1);
					return false;
	}
	if(trim(document.forms[0].newPassword.value).length < 8 || trim(document.forms[0].newPassword.value).length > 12 )
	{
					var id = '225';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("newPassword",-1);
					document.forms[0].newPassword.value="";
					document.forms[0].confirmPassword.value="";
					
					return false;
	}
	if(trim(document.forms[0].confirmPassword.value).length==0 || trim(document.forms[0].confirmPassword.value)=="" )
	{
					var id = '149';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("confirmPassword",-1);		
					return false;
	}
	
    if(trim(document.forms[0].newPassword.value)!= trim(document.forms[0].confirmPassword.value) )
	{
					var id = '150';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("newPassword",-1);		
					document.forms[0].confirmPassword.value="";
					return false;
	}

	document.forms[0].action="ChangePassword.do?method=updatePwd";
	document.forms[0].submit();
	
}


