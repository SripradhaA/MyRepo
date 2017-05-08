function fnClosePopUp(){
             
     window.opener.fnLeadCompReset();            
     window.close();
}

function fnLeadCompReset(){
        
     document.forms['AddClauseOrderForm'].leadComponentSeqNo.options[0].selected = true;
}
function fnValidatedComp(){
   
	  if(document.forms[0].hdnOrdrNewComp.value=="Y"){
	  window.opener.document.forms[0].validCompName.value=document.forms[0].componentName.value;
	  window.close();
	  }
}
/** key handler for new component screen CM68219*/
function keyHandler(e)
{ 
   	 var asciiValue = e ? e.which : window.event.keyCode;
	 if(asciiValue ==13){         
	    if(document.forms[0].flag.value == "Y"){
	      	fnValidateCompName();
    	  	return false;
    	  }
        }
       
}
  document.onkeypress = keyHandler;  
  /* ends*/

function fnValidateCompName(){
 
	   comName =  trim(document.forms[0].componentName.value);

       if(comName=="" && comName.length == 0){
       
                     var id = '208';
                     hideErrors();
                     addMsgNum(id);
                     showScrollErrors("componentName",-1);
                     return false;

          }
          
          document.forms[0].hdnOrdrNewComp.value="";
          document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=validateOrderCompName";
          document.forms['AddClauseOrderForm'].submit();

}


function checkNewComp(){
      
      if(document.forms[0].compGroupSeqNo.options[document.forms[0].compGroupSeqNo.selectedIndex].value == -1){          
            
            
         if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -2){
                     
                     document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value = -1;
                     
                     var id = '761';
                     hideErrors();
                     addMsgNum(id);
                     showScrollErrors("sltLeadCompGrp",-1);                            
               
               }  
        }else{
        
              if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -2){
            
                
                var modelSeqNo=document.forms[0].hdnModelSeqNo.value;
                var subSecSeqNo=document.forms[0].hdnSubSecSeqNo.value;
                var orderNo=document.forms[0].hdnOrderNo.value;
                var compGrpSeqNo = document.forms[0].compGroupSeqNo.options[document.forms[0].compGroupSeqNo.selectedIndex].value;
                
                newwindow = window.open("orderAddClauseAction.do?method=initLoadOrderComp&compGrpSeqNo="+compGrpSeqNo+"&modelSeqNo="+modelSeqNo+"&subSecSeqNo="+subSecSeqNo+"&orderNo="+orderNo,"Clause","location=0,resizable=no ,status=0,scrollbars=1,width=850,height=300");
                setTimeout("newwindow.focus()", 200);
                
               } 
               
               if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value != -2){
            
                 document.forms[0].validCompName.value="";                    
                
                
               } 
           }             
        }
        
function AddComponent()
{
var selectedModelID=document.forms['AddClauseOrderForm'].hdnModelSeqNo.value;
window.open("orderCompSearchAction.do?method=initLoad&selectedModelID="+selectedModelID,"Clause","location=0,resizable=no ,status=0,scrollbars=1,width=850,height=500");
}

function deleteComponent()
{
if (document.forms[0].component.options.length!=0)
{
var del=confirm("Are you sure you want to clear all the components");
if(del == true)
{
document.forms[0].component.options.length=0;
document.forms[0].hdncomponentGroupSeqNo.value="";
document.forms[0].componentSeqNo.value="";
//Added for CR-74 retain Components tied to Clause after validation
document.forms[0].hdnComponentName.value="";

}
}

}

/*
* Name: fetchComponent()
*  Purpose: Used to fetch the Components
*
*/
function fetchComponent()
{
//Modified for CR_81 by RR68151
//var selectedOption=document.forms['OrderCompSearchForm'].characterisationFlag.selectedIndex;
/* Commented for CR-114
var selectedOption=document.forms['OrderCompSearchForm'].compGroupTypeSeqNo.selectedIndex;
if(document.forms['OrderCompSearchForm'].compGroupTypeSeqNo.options[selectedOption].value==-1)
{
var id = '523';
hideErrors();
addMsgNum(id);
showErrors();
}
else
{*/
document.forms['OrderCompSearchForm'].action="orderCompSearchAction.do?method=fetchComps";
document.forms['OrderCompSearchForm'].submit();
//}Commented for CR-114
}

/*
* Name: fnCancel
*    Purpose: Used to Close the windows opened from the page
*
*/
function fnCancel()
{
window.close();
}


/*
*    Name: fnComponentSubmit
*  Purpose: Used to Submit the component after selection
*
*/

