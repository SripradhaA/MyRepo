<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML//EN">
<html>
<title>Spell Check</title>
<style type="text/css" media="all">
<!--
	BODY
	{
		    MARGIN-TOP: 0px;
		    FONT-family:"Arial","Times",serif;
			font-size: 9pt;
		    MARGIN-LEFT: 0px;
		    MARGIN-RIGHT: 0px;
			MARGIN-BOTTOM: 0px;
			BACKGROUND:white
		}

	.buttonStyle
	{
		background-color:#59619F;
		font-weight: bold;
		border:0;
		width:auto;
		border-left: 2px #1B174D;
		border-right: 2px #1B174D;
		border-top: 2px #1B174D;
		border-bottom: 2px #1B174D;
		font-family: Verdana, Arial, Helvetica, sans-serif;
		font-size: 11px;
		line-height: 18px;color: white;
		}
		
	.textBox{

		background-color: white;
		font-size: 9pt;
		font-family: arial;
		border: groove; border-width: 1px thin thin 1px;
		width:145px;
		}
		
	 TD.headerbgcolor
	 	{
	    BACKGROUND: #D2DDF9;
	    FONT: bold 9pt arial;
	    COLOR: black;
		}
		-->
</style>
<body>
<Center>

<RapidSpellWeb:rapidSpellWeb licenseKey="5A556A61675D69626B5C6D6C64485426403D3C463B463D423F45444647444E4B4A548" 
ignoreAllButtonstyleClass="buttonStyle" ignoreButtonstyleClass="buttonStyle" finishButtonstyleClass="buttonStyle" 
changeButtonstyleClass="buttonStyle" undoButtonstyleClass="buttonStyle" changeAllButtonstyleClass="buttonStyle" 
addButtonstyleClass="buttonStyle" previewPaneHeight="215" previewPaneWidth="250" changeToBoxstyleClass="textBox" 
previewPaneStyle="background-color: white;font-size: 9pt; font-family: arial;border: groove; border-width: 1px thin thin 1px;" 
suggestionsBoxStyle="background-color: white;font-size: 9pt;	
font-family: arial; width: 200px; height: 130px " 
layout="<table border=0>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan=3 CLASS=headerbgcolor>
					<b>Clause Description - Spell Checking</b>
				</td>
			</tr>
			<tr><td></td></tr>
			<tr>
			<td>
				<PreviewPane/>
			</td>
			<td>
				<table border=0 height=190>
					<tr><td CLASS=headerbgcolor>
						<ChangeToLabel/>
						</td>
					</tr>
					<tr><td>
						<ChangeToBox/>
						</td>
					</tr>
					<tr><td CLASS=headerbgcolor>
						<SuggestionsLabel/>
						</td>
					</tr>
					<tr><td valign=bottom>
						<SuggestionsBox/>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table border=0 height=180>
					<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td>
						<IgnoreButton/>
					</td>
					</tr>
					<tr><td>
						<IgnoreAllButton/>
					</td>
					</tr>
					<tr><td>
						<AddButton/>
					</td>
					</tr>
					<tr><td>
						<ChangeButton/>
					</td>
					</tr>
					<tr><td>
						<ChangeAllButton/>
					</td>
					</tr>
					<tr><td>
						<FinishButton/>
					</td>
					</tr>
				</table>
			</td>
			</tr>
		</table>" />		

</center>
</body>
</html>