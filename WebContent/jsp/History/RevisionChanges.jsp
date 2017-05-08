<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<HEAD>
<SCRIPT type="text/javascript">
	function showAll()
	{
		 $('.panel-collapse').collapse('show');
		
	}	
	function hideAll()
	{
		 $('.panel-collapse').collapse('hide');
		    
	}
</SCRIPT>
</HEAD>

<div class="container-fluid"> 

<html:form action="/RevisionChangesAction" method="post">

	<h4 class="text-center"><bean:message key="Application.Screen.RevisionHistory" /></h4>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>

	<logic:present name="RevisionChangesForm" property="messageID"
		scope="request">	
	  	<bean:define id="messageID" name="RevisionChangesForm" property="messageID"/>
  		<input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>">
 		<input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>">
	</logic:present>
	
	<div class="row">
		<div class="col-sm-2">
			<A CLASS=linkstyleItem HREF="javascript:showAll()">Expand All</A>&nbsp;|&nbsp;<A CLASS=linkstyleItem HREF="javascript:hideAll()">Collapse All</A>
		</div>
	</div>
	
	<div class="row">
		<div class="spacer20"></div>
	</div>
			
	<logic:iterate id="revision" name="RevisionChangesForm"
	property="revisionDetails" type="com.EMD.LSDB.vo.common.RevisionChangesVO">
	 
			<div class="panel panel-info">
				<div class="panel-heading" role="tab" id="heading<bean:write name='revision' property='revisionID'/>">
			      <h4 class="panel-title">
			      	<a role="button" data-toggle="collapse" aria-expanded="false" class="collapsed" aria-controls="<bean:write name='revision' property='revisionID'/>" href="#<bean:write name='revision' property='revisionID'/>"><strong><bean:write name="revision" property="revisionDesc"/></strong></a>			      	
		      		<div style="float:right"><small><em> Released on <bean:write name="revision" property="revisionRelDate" /> </em></small></div>
			      </h4>
			    </div>
			    <div id="<bean:write name='revision' property='revisionID'/>" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading<bean:write name='revision' property='revisionID'/>">
		      		<div class="panel-body">
						<div align="left" style="width:100%" class="revdesc">
							<ul class="revUList">
							<logic:iterate id="revisionItem" name="revision" property="arlRevisionItem">
								<li class="revList"><bean:write name="revisionItem" property="revisionItemDesc" /> </li>
								<div class="spacer10"></div>
							</logic:iterate>	
							</ul>
						</div>
			 		</div>
		    	</div>
		  	</div>
		  	
  	</logic:iterate>
					
	<!--<logic:iterate id="revision" name="RevisionChangesForm"
			property="revisionDetails" type="com.EMD.LSDB.vo.common.RevisionChangesVO">
			<center>
			<div style="width:90%" class="rev" onclick="javascript:showORhide(<bean:write name='revision' property='revisionID'/>)">
				<div align="left" style="width:100%;height:20%" class="revHeading">
					<bean:write name="revision" property="revisionDesc"/>  
				</div>
				<div id="<bean:write name='revision' property='revisionID'/>" style="display:none" class="hidden"> 
					<div style="float:right;width:30%" class="revdate"> 
					<I> Released on <bean:write name="revision" property="revisionRelDate" /> </I>
					</div> 
					<BR/>
					<div align="left" style="width:100%" class="revdesc">
					<ul class="revUList">
					<logic:iterate id="revisionItem" name="revision" property="arlRevisionItem">
							<li class="revList"><bean:write name="revisionItem" property="revisionItemDesc" /> </li>
					</logic:iterate>	
					</ul>
					</div>
				</div>	
			</div>	
			</center>
		<div style="height:5%"></div>
		
		</logic:iterate> 
		
		-->	


</html:form>
</div>