function fnComponentSubmit()
{
var index =0;//Added for CR_121

var componentArray = document.getElementsByName("componentSeqNo");
var hiddencomponentNameArray =new Array() ;
var cnt=0;
var prevCompGrp=0;
var currCompGrp=0;
var index=0;
var tmp=0;
var chkd=0;
var space="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
var msg="Please select single Component for the following Component Groups ";


if(componentArray.length>1){

		   for(cnt=0;cnt<componentArray.length;cnt++){
			
				        if(componentArray[cnt].checked){
								     
								      if(index==0){
						
						                prevCompGrp=document.forms[0].componentGroupSeqNo[cnt].value;
										currCompGrp=prevCompGrp;
						                index=index+1;
						
									 }else{
									    
										currCompGrp=document.forms[0].componentGroupSeqNo[cnt].value;
						                
													if(prevCompGrp == currCompGrp && chkd!=currCompGrp){
									            
									                    hiddencomponentNameArray[tmp]=document.forms[0].componentGroupName[cnt].value;
														
														tmp++;  
									                    chkd=currCompGrp;
									                    prevCompGrp=currCompGrp;
													
													}else{
									
									                      prevCompGrp=currCompGrp;
									
													}
						
									 }
									
						}    
           }
}

if(hiddencomponentNameArray.length>0){
  
    	for(cnt=0;cnt<hiddencomponentNameArray.length;cnt++){
         
			  if(cnt==0){
			 
			  //msg=msg+"<BR>"+space+(cnt+1)+" . "+hiddencomponentNameArray[cnt];
	          //Changed for CR_121-to display proper message
	           msg=msg+": "+hiddencomponentNameArray[cnt];
	          }else{
	          
	          //msg=msg+"<BR>"+space+(cnt+1)+" . "+hiddencomponentNameArray[cnt];
	          //Changed for CR_121-to display proper message
	          msg=msg+", "+hiddencomponentNameArray[cnt];
	          
	          }
	  }
        
        hideErrors();
		addMessageCommon(msg ,"N");
		showScrollErrors("componentSeqNo",index);
		return false;

}

var hiddencomponentArray =new Array() ;
var hiddencomponentSeqNo =new Array() ;
var hiddencomponentGrpSeqNo =new Array() ;
var existList=window.opener.document.forms[0].component.options.length;
var count=0;
if (window.opener.document.forms[0].component.options.length!=0)
{
window.opener.document.forms[0].component.options.length=0;
}


if (existList!=0)
{
existList=0;
}
//Modified for CR_81 by RR68151
if(document.forms[0].compGroupTypeSeqNo.options[document.forms[0].compGroupTypeSeqNo.selectedIndex].value==-1)
{
var id = '523';
hideErrors();
addMsgNum(id);
showScrollErrors("compGroupTypeSeqNo",-1);
return false;
}

if (document.forms[0].componentSeqNo==null)
{
var id = '527';
hideErrors();
addMsgNum(id);
showScrollErrors("componentSeqNo",-1);
return false;
}
else
{
	if (componentArray.length>1)
	{
	
	for (i=0;i<componentArray.length;i++)
	{
	
	if (document.forms[0].componentSeqNo[i].checked)
	{
	hiddencomponentArray[count]=document.forms[0].componentName[i].value;
	hiddencomponentSeqNo[count] = document.forms[0].componentSeqNo[i].value;
	hiddencomponentGrpSeqNo[count]=document.forms[0].componentGroupSeqNo[i].value;
	count++;
	}
	}
	}
	else
	{
	if(document.forms[0].componentSeqNo.checked)
	{
	hiddencomponentArray[count]=document.forms[0].componentName.value;
	hiddencomponentSeqNo[count] = document.forms[0].componentSeqNo.value;
	hiddencomponentGrpSeqNo[count]=document.forms[0].componentGroupSeqNo[i].value;
	count++;
	}
	}
}
if (count==existList)
{
var id = '522';
hideErrors();
addMsgNum(id);
showScrollErrors("componentSeqNo",-1);
}
else
{
window.opener.fnCompSubmit(hiddencomponentArray,hiddencomponentSeqNo,hiddencomponentGrpSeqNo);
window.close();
}
}
/*
* Name: fnCompSubmit
*    Purpose: Used to add the selected Components from Pop-Up screen
*
*/
function fnCompSubmit(hiddencomponentGrpArray , hiddencomponentSeqNo,hiddencomponentGrpSeqNo)
{
//Added to solve Components tied to clause Issue    
document.forms[0].hdncomponentGroupSeqNo.value = "";
//Added for CR-74 to retain Components Tied To Clause after Serverside validation
document.forms[0].hdnComponentName.value="";
existLength= document.forms[0].component.options.length;
for (i=existLength;i<hiddencomponentGrpArray.length;i++)
{
additem = new Option();
additem.value = hiddencomponentSeqNo[i];
additem.text = hiddencomponentGrpArray[i];
document.forms[0].component.options[existLength]=additem;
document.forms[0].hdncomponentGroupSeqNo.value+=hiddencomponentGrpSeqNo[i]+"~";
//Added for CR-74 to retain Components Tied To Clause after Serverside validation
document.forms[0].hdnComponentName.value+=hiddencomponentGrpArray[i]+"~";
existLength++;
}

}

/*
*  Name: deleteTable
*  Purpose: Used to delete the table
*
*/
function deleteTable()
{
	var conf = window.confirm("Do you want to delete the table?","YesNo");
	if(conf)
	{
		$('#tabledata').remove();
		document.getElementById('showmainlink').style.visibility="visible";
		document.getElementById('showsublink').style.visibility="hidden";
	}else{
	/*rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
	var len = rows.length;
	var conf = window.confirm("Do you want to delete the table?","YesNo");
	if(conf)
	{
	for (var i = 0; i < len; i++)
	{
	document.all("tblGrid").deleteRow();
	}
	}
	else
	{
	return;
	}
	
	document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>                             <TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>                <TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>             <TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";*/
	document.getElementById('showmainlink').style.visibility="hidden";
	document.getElementById('showsublink').style.visibility="visible";
	}

}
/*
*    Name: RemoveRow
*  Purpose: Used to Remove the selected Row of the table
*
*/
function removeRow()
{
	var val=$("input[type='radio'][name='deleterow']:checked").length;
	if (val > 0)	{
		var conf = window.confirm("Do you want to delete the selected row?","YesNo");
		if(conf){
		$("input[type='radio'][name='deleterow']:checked").closest(".tablerow").remove();
		}
	}
/*var pos;
var flag = false;
var defaultrow;
var len = document.forms[0].deleterow.length;

if(len==undefined && document.forms[0].deleterow.checked)
{
defaultrow =true;
}
for(var i =0; i < len; i++)
{
if(document.forms[0].deleterow[i].checked)
{
pos = i;
flag = true;
break;
}

}

if(flag || defaultrow)
{
var conf = window.confirm("Do you want to delete the selected row?","YesNo");

if(conf && flag)
{
document.all("tblGrid").deleteRow(pos+1);
}
else if(conf && defaultrow)
{
document.all("tblGrid").deleteRow(1);
}
else
{
return;
}
}*/

}

