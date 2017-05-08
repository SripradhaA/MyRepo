/* Added for LSDB_CR_46 PM&I Change */

function fetchModels(){
	
	document.forms[0].action="modelAddClauseAction.do?method=fetchModels";
	document.forms[0].submit();

}



/*
*	Name: AddComponent
*	Purpose: Used to Load Componentcharacterization.jsp and load its initial parameters
*	
*/
function AddComponent()
{	
    
	
	if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value =="-1")
	{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		
   
	}
	
	else if (document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value =="-1")
	{
	   
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
		   
	}else{
	   var selectedModelID = document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value;
	   //Updated width for CR_81
	   window.open("compSearchAction.do?method=initLoad&selectedModelID="+selectedModelID,"Clause","location=0,resizable=no ,status=0,scrollbars=1,width=850,height=500"); 
	
	}
}
/*
*	Name: deleteTable
*	Purpose: Used to Delete the Table in the Screen
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
	}	  document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>								<TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";*/
	document.getElementById('showmainlink').style.visibility="hidden";
	document.getElementById('showsublink').style.visibility="visible";
	}
}
/*
*	Name: RemoveRow
*	Purpose: Used to Remove the selected Row of the table
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
*	Name: addRow
*	Purpose: Used to Add a row to the table
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
}
/*
*	Name: addTable
*	Purpose: Used to add a Table
*	
*/
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
/*
*	Name: LoadParentClauses
*	Purpose: Used to Load LoadParentClauseByModel.jsp and load its initial parameters
*	
*/
function LoadParentClauses()
{

	var selectedArray=new Array();	
	var selectedModelID=document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value;
	var selectedSectionID=document.forms['ModelAddClauseForm'].sectionSeqNo.options[document.forms['ModelAddClauseForm'].sectionSeqNo.selectedIndex].value;
	//var	selectedSubSectionID=document.forms['ModelAddClauseForm'].subSectionSeqNo.options[document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex].value;
	var subSecID=document.forms['ModelAddClauseForm'].subSectionSeqNo.options[document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex].value;
	var selectedModelName= document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].text;
	var selectedSectionName=document.forms['ModelAddClauseForm'].sectionSeqNo.options[document.forms['ModelAddClauseForm'].sectionSeqNo.selectedIndex].text;
	var selectedSubSectionName=document.forms['ModelAddClauseForm'].subSectionSeqNo.options[document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex].text;
	
	//Added for LSDB_CR_46 PM&I Change
	var selIndex = document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex;
	var specType = document.forms['ModelAddClauseForm'].specTypeNo.options[selIndex].text;
	

	//Added for LSDB_CR_46 PM&I

	if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value =="-1")
	{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		
   
	}else if (document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value =="-1"){
	
		var id = '19';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltModel",-1);
	}else if (document.forms['ModelAddClauseForm'].sectionSeqNo.options[document.forms['ModelAddClauseForm'].sectionSeqNo.selectedIndex].value =="-1"){
	
		var id = '205';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSection",-1);
		   
	}

	/*
	*	SectionSeqNo and SubSectionSeqNo are passed as a request parameters 
	*	Added For LSDB_CR-50
	*   Added on 26-June-08
	*	by ps57222
	*	
	*/

	else if(document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex==0)
	{
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	
	}
	else
	{
	window.open("parentClauseSearchAction.do?method=ParentClauseOpen&selectedModelName="+escape(selectedModelName)+"&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedSubSectionID="+subSecID+"&selectedSectionID="+selectedSectionID+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600"); 
	}
}

/*
*	Name: DeleteParentClause
*	Purpose: Used to delete Parent Clause Description field data.
*	
*/
function deleteParentClause()
{
if (document.forms[0].parentClauseSeqNo.value!=0)
{

	
	var del=confirm("Are you sure to Clear the Clause");
	if(del == true){
    document.getElementById("parentclause").innerHTML = "<TD> &nbsp;</TD>"
    document.forms[0].parentClauseSeqNo.value=0;
    }
  
}
}


/*
*	Name: fnLoadPartOfSubSection
*	Purpose: Used to Load Sub Section List on selection of SectionList in PartOf PopUp.
*	
*/

function fnLoadPartOfSubSection()
{
	
	document.forms['PartOfForm'].action="partOfAction.do?method=SubSectionLoad";
	document.forms['PartOfForm'].submit();
	
}

/*
*	Name: fnLoadSubSection
*	Purpose: Used to Load Sub Section List on selection of SectionList
*	
*/
function fnLoadSubSection()
{
document.forms['ModelAddClauseForm'].action="modelAddClauseAction.do?method=SubSectionLoad";
document.forms['ModelAddClauseForm'].submit();
}
/*
*	Name: fnLoadSection
*	Purpose: Used to Load Section List on selection of ModelList
*	
*/
function fnLoadSection()
{
document.forms['ModelAddClauseForm'].action="modelAddClauseAction.do?method=SectionLoad";
document.forms['ModelAddClauseForm'].submit();
}


/*
*	Name: trim
*	Purpose: Used to trim the text.
*	
*/

function trim(sValue) 
{
	
	var s=sValue; 
	return ltrim(rtrim(s));
	
}
	
/*
*	Name: ltrim
*	Purpose: Used to trim the text from the left side.
*	
*/
function ltrim(sValue) 
{
	
	var s=sValue;	

	return s.replace(/^ */, "");
}

/*
*	Name: rtrim
*	Purpose: Used to trim the text from the right side.
*	
*/
function rtrim(sValue) {
	
	var s=sValue; 
	return s.replace(/ *$/, "");
}

/*
*	Name: fnAddClause
*	Purpose: Used to Add a Clause into the database.
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*
*/
function fnAddClause()
{
var mod = document.forms['ModelAddClauseForm'];
var clauseDesc = trim($('#clauseDesc_id').val());//var clauseDesc = trim(mod.clauseDesc.value);Replaced this with jQuery to avoid discrepancies, CR_99
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);

//var reasons=trim(mod.reason.value);
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
mod.clauseDesc.value=clauseDesc;
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

var partOfclaSeqNo1=trim(mod.partOfclaSeqNo[0].value);
var partOfclaSeqNo2=trim(mod.partOfclaSeqNo[1].value);
var partOfclaSeqNo3=trim(mod.partOfclaSeqNo[2].value);
var partOfclaSeqNo4=trim(mod.partOfclaSeqNo[3].value);	

//Added for LSDB_CR_46 PM&I Change

if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
}


if (document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value =="-1")
{
   
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltModel",-1);
	return false;
	   
}

if (mod.sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
	return false;
}

if (mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
	return false;
	   
}

if (mod.subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	return false;
}
if (mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	return false;
	   
}
 //CR 88
 if (mod.leadComponentSeqNo.options[mod.leadComponentSeqNo.selectedIndex].value !="-1")
{	

if(mod.hdnCombntnSeqNoArr.value!=""){
		var id = '900';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("compGroupList",-1);
		return false;
	}
}

if (!(mod.compGroupSeqNo.options[mod.compGroupSeqNo.selectedIndex].value =="-1"))
{	
	if (mod.leadComponentSeqNo.options[mod.leadComponentSeqNo.selectedIndex].value =="-1")
	{
	var id = '789';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("compGroupList",-1);
	return false;
	}  
}

/*
 if(clauseDesc.length==0 || clauseDesc=="" )
{
	
		var id = '506';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		mod.clauseDesc.value="";
		mod.clauseDesc.select();
		mod.clauseDesc.focus();
		return false;
}

 if(clauseDesc.length > 4000)
{
	
		var id = '516';
		hideErrors();
		addMsgNum(id);
		showErrors();
		
		//mod.clauseDesc.value="";
		mod.clauseDesc.select();
		mod.clauseDesc.focus();
		return false;
}*/

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

