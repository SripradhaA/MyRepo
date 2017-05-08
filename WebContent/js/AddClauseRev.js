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
	}

	document.getElementById('showgrid').innerHTML="<TABLE WIDTH='100%' BORDER=0  id='tblGrid'style='table-layout:fixed' cellspacing='0' cellpadding='0'><TBODY>								<TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR><TR><TD WIDTH='19%' >&nbsp;&nbsp;&nbsp;&nbsp;</TD>				<TD WIDTH='17%' >&nbsp;&nbsp;</TD><TD WIDTH='20%' >&nbsp;&nbsp;</TD></TR></TBODY></TABLE>";*/
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
function addTableRow(src)
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
*	Name: deletePartOf()
*	Purpose: Used to Delete the Part Of Fields 
*	
*/
function deletePartOf(index)
{
document.forms[0].partOf[index-1].value="";
document.forms[0].partOfSeqNo[index-1].value="";
document.forms[0].clauseSeqNum[index-1].value="";

}




/*
*	Name: fnAddClause
*	Purpose: Used to Add a Clause into the database.
*	
*/
function fnAddClause()
{
var mod = document.forms[0];
var orderKey = document.forms[0].orderKey.value;
var secSeq =   document.forms[0].secSeq.value;
var revCode = document.forms[0].revCode.value;
var subsecseqno = document.forms[0].subSectionSeqNo.value;
var clauseSeqNo=document.forms[0].clauseSeqNo.value;
var clauseDesc = trim($('#clauseDesc_id').val());//var clauseDesc = trim(mod.clauseDesc.value);Replaced this with jQuery to avoid discrepancies, CR_129
var dwoNumber = trim(mod.dwONumber.value);
var partNumber=trim(mod.partNumber.value);
var priceBookNumber=trim(mod.priceBookNumber.value);
var comments=trim(mod.comments.value);

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

var partOfSeqNo1=trim(mod.partOf[0].value);
var partOfSeqNo2=trim(mod.partOf[1].value);
var partOfSeqNo3=trim(mod.partOf[2].value);
var partOfSeqNo4=trim(mod.partOf[3].value);



mod.clauseDesc.value=clauseDesc;
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




var partOfclaSeqNo1=trim(mod.clauseSeqNum[0].value);
var partOfclaSeqNo2=trim(mod.clauseSeqNum[1].value);
var partOfclaSeqNo3=trim(mod.clauseSeqNum[2].value);
var partOfclaSeqNo4=trim(mod.clauseSeqNum[3].value);	
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
		
		
		mod.clauseDesc.select();
		mod.clauseDesc.focus();
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


if ((mod.standardEquipmentSeqNo.options[mod.standardEquipmentSeqNo.selectedIndex].value =="-1")&&(refEdl1=="")&&(refEdl2=="")&&(refEdl3=="")&&(edlNo1=="")&&(edlNo2=="")&&(edlNo3=="")&&(partOfSeqNo1.length==0 || partOfSeqNo1==0|| partOfSeqNo1=="")&&(partOfSeqNo2.length==0 || partOfSeqNo2==0 || partOfSeqNo2=="")&&(partOfSeqNo3.length==0 || partOfSeqNo3==0 || partOfSeqNo3=="")&&(partOfSeqNo4.length==0 || partOfSeqNo4==0 || partOfSeqNo4=="")&&(dwoNumber.length==0 || dwoNumber=="" )&&(partNumber.length==0 || partNumber=="" )&&(priceBookNumber.length==0 || priceBookNumber=="" )&&(comments.length==0 || comments==""))
{
	
		var id = '505';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("refEDLNo",0);
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


/*for(i=0;i<document.forms[0].refEDLNo.length;i++){
if(document.forms[0].refEDLNo[i].value != ""){
if(!validateNumber(document.forms[0].refEDLNo[i].value)){
		var id = '515';
		hideErrors();
		addMsgNum(id);
		showErrors();

		document.forms[0].refEDLNo[i].focus();
		return;
}
}
}*/

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
		showErrors();
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
	

	
	if (partOfSeqNo1.length!=0 && partOfSeqNo1!=0 && partOfSeqNo1!="") 
	{
		
		
		if(partOfclaSeqNo1.length==0 || partOfclaSeqNo1==0 || partOfclaSeqNo1=="")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",0);
			return false;
		}

		
		if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo1==partOfSeqNo3 ||partOfSeqNo1==partOfSeqNo4)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",0);	
			
			return false;


		}

	}

	if (partOfSeqNo2.length!=0 && partOfSeqNo2!=0 && partOfSeqNo2!="") 
	{
		
		
		if(partOfclaSeqNo2.length==0 || partOfclaSeqNo2==0 || partOfclaSeqNo2=="")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",1);
			return false;
		}


		
		if (partOfSeqNo1==partOfSeqNo2||partOfSeqNo2==partOfSeqNo3||partOfSeqNo4==partOfSeqNo2)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",1);		
			return false;


		}

	}
	if (partOfSeqNo3.length!=0 && partOfSeqNo3!=0 && partOfSeqNo3!="") 
	{
		
		if(partOfclaSeqNo3.length==0 || partOfclaSeqNo3==0 || partOfclaSeqNo3=="")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",2);
			return false;
		}

		
		if (partOfSeqNo3==partOfSeqNo1||partOfSeqNo3==partOfSeqNo2||partOfSeqNo3==partOfSeqNo4)
		{
			
			var id = '524';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",2);		
			return false;

		}

	}
	if (partOfSeqNo4.length!=0 && partOfSeqNo4!=0 && partOfSeqNo4!="") 
	{
		
		if(partOfclaSeqNo4.length==0 || partOfclaSeqNo4==0 || partOfclaSeqNo4=="")
		{
			var id = '816';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("partOf",3);
			return false;
		}
		
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
if(priceBookNumber!=0 || priceBookNumber!="" )
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

/*var isSpecChar5 = SpecialCharacterCheck(comments);
     if(isSpecChar5){
			mod.comments.value="";
			mod.comments.select();
			mod.comments.focus();
			var id = '525';
			hideErrors();
			addMsgNum(id);
			showErrors();
		   return false;
	 }*/

if(comments.length > 2000)
{
	
		var id = '517';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("comments",-1);
		
		return false;
}
}
/** Commented based on the requirement of LSDB_CR-74 0n 05-June-09 by ps57222 **/
//Modified for CR_79 removing validation before Opening Status on 28-Oct-09 by RR68151
var reasons=trim(mod.reason.value);
mod.reason.value=reasons;

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
//Added and Commented for CR_99 to remove junk characters by Validating the Clause Description through AJAX
var url = "AddClauseRev.do?method=insertClause&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno+"";
var clauseDesc = $('#clauseDesc_id').val();
fnValidateClauseDesc(clauseDesc,url,'clauseDesc_id');