/*
*   Name: addRow
*    Purpose: Used to Add a row to the table
*
*/
function addRow(src)
{
$('#tabledata').append("<div class='row tablerow'><div class='col-sm-1 text-right'>" +
				"<label class='radio-inline'><INPUT type='radio' name='deleterow'  /></label></div>" +
				"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray1'  SIZE='15' MAXLENGTH='100'/></div>" +
				"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray2' SIZE='15' MAXLENGTH='100'/></div>" +
				"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray3' SIZE=15 MAXLENGTH='100'/></div>" + 
				"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray4' SIZE=15 MAXLENGTH='100'/></div>" +
				"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray5' SIZE=15 MAXLENGTH='100'/></div></div>");
/*
rows = $('#tabledata > .tablerow').length; //document.getElementById('tblGrid').getElementsByName('tabledata')[0].getElementsByName('tablerow');
alert(rows);
for (i = 0; i < rows.length; i++)
{
index = this.rowIndex + 1;
index1 = this.rowIndex ;
}
var newRow = document.all("tblGrid").insertRow(rows.length);
var oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input  type='radio' name='deleterow'/>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='form-control' type='text' name='clauseTableDataArray1' SIZE='15' MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='form-control' type='text' name='clauseTableDataArray2' SIZE='15' MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='form-control' type='text' name='clauseTableDataArray3' SIZE='15' MAXLENGTH='100'>";
oCell = newRow.insertCell(); 
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='form-control' type='text' name='clauseTableDataArray4' SIZE='15' MAXLENGTH='100'>";
oCell = newRow.insertCell();
oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='form-control' type='text' name='clauseTableDataArray5' SIZE='15' MAXLENGTH='100'>";*/
}
/*
* Name: addTable
*   Purpose: Used to add a Table
*
*///Edited for CR-131
function addTable()
{
var val ='';
val = "<div id='tabledata' class='form-horizontal'><div class='row tablerow'>";
val = val+"<div class='col-sm-1'></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='clauseTableDataArray1' SIZE='13' MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='clauseTableDataArray2' SIZE='13' MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='clauseTableDataArray3' SIZE=13 MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='clauseTableDataArray4' SIZE=13 MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='clauseTableDataArray5' SIZE=13 MAXLENGTH='100'/></div>";
val = val+"</div><div class='row tablerow'><div class='col-sm-1 text-right'><label class='radio-inline'><INPUT type='radio' name='deleterow'  /></label></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray1'  SIZE='15' MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray2' SIZE='15' MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray3' SIZE=15 MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray4' SIZE=15 MAXLENGTH='100'/></div>";
val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='clauseTableDataArray5' SIZE=15 MAXLENGTH='100'/></div>";
val = val+"</div></div>";
document.getElementById('showgrid').innerHTML=val;
document.getElementById('showgrid').style.visibility="visible";
document.getElementById('showsublink').style.visibility="visible";
document.getElementById('showmainlink').style.visibility="hidden";
}


/***
*    fnAddClauseOrder and fnAddClauseModel are added based on LSDB_CR-35
*  These two functions sets the clauseSource as M or O based on the  user Action and call Addclause function
*   Added on 04-April-08
*  Modified for CR_97
**/

function fnAddClauseOrder()
{

if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -2){
                     
                    if(document.forms[0].validCompName.value=="" && document.forms[0].validCompName.value.length ==0){
                    
                        var id = '789';
						hideErrors();
						addMsgNum(id);
						showScrollErrors("sltLeadComp",-1);
						return false;
                    
                    } 
}
//Added for giving missed out validation
      if(document.forms[0].compGroupSeqNo.options[document.forms[0].compGroupSeqNo.selectedIndex].value >0){
      if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -1){     
              
                 var id = '789';
				 hideErrors();
				 addMsgNum(id);
				 showScrollErrors("sltLeadComp",-1);
                 return false;
               }
      }
document.forms[0].clauseSource.value="O";
fnAddClauses();

}

