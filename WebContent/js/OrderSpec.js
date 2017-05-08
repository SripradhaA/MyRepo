function initLoadSpecification()
{
		
	document.forms[0].action="OrderSpecAction.do?method=initLoadSpecification";
	document.forms[0].submit();
}

function insertSpecification()
{

   var spec = document.forms[0];
   var title = trim(spec.orderSpecName.value);
   var desc  = trim(spec.orderSpecItemDesc.value);
   var item = trim(spec.orderSpecItemValue.value);
 

	if (trim(spec.orderSpecName.value).length==0 || trim(spec.orderSpecName.value)=="")
	   {
	 
			var id = '216';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderSpecName",-1);
			return false;
	   
		}
		/*var isSpecCharc = SpecialCharacterCheck(spec.orderSpecName.value);
    if(isSpecCharc){

                    var id = '216';
                    hideErrors();
                    addMsgNum(id);
                    showErrors();	               
                    spec.orderSpecName.focus();
                    return false;

                  }  */  

	if (trim(spec.orderSpecItemDesc.value).length==0 || trim(spec.orderSpecItemDesc.value)=="")
	   {
	 
			var id = '215';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderSpecItemDesc",-1);
			return false;
	   
		}	
/*var isSpecCharc = SpecialCharacterCheck(spec.orderSpecItemDesc.value);
    if(isSpecCharc){

                    var id = '215';
                    hideErrors();
                    addMsgNum(id);
                    showErrors();
	                //custForm.custName.value="";
                    spec.orderSpecItemDesc.focus();
                    return false;

                  }*/    
   
	document.forms[0].action="OrderSpecAction.do?method=insertSpecification";
	document.forms[0].submit();


}

function initLoadSpecificationItem(specname,seqno)
{	
		
	document.forms[0].hdnSpecName.value = specname;	
	document.forms[0].hdnSpecSeqNo.value = seqno;    
	document.forms[0].action="OrderSpecAction.do?method=initLoadSpecificationItem&seqno="+seqno;
	document.forms[0].submit();

}

function deleteSpecificationItem()
{
	var spec = document.forms[0];
	var specchecked;
	
	
	if(spec.itemSeqNo.length>0){

	for(var i = 0 ; i < spec.itemSeqNo.length ; i++){
		if(spec.itemSeqNo[i].checked){
			specchecked = true;
			index = i;
			break;
		
		}

	}
}
else{
		if(spec.itemSeqNo.checked)
			specchecked = true;
		
}
	
	if (!specchecked)
	{
		var id= '44';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("itemSeqNo",-1);
		return false;
	

	}

	 var del=confirm("Are you sure you want to delete the selected description and value ?");
	 if(del == true){
	   document.forms[0].action="OrderSpecAction.do?method=deleteSpecificationItem";
	   document.forms[0].submit();
     }else{
	 }
}

function insertSpecificationItem()
{

	var spec = document.forms[0];

	var desc  = trim(spec.orderSpecItemDesc.value);
	var item = trim(spec.orderSpecItemValue.value);

	
	if (desc.length==0 || desc=="")
	   {
	 
			var id = '215';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderSpecItemDesc",-1);
			return false;
	   
		}
		/*var isSpecCharc = SpecialCharacterCheck(spec.orderSpecItemDesc.value);
    if(isSpecCharc){

                    var id = '215';
                    hideErrors();
                    addMsgNum(id);
                    showErrors();
	                //custForm.custName.value="";
                    spec.orderSpecItemDesc.focus();
                    return false;

                  } */   

    document.forms[0].action="OrderSpecAction.do?method=insertSpecificationItem";
	document.forms[0].submit();


}