/*added for CR 73 by cm68219 starts*/
//document.forms[0].action="AddClauseRev.do?method=insertClause&clauseSeq="+clauseSeqNo+"&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
/*added for CR 73 by cm68219 ends*/
//document.forms[0].submit();
//Ends here for CR_99

}



function ModifySpec()
{
	
	var orderKey = document.forms[0].orderKey.value;
  	var secSeq =   document.forms[0].secSeq.value;
  	var revCode = document.forms[0].revCode.value;
	//Added for landing to the subsection
	var subsecseqno = document.forms[0].subSectionSeqNo.value;
  	
    document.forms[0].action="OrderSection.do?method=fetchSectionDetails&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode+"&subsecno="+subsecseqno;
    document.forms[0].submit();
}


/*
*	Name: submitDependantClause()
*	Purpose: Used to populate the Part Of Fields in parent window
*	
*/
function submitDependantClause()
{
var clauseNo;
var selIndex;
var flag = false;
var	selectedSubSectionID=document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.selectedIndex;
var subSecID=document.forms['OrderClausePartOfPopUpForm'].subSectionSeqNo.options[selectedSubSectionID].value;
index = document.forms['OrderClausePartOfPopUpForm'].textBoxIndex.value;

if (document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.length!=undefined)
{
	for(var i = 0 ; i < document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.length; i++)
	{

		if(document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[i].checked)
		{
			selIndex = i;
			flag = true;
			break;
		}
	}
}
else
{
	if(document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo.checked)
	{
		selIndex=1;
		flag = true;
	}
	
}
if (flag)
{
clauseNo = document.forms['OrderClausePartOfPopUpForm'].hdnClauseNum[selIndex].value; 

window.opener.document.forms[0].partOf[index-1].value = clauseNo;
window.opener.document.forms[0].partOfSeqNo[index-1].value = subSecID;
window.opener.document.forms[0].clauseSeqNum[index-1].value = document.forms[0].clauseSeqNo[selIndex].value;
//window.opener.document.forms[0].clauseNo[index-1].value = document.forms['OrderClausePartOfPopUpForm'].clauseSeqNo[selIndex].value;
window.close();
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

 function fnSelectClauseRevSpec()
  {
  
  var orderKey = document.forms[0].orderKey.value;
  var secSeq = document.forms[0].secSeq.value;
  var revCode = document.forms[0].revCode.value;
  document.forms[0].action="OrderSelClauseRevAction.do?method=fetchClauseVersions&orderKey="+orderKey+"&secSeq="+secSeq+"&revCode="+revCode;
  document.forms[0].submit();
  
  }
function LoadClauses(index)
{
	
	var orderNo=document.forms[0].orderNo.value;
	var orderKey=document.forms[0].orderKey.value;
	var modelSeqNo=document.forms[0].modelSeqNo.value;

	
	window.open("orderClausePartOfPopUpAction.do?method=initLoad&textBoxIndex="+index+"&ModelSeqNo="+modelSeqNo+"&OrderNum="+orderNo+"&orderKey="+orderKey+"","Clause","location=0,resizable=no,status=0,scrollbars=1,width=850,height=600"); 
}
/*Added for CR_100 for disabling Price Book No for locomotives*/
$(document).ready(function() {
	if($('#specTypeNo').val()==1){
		$('#priceBookNo').removeClass('TEXTBOX2').addClass('TEXTBOX02');
		$("#priceBookNo").attr("disabled", "disabled");
		}
})