function fnAddClauseModel()
{
	if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -2){
                     
        if(document.forms[0].validCompName.value=="" && document.forms[0].validCompName.value.length ==0){
                    
              var id = '789';
			  hideErrors();
			  addMsgNum(id);
			  showScrollErrors("sltLeadComp",-1);
			  return false;
                    
         } 
    } 

	//Added for giving missed out validation
    
      if(document.forms[0].compGroupSeqNo.options[document.forms[0].compGroupSeqNo.selectedIndex].value >0){
      if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -1){     
              
                 var id = '789';
				 hideErrors();
				 addMsgNum(id);
				 showScrollErrors("sltLeadComp",-1);
                 return false;
               }  
               
	  } 
	  
	//Added for CR-68 Order New Component
	if(document.forms[0].leadComponentSeqNo.options[document.forms[0].leadComponentSeqNo.selectedIndex].value == -2){
   
         var id = '750';
         hideErrors();
         addMsgNum(id);
         showScrollErrors("sltLeadComp",-1);
         return false;
   
   
   }
  
 /*if(document.forms[0].roleID.value=="SE" && document.forms[0].specTypeSeqNo.value == 1){

    var accept=confirm("Are you sure to add the clause at Order and Model levels ?");

	if(accept==true){

            var acc=confirm("This function is beyond the Current user role. Are you sure to add the clause to Model level?");
				if(acc==true){
				document.forms[0].clauseSource.value="M";
				fnAddClauses();
				}
				else{
				}
			
	}	
			
			
			
	if(accept==false){
			}
	

 }else{
    
	var accept=confirm("Are you sure to add the clause at Order and Model levels ?");

	if(accept==true){*/
            document.forms[0].clauseSource.value="M";
			fnAddClauses();
	/*}else{
	}

 }*/

 


}
/* Modified for CR_97 */
function fnAddClauses()
{
/*added for CR 73 by cm68219 starts*/
var orderKey = document.forms[0].hdnOrderKey.value;
var secSeq =   document.forms[0].hdnsecSeq.value;
/*added for CR 73 by cm68219 starts*/
var secName = document.forms[0].hdnSecName.value;
var revCode = document.forms[0].hdnRevCode.value;
var subsecseqno=document.forms[0].hdnSubSecSeqNo.value;
var addClauseForm = document.forms['AddClauseOrderForm'];
var clauseDesc = trim(addClauseForm.newClauseDesc.value);

addClauseForm.newClauseDesc.value=clauseDesc;


var dwoNumber = trim(addClauseForm.dwoNo.value);
var partNumber=trim(addClauseForm.partNo.value);
var priceBookNumber=trim(addClauseForm.priceBookNo.value);
var comments=trim(addClauseForm.comments.value);

var refEdl1=trim(addClauseForm.refEdlNo[0].value);
var refEdl2=trim(addClauseForm.refEdlNo[1].value);
var refEdl3=trim(addClauseForm.refEdlNo[2].value);
var refEdl=refEdl1+refEdl2+refEdl3;
var edlNo1=trim(addClauseForm.newEdlNo[0].value);
var edlNo2=trim(addClauseForm.newEdlNo[1].value);
var edlNo3=trim(addClauseForm.newEdlNo[2].value);
var edlNo=edlNo1+edlNo2+edlNo3;
var compSeqNo;
var partOfSeqNo1=trim(addClauseForm.partOf[0].value);
var partOfSeqNo2=trim(addClauseForm.partOf[1].value);
var partOfSeqNo3=trim(addClauseForm.partOf[2].value);
var partOfSeqNo4=trim(addClauseForm.partOf[3].value);
addClauseForm.newClauseDesc.value=clauseDesc;
addClauseForm.comments.value=comments;
addClauseForm.dwoNo.value=dwoNumber;
addClauseForm.partNo.value=partNumber;
addClauseForm.priceBookNo.value=priceBookNumber;
addClauseForm.newEdlNo[0].value=edlNo1;
addClauseForm.newEdlNo[1].value=edlNo2;
addClauseForm.newEdlNo[2].value=edlNo3;
addClauseForm.refEdlNo[0].value=refEdl1;
addClauseForm.refEdlNo[1].value=refEdl2;
addClauseForm.refEdlNo[2].value=refEdl3;
//Added to solve Components tied to clause Issue
document.forms['AddClauseOrderForm'].componentSeqNo.value="";
for(var i=0;i<document.forms['AddClauseOrderForm'].component.options.length; i++ ){

compSeqNo = document.forms['AddClauseOrderForm'].component.options[i].value;
document.forms['AddClauseOrderForm'].componentSeqNo.value=document.forms['AddClauseOrderForm'].componentSeqNo.value+compSeqNo+"~";
}


/* Added For Attach Clause CR **********/
if(document.forms['AddClauseOrderForm'].hdncomponentGroupSeqNo.value!=undefined){

var hdnCompSeqNo=document.forms['AddClauseOrderForm'].hdncomponentGroupSeqNo.value;
var splitCompGrpSeqNo=hdnCompSeqNo.split("~");

for(var i=0;i<splitCompGrpSeqNo.length-1;i++){

if(document.forms['AddClauseOrderForm'].compGroupSeqNo.value==splitCompGrpSeqNo[i]){

var id = '788';
hideErrors();
addMsgNum(id);
showScrollErrors("sltLeadCompGrp",-1);
return false;
}
}

}

/* Added For CR_88 */
if ($('#clauseDesc_id').val().length == 0) {
		var id = '506';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc_id_ifr",-1);
		return false;
}
/* Commented for CR-121
if ($('#clauseDesc_id').val().length > 4000) {

		var id = '516';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc_id",-1);
		
		tinymce.execCommand('mceFocus',false,'clauseDesc_id');
		return false;
}*/

/* Added For CR_88 - Ends here */
/*
if(clauseDesc.length==0 || clauseDesc=="" )
{

var id = '506';
hideErrors();
addMsgNum(id);
showErrors();

addClauseForm.newClauseDesc.value="";
addClauseForm.newClauseDesc.select();
addClauseForm.newClauseDesc.focus();
return false;
}

if(clauseDesc.length > 4000)
{

var id = '516';
hideErrors();
addMsgNum(id);
showErrors();


addClauseForm.newClauseDesc.select();
addClauseForm.newClauseDesc.focus();
return false;
}
*/
if (document.forms[0].clauseTableDataArray1!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray1.length;i++)
{
document.forms[0].clauseTableDataArray1[i].value=trim(document.forms[0].clauseTableDataArray1[i].value);
}
}
if (document.forms[0].clauseTableDataArray2!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray2.length;i++)
{
document.forms[0].clauseTableDataArray2[i].value=trim(document.forms[0].clauseTableDataArray2[i].value);
}
}
if (document.forms[0].clauseTableDataArray3!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray3.length;i++)
{
document.forms[0].clauseTableDataArray3[i].value=trim(document.forms[0].clauseTableDataArray3[i].value);
}
}
if (document.forms[0].clauseTableDataArray4!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray4.length;i++)
{
document.forms[0].clauseTableDataArray4[i].value=trim(document.forms[0].clauseTableDataArray4[i].value);
}
}
if (document.forms[0].clauseTableDataArray5!=null)
{
for (var i=0;i<document.forms[0].clauseTableDataArray5.length;i++)
{
document.forms[0].clauseTableDataArray5[i].value=trim(document.forms[0].clauseTableDataArray5[i].value);
}
}


if (document.forms[0].clauseTableDataArray1!=null)
{
if (document.forms[0].clauseTableDataArray5.length!=undefined)
{
for (var i=0;i<document.forms[0].clauseTableDataArray5.length;i++)
{
if ((document.forms[0].clauseTableDataArray1[i].value=="")&&(document.forms[0].clauseTableDataArray2[i].value=="")&&(document.forms[0].clauseTableDataArray3[i].value=="")&&(document.forms[0].clauseTableDataArray4[i].value=="")&&(document.forms[0].clauseTableDataArray5[i].value==""))
{
var id = '526';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseTableDataArray1",i);
return false;

}
}
}
else
{
if ((trim(document.forms[0].clauseTableDataArray1.value)=="")&&(trim(document.forms[0].clauseTableDataArray2.value)=="")&&(trim(document.forms[0].clauseTableDataArray3.value)=="")&&(trim(document.forms[0].clauseTableDataArray4.value)=="")&&(trim(document.forms[0].clauseTableDataArray5.value)==""))
{
var id = '526';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseTableDataArray1",-1);
return false;

}
}
}


