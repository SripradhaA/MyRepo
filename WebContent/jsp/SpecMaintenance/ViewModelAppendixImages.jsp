<!DOCTYPE HTML>
<HTML>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" rel="stylesheet"/>
	<link rel="shortcut icon" href="images/favicon.ico" />
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<SCRIPT language="JavaScript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error.js"></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" SRC="js/error_messages.js"></SCRIPT>
	<SCRIPT language="JavaScript" SRC="js/Appendix.js"></SCRIPT>
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</HEAD>
<BODY>
<html:form action="/AppendixViewImagesAction" enctype="multipart/form-data">

	<div class="container">
		<!-- Application PageName Display starts here-->
		<h4 class="text-center"><strong>
			<bean:message key="Application.Screen.ViewModelAppendixImages" />
		</strong></h4>
		<h5 class="text-center"><bean:message
				key="AppendixImageMessage" /></h5>
	<!-- Validation Message Display Part Starts Here -->

	<div class="errorlayerhide" id="errorlayer"></div>
	
	<div class="row">
		<div class="spacer10"></div>
	</div>

	<!-- Validation Message Display Part Ends Here -->

	<!-- Logic Tag Check For Display The Records After View Images to download Starts Here -->

	<logic:present name="AppendixForm" property="imageList"
		scope="request">
		<bean:size id="imagesize" name="AppendixForm"
			property="imageList" />
	</logic:present>

	<!-- Logic Tag Check For Display The Records After View Images to download Ends Here -->

	<!-- Logic Tag Check For Display The error Message for no Images found Starts Here -->


	<logic:match name="AppendixForm" property="method"
		value="NoAppendixImage">
		<logic:lessThan name="imagesize" value="1">
			<script> 
              fnNoRecords();
        	</script>

		</logic:lessThan>
	</logic:match>


	<logic:present name="AppendixForm" property="messageID"
		scope="request">

			<bean:define id="messageID" name="AppendixForm" property="messageID"/>
            <input type="hidden" name="messageID" id="messageID" value="<bean:write name="messageID"/>" >
            <input type="hidden" name="errorElement" id="errorelement" value="<bean:message name="messageID" bundle="ServerMessageBundle"/>"/>


	</logic:present>

	<!-- Logic Tag Check For Display The error Message for no Images found ENDS Here -->
	
	
	<logic:greaterThan name="imagesize" value="0">
		
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1 text-center bg-info">
					<h4><strong>Model</strong>&nbsp;&nbsp;:&nbsp;&nbsp;<bean:write name="AppendixForm" property="modelName" /></h4>
				</div>
			</div>
		
		<TABLE class="table table-bordered">
			<thead>
				<TR>
					<TH class='text-center' width="10%">Appendix Image</TH>
				</TR>
			</thead>
			<tbody>
				<logic:iterate id="AppendixVO" name="AppendixForm"
					property="imageList" type="com.EMD.LSDB.vo.common.AppendixVO"
					scope="request">
					<%-- Added for CR_97 for allowing PDF in Appendix  --%>
					<bean:define id="contenttype" value='<%=String.valueOf(AppendixVO.getFileVO().getContentType())%>' />
					<%-- CR_97 Ends here  --%>
					<TR>
						<TD width="10%">
							<div class="row form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 col-sm-offset-3 control-label">Image Name:</label>
									<div class="col-sm-3">
										<html:text styleClass="form-control" name="AppendixVO"
											property="imageName" size="25" maxlength="100" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="spacer10"></div>
								<div class="col-sm-12 text-center">
									<logic:equal name="contenttype" value="application/pdf">
										<A class="text-danger" href="javascript:fnShowPDF('<%=AppendixVO.getFileVO().getImageSeqNo()%>','<%=String.valueOf(AppendixVO.getImageName())%>')"
										title="Click to Save this PDF" data-toggle="tooltip">
											<span class="glyphicon glyphicon glyphicon-save" aria-hidden="true"></span> PDF Image
										</A>
									</logic:equal>
									<logic:notEqual name="contenttype" value="application/pdf">
										<a href="javascript:fnSaveImages(<%=AppendixVO.getFileVO().getImageSeqNo()%>,'<%=String.valueOf(AppendixVO.getImageName())%>');"
											title="Click to Save this Image." data-toggle="tooltip">
											 <img src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=AppendixVO.getFileVO().getImageSeqNo()%>'"
											border="0"/>
										</A>
									</logic:notEqual>
								</div>
							</div>
							<div class="row">
								<div class="spacer10"></div>
							</div>
							<div class="row form-horizontal">
								<div class="form-group">
									<label class="col-sm-2 col-sm-offset-3 control-label">Image Description:</label>
									<div class="col-sm-3">
										<html:textarea styleClass="form-control"
											name="AppendixVO" property="imageDesc" rows="2" cols="32" />
									</div>
								</div>
							</div>
							
						
						 <!--<TABLE>
							<TR>
								<TD>Image Name:</TD>
								<TD class="text-left" width="20%"><html:text
									styleClass="form-control" name="AppendixVO"
									property="imageName" size="25" maxlength="100" /></TD>
							</TR>
	
	
							<TR>
	
								<TD colspan=2 class="text-center">
								<%-- Added & Modified for CR_97 for allowing PDF in Appendix  --%>
								<logic:equal name="contenttype" value="application/pdf">
								<A href="javascript:fnShowPDF('<%=AppendixVO.getFileVO().getImageSeqNo()%>','<%=String.valueOf(AppendixVO.getImageName())%>')"
									Class="vtip" title="Click to Save this PDF" style="color:red;FONT:bold 9pt Verdana;">
									 PDF Image
								</A>
								</logic:equal>
								<logic:notEqual name="contenttype" value="application/pdf">
								<a href="javascript:fnSaveImages(<%=AppendixVO.getFileVO().getImageSeqNo()%>,'<%=String.valueOf(AppendixVO.getImageName())%>');"
									title="Click to Save this Image." class="vtip" >
									 <img src="<%=request.getContextPath()%>/DownLoadImage.do?method=downloadImage&ImageSeqNo='<%=AppendixVO.getFileVO().getImageSeqNo()%>'"
									border="0"/>
								</A>
								</logic:notEqual>
								<%-- Modification for  CR_97 Ends here  --%>
								</TD>
							</TR>
	
							<TR>
								<TD CLASS="borderbottom1">Image Description:</TD>
								<TD align="left" CLASS="borderbottom1"><html:textarea styleClass="form-control"
									name="AppendixVO" property="imageDesc" rows="2" cols="32" />
								</TD>
							</TR>
	
	
						</TABLE> -->
						</TD>
					</TR>
				</logic:iterate>
			</tbody>
		</TABLE>

		</logic:greaterThan>
		
		
		<div class="form-group">
			<div class="col-sm-12 text-center">
				<button class="btn btn-primary" type='button' id="close" ONCLICK="javascript:fnClose()">Close</button>
			</div>
		</div>
		
		<div class="row">
			<div class="spacer100"></div>
		</div>
		
		<nav class="navbar navbar-default navbar-fixed-bottom navbar-small">
			<SCRIPT type="text/javascript">
				var currentTime = new Date();
				var year = currentTime.getFullYear();
			</SCRIPT>
			<h5 class="navbar-footer-text">Copyright &copy; <script type="text/javascript">document.write(year)</script> Electro-Motive Diesel, Inc</h5>
		</nav>
</div>
</html:form>
</BODY>
</HTML>
