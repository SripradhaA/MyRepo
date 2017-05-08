//Falg variable
var clauseDescChangeFlag=0;
/*
*	Name: deleteTable
*	Purpose: Used to Delete the Table in the Screen
*	
*/

function deleteTable()
{
 	rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
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

	document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>								<TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";
	document.getElementById('showmainlink').style.visibility="visible";
	document.getElementById('showsublink').style.visibility="hidden";
				
}
/*
*	Name: RemoveRow
*	Purpose: Used to Remove the selected Row of the table
*	
*/
function removeRow()
{
	var pos;
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
	}					
	
}
            
/*
*	Name: addRow
*	Purpose: Used to Add a row to the table
*	
*/
function addRow(src)
{	
	rows = document.getElementById('tblGrid').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
 
	for (i = 0; i < rows.length; i++)
    {
		index = this.rowIndex + 1;
	    index1 = this.rowIndex ;        

	}


	var newRow = document.all("tblGrid").insertRow(rows.length);

	 
	var oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input class='radcheck' type='radio' name='deleterow'/>&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1' SIZE='15' MAXLENGTH='100'>";
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'>";
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE='15'MAXLENGTH='100'>";	
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE='15'MAXLENGTH='100'>";	
	oCell = newRow.insertCell();
	oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE='15'MAXLENGTH='100'>";	
}
/*
*	Name: addTable
*	Purpose: Used to add a Table
*	
*/
function addTable()	
{
var val ='';
val = "<TABLE WIDTH='98%' BORDER=0  id='tblGrid' style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY><TR>";
val = val+"<TD WIDTH='22%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
val = val+'<INPUT type="text" CLASS="COMMONTEXTBOXTABLE" name="clauseTableDataArray1"  SIZE="13" MAXLENGTH="100"/></TD>&nbsp;';
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray2' SIZE='13'MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray3' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='18%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray4' SIZE=13 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='30%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOXTABLE' type='text' name='clauseTableDataArray5' SIZE=13 MAXLENGTH='100'/></TD></TR>";
val = val+"<TR><TD WIDTH='19%' >&nbsp;&nbsp;<INPUT class='radcheck' type='radio' name='deleterow'  />&nbsp;&nbsp;";
val = val+"<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray1'  SIZE='15' MAXLENGTH='100'/></TD>";	
val = val+"<TD WIDTH='17%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray2' SIZE='15'MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray3' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray4' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"<TD WIDTH='20%' >&nbsp;&nbsp;<INPUT CLASS='COMMONTEXTBOX' type='text' name='clauseTableDataArray5' SIZE=15 MAXLENGTH='100'/></TD>";
val = val+"</TR></TBODY></TABLE>";
document.getElementById('showgrid').innerHTML=val;
document.getElementById('showgrid').style.visibility="visible";
document.getElementById('showsublink').style.visibility="visible";
document.getElementById('showmainlink').style.visibility="hidden";
}

/*
*	Name: LoadClauses
*	Purpose: Used to LoadClause pop-up screen 
*	
*/

function fnLoadClausePopup(){
var ModCluause=document.forms[0];

var ModelSeqNo=ModCluause.modelSeqNo.selectedIndex;
var ModelName=ModCluause.modelSeqNo.options[ModelSeqNo].text;

if (ModCluause.modelSeqNo.options[ModCluause.modelSeqNo.selectedIndex].value =="-1")
   {
 
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
   
    }
	else
	{
	window.open("parentClauseSearchAction.do?method=ParentClauseOpen&ModelName="+ModelName+"&ModelSeqNo="+ModelSeqNo+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=850,height=600"); 
	}

}
/*
*	Name: Search
*	Purpose: Used to call the FetchClauses method in ModelAddClauseRevAction 
*	
*/
function fnSearch(){
clauseDescChangeFlag=0;
var ModClause=document.forms[0];
var mod;

mod = document.getElementById("clauseDesc").innerHTML;
	if (ModClause.modelSeqNo.options[ModClause.modelSeqNo.selectedIndex].value =="-1")
   {
 
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("modelSeqNo",-1);
		return false;
   
    }

	
	if(mod=="&nbsp; " || mod=="" ){
		
		var id = '20';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc",-1);
		return false;
	
	}

	
	if(ModClause.hdPreSelectedModel.value!=ModClause.modelSeqNo.options[ModClause.modelSeqNo.selectedIndex].value){
		

									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                   showScrollErrors("searchButton",-1);
									return false;
	}
	
	
	if((ModClause.clauseSeqNo.value=="") || (ModClause.versionNo.value=="")){
		
		var id = '20';
		hideErrors();
		addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
		return false;
	
	}
	
	document.forms[0].hdSelectedModel.value = document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].text ;
	document.forms[0].clauseDesc.value=mod;
	document.forms[0].action="ModAddClauseRev.do?method=fetchClauses";
	document.forms[0].submit();
	
}