if ((addClauseForm.standardEquipmentSeqNo.options[addClauseForm.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
var id = '505';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",0);
return false;
}
else
{

/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/

/* for(i=0;i<document.forms[0].refEdlNo.length;i++){
if(document.forms[0].refEdlNo[i].value != ""){
if((!validateNumber(trim(document.forms[0].refEdlNo[i].value)))){
var id = '515';
hideErrors();
addMsgNum(id);
showErrors();
document.forms[0].refEdlNo[i].focus();
return;
}
}
}
*/

if ((refEdl1<0 ) && (refEdl2<0||refEdl2.length==0) && (refEdl3<0||refEdl3.length==0))
{
if (refEdl1!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",0);

return false;
}
}
if (refEdl1.length!=0 ||refEdl1!="")
{
if (refEdl1<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",0);

return false;
}
if (refEdl1==refEdl2||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",0);
return false;

}


}

if ((refEdl2<0) &&((refEdl1<0)||(refEdl1.length==0)) &&((refEdl3<0)||(refEdl3.length==0)))
{
if (refEdl2!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",1);
return false;
}
}
if (refEdl2.length!=0 ||refEdl2!="")
{
if (refEdl2<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",1);

return false;
}
if (refEdl1==refEdl2||refEdl2==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",1);
return false;

}

}

if ((refEdl3<0) &&((refEdl2<0)||(refEdl2.length==0)) &&((refEdl1<0)||(refEdl1.length==0)))
{
if (refEdl3!="")
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",2);
return false;
}
}
if (refEdl3.length!=0 ||refEdl3!="")
{
if (refEdl3<0)
{
var id = '528';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",2);

return false;
}

if (refEdl2==refEdl3||refEdl1==refEdl3)
{
var id = '510';
hideErrors();
addMsgNum(id);
showScrollErrors("refEdlNo",2);
return false;

}

}
/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/

/* for(i=0;i<document.forms[0].newEdlNo.length;i++){
if(document.forms[0].newEdlNo[i].value != ""){
if(!(validateNumber(trim(document.forms[0].newEdlNo[i].value)))){
var id = '514';
hideErrors();
addMsgNum(id);
showErrors();
document.forms[0].newEdlNo[i].focus();
return;
}
}
}
*/

if ((edlNo1<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo1!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",0);

return false;
}
}






if (edlNo1.length!=0 ||edlNo1!="")
{
if (edlNo1<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",0);
return false;
}
if (edlNo1==edlNo2||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",0);
return false;

}

}

if ((edlNo2<0 ) && (edlNo1<0||edlNo1.length==0) && (edlNo3<0||edlNo3.length==0))
{
if (edlNo2!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",1);
return false;
}
}
if (edlNo2.length!=0 ||edlNo2!="")
{
if (edlNo2<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",1);

return false;
}
if (edlNo1==edlNo2||edlNo2==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",1);
return false;

}

}
if ((edlNo3<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo1<0||edlNo1.length==0))
{
if (edlNo3!="")
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",2);

return false;
}
}
if (edlNo3.length!=0 ||edlNo3!="")
{
if (edlNo3<0)
{
var id = '529';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",2);

return false;
}
if (edlNo2==edlNo3||edlNo1==edlNo3)
{
var id = '509';
hideErrors();
addMsgNum(id);
showScrollErrors("newEdlNo",2);
return false;

}

}
if (partOfSeqNo1.length!=0 ||partOfSeqNo1!=0)
{

if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOf",0);
return false;

}

}

if (partOfSeqNo2.length!=0 ||partOfSeqNo2!=0)
{
if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOf",1);
return false;

}

}
if (partOfSeqNo3.length!=0 ||partOfSeqNo3!=0)
{
if (partOfSeqNo2==partOfSeqNo3||partOfSeqNo1==partOfSeqNo3||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOf",2);
return false;

}

}
if (partOfSeqNo4.length!=0 ||partOfSeqNo4!=0)
{
if (partOfSeqNo1==partOfSeqNo4||partOfSeqNo2==partOfSeqNo4||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOf",3);
return false;

}

}

if(dwoNumber.length!=0 || dwoNumber!="" )
{
if(!(validateNumber(trim(dwoNumber))))
{
var id = '508';
hideErrors();
addMsgNum(id);
showScrollErrors("dwoNo",-1);
return false;
}
}
if (dwoNumber<=0 && dwoNumber.length!=0 )
{
var id = '530';
hideErrors();
addMsgNum(id);
showScrollErrors("dwoNo",-1);
return false;
}

if(partNumber.length!=0 || partNumber!="")
{
if(!(validateNumber(trim(partNumber))))
{

var id = '512';
hideErrors();
addMsgNum(id);
showScrollErrors("partNo",-1);
return false;
}
}
if (partNumber<=0 && partNumber.length!=0)
{
var id = '531';
hideErrors();
addMsgNum(id);
showScrollErrors("partNo",-1);
return false;
}

if(priceBookNumber!=0 || priceBookNumber!="" )
{
if(!(validateNumber(trim(priceBookNumber))))
{

var id = '513';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNo",-1);
return false;
}
}

if (priceBookNumber<=0 && priceBookNumber.length!=0)
{
var id = '532';
hideErrors();
addMsgNum(id);
showScrollErrors("priceBookNo",-1);
return false;
}
if(comments.length > 2000)
{

var id = '517';
hideErrors();
addMsgNum(id);
showScrollErrors("comments",-1);
return false;
}
}//end of else

/** Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 **/

//Modified for CR_79 removing validation before Opening Status on 28-Oct-09 by RR68151
var reasons=trim(addClauseForm.reason.value);
addClauseForm.reason.value=reasons;

