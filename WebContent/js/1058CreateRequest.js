/*
 *	Name: document.ready function
 *	Purpose: Initializes JSON fetching all required order numbers and uses jquery ui to load suggestions
 *
 * Added few changes for CR-116 for set disabled button background color */
$(document).ready(function() {

	$('#createLsdb').prop("disabled","true");
	$('#createNLsdb').prop("disabled","true");
	$('#orderNo').prop("disabled","true");

	$.getJSON("ChangeRequest1058Action.do?method=loadOrdersFor1058", {ajax: 'true'}, function(j) {
	    	
          
	        if (j != null)
	        {
	              var jsonObj = $.map(j.orderNoList, function(item) {

	            	var mdlName ="";
	            	
	            	if(item.mdlName!=null)
	        				mdlName="MODEL : "+item.mdlName;
	            	
	            	 return {
	            		 "label": item.orderNo,
	                     "value": item.orderKey,
	                     "desc": mdlName,
	                     "nonLsdbFlag":item.nonLsdbFlag
	                };
	                
	            });
	              
	        }
	        
	        var orders = new Bloodhound({
	      	  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('label'),
	      	  queryTokenizer: Bloodhound.tokenizers.whitespace,
	      	  local: jsonObj
	        });
	        
	        $('.typeahead').typeahead({
		  		  hint: false,
		  		  highlight: true,
		  		  minLength: 1
	        	},
		  		{
		  		  display: 'label',
		  		  limit: 15,
		  		  source: orders,
		  		  templates: {
		  			  	empty: [
		  	                '<p class="empty-message" id="emptyorders">&nbsp;&nbsp;<strong>No existing orders/1058s found</strong></p>'
		  	                ].join('\n'),
			  		    suggestion: function (data) {
			  		    	
			  		    	if (data.nonLsdbFlag=="Y") {
				  		    	if (data.desc != "")
				  		    		return '<p class="text-danger">' + data.label + ' - ' + data.desc + '</p>';
				  		    	else
				  		    		return '<p class="text-danger">' + data.label + '</p>';
			  		    	} else {
			  		    		return '<p>' + data.label + ' - ' + data.desc + '</p>';
			  		    	}
			  		    	
			  		    }
		  		  }
		  	});

	        $("#orderNo").keydown(function (e) {
	        	var code = (e.keyCode ? e.keyCode : e.which);
                if (code == 13) {
                	
                    return false;
                    
                } else {
    	        		
	                	$('#orderKey').val("");
		        	    $('#createNLsdb').removeAttr("disabled");
		        	  	$('#createLsdb').prop("disabled","true");
		        	 
                }
	        });
	        
	        $('.typeahead').on('typeahead:select', function(ev, suggestion) {

		        	  if (suggestion.nonLsdbFlag == 'Y')	{
		        		  $('#orderKey').val("");
		        	  	  $('#createNLsdb').removeAttr("disabled");
		        	  	  $('#createLsdb').prop("disabled","true");
		        	  }	else {
		        		  $('#orderKey').val(suggestion.value);
		        	  	  $('#createNLsdb').prop("disabled","true");
		        	  	  $('#createLsdb').removeAttr("disabled");
		        	  }
		        	  
        	});

	    	$('.loadOrders').remove();  
	    	$('#orderNo').removeAttr("disabled");
	        $('#orderNo').show()
	    	$('#orderNo').focus();
	        
	  });
	
});

/*
 *	Name: fnCreateLSDBRequest
 *	Purpose: Used to Create LSDB Request	
 */
function fnCreateLSDBRequest() {
    if ($('#orderNo').val().length == 0 || $('#orderNo').val() == "") {
        var id = '105';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("orderNo",-1);
        return false;
    }
    else {
        document.forms[0].action = "ChangeRequest1058Action.do?method=create1058LSDBRequest";
        document.forms[0].submit();
        $('button').prop('disabled', true);
    }
}

/*
 *	Name: fnCreateNonLSDBRequest
 *	Purpose: Used to create non-LSDB Request
 *	
 */
function fnCreateNonLSDBRequest() {
    if ($('#orderNo').val().length == 0 || $('#orderNo').val() == "") {
        var id = '105';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("orderNo",-1);
        return false;
    }
    else {
        var conf = window.confirm("Are you sure to create 1058 for NON-LSDB Specification - " + $('#orderNo').val() + "?", "YesNo");
        if (conf)
        {
            document.forms[0].action = "ChangeRequest1058Action.do?method=create1058NonLSDBRequest";
            document.forms[0].submit();
            $('button').prop('disabled', true);
        }
    }
}
