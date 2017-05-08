<div class="modal fade" id="suggest" tabindex="-1" role="dialog" aria-labelledby="suggestionBoxModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">		      	
		      <form action="SuggestAction.do?method=submitSuggestion&screenid=24" id="suggestForm" method="post" enctype="multipart/form-data">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      	<h4 class="modal-title" id="suggestionBoxModal">Suggestion Box</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">User Name:</label>
						<div class="col-sm-6">
							<input type='text' CLASS="form-control disabled" id="userName" value='<%=session.getAttribute("FullName")%>' disabled="disabled"/> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Screen Name:<font color=red size=2>*</font></label>
						<div class="col-sm-6"> 
							<input type='text' name="screenName" id="screenName" class="form-control" size="55"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Suggestion:<font color=red size=2>*</font></label>
						<div class="col-sm-6"> 
							<textarea name="suggestions" class="notAssigned form-control" id="suggestion" rows="7" cols="60" style="overflow:auto"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Attachment:</label>
						<div class="col-sm-6">
							<div class="input-group">
								<input type="text" class="form-control input-file-readonly" id="suggestFileUpload" readonly/>
				                <span class="input-group-btn">
				                    <span class="btn btn-primary btn-file">
				                        Browse&hellip; <input type='file' class="form-control" name="uploadedFile" id="uploadedFile"/>
				                    </span>
				                </span>		                
			            	</div> 
						</div>
					</div>
				</div>
			  </div>
			  <div class="modal-footer">
			  		<button type="submit" class="btn btn-primary" id="submitSugg">Save</button>
					<button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
			  </form>
		</div>
	</div>
</div>

<div class="modal fade" id="suggestSuccess" tabindex="-1" role="dialog" aria-labelledby="suggestionSuccessModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      	<h4 class="modal-title" id="suggestionSuccessModal">Suggestion Box</h4>
		      </div>
		      <div class="modal-body">
					<div class="text-center"><IMG SRC="images/emd_logo.gif" ></div>
					<div class="spacer10"></div>
					<h4 class="text-center">Thank you. Your suggestion has been submitted.</h4>
			  </div>
			  <div class="modal-footer">
			  		<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
			  </div>
		</div>
	</div>
</div>
			
<nav class="navbar navbar-default navbar-fixed-bottom navbar-small">
	<SCRIPT type="text/javascript">
		var currentTime = new Date();
		var year = currentTime.getFullYear();
		/* Added for make new line entry when press enter key inside the suggestion box textarea */
		function setSelRange(inputEl, selStart, selEnd) { 
		 if (inputEl.setSelectionRange) { 
		  inputEl.focus(); 
		  inputEl.setSelectionRange(selStart, selEnd); 
		 } else if (inputEl.createTextRange) { 
		  var range = inputEl.createTextRange(); 
		  range.collapse(true); 
		  range.moveEnd('character', selEnd); 
		  range.moveStart('character', selStart); 
		  range.select(); 
		 } 
		}
		function cellFieldKeyDown(ev){
		 return;
		}
		/* Added for make new line entry when press enter key inside the suggestion box textarea - Ends here */
		$(document).ready(function() { 
			 $("textarea#suggestion").keypress(function(e) {     
			 	if(e.keyCode == 13)      { 
			 			$("textarea#suggestion").val($("textarea#suggestion").val()+"\n");
			 			var cellField = document.getElementById("suggestion");
			 			setSelRange(cellField, cellField.value.length, cellField.value.length);/*Added for make new line entry when press enter key inside the suggestion box textarea*/
			 			return false;     
					}       
			 });    
		})
	</SCRIPT>	
	<div class="container-fluid">	
		<h5 class="navbar-footer-text">Copyright &copy; <script type="text/javascript">document.write(year)</script> Electro-Motive Diesel, Inc</h5>
	</div>
</nav>