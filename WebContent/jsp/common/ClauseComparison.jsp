		<div class="modal fade" id="hiddenJunkCharClause" tabindex="-1" role="dialog" aria-labelledby="hdnJunkCharCla">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					      	<h3 class="modal-title" id="hdnJunkCharCla">Review Clause Description</h3>
					      </div>
					      <div class="modal-body">	
					      	<div class="form-horizontal">
					      		<h4 class="text-center"><strong>Old Clause Description</strong></h4>			
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<div id='oldClauseDesc'></div>
									</div>
								</div>
								<div class="spacer10"></div>
								<h4 class="text-center"><strong>New Clause Description</strong></h4>			
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<div id='newClauseDesc'></div>
									</div>
								</div>
							</div>
						  </div>
						  <div class="modal-footer">
								<div class="form-group">
									<p class="text-center"><mark><strong><FONT color='red'>Note:</FONT> Rejecting the new clause description shall add junk characters to the Spec.</strong></mark></p>
								</div>
						  		<button type="button" class="btn btn-primary" id="btnAcceptClause" data-url="" data-clausedescID="">Accept</button>
								<button type="button" class="btn btn-primary" id="btnRejectClause" data-url="">Reject</button>
								<button type="button" class="btn btn-default" id="btnCancelClause" data-dismiss="modal">Cancel</button>
						  </div>
						  
					</div>
			</div>
		</div>	
			<!-- <TABLE WIDTH="100%" ALIGN="center">
					<TR>
						<TD colspan=2><HR></TD>
					</TR>
					<TR>
						<TD colspan=2 align="center"><B><FONT color='red'>Note:</FONT> Rejecting the new clause description shall add junk characters to the Spec.</B></TD>
					</TR>
			</TABLE> -->
	
	<SCRIPT language="JavaScript" SRC="js/ClauseComparison.js"></SCRIPT>