function savePartOf()
{
var flag;

if (document.forms[0].clauseSeqNo==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
	return false;
}


if(document.forms[0].clauseSeqNo.length > 0)
{
   for( var i=0;i<document.forms[0].clauseSeqNo.length;i++)
	    {
	    	if(document.forms[0].clauseSeqNo[i].checked)
	    	{
	    		flag="true";
		         document.forms[0].clauseSeqNo.value=document.forms[0].clauseSeqNo[i].value;		         
		         document.forms[0].numClause.value=document.forms[0].hdnClauseNum[i].value;
		         window.opener.document.forms[0].subSecCode.value=document.forms[0].hdnClauseNum[i].value;  			
		        
	         	break;
	         }
	      }
     }else
     {
         if(document.forms[0].clauseSeqNo.checked)
         {
         flag="true";
         document.forms[0].clauseSeqNo.value=document.forms[0].clauseSeqNo.value;
         document.forms[0].numClause.value=document.forms[0].hdnClauseNum.value;
         window.opener.document.forms[0].subSecCode.value=document.forms[0].hdnClauseNum.value;  			
       
         }
     }
	if (!flag)
		{
			var id = '520';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("clauseSeqNo",-1);
			return false;
		}

document.forms[0].action="OrderPartofPopUpAction.do?method=savePartOf";	
document.forms[0].submit();

}


function Cancelpopup()
{
var orderKey;
var secSeqNo;
var revCode;
orderKey=document.forms[0].orderKey.value;
secSeqNo=document.forms[0].parentSecSeqNo.value;
revCode=document.forms[0].revCode.value;
var flag;
if (document.forms[0].clauseSeqNo==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
	return false;
}
if(document.forms[0].clauseSeqNo.length > 0)
{
   for( var i=0;i<document.forms[0].clauseSeqNo.length;i++)
	    {
	    	if(document.forms[0].clauseSeqNo[i].checked)
	    	{
	    		flag="true";       			
		        
	         	break;
	         }
	      }
     }else
     {
         if(document.forms[0].clauseSeqNo.checked)
         {
         flag="true";         
         }
     }
	if (!flag)
		{
			var id = '520';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("clauseSeqNo",-1);
			return false;
		}
window.opener.document.forms['OrderSectionForm'].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeqNo+"&revCode="+revCode+"";
window.opener.document.forms['OrderSectionForm'].submit();
}

function CloseWindow()
{
window.close();
}
