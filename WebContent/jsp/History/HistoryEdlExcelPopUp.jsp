<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page contentType="application/vnd.ms-excel"%>

<HTML>
<HEAD>

<STYLE type="text/css">
.greytext1 {
			FONT-WEIGHT: bold;FONT-SIZE: 9pt; COLOR: #000000
         	}  
.greytext {
			FONT-SIZE: 9pt; COLOR: #000000
         	} 
 BODY.main
{
    MARGIN-TOP: 0px;
    FONT-family:"Arial","Times",serif;
	font-size: 9pt;
	scrollbar-arrow-color: #000000;
	scrollbar-DarkShadow-Color: #000000;
    MARGIN-LEFT: 0px;
    MARGIN-RIGHT: 0px;

	MARGIN-BOTTOM: 0px;
	BACKGROUND:white
}

.dashBoardSubHeading {							/* page heading */

			font-family: Tahoma;
			font-size: 9pt;
			color: #59619F;
			font-weight: bold;
		}    
.table_heading {
	
	
}


		
		
		</STYLE> 
<%!
	public String replace(String clause){

		
		if(clause != null && !"".equals(clause)){
		
			clause.replaceAll("&lt","<").replaceAll("&gt",">");
			
		}
		return clause; 
}
	
%>
<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>


<BODY CLASS="main">

