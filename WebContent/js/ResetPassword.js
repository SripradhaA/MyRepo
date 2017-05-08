 function fnResetPassword(){
 /*spectypeCheck = false;*/
	var mod = document.forms[0];
	
	 if (mod.userId.options[mod.userId.selectedIndex].value =="-1")
	  {
			var id = '152';
			//hideErrors();
			addMsgNum(id);
			showScrollErrors("sltUserID",-1);
			return false;
			}
	mod.userId.value = mod.userId.options[mod.userId.selectedIndex].value;
	document.forms[0].action="ResetPasswordAction.do?method=ResetPassword";
	document.forms[0].submit();

}