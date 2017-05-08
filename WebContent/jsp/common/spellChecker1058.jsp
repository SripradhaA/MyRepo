	
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
	
	<script type="text/javascript">
	var haveAdjustedOverlay=false;
	function fnCheckSpellnCont(fnToCall,id) {
		if($('#rswid').val()!=id){
			$('#rswid').val(id);
			$('#actionFlag').val("N");
		}
		var spellchecker =id+"_SpellChecker.OnSpellButtonClicked();";
		eval(spellchecker);
			$('#actionFlag').unbind('click');
			var funcCall = fnToCall + ";"; 
			$('#actionFlag').click(function() {
				eval(funcCall);
			}); 
	}
	function fnSpellComplete() {
		//$('#'+$('#rswid').val()+'_toolbar1').show();
		$('.mce-toolbar').show(); 
	}
	function fnSpellCheck(complete, numberOfErrors) {
		if ($('#actionFlag').val() == "Y"){
			$('#actionFlag').click();
		}else if ($('#actionFlag').val() != "Z")	{
			if (numberOfErrors <= 0 && numberOfErrors != -1){
					//$('#'+$('#rswid').val()+'_toolbar1').show();
					$('.mce-toolbar').show(); 
					$('#actionFlag').click();
			}
			else {
				$('#actionFlag').val("Y");
				//$('#'+$('#rswid').val()+'_toolbar1').hide();
				$('.mce-toolbar').hide();
				document.getElementById($('#rswid').val()).scrollIntoView(true);
			}
		}
	}
	function onSpellStart(){
        if(haveAdjustedOverlay) return;
        haveAdjustedOverlay = true;
        var overlay = document.getElementById($('#rswid').val()+'_D');
        //setSpellCheckCoOrd();
        haveAdjustedOverlay = false;
    }
    function setSpellCheckCoOrd() {    
   	   var spellCheckID = $('#rswid').val()+'_D';
       if ($('#'+spellCheckID).is(':visible')) {
			var absPos = findTotalOffsetElement(document.getElementById($('#rswid').val()+'_ifr'));
         	var spellCheckerHeight = parseInt($('.mce-toolbar').height())+
         								parseInt(($('#'+$('#rswid').val()+'_ifr')).height())+
         									parseInt($('.mce-statusbar').height());
         	var prevElementOffset = $('#'+$('#rswid').val()).parent().parent().offset();
         	$('#'+spellCheckID).css({
			        top: (absPos.top) + 'px',
			        left: (absPos.left) + 'px',
			        height: spellCheckerHeight + 'px',
			        marginTop: prevElementOffset.top-absPos.top +'px'
			});
			return true;
		} else {
			setTimeout( "setSpellCheckCoOrd();", 200 );
		} 
	}	
    function findTotalOffsetElement(obj) {
        var ol = ot = 0;
        if (obj.offsetParent) {
            do {
                ol += obj.offsetLeft;
                ot += obj.offsetTop;
            } while (obj = obj.offsetParent);
        }
        return {left: ol, top: ot};
    } 
	</script>
	
	<% com.keyoti.rapidSpell.web.Globals.clientFilesLocation = "js/Others/ClientFiles/";	%>
	<logic:notPresent name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
		<RapidSpellWeb:rapidSpellWInline id="addClauseDescription" 
			textComponentID="addClauseDescription" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
			textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
			enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
			showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
			userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
			showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
			checkCompoundWords="false" allowMixedCase="true"  />
	
		<RapidSpellWeb:rapidSpellWInline id="clauseDescriptionMod" 
			textComponentID="clauseDescriptionMod" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
			textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
			enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
			showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
			userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
			showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
			checkCompoundWords="false" allowMixedCase="true"  />
		
		
		<RapidSpellWeb:rapidSpellWInline id="clauseDescriptionChngComp" 
			textComponentID="clauseDescriptionChngComp" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
			textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
			enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
			showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
			userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
			showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
			checkCompoundWords="false" allowMixedCase="true"  />
		
	</logic:notPresent>
	
	<logic:present name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request">
	<logic:iterate id="revClaData" name="ChangeRequest1058Form" property="reviseClauseDetailsList" scope="request" type="com.EMD.LSDB.vo.common.ClauseVO">
		<logic:equal name="revClaData" property="clauseChangeType" value="1">
			<RapidSpellWeb:rapidSpellWInline id="addClauseDescription" 
					textComponentID="addClauseDescription" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
					textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
					enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
					showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
					userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
					showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
					checkCompoundWords="false" allowMixedCase="true"  />
		</logic:equal>
		
		<logic:equal name="revClaData" property="clauseChangeType" value="2">
			<RapidSpellWeb:rapidSpellWInline id="clauseDescriptionMod" 
					textComponentID="clauseDescriptionMod" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
					textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
					enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
					showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
					userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
					showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
					checkCompoundWords="false" allowMixedCase="true"  />
		</logic:equal>
		
		<logic:equal name="revClaData" property="clauseChangeType" value="4">
			<RapidSpellWeb:rapidSpellWInline id="clauseDescriptionChngComp" 
					textComponentID="clauseDescriptionChngComp" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
					textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
					enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
					showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true"
					userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
					showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
					checkCompoundWords="false" allowMixedCase="true"  />			
		</logic:equal>
	
	</logic:iterate>
	</logic:present>
	<input type="hidden" name="actionFlag" id="actionFlag" value="N"/>
	<input type="hidden" name="rswid" id="rswid" value=""/>
	<br> 	