if (mod.componentfrom.options.length!=0)
{    	
   if(mod.hdnCombntnSeqNoArr.value!=""){
		var id = '900';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("componentfrom",-1);
		return false;
	}
}
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
if (document.forms[0].clauseTableDataArray1.length!=undefined)
{

for (var i=0;i<document.forms[0].clauseTableDataArray1.length;i++)
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


	

if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
	//if condition added for CR-131 to make error display appropriately for CGC
	if ($('#sltCGCFlag').prop('checked')){
		var id = '505';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfCode",0);
		return false;
	}else{
		var id = '505';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("refEDLNo",0);
		return false;
	}
}
else
{


/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/

/* for(i=0;i<document.forms[0].refEDLNo.length;i++){
if(document.forms[0].refEDLNo[i].value != ""){
if(!validateNumber(document.forms[0].refEDLNo[i].value)){
		var id = '515';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		document.forms[0].refEDLNo[i].focus();
		return;
}
}
} */

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
			showScrollErrors("refEDLNo",1);
		
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
			showScrollErrors("refEDLNo",2);
		
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
	
/**
	Code commented as per the client requirement.
	To make RefEDL and EDL number as a alpha numeric field.
	Modified on 29-09-08
	by ps57222.
**/


/* for(i=0;i<document.forms[0].edlNo.length;i++){
if(document.forms[0].edlNo[i].value != ""){
if(!validateNumber(document.forms[0].edlNo[i].value)){

		var id = '514';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		document.forms[0].edlNo[i].focus();
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
	
if (partOfSeqNo1.length!=0 ||partOfSeqNo1!=0)
{
//Added For CR_97 to avoid part of mapping to subsection
if(partOfclaSeqNo1.length==0 || partOfclaSeqNo1==0 || partOfclaSeqNo1=="" || partOfclaSeqNo1=="null")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfclaSeqNo",0);
			return false;
		}
		
if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",0);
return false;

}

}

if (partOfSeqNo2.length!=0 ||partOfSeqNo2!=0)
{
//Added For CR_97 to avoid part of mapping to subsection
if(partOfclaSeqNo2.length==0 || partOfclaSeqNo2==0 || partOfclaSeqNo2=="" || partOfclaSeqNo2=="null")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfclaSeqNo",1);
			return false;
		}

if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",1);
return false;

}

}
if (partOfSeqNo3.length!=0 ||partOfSeqNo3!=0)
{
//Added For CR_97 to avoid part of mapping to subsection
if(partOfclaSeqNo3.length==0 || partOfclaSeqNo3==0 || partOfclaSeqNo3=="" || partOfclaSeqNo3=="null")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfclaSeqNo",2);
			return false;
		}


if (partOfSeqNo2==partOfSeqNo3||partOfSeqNo1==partOfSeqNo3||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",2);
return false;

}

}
if (partOfSeqNo4.length!=0 ||partOfSeqNo4!=0)
{
//Added For CR_97 to avoid part of mapping to subsection
if(partOfclaSeqNo4.length==0 || partOfclaSeqNo4==0 || partOfclaSeqNo4=="" || partOfclaSeqNo4=="null")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOfclaSeqNo",3);
			return false;
		}
		
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

/* if(reasons.length==0 || reasons=="" )
{
		var id = '507';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		
		
		mod.reason.select();
		mod.reason.focus();
		return false;
}

 if(reasons.length > 2000)
{
	
		var id = '518';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("subSectionSeqNo",-1);
		
		
		mod.reason.select();
		mod.reason.focus();
		return false;
}*/

//Added to solve Components tied to clause Issue
document.forms['ModelAddClauseForm'].componentSeqNo.value="";
for(var i=0;i<document.forms['ModelAddClauseForm'].componentfrom.options.length; i++ ){

compSeqNo = document.forms['ModelAddClauseForm'].componentfrom.options[i].value;
document.forms['ModelAddClauseForm'].componentSeqNo.value=document.forms['ModelAddClauseForm'].componentSeqNo.value+compSeqNo+"~";
}


/* Added For Attach Clause CR **********/
if(document.forms['ModelAddClauseForm'].hdncomponentGroupSeqNo.value!=undefined){

	var hdnCompSeqNo=document.forms['ModelAddClauseForm'].hdncomponentGroupSeqNo.value;
	var splitCompGrpSeqNo=hdnCompSeqNo.split("~");
	
	for(var i=0;i<splitCompGrpSeqNo.length-1;i++){
		
		if(document.forms['ModelAddClauseForm'].compGroupSeqNo.value==splitCompGrpSeqNo[i]){
			
				var id = '788';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("sltLeadCompGrp",-1);
				return false;
		}
	}

}
//Added for CR-121
var subClaExistsFlag=document.forms[0].leadSubClaExistsFlag.value;
if(subClaExistsFlag == "Y"){
	confirmDialog(clauseDesc);
}else{
	//Added and Commented for CR_99 to remove junk characters by Validating the Clause Description through AJAX
	var url="modelAddClauseAction.do?method=insertClause";
	fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
}
//Added for CR-121 Ends here

//document.forms['ModelAddClauseForm'].action="modelAddClauseAction.do?method=insertClause";
//document.forms['ModelAddClauseForm'].submit();Commented both lines and kept in a new function fnInsertClause for CR_99

}

/*
*	Name: validateNumber
*	Purpose: Used to check if the details entered are Numeric.
*	
*/
function validateNumber(fieldVal)
{	
	num =  /^-?\d+$/;
	if(!num.test(fieldVal))
	{
		return false;
	}
	else
	{
	return true;
	}
}

/*
*	Name: Cancelpopup
*	Purpose:Used to Close the pop up window
*	
*/
function Cancelpopup()
{
window.close();
}

/*
*	Name: fnAddPart
*	Purpose: Used to Add PartOf details
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*/
function fnAddPart()
{
var selectedSubSection=document.forms['PartOfForm'].subSectionSeqNo.selectedIndex;

if (document.forms['PartOfForm'].sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);
	return false;
}
if (document.forms['PartOfForm'].sectionSeqNo.options[document.forms['PartOfForm'].sectionSeqNo.selectedIndex].value==-1)
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);	
	return false;
}

if (document.forms['PartOfForm'].subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);
	return false;
}

if (document.forms['PartOfForm'].subSectionSeqNo.options[selectedSubSection].value==-1)
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);	
	return false;
}

selectFlag=false;

var cnt= document.forms[0].clauseSeqNo.length;

if(cnt>0){
for(var i=0;i<cnt;i++){
if(document.forms[0].clauseSeqNo[i].checked){
document.forms[0].hdnClauseSeqNo.value=document.forms[0].clauseSeqNo[i].value;
document.forms[0].hdnClauseNo.value=document.forms[0].clauseNum[i].value;

selectFlag=true;
break;

}
}
}else{
if(document.forms[0].clauseSeqNo.checked){
document.forms[0].hdnClauseSeqNo.value=document.forms[0].clauseSeqNo.value;
document.forms[0].hdnClauseNo.value=document.forms[0].clauseNum.value;
selectFlag=true;
}
}

if(selectFlag){
index = document.forms['PartOfForm'].textBoxIndex.value;
var Subsecseqno=document.forms[0].subSectionSeqNo.options[document.forms[0].subSectionSeqNo.selectedIndex].value;
window.opener.document.forms[0].partOfCode[index-1].value = document.forms[0].hdnClauseNo.value;
window.opener.document.forms[0].partOfSeqNo[index-1].value = Subsecseqno;
window.opener.document.forms[0].partOfclaSeqNo[index-1].value=document.forms[0].hdnClauseSeqNo.value;
window.close();
}else{
var id = '227';
hideErrors();
addMsgNum(id);
showScrollErrors("clauseSeqNo",-1);

}
}

