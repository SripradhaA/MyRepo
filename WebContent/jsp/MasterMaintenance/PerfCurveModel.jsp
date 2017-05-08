<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:useBean id="ModelPerformanceveForm"
	class="com.EMD.LSDB.form.MasterMaintenance.PerfCurveModelForm"
	scope="session" />
<%@ page language="java" import="java.io.*"%>
<html>
<HEAD>
<LINK REL="stylesheet" HREF="css/Emd_Lsdb.css" TYPE="text/css">
<LINK REL="stylesheet" TYPE="text/css" HREF="css/EmdMenu.css">
<SCRIPT LANGUAGE="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/File.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/PerfCurveModel.js"></SCRIPT>
<script>




</script>
</HEAD>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
<BODY CLASS="main">

<html:form action="/PerfCurveModelAction" enctype="multipart/form-data">

	<TABLE BORDER="0" WIDTH="60%" ALIGN="CENTER">

		<TR>
			<BR>
			<TD WIDTH="60%" CLASS="dashBoardSubHeading" ALIGN="CENTER"><bean:message
				key="Application.Screen.PerfCurveModel" /></TD>
		</TR>
		<TR>
			<TD align=center class="errortext"><bean:message
				key="PerfCurveMessage" /></TD>
		</TR>
	</TABLE>


	<!-- Validation Message Display Part Starts Here -->

	<div class="errorlayerhide" id="errorlayer">
	</div>

	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Starts Here -->

	<logic:present name="ModelPerformanceveForm" property="resultList"
		scope="request">
		<bean:size name="ModelPerformanceveForm" id="modelsize"
			property="resultList" />
	</logic:present>

	<!-- Logic Tag Check For Display The Records After Upload and Delete Functionality Ends Here -->

	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->

	<logic:present name="ModelPerformanceveForm" property="messageID"
		scope="request">

		<bean:define id="messageID" name="ModelPerformanceveForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>


	</logic:present>

	<logic:match name="ModelPerformanceveForm" property="method"
		value="fetchPerfCurveImages">
		<logic:lessThan name="modelsize" value="1">
			<script> 
              fnNoRecords();
        	</script>
		</logic:lessThan>
	</logic:match>


	<!-- Logic Tag Check For Display The Success Message After Upload and Delete Functionality Starts Here -->




	<TABLE WIDTH="70%" BORDER="0" ALIGN="center">
		<TR>
			<TD>&nbsp;</TD>
		</TR>

		<TR>
			<TD WIDTH="3%" ALIGN="left" CLASS=""></TD>
			<TD WIDTH="3%" ALIGN="left" CLASS="headerbgcolor"><bean:message
				key="model" /><font color="red">*</font></TD>
			<TD WIDTH="3%" class="navycolor"><html:select
				styleClass="selectstyle2" name="ModelPerformanceveForm"
				property="modelSeqNo">
				<option selected value="-1">---Select---</option>
				<html:optionsCollection name="ModelPerformanceveForm"
					property="listModels" label="modelName" value="modelSeqNo" />
			</html:select></TD>
			<TD WIDTH="5%" ALIGN="left" CLASS=""><html:button property="method"
				styleClass="buttonStyle" onclick="javascript:fnSearchGenArrImages()">
				<bean:message key="PerfCurve.search" />
			</html:button></TD>

		</TR>

	</TABLE>

	<logic:equal name="ModelPerformanceveForm" property="hdnDisp" value="0">
		<TABLE WIDTH="70%" BORDER="0" ALIGN="center">
			<TR>
				<BR>
				<TD WIDTH="6%" ALIGN="left" CLASS=""></TD>
				<TD WIDTH="40%">&nbsp;</TD>
				<TD class='' WIDTH="10%" align="center"><html:file
					property="theFile" onchange="javascript:validateFile(this)" /></TD>
<%--Edited for CR_121 Starts here--%>
				<TD><html:button property="method" styleClass="buttonStyle" styleId="searchButton"
					onclick="javascript:uploadModelGenImage()">
							Upload
						</html:button></TD>
						<%--Edited for CR_121 ends here--%>
			</TR>
		</TABLE>
	</logic:equal>




	<html:hidden property="filePath" />
	<html:hidden property="curSeqNo" />
	<html:hidden property="modSeqNo" />
	<html:hidden property="imgSeqNo" />
	<html:hidden property="selectMdl" />
	<html:hidden property="hdnDisp" />

	<logic:greaterThan name="modelsize" value="0">



		<HR>
		<TABLE ALIGN=center WIDTH="70%" BORDER=0 bgcolor=#D2DDF9>

			<TR>
				<TD ALIGN="left"><span CLASS=greytext1>Model:</span> <bean:write
					name="ModelPerformanceveForm" property="selectMdl" /></TD>
			</TR>
		</TABLE>

		<TABLE width="70%" align="center" border="1" bordercolor="#5780ae">
			<TR>
				<TH class='table_heading' width="2%">Select</TH>

				<TH class='table_heading' width="10%">Image</TH>



			</TR>

			<logic:iterate id="PerfCurveVO" name="ModelPerformanceveForm"
				property="resultList"
				type="com.EMD.LSDB.vo.common.PerformanceCurveVO" scope="request">
				<TR>
					<TD class='' width="2%" align="center"><html:radio
						styleClass="radcheck" property="curveSeqNo"
						value='<%= String.valueOf(PerfCurveVO.getCurveSeqNo())%>' /></TD>


					<TD class='' width="10%" align="center"><img
						src="/EMD-LSDB/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=PerfCurveVO.getFileVO().getImageSeqNo()%>'"
						border="0" alt=""> <html:hidden property="imgSeqNo"
						value='<%=String.valueOf(PerfCurveVO.getFileVO().getImageSeqNo())%>' /></TD>



				</TR>
			</logic:iterate>
		</TABLE>

		<TABLE BORDER="0" WIDTH="10%" ALIGN="CENTER">
			<TR>
				<TD class='' width="5%" align="center"><html:file property="theFile"
					onchange="javascript:validateFile(this)" /></TD>
				<%--Edited for CR_121 Starts here--%>
				<TD><html:button property="method" styleClass="buttonStyle" styleId="searchButton"
					onclick="javascript:uploadModelGenImage()">
							Upload
						</html:button></TD>
						<%--Edited for CR_121 ends here--%>
						<%--Edited for CR_121 Starts here--%>
				<TD><html:button property="method" styleClass="buttonStyle" styleId="searchButton"
					onclick="javascript:deleteModelGenImage()">
						Delete
						</html:button></TD>
						<%--Edited for CR_121 ends here--%>
			</TR>
		</TABLE>
	</logic:greaterThan>




</html:form>
</body>

</html>
