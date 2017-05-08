<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/bootstrap-table.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<LINK REL="stylesheet" TYPE="text/css" HREF="css/jquery-ui-custom.min.css"> 
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Others/jquery-ui.custom.min.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-all.min.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap-table-en-US.min.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	<SCRIPT type="text/javascript" src="js/Others/webtoolkit.aim.js"></SCRIPT>
	<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
	<SCRIPT LANGUAGE="JavaScript" SRC="js/CompInOrderReport.js"></SCRIPT>
</HEAD>

<BODY class="main">

<div class = "container-fluid">

<html:form action="/CompGroupAction" method="post" styleClass="form-horizontal">
	
	<div class="spacer10"></div>
		
	<h4 class ="text-center">Components in Orders MAP Report</h4>
	
	<div class="errorlayerhide" id="errorlayer">
	</div>
	
	<div class="spacer10"></div>
	
	<logic:present name="CompGroupMaintForm" property="messageID"
		scope="request">
		<script>
	    <bean:define id="messageID" name="CompGroupMaintForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>

        </script>
	</logic:present>
	
	<logic:present name="CompGroupMaintForm" property="compgroupList">
		<bean:size id="CompGrpListLen" name="CompGroupMaintForm"
			property="compgroupList" />
	</logic:present>

	<TABLE class="table table-bordered" id="tbCompInOrdersMap">
		<logic:iterate id="ordList" name="CompGroupMaintForm"
			property="orderList" type="com.EMD.LSDB.vo.common.OrderVO">
			<TD colspan="2" CLASS='text-center v-midalign' width="20%">
			<TABLE class="table table-bordered">
				<TR valign="top">
					<TD CLASS='heading-fontbgcolor' width="50%"><strong>Order No</strong></TD>
					<TD CLASS='text-left v-midalign'>
						<bean:write name="ordList" property="orderNo" />
					</TD>
				</TR>
				<TR valign="top">
					<TD CLASS='heading-fontbgcolor'  width="50%"><strong>Customer Name</strong></TD>
					<TD CLASS='text-left v-midalign'>
						<bean:write name="ordList" property="customerName" />
					</TD>	
				</TR>
				<TR valign="top">
					<TD CLASS='heading-fontbgcolor'  width="50%"><strong>Spec Status</strong></TD>
					<TD CLASS='text-left v-midalign'>
						<bean:write name="ordList" property="statusDesc" />
					</TD>	
				</TR>	
			</TABLE>						
			</TD>
		</logic:iterate>
			
		<logic:iterate id="specList" name="CompGroupMaintForm"
			property="specItemList" type="com.EMD.LSDB.vo.common.CompGroupVO">
			<TR valign="top">
				<logic:iterate id="ordList" name="specList"
					property="specificationItemVO" type="com.EMD.LSDB.vo.common.SpecificationVO">
					<TD colspan="2" CLASS='text-center'>
						<TABLE class="table table-bordered">
							<logic:notEqual name="ordList" property="specSeqNo" value="0">
								<TR valign="top">
									<TD CLASS='heading-fontbgcolor text-center v-midalign' colspan="2"><b><bean:write name="specList" 
										property="specDesc" /></b>
									</TD>
								</TR>
							</logic:notEqual>
							<logic:equal name="ordList" property="specSeqNo" value="0">
								<TR valign="top">
									<TD CLASS='text-center' colspan="2">&nbsp;</TD>
								</TR>
							</logic:equal>
							<logic:iterate id="itemList" name="ordList"
							property="specItemList" type="com.EMD.LSDB.vo.common.SpecificationItemVO">
								<TR valign="top" rowspan="<bean:write name="ordList" 
										property="itemCount" />">
									<logic:empty name="itemList" property="itemDesc">
										<TD CLASS='text-center ' width="50%">&nbsp;</TD>
										<TD CLASS='text-center '>&nbsp;</TD>
									</logic:empty>	
									<logic:notEmpty name="itemList" property="itemDesc">
									    <TD CLASS='text-center v-midalign' height="20%" width="50%"><bean:write name="itemList" 
											property="itemDesc" />
										</TD>
										<logic:empty name="itemList" property="itemValue">
											<TD CLASS='text-center v-midalign'>&nbsp;</TD>
										</logic:empty>	
										<logic:notEmpty name="itemList" property="itemValue">
											<TD CLASS='text-center v-midalign'><bean:write name="itemList" 
												property="itemValue" />
											</TD>
										</logic:notEmpty>	
									</logic:notEmpty>
								  </TR>
							  
							</logic:iterate>	
						</TABLE>
					</TD>		
			    </logic:iterate>
    	   	</TR>	 
	
		</logic:iterate>
	  
		<logic:iterate id="orderList" name="CompGroupMaintForm"
			property="compgroupList" type="com.EMD.LSDB.vo.common.CompGroupVO" indexId="counter">
			<logic:equal name="counter" value="0">
			<TR valign="top">
				<logic:iterate id="compList" name="orderList"
				property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO">
					<logic:equal name="counter" value="0">
							<TD CLASS='heading-fontbgcolor text-center  bd-right-one'><strong>COMPONENT GROUP</strong></TD>
							<TD CLASS='heading-fontbgcolor text-center  bd-right-one'><strong>COMPONENT</strong></TD>
					</logic:equal>
				</logic:iterate>
			</TR>
			</logic:equal>
		    <TR valign="top">
		    <logic:iterate id="compList" name="orderList" property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO">
		     	<logic:equal name="compList" property="componentGroupSeqNo" value="0">
					<TD CLASS='text-center '>&nbsp;</TD>
					<TD CLASS='text-center '>&nbsp;</TD>
				</logic:equal>
				<logic:notEqual name="compList" property="componentGroupSeqNo" value="0">
					<TD CLASS='text-center v-midalign '><bean:write name="orderList" 
						property="componentGroupName" />
					</TD>
					<logic:empty name="compList" property="componentName">
						<TD CLASS='text-center v-midalign '>&nbsp;</TD>
					</logic:empty>
					<logic:notEmpty name="compList" property="componentName">
						<logic:equal name="compList" property="compColorFlag" value="GREEN">
						<TD CLASS='text-center v-midalign ' bgcolor="green"><FONT color="white"><bean:write name="compList" 
						property="componentName" /></FONT>
						</logic:equal>
						<logic:equal name="compList" property="compColorFlag" value="LIGHT_GREEN">
						<TD CLASS='text-center v-midalign ' bgcolor="lightgreen"><bean:write name="compList" 
						property="componentName" />
						</logic:equal>
						<logic:equal name="compList" property="compColorFlag" value="NONE">
						<TD CLASS='text-center v-midalign'><bean:write name="compList" 
						property="componentName" />
						</logic:equal>
					</logic:notEmpty>
					</TD>
				</logic:notEqual>
			</logic:iterate>
			</TR>
		</logic:iterate>
	   </TABLE>
	   

	<div class="spacer20"></div>
		
	<div class="row">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' id="btnPrint" ONCLICK="window.print();">Print</button>
			<button class="btn btn-primary" type='button' id="btnCancel" ONCLICK="window.close();">Cancel</button>
		</div>
	</div>
	
	<div class="spacer50"></div>
				
</html:form>

</div>
</BODY>
</html:html>