<html:form action="/historyEdlAction.do">

	<TABLE ALIGN=center WIDTH="50%" BORDER=0>
		<TR>
			<TD>&nbsp;</TD>
		</TR>

		<TR>
			<%-- Modified colspan for CR_106 to include Clause description against empty components --%>
			<TD WIDTH="500" colspan=6 CLASS="dashBoardSubHeading" align="center">Spec
			History - EDL Number List</TD>
		</TR>
		<TR>
			<TD>&nbsp;</TD>
		</TR>
	</TABLE>

	<TABLE ALIGN=center WIDTH="500"  BORDER=0 bgcolor=#D2DDF9>
		<TR colspan=5>

			<%-- Modified colspan for CR_106 to include Clause description against empty components --%>
			<TD ALIGN=left colspan=2><span CLASS=greytext1>Order Number :</span><span CLASS=greytext><bean:write
				name="HistoryEdlPopUpForm" property="orderNo" /></span></TD>


			<TD ALIGN=left><span CLASS=greytext1>Spec Status :</span><span CLASS=greytext><bean:write
				name="HistoryEdlPopUpForm" property="specStatus" /></span></TD>


			<TD ALIGN=left colspan=2><span CLASS=greytext1>Customer Name :</span><span CLASS=greytext><bean:write
				name="HistoryEdlPopUpForm" property="customerName" /></span></TD>


			<TD ALIGN=left><span CLASS=greytext1>Model : </span><span CLASS=greytext><bean:write
				name="HistoryEdlPopUpForm" property="modelName" /></span></TD>
		</TR>

	</TABLE>
	
	<br>
	<br>
	<logic:present name="HistoryEdlPopUpForm" property="clauseGroup">
		<TABLE BORDER=1 WIDTH="95%" CELLPADDING=0 CELLSPACING=0 ALIGN="CENTER"
			BORDERCOLOR="#5780ae">
			<TR>
				<TD>
				<TABLE WIDTH="100%" BORDER="1" BORDERCOLOR="#A9D0F5" CELLPADDING=1 CELLSPACING=1>
					<TR colspan=5  align="center">
						<TH style="font-family:Verdana; font-size:9pt; font-weight: bold; color: #000000; background-color:#D1E1F3; text-align:center; padding-top: 5px; padding-bottom: 5px; padding-right: 5px;">Clause No</TH>
						<%-- Modified title for CR_106 to include Clause description against empty components --%>
						<TH style="font-family:Verdana; font-size:9pt; font-weight: bold; color: #000000; background-color:#D1E1F3; text-align:center; padding-top: 5px; padding-bottom: 5px; padding-right: 5px; padding-left: 5px;" colspan=3>Component / <font style="font-style:italic">Clause Description</font></TH>
						<TH style="font-family:Verdana; font-size:9pt; font-weight: bold; color: #000000; background-color:#D1E1F3; text-align:center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px;">EDL No</TH>
						<TH style="font-family:Verdana; font-size:9pt; font-weight: bold; color: #000000; background-color:#D1E1F3; text-align:center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px;">Ref EDL No</TH>
						<%-- Modified for CR_106 - Ends here --%>
					</TR>
				</TABLE>
				</TD>
			</TR>

			<%-- Modified for CR_105 to match the EDL Comparison Report Changes --%>
			<logic:iterate id="sectionId" name="HistoryEdlPopUpForm"
				property="clauseGroup" scope="request" type="com.EMD.LSDB.vo.common.SectionVO" indexId="seccount">
					<TR>
						<TD>
						<TABLE WIDTH="100%" BORDER="0">
							<TR>
								<TH style="color:black; font-size:9pt; font-family:Verdana; bold; background-color:#D6D8DB;" align="center" colspan="6">
								<bean:write name="sectionId" property="sectionName" /></TH>
							</TR>
					<logic:notEmpty name="sectionId" property="subSec">
					<logic:iterate id="subSecList" name="sectionId" property="subSec" indexId="subcount">
					<logic:notEmpty name="subSecList" property="clauseGroup">
					<logic:iterate id="clause" name="subSecList" property="clauseGroup" indexId="clacount">
						<logic:present name="clause" property="clauseNum">	
							<TR>
								<TD style="border-left: 1px solid #DAE2F1; font-family:Arial; font-size: 9pt; border-top: 0px; border-right: 0px ; border-bottom: 1px solid #DAE2F1; text-align:center;vertical-align:middle;" width="15%"><bean:write
									name="clause" property="clauseNum" /><BR>
								</TD>
								<%-- Modified for CR_106 to include Clause description against empty components --%>
								<logic:notEmpty name="clause" property="componentName">
								<TD style="border-left: 1px solid #DAE2F1; font-family:Arial; font-size: 9pt; border-top: 0px; border-right: 0px ; border-bottom: 1px solid #DAE2F1; text-align:center;vertical-align:middle;" width="45%" colspan=3><bean:write
									name="clause" property="componentName" /><BR>
								</TD>
								</logic:notEmpty>
								<logic:empty name="clause" property="componentName">
								<TD style="border-left: 1px solid #DAE2F1; font-family:Arial; font-style:italic; font-size: 9pt; border-top: 0px; border-right: 0px ; border-bottom: 1px solid #DAE2F1; text-align:center;vertical-align:middle;" width="45%" colspan=3>
									<bean:define name="clause" property="clauseDesc" id="clauseDesc" /> 
									<%=(String.valueOf(clauseDesc)).replaceAll("\n","<br>")%><BR>
								</TD>
								</logic:empty>
								<TD style="border-left: 1px solid #DAE2F1; font-family:Arial; font-size: 9pt; border-top: 0px; border-right: 0px ; border-bottom: 1px solid #DAE2F1; text-align:center;vertical-align:middle;"width="20%"><bean:write
									name="clause" property="edlNum" />&nbsp;</BR>&nbsp;
								</TD>
								<TD style="border-left: 1px solid #DAE2F1; font-family:Arial; font-size: 9pt; border-top: 0px; border-right: 0px ; border-bottom: 1px solid #DAE2F1; text-align:center;vertical-align:middle;"width="20%"><bean:write
									name="clause" property="refEdlNum" />&nbsp;</BR>&nbsp;
								</TD>
								<%-- Modified for CR_106 - Ends here --%>
							</TR>
						</logic:present>
					</logic:iterate>
					</logic:notEmpty>
					</logic:iterate>
					</logic:notEmpty>
						</TABLE>
						</TD>
					</TR>
			</logic:iterate>
		</TABLE>
	</logic:present>
		
</html:form>

</BODY>
</HTML>