//Script to perform validations for mofify specification items
function fnModifyOrderSpecItem()
{
	var spec = document.forms[0];
	var specchecked;
	
	
	if(spec.itemSeqNo.length>0){

	for(var i = 0 ; i < spec.itemSeqNo.length ; i++){
		if(spec.itemSeqNo[i].checked){
			specchecked = true;
			index = i;
			break;
		
		}

	}
}
else{
		if(spec.itemSeqNo.checked)
			specchecked = true;
		
}
	
	if (!specchecked)
	{
		var id= '43';
		hideErrors();
		addMsgNum(id);
		showScrollErrors("itemSeqNo",-1);
		return false;
	

	}else{
			var specItemName = "";
			var specItemValue ="";
			if(spec.itemSeqNo.length>0){
			
				 specItemName = trim(spec.itemDesc[index].value);
				 specItemValue = trim(spec.itemValue[index].value);

				if(specItemName.length==0 || specItemName=="" ){
					var id = '215';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("itemDesc",index);
					return false;
		
				}
				
		}else{
				 specItemName = trim(spec.itemDesc.value);
				 specItemValue  = trim(spec.itemValue.value);
				if(specItemName.length==0 || specItemName=="" ){
					var id = '215';
					hideErrors();
					addMsgNum(id);
					showScrollErrors("itemDesc",index);
					return false;
		
				}
				
		}
			
		
			
}	
	
	if(spec.itemSeqNo.length>0){	
		for(var i = 0 ; i < document.forms[0].itemSeqNo.length ; i++){

			if(document.forms[0].itemSeqNo[i].checked){
									
				document.forms[0].hdnitemDesc.value=trim(document.forms[0].itemDesc[i].value);
				document.forms[0].hdnitemValue.value=trim(document.forms[0].itemValue[i].value);
								
				break;

			}
		}
	}else{

			if(document.forms[0].itemSeqNo.checked){
				
				document.forms[0].hdnitemDesc.value=document.forms[0].itemDesc.value;
				document.forms[0].hdnitemValue.value=document.forms[0].itemValue.value;
			}
	}
	 /*if(spec.itemSeqNo.length>0)
     {
       var cnt = spec.itemSeqNo.length;
          for(var i=0;i<cnt;i++)
              {
	var isSpecCharc = SpecialCharacterCheck(spec.itemDesc[index].value);	
                    if(isSpecCharc)
                      {                
                           var id = '215';
                           hideErrors();
                           addMsgNum(id);
                            showErrors();	           
                           spec.itemDesc[index].focus();
                           return false;

                        }
                } 
         }
            else{
                   var isSpecCharc = SpecialCharacterCheck(spec.itemDesc.value);	
                       if (isSpecCharc)
                            {  
                               var id = '215';
                               hideErrors();
                              addMsgNum(id);
                              showErrors();	               
                              spec.itemDesc.focus();
                               return false;
                            }
                     }*/

document.forms[0].action="OrderSpecAction.do?method=updateSpecificationItem";
document.forms[0].submit();

}

function fnModifyHpRating()
{
var spec = document.forms[0];

	var hprating  = trim(spec.orderHpDesc.value);
	spec.orderHpDesc.value=hprating;
	if (hprating.length==0 || hprating=="")
	   {
	        
			var id = '12';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderHpDesc",-1);
			return false;
	   
		}

/*var isSpecCharc = SpecialCharacterCheck(spec.orderHpDesc.value);
    if(isSpecCharc){

                    var id = '12';
                    hideErrors();
                    addMsgNum(id);
                    showErrors();	                
                    spec.orderHpDesc.focus();
                    return false;
	}*/
document.forms[0].action="OrderSpecAction.do?method=updateHpRating";
document.forms[0].submit();
}
function orderspecHomePage()
{

	document.forms[0].action="OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value+"&seqno="+document.forms[0].hdnSpecSeqNo.value;
	document.forms[0].submit();
}
function orderspecAddSpecification()
 {
    document.forms[0].action="OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
    document.forms[0].submit();
 }
/*************** Added for LSDB_CR-64 by ka57588 ***************/
/**************  Starts here ***************/

function previewSpecification(orderkey , modelname){
	
	document.forms[0].action="OrderSpecAction.do?method=previewSpecificationByPDF&orderkey="+orderkey+"&modelname="+escape(modelname);
	document.forms[0].submit();

}

function displayModifiedSpecification(specname , specseqno){

	document.forms[0].hdnSpecName.value = specname;	
	document.forms[0].hdnSpecSeqNo.value = specseqno; 
	document.forms[0].action="OrderSpecAction.do?method=displayModifiedSpecification&orderKeyId="+document.forms[0].orderKey.value;
	document.forms[0].submit();

}

function updateSpecification(){
	 var spec = document.forms[0];
	 var title = trim(spec.orderSpecName.value);
	 if (trim(spec.orderSpecName.value).length==0 || trim(spec.orderSpecName.value)=="")
	   {
	 
			var id = '216';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("orderSpecName",-1);
			return false;
	   
		}
	document.forms[0].action="OrderSpecAction.do?method=updateSpecification";
	document.forms[0].submit();
}

function deleteSpecification(specseqno){
		
	document.forms[0].hdnSpecSeqNo.value = specseqno; 
	var del=confirm("Are you sure you want to delete the entire section ?");
	if(del){
		document.forms[0].action="OrderSpecAction.do?method=deleteSpecification";
		document.forms[0].submit();
	}
}


function fnLoadAppendix(){

  
  document.forms[0].action="AppendixAction.do?method=fetchImage&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}

function fnLoadPerfCurve(){

document.forms[0].action="orderPerfCurveAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnMainFeature(){

  document.forms[0].action="MainFeatureInfo.do?method=fetchComponentDesc&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
  document.forms[0].submit();
}
function fnShowGeneralArrangement(){

document.forms[0].action="orderGenArrangeAction.do?method=initLoad&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

function fnShowSpecification(){

document.forms[0].action="OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId="+document.forms[0].orderKey.value+"&revCode="+document.forms[0].hdnRevViewCode.value;
document.forms[0].submit();

}

/**************  Ends here  ***************/
//Added for CR-74 01-06-09
function fnChangeRevisionView() {
    var revCode = document.forms[0].revCode.options[document.forms[0].revCode.selectedIndex].value;
    document.forms[0].action = "OrderSpecAction.do?method=fetchSpecificationItems&orderKeyId=" + document.forms[0].orderKey.value + "&revCode=" + revCode;
    document.forms[0].submit();
}