if(document.forms[0].specStatusCode.value>=3)
{ 

if(reasons.length==0 || reasons=="" )
{
var id = '507';
hideErrors();
addMsgNum(id);
showScrollErrors("reason",-1);
return false;
}
}

if(reasons.length > 2000)
{
var id = '518';
hideErrors();
addMsgNum(id);
showScrollErrors("reason",-1);
return false;
}
/* } */
var accept;
//Added and Commented for CR_99 to remove junk characters by Validating the Clause Description through AJAX
var url = "orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"";
var clauseDesc = $('#clauseDesc_id').val();
var clauseSource=document.forms[0].clauseSource.value;
if(clauseSource=="O")
{
	/*accept=confirm("Are you sure to add the clause at Order level only ?");
	if(accept){
	Commented for CR_97 */
		 if(document.forms[0].validCompName.value!=""){
		confirmDialog(orderKey,secSeq,revCode,subsecseqno,clauseDesc);
		}else{
		fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
		//document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
		//document.forms['AddClauseOrderForm'].submit();
		}
	/*}else{
		return false;
	}*/

}else{
/*added to remove alert before validations by cm68219 starts*/
if(document.forms[0].roleID.value=="SE" && document.forms[0].specTypeSeqNo.value == 1){
 
    var accept=confirm("Are you sure to add the clause at Order and Model levels ?");

	if(accept==true){

            var acc=confirm("This function is beyond the Current user role. Are you sure to add the clause to Model level?");
				if(acc==true){
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
				//document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
           		//document.forms['AddClauseOrderForm'].submit();
				}
				else{
					return false;
				}
			
	}	
			
			
			
	if(accept==false){
				return false;
			}
	

 }else{
    
	var accept=confirm("Are you sure to add the clause at Order and Model levels ?");

	if(accept==true){
			fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
            //document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
            //document.forms['AddClauseOrderForm'].submit();
	}else{
	}

 }
/*added to remove alert before validations by cm68219 ends*/
}
//Commented out on 22 Jun 09 by KA57588
//Starts here
/*if(accept==true)
{
document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;

document.forms['AddClauseOrderForm'].submit();
}else{
}*/

