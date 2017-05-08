/*
*	Purpose: Used to View the Pending/Accepted/Rejected/completed Suggestions
* we added a new completed suggestion for CR_106
*/

$(document).ready(function(){
				var statusCode = document.forms[0].suggestStatusCode.value; 
				$('#suggestTab li').removeClass('active');
				if(statusCode == 1)	{
					$('#suggestTab li:eq(0)').addClass("bg-info");
					$('.pagination').addClass("pg-info");
				}
				else if(statusCode == 2)	{
					$('#suggestTab li:eq(1)').addClass("bg-success");
					$('.pagination').addClass("pg-success");
				}
				else if(statusCode == 3)	{
					$('#suggestTab li:eq(2)').addClass("bg-danger");
					$('.pagination').addClass("pg-danger");
				}
				else if(statusCode == 4)	{
					$('#suggestTab li:eq(3)').find('a').addClass('bg-primary');
				}
               /* if ($('ul#suggestlist').hasClass('ulacclist'))
                                $('ul#suggestlist').easyPaginate({
                                                licontrols:'accpaginate'
                                 });
                else if ($('ul#suggestlist').hasClass('ulrejlist'))
                                $('ul#suggestlist').easyPaginate({
                                                licontrols:'rejpaginate'
                                 });
                else if ($('ul#suggestlist').hasClass('ulpendlist'))
                                $('ul#suggestlist').easyPaginate({
                                                licontrols:'pendpaginate'
                                 });
                else
                                $('ul#suggestlist').easyPaginate();
                                
                 $('.peddingbox').hover( 
                  function () {
                                $(this).addClass("penddingdhover");
                  },
                  function () {
                                $(this).removeClass("penddingdhover");
                  }
                );
                                
                $('.completedbox').hover( 
                  function () {
                                $(this).addClass("completehover");
                  },
                  function () {
                                $(this).removeClass("completehover");
                  }
                );             
                $('.acceptbox').hover( 
                  function () {
                                $(this).addClass("accepthover");
                  },
                  function () {
                                $(this).removeClass("accepthover");
                  }
                );
                $('.rejectbox').hover( 
                  function () {
                                $(this).addClass("rejecthover");
                  },
                  function () {
                                $(this).removeClass("rejecthover");
                  }
                );
               
                if (!$('ul#suggestlist').hasClass('ulacclist')) {
	                $('.commentdesc').hover( 
	                    function () {      
	                    		//Modified with fixes for linebreaks
	                    		var text = $(this).html();
	                    		var matches = text.match(/\<br\>/gi);
	                    		var breaks = matches ? matches.length : 0;
	                            if ($(this).children().text() == "" && ($(this).text().length > 300 || parseInt(breaks) > 2))
	                                            $(this).css('height','auto');
	                    },
	                    function () {
	                            if ($(this).children().text() == "")
	                                            $(this).css('height','35px');
	                    }                              
	                );
                }*/
                //Added for CR-127
        var initiator 	  = document.getElementById("userID");
		var selectedValue  = document.forms[0].selectedInitiator.value;
		if(selectedValue == "" ){
		initiator.value = 0;	
		}else{
		initiator.value = selectedValue;	
		}
		
		$('#pages').pageMe({pagerSelector:'#suggestPages',childSelector:'.page',showPrevNext:true,hidePageNumbers:false,perPage:8});
});

/*
*	Name: fnViewSuggestions
*	Purpose: Used to View the Pending/Accepted/Rejected Suggestions
*/
function fnViewSuggestions(statusCode){
	//Added for CR-127
	var searchFlag = false;
	if (document.forms[0].selectedInitiator.value !=
             document.forms[0].userName.options
            [document.forms[0].userName.selectedIndex].value)
    {
    	searchFlag = true;
        var id = '207';
        hideErrors();
        addMsgNum(id);
        showErrors("errorlayer",-1);
        $("#errorlayer").removeClass("successTip");
		$("#errorlayer").addClass("errorTip");
    }
    if(searchFlag == false){	//Added for CR-127 ends here
	  document.forms[0].suggestStatusCode.value = statusCode;
	  document.forms[0].action="SuggestAction.do?method=fetchSuggestions";
	  document.forms[0].submit();
    } 
    
}

/*
*	Name: fnUpdateSuggestions
*	Purpose: Used to Accept the Suggestions
*/
// Added For CR_106
function fnUpdateSuggestions(suggestId,index,flag){
	  //Modified for CR_108
	  var suggest;
	  if ($('#txtSuggest'+index).length)
	 	 suggest = trim($('#txtSuggest'+index).val());
	  else
	  	 //suggest = $('#divSuggest'+index).html().replace(/\<br\>/gi, "\n"); Commented fr CR-131
	  	 suggest = trim($('#divSuggest'+index).val()); //Added for CR-131
		
		var adminComments = trim($('#suggestComments'+index).val());
		
		if (suggest == "")
		{ 
			var id = '908';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("divSuggest"+index,-1);
			return false;  
		}	
		else if (suggest.length > 4000)
		{
			var id = '909';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("divSuggest"+index,-1);   
			return false;
		}
		else if (adminComments.length > 2000)
		{
			var id = '912';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("suggestComments",index);  
			return false;
		}
		else {
		  $('#suggestId').val(suggestId);
		  if (flag=="Y") {
		 	$('#updateStatusCode').val(2);
		 
		 } else if(flag=="N")
		  {
		 	$('#updateStatusCode').val(3);
		 }
   	     //Modified for CR_106 Save Admin Comments by JG101007 
   	      else if(flag=="C")
		  {
		  	$('#updateStatusCode').val(4);
		  	
		  }
		 else if(flag=="S")
		  	{$('#updateStatusCode').val($('#suggestStatusCode').val());
		  	}else{
		  	}
		  	
		  $('#hdnAdminComments').val(adminComments);
		  $('#suggestions').val(suggest);
		  document.forms[0].action="SuggestAction.do?method=updateSuggestion";
		  document.forms[0].submit();	
		}
}