/*
*	Name: LoadAllClauses
*	Purpose: Used to Load LoadAllClausesByModel.jsp and load its initial parameters
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*
*/
function LoadAllClauses(index)
{

	var selectedModel=document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex;
	var selectedModelName= document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].text;
	var selectedModelID = document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value;
	//Added for LSDB_CR_46 PM&I Change
	var selIndex = document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex;
	var specType = document.forms['ModelAddClauseForm'].specTypeNo.options[selIndex].text;

	
	if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value =="-1")
	{
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		
   
	}
	else if(document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value==-1)
	{	
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltModel",-1);	
	}
	else
	{
	window.open("partOfAction.do?method=insertPartOf&selectedModelName="+escape(selectedModelName)+"&textBoxIndex="+index+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"","Clause","location=0,resizable=yes,status=0,scrollbars=1,width=800,height=500"); 
	}
}

/*
*	Name: deletePartOfCode()
*	Purpose: Used to Delete the Part Of Fields 
*	Modified for LSDB_CR-48[Part Of CR] on 04-Aug-08 by ps57222
*
*/
function deletePartOfCode(index)
{
document.forms['ModelAddClauseForm'].partOfCode[index-1].value="";
document.forms['ModelAddClauseForm'].partOfSeqNo[index-1].value="";
document.forms['ModelAddClauseForm'].partOfclaSeqNo[index-1].value="";

}

/*
*	Name: fetchComponent()
*	Purpose: Used to fetch the Components 
*	
*/
function fetchComponent() 
{
//Modified for CR_81 by RR68151
//var selectedOption=document.forms['CompSearchForm'].characterisationFlag.selectedIndex;
/*  Commented for CR-114
        var selectedOption=document.forms['CompSearchForm'].compGroupTypeSeqNo.selectedIndex;
		if(document.forms['CompSearchForm'].compGroupTypeSeqNo.options[selectedOption].value==-1)
		{
			var id = '523';
 			hideErrors();
 			addMsgNum(id);
 			showScrollErrors("subSectionSeqNo",-1);	
		}
		else
		{*/
		document.forms['CompSearchForm'].action="compSearchAction.do?method=fetchComps";		
		document.forms['CompSearchForm'].submit();
		//}Commented for CR-114
}
/*
*	Name: fnCancel
*	Purpose: Used to Close the windows opened from the page
*	
*/
function fnCancel()
{
  window.close();
}
/*
*	Name: fnComponentSubmit
*	Purpose: Used to Submit the component after selection
*	Form Name is changed to reuse the component pop-up for CRForm CR by ps57222
*/

function fnComponentSubmit()
{

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
			  
			  msg=msg+": "+hiddencomponentNameArray[cnt];
	          
	          }else{
	          
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
var existList=window.opener.document.forms[0].componentfrom.options.length;
var count=0;
if (window.opener.document.forms[0].componentfrom.options.length!=0)
{    	
    window.opener.document.forms[0].componentfrom.options.length=0;   
}


if (existList!=0)
{
existList=0;
}
//Modified for CR_81 by RR68151
if(document.forms['CompSearchForm'].compGroupTypeSeqNo.options[document.forms['CompSearchForm'].compGroupTypeSeqNo.selectedIndex].value==-1)
{
var id = '523';
hideErrors();
addMsgNum(id);
showScrollErrors("compGroupTypeSeqNo",-1);
return false;	
}


if (document.forms['CompSearchForm'].componentSeqNo==null)
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
	
if (document.forms['CompSearchForm'].componentSeqNo[i].checked)
{

  hiddencomponentArray[count]=document.forms['CompSearchForm'].componentName[i].value;
  hiddencomponentSeqNo[count] = document.forms['CompSearchForm'].componentSeqNo[i].value;
  hiddencomponentGrpSeqNo[count]=document.forms['CompSearchForm'].componentGroupSeqNo[i].value;
  count++; 
}
}
}
else
{
	if(document.forms['CompSearchForm'].componentSeqNo.checked)
	{
		hiddencomponentArray[count]=document.forms['CompSearchForm'].componentName.value;
  		hiddencomponentSeqNo[count] = document.forms['CompSearchForm'].componentSeqNo.value;
		hiddencomponentGrpSeqNo[count]=document.forms['CompSearchForm'].componentGroupSeqNo.value;
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
window.opener.fnlocalSubmit(hiddencomponentArray,hiddencomponentSeqNo,hiddencomponentGrpSeqNo);
window.close();
}
}

/*
*	Name: SelectParentClauses
*	Purpose: Used to Submit the Chosen Parent Clause.
*	
*/

function SelectParentClauses()
{
var selectedClause=document.forms['ParentClauseForm'].clauseSeqNo;
var selectedClauseDesc;
var flag = false;
var tableData= new Array();
if (selectedClause==null)
{
	var id = '16';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseSeqNo",-1);
	return false;
}
else
{
if (selectedClause.length!=undefined)
{
	for (i=0;i<selectedClause.length;i++)
	{
		
		if (selectedClause[i].checked)
		{
  			selectedClauseDesc= document.forms['ParentClauseForm'].clauseDescTmp[i].value;
  			tableData=document.forms['ParentClauseForm'].tableArrayData1Tmp[i].value; 						
  			window.opener.document.forms['ModelAddClauseForm'].parentClauseSeqNo.value=selectedClause[i].value;   				
  			flag = true;
  			break;
		}
	}
}
else
{
	if(selectedClause.checked)
	{
		selectedClauseDesc= document.forms['ParentClauseForm'].clauseDescTmp.value;		
		tableData=document.forms['ParentClauseForm'].tableArrayData1Tmp.value; 		
		window.opener.document.forms['ModelAddClauseForm'].parentClauseSeqNo.value=selectedClause.value; 				
		flag = true;		
	}
}
}
	if(flag){		
	var arr = new Array(tableData);	
	if(selectedClause.length!=undefined && selectedClause.length > 1)
		window.opener.document.getElementById("parentclause").innerHTML =document.getElementById("clauseID"+i).innerHTML;
	else	
		window.opener.document.getElementById("parentclause").innerHTML =document.getElementById("clauseID0").innerHTML;	
	window.close();
	}else{
	
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);			
	}
	
	
}
 
/*
*  These Two Functions are Added for Attach to Clause Change Request 
*
*/

/*
*	Name: fnLoadCompGroup
*	Purpose: Used to Load ComponentGroup List on selection of SubSectionList
*	
*/
function fnLoadCompGroup(flag)
{
	//Added For CR_88
	if (document.forms[0].modifyClauseFlag.value == "N" && flag == "N")	{
		fnLoadWithOutChildClause();
		}
	else	{
		document.forms[0].childFlag.value = "Y";
		document.forms[0].action="modelAddClauseAction.do?method=loadComponentGroup";
		document.forms[0].submit();
		}
}


/*
*	Name: fnLoadComponents
*	Purpose: Used to Load Component List on selection of ComponentGroupList
*	
*/
function fnLoadComponents()
{
$('#clauseDesc_id').val("");
document.forms[0].action="modelAddClauseAction.do?method=loadComponent";
document.forms[0].submit();
}



/*
*	Name: fnLoadClauses
*	Purpose: Used to Load Clauses for selected subsection
*	Added For LSDB_CR-48[Part Of CR] on 30-July-08 by ps57222
*/


function fnLoadClauses()
{

if (document.forms['PartOfForm'].sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);
	return false;
}
if (document.forms['PartOfForm'].sectionSeqNo.options[document.forms['PartOfForm'].sectionSeqNo.selectedIndex].value==-1)
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sectionSeqNo",-1);	
	return false;
}

if (document.forms['PartOfForm'].subSectionSeqNo.options[document.forms['PartOfForm'].subSectionSeqNo.selectedIndex].value==-1)
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("subSectionSeqNo",-1);	
	return false;
}



document.forms[0].action="partOfAction.do?method=loadClauses";
document.forms[0].submit();
}

/*
*	Name: fnCharacteristicCompGroup
*	Purpose: Used to disabled the fileds if Characteristic Group selected
*	Added For LSDB_CR-81 on 24-Dec-09 by sd41630
*/

