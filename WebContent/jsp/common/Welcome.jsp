<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<div class="container-fluid">
	<html:form action="/LogoutAction" method="post">  
		<div class="row">
			<div class="col-sm-12">
				<h4 class="text-center">Welcome To Locomotive Spec Database</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="spacer20"></div>
			</div>
		</div>
		
		<noscript>
			<!-- To check if Scripts are enabled  -->
			<h4 class="bg-warning text-center text-uppercase">
		    	<strong>This site works best with Javascript enabled.</strong>
			</h4>
		</noscript>
		
		<!--[if lte IE 9 ]>    
			<h4 class="bg-danger text-center text-uppercase">
		    	<strong>Support for Internet Explorer 9 and below is dropped. Please update to latest version to have better experience.</strong>
			</h4>
		<![endif]-->
		
		<div class="col-sm-12">
		    <h4 class="text-center"><B>Message Of The Day</B></h4>
		</div>		
		<div class="col-sm-12">
			<div class="spacer20"></div>
		</div>
		
		<div class="col-sm-12">
	
				<logic:equal name="UserMaintForm" property="roleID" value="ADMN">
						<div id="Container">
		   					<div id="Container_Text" onclick="switchToEdit();" style="cursor: pointer;" class="text-center">
		   						<!--Updated for CR-112 for QA Fix -->
								<bean:define id="welcomeMsg" name="UserMaintForm" property="hdnMessage"/>
								<div class="col-sm-8 col-sm-offset-2 welcomeMsgText">
		   						<mark data-toggle="tooltip" title="Click to edit Message of the Day"><%=(String.valueOf(welcomeMsg)).replaceAll("\n","<br>")%></mark>
		   						</div>
					   			<!--Updated for CR-112 for QA Fix  Ends Here -->
							</div>
							<div id="Container_Edittable" style="display: none;">
								<div class="col-sm-8 col-sm-offset-2">
									<div class="form-group">
										<textarea class="form-control" rows="6" id="Message" maxlength="1000"></textarea>
									</div>
									<div class="form-group text-center">
										<button type="button" onclick="switchBack(true);" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Save Message</button>
										<button type="button" onclick="switchBack(false);" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Cancel Edit</button>
									</div>
				    				<html:hidden property="hdnMessage" styleId="hdnMessage" />
			    				</div>
		        	   		</div>
			
						</div>
				</logic:equal>
				<logic:notEqual name="UserMaintForm" property="roleID" value="ADMN">
				    <div class="text-center">
						<!--Updated for CR-112 for QA Fix --> 
		   						<bean:define id="welcomeMsg" name="UserMaintForm" property="hdnMessage"/>
		   						<%=(String.valueOf(welcomeMsg)).replaceAll("\n","<br>")%>
			   			<!--Updated for CR-112 for QA Fix  Ends Here -->
					</div>
				</logic:notEqual>
					
		</div>
		
		<div class="col-sm-12">
			<div class="spacer50"></div>
		</div>
		
		<div class="col-sm-10 col-sm-offset-1">
			<IMG SRC="images/Front-Composite.jpg" class="img-thumbnail img-responsive center-block"></IMG>
		</div>

		<div class="col-sm-12">
				<h5 class="text-center text-info"><B>Locomotive Spec Database(LSDB)</B>
				will be used by Systems Engineering, Inquiry Group and Area Sales
				departments for the purpose of creating original spec documents for
				locomotive and component set orders.</h5>
		</div>
	</html:form>
</div>