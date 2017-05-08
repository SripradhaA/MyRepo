
<script type="text/javascript">
/*Added for CR_88*/
$(document).ready(function() {
	 $("#clauseDesc_id").tinymce({    
	      	script_url : 'js/Others/tiny_mce/tinymce.min.js',
        	content_css : "css/tinymce.css",
	      	convert_urls : false,
			theme : "modern",
			/*added entity '&' for removing &nbsp; and should be replaced with normal space for CR_92*/
			entity_encoding : "named",
			selector : "textarea#clauseDesc_id",
			plugins : "paste",
			toolbar : "bold,italic,|,removeJunkChar,|,undo,redo,|,paste",// Commented for CR_104 ,|,spellchk",
			menubar : false,
			statusbar : true,
			resize: false,
			width : 450,
			height: 200,
			browser_spellcheck : true,
			valid_elements: "strong/b,em/i,p,br",
			//remove_linebreaks : "true",
			//convert_newlines_to_brs : "true",
        	//force_br_newlines : "true",
			//force_p_newlines : "false",
			//forced_root_block : "false",
			paste_auto_cleanup_on_paste : "true",
			paste_remove_spans : "true",
			paste_remove_styles : "true",
			paste_as_text: true, //Added for preserve double spaces -CR-130
			paste_preprocess : function(pl, o) {
            	// Remove empty paragraphs while pasting from word
	            o.content = o.content.replace(/<p>\u00a0<\/p>/gi, ''); 
	            o.content = o.content.replace(/<p>&nbsp;<\/p>/gi, '');
        	},
    	   	paste_postprocess : function(pl, o) { //Added for CR-121
            	tinymce.activeEditor.theme.panel.find('#wordcount').text(['{0}', tinymce.activeEditor.getContent().replace(/(<([^>]+)>)/ig,"").length]);
	        },
			setup : function(ed) {
				// Add a custom button
				/* Commented for CR_104 making the spell checker part of Add button
				ed.addButton('spellchk', {
					title : 'Spell Check',
					onclick : function() {
					// Call to spell check function
					//popUpCheckSpellingrapidSpellWebLauncher('rsTCIntrapidSpellWebLauncher');
					rswi1_SpellChecker.OnSpellButtonClicked();    
					}
				});*/
				//Added for CR-114 Remove Junk Character Functionality
				ed.addButton('removeJunkChar', {
					title : 'Remove Junk Characters',
					onclick : function() {
						bootbox.confirm("Are you sure you want to remove the junk characters?", function(result) {
					    	if (result)	{
							var oldClauseDesc = trim($('#clauseDesc_id').val());
							$.getJSON("commonAppUtilAction.do?method=validateClauseDescriptionThruAJAX",{clauseDesc:oldClauseDesc, ajax: 'true',cache: 'false'}, function(data){
    						if (data != null)
    							{		
									$.each(data.Clause, function(i,item) 
									{
						    			if(item.claDiffFlag=='Y')
							    			{
			            						$('#clauseDesc_id').val(item.newClauseDesc);
			            						ed.on('Undo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
			            						ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
						
			         						}
		         					});
		         				}
		         			});	
		         		}
						}); 
					},
					image: "images/junkremove.png"
				});
				//Added for CR-114 Remove Junk Character Functionality Ends
				ed.on('PostProcess', function(e) {
                    // Remove empty paragraphs on submitting the clause description
                   	//e.content = e.content.replace(/<p><\/p>/g, '');
                 });  
              	
              	ed.on('init', function() {
              		// Remove empty paragraphs when loading existing Clause Descriptions
              		//ed.getBody().setAttribute('spellcheck', true);
              		//Modified for CR_131 to enable spell check on load
              		setTimeout(function () {
						tinymce.execCommand('mceSpellCheck', true);
					 }, 1);
              		//Added for CR-129 for loading clauses in TinyMCE Issue
					if ($('#hdnclauseDescForTextArea').length > 0) {
	              		var preFormattedtext = (($('#hdnclauseDescForTextArea').val()).replace(/\n\n/g, '</p><p>&nbsp;</p>')).replace(/\n/g, '</p><p>');
	              		tinymce.activeEditor.setContent(preFormattedtext);
	              	} 
              		//Added for CR-129 for loading clauses in TinyMCE Issue ends here
              		var statusbar = ed.theme.panel && ed.theme.panel.find('#statusbar')[0];
					if (statusbar) {
	              		statusbar.insert({
							type: 'label',
							name: 'wordcount',
							text: ['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length],
							classes: 'wordcount',
							disabled: ed.settings.readonly
						}, 0);
					}
					$(ed.getContainer()).find(".mce-path").css("opacity", "0");
              	});
              	ed.on('keyup', function(e) {
              		ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 });
   				 
   				 ed.on('focus', function(e) {
   				 	if($(".scrollError").val()==""){
              			$(".scrollError").tooltip("disable");
	    	 			$(".scrollError").attr("title","");
	    	 			$(".scrollError").removeClass("scrollError");
    	 			}
   				 });
   				 
   				ed.on('change', function(e) { 
	               ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 }); 
   				//Added for CR-114 QA Fixes 
   				ed.on('Undo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
   				ed.on('Redo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
   				ed.on('keypress', function(e) {
		 	       ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 });
  			}
						
						
		 });
}); 
</script>
<script type="text/javascript">
function RSCustomInterface(tbElementName){
	this.tbName = tbElementName;
	this.getText = getText;
	this.setText = setText; 
	function getText(){  
		return  tinyMCE.get(this.tbName).getContent();
	}
	function setText(text){
	    tinyMCE.activeEditor.setContent(text);
  	}
}
</script>