function fnCharacteristicCompGroup(){
var compGroupform = document.forms['ModelAddClauseForm'];
var compGroup = document.forms[0];
var refEdlLen = compGroupform.refEDLNo.length;
var edlLen = compGroupform.edlNo.length;
var count=0;

if(compGroup.selectCGCFlag.checked)	{
//Added For CR_97
$('#viewLeadClauses').hide();
compGroupform.compGroupSeqNo.selectedIndex = 0;
compGroupform.compGroupSeqNo.selectedValue = "-1";

compGroupform.leadComponentSeqNo.selectedIndex = 0;
compGroupform.leadComponentSeqNo.selectedValue = "-1";

compGroupform.compGroupSeqNo.disabled=true;
compGroupform.leadComponentSeqNo.disabled=true;
//Added For CR_97
var select=$("select#compGroupList");
$('>option', select).remove();
var options = '<option selected value="-1">---Select---</option>';
select.html(options);

    

for(var i = 0 ; i < edlLen; i++){
	compGroupform.edlNo[i].value="";
	compGroupform.edlNo[i].disabled=true;
	
		}
		
	for(var i = 0 ; i < refEdlLen; i++){
	compGroupform.refEDLNo[i].value="";
	
	compGroupform.refEDLNo[i].disabled=true;
	
				}
		
	
    if (document.forms[0].componentfrom.options.length!=0)
    {
    
	compGroup.componentfrom.options.length=0;
	compGroup.hdncomponentGroupSeqNo.value="";
	compGroup.componentSeqNo.value="";
      
    }
	
document.getElementById("addCompToCla").onclick = function() {
		        return false;
		        
		      };
		      // added for CR_85
		      
		      if (document.forms[0].chrstcEdl.options.length!=0)
				    {
				    document.forms[0].chrstcEdl.options.length=0;
					document.forms[0].hdnCombntnSeqNo.value="";
					document.forms[0].hdnCharClaSeqNo.value="";	   
				    }
			//Added for CR-118	    
			if($('#sltSpecType').val()!= 2)	{	    
			     document.getElementById("addEdlToCla").disabled=true;
			      document.getElementById("addEdlToCla").onclick = function() {	       
			        return false;    
			     	 };
			      document.getElementById("viewChrCombntn").disabled=true;
			      document.getElementById("viewChrCombntn").onclick = function() {
			        return false;  
			     	};
			}
		}  

else{
compGroupform.compGroupSeqNo.disabled=false;
compGroupform.leadComponentSeqNo.disabled=false;

	for(var i = 0 ; i < refEdlLen; i++){
	compGroupform.refEDLNo[i].disabled=false;
				
			}
		
			for(var i = 0 ; i < edlLen; i++){
			compGroupform.edlNo[i].disabled=false;
			
				}
		
document.getElementById("addCompToCla").onclick = function() {
		        return true;
		      };
		      //Added for CR_85
		      //Added for CR-118
              if($('#sltSpecType').val()== 2)	{		      
			       document.getElementById("addEdlToCla").disabled=false;
			       document.getElementById("addEdlToCla").onclick = function() {
			        return true;   
			      };
			      document.getElementById("viewChrCombntn").disabled=false;
			      document.getElementById("viewChrCombntn").onclick = function() {
			        return true;  
			      };
              }
}
	//CR 88
	compGroup.hdnCombntnSeqNoArr.value="";

}

/*
*	Name: fnLoadAddModifyPage
*	Purpose: Used to traverse to Mdd/Modify Page
*	Added For LSDB_CR-83 on 12-Feb-10 by RR68151
*/

function fnLoadAddModifyPage(modifyFlag){

var mod = document.forms[0];
selectClause=false;

if (mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value =="-1")
{
	
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);

}

else if (mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value =="-1")
{
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltModel",-1);	   
}

else if (mod.sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
}

else if (mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
		   
}

else if (mod.subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
}
else if (mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);	   
}

else if (modifyFlag == "Y")	{

	if(mod.modifyClauseFlag.value != "Y"){
		
		if(mod.clauseSeqNo != null){
			
				if(mod.clauseSeqNo.length>1){
			
			  	 for(i=0;i<mod.clauseSeqNo.length;i++){	
			  	 	if (mod.clauseSeqNo[i].disabled == false)	{		     
						 selectClause = true;
					   		}
					else{	     
						  mod.clauseSeqNo[i].disabled = false;
						  mod.clauseSeqNo[i].checked = false;
						  }					
						}
					}
					else{
					if (mod.clauseSeqNo.disabled == false)	{
					     selectClause = true;
					     } 
					     else{
					     mod.clauseSeqNo.disabled = false;
					     mod.clauseSeqNo.checked = false;
					     }
					}
			if (document.getElementById('divAddModify').style.display == "none")
			{
				document.getElementById('divAddModify').style.display = "block";
			}
			if (mod.childFlag.value == "Y"){	
				document.getElementById('AddClause1').style.display = "none";
				document.getElementById('AddClause2').style.display = "none";
				}
			else{
				document.getElementById('RearrangeClause').style.display = "none";
				}
			}
			
		else{		
			var id = '888';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("clauseSeqNo",-1);		
			}
		}
	else{
		if(mod.clauseSeqNo == null){
			var id = '888';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("clauseSeqNo",-1);
			}
		else	{
			if(mod.clauseSeqNo.length>1){
			
			  	 for(i=0;i<mod.clauseSeqNo.length;i++){
			  	 
			  	 	if (mod.clauseSeqNo[i].checked == true)	{		     
						 selectClause = false;
						 break;
					   		}
						}
					}else{
					 
					 if (mod.clauseSeqNo.checked == true)	{
					     selectClause = false;
					     }  	
					}
			if (document.getElementById('divAddModify').style.display == "none")
				document.getElementById('divAddModify').style.display = "block";
				}
			}			
	}	
else if (modifyFlag == "N")	{
	if(mod.modifyClauseFlag.value == "Y" || mod.childFlag.value=="N" ){
	
		mod.modifyClauseFlag.value="N";
		
		if(mod.clauseSeqNo != null){	
		
			if(mod.clauseSeqNo.length>1){
			
			  	 for(i=0;i<mod.clauseSeqNo.length;i++){	
						 mod.clauseSeqNo[i].checked = false; 
						}
					}else{
					     mod.clauseSeqNo.checked = false;
					}
			}
		fnLoadCompGroup('Y');
		}
	else if (modifyFlag == "N"){
			if(mod.clauseSeqNo != null){
			if (document.getElementById('divAddModify').style.display == "none")
				document.getElementById('divAddModify').style.display = "none";
			if(mod.clauseSeqNo.length>1){
			
			  	 for(i=0;i<mod.clauseSeqNo.length;i++){	
			  	 		 mod.clauseSeqNo[i].disabled = true;
						 mod.clauseSeqNo[i].checked = false; 
						}
					}else{
						 mod.clauseSeqNo.disabled = true;
					     mod.clauseSeqNo.checked = false;
					}
			}
		document.getElementById('AddClause1').style.display = "block";
		document.getElementById('AddClause2').style.display = "block";
		}
	}

if (selectClause){

 	  var id = '887';
	  hideErrors();
	  addMsgNum(id);
	  showScrollErrors("divAddModify",-1);	
	}
}

/*
*	Name: fnFetchClauseversions
*	Purpose: Used to fetch Clause Versions for the selected Clause
*	Added For LSDB_CR-83 on 12-Feb-10 by RR68151
*/

function fnFetchClauseversions(){

var ModClause=document.forms[0];

if(document.forms[0].clauseSeqNo.length>1){

  	 for(i=0;i<document.forms[0].clauseSeqNo.length;i++){
     
			if(document.forms[0].clauseSeqNo[i].checked){
				ModClause.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo[i].value;		
				}

		   }
		
		}else{
		
		    if(document.forms[0].clauseSeqNo.checked){
				ModClause.hdnClauseSeqNo.value = document.forms[0].clauseSeqNo.value;
				}

		}

document.forms[0].action="modelAddClauseAction.do?method=fetchClauseVersions";
document.forms[0].submit();
}

/*
*  Name: fnApplyDefault()
*  Purpose: Used to Apply the selected Clause version as default.
*
*/

