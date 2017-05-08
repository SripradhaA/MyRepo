	
<%@ taglib uri="/WEB-INF/RapidSpellWeb.tld" prefix="RapidSpellWeb"%>
	
	<script type="text/javascript">
	var haveAdjustedOverlay=false;
	function fnCheckSpellnCont(fnToCall) {
		rswi1_SpellChecker.OnSpellButtonClicked();  
		//$('#addClause').attr("disabled", true); 
		$('#actionFlag').unbind('click');
		var funcCall = fnToCall + "();"; 
		$('#actionFlag').click(function() {
			eval(funcCall);
		}); 
	}
	function fnSpellComplete() {
		$('.mce-toolbar').show(); 
	}
	function fnSpellCheck(complete, numberOfErrors) {
			//Modified functon to suit single time spell check for CR_108
				if ($('#actionFlag').val() == "Y" ){
					$('#actionFlag').click();
				}else	{
					if (numberOfErrors <= 0 && numberOfErrors != -1){
							$('.mce-toolbar').show(); 
							$('#actionFlag').click();
						//$('#addClause').attr("disabled", false); 
					}
					/*else if (numberOfErrors == -1) {
						$('#addClause').attr("disabled", false); 
					}*/
					else {
						$('#actionFlag').val("Y");
						$('.mce-toolbar').hide();
						document.getElementById('clauseDesc_id_D').scrollIntoView();
					}
				}
	}
	//Added for CR-121
	function onSpellStart(){
        if(haveAdjustedOverlay) return;
        haveAdjustedOverlay = true;
        setSpellCheckCoOrd();     
        haveAdjustedOverlay = false;
    }
    function setSpellCheckCoOrd() {    
       if ($('#clauseDesc_id_D').is(':visible')) {
			var absPos = findTotalOffsetElement(document.getElementById('clauseDesc_id'));
         	var spellCheckerHeight = parseInt($('.mce-toolbar').height())+parseInt($('#clauseDesc_id_ifr').height());
         	var prevElementOffset = $('#clauseDesc_id').parent().offset();
         	$('#clauseDesc_id_D').css({
			        top: (prevElementOffset.top) + 'px',
			        left: (prevElementOffset.left) + 'px',
			        width: '100px',
			        height: '800px'
			        //marginTop: prevElementOffset.top-absPos.top +'px'
			});
			return true;
		} else {
			setTimeout( "setSpellCheckCoOrd();", 300 );
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
  	//Added for CR-121 ends here
	</script>

	<% com.keyoti.rapidSpell.web.Globals.clientFilesLocation = "js/Others/ClientFiles/";	%>
	<!-- Added For CR_88 -->
	<!--  CR 88 -increase the size of text area and adding
	 user dictionary -->
	<RapidSpellWeb:rapidSpellWInline id="rswi1" 
		textComponentID="clauseDesc_id" ignoreWordsWithDigits="false" doubleClickSwitchesMode="false"
		textComponentInterface="Automatic" ignoreXML="true" rightClickForMenu="false" enterStaticSpellCheckListener="onSpellStart"
		enterEditModeWhenNoMoreErrors="true" checkAsYouType="true" checkAsYouTypeOnPageLoad="true"
		showButton="false" includeUserDictionaryInSuggestions="true" showChangeAllItem="true" 
		userDictionaryFile="/data/lsdb_qa/userDictionary/userDictionary.txt" leaveStaticSpellCheckListener="fnSpellComplete"
		showAddItemAlways="false" finishedListener="fnSpellCheck" ignoreCapitalizedWords="true"
		checkCompoundWords="false" allowMixedCase="true"  />
		
	<!-- Added For CR_81 on 24-Dec-09 by ------- - Ends here-->
	<input type="hidden" name="actionFlag" id="actionFlag" value="N"/>
	<br> 