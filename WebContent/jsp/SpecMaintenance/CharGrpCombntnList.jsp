<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/EMDCustomTags.tld" prefix="LSDB"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<HEAD>
<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
<link href="css/EMDCustom.css" rel="stylesheet"/>
<link rel="shortcut icon" href="images/favicon.ico" />
<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<div class="container" width="90%">
<html:form action="/OrderSection" method="post"	enctype="multipart/form-data" styleClass="form-horizontal">

	<!-- Application PageName Display starts here-->
	<h4 class="text-center"><bean:message key="Application.Screen.CharCombntnEdlMap" /></h4>	
	<!-- Application PageName Display Ends here -->

	<!-- Validation Message Display Part Starts Here -->

	<div class="errorlayerhide" id="errorlayer">
	</div>

	<!-- Validation Message Display Part Ends Here -->


	<logic:present name="OrderSectionForm" property="charGrpCombntnList"
		scope="request">
		<bean:size name="OrderSectionForm" id="chargrpcombnsize"
			property="charGrpCombntnList" />
	</logic:present>

	<logic:match name="OrderSectionForm" property="method"
		value="fetchCharCombntnEdlMap">
		<logic:lessThan name="chargrpcombnsize" value="1">
			<script>
				var id='886';
				hideErrors();
				addMsgNum(id);
				showScrollErrors();
			</script>
		</logic:lessThan>
	</logic:match>




	<logic:greaterThan name="chargrpcombnsize" value="0">
		<logic:present name="OrderSectionForm" property="charGrpCombntnList">
			<TABLE class="table table-bordered">
				<thead>
					<TR>
						<TH width="25%">Characteristic Group(s)</TH>
						<TH width="30%">Characteristic Component(s)</TH>
						<TH width="10%">EDL#</TH>
						<TH width="10%">RefEDL#</TH>
					</TR>
				</thead>
				<tbody>
					<logic:iterate id="charGrpCombntnList" name="OrderSectionForm"
						property="charGrpCombntnList"
						type="com.EMD.LSDB.vo.common.ClauseVO" indexId="counter">
						<TR>
							<TD COLSPAN="2">
								<TABLE class="table table-bordered text-center">
									<logic:iterate id="charGrpCompList" name="charGrpCombntnList"
										property="componentVO" type="com.EMD.LSDB.vo.common.ComponentVO"
										scope="page">
										<TR>
											<TD width="25%"><%=String.valueOf(charGrpCompList
															.getComponentGroupName())%></TD>
											<TD width="30%"><%=String.valueOf(charGrpCompList
															.getComponentName())%></TD>
										</TR>
									</logic:iterate>
								</TABLE>
							</TD>
							<TD CLASS="text-center v-midalign">
						  		<logic:notEmpty name="charGrpCombntnList" property="charEdlNo">
									<%=String.valueOf(charGrpCombntnList.getCharEdlNo())%>
								</logic:notEmpty>
							</TD>
							<TD CLASS="text-center v-midalign">
								<logic:notEmpty name="charGrpCombntnList" property="charRefEDLNo">
									<%=String.valueOf(charGrpCombntnList.getCharRefEDLNo())%>
								</logic:notEmpty>
							</TD>
						</TR>
					</logic:iterate>
				</tbody>
			</TABLE>
		</logic:present>
	</logic:greaterThan>
	<div class="form-group">
		<div class="col-sm-12 text-center">
			<button class="btn btn-primary" type='button' onclick="javascript:window.close();">Close</button>
		</div>
	</div>
	<div class="spacer30"></div>
</html:form>
</div>
</HTML>