function fnApplyDefault(){

var no=document.forms[0].versionNo.length;

/* Codes Changed for LSDB_CR-49
* Changed on 15-July-08 by ps57222
*/
selectFlag=false;


if(document.forms[0].versionNo.length > 0){
var cnt=document.forms[0].versionNo.length;

for(var i=0;i<cnt;i++){
if(document.forms[0].versionNo[i].checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
selectFlag=true;
break;

}
}
}else{
if(document.forms[0].versionNo.checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
selectFlag=true;
}
}
if(selectFlag){
var del=confirm("Are you sure to apply this clause version as default ?");
if(del == true){
//Added for CR-109
//Modified for CR_114
if(document.forms[0].appendixExitsFlag.value == "Y"){
	//alert("Appendix image mapping has been broken." +"\n"+ "Kindly re-map the clause from Appendix Image Screen.");
	//Updated for CR-118
	   	var appendixMapCheck = confirm("Appendix image mapping will be removed. " +
			    			"Do you want to re-map the appendix image to this new clause version?"
			    			+"\n"+"\n"+"Ok - Changes the version of the clause and re-maps the appendix image"
			    			+"\n"+"Cancel - Doesn't re-map the appendix image to the new version");
    	if(appendixMapCheck){
    		document.forms[0].mapAppendixImg.value=1;
    	}
	
}
//Modified for CR_114 Ends here
//Added for CR-109 Ends here  
document.forms[0].action="modelAddClauseAction.do?method=updateApplyDefaultClause";
document.forms[0].submit();

}else{
}
}else{
var id = '889';
hideErrors();
addMsgNum(id);
showScrollErrors("versionNoRadio",-1);
}
}

/*
* Name: deleteClause()
* Purpose: Used to Delete the clause and all it's subclause and also the versions of it.
*
*/
function fnDeleteClause()
{
var flag = window.confirm("Are you sure to delete the Clause, Sub Clauses and all its versions ?");

if(flag){
document.forms[0].action="modelAddClauseAction.do?method=deleteClause";
document.forms[0].submit();
}else{
}
}


/*
*  Name: fnDeleteVersion()
*  Purpose: Used to Delete the selected version of a clause.
*
*/
function fnDeleteVersion()
{

selectFlag=false;
var cnt=document.forms[0].versionNo.length;
if(cnt!=undefined){
if(document.forms[0].versionNo.length > 0){
for(var i=0;i<cnt;i++){
if(document.forms[0].versionNo[i].checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo[i].value;
selectFlag=true;
break;

}
}
}
}else{
if(document.forms[0].versionNo.checked){
document.forms[0].hdnClauseVersionNo.value=document.forms[0].versionNo.value;
selectFlag=true;
}
}
if(selectFlag){
var deleteFlag = window.confirm("Are you sure to delete the version of the clause ?");
if(deleteFlag){
document.forms[0].action="modelAddClauseAction.do?method=deleteVersion";
document.forms[0].submit();
}else{
}
}else{
var id = '889';
hideErrors();
addMsgNum(id);
showScrollErrors("versionNoRadio",-1);

}
}

/*
*	Name: fnAddClauseVersion
*   Purpose: Used to Add a Clause into the database.
*/
function fnAddClauseVersion()
{
var mod = document.forms[0];
var clauseDesc = trim($('#clauseDesc_id').val());//var clauseDesc = trim(mod.clauseDescForTextArea.value);Replaced this with jQuery to avoid discrepancies, CR_99
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);
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
mod.dwONumber.value=dwoNumber;
mod.partNumber.value=partNumber;
mod.priceBookNumber.value=priceBookNumber;
mod.edlNo[0].value=edlNo1;
mod.edlNo[1].value=edlNo2;
mod.edlNo[2].value=edlNo3;
mod.refEDLNo[0].value=refEdl1;
mod.refEDLNo[1].value=refEdl2;
mod.refEDLNo[2].value=refEdl3;

var partOfclaSeqNo1=trim(mod.partOfclaSeqNo[0].value);
var partOfclaSeqNo2=trim(mod.partOfclaSeqNo[1].value);
var partOfclaSeqNo3=trim(mod.partOfclaSeqNo[2].value);
var partOfclaSeqNo4=trim(mod.partOfclaSeqNo[3].value);

if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value =="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		return false;
   
}


if (document.forms['ModelAddClauseForm'].modelSeqNo.options[document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex].value =="-1")
{
   
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltModel",-1);
	return false;
	   
}

if (mod.sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
	return false;
}

if (mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
	return false;
	   
}

if (mod.subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	return false;
}
if (mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
	return false;
	   
}

/*if(clauseDesc.length==0 || clauseDesc=="" )
{

var id = '506';
hideErrors();
addMsgNum(id);
showScrollErrors("subSectionSeqNo",-1);

mod.clauseDescForTextArea.value="";
mod.clauseDescForTextArea.select();
mod.clauseDescForTextArea.focus();
return false;
}

if(clauseDesc.length > 4000)
{

var id = '516';
hideErrors();
addMsgNum(id);
showScrollErrors("subSectionSeqNo",-1);


mod.clauseDescForTextArea.select();
mod.clauseDescForTextArea.focus();
return false;
}*/

/* Added For CR_88 issue */
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

if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 ||partOfSeqNo1==0)&&(partOfSeqNo2.length==0 ||partOfSeqNo2==0)&&(partOfSeqNo3.length==0 ||partOfSeqNo3==0)&&(partOfSeqNo4.length==0 ||partOfSeqNo4==0)&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
var id = '505';
hideErrors();
addMsgNum(id);
showScrollErrors("refEDLNo",0);
return false;
}
else
{

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
showScrollErrors("refEDLNo",1);

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
showScrollErrors("refEDLNo",2);

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

if (partOfSeqNo1.length!=0 ||partOfSeqNo1!=0)
{	
	
	if(partOfclaSeqNo1.length==0 || partOfclaSeqNo1==0 || partOfclaSeqNo1 =="null" || partOfclaSeqNo1=="")
	{
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfclaSeqNo1",0);
		return false;
	}

if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",0);
return false;

}

}

if (partOfSeqNo2.length!=0 ||partOfSeqNo2!=0)
{	
	
	if(partOfclaSeqNo2.length==0 || partOfclaSeqNo2==0 || partOfclaSeqNo2=="null" || partOfclaSeqNo2=="")
	{
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfclaSeqNo1",1);
		return false;
	}
if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
{

var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",1);
return false;

}

}
if (partOfSeqNo3.length!=0 ||partOfSeqNo3!=0)
{	
	
	if(partOfclaSeqNo3.length==0 || partOfclaSeqNo3==0 || partOfclaSeqNo3=="null" || partOfclaSeqNo3=="")
	{
		
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfclaSeqNo1",2);
		return false;
	}
if (partOfSeqNo2==partOfSeqNo3||partOfSeqNo1==partOfSeqNo3||partOfSeqNo3==partOfSeqNo4)
{
var id = '524';
hideErrors();
addMsgNum(id);
showScrollErrors("partOfCode",2);
return false;
}

}
if (partOfSeqNo4.length!=0 ||partOfSeqNo4!=0)
{
if(partOfclaSeqNo4.length==0 || partOfclaSeqNo4==0 || partOfclaSeqNo4=="null" || partOfclaSeqNo4=="")
	{
		
		var id = '816';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("partOfclaSeqNo1",3);
		return false;
	}
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
    //Added for CR-109
    if(document.forms[0].appendixExitsFlag.value == "Y" && document.forms[0].applyToDefault[0].checked){
    	//alert("Appendix image mapping has been broken." +"\n"+ "Kindly re-map the clause from Appendix Image Screen.");
    	
    	var appendixMapCheck = confirm("Appendix image mapping will be removed. Do you want to re-map the appendix image to this new clause version?");
    	if(appendixMapCheck){
    		document.forms[0].mapAppendixImg.value=1;
    	}
    }
   
	//Added for CR-109 Ends here  
//Added and Commented for CR_99 to remove junk characters by Validating the Clause Description through AJAX
	var url="modelAddClauseAction.do?method=insertClauseVersion";
	fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
//document.forms[0].action="modelAddClauseAction.do?method=insertClauseVersion";
//document.forms[0].submit();

}