//Ends
}
/*
*  Name: LoadClauses
*   Purpose: Used to Load LoadClausesByOrder.jsp and load its initial parameters
*
*/
function LoadClauses(index)
{
var orderNo=document.forms[0].hdnOrderNo.value;
var orderKey=document.forms[0].hdnOrderKey.value;
var modelSeqNo=document.forms[0].hdnModelSeqNo.value;

window.open("orderClausePartOfPopUpAction.do?method=initLoad&textBoxIndex="+index+"&ModelSeqNo="+modelSeqNo+"&OrderNum="+orderNo+"&orderKey="+orderKey+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=850,height=600");
}

/*
*   Name: deletePartOfCode()
* Purpose: Used to Delete the Part Of Fields
*
*/
function deletePartOf(index)
{
document.forms[0].clauseSeqNum[index-1].value="";
document.forms[0].partOf[index-1].value="";
document.forms[0].partOfSeqNo[index-1].value="";
}


/*
*   Name: fnLoadSubSection()
*   Purpose: Used to load Subsection on selection of Section
*
*/
function fnLoadSubSection()
{
 //Added for CR-121 DataLocation Type Issue 
var dataLocType = getParameterByName('dataLocType');
if(dataLocType == ""){
	document.forms['OrderClausePartOfPopUpForm'].action="orderClausePartOfPopUpAction.do?method=SubSectionLoad";	
	document.forms['OrderClausePartOfPopUpForm'].submit();
}else{
	document.forms['OrderClausePartOfPopUpForm'].action="orderClausePartOfPopUpAction.do?method=SubSectionLoad&dataLocType="+dataLocType;
	document.forms['OrderClausePartOfPopUpForm'].submit();
}
}

/*
*   Name: fnLoadClause()
*    Purpose: Used to load Clause on selection of SubSection
*
*/
function fnLoadClause()
{
 //Added for CR-121 DataLocation Type Issue 
var dataLocType = getParameterByName('dataLocType');
if(dataLocType == ""){
	document.forms['OrderClausePartOfPopUpForm'].action="orderClausePartOfPopUpAction.do?method=fetchClause";	
	document.forms['OrderClausePartOfPopUpForm'].submit();	
}else{	
	document.forms['OrderClausePartOfPopUpForm'].action="orderClausePartOfPopUpAction.do?method=fetchClause&dataLocType="+dataLocType;
	document.forms['OrderClausePartOfPopUpForm'].submit();
}
}

/*
*  Name: submitDependantClause()
*  Purpose: Used to populate the Part Of Fields in parent window
*
*/
function submitDependantClause()
{
var clauseNo;
var selIndex;
var flag = false;
//Added for CR-74 VV49326 09-06-09
var reservedFlag = false;
var selectedSubSectionID=document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.selectedIndex;
var subSecID=document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.options[selectedSubSectionID].value;
index = document.forms['OrderClausePartOfPopUpForm'].textBoxIndex.value;
var tooltipindex;//Added for CR_121

if (document.forms['OrderClausePartOfPopUpForm'].sectionSeqNo.options.length<=1)
{
var id = '519';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionSeqNo",-1);
return false;
}
if (document.forms['OrderClausePartOfPopUpForm'].sectionSeqNo.options[document.forms['OrderClausePartOfPopUpForm'].sectionSeqNo.selectedIndex].value==-1)
{
var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionSeqNo",-1);
return false;
}

if (document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.options.length<=1)
{
var id = '511';
hideErrors();
addMsgNum(id);
showScrollErrors("subSectionSeqNo",-1);
return false;
}

if (document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.options[document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.selectedIndex].value==-1)
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("subSectionSeqNo",-1);
return false;
}

if (document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo==null)
{
var id = '16';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
return false;
}

else
{

if (document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.length!=undefined)
{
for(var i = 0 ; i < document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.length; i++)
{

if(document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[i].checked)
{   
    //Added for CR-74 VV49326 09-06-09
    if(document.forms['OrderClausePartOfPopUpForm'].reservedFlag[i].value!="Y")
    {
       reservedFlag=true;
    }
	selIndex = i;
	flag = true;
	tooltipindex =i; //Added for CR_121
	break;
}
}
}
else
{
if(document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.checked)
{
   //Added for CR-74 VV49326 09-06-09
   if(document.forms['OrderClausePartOfPopUpForm'].reservedFlag.value!="Y")
    {
       reservedFlag=true;
    }
	selIndex=1;
	flag = true;
}

}
}
//Added for CR-74 VV49326 09-06-09 changed 12-06-09
if(flag && !reservedFlag){
var id = '762';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",tooltipindex);
return false;


}
if (flag)
{
if (document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.length!=undefined)
{

//Added for CR_110
if(index==9999)//Added for CR_110 to check for clause to be modified
{
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum[selIndex].value;
window.opener.document.forms[0].clauseToModify.value = clauseNo;
window.opener.document.forms[0].hdnClauseToModify.value = clauseNo;
window.opener.document.forms[0].hdnClauseToModifySeqNo.value=document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[selIndex].value;
//Added for CR_117 QA - Fixes
var versionNo =document.forms[0].versionNo[selIndex].value;
//Added for CR_117 QA - Fixes
window.opener.document.forms[0].sectionSeqNo.value=$("#sectionSeqNo").val();
var sectionseqNo =document.forms[0].sectionSeqNo.value;
        $("#sectionSeqNo option").each(function() {
            if ($(this).val() == sectionseqNo) {
              window.opener.document.forms[0].hdnSectionName.value=$(this).html();
			 }
        });

window.opener.document.forms[0].subSectionSeqNo.value=$("#subSectionSeqNo").val();
var subSectionseqNo =document.forms[0].subSectionSeqNo.value;
        $("#subSectionSeqNo option").each(function() {
            if ($(this).val() == subSectionseqNo) {
              window.opener.document.forms[0].hdnSubSectionName.value=$(this).html();
			 }
        });

window.opener.parentsubmit(versionNo);
window.close();
}
else if(index==9998){
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum[selIndex].value;
window.opener.document.forms[0].clauseToDelete.value = clauseNo;
window.opener.document.forms[0].hdnClauseToDelete.value = clauseNo;
window.opener.document.forms[0].hdnClauseToDeleteSeqNo.value=document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[selIndex].value;
window.close();
}
else{

//Added for CR_110 Ends

clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum[selIndex].value;
window.opener.document.forms[0].partOf[index-1].value = clauseNo;
window.opener.document.forms[0].partOfSeqNo[index-1].value = subSecID;
window.opener.document.forms[0].clauseSeqNum[index-1].value = document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[selIndex].value;
window.close();
}
}
else
{

//Added for CR_110
if(index==9999){
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum.value;
window.opener.document.forms[0].clauseToModify.value = clauseNo;
window.opener.document.forms[0].hdnClauseToModify.value = clauseNo;
window.opener.document.forms[0].hdnClauseToModifySeqNo.value=document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.value;
//Added for CR_117 QA - Fixes
var versionNo =document.forms[0].versionNo.value;
//Added for CR_117 QA - Fixes
window.opener.document.forms[0].sectionSeqNo.value=$("#sectionSeqNo").val();
var sectionseqNo =document.forms[0].sectionSeqNo.value;
        $("#sectionSeqNo option").each(function() {
            if ($(this).val() == sectionseqNo) {
              window.opener.document.forms[0].hdnSectionName.value=$(this).html();
			 }
        });

window.opener.document.forms[0].subSectionSeqNo.value=$("#subSectionSeqNo").val();
var subSectionseqNo =document.forms[0].subSectionSeqNo.value;
        $("#subSectionSeqNo option").each(function() {
            if ($(this).val() == subSectionseqNo) {
              window.opener.document.forms[0].hdnSubSectionName.value=$(this).html();
			 }
        });

window.opener.parentsubmit(versionNo);
window.close();
}
else if(index==9998){
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum.value;
window.opener.document.forms[0].clauseToDelete.value = clauseNo;
window.opener.document.forms[0].hdnClauseToDelete.value = clauseNo;
window.opener.document.forms[0].hdnClauseToDeleteSeqNo.value=document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.value;
window.close();
}
else{

//Added for CR_110 ends
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum.value;
window.opener.document.forms[0].partOf[index-1].value = clauseNo;
window.opener.document.forms[0].partOfSeqNo[index-1].value = subSecID;
window.opener.document.forms[0].clauseSeqNum[index-1].value = document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.value;
window.close();
}
}
}
else
{

var id = '520';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);
return false;
}

}

/*
*  Name: fnCloseWindow()
*  Purpose: Used to close the window
*
*/
function fnCloseWindow()
{
window.close();
}

function fnModifySpec()
{
var orderKey = document.forms[0].hdnOrderKey.value;
var secSeq =   document.forms[0].hdnsecSeq.value;
var secCode = document.forms[0].hdnsecCode.value;
var secName = document.forms[0].hdnSecName.value;
var revCode = document.forms[0].hdnRevCode.value;
var subsecseqno=document.forms[0].hdnSubSecSeqNo.value;
document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&secCode="+secCode+"&secName="+secName+"&revCode="+revCode+"&subsecno="+subsecseqno;
document.forms[0].submit();
}
//Added for CR
/*
* Name: fnLoadComponents
*    Purpose: Used to Load Component List on selection of ComponentGroupList
*
*/
function fnLoadComponents1()
{
document.forms[0].validCompName.value="";
document.forms[0].action="orderAddClauseAction.do?method=loadComponent";
document.forms[0].submit();
}
//added by Cm68219 for deleting new component name
function deleteNewComponent()
{
if (document.forms[0].validCompName.value!=0)
{
var del=confirm("Are you sure you want to clear the New Component ");
if(del == true)
{

document.forms[0].validCompName.value="";
document.forms[0].componentSeqNo.value="";

}
}

}
//Ends Here
//Added for CR_97