/*
*	Name: LoadAllClauses
*	Purpose: Used to Load LoadAllClausesByModel.jsp and load its initial parameters
*	
*/
function LoadAllClauses(index)
{
	var selectedModel=document.forms[0].modelSeqNo.selectedIndex;
	var selectedModelName= document.forms[0].modelSeqNo.options[selectedModel].text;
	var selectedModelID = document.forms[0].modelSeqNo.options[selectedModel].value;

	if(document.forms[0].modelSeqNo.options[selectedModel].value==-1)
	{
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("modelSeqNo",-1);
	}
	else
	{
	window.open("partOfAction.do?method=insertPartOf&selectedModelName="+selectedModelName+"&textBoxIndex="+index+"&selectedModelID="+selectedModelID+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=600,height=250"); 
	} 
}

/*
*	Name: fetchComponent()
*	Purpose: Used to Delete the Part Of Fields 
*	
*/
function deletePartOfCode(index)
{
document.forms[0].partOfCode[index-1].value="";
document.forms[0].partOfSeqNo[index-1].value="";



}


/*
*	Name: fnLoadPartOfSubSection
*	Purpose: Used to Load Sub Section List on selection of SectionList in PartOf PopUp.
*	
*/

function fnLoadPartOfSubSection()
{
	
	document.forms[0].action="partOfAction.do?method=SubSectionLoad";
	document.forms[0].submit();
	
}


/*
*	Name: fnAddClause
*	Purpose: Used to Add a Clause into the database.
*	
*/
function fnAddClause()
{
var mod = document.forms[0];
var clauseDesc = trim(mod.clauseDescForTextArea.value);
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);
//var reasons=trim(mod.reason.value);
var comments=trim(mod.comments.value)
var refEdl1=trim(mod.refEDLNo[0].value);
var refEdl2=trim(mod.refEDLNo[1].value);
var refEdl3=trim(mod.refEDLNo[2].value);
var refEdl=refEdl1+refEdl2+refEdl3;
var edlNo1=trim(mod.edlNo[0].value);
var edlNo2=trim(mod.edlNo[1].value);
var edlNo3=trim(mod.edlNo[2].value);
var edlNo=edlNo1+edlNo2+edlNo3;
var compSeqNo;
var partOfSeqNo1=trim(mod.partOfCode[0].value);
var partOfSeqNo2=trim(mod.partOfCode[1].value);
var partOfSeqNo3=trim(mod.partOfCode[2].value);
var partOfSeqNo4=trim(mod.partOfCode[3].value);
mod.clauseDescForTextArea.value=clauseDesc;
mod.comments.value=comments;
//mod.reason.value=reasons;
mod.dwONumber.value=dwoNumber;
mod.partNumber.value=partNumber;
mod.priceBookNumber.value=priceBookNumber;
mod.edlNo[0].value=edlNo1;
mod.edlNo[1].value=edlNo2;
mod.edlNo[2].value=edlNo3;
mod.refEDLNo[0].value=refEdl1;
mod.refEDLNo[1].value=refEdl2;
mod.refEDLNo[2].value=refEdl3;
	var isSpecChar1 = SpecialCharacterCheck(partOfSeqNo1);
     if(isSpecChar1){
			partOfSeqNo1="";comments
		   return false;
	 }

	var isSpecChar2 = SpecialCharacterCheck(partOfSeqNo2);
     if(isSpecChar2){
			partOfSeqNo2="";
		   return false;
	 }

	 var isSpecChar3 = SpecialCharacterCheck(partOfSeqNo3);
     if(isSpecChar3){
			partOfSeqNo3="";
		   return false;
	 }

	  var isSpecChar4 = SpecialCharacterCheck(partOfSeqNo4);
		 if(isSpecChar4){
			partOfSeqNo4="";
		   return false;
	 }
	 
	if(clauseDescChangeFlag==1)
	{
			var id = '207';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("searchButton",-1);
			return false;
	}

if(mod.hdPreSelectedModel.value!=mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value){
		

									var id = '207';
                                    hideErrors();
                                    addMsgNum(id);
                                    showScrollErrors("searchButton",-1);
									return false;
	}


if (document.forms[0].modelSeqNo.options[document.forms[0].modelSeqNo.selectedIndex].value =="-1")
{
   
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("modelSeqNo",-1);
	return false;
	   
}


 

 if(clauseDesc.length==0 || clauseDesc=="" )
{
	
		var id = '506';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc",-1);
		return false;
}
/* Commented for CR-121
 if(clauseDesc.length > 4000)
{
	
		var id = '516';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseDesc",-1);
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
		showScrollErrors("clauseTableDataArray",i);
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
		showScrollErrors("clauseTableDataArray",-1);
		return false;
		
}
}
}





if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
		var id = '505';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("standardEquipmentSeqNo",-1);
		return false;
}
else
{




for(i=0;i<document.forms[0].refEDLNo.length;i++){
if(document.forms[0].refEDLNo[i].value != ""){
if(!validateNumber(document.forms[0].refEDLNo[i].value)){
		var id = '515';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("refEDLNo",[i]);
		return;
}
}
}
if ((refEdl1<0 ) && (refEdl2<0||refEdl2.length==0) && (refEdl3<0||refEdl3.length==0))
{
	if (refEdl1!="")
	{			
			var id = '528';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("refEDLNo",0);
		
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
			showScrollErrors("refEDLNo",0);
			return false;
		}
		if (refEdl1==refEdl2||refEdl1==refEdl3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("refEDLNo",0);
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
			showErrors();
		
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
			showScrollErrors("refEDLNo",1);
			return false;
		}
		if (refEdl1==refEdl2||refEdl2==refEdl3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("refEDLNo",1);
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
			showErrors();
		
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
			showScrollErrors("refEDLNo",2);
			return false;
		}
		if (refEdl2==refEdl3||refEdl1==refEdl3)
		{
			var id = '510';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("refEDLNo",2);
			return false;

		}

	}

for(i=0;i<document.forms[0].edlNo.length;i++){
if(document.forms[0].edlNo[i].value != ""){
if(!validateNumber(document.forms[0].edlNo[i].value)){

		var id = '514';
		hideErrors();
		addMsgNum(id);
		showErrors();
		document.forms[0].edlNo[i].focus();
		return;
}
}
}
if ((edlNo1<0 ) && (edlNo2<0||edlNo2.length==0) && (edlNo3<0||edlNo3.length==0))
{
	if (edlNo1!="")
	{			
			var id = '529';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("edlNo",0);
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
			showScrollErrors("edlNo",0);
			return false;
		}
		if (edlNo1==edlNo2||edlNo1==edlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
		showScrollErrors("edlNo",0);				
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
			showScrollErrors("edlNo",1);
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
			showScrollErrors("edlNo",1);
			return false;
		}
		if (edlNo1==edlNo2||edlNo2==edlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("edlNo",1);	
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
			showScrollErrors("edlNo",2);
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
			showScrollErrors("edlNo",2);
			return false;
		}
		if (edlNo2==edlNo3||edlNo1==edlNo3)
		{
			var id = '509';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("edlNo",2);		
			return false;

		}

	}
	

	
	if (partOfSeqNo1.length!=0 && partOfSeqNo1>0 && partOfSeqNo1!="") 
	{
		
		if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfCode",0);					
			return false;


		}

	}

	if (partOfSeqNo2.length!=0 && partOfSeqNo2>0 && partOfSeqNo2!="") 
	{
		
		if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfCode",1);			
			return false;


		}

	}
	if (partOfSeqNo3.length!=0 && partOfSeqNo3>0 && partOfSeqNo3!="") 
	{
		
		if (partOfSeqNo3==partOfSeqNo1||partOfSeqNo3==partOfSeqNo2||partOfSeqNo3==partOfSeqNo4)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfCode",2);		
			return false;

		}

	}
	if (partOfSeqNo4.length!=0 && partOfSeqNo4>0 && partOfSeqNo4!="") 
	{
		
		if (partOfSeqNo1==partOfSeqNo4||partOfSeqNo2==partOfSeqNo4||partOfSeqNo3==partOfSeqNo4)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfCode",3);			
			return false;

		}

	}
	
	

if(dwoNumber.length!=0 || dwoNumber!="" )
{
	if(!validateNumber(dwoNumber))
	{

		var id = '508';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("dwONumber",-1);
		return false;
	}
}
if (dwoNumber<=0 && dwoNumber.length!=0 )
{
	var id = '530';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("dwONumber",-1);
	return false;
}
if(partNumber.length!=0 || partNumber!="" )
{
	if(!validateNumber(partNumber))
	{

		var id = '512';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partNumber",-1);
		return false;
	}
}

if (partNumber<=0 && partNumber.length!=0)
{
	var id = '531';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partNumber",-1);
		return false;
}
if(priceBookNumber.length!=0 || priceBookNumber!="" )
{
	if(!validateNumber(priceBookNumber))
	{

		var id = '513';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("priceBookNumber",-1);
		return false;
	}
}

if (priceBookNumber<=0 && priceBookNumber.length!=0)
{
		var id = '532';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("priceBookNumber",-1);
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
}

/*if(reasons.length==0 || reasons=="" )
{
		var id = '507';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		
		mod.reason.select();
		mod.reason.focus();
		return false;
}

 if(reasons.length > 2000)
{
	
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		
		mod.reason.select();
		mod.reason.focus();
		return false;
}*/




document.forms[0].action="ModAddClauseRev.do?method=insertClause";
document.getElementById("clauseDesc").innerHTML="";
document.forms[0].submit();

}


/*
*	Name: LoadClauseDesc
*	Purpose:Used to Load the popup screen
*	
*/
function LoadClauseDesc()
{
clauseDescChangeFlag=1;
var selectedModel=document.forms[0].modelSeqNo.selectedIndex;
var selectedModelName= document.forms[0].modelSeqNo.options[selectedModel].text;
var selectedModelID = document.forms[0].modelSeqNo.options[selectedModel].value;
if(document.forms[0].modelSeqNo.options[selectedModel].value==-1)
{	
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("modelSeqNo",-1);
}
else
{
window.open("modelClauseDescPopupAction.do?method=initLoad&selectedModelName="+selectedModelName+"&selectedModelID="+selectedModelID+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=700,height=600"); 
}

}