/*
* Name: fntoggleDiv()
* Purpose: Used to Delete the clause and all it's subclause and also the versions of it.
* Modified during CR_88
*/
function fntoggleDiv()
{
	$('#divAddModify').toggle();
	/*if (document.getElementById('modifyClauseList').style.display == "none")
		document.getElementById('modifyClauseList').style.display = "block";
	else if (document.getElementById('modifyClauseList').style.display == "block")
		document.getElementById('modifyClauseList').style.display = "none";			*/

}


/*
*	Name: fnAddCharsticComponent for CR_85
*	Purpose: Used to Load Componentcharacterization.jsp and load its initial parameters
*	
*/
function fnAddCharsticComponent()
{

//CR 88
var hdnCombntnSeqNo=document.forms['ModelAddClauseForm'].hdnCombntnSeqNoArr.value;


var selectedModel=document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex;
var selectedModelName= document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].text;
var selectedModelID = document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value;
var selIndex = document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelAddClauseForm'].specTypeNo.options[selIndex].text;

//CR 88
if(hdnCombntnSeqNo!=""){
		var id = '898';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("charCombTied",-1);

}else if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value=="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);
		   
}else if(document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value=="-1")
{
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("sltModel",-1);
}else if(document.forms['ModelAddClauseForm'].sectionSeqNo.options[document.forms['ModelAddClauseForm'].sectionSeqNo.selectedIndex].value=="-1")
{
var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSection",-1);
}else if(document.forms['ModelAddClauseForm'].subSectionSeqNo.options[document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex].value=="-1")
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("sltSubSection",-1);
}
else
{
window.open("modelClauseDescPopupAction.do?method=initLoadPopUp&selectedModelName="+escape(selectedModelName)+"&selectedModelID="+selectedModelID+"&spectype="+escape(specType)+"&ChrstcFlag="+document.forms[0].hdnClaChrstcFlag.value+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");
}
}


function fnLoadCombntnList()
{
if(document.forms['ModelClauseDescPopUpForm'].sectionSeqNo.options[document.forms['ModelClauseDescPopUpForm'].sectionSeqNo.selectedIndex].value=="-1")
{
var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionSeqNo",-1);
}else 
if(document.forms['ModelClauseDescPopUpForm'].subSecSeqNo.options[document.forms['ModelClauseDescPopUpForm'].subSecSeqNo.selectedIndex].value=="-1")
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("subSecSeqNo",-1);
}

var Next='Y';
var selectFlag=false;
var cnt= document.forms['ModelClauseDescPopUpForm'].clauseSeqNo.length;
var selectedModelName= document.forms['ModelClauseDescPopUpForm'].hdnModelName.value;
var selectedModelNo=document.forms['ModelClauseDescPopUpForm'].modelSeqNo.value;
var specType = document.forms['ModelClauseDescPopUpForm'].hdnspecType.value;


var selectedSection=document.forms['ModelClauseDescPopUpForm'].sectionSeqNo.selectedIndex;
var selectedSectionName= document.forms['ModelClauseDescPopUpForm'].sectionSeqNo.options[selectedSection].text;
var selectedSectionID = document.forms['ModelClauseDescPopUpForm'].sectionSeqNo.options[selectedSection].value;
var selIndex = document.forms['ModelClauseDescPopUpForm'].subSecSeqNo.selectedIndex;
var selectedSubSectionName = document.forms['ModelClauseDescPopUpForm'].subSecSeqNo.options[selIndex].text;
var hdnClaChrstcFlag=document.forms['ModelClauseDescPopUpForm'].hdnClaChrstcFlag.value;

if(cnt>0){
for(var i=0;i<cnt;i++){
if(document.forms['ModelClauseDescPopUpForm'].clauseSeqNo[i].checked){
var clauseSeqNo=document.forms['ModelClauseDescPopUpForm'].clauseSeqNo[i].value;
selectFlag=true;
break;
}
}
}else{
if(document.forms['ModelClauseDescPopUpForm'].clauseSeqNo.checked){
var clauseSeqNo=document.forms['ModelClauseDescPopUpForm'].clauseSeqNo.value;
selectFlag=true;
}
}
if(selectFlag){
window.location="modelClauseDescPopupAction.do?method=fetchCharCombntnEdlView&clauseSeq="+escape(clauseSeqNo)+"&selectedModelName="+escape(selectedModelName)+"&specType="+escape(specType)+"&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedModelNo="+escape(selectedModelNo)+"&ChrstcFlag="+escape(hdnClaChrstcFlag)+"&Next="+escape(Next);
	}else{
																																								
		var id = '520';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("clauseSeqNo",-1);			
	}

}

function fnReloadAddCharsticComponent(){

var hdnselectedModelName= document.forms['ModelClauseDescPopUpForm'].hdnModelName.value;
var selectedModelID=document.forms['ModelClauseDescPopUpForm'].modelSeqNo.value;
var hdnspecType = document.forms['ModelClauseDescPopUpForm'].hdnspecType.value;
var hdnClaChrstcFlag=document.forms['ModelClauseDescPopUpForm'].hdnClaChrstcFlag.value;

window.location="modelClauseDescPopupAction.do?method=initLoadPopUp&selectedModelName="+escape(hdnselectedModelName)+"&selectedModelID="+selectedModelID+"&spectype="+escape(hdnspecType)+"&ChrstcFlag="+hdnClaChrstcFlag;
}

/*
* Name: fnLoadSubSection
* Purpose:Used to Load subsectionList in popup for CR_85
*
*/
function fnPopupLoadSubSection()
{
var popupFlag='Y';
document.forms['ModelClauseDescPopUpForm'].action="modelClauseDescPopupAction.do?method=SubSectionLoad&popupFlag="+popupFlag;
document.forms['ModelClauseDescPopUpForm'].submit();
}


/*
* Name: fnLoadClauseDesc
*  Purpose:Used to Load The clause desc in popup
*
*/
function fnPopupLoadClauseDesc()
{
var popupFlag='Y';
document.forms['ModelClauseDescPopUpForm'].action="modelClauseDescPopupAction.do?method=LoadClauseDesc&popupFlag="+popupFlag;
document.forms['ModelClauseDescPopUpForm'].submit();

}
/*
* Name: fnLoadClauseDesc
*  Purpose:Used to Load The clause desc in popup
*
*/
function fnViewChrCombntnList()
{
var popupFlag='Y';
var clauseSeq=document.forms[0].hdnCharClaSeqNo.value;
var selectedModel=document.forms['ModelAddClauseForm'].modelSeqNo.selectedIndex;
var selectedModelName= document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].text;
var selectedModelID = document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value;
var selIndex = document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex;
var specType = document.forms['ModelAddClauseForm'].specTypeNo.options[selIndex].text;

//var combntnSeq = document.forms['ModelAddClauseForm'].hdnCombntnSeqNo.value;
var combntnSeq = document.forms['ModelAddClauseForm'].hdnCombntnSeqNoArr.value;

if (document.forms['ModelAddClauseForm'].specTypeNo.options[document.forms['ModelAddClauseForm'].specTypeNo.selectedIndex].value=="-1")
{
	 
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("specTypeNo",-1);
		   
}else if(document.forms['ModelAddClauseForm'].modelSeqNo.options[selectedModel].value=="-1")
{
var id = '19';
hideErrors();
addMsgNum(id);
showScrollErrors("modelSeqNo",-1);
}else if(document.forms['ModelAddClauseForm'].sectionSeqNo.options[document.forms['ModelAddClauseForm'].sectionSeqNo.selectedIndex].value=="-1")
{
var id = '205';
hideErrors();
addMsgNum(id);
showScrollErrors("sectionSeqNo",-1);
}else if(document.forms['ModelAddClauseForm'].subSectionSeqNo.options[document.forms['ModelAddClauseForm'].subSectionSeqNo.selectedIndex].value=="-1")
{
var id = '182';
hideErrors();
addMsgNum(id);
showScrollErrors("subSectionSeqNo",-1);
}else if(document.forms['ModelAddClauseForm'].chrstcEdl.options.length==0){
var id = '896';
hideErrors();
addMsgNum(id);
showScrollErrors("addEdlToCla",-1);

} else {

if(clauseSeq!="" && document.forms['ModelAddClauseForm'].chrstcEdl.options.length!==0){

 window.open("modelAddClauseAction.do?method=fetchCharCombntnEdlMap&clauseSeq="+escape(clauseSeq)+"&combntnSeq="+combntnSeq+"","ViewCharCombntnClause","location=0,resizable=no,status=0,scrollbars=1,width=780,height=600");
 }
 
}


}


