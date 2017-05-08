function fnSort1058Number(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
				if(sortKey == 1){
		document.forms[0].orderbyFlag.value=2;
		}else if(sortKey == 2){
		document.forms[0].orderbyFlag.value=1;
		}else{
	    document.forms[0].orderbyFlag.value=1;
		}
		document.forms[0].columnheader.value="1058Number";
		document.forms[0].action="SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
		document.forms[0].submit();
}

function fnSortStatus(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 3){
		document.forms[0].orderbyFlag.value=4;
		}else if(sortKey == 4){
		document.forms[0].orderbyFlag.value=3;
		}else{
	    document.forms[0].orderbyFlag.value=3;
		}
		document.forms[0].columnheader.value="Status";
		document.forms[0].action="SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
		document.forms[0].submit();
}

function fnSortSpecRevision(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 5){
		document.forms[0].orderbyFlag.value=6;
		}else if(sortKey == 6){
		document.forms[0].orderbyFlag.value=5;
		}else{
	    document.forms[0].orderbyFlag.value=5;
		}
		document.forms[0].columnheader.value="SpecRevision";
		document.forms[0].action="SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
		document.forms[0].submit();
}

function fnSortCustomerName(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 7){
		document.forms[0].orderbyFlag.value=8;
		}else if(sortKey == 8){
		document.forms[0].orderbyFlag.value=7;
		}else{
	    document.forms[0].orderbyFlag.value=7;
		}
		document.forms[0].columnheader.value="CustomerName";
		document.forms[0].action="SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
		document.forms[0].submit();
}

function fnSortIssuedBy(){
	
		var sortKey=document.forms[0].orderbyFlag.value;
		if(sortKey == 9){
		document.forms[0].orderbyFlag.value=10;
		}else if(sortKey == 10){
		document.forms[0].orderbyFlag.value=9;
		}else{
	    document.forms[0].orderbyFlag.value=9;
		}
		document.forms[0].columnheader.value="IssuedBy";
		document.forms[0].action="SearchRequest1058Action.do?method=view1058ClauseAppliedtoSpec";
		document.forms[0].submit();
}
