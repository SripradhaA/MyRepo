/*
 * Name 	:	fnValidateClauseDesc
 * Purpose	:	To Validate the clause description through AJAX
 */
function fnValidateClauseDesc(clauseDesc,url,clausedescID)
{
var oldClauseDesc = trim(clauseDesc);
$.getJSON("commonAppUtilAction.do?method=validateClauseDescriptionThruAJAX",{clauseDesc:oldClauseDesc, ajax: 'true',cache: 'false'}, function(data){
    	if (data != null)
    	{		
			$.each(data.Clause, function(i,item) 
			{
    			if(item.claDiffFlag=='Y')
    			{
	    			$('#oldClauseDesc').html(oldClauseDesc);
	    			$('#newClauseDesc').html(item.newClauseDesc);
	            	//fnShowModal('hiddenJunkCharClause',url,clausedescID);
	            	$('#btnAcceptClause').data("url",url);
	            	$('#btnRejectClause').data("url",url);
	            	$('#btnAcceptClause').data("clausedescID",clausedescID);
	            	$('#hiddenJunkCharClause').modal();
	            }
	           	else
	           	{ 
					document.forms[0].action=url;
					document.forms[0].submit();
		        }
		    });
	   	}
	   	//Added For CR_100 to handle Ajax related exceptions
            else {
                            window.location = "AjaxException.do";
            }
	   	//CR_100 Ends here
    });
}

/*
 * Name 	:	fnShowModal
 * Purpose	:	To Display the junk characters in Clause Descriptions
*/

function fnShowModal(id,url,clausedescID)
{
var divHeight = $("#"+id).height();
$('#'+id).modal({
		minHeight:75,
		minWidth:400,
		escClose:true,
		//Commented persist to fix issue with cancel button
		//persist:true,
		onShow: function(dialog) {
						if ( divHeight > 300)	{
				        	$(dialog.container).css('height', '350px');
				        	$(dialog.container).css('overflow', 'auto');
				        	}
				    },
		onOpen: function (dialog) {
			dialog.overlay.fadeIn('fast', function () {
				dialog.data.hide();
				dialog.container.fadeIn('fast', function () {
					dialog.data.slideDown('fast');
				});
			});
		}
	});
//Added unbind functions for repeated clause addition fixes
$('#btnAcceptClause').unbind('click');
$('#btnRejectClause').unbind('click');
$('#btnAcceptClause').click(function(){	fnAcceptClauseDesc(url,clausedescID); });
$('#btnRejectClause').click(function(){	fnRejectClauseDesc(url); });
}

$('#hiddenJunkCharClause').on('shown.bs.modal', function () {
  	//Added unbind functions for repeated clause addition fixes
	$('#btnAcceptClause').unbind('click');
	$('#btnRejectClause').unbind('click');
	$('#btnAcceptClause').click(function(){	fnAcceptClauseDesc(); });
	$('#btnRejectClause').click(function(){	fnRejectClauseDesc(); });
})

/*
 * Name 	:	fnAcceptClauseDesc
 * Purpose	:	To Accept the Clause Description suggested by DB 
 */
 
function fnAcceptClauseDesc()
{
	var txt=$('#newClauseDesc').html();
	tinymce.activeEditor.setContent(txt);//Updated for CR-121
    $("input[type=button]").attr("disabled", "disabled");
	document.forms[0].action=$('#btnAcceptClause').data("url");
	document.forms[0].submit();
}

/*
 * Name 	:	fnRejectClauseDesc
 * Purpose	:	To Reject the Clause Description suggested by DB 
 */
 
function fnRejectClauseDesc()
{
    $("input[type=button]").attr("disabled", "disabled");
	document.forms[0].action=$('#btnRejectClause').data("url");
	document.forms[0].submit();
}