/*
* Name: fnAddcharEdl
*  Purpose:Used to Load The clause desc in popup
*
*/

function fnAddcharEdl()
{

var selectFlag = false;
var flagVal = false;

var cnt= document.forms['ModelClauseDescPopUpForm'].combntnSeqNo.length;
var seqnoFlag=document.forms['ModelClauseDescPopUpForm'].combntnSeqNo.value;

if(seqnoFlag != undefined && seqnoFlag != ""){  //empty check added for CR-131
cnt=0;
} 
var combntnSeqNo;
var obj;
var chrstcEdl;
var hiddencombntnSeqNo =new Array();
var hiddenchrstcEdl =new Array();

if(cnt>0){
var j=0;

for(var i=0;i<cnt;i++){

   if(document.forms['ModelClauseDescPopUpForm'].combntnSeqNo[i].checked){
	flagVal = true;
		combntnSeqNo=document.forms['ModelClauseDescPopUpForm'].combntnSeqNo[i].value;
		//CR 88

		hiddencombntnSeqNo[j]=combntnSeqNo;
		obj = document.forms['ModelClauseDescPopUpForm'].combntnSeqNo[i].parentNode.parentNode;
		chrstcEdl = "EDL : " + obj.childNodes[5].innerText; //Updated childnode from 2 to 5 for CR-131
		hiddenchrstcEdl[j]=chrstcEdl;
		selectFlag=true;
		j=j+1;
		}
	}
}else{
	
		if(document.forms['ModelClauseDescPopUpForm'].combntnSeqNo.checked){
		flagVal = true;
		//combntnSeqNo=document.forms['ModelClauseDescPopUpForm'].combntnSeqNo[i].value;
		//CR 88
		combntnSeqNo=document.forms['ModelClauseDescPopUpForm'].combntnSeqNo.value;
		hiddencombntnSeqNo[0]=combntnSeqNo;
		obj = document.forms['ModelClauseDescPopUpForm'].combntnSeqNo.parentNode.parentNode;
		chrstcEdl = "EDL : " + obj.childNodes[5].innerText;//Updated childnode from 2 to 5 for CR-131
		hiddenchrstcEdl[0]=chrstcEdl;
		selectFlag=true;
		j=j+1;
		
		}
	}
if(!flagVal){

		var id = '894';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("combntnSeqNo",-1);
	}
	if (selectFlag){
		window.opener.fnAddChrstcEdl(hiddenchrstcEdl,hiddencombntnSeqNo,document.forms['ModelClauseDescPopUpForm'].hdnClauseSeqNo.value);		
		window.close();
		}
}
/*
* Name: fnUnlinkChrComdntnt
*  Purpose:Used to clear Edlno 
*/
function  fnUnlinkChrComdntnt()
{
var conf = window.confirm("Do you want to Unlink the selected Characteristic Combination EDL # ?");
if(conf)
{
window.opener.fnClearChrstcEdl();
window.close();
}

}

/*
*  Name: fnUnlinkChrCombntn
*  Purpose:Used to clear Edlno 
*/
function  fnUnlinkChrCombntn()
{
if (document.forms[0].chrstcEdl.options.length!=0){
var conf = window.confirm("Do you want to delete the selected Characteristic Combination EDL # ?");
	if(conf)
	{
	window.fnClearChrstcEdl();
	}
}
}
/*
*  Name: fnRearrangeClausesPage
*  Purpose:Used to rearrange the clauses  
*/
//Added for CR_88 on 5July10
function fnRearrangeClausesPage(childFlag){

var mod = document.forms[0];
selectClause=false;
if (mod.specTypeNo.options[mod.specTypeNo.selectedIndex].value =="-1")
{
		var id = '2';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("sltSpecType",-1);

}

else if (mod.modelSeqNo.options[mod.modelSeqNo.selectedIndex].value =="-1")
{
	var id = '19';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltModel",-1);	   
}

else if (mod.sectionSeqNo.options.length<=1)
{
	var id = '519';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
}

else if (mod.sectionSeqNo.options[mod.sectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '205';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSection",-1);
		   
}

else if (mod.subSectionSeqNo.options.length<=1)
{
	var id = '511';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);
}
else if (mod.subSectionSeqNo.options[mod.subSectionSeqNo.selectedIndex].value =="-1")
{	
	var id = '182';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("sltSubSection",-1);	   
}
else if (childFlag == "N")	{

		mod.childFlag.value="N";
		fnLoadWithOutChildClause();		
		}
else if (childFlag == "R")	{

		mod.childFlag.value="N";
		document.forms[0].action="modelAddClauseAction.do?method=initLoad#Reset";
		document.forms[0].submit();
		
		}   
}

/*
*  Name: fnLoadWithOutChildClause
*  Purpose:Used to get the without child clauses  
*/

function fnLoadWithOutChildClause()
{
document.forms[0].action="modelAddClauseAction.do?method=initLoad";
document.forms[0].submit();
}

/*
*  Name: fnReSetClauses
*  Purpose:Used to reset clauses  on page
*/

function fnReSetClauses()
{

if(document.forms['ModelAddClauseForm'].rowIndexList.value !=null && document.forms['ModelAddClauseForm'].rowIndexList.value !=""){
document.forms[0].action="modelAddClauseAction.do?method=updateReInsertClause";
document.forms[0].submit();
}else{

	var id = '899';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseNo",-1);
}

}
//Added For CR_94

function fnAllowSubClause(hrchyLevel,parentClaSeq)
{
var divHeight = $("#clausesChild"+parentClaSeq).height();
if ($("#clausesChild"+parentClaSeq).length > 0)
		{
		$("#clauseDetails"+parentClaSeq).tableDnD({
		onDragStart: function(table, row) {
			$(row).css('background-color', '#D8F2FF');
            $(row).contents('td').css({'border': '1px solid red', 'border-left': '1px dotted red', 'border-right': '1px dotted red'});
            $(row).contents('td:first').css('border-left', '1px solid red');
            $(row).contents('td:last').css('border-right', '1px solid red');
			},
		 onDrop: function(table, row) {
	          var rows = table.rows;
    	      var result = "";
        	  for (var i=0; i<rows.length; i++) {
            	if (result.length > 0) result += ",";
	            var rowId = rows[i].id;
	            if (rowId && rowId && table.tableDnDConfig && table.tableDnDConfig.serializeRegexp) {
                rowId = rowId.match(table.tableDnDConfig.serializeRegexp)[0];
    	        	}
            	result += rowId;
            	}
    		  var rowIDList=result;
	          document.forms['ModelAddClauseForm'].rowIndexList.value = rowIDList;
	          $(row).contents('td:first').html("");
          }
		});
		    $("#clausesChild"+parentClaSeq).modal();//Updated for CR-131 
		}
}
function fnSaveChildClauses()
{
if(document.forms['ModelAddClauseForm'].rowIndexList.value !=null && document.forms['ModelAddClauseForm'].rowIndexList.value !=""){
	document.forms[0].action="modelAddClauseAction.do?method=updateReInsertClause";
	document.forms[0].submit();
}/*else{
	$.modal.close();
	var id = '6';
	hideErrors();
	addMsgNum(id);
	showErrors();
	document.location.href="#errorlayer";
}*/
//Added for fix of CR 131
else{
	
	var id = '899';
	hideErrors();
	addMsgNum(id);
	showScrollErrors("clauseNo",-1);
}
//ends


}