function confirmDialog(orderKey,secSeq,revCode,subsecseqno,clauseDesc) {
	var url="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"";
		
		bootbox.dialog({
		  message: "You are about to create a NEW component & a NEW clause to the Order.<br/><br/>	Would you like to submit the Change Request Form for this NEW Component & Clause to be added to the Model?<br/><br/><strong>Yes</strong> - Create a Change Request Form<br/><strong>No</strong> - Add to Order Only",
		  title: "Confirm Box",
		  buttons: {
		    yes: {
		      label: "Yes",
		      className: "btn-primary",
		      callback: function() {
		        document.forms[0].newCRFlag.value="Y";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
		      }
		    },
		    no: {
		      label: "No",
		      className: "btn-primary",
		      callback: function() {
		        document.forms[0].newCRFlag.value="N";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
		      }
		    },
		    cencel: {
		      label: "Cancel",
		      className: "btn-default",
		   	}
		  }
		});
	
	
	
	/*$('#confirm').modal({
		closeHTML: "<a href='#' id='close' title='Close' class='modal-close' >X</a>",
		position: ["20%",],
		escClose:false,
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
		var modal = this;

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				modal.close(); // or $.modal.close();
				document.forms[0].newCRFlag.value="Y";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
				//document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
				//document.forms['AddClauseOrderForm'].submit();
			});
			// if the user clicks "no"
			$('.no', dialog.data[0]).click(function () {
				modal.close(); // or $.modal.close();
				document.forms[0].newCRFlag.value="N";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
				//document.forms['AddClauseOrderForm'].action="orderAddClauseAction.do?method=insertClause&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
				//document.forms['AddClauseOrderForm'].submit();
			});
		}
	});*/
}
/*Added for CR_100 for disabling Price Book No for locomotives*/
$(document).ready(function() {
	if($('#specTypeNo').val()==1){
		$('#priceBookNo').removeClass('TEXTBOX2').addClass('TEXTBOX02');
		$("#priceBookNo").attr("disabled", "disabled");
		}
	/*Added For CR_109
	if (parseInt($('#compMapsize').val())<=(parseInt($('#compCount').val())+1))
		$('#viewAllCompClauses').hide();*/
})

/*
* Name: fnLoadAllComponents
* Purpose: Used to display a popup for loading all components across models
* 		   Added for CR_109
*/
function fnLoadAllComponents() {
	var addClauseOrder = document.forms[0];
	window.open("orderAddClauseAction.do?method=loadAllComponents&orderKey="+addClauseOrder.hdnOrderKey.value+"&modelSeqNo="+addClauseOrder.hdnModelSeqNo.value+"&secSeqNo="+addClauseOrder.hdnsecSeq.value+"&subSecSeqNo="+addClauseOrder.hdnSubSecSeqNo.value+"&compGrpSeqNo="+addClauseOrder.compGroupSeqNo.value+"");
}

/*
* Name: fnLoadAllClauses
* Purpose: Used to display all list of clauses irrespective of Model
* 		   Added for CR_109
*/
function fnLoadAllClauses() {
	document.forms[0].action="orderAddClauseAction.do?method=loadAllClauses";
	document.forms[0].submit();	
}

/*
* Name: fnFetchClauseDetails
* Purpose: Used to load all the clause details
* 		   Added for CR_109
*/
function fnMapCompnfetchCla()
{
	var clauseSeqNo = document.forms[0].hdnClauseSeqNo.value;
	var modelSeqNo = document.forms[0].hdnModelSeqNo.value;
	var subSecSeqNo = document.forms[0].hdnSubSecSeqNo.value;
	var compGrpSeqNo = document.forms[0].compGroupSeqNo.value;
	var compSeqNo = document.forms[0].leadComponentSeqNo.value;
	var secSeqNo = document.forms[0].hdnsecSeq.value;
	var orderKey = document.forms[0].hdnOrderKey.value;
	var frmModelSeqNo = document.forms[0].frmModelSeqNo.value;
	var frmSubSecSeqNo = document.forms[0].frmSubSecSeqNo.value;
	var flag = false;
	
	if (document.forms[0].leadClauseSeqNo.length!=undefined)
	{
	for(var i = 0 ; i < document.forms[0].leadClauseSeqNo.length; i++)
		{	
		if(document.forms[0].leadClauseSeqNo[i].checked)
			{   
			flag = true;
			break;
			}
		}
	}
	else
	{
		if(document.forms[0].leadClauseSeqNo.checked)
			{
				flag = true;
			}
	
	}

	if(flag)
	{
		var map = confirm("Are you sure to map the selected component to Model(if not mapped earlier) and load clause details?");
		if(map == true)
		{
			window.close();
			window.opener.document.forms[0].action="orderAddClauseAction.do?method=mapCompnFetchClause&orderKey="+orderKey+"&modelSeqNo="+modelSeqNo+"&secSeqNo="+secSeqNo+"&subSecSeqNo="+subSecSeqNo+"&compGrpSeqNo="+compGrpSeqNo+"&compSeqNo="+compSeqNo+"&clauseSeqNo="+clauseSeqNo+"&frmModelSeqNo="+frmModelSeqNo+"&frmSubSecSeqNo="+frmSubSecSeqNo+"";
			window.opener.document.forms[0].submit();
		}
	} else {
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("leadClauseSeqNo",-1);
		return false;
	}
}

/*
* Name: fnSetSelectedClaValues
* Purpose: Used to set all the clause related details for loading
* 		   Added for CR_109
*/
function fnSetSelectedClaValues(claSeqNo,modelSeqNo,subSecSeqNo){
	document.forms[0].hdnClauseSeqNo.value=claSeqNo;
	document.forms[0].frmModelSeqNo.value=modelSeqNo;
	document.forms[0].frmSubSecSeqNo.value=subSecSeqNo;
}