/*
*	Name: fnDeleteAttachment
*	Purpose: Used to delete attachment tied with the Suggestions
*/
function fnDeleteAttachment(suggestId,imgSeq){
	  $('#suggestId').val(suggestId);
	  $('#imgSeqNo').val(imgSeq);
	  document.forms[0].action="SuggestAction.do?method=deleteAttachment";
	  document.forms[0].submit();	
}

/*
*	Name: fnViewAttachment
*	Purpose: Used to open/save attachment tied with the Suggestions
*/
function fnViewAttachment(imgSeq,imgName){
  var URL="DownLoadImage.do?method=downloadImage&ImageSeqNo="+imgSeq+"&download=Y"+"&ImageName="+imgName+"";
  window.open(URL,'Attachment',"location=0,resizable=yes ,status=0,scrollbars=1,WIDTH="+screen.width+",height=600");
}

/*
*	Name: fnEditSuggestion
*	Purpose: Used to Edit the Suggestions from Pending Page
*/
function fnEditSuggestion(index){
        if ($('#txtSuggest'+index).length == 0 ){
          $('#divSuggest'+index).removeClass('commentdesc vtip').addClass('commentdesc').css('height','auto');
          var suggest = $('#divSuggest'+index).html().replace(/\<br\>/gi, "\n");
          $('#divSuggest'+index).hide().html('<TEXTAREA id="txtSuggest' + index + '" ROWS="8" COLS="145"></TEXTAREA>').slideDown();
          $('#txtSuggest'+index).val(suggest);
          $('#txtSuggest'+index).focus();
        }
}
//Added for CR-118 by vb106565
function fnsuggestionsToExcel(){
	
	 if (document.forms[0].roleName.options[document.forms[0].roleName.selectedIndex].value =="-1")	 
	  {
			var id = '1023';
			hideErrors();
			addMsgNum(id);
			showScrollErrors("sltRoles",-1);
	  } else {
			
			var statusCode = document.forms[0].roleName.options[document.forms[0].roleName.selectedIndex].value;
			document.forms[0].suggestStatusCode.value = statusCode;
			document.forms[0].action="SuggestAction.do?method=suggestionsToExcel";
			document.forms[0].submit();
	  }
} 		
	
//Added for CR-127
function fnSearch(){
	var selectedInitiator = document.forms[0].userName.options[document.forms[0].userName.selectedIndex].value;
	document.forms[0].selectedInitiator.value = selectedInitiator;
	document.forms[0].action="SuggestAction.do?method=fetchSuggestions";
	document.forms[0].submit();
}	

//Added for CR-127 ends here

/* Pagination as part of CR-131 starts */

$.fn.pageMe = function(opts){
    var $this = this,
        defaults = {
            perPage: 4,
            showPrevNext: false,
            numbersPerPage: 10,
            hidePageNumbers: false
        },
        settings = $.extend(defaults, opts);
    
    var listElement = $this;
    var perPage = settings.perPage; 
    var children = listElement.children();
    var pager = $('.pagination');
    
    if (typeof settings.childSelector!="undefined") {
        children = listElement.find(settings.childSelector);
    }
    
    if (typeof settings.pagerSelector!="undefined") {
        pager = $(settings.pagerSelector);
    }
    
    var numItems = children.size();
    var numPages = Math.ceil(numItems/perPage);

    pager.data("curr",0);
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="prev_link" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>').appendTo(pager);
    }
    
    var curr = 0;
    while(numPages > curr && (settings.hidePageNumbers==false)){
        $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
        curr++;
    }
  
    if (settings.numbersPerPage>1) {
       $('.page_link').hide();
       $('.page_link').slice(pager.data("curr"), settings.numbersPerPage).show();
    }
    
    if (settings.showPrevNext){
        $('<li><a href="#" class="next_link" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>').appendTo(pager);
    }
    
    pager.find('.page_link:first').addClass('active');
    if (numPages<=1) {
        pager.find('.next_link').hide();
    }
  	pager.children().eq(1).addClass("active");
    
    children.hide();
    children.slice(0, perPage).show();
    
    pager.find('li .page_link').click(function(){
        var clickedPage = $(this).html().valueOf()-1;
        goTo(clickedPage,perPage);
        return false;
    });
    pager.find('li .prev_link').click(function(){
        previous();
        return false;
    });
    pager.find('li .next_link').click(function(){
        next();
        return false;
    });
    
    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }
     
    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }
    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;
        
        children.css('display','none').slice(startAt, endOn).show();
        
        if (page>=1) {
            pager.find('.prev_link').show("slow");
        }
        else {
            pager.find('.prev_link').hide("slow");
        }
        
        if (page<(numPages-1)) {
            pager.find('.next_link').show("slow");
        }
        else {
            pager.find('.next_link').hide("slow");
        }
        
        pager.data("curr",page);
       
        if (settings.numbersPerPage>1) {
        	if (numPages > settings.numbersPerPage)	{
	       		$('.page_link').hide();
	       		if (settings.numbersPerPage+page <= numPages)
	       			$('.page_link').slice(page, settings.numbersPerPage+page).show();
	       		else
	       			$('.page_link').slice(page-((settings.numbersPerPage+page)-numPages), settings.numbersPerPage+page).show();
        	} else {
        		$('.page_link').show();
        	}
    	}
      
      	pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");  
    }
};


/************************/