/*
*	Name: fnLoadComponentsClauses For CR_97
*	Purpose: Used to Load Component List on selection of ComponentGroupList
*	
*/
function fnLoadComponentClauses()
{
var LeadCompFlag='Y';
$('#clauseDesc_id').val("");
document.forms[0].standardEquipmentSeqNo.value=-1;
document.forms[0].action="modelAddClauseAction.do?method=loadComponent&LeadCompFlag="+LeadCompFlag;
document.forms[0].submit();
}

function fnViewLeadcompClauses()
{
 $("#leadcompClausesList").modal(); //Updated for CR-131
}

/*
*	Name: fnLoadComponentsClauses For CR_97
*	Purpose: Used to fnFetchClauseDetails
*	
*/

function fnFetchClauseDetails()
{
var radio_buttons = $("input[name='leadClauseSeqNo']");
if( radio_buttons.filter(':checked').length == 0){
		var id = '20';
		hideErrors();
		addMsgNum(id);
		showScrollSuggestErrors("leadClauseSeqNo",radio_buttons.length-1);
}
else{
//$.modal.close(); //Commented for CR-131
$('#modal').modal('toggle'); //added for CR-131
var ModClause=document.forms['ModelAddClauseForm'];
var leadClaMdlSeq= document.forms[0].leadClaMdlSeq.value;
var leadClaSubSecSeq=document.forms[0].leadClaSubSecSeq.value;

document.forms[0].action="modelAddClauseAction.do?method=fetchClauseDetails&leadClaMdlSeqNo="+leadClaMdlSeq+"&leadClaSubSecSeq="+leadClaSubSecSeq+"";
document.forms[0].submit();
}
}
/*Added for CR_100 for disabling Price Book No for locomotives*/
$(document).ready(function() {
	var modifyClauseFlag = "";
	if ($("#modifyClauseFlag").length > 0) {
		modifyClauseFlag =document.forms[0].modifyClauseFlag.value; 
	}
	$("#sltSpecType").select2({theme:'bootstrap'});
   	$("#sltModel").select2({theme:'bootstrap'});
   	$("#sltSection").select2({theme:'bootstrap'});
	$("#sltSubSection").select2({theme:'bootstrap'});
	$("#sltCustomer").select2({theme:'bootstrap'});
   	$("#sltLeadComp").select2({theme:'bootstrap'});
   	$("#sltLeadCompGrp").select2({theme:'bootstrap'});
   	$("#sltEnggData").select2({theme:'bootstrap'});
   	$("#compGroupList").select2({theme:'bootstrap'});
	$('#tbAddModify').DataTable({
    	searching: false,
    	lengthChange: false,
    	pageLength: 5,
    	paging:false,
    	ordering:false,
    	info: false,
    	scrollY: '265px',
    	scrollCollapse: true
 	});
	if (($("#divAddModify").length > 0) && ($("#hdnClauseSeqNo").val() != "") && ($("#hdnClauseSeqNo").val() != "0") && (modifyClauseFlag == "Y")){
		$('input[name=clauseSeqNo][value=' + $("#hdnClauseSeqNo").val() + ']').prop('checked', true);
	}
	if(modifyClauseFlag == 'N')	{
		$('#divAddModify').hide();
	}
	else if(modifyClauseFlag == 'Y')	{
		$('#divAddModify').show();
	}
	if ($("#errorlayer").text().length > 1)
		$('#divAddModify').show();
	
	if($('#sltSpecType').val()==1){
		//$('#priceBookNo').removeClass('TEXTBOX2').addClass('TEXTBOX02');//Commented for CR-131
		$('#priceBookNo').addClass("disabled");//Added for CR-131
		$("#priceBookNo").prop("disabled", true);
		}
	//Added for CR-118 
	//Commented for CR-125
	  /*  $(".makeDisableCheck").attr('disabled',true);
	    $('.makeDisableCheck').bind('click', false);
	    if($('#sltSpecType').val()==2)	{
	    	 $(".makeDisableCheck").attr('disabled',false);
    	 	 $('.makeDisableCheck').unbind('click', false);
	    }*/		
	if ($("input[name=childFlag]").length > 0)	{
    	
	    var childFlag = document.forms[0].childFlag.value; 
	    
		$('#clausesTab li').removeClass('bg-primary');
		if(childFlag == 'Y' && modifyClauseFlag == 'N')	{
			$('#clausesTab li:eq(0)').find('a').addClass('bg-primary');
		}
		else if(childFlag == 'Y' && modifyClauseFlag == 'Y')	{
			$('#clausesTab li:eq(1)').find('a').addClass('bg-primary');
		}
		else if(childFlag == 'N' && modifyClauseFlag == 'Y')	{
			$('#clausesTab li:eq(2)').find('a').addClass('bg-primary');
			$('#clausesTab li:eq(2)').find('a').prop('href','').prop('href','javascript:fnRearrangeClausesPage("Y")');
		}
		else if(childFlag == 'N' && modifyClauseFlag == 'N')	{
			$('#clausesTab li:eq(2)').find('a').addClass('bg-primary');
			$('#clausesTab li:eq(2)').find('a').prop('href','').prop('href','javascript:fnRearrangeClausesPage("Y")');
		}	
    }
})
//Added for CR-121
function confirmDialog(clauseDesc) {
	/*$('#confirm').modal({
		closeHTML: "<a href='#' id='close' title='Close' class='modal-close' >X</a>",
		position: ["20%",],
		escClose:true,
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (Dialog) {
			// if the user clicks "yes"
			$('.yes', Dialog.data[0]).click(function () {
				var url="modelAddClauseAction.do?method=insertClause";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
				$.modal.close(); 
			});
			// if the user clicks "no"
			$('.no', Dialog.data[0]).click(function () {
				document.forms[0].leadSubClaExistsFlag.value = "N";
				var url="modelAddClauseAction.do?method=insertClause";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
				$.modal.close();
			});
		}
	});Commented for CR-131*/ 
	//Added for CR-131
	var url="modelAddClauseAction.do?method=insertClause";
	bootbox.dialog({
		  message: "Sub-clauses available from the Copy from Clause.<br><br> Do you want to automatically add those sub-clauses to the current clause?",
		  title: "Confirm Box",
		  buttons: {
		    yes: {
		      label: "Yes",
		      className: "btn-primary",
		      callback: function() {
		        document.forms[0].leadSubClaExistsFlag.value = "Y";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
		      }
		    },
		    no: {
		      label: "No",
		      className: "btn-primary",
		      callback: function() {
		        document.forms[0].leadSubClaExistsFlag.value = "N";
				fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');
		      }
		    }
		  }
		});
}


/*
*  Added For CR_134
*  Name: fnShowMarkClause
*  Purpose: Used to show the MarkClause Overlay
*/
function fnShowMarkClause(clauseseqno,versionno,usermarkerflag)
{	
	$('#markClaReason').val("");
	
	$('#MarkClause').on('shown.bs.modal', function () {
	  $('#markClaReason').focus();
	})
	$('#MarkClause').modal();
	$('#saveClaReason').unbind('click');
	$("#saveClaReason").click(function () { 
	      fnSetUserMarker(clauseseqno,versionno,usermarkerflag); 
    });
}


/*
 * Added for LSDB_CR-134 
 * setting user marker flag as 'Y' or 'N'
*/
function fnSetUserMarker(clauseseqno,versionno, usermarkerflag){

	var conMasg = true;
	if(usermarkerflag == "N"){
	document.forms[0].markClaReason.value = "";
	}
    if(conMasg){
		document.forms[0].action="modelAddClauseAction.do?method=updateUserMarker&clauseseqno="+clauseseqno+"&versionno="+versionno+"&usermarkerflag="+usermarkerflag;
		document.forms[0].submit();
		
	}else{
		return;
	}
	
}