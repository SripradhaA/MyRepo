<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
	
	<logic:present name="PreviewRequestForm" property="effClauseList">
		<logic:notEmpty name="PreviewRequestForm"
			property="effClauseList">
			<TR>
				<TD>
				<TABLE width=80% BORDER=0 ALIGN=center>
					<TR>
						<TD valign=top width="40%" ALIGN=center>
						<FIELDSET CLASS="fieldset5"><LEGEND ALIGN="left"
							CLASS="formSubHeading">Affected Items</LEGEND>
	
						<TABLE width="100%" border=1 bordercolor=#DAE2F1 ALIGN=center>
							<TR>
								<TD WIDTH="25%" ALIGN=center CLASS="headerbgcolor"><b>Components</b></TD>
								<TD WIDTH="10%" ALIGN=center CLASS="headerbgcolor"><b>Default</b></TD>
								<TD WIDTH="65%" ALIGN=center CLASS="headerbgcolor"><b>Clause Description</b></TD>	
							</TR>
							 <logic:iterate id="effClaList" name="PreviewRequestForm" 
							 	property="effClauseList" type="com.EMD.LSDB.vo.common.ClauseVO" scope="request">									  	
								<TR>
									<TD WIDTH="25%" class=borderbottomright1 ALIGN="center">
									<bean:define id="dfaultflag" value="N"/>
									<logic:notEmpty name="effClaList" property="componentVO" >
										<bean:size id="compLength" name="effClaList" property="componentVO"/>
											<logic:equal name="compLength" value="1">
											 <logic:iterate id="claCompList" name="effClaList" property="componentVO"
									           	type="com.EMD.LSDB.vo.common.ComponentVO">
									                <bean:write name="claCompList" property="componentName"/>
										            <logic:equal name="claCompList" property="defaultFlag" value="true">
										            	<bean:define id="dfaultflag" value="Y"/>
										            </logic:equal> 				          	
											   </logic:iterate>
											</logic:equal>
											<logic:greaterThan name="compLength" value="1">
									           <logic:iterate id="claCompList" name="effClaList" property="componentVO"
									           	type="com.EMD.LSDB.vo.common.ComponentVO" indexId="count">
									           		<bean:define id="cnt" name="count" />
									                <bean:write name="claCompList" property="componentName"/>
									              		;<BR>		
									            <logic:equal name="claCompList" property="defaultFlag" value="true">
									            	<bean:define id="dfaultflag" value="Y"/>
									            </logic:equal>  			          	
											   </logic:iterate>
											</logic:greaterThan>
									</logic:notEmpty>
									</TD>
									<TD WIDTH="10%" class=borderbottomright1 ALIGN="center">
									  <logic:equal name="dfaultflag" value="Y">
									          X
									  </logic:equal>
									   <logic:notEqual name="dfaultflag" value="Y">
									          &nbsp;
									  </logic:notEqual></TD>
									<TD WIDTH="65%" class=borderbottomright1 ALIGN="Left">
									<bean:write name="effClaList" property="clauseDesc"/>
								</TR>
							</logic:iterate>
						</TABLE>
						</FIELDSET>
						</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
		</logic:notEmpty>
	</logic:present>