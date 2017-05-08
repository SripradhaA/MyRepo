/*Added few changes for CR-116 for set disabled button background color */

var	inputDataAtLoad;

$(document).ready(function() {
	
	//Added for CR_131
  	$("#sysEngg").select2({theme:'bootstrap'});  
  	$("#opRep").select2({theme:'bootstrap'});  
  	$("#finRep").select2({theme:'bootstrap'});  
  	$("#pgmMgr").select2({theme:'bootstrap'});  
  	$("#propMgr").select2({theme:'bootstrap'});  
	
	//Added for CR-117 
    document.forms[0].hdnSpecRev.value = document.forms[0].specRev.value;
	
	$(".btn:disabled").each(function() {
            $(this).addClass('disabled');
    });
	//Added for CR-117 to disabling request type
	if($("#statusSeqNo").val() == 1){
  	       fnDisableRepList();
      }else{
      	   $("input[name='requestType']").prop("disabled", true);
      }
	var mdlClaChng = $('#mdlClaChangeListSize').val();
		if(mdlClaChng <=0)
			$('#ClauseChange5').prop('disabled',true);
		else{
			if ($("#reviseClauseCheck").val() != 1)
				$('#ClauseChange5').prop('disabled',false);
		}
	var impSubSec = $('#importSubSecListSize').val();
		if(impSubSec <=0)
			$('#ClauseChange6').prop('disabled',true);
		else{
			if ($("#reviseClauseCheck").val() != 1)
				$('#ClauseChange6').prop('disabled',false);
		}
   //Added for CR-117 to disabling request type Ends Here   
	if ($(".reqSpecChkRadio").length == 0)
			fnDisableButtons(".btnSpecChngAction");
 	if ($("#statusSeqNo").val() == 5 || $("#statusSeqNo").val() == 10){
        fnDisableButtons(""); 
        $(".ClauseChangeTypes").prop("disabled", true);
         //Updated for CR-117 Suggestion Module
        $("input[type=file]").not("#uploadedFile").prop("disabled", true);
        //End Updated for CR-117 Suggestion Module
        if ($("#role").val() == "ADMN")
	    	fnEnableButtons("#btnResetStatus");
		}else{
      if($("#statusSeqNo").val() != 1){
      	if (($("#roleID").val() == "VALID_USER") || ($("#ownerFlag").val() == "Y")) {
         	fnEnableButtons("#btnResetStatus");
      	}
      }
    }
   	
    $("#chkReAssignRep").click(function() {
	   if ($(this).is(":checked")) {
		   fnEnableButtons("#btnReAssignRep");
		   //Added for CR-117 to Enabling request type
           fnEnableRepList();
	       /*$("#sysEngg").prop("disabled", false);
	       $("#opRep").prop("disabled", false);
	       $("#finRep").prop("disabled", false);
	       $("#pgmMgr").prop("disabled", false);
	       $("#propMgr").prop("disabled", false);*/
	   } else {
	   	   fnDisableButtons("#btnReAssignRep");
	       //$('#reAssignRep').hide();
	       //Added for CR-117 to disabling request type
	       //fnDisableRepList();
	       //Commented above for CR-131 to ensure the unselect checkbox leads to disable of all dropdowns
	       $("#sysEngg").prop("disabled", true);
	       $("#opRep").prop("disabled", true);
	       $("#finRep").prop("disabled", true);
	       $("#pgmMgr").prop("disabled", true);
	       $("#propMgr").prop("disabled", true); 
	   }
 	});

   //Added for CR-126
   
   //Add clause 
   	$('#addClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divAddClause').hide('normal');
		var radio = $('#ClauseChange1');
		if(radio.is(':checked')) radio.prop('checked',false);
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false);
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		fnTinymceCall();
   		$('#ClauseChange1').focus();
   	});
	
	//Modify Clause
	$('#modClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divModClause').hide('normal');
		var radio = $('#ClauseChange2');
		if(radio.is(':checked')) radio.prop('checked',false);
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false);
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		fnTinymceCall();
		$('#ClauseChange2').focus();
	});
	

	//Delete Clause
	$('#delClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divDelClause').hide('normal');
		var radio = $('#ClauseChange3');
		if(radio.is(':checked')) radio.prop('checked',false);
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false);
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		 fnTinymceCall();
		$('#ClauseChange3').focus();
	});

	//Change Component
	$('#compClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divChangeComp').hide('normal');
		var radio = $('#ClauseChange4');
		if(radio.is(':checked')) radio.removeAttr('checked');
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false).css("filter","alpha(opacity=100)");
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		 fnTinymceCall();
		$('#ClauseChange4').focus();
	});
   //Added for CR-126 ends here
   
   //Added for CR-127 starts here
   //Add Model Cla Change
	$('#addMdlChangesClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divAddMdlClaChanges').hide('normal');
		var radio = $('#ClauseChange5');
		if(radio.is(':checked')) radio.removeAttr('checked');
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false).css("filter","alpha(opacity=100)");
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		 fnTinymceCall();
		$('#ClauseChange5').focus();
	});
   //Added for CR-127 ends here
   
   //Added for CR-130 starts here
   //Import Subsection to Model Change
	$('#impSubSecChangeClose').click(function(){
  		if ($("#reviseClauseCheck").val() == 1) fnresetlastsave();
		$('#divImportSubSection').hide('normal');
		var radio = $('#ClauseChange6');
		if(radio.is(':checked')) radio.removeAttr('checked');
	 	if ($("#statusSeqNo").val() < 2)$('#saveSend').prop('disabled',false).css("filter","alpha(opacity=100)");
		$('#save').prop('disabled', false);
		/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
		 fnTinymceCall();
		$('#ClauseChange6').focus();
	});
   //Added for CR-130 ends here
   
   //Uncommented for CR-120
    $('input:text').each(function() {
      //  if ($(this).val() == "0" || $(this).val() == "0.0" || $(this).val() == "0.00") {
        if ($(this).val() == "-999999999") {
            $(this).val("");
        }
    });
    //Uncommented for CR-120 Ends here

    $('.hdn').hide();

    if ($('#customerApprovalReq').is(':checked'))
        $('.hdn').show();

    $("#customerApprovalReq").click(function() {
        $('.hdn').show();
    });

    $("#customerApprovalnReq").click(function() {
        $('.hdn').hide();
    });

    /*function findTotalOffset(obj) {
        var ol = ot = 0;
        if (obj.offsetParent) {
            do {
                ol += obj.offsetLeft;
                ot += obj.offsetTop;
            } while (obj = obj.offsetParent);
        }
        return {left: ol, top: ot};
    }*/

    $(".input-group.date").datepicker({
    	autoclose: true,
    	clearBtn: true
    });

    checkRadios();
    var checkChangeAction = false;
    /*
    var fileToAttach = $('#fileToAttach');
    $('#fileToAttach').bind('change', function() {
        var check = validate($('#fileToAttach').val());
    	alert(check);
        var checkAttachmentReturned = true;
        if (check) {
            $('#progressimg1').show();
            var param = "" + $('#fileToAttach').val();
            var seqNo = "" + $('#seqNo1058').val();

            $.ajax({
                url: 'ChangeRequest1058Action.do?method=uploadAttachment',
                data: {"param": param, "seqNo": seqNo},
                dataType: "json",
                cache: false,
                contentType: "application/json",
                success: function(result) {
                	alert(result);
                    if (result != null)
                    {
                        var count = 0;
                        var table = "";
                        $.each(result.attachment, function(i, item)//Get all 
                        {
                            if (item.message == null) {
                                $('#attachmentTable tr').each(function() {
                                    $(this).remove();
                                });
                                if (count == 0 || count % 4 == 0) {
                                    table = table + '<tr>';
                                }

                                table = table + '<TD width="25%" style="word-wrap:break-word;max-width:20px;"><a  CLASS="linkstyleItem vtip" title="Click to View/Download Attachment" href="javascript:fnDwnLdAttachment('
                                        + item.imgSeqNo +
                                        ',' + "'"
                                        + item.imgName
                                        + "'" + ')">'
                                        + item.imgName +
                                        '</a>&nbsp; <a href="javascript:fnDeleteAttachment('
                                        + item.imgSeqNo +
                                        ')"> <IMG class="vtip" title="Click to Delete Attachment" SRC="images/delete1.gif" alt="Delete" BORDER="0" /></a>';

                                count++;
                                if (count % 4 == 0) {
                                    table = table + '</tr>'
                                }

                            }
                            else {
                                var id = '999';
                                hideErrors();
                                addMsgNum(id);
                                showScrollErrors("attachmentSourceId",-1);
                                checkRadios();
                                checkAttachmentReturned = false;
                                $('#progressimg1').hide();
                            }
                        });

                        if (checkAttachmentReturned) {
                            $('#progressimg1').hide();
                            $('#attachmentTable').append(table);
                            checkRadios();
                        }
                    }
                    else {
                        window.location = "AjaxException.do";
                    }
                }
                , error: function(e) {
                    window.location = "AjaxException.do";
                }
            });
            fileToAttach.replaceWith(fileToAttach = fileToAttach.clone(true));
        }
        else {
            fileToAttach.replaceWith(fileToAttach = fileToAttach.clone(true));
            checkRadios();
        }
    });    */
    
    if ($('#specType').val() == 1) {
        $('[name="priceBookNoAdd"]').val("");
        $('[name="priceBookNoAdd"]').prop("disabled", true);
        
        $('[name="priceBookNoMod"]').val("");
        $('[name="priceBookNoMod"]').prop("disabled", true);
   
        $('[name="priceBookChngComp"]').val("");
        $('[name="priceBookChngComp"]').prop("disabled", true);
    }

    if ($("#reviseClauseCheck").val() == 0) {
        $('#ClauseChange1').prop('checked', false);
        $('#ClauseChange2').prop('checked', false);
        $('#ClauseChange3').prop('checked', false);
        $('#ClauseChange4').prop('checked', false);
        //Added for CR-127
        $('#ClauseChange5').prop('checked', false);
		$('#ClauseChange6').prop('checked', false);//Added for CR-130

        $('#divModClause').hide();
        $('#divAddClause').hide();
        $('#divDelClause').hide();
        $('#divChangeComp').hide();
        //Added for CR-127
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').hide();//Added for CR-130
    }

    if ($("#reviseClauseCheck").val() == 1) {
    	
    	/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
        fnTinymceCall();
    	
        showTinyMce("addClauseDescription");
        showTinyMce("clauseDescriptionMod");
        showTinyMce("clauseDescriptionChngComp");
        jQuery('html,body').animate({scrollTop: $('#clauseChngType').offset().top}, 1000);
        
        //Added for CR-126
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
	  //Added for CR-126 ends here	
    }


    if ($('#checkCompChangeClauses').val() == 1 && $("#reviseClauseCheck").val() == 0) {
        $('#ClauseChange4').prop('checked', true);
        $('#divChangeComp').show();

        $('#sectionSeqNo').val()
        $('#sltSectionChngComp').val($('#sectionSeqNo').val());
        $('#sltSubSectionChngComp').val($('#subSectionSeqNo').val());
        $("#sltLeadCompGroupChngComp").val($('#componentGrpSeqNoinAdd').val());

			/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
        fnTinymceCall();
		
        showTinyMce("clauseDescriptionChngComp");       
        jQuery('html,body').animate({scrollTop: $('#clauseChngType').offset().top}, 1000);
    }
    
    if ($('#checkVersionClause').val() == 1) {
    	$('#ClauseChange2').prop('checked', true);
        $('#divModClause').show();
       
      
       	$("#clauseToModify").val($("#hdnClauseToModify").val());
       	$("#versionNoMod").val($("#hdnVersionNoMod").val());
       	
       	/*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
        fnTinymceCall(); 
       
        showTinyMce("clauseDescriptionMod");
        jQuery('html,body').animate({scrollTop: $('#clauseChngType').offset().top}, 1000);
    }

	
	
    $('#ClauseChange1').click(function() {
    	
   	  //Added for CR-126
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
	  //Added for CR-126 ends here	
        ajaxStandardEquipMents('ClauseChange1');
        $("#clauseChangeType").val(1);
        /*initializing every parameter to null or zero*/
        $("#sectionSeqNo").val(0);
        $("#subSectionSeqNo").val(0);
        $("#hdnSectionName").val("");
        $("#hdnSubSectionName").val("");

        $("#componentGrpSeqNoinAdd").val(0);
        $("#hdnLeadComponentSeqNoinAdd").val(0);
        $("#hdnLeadComponentNameinAdd").val("");

        $("#hdncomponentGroupSeqNo").val(0);
        $("#componentSeqNo").val("");
        $("#hdnComponentName").val("");
        $("#hdnParentClauseSeqNo").val(0);
        $('[name="partOfSeqNo"]').each(function() {
            $(this).val(0);
        });
        $('[name="clauseSeqNum"]').each(function() {
            $(this).val(0);
        });

        //html part
        $("#divAddClause").find('[name="showgrid"]').empty();
        $("#divAddClause").find('[name="showsublink"]').hide();
        $("#divAddClause").find('[name="showmainlink"]').show();
        $("#sltSubSection option").remove();
        $("#sltLeadCompGroup option").remove();
        $("#sltLeadComp option").remove();
        $("#component").html("");
        $("#parentclause").html("");
        $("#addClauseDescription").html("");
        $('[name="refEdlNoAdd"]').each(function() {
            $(this).val("");
        });
        $('[name="newEdlNoAdd"]').each(function() {
            $(this).val("");
        });
        $('[name="partOf"]').each(function() {
            $(this).val("");
        });
        document.forms[0].dwoNoAdd.value = "";
        document.forms[0].partNoAdd.value = "";
        document.forms[0].priceBookNoAdd.value = "";
        document.forms[0].commentsAdd.value = "";
        document.forms[0].reasonAdd.value = "";

        /*End intitialization*/

        ajaxsections(1);
        $('#divAddClause #sltSection').prop('disabled',false);
        $('#divAddClause #sltSubSection').prop('disabled',false);
        $('#divAddClause #sltLeadCompGroup').prop('disabled',false);
        $('#divAddClause #sltLeadComp').prop('disabled',false);

        /*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
        fnTinymceCall();

        $('#divModClause').hide();
        $('#divDelClause').hide();
        $('#divChangeComp').hide();
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').hide();//Added for CR-130
        $('#divAddClause').show('normal');

        showTinyMce("addClauseDescription");

    });


    $('#ClauseChange3').click(function() {
    	  //Added for CR-126
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
	  //Added for CR-126 ends here	
        $("#clauseChangeType").val(3);
        $("#reasonDel").html("");
        $("#clauseToDelete").val("");
        $("#hdnClauseToDelete").val("");
        $("#hdnClauseToDeleteSeqNo").val(0);
        $("#divModClause").find('[name="showgrid"]').empty();
        $("#divModClause").find('[name="showsublink"]').hide();
        $("#divModClause").find('[name="showmainlink"]').show();


        $('#divModClause').hide();
        $('#divAddClause').hide();
        $('#divChangeComp').hide();
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').hide();//Added for CR-130
        $('#divDelClause').show('normal');
        $('#delSelect').show();
        $('#txtDelClauseNo').val("");
    });


    $('#ClauseChange2').click(function() {
    	
   	  //Added for CR-126
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
	  //Added for CR-126 ends here	
        ajaxStandardEquipMents('ClauseChange2');
        $("#clauseChangeType").val(2);

        /*initializing every parameter to null or zero*/
        $("#clauseToModify").val("");
        $("#hdnClauseToModify").val("");
        $("#hdnClauseToModifySeqNo").val("");

        $('[name="partOfSeqNo"]').each(function() {
            $(this).val(0);
        });
        $('[name="clauseSeqNum"]').each(function() {
            $(this).val(0);
        });

        //html part
        $("#clauseDescriptionMod").html("");
        $('[name="refEdlNoMod"]').each(function() {
            $(this).val("");
        });
        $('[name="newEdlNoMod"]').each(function() {
            $(this).val("");
        });
        $('[name="partOf"]').each(function() {
            $(this).val("");
        });
        document.forms[0].dwoNoMod.value = "";
        document.forms[0].partNoMod.value = "";
        document.forms[0].priceBookNoMod.value = "";
        document.forms[0].commentsMod.value = "";
        document.forms[0].reasonMod.value = "";

        /*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
       	fnTinymceCall();
        $('#divDelClause').hide();
        $('#divAddClause').hide();
        $('#divChangeComp').hide();
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').hide();//Added for CR-130
        $('#divModClause').show('normal');
        $('#modSelect').show();
        $('#txtModClauseNo').val("");

        showTinyMce("clauseDescriptionMod");

    });

    $('#ClauseChange4').click(function() {
   	  //Added for CR-126
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
	  //Added for CR-126 ends here	
        ajaxStandardEquipMents('ClauseChange4');
        $("#clauseChangeType").val(4);

        $("#sectionSeqNo").val(0);
        $("#subSectionSeqNo").val(0);

        $("#componentGrpSeqNoinAdd").val(0);
        $("#hdnLeadComponentSeqNoinAdd").val(0);
        $("#hdnLeadComponentNameinAdd").val("");

        $("#hdncomponentGroupSeqNo").val(0);
        $('[name="partOfSeqNo"]').each(function() {
            $(this).val(0);
        });
        $('[name="clauseSeqNum"]').each(function() {
            $(this).val(0);
        });

        //html part
        $('#sltSubSectionChngComp').html("");
        $('#sltLeadCompGroupChngComp').html("");
        $('#sltLeadCompChngCompOld').html("");
        $('#sltLeadCompChngCompNew').html("");
        $("#divChangeComp").find('[name="showgrid"]').empty();
        $("#divChangeComp").find('[name="showsublink"]').hide();
        $("#divChangeComp").find('[name="showmainlink"]').show();


        $("#clauseDescriptionChngComp").html("");
        $('[name="refEdlNoChngComp"]').each(function() {
            $(this).val("");
        });
        $('[name="newEdlNoChngComp"]').each(function() {
            $(this).val("");
        });
        $('[name="partOf"]').each(function() {
            $(this).val("");
        });
        document.forms[0].dwoNoChngComp.value = "";
        document.forms[0].partNoChngComp.value = "";
        document.forms[0].priceBookChngComp.value = "";
        document.forms[0].commentsChngComp.value = "";
        document.forms[0].reasonChngComp.value = "";

        ajaxsections(4);

        /*for (var i = 0; i < rsw_scs.length; i++) {
            if (rsw_scs[i].state == 'overlay') {
                $('#actionFlag').unbind('click');
                $('#actionFlag').val("Z");
                rsw_scs[i].OnSpellButtonClicked();
            }
        }*/
        fnTinymceCall();
        $('#divDelClause').hide();
        $('#divAddClause').hide();
        $('#divModClause').hide();
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').hide();//Added for CR-130
        $('#divChangeComp').show('normal');

        showTinyMce("clauseDescriptionChngComp");

    });

//Added for CR-127 starts here
	$('#ClauseChange5').click(function() {
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
        $("#clauseChangeType").val(5);
        $("#reasonMdl").html("");
		$(".claSeqNum").prop('checked',false);
        $("#divAddMdlClaChanges").find('[name="showgrid"]').empty();
        $("#divAddMdlClaChanges").find('[name="showsublink"]').hide();
        $("#divAddMdlClaChanges").find('[name="showmainlink"]').show();

        $('#divModClause').hide();
        $('#divAddClause').hide();
        $('#divChangeComp').hide();
        $('#divDelClause').hide();
        $('#divAddMdlClaChanges').show('normal');
        $('#divImportSubSection').hide();//Added for CR-130
    });

//Added for CR-127 ends here

//Added for CR-130 starts here
	$('#ClauseChange6').click(function() {
		$('#saveSend').prop('disabled', true);
		$('#save').prop('disabled', true);
        $("#clauseChangeType").val(6);
        $("#reasonSubSec").html("");
		$(".secSeqNum").prop('checked',false);
        $("#divImportSubSection").find('[name="showgrid"]').empty();
        $("#divImportSubSection").find('[name="showsublink"]').hide();
        $("#divImportSubSection").find('[name="showmainlink"]').show();

        $('#divModClause').hide();
        $('#divAddClause').hide();
        $('#divChangeComp').hide();
        $('#divDelClause').hide();
        $('#divAddMdlClaChanges').hide();
        $('#divImportSubSection').show('normal');
    });

//Added for CR-130 ends here

    $("#sltSection").change(function() {
        $("#sectionSeqNo").val($(this).val());
        var sectionseqNo = $(this).val();
        $("#sltSection option").each(function() {
            if ($(this).val() == sectionseqNo) {
                $("#hdnSectionName").val($(this).html());

            }
        });
        ajaxsubsections(1);
        $("#sltLeadCompGroup option").remove();
        $("#sltLeadComp option").remove();
        $("#parentclause").html("");
        document.forms[0].hdnParentClauseSeqNo.value = "";
    });

    $("#sltSectionChngComp").change(function() {
        $("#sectionSeqNo").val($(this).val());
        ajaxsubsections(4);
        $("#sltLeadCompGroupChngComp option").remove();
        $("#sltLeadCompChngCompOld option").remove();
        $("#sltLeadCompChngCompNew option").remove();
    });

    $("#sltSubSection").change(function() {
        $("#subSectionSeqNo").val($(this).val());
        var subSectionseqNo = $(this).val();
        $("#sltSubSection option").each(function() {
            if ($(this).val() == subSectionseqNo) {
                $("#hdnSubSectionName").val($(this).html());
            }
        });
        ajaxComponentGroup(1);
        $("#sltLeadComp option").remove();
        $("#parentclause").html("");
        document.forms[0].hdnParentClauseSeqNo.value = "";
    });

    $("#sltSubSectionChngComp").change(function() {
        $("#subSectionSeqNo").val($(this).val());
        ajaxComponentGroup(4);
        $("#sltLeadCompChngCompOld option").remove();
        $("#sltLeadCompChngCompNew option").remove();
    });

    $("#sltLeadCompGroup").change(function() {
        $("#componentGrpSeqNoinAdd").val($(this).val());
        ajaxComponents(1);
        $("#sltLeadComp option").remove();
    });

    $("#sltLeadCompGroupChngComp").change(function() {
        $("#componentGrpSeqNoinAdd").val($(this).val());
        ajaxComponents(4);
        $("#sltLeadCompChngCompNew option").remove();
    });

    $("#sltLeadComp").change(function() {
        var LeadComponentSeqNoinAdd = $(this).val();
        $("#hdnLeadComponentSeqNoinAdd").val($(this).val());
        $("#sltLeadComp option").each(function() {
            if ($(this).val() == LeadComponentSeqNoinAdd) {
                $("#hdnLeadComponentNameinAdd").val($(this).html());

            }
        });

    });

    $("#sltLeadCompChngCompNew").change(function() {
        var LeadComponentSeqNoinAdd = $(this).val();
        $("#hdnLeadComponentSeqNoinAdd").val($(this).val());
        $("#sltLeadCompChngCompNew option").each(function() {
            if ($(this).val() == LeadComponentSeqNoinAdd) {
                $("#hdnLeadComponentNameinAdd").val($(this).html());
			}
        });
		//Added for CR_128 to make Change Component Clause to empty
        if ($("#sltLeadCompChngCompNew").val() == -1)	{
        	$("#clauseDescriptionChngComp").val("");
        	$("#standardEquipmentSeqNoChngComp").val(-1);
        }
        
        fnGetClausesForChangeComponent();
    });
    
    if ($('#checkVersionClause').val() == 1)
		inputDataAtLoad=$('#divModClause :input').not(document.getElementById('reasonMod')).serializeArray();
	else if ($('#clauseChangeType').val() == 2)
		inputDataAtLoad=$('#divModClause :input').not(document.getElementById('reasonMod')).serializeArray();
	if ($('#checkCompChangeClauses').val() == 1) 
		inputDataAtLoad=$('#divChangeComp :input').not(document.getElementById('reasonChngComp')).serializeArray();
			
	//Added for CR-126
		var addClauseRadio = $('#ClauseChange1');
		if(addClauseRadio.is(':checked')){
			$('#saveSend').prop('disabled', true);
			$('#save').prop('disabled', true);	
		}
		var modifyClauseRadio = $('#ClauseChange2');
			if(modifyClauseRadio.is(':checked')){
				$('#saveSend').prop('disabled',true);
				$('#save').prop('disabled',true);	
			}	
		var deleteClauseRadio = $('#ClauseChange3');
			if(deleteClauseRadio.is(':checked')){
				$('#saveSend').prop('disabled',true);
				$('#save').prop('disabled',true);	
			}
		var chngeCmpRadio = $('#ClauseChange4');
		if(chngeCmpRadio.is(':checked')){
			$('#saveSend').prop('disabled',true);
			$('#save').prop('disabled',true);	
		}
	// Added for CR-126 ends here	
	
	//Added for CR-127
		var addMdlClaChngRadio = $('#ClauseChange5');
		if(addMdlClaChngRadio.is(':checked')){
			$('#saveSend').prop('disabled', true);
			$('#save').prop('disabled',true);	
		}
		
		
		/*var mdlClaChng = $('#mdlClaChangeListSize').val();
			if(mdlClaChng <=0)
			{
				$('#ClauseChange5').prop('disabled',true);
			}
			
			else
			{
				if ($("#reviseClauseCheck").val() != 1)
					$('#ClauseChange5').prop('disabled',false);
			}*/
		
		
	// Added for CR-127 ends here
	
	//Added for CR-130
		var importSubSecRadio = $('#ClauseChange6');
		if(importSubSecRadio.is(':checked')){
			$('#saveSend').prop('disabled',true);
			$('#save').prop('disabled',true);
		}
		
		
		/*var impSubSec = $('#importSubSecListSize').val();
			if(impSubSec <=0)
			{
				$('#ClauseChange6').prop('disabled',true);
			}
			else
			{
				if ($("#reviseClauseCheck").val() != 1)
					$('#ClauseChange6').prop('disabled',false);
			}*/
		
		
	// Added for CR-130 ends here
});


function ajaxsections(divId)
{
    var sectionId = "";
    if (divId == 1) {
        sectionId = "sltSection";
    }
    if (divId == 4) {
        sectionId = "sltSectionChngComp";
    }
    var optionSelect = '<option selected value="-1">---Select---</option>';
    var optionSection = [];
    optionSection.push('<option selected value="-1">---Select---</option>');
    if ($("#mdlSeqNo").val() == "-1")//When Model is on select set all drop down values to select
    {
        fillSelect(sectionId, optionSelect, "Y");
    }
    else
    {
        $.getJSON("ChangeRequest1058Action.do?method=sectionLoadThruAJAX", {Model: $("#mdlSeqNo").val(), OrderKey: $('#orderKey').val(), DataLocType: $('#dataLocType').val(), ajax: 'true'}, function(j) {
            if (j != null)
            {
                $.each(j.Sections, function(i, item)//Get all model values here and append it to local var optionSection
                {
                    optionSection.push('<option value="', item.sectionSeqNo, '">', item.sectionCode + "." + item.sectionName, '</option>');
                })
                fillSelect(sectionId, optionSection.join(''), "N");
            }
            else {
                window.location = "AjaxException.do";
            }

        });

    }

}

function ajaxsubsections(divId)
{
    var sectionId = "";
    var subSectionId = "";
    if (divId == 1) {
        sectionId = "sltSection";
        subSectionId = "sltSubSection";
    }
    if (divId == 4) {
        sectionId = "sltSectionChngComp";
        subSectionId = "sltSubSectionChngComp";
    }

    var optionSelect = '<option selected value="-1">---Select---</option>';
    var optionSubSection = [];
    optionSubSection.push('<option selected value="-1">---Select---</option>');
    if ($(sectionId).val() == "-1")//When Model is on select set all drop down values to select
    {
        fillSelect(subSectionId, optionSelect, "Y");
    }
    else
    {
        $.getJSON("ChangeRequest1058Action.do?method=subSectionLoadThruAJAX", {Model: $("#mdlSeqNo").val(), Section: $('#sectionSeqNo').val(), OrderKey: $('#orderKey').val(), DataLocType: $('#dataLocType').val(), ajax: 'true'}, function(j) {
            if (j != null)
            {
                $.each(j.SubSections, function(i, item)//Get all model values here and append it to local var optionSubSection
                {
                    optionSubSection.push('<option value="', item.subSecSeqNo, '">', item.subCode + " " + item.subSecName, '</option>');
                })//Now i set the values in the SubSection drop down
                fillSelect(subSectionId, optionSubSection.join(''), "N");
            }
            else {
                window.location = "AjaxException.do";
            }

        })
    }

}


function ajaxComponentGroup(divId)
{
	//Added for CR-117 QA-Fixes
	$("#componentGrpSeqNoinAdd").val(0);
	$("#hdnLeadComponentSeqNoinAdd").val(0);
	$("#hdnLeadComponentNameinAdd").val("");
	//Added for CR-117 QA-Fixes Ends
    var sectionId = "";
    var subSectionId = "";
    if (divId == 1) {
        sectionId = "sltSection";
        subSectionId = "sltSubSection";
        componentGrpId = "sltLeadCompGroup";
    }
    if (divId == 4) {
        sectionId = "sltSectionChngComp";
        subSectionId = "sltSubSectionChngComp";
        componentGrpId = "sltLeadCompGroupChngComp";
    }

    var optionSelect = '<option selected value="-1">---Select---</option>';
    var optionComponentGroup = [];
    optionComponentGroup.push('<option selected value="-1">---Select---</option>');
    if ($(subSectionId).val() == "-1")//When Model is on select set all drop down values to select
    {
        fillSelect(componentGrpId, optionSelect, "Y");
    }
    else
    {
        $.getJSON("ChangeRequest1058Action.do?method=ComponentGroupThruAJAX", {Model: $("#mdlSeqNo").val(), Section: $('#sectionSeqNo').val(), SubSection: $('#subSectionSeqNo').val(), OrderKey: $('#orderKey').val(), DataLocType: $('#dataLocType').val(), ajax: 'true'}, function(j) {
            if (j != null)
            {
                $.each(j.ComponentGroups, function(i, item)//Get all model values here and append it to local var optionSubSection
                {
                    optionComponentGroup.push('<option value="', item.componentGroupSeqNo, '">', item.componentGroupName, '</option>');
                })//Now i set the values in the SubSection drop down
                fillSelect(componentGrpId, optionComponentGroup.join(''), "N");
            }
            else {
                window.location = "AjaxException.do";
            }

        })
    }

}


function ajaxComponents(divId)
{
	//Added For CR-117 QA Fixes
	$("#hdnLeadComponentSeqNoinAdd").val(0);
	$("#hdnLeadComponentNameinAdd").val("");
	var checkNoOldComponent =true; 
	//Added For CR-117 QA Fixes Ends Here
    var sectionId = "";
    var subSectionId = "";
    if (divId == 1) {
        sectionId = "sltSection";
        subSectionId = "sltSubSection";
        componentGrpId = "sltLeadCompGroup";
        componentId = "sltLeadComp";
    }
    if (divId == 4) {
        sectionId = "sltSectionChngComp";
        subSectionId = "sltSubSectionChngComp";
        componentGrpId = "sltLeadCompGroupChngComp";
        componentOldId = "sltLeadCompChngCompOld";
        componentId = "sltLeadCompChngCompNew";
    }

    var optionSelect = '<option selected value="-1">---Select---</option>';
    var optionComponents = [];
    var optionOldComp = [];
    optionComponents.push('<option selected value="-1">---Select---</option>');
    if ($(componentGrpId).val() == "-1")//When Model is on select set all drop down values to select
    {

        fillSelect(componentId, optionSelect, "Y");

    }
    else
    {
        $.getJSON("ChangeRequest1058Action.do?method=ComponentsThruAJAX", {Model: $("#mdlSeqNo").val(), Section: $('#sectionSeqNo').val(), SubSection: $('#subSectionSeqNo').val(), ComponentGroup: $('#componentGrpSeqNoinAdd').val(), OrderKey: $('#orderKey').val(), DataLocType: $('#dataLocType').val(), ajax: 'true'}, function(j) {


            if (j != null)
            {
                $.each(j.Components, function(i, item)//Get all model values here and append it to local var optionSubSection
                {

                    if (item.componentSeqNo == $('#hdnLeadComponentSeqNoinAdd').val()) {
                        optionComponents.push('<option value="', item.componentSeqNo, '" selected>', item.componentName, '</option>');

                        if (item.selectedComponent == 'Y') {
                            optionOldComp.push('<option value="', item.componentSeqNo, '">', item.componentName, '</option>');
                            $("#hdnOldComponentSeqNo").val(item.componentSeqNo);
                            $("#hdnOldComponentName").val(item.componentName);
                            checkNoOldComponent = false; //Added For CR-117 QA Fixes.
                        }
                        optionComponents.push('<option value="', item.componentSeqNo, '" selected>', item.componentName, '</option>');
                        //Added For CR-117 QA Fixes
                          if(checkNoOldComponent){
                        	 $("#hdnOldComponentSeqNo").val(0);
                            $("#hdnOldComponentName").val("");
                        }
                    }
                    else {
                        if (item.selectedComponent == 'Y') {
                            optionOldComp.push('<option value="', item.componentSeqNo, '">', item.componentName, '</option>');
                            $("#hdnOldComponentSeqNo").val(item.componentSeqNo);
                            $("#hdnOldComponentName").val(item.componentName);
                            checkNoOldComponent = false; //Added For CR-117 QA Fixes.
                        }
                        optionComponents.push('<option value="', item.componentSeqNo, '">', item.componentName, '</option>');
                          //Added For CR-117 QA Fixes
                          if(checkNoOldComponent){
                        	 $("#hdnOldComponentSeqNo").val(0);
                            $("#hdnOldComponentName").val("");
                        }
                    }

                })//Now i set the values in the SubSection drop down
                fillSelect(componentId, optionComponents.join(''), "N");
                if (divId == 4) {
                    fillSelect(componentOldId, optionOldComp.join(''), "N");
                }
            }
            else {
                window.location = "AjaxException.do";
            }

        })
    }
}


function ajaxStandardEquipMents(type)
{
    stdEqId = "";

    if (type == "ClauseChange1") {
        stdEqId = "standardEquipmentSeqNoAdd";
    }
    if (type == "ClauseChange2")
    {
        stdEqId = "standardEquipmentSeqNoMod";
    }
    if (type == "ClauseChange4") {
        stdEqId = "standardEquipmentSeqNoChngComp";
    }

    var optionSelect = '<option selected value="-1">---Select---</option>';
    var optionStdEquip = [];
    optionStdEquip.push('<option selected value="-1">---Select---</option>');

    if ($("#mdlSeqNo").val() == "-1")//When Model is on select set all drop down values to select
    {
        fillSelect(stdEqId, optionSelect, "Y");

    }
    else
    {
        $.getJSON("ChangeRequest1058Action.do?method=standardEquipmentThruAJAX", {ajax: 'true'}, function(j) {
            if (j != null)
            {
                $.each(j.StandardEquipment, function(i, item)//Get all model values here and append it to local var optionSubSection
                {
                    optionStdEquip.push('<option value="', item.standardEquipmentSeqNo, '">', item.standardEquipmentDesc, '</option>');
                })//Now i set the values in the SubSection drop down
                fillSelect(stdEqId, optionStdEquip.join(''), "N");
            }
            else {
                window.location = "AjaxException.do";
            }

        })
    }
}

function fillSelect(id, options, resizeFlag)//Just pass id and options: end result will have a fade in & fade out with data loaded in the drop down :)
{
    $("#" + id).fadeOut().html(options).fadeIn();
    if (resizeFlag == "Y")
    {
        if ($("#" + id).width() > 80)
            $("#" + id).css({width: '100'});
    }
    else if (resizeFlag == "N") {
        if ($("#" + id).width() < 100)
            $("#" + id).css({width: ''});
    }

}


function addTable(segMentId, reviseCheck)
{
    var div = "";
    if (segMentId == "divAddClause") {
        div = "Add";
    }
    if (segMentId == "divModClause") {
        div = "Mod";
    }
    if (segMentId == "divChangeComp") {
        div = "ChngComp";
    }

    var tblGrid = "tblGrid" + div;
    var clauseTableDataArray1 = "clauseTableDataArray1" + div;
    var clauseTableDataArray2 = "clauseTableDataArray2" + div;
    var clauseTableDataArray3 = "clauseTableDataArray3" + div;
    var clauseTableDataArray4 = "clauseTableDataArray4" + div;
    var clauseTableDataArray5 = "clauseTableDataArray5" + div;



    segMentId = "#" + segMentId;
    var val = '';
    if (reviseCheck == 0) {
        val = "<div id='"+ tblGrid +"' class='form-horizontal'><div class='row tablerow'>";
    } else {
        val = "<div id='"+ tblGrid +"' class='form-horizontal'><div class='row tablerow'>";
    }
    val = val+"<div class='col-sm-1'></div>";
    val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='"+clauseTableDataArray1+"' SIZE='13' MAXLENGTH='100'/></div>";
    val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='"+clauseTableDataArray2+"' SIZE='13' MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='"+clauseTableDataArray3+"' SIZE=13 MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='"+clauseTableDataArray4+"' SIZE=13 MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control textboxbluebold' type='text' name='"+clauseTableDataArray5+"' SIZE=13 MAXLENGTH='100'/></div>";
    val = val+"</div><div class='row tablerow'><div class='col-sm-1 text-right'><label class='radio-inline'><INPUT type='radio' name='deleterow'  /></label></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray1+"'  SIZE='15' MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray2+"' SIZE='15' MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray3+"' SIZE=15 MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray4+"' SIZE=15 MAXLENGTH='100'/></div>";
	val = val+"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray5+"' SIZE=15 MAXLENGTH='100'/></div>";
	val = val+"</div></div>";
    $(segMentId).find('[name="showgrid"]').append(val);
    $(segMentId).find('[name="showsublink"]').show();
    $(segMentId).find('[name="showmainlink"]').hide();

}

function addRow(src, segMentId)
{

    var div = "";
    if (segMentId == "divAddClause") {
        div = "Add";
    }
    if (segMentId == "divModClause") {
        div = "Mod";
    }
    if (segMentId == "divChangeComp") {
        div = "ChngComp";
    }


    var tblGrid = "tblGrid" + div;
    var clauseTableDataArray1 = "clauseTableDataArray1" + div;
    var clauseTableDataArray2 = "clauseTableDataArray2" + div;
    var clauseTableDataArray3 = "clauseTableDataArray3" + div;
    var clauseTableDataArray4 = "clauseTableDataArray4" + div;
    var clauseTableDataArray5 = "clauseTableDataArray5" + div;
	{
	$('#'+tblGrid+'').append("<div class='row tablerow'><div class='col-sm-1 text-right'>" +
		"<label class='radio-inline'><INPUT type='radio' name='deleterow'  /></label></div>" +
		"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray1+"'  SIZE='15' MAXLENGTH='100'/></div>" +
		"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray2+"' SIZE='15' MAXLENGTH='100'/></div>" +
		"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray3+"' SIZE=15 MAXLENGTH='100'/></div>" + 
		"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray4+"' SIZE=15 MAXLENGTH='100'/></div>" +
		"<div class='col-sm-2'>&nbsp;&nbsp;<INPUT CLASS='form-control' type='text' name='"+clauseTableDataArray5+"' SIZE=15 MAXLENGTH='100'/></div></div>");
	}

   /* rows = document.getElementById(tblGrid).getElementsByTagName('tbody')[0].getElementsByTagName('tr');

    for (i = 0; i < rows.length; i++)
    {

        index = this.rowIndex + 1;
        index1 = this.rowIndex;
    }

    var newRow = document.getElementById(tblGrid).insertRow(rows.length);
    var oCell = newRow.insertCell();
    oCell.innerHTML = "&nbsp;&nbsp;<input class='radcheck' type='radio' name='deleterow'/>&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='" + clauseTableDataArray1 + "' SIZE='15' MAXLENGTH='100'>";
    oCell = newRow.insertCell();
    oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='" + clauseTableDataArray2 + "' SIZE='15' MAXLENGTH='100'>";
    oCell = newRow.insertCell();
    oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='" + clauseTableDataArray3 + "' SIZE='15' MAXLENGTH='100'>";
    oCell = newRow.insertCell();
    oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='" + clauseTableDataArray4 + "' SIZE='15' MAXLENGTH='100'>";
    oCell = newRow.insertCell();
    oCell.innerHTML = "&nbsp;&nbsp;<input CLASS='COMMONTEXTBOX' type='text' name='" + clauseTableDataArray5 + "' SIZE='15' MAXLENGTH='100'>";
}*/
}

function removeRow(segMentId)
{
    var div = "";
    if (segMentId == "divAddClause") {
        div = "Add";
    }
    if (segMentId == "divModClause") {
        div = "Mod";
    }
    if (segMentId == "divChangeComp") {
        div = "ChngComp";
    }

    var tblGrid = "tblGrid" + div;

    var pos;
    var flag = false;
    var defaultrow;
    segMentId = "#" + segMentId + " ";
    var len = $(segMentId + '[name ="deleterow"]').length;
   	var val=$(segMentId + "input[type='radio'][name='deleterow']:checked").length;
   	
   	if (val > 0)	{
		var conf = window.confirm("Do you want to delete the selected row?","YesNo");
		$(segMentId + "input[type='radio'][name='deleterow']:checked").closest(".tablerow").remove();
	}
   	/*
    if (len == undefined && $(segMentId + '[name ="deleterow"]').is(':checked'))
    {
        defaultrow = true;
    }
    for (var i = 0; i < len; i++)
    {
        if ($(segMentId + '[name ="deleterow"]').eq(i).is(':checked'))
        {
            pos = i;
            flag = true;
            break;
        }

    }

    if (flag || defaultrow)
    {
        var conf = window.confirm("Are you sure to delete the selected row?", "YesNo");

        if (conf && flag)
        {

            document.all(tblGrid).deleteRow(pos + 1);
        }
        else if (conf && defaultrow)
        {
            document.getElementById(tblGrid).deleteRow(1);
        }
        else
        {
            return;
        }
    }*/

}


function deleteTable(segMentId)
{
    segMentId = "#" + segMentId;
    var conf = window.confirm("Are you sure to delete the table?", "YesNo");
    if (conf) {
        $(segMentId).find('[name="showgrid"]').empty();
        $(segMentId).find('[name="showsublink"]').hide();
        $(segMentId).find('[name="showmainlink"]').show();
        $(segMentId).find('[name="showmainlink"]').removeAttr("style");
    }
}

function LoadClauses(index)
{
    var orderNo = $('#orderNo').val();
    var orderKey = $('#orderKey').val();
    var modelSeqNo = $('#mdlSeqNo').val();
	 //Added for CR-121 DataLocation Type Issue 
    var dataLocType = $("#dataLocType").val();
    
    window.open("orderClausePartOfPopUpAction.do?method=initLoad&textBoxIndex=" + index + "&ModelSeqNo=" + modelSeqNo + "&OrderNum=" + orderNo + "&orderKey=" + orderKey + "&dataLocType=" + dataLocType + "", "Clause", "location=0,resizable=no,status=0,scrollbars=1,width=800,height=500");

}


function deletePartOf(index)
{
    document.forms[0].clauseSeqNum[index - 1].value = "";
    document.forms[0].partOf[index - 1].value = "";
    document.forms[0].partOfSeqNo[index - 1].value = "";
}



function fnCloseWindow()
{
    window.close();
}


function AddComponent()
{
	var selectedModelID = $('#mdlSeqNo').val();
    window.open("orderCompSearchAction.do?method=initLoad&selectedModelID=" + selectedModelID, "Clause", "location=0,resizable=no ,status=0,scrollbars=1,width=850,height=500");
}

function deleteComponent()
{
    if (document.forms[0].component.options.length != 0)
    {
        var del = confirm("Are you sure you want to clear all the components?");
        if (del == true)
        {
            document.forms[0].component.options.length = 0;
            document.forms[0].hdncomponentGroupSeqNo.value = "";
            document.forms[0].componentSeqNo.value = "";
            document.forms[0].hdnComponentName.value = "";

        }
    }

}


function fnCompSubmit(hiddencomponentGrpArray, hiddencomponentSeqNo, hiddencomponentGrpSeqNo)
{ 
    document.forms[0].hdncomponentGroupSeqNo.value = "";
    document.forms[0].hdnComponentName.value = "";
    existLength = document.forms[0].component.options.length;
    for (i = existLength; i < hiddencomponentGrpArray.length; i++)
    {
        additem = new Option();
        additem.value = hiddencomponentSeqNo[i];
        additem.text = hiddencomponentGrpArray[i];
        document.forms[0].component.options[existLength] = additem;
        document.forms[0].hdncomponentGroupSeqNo.value += hiddencomponentGrpSeqNo[i] + "~";
        document.forms[0].hdnComponentName.value += hiddencomponentGrpArray[i] + "~";
        existLength++;
    }

}


function LoadParentClauses() {
    var orderNo = $('#orderNo').val();
    var orderKey = $('#orderKey').val();
    var modelSeqNo = $('#mdlSeqNo').val();
    var sectionSeqNo = $("#sltSection").val();
    var subSectionSeqNo = $("#sltSubSection").val();
    var sectionName = $("#hdnSectionName").val();
    var subSectionName = $("#hdnSubSectionName").val();
    //Added for CR-121 DataLocation Type Issue 
    var dataLocType = $("#dataLocType").val();

    var valid = true;

    if (sectionSeqNo == -1 || sectionSeqNo == 0 || sectionSeqNo == null) {
        var id = '964';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("sltSection",-1);
        valid = false;
    }
    else {
        if (subSectionSeqNo == -1 || subSectionSeqNo == 0 || subSectionSeqNo == null) {
            var id = '965';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltSubSection",-1);
            valid = false;
        }
    }

    if (valid) {
        window.open("ChangeRequest1058Action.do?method=getParentClause&ModelSeqNo=" + modelSeqNo + "&sectionSeqNo=" + sectionSeqNo + "&sectionName=" + escape(sectionName) + "&subSectionSeqNo=" + subSectionSeqNo + "&subSectionName=" + escape(subSectionName) + "&OrderNum=" + orderNo + "&orderKey=" + orderKey + "&dataLocType=" + dataLocType + "", "Clause", "location=0,resizable=no,status=0,scrollbars=1,width=850,height=500");
    }
}

function submitDependantParentClause()
{
	var clauseNo;
    var selectedClauseDesc;
    var selIndex;
    var flag = false;
    var reservedFlag = false;
    if (document.forms['ChangeRequest1058Form'].clauseSeqNo == null)
    {
        var id = '16';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("btnCancel",-1);
        return false;
    }
    //Added for CR_121
    var index =0;
    //Added for CR_121 Ends
    if (document.forms['ChangeRequest1058Form'].clauseSeqNo.length != undefined)
    {
        for (var i = 0; i < document.forms['ChangeRequest1058Form'].clauseSeqNo.length; i++)
        {
            if (document.forms['ChangeRequest1058Form'].clauseSeqNo[i].checked)
            {
                if (document.forms['ChangeRequest1058Form'].reservedFlag[i].value != "Y")
                {
                    reservedFlag = true;
                    window.opener.$("#parentclause").html(document.forms[0].clauseDescription[i].value);
                    window.opener.$("#hdnParentClauseSeqNo").val(document.forms[0].clauseSeqNo[i].value);
                }
                flag = true;
                index =i; //Added for CR_121
                break;
            }
        }
    }
    else
    {
        if (document.forms['ChangeRequest1058Form'].clauseSeqNo.checked)
        {
            if (document.forms['ChangeRequest1058Form'].reservedFlag.value != "Y")
            {
                reservedFlag = true;
                window.opener.$("#parentclause").html(document.forms[0].clauseDescription.value);
                window.opener.$("#hdnParentClauseSeqNo").val(document.forms[0].clauseSeqNo.value);
            }
            flag = true;
        }
    }

    if (flag && !reservedFlag) {
    	var id = '762';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("clauseSeqNo",index);
        return false;
    }
    if (!flag)
    {
        var id = '520';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("clauseSeqNo",-1);
        return false;
    }
    else {
        window.close();
    }
}

function deleteParentClause() {
    if ($("#hdnParentClauseSeqNo").val() != "" && $("#hdnParentClauseSeqNo").val() != 0) {
        var del = confirm("Are you sure you want to clear the Parent Clause?");
        if (del == true)
        {
            $("#parentclause").html("");
            document.forms[0].hdnParentClauseSeqNo.value = "";
        }
    }
}




function fnCompSubmit(hiddencomponentGrpArray, hiddencomponentSeqNo, hiddencomponentGrpSeqNo)
{
    document.forms[0].hdncomponentGroupSeqNo.value = "";
    document.forms[0].hdnComponentName.value = "";
    existLength = document.forms[0].component.options.length;
    for (i = existLength; i < hiddencomponentGrpArray.length; i++)
    {
        additem = new Option();
        additem.value = hiddencomponentSeqNo[i];
        additem.text = hiddencomponentGrpArray[i];
        document.forms[0].component.options[existLength] = additem;
        document.forms[0].hdncomponentGroupSeqNo.value += hiddencomponentGrpSeqNo[i] + "~";
        document.forms[0].componentSeqNo.value += hiddencomponentSeqNo[i] + "~";
        document.forms[0].hdnComponentName.value += hiddencomponentGrpArray[i] + "~";
        existLength++;
    }
}


function fnSaveAddClause(reviseCheck, divName) {
    var valid = fnClauseValidation(reviseCheck, divName);
    var url = "";
    if (valid) {
        fnCheckReqDetRadios();
        var clauseDesc = trim($('#addClauseDescription').val());
        if (reviseCheck == 1) {
            url = "ChangeRequest1058Action.do?method=saveAddClause&reviseCheck=" + reviseCheck;
        }
        else {
            url = "ChangeRequest1058Action.do?method=saveAddClause";
        }
        fnValidateClauseDesc(clauseDesc,url,'addClauseDescription');

    }
}

function fnSaveModifyClause(reviseCheck, divName) {
    var valid = fnClauseValidation(reviseCheck, divName);
  	var url = "";
    if (valid) {
    	fnCheckReqDetRadios();
		
		var inputDataAtSubmit=$('#divModClause :input').not(document.getElementById('reasonMod')).serializeArray();
		var matched =checkVersion(inputDataAtSubmit);
		if (!matched)	{
			$('#hdnVersionNoMod').val(0);
		}
		        
        var clauseDesc = trim($('#clauseDescriptionMod').val());
        if (reviseCheck == 1) {
            url = "ChangeRequest1058Action.do?method=saveModifyClause&reviseCheck=" + reviseCheck;
        }
        else {
            url = "ChangeRequest1058Action.do?method=saveModifyClause";
        }
        fnValidateClauseDesc(clauseDesc,url,'clauseDescriptionMod');
    }
}

function fnSaveDeleteClause(reviseCheck, divName) {
    var valid = fnDelClauseValidation(reviseCheck, divName);
    if (valid) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        if (reviseCheck == 1) {
            document.forms[0].action = "ChangeRequest1058Action.do?method=saveDeleteClause&reviseCheck=" + reviseCheck;
        }
        else {
            document.forms[0].action = "ChangeRequest1058Action.do?method=saveDeleteClause";
        }
        document.forms[0].submit();
    }
}

function fnSaveChangeComponent(reviseCheck, divName) {
    var valid = fnClauseValidation(reviseCheck, divName);
    var url = "";
    
    if (valid) {
        fnCheckReqDetRadios();   
        if ($("#sltLeadCompChngCompNew").val() != -1)	{
	        var inputDataAtSubmit=$('#divChangeComp :input').not(document.getElementById('reasonChngComp')).serializeArray();
	        var matched =checkVersion(inputDataAtSubmit);
			if (!matched)	{  	
	   	 		$('#clauseToVerNo').val(0);
	    	}
        }
        var clauseDesc = trim($('#clauseDescriptionChngComp').val());
        if (reviseCheck == 1) {
			url = "ChangeRequest1058Action.do?method=saveChangeComponent&reviseCheck=" + reviseCheck;
        }
        else {
            url = "ChangeRequest1058Action.do?method=saveChangeComponent";
        }
        if ($("#sltLeadCompChngCompNew").val() != -1)	{ //Added only specific validations for CR_128
        	fnValidateClauseDesc(clauseDesc,url,'clauseDescriptionChngComp');
        } else {
        	document.forms[0].action=url;
			document.forms[0].submit();
        }
    }
}

//Added for CR-127
function fnSaveMdlClaChanges(reviseCheck, divName) {
	var valid = fnAddMdlClauseValidation(reviseCheck, divName);
    if (valid) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        if (reviseCheck == 1) {
            document.forms[0].action = "ChangeRequest1058Action.do?method=addMdlClauseChanges&reviseCheck=" + reviseCheck;
        }
        else {
            document.forms[0].action = "ChangeRequest1058Action.do?method=addMdlClauseChange";
       }
        document.forms[0].submit();
    }
}
//Added for CR-127 ends here

//Added for CR-130
function fnSaveImpSubSecClaChanges(reviseCheck, divName) {
	var valid = fnImpSubSecValidation(reviseCheck, divName);
    if (valid) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        if (reviseCheck == 1) {
            document.forms[0].action = "ChangeRequest1058Action.do?method=addMdlSubSecChanges&reviseCheck=" + reviseCheck;
        }
        else {
            document.forms[0].action = "ChangeRequest1058Action.do?method=addMdlSubSecChanges";
       }
        document.forms[0].submit();
    }
}
//Added for CR-130 ends here

function fnDeleteSelectedClause() {
    var chngClaSeqNo = 0;

    chngClaSeqNo = $('input:radio[name=reqSpecChkRadio]:checked').val();
    if (chngClaSeqNo == 0 || chngClaSeqNo == null) {
        var id = '949';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reqSpecChkRadio",-1);
    }
    else {
        if (confirm("Are you sure to delete the selected Request Change?")) {
            fnCheckReqDetRadios();
            fnDisableButtons("");
            document.forms[0].action = "ChangeRequest1058Action.do?method=deleteSelectedClause&chngClaSeqNo=" + chngClaSeqNo;
            document.forms[0].submit();
        }
    }
}

function fnReviseSelectedClause() {
    var chngClaSeqNo = 0;
    chngClaSeqNo = $('input:radio[name=reqSpecChkRadio]:checked').val();
	var chkSubSec = $('input:radio[name=reqSpecChkRadio]:checked').attr("entity") //Added for CR-130
	
    if (chngClaSeqNo == 0 || chngClaSeqNo == null) {
        var id = '949';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reqSpecChkRadio",-1);
    }else if(chkSubSec == "subSec"){    //Else If condition added for CR-130
    	var id = '1037';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reqSubSecChkRadio",-1);
    }
    else {
        fnCheckReqDetRadios();
	    fnDisableButtons("");
        document.forms[0].action = "ChangeRequest1058Action.do?method=reviseSelectedClause&chngClaSeqNo=" + chngClaSeqNo;
        document.forms[0].submit();
    }
}


function fnUpdateClausetoSpec() {
    var chngClaSeqNo = 0;
    chngClaSeqNo = $('input:radio[name=reqSpecChkRadio]:checked').val();
    if (chngClaSeqNo == 0 || chngClaSeqNo == null) {
        var id = '949';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reqSpecChkRadio",-1);
    }
    else {
        if (confirm("Are you sure to update the selected Request Change to spec?")) {
            fnCheckReqDetRadios();
            fnDisableButtons("");
            document.forms[0].action = "ChangeRequest1058Action.do?method=updateClausesToSpec&chngClaSeqNo=" + chngClaSeqNo;
            document.forms[0].submit();
        }
    }
}

function fnUpdateAllClausestoSpec() {
    if (confirm("Are you sure to update all the Requested Changes to spec?")) {
        fnCheckReqDetRadios();
        fnDisableButtons("");
        document.forms[0].action = "ChangeRequest1058Action.do?method=updateClausesToSpec";
        document.forms[0].submit();
    }
}

function fnGetClausesForChangeComponent() {
    fnCheckReqDetRadios();
    fnDisableButtons("");
    document.forms[0].action = "ChangeRequest1058Action.do?method=getClausesForChangeComponent";
    document.forms[0].submit();
}



function showTinyMce(id) {

    $("#" + id).tinymce({
        	script_url : 'js/Others/tiny_mce/tinymce.min.js',
        	content_css : "css/tinymce.css",
	      	convert_urls : false,
			theme : "modern",
			/*added entity '&' for removing &nbsp; and should be replaced with normal space for CR_92*/
			entity_encoding : "named",
			selector : id,
			plugins : "paste",
			browser_spellcheck : true,
			toolbar : "bold,italic,|,removeJunkChar,|,undo,redo,|,paste",// Commented for CR_104 ,|,spellchk",
			menubar : false,
			statusbar : true,
			resize: false,
			width : 450,
			height: 200,
			valid_elements: "strong/b,em/i,p,br",
			//remove_linebreaks : "true",
			//convert_newlines_to_brs : "true",
        	//force_br_newlines : "true",
			//force_p_newlines : "false",
			paste_auto_cleanup_on_paste : "true",
			paste_remove_spans : "true",
			paste_remove_styles : "true",
			paste_as_text: "true", //Added for preserve double spaces -CR-130
			paste_preprocess : function(pl, o) {
            	// Remove empty paragraphs while pasting from word
	            o.content = o.content.replace(/<p>\u00a0<\/p>/gi, ''); 
	            o.content = o.content.replace(/<p>&nbsp;<\/p>/gi, '');
        	},
    	   	paste_postprocess : function(pl, o) { //Added for CR-121
            	tinymce.activeEditor.theme.panel.find('#wordcount').text(['{0}', tinymce.activeEditor.getContent().replace(/(<([^>]+)>)/ig,"").length]);
	        },
        setup: function(ed) {
            // Add a custom button
            /* Commented for CR_104 making the spell checker part of Add button
             ed.addButton('spellchk', {
             title : 'Spell Check',
             onclick : function() {
             // Call to spell check function
             //popUpCheckSpellingrapidSpellWebLauncher('rsTCIntrapidSpellWebLauncher');
             rswi1_SpellChecker.OnSpellButtonClicked();    
             }
             });*/
             //Added for CR-114 Remove Junk Character Functionality
				ed.addButton('removeJunkChar', {
					title : 'Remove Junk Characters',
					onclick : function() {
						var junkRem =confirm("Are you sure you want to remove the junk characters?");
						if(junkRem){
							var oldClauseDesc = trim($("#" + id).val());
							$.getJSON("commonAppUtilAction.do?method=validateClauseDescriptionThruAJAX",{clauseDesc:oldClauseDesc, ajax: 'true',cache: 'false'}, function(data){
    						if (data != null)
    							{		
									$.each(data.Clause, function(i,item) 
									{
						    			if(item.claDiffFlag=='Y')
							    			{
			            						$("#" + id).val(item.newClauseDesc);
			            						ed.on('Undo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
			            						ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
			         						}
		         					});
		         				}
		         			});	
						}
					},
					image: "images/junkremove.png"
				});
				//Added for CR-114 Remove Junk Character Functionality Ends
         		ed.on('PostProcess', function(e) {
                    // Remove empty paragraphs on submitting the clause description
                   	//e.content = e.content.replace(/<p><\/p>/g, '');
                 });  
              	
              	ed.on('init', function() {
              		// Remove empty paragraphs when loading existing Clause Descriptions
              		//Added for CR-129 for loading clauses in TinyMCE Issue
              		//Modified for CR_131 to enable spell check on load
              		setTimeout(function () {
						tinymce.execCommand('mceSpellCheck', true);
					 }, 1);
					if ($('#hdnclauseDescForTextArea').length > 0) {
	              		var preFormattedtext = (($('#hdnclauseDescForTextArea').val()).replace(/\n\n/g, '</p><p>&nbsp;</p>')).replace(/\n/g, '</p><p>');
	              		tinymce.activeEditor.setContent(preFormattedtext);
	              	} 
              		//Added for CR-129 for loading clauses in TinyMCE Issue ends here
              		//tinymce.activeEditor.setContent(tinymce.get(id).getContent().replace(/<p>&nbsp;<\/p>/g, ''));
              		//fnCharCounter();
              		var statusbar = ed.theme.panel && ed.theme.panel.find('#statusbar')[0];
					if (statusbar) {
	              		statusbar.insert({
							type: 'label',
							name: 'wordcount',
							text: ['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length],
							classes: 'wordcount',
							disabled: ed.settings.readonly
						}, 0);
					}
					$(ed.getContainer()).find(".mce-path").css("opacity", "0");
              	});
              	ed.on('keyup', function(e) {
              		ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 });
   				ed.on('focus', function(e) {
   				 	if($(".scrollError").val()==""){
   				 		
   				 		$(".scrollError").tooltip("disable");
	    	 			$(".scrollError").attr("title","");
	    	 			$(".scrollError").removeClass("scrollError");
	              	}
               });
   				ed.on('change', function(e) { 
	               ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 }); 
   				//Added for CR-114 QA Fixes 
   				ed.on('Undo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
   				ed.on('Redo', function() {ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);}); 
   				ed.on('keypress', function(e) {
		 	       ed.theme.panel.find('#wordcount').text(['{0}', ed.getContent().replace(/(<([^>]+)>)/ig,"").length]);
   				 });
   				 
   				 
        }
    });
}


//Validations

function fnClauseValidation(reviseCheck, divName) {
    var mod = document.forms['ChangeRequest1058Form'];
    var i = 0;
    var clauseValidation = true; //Added for CR_128
    if (divName == "divAddClause")
    {  
    	div = "Add";
    	var clauseDesc = trim(tinyMCE.get('addClauseDescription').getContent());
        var dwoNumber = trim(mod.dwoNoAdd.value);
        var partNumber = trim(mod.partNoAdd.value);
        var priceBookNumber = trim(mod.priceBookNoAdd.value);
        var comments = trim(mod.commentsAdd.value);
        var reason = trim(mod.reasonAdd.value);

        var DwoNumber = mod.dwoNoAdd;
        var PartNumber = mod.partNoAdd;
        var PriceBookNumber = mod.priceBookNoAdd;
        var Comments = mod.commentsAdd;
		
        if ($("#sltSection").val() == -1) {
            var id = '205';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltSection",-1);
            return false;
        }
        if ($("#sltSubSection").val() == -1) {
            var id = '182';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltSubSection",-1);
            return false;
        }
        
        //Added for lead component selection error message fix
         if (($('#sltLeadCompGroup').val()!="-1"))
			{	
				if (($('#sltLeadComp').val()=="-1"))
				{
				var id = '789';
				hideErrors();
				addMsgNum(id);
				showScrollErrors("sltLeadComp",-1);
				return false;
				}  
			}
		//Ends
        if (clauseDesc == "") {
            var id = '506';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("addClauseDescription_ifr",-1);
            
            return false;
        }
         
		
        /* Commented for CR-121
         if ($('#addClauseDescription').val().length > 4000) {

            var id = '516';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("addClauseDescription",-1);
            return false;
        }*/
        var refEdl1 = trim($("[name='refEdlNoAdd']").eq(0).val());
        var refEdl2 = trim($("[name='refEdlNoAdd']").eq(1).val());
        var refEdl3 = trim($("[name='refEdlNoAdd']").eq(2).val());
        var refEdl = refEdl1 + refEdl2 + refEdl3;
        var edlNo1 = trim($("[name='newEdlNoAdd']").eq(0).val());
        var edlNo2 = trim($("[name='newEdlNoAdd']").eq(1).val());
        var edlNo3 = trim($("[name='newEdlNoAdd']").eq(2).val());
        var edlNo = edlNo1 + edlNo2 + edlNo3;
        var compSeqNo;
        i = 4;
        mod.addClauseDescription.value = clauseDesc;
        mod.commentsAdd.value = comments;
        mod.reasonAdd.value = reason;
        mod.dwoNoAdd.value = dwoNumber;
        mod.partNoAdd.value = partNumber;
        mod.priceBookNoAdd.value.value = priceBookNumber;

        mod.newEdlNoAdd[0].value = edlNo1;
        mod.newEdlNoAdd[1].value = edlNo2;
        mod.newEdlNoAdd[2].value = edlNo3;
        mod.refEdlNoAdd[0].value = refEdl1;
        mod.refEdlNoAdd[1].value = refEdl2;
        mod.refEdlNoAdd[2].value = refEdl3;

        var clauseTableDataArray1 = mod.clauseTableDataArray1Add;
        var clauseTableDataArray2 = mod.clauseTableDataArray2Add;
        var clauseTableDataArray3 = mod.clauseTableDataArray3Add;
        var clauseTableDataArray4 = mod.clauseTableDataArray4Add;
        var clauseTableDataArray5 = mod.clauseTableDataArray5Add;
        var standardEquipmentSeqNo = mod.standardEquipmentSeqNoAdd;
    }


    else if (divName == "divModClause") {
    	div = "Mod"; //Added for CR_121
        var mod = document.forms['ChangeRequest1058Form'];
        var clauseDesc = trim(tinyMCE.get('clauseDescriptionMod').getContent());
        var dwoNumber = trim(mod.dwoNoMod.value);
        var partNumber = trim(mod.partNoMod.value);
        var priceBookNumber = trim(mod.priceBookNoMod.value);
        var comments = trim(mod.commentsMod.value);
        var reason = trim(mod.reasonMod.value);

        var DwoNumber = mod.dwoNoMod;
        var PartNumber = mod.partNoMod;
        var PriceBookNumber = mod.priceBookNoMod;
        var Comments = mod.commentsMod;

        if ($('#clauseToModify').val() == "") {
            var id = '962';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("clauseToModify",-1);
            return false;
        }


        if (clauseDesc == "") {
            var id = '506';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("clauseDescriptionMod_ifr",-1);
            return false;
        }

       /* Commented for CR-121
        if ($('#clauseDescriptionMod').val().length > 4000) {

            var id = '516';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("clauseDescriptionMod",-1);
            return false;
        }
       */
        var refEdl1 = trim($("[name='refEdlNoMod']").eq(0).val());
        var refEdl2 = trim($("[name='refEdlNoMod']").eq(1).val());
        var refEdl3 = trim($("[name='refEdlNoMod']").eq(2).val());

        var refEdl = refEdl1 + refEdl2 + refEdl3;


        var edlNo1 = trim($("[name='newEdlNoMod']").eq(0).val());
        var edlNo2 = trim($("[name='newEdlNoMod']").eq(1).val());
        var edlNo3 = trim($("[name='newEdlNoMod']").eq(2).val());

        var edlNo = edlNo1 + edlNo2 + edlNo3;

        var compSeqNo;
        i = 0;
        mod.clauseDescriptionMod.value = clauseDesc;
        mod.commentsMod.value = comments;
        mod.reasonMod.value = reason;
        mod.dwoNoMod.value = dwoNumber;
        mod.partNoMod.value = partNumber;
        mod.priceBookNoMod.value.value = priceBookNumber;

        mod.newEdlNoMod[0].value = edlNo1;
        mod.newEdlNoMod[1].value = edlNo2;
        mod.newEdlNoMod[2].value = edlNo3;
        mod.refEdlNoMod[0].value = refEdl1;
        mod.refEdlNoMod[1].value = refEdl2;
        mod.refEdlNoMod[2].value = refEdl3;

        var clauseTableDataArray1 = mod.clauseTableDataArray1Mod;
        var clauseTableDataArray2 = mod.clauseTableDataArray2Mod;
        var clauseTableDataArray3 = mod.clauseTableDataArray3Mod;
        var clauseTableDataArray4 = mod.clauseTableDataArray4Mod;
        var clauseTableDataArray5 = mod.clauseTableDataArray5Mod;
        var standardEquipmentSeqNo = mod.standardEquipmentSeqNoMod;

    }

    else if (divName == "divChangeComp") {
    	div = "ChngComp"; //Added for CR_121trim(mod.clauseDescriptionChngComp.value)
        var clauseDesc = trim(tinyMCE.get('clauseDescriptionChngComp').getContent());
        var dwoNumber = trim(mod.dwoNoChngComp.value);
        var partNumber = trim(mod.partNoChngComp.value);
        var priceBookNumber = trim(mod.priceBookChngComp.value);
        var comments = trim(mod.commentsChngComp.value);
        var reason = trim(mod.reasonChngComp.value);

        var DwoNumber = mod.dwoNoChngComp;
        var PartNumber = mod.partNoChngComp;
        var PriceBookNumber = mod.priceBookChngComp;
        var Comments = mod.commentsChngComp;

        if ($("#sltSectionChngComp").val() == -1) {
            var id = '205';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltSectionChngComp",-1);
            return false;
        }
        if ($("#sltSubSectionChngComp").val() == -1) {
            var id = '182';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltSubSectionChngComp",-1);
            return false;
        }
		if ($("#sltLeadCompGroupChngComp").val() == -1) {
            var id = '183';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("sltLeadCompGroupChngComp",-1);
            return false;
        }
        /* Modified validation for CR-128 */
        if ($("#sltLeadCompChngCompNew").val() == -1)	{
        	
        	if (($("#sltLeadCompChngCompOld").val() == -1) || ($("#sltLeadCompChngCompOld").val() == null)) {
        		var id = '1009';
	            hideErrors();
	            addMsgNum(id);
	            showScrollErrors("sltLeadCompChngCompNew",-1);
	            return false;
        	}
        	else
	        	clauseValidation = false;
	        	
        }
        else if ($("#sltLeadCompChngCompNew").val() != -1)	{
        	
			if ($("#sltLeadCompChngCompNew").val() == -1) {
	            var id = '1009';
	            hideErrors();
	            addMsgNum(id);
	            showScrollErrors("sltLeadCompChngCompNew",-1);
	            return false;
	        }
	        if (clauseDesc == "") {
	            var id = '506';
	            hideErrors();
	            addMsgNum(id);
	            showScrollErrors("clauseDescriptionChngComp_ifr",-1);
	            return false;
	        }
	
	        /* Commented for CR-121
	        if ($('#clauseDescriptionChngComp').val().length > 4000) {
	
	            var id = '516';
	            hideErrors();
	            addMsgNum(id);
	            showScrollErrors("clauseDescriptionChngComp",-1);
	            return false;
	        }*/
	
	        var refEdl1 = trim($("[name='refEdlNoChngComp']").eq(0).val());
	        var refEdl2 = trim($("[name='refEdlNoChngComp']").eq(1).val());
	        var refEdl3 = trim($("[name='refEdlNoChngComp']").eq(2).val());
	
	        var refEdl = refEdl1 + refEdl2 + refEdl3;
	
	        var edlNo1 = trim($("[name='newEdlNoChngComp']").eq(0).val());
	        var edlNo2 = trim($("[name='newEdlNoChngComp']").eq(1).val());
	        var edlNo3 = trim($("[name='newEdlNoChngComp']").eq(2).val());
	
	        var edlNo = edlNo1 + edlNo2 + edlNo3;
	        var compSeqNo;
	        i = 8;
	        mod.clauseDescriptionChngComp.value = clauseDesc;
	        mod.commentsChngComp.value = comments;
	        mod.reasonChngComp.value = reason;
	        mod.dwoNoChngComp.value = dwoNumber;
	        mod.partNoChngComp.value = partNumber;
	        mod.priceBookChngComp.value.value = priceBookNumber;
	
	        mod.newEdlNoChngComp[0].value = edlNo1;
	        mod.newEdlNoChngComp[1].value = edlNo2;
	        mod.newEdlNoChngComp[2].value = edlNo3;
	        mod.refEdlNoChngComp[0].value = refEdl1;
	        mod.refEdlNoChngComp[1].value = refEdl2;
	        mod.refEdlNoChngComp[2].value = refEdl3;
	
	        var clauseTableDataArray1 = mod.clauseTableDataArray1ChngComp;
	        var clauseTableDataArray2 = mod.clauseTableDataArray2ChngComp;
	        var clauseTableDataArray3 = mod.clauseTableDataArray3ChngComp;
	        var clauseTableDataArray4 = mod.clauseTableDataArray4ChngComp;
	        var clauseTableDataArray5 = mod.clauseTableDataArray5ChngComp;
	        var standardEquipmentSeqNo = mod.standardEquipmentSeqNoChngComp;
	        
        	}	
	    }
		
		if (clauseValidation)	{
		    if (reviseCheck == 1) {
		        var partOfSeqNo1 = trim(mod.partOfSeqNo[0].value);
		        var partOfSeqNo2 = trim(mod.partOfSeqNo[1].value);
		        var partOfSeqNo3 = trim(mod.partOfSeqNo[2].value);
		        var partOfSeqNo4 = trim(mod.partOfSeqNo[3].value);
		    }
		    else {
		        var partOfSeqNo1 = trim(mod.partOfSeqNo[i].value);
		        var partOfSeqNo2 = trim(mod.partOfSeqNo[i + 1].value);
		        var partOfSeqNo3 = trim(mod.partOfSeqNo[i + 2].value);
		        var partOfSeqNo4 = trim(mod.partOfSeqNo[i + 3].value);
		    }
		
		    if (reviseCheck == 1) {
		        var partOfclaSeqNo1 = $("[name='clauseSeqNum']").eq(0).val();
		        var partOfclaSeqNo2 = $("[name='clauseSeqNum']").eq(1).val();
		        var partOfclaSeqNo3 = $("[name='clauseSeqNum']").eq(2).val();
		        var partOfclaSeqNo4 = $("[name='clauseSeqNum']").eq(3).val();
		    }
		    else {
		        var partOfclaSeqNo1 = $("[name='clauseSeqNum']").eq(i).val();
		        var partOfclaSeqNo2 = $("[name='clauseSeqNum']").eq(i + 1).val();
		        var partOfclaSeqNo3 = $("[name='clauseSeqNum']").eq(i + 2).val();
		        var partOfclaSeqNo4 = $("[name='clauseSeqNum']").eq(i + 3).val();
		    }
		    if (clauseTableDataArray1 != null)
		    {
		        for (var i = 0; i < clauseTableDataArray1.length; i++)
		        {
		            clauseTableDataArray1[i].value = trim(clauseTableDataArray1[i].value);
		        }
		    }
		    if (clauseTableDataArray2 != null)
		    {
		        for (var i = 0; i < clauseTableDataArray2.length; i++)
		        {
		            clauseTableDataArray2[i].value = trim(clauseTableDataArray2[i].value);
		        }
		    }
		    if (clauseTableDataArray3 != null)
		    {
		        for (var i = 0; i < clauseTableDataArray3.length; i++)
		        {
		            clauseTableDataArray3[i].value = trim(clauseTableDataArray3[i].value);
		        }
		    }
		    if (clauseTableDataArray4 != null)
		    {
		        for (var i = 0; i < clauseTableDataArray4.length; i++)
		        {
		            clauseTableDataArray4[i].value = trim(clauseTableDataArray4[i].value);
		        }
		    }
		    if (clauseTableDataArray5 != null)
		    {
		        for (var i = 0; i < clauseTableDataArray5.length; i++)
		        {
		            clauseTableDataArray5[i].value = trim(clauseTableDataArray5[i].value);
		        }
		    }
		
		    if (clauseTableDataArray1 != null)
		    {
		        if (clauseTableDataArray1.length != undefined)
		        {
		
		            for (var i = 0; i < clauseTableDataArray1.length; i++)
		            {
		                if ((clauseTableDataArray1[i].value == "") && (clauseTableDataArray2[i].value == "") && (clauseTableDataArray3[i].value == "") && (clauseTableDataArray4[i].value == "") && (clauseTableDataArray5[i].value == ""))
		                {
		                    var id = '526';
		                    hideErrors();
		                    addMsgNum(id);
		                    showScrollErrors("clauseTableDataArray1"+div,i);
		                    return false;
		
		                }
		            }
		        }
		        else
		        {
		            if ((trim(clauseTableDataArray1.value) == "") && (trim(clauseTableDataArray2.value) == "") && (trim(clauseTableDataArray3.value) == "") && (trim(clauseTableDataArray4.value) == "") && (trim(clauseTableDataArray5.value) == ""))
		            {
		
		                var id = '526';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("clauseTableDataArray1"+div,-1);
		                return false;
		
		            }
		        }
		    }
		
		    if ((standardEquipmentSeqNo.value == -1) && (refEdl1 == "") && (refEdl2 == "") && (refEdl3 == "") && (edlNo1 == "") && (edlNo2 == "") && (edlNo3 == "") && (partOfSeqNo1.length == 0 || partOfSeqNo1 == 0) && (partOfSeqNo2.length == 0 || partOfSeqNo2 == 0) && (partOfSeqNo3.length == 0 || partOfSeqNo3 == 0) && (partOfSeqNo4.length == 0 || partOfSeqNo4 == 0) && (dwoNumber.length == 0 || dwoNumber == "") && (partNumber.length == 0 || partNumber == "") && (priceBookNumber.length == 0 || priceBookNumber == "") && (comments.length == 0 || comments == ""))
		    {
		    	var id = '505';
		        hideErrors();
		        addMsgNum(id);
		       	showScrollErrors("refEdlNo"+div,0);
		       	return false;
		    }
		    else
		    {
		
		        if ((refEdl1 < 0) && (refEdl2 < 0 || refEdl2.length == 0) && (refEdl3 < 0 || refEdl3.length == 0))
		        {
		            if (refEdl1 != "")
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,0);
		
		                return false;
		            }
		        }
		        if (refEdl1.length != 0 || refEdl1 != "")
		        {
		            if (refEdl1 < 0)
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,0);
		
		                return false;
		            }
		            if (refEdl1 == refEdl2 || refEdl1 == refEdl3)
		            {
		                var id = '510';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,0);
		
		                return false;
		
		            }
		
		
		        }
		
		        if ((refEdl2 < 0) && ((refEdl1 < 0) || (refEdl1.length == 0)) && ((refEdl3 < 0) || (refEdl3.length == 0)))
		        {
		            if (refEdl2 != "")
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,1);
		
		                return false;
		            }
		        }
		        if (refEdl2.length != 0 || refEdl2 != "")
		        {
		            if (refEdl2 < 0)
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,1);
		
		                return false;
		            }
		            if (refEdl1 == refEdl2 || refEdl2 == refEdl3)
		            {
		                var id = '510';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,1);
		
		                return false;
		
		            }
		
		        }
		
		        if ((refEdl3 < 0) && ((refEdl2 < 0) || (refEdl2.length == 0)) && ((refEdl1 < 0) || (refEdl1.length == 0)))
		        {
		            if (refEdl3 != "")
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,2);
		
		                return false;
		            }
		        }
		        if (refEdl3.length != 0 || refEdl3 != "")
		        {
		            if (refEdl3 < 0)
		            {
		                var id = '528';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,2);
		
		                return false;
		            }
		
		            if (refEdl2 == refEdl3 || refEdl1 == refEdl3)
		            {
		                var id = '510';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("refEdlNo"+div,2);
		
		                return false;
		
		            }
		
		        }
		
		        /**
		         Code commented as per the client requirement.
		         To make RefEDL and EDL number as a alpha numeric field.
		         Modified on 29-09-08
		         by ps57222.
		         **/
		
		
		        /* for(i=0;i<document.forms[0].edlNo.length;i++){
		         if(document.forms[0].edlNo[i].value != ""){
		         if(!validateNumber(document.forms[0].edlNo[i].value)){
		         
		         var id = '514';
		         hideErrors();
		         addMsgNum(id);
		         showErrors();
		         document.forms[0].edlNo[i].focus();
		         return;
		         }
		         }
		         }
		         */
		        if ((edlNo1 < 0) && (edlNo2 < 0 || edlNo2.length == 0) && (edlNo3 < 0 || edlNo3.length == 0))
		        {
		            if (edlNo1 != "")
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,0);
		
		                return false;
		            }
		        }
		
		
		
		
		
		
		        if (edlNo1.length != 0 || edlNo1 != "")
		        {
		            if (edlNo1 < 0)
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,0);
		
		                return false;
		            }
		            if (edlNo1 == edlNo2 || edlNo1 == edlNo3)
		            {
		                var id = '509';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,0);
		                return false;
		
		            }
		
		        }
		
		        if ((edlNo2 < 0) && (edlNo1 < 0 || edlNo1.length == 0) && (edlNo3 < 0 || edlNo3.length == 0))
		        {
		            if (edlNo2 != "")
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,1);
		
		                return false;
		            }
		        }
		        if (edlNo2.length != 0 || edlNo2 != "")
		        {
		            if (edlNo2 < 0)
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,1);
		
		                return false;
		            }
		            if (edlNo1 == edlNo2 || edlNo2 == edlNo3)
		            {
		                var id = '509';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,1);
		                return false;
		
		            }
		
		        }
		        if ((edlNo3 < 0) && (edlNo2 < 0 || edlNo2.length == 0) && (edlNo1 < 0 || edlNo1.length == 0))
		        {
		            if (edlNo3 != "")
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,2);
		
		                return false;
		            }
		        }
		        if (edlNo3.length != 0 || edlNo3 != "")
		        {
		            if (edlNo3 < 0)
		            {
		                var id = '529';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,2);
		
		                return false;
		            }
		            if (edlNo2 == edlNo3 || edlNo1 == edlNo3)
		            {
		                var id = '509';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("newEdlNo"+div,2);
		                return false;
		
		            }
		
		        }
		
		        if (partOfSeqNo1 != 0)
		        {
		            if (partOfclaSeqNo1.length == 0 || partOfclaSeqNo1 == 0 || partOfclaSeqNo1 == "" || partOfclaSeqNo1 == "null")
		            {
		                var id = '816';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+0));
		                return false;
		            }
		
		            if (partOfSeqNo1 == partOfSeqNo2 || partOfSeqNo1 == partOfSeqNo3 || partOfSeqNo1 == partOfSeqNo4)
		            {
		
		                var id = '524';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+0));
		                return false;
		
		            }
		
		        }
		
		        if (partOfSeqNo2 != 0)
		        {
		            if (partOfclaSeqNo2.length == 0 || partOfclaSeqNo2 == 0 || partOfclaSeqNo2 == "" || partOfclaSeqNo2 == "null")
		            {
		                var id = '816';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+1));
		                return false;
		            }
		
		            if (partOfSeqNo1 == partOfSeqNo2 || partOfSeqNo2 == partOfSeqNo3 || partOfSeqNo4 == partOfSeqNo2)
		            {
		
		                var id = '524';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+1));
		                return false;
		
		            }
		
		        }
		        if (partOfSeqNo3 != 0)
		        {
		            if (partOfclaSeqNo3.length == 0 || partOfclaSeqNo3 == 0 || partOfclaSeqNo3 == "" || partOfclaSeqNo3 == "null")
		            {
		                var id = '816';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+2));
		                return false;
		            }
		
		
		            if (partOfSeqNo2 == partOfSeqNo3 || partOfSeqNo1 == partOfSeqNo3 || partOfSeqNo3 == partOfSeqNo4)
		            {
		                var id = '524';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+2));
		                return false;
		
		            }
		
		        }
		        if (partOfSeqNo4 != 0)
		        {
		            if (partOfclaSeqNo4.length == 0 || partOfclaSeqNo4 == 0 || partOfclaSeqNo4 == "" || partOfclaSeqNo4 == "null")
		            {
		                var id = '816';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+3));
		                return false;
		            }
		
		            if (partOfSeqNo1 == partOfSeqNo4 || partOfSeqNo2 == partOfSeqNo4 || partOfSeqNo3 == partOfSeqNo4)
		            {
		                var id = '524';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partOf",(i+3));
		                return false;
		
		            }
		
		        }
		
		
		
		
		        if (dwoNumber.length != 0 || dwoNumber != "")
		        {
		            if (!validateNumber(dwoNumber))
		            {
		
		                var id = '508';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("dwoNo"+div,-1);
		                return false;
		            }
		        }
		        if (dwoNumber <= 0 && dwoNumber.length != 0)
		        {
		            var id = '530';
		            hideErrors();
		            addMsgNum(id);
		            showScrollErrors("dwoNo"+div,-1);
		            return false;
		        }
		        if (partNumber.length != 0 || partNumber != "")
		        {
		            if (!validateNumber(partNumber))
		            {
		
		                var id = '512';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("partNo"+div,-1);
		                return false;
		            }
		        }
		        if (partNumber <= 0 && partNumber.length != 0)
		        {
		            var id = '531';
		            hideErrors();
		            addMsgNum(id);
		            showScrollErrors("partNo"+div,-1);
		            return false;
		        }
		        if (priceBookNumber.length != 0 || priceBookNumber != "")
		        {
		            if (!validateNumber(priceBookNumber))
		            {
		
		                var id = '513';
		                hideErrors();
		                addMsgNum(id);
		                showScrollErrors("priceBookNo"+div,-1);
		                return false;
		            }
		        }
		        if (priceBookNumber <= 0 && priceBookNumber.length != 0)
		        {
		            var id = '532';
		            hideErrors();
		            addMsgNum(id);
		            showScrollErrors("priceBookNo"+div,-1);
		            return false;
		        }
		
		        if (comments.length > 2000)
		        {
		
		            var id = '517';
		            hideErrors();
		            addMsgNum(id);
		            showScrollErrors("comments"+div,-1);
		            return false;
		        }
		    }
    }


    if (divName == "divAddClause")
    {
        document.forms['ChangeRequest1058Form'].componentSeqNo.value = "";
        for (var i = 0; i < document.forms['ChangeRequest1058Form'].component.options.length; i++) {

            compSeqNo = document.forms['ChangeRequest1058Form'].component.options[i].value;
            document.forms['ChangeRequest1058Form'].componentSeqNo.value = document.forms['ChangeRequest1058Form'].componentSeqNo.value + compSeqNo + "~";
        }


        /* Added For Attach Clause CR **********/
        if (document.forms['ChangeRequest1058Form'].hdncomponentGroupSeqNo.value != undefined) {

            var hdnCompSeqNo = document.forms['ChangeRequest1058Form'].hdncomponentGroupSeqNo.value;
            var splitCompGrpSeqNo = hdnCompSeqNo.split("~");

            for (var i = 0; i < splitCompGrpSeqNo.length - 1; i++) {

                if (document.forms['ChangeRequest1058Form'].componentGrpSeqNoinAdd.value == splitCompGrpSeqNo[i]) {

                    var id = '788';
                    hideErrors();
                    addMsgNum(id);
                    showScrollErrors("sltLeadComp",-1);;
                    return false;
                }
            }

        }

    }


    if (reason == "") {
        var id = '507';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reason"+div,-1);
        return false;
    }
    if (reason.length > 2000) {

        var id = '518';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reason"+div,-1);
        return false;
    }
    return true;
}

function fnDelClauseValidation(reviseCheck, divName) {
    var mod = document.forms['ChangeRequest1058Form'];
    if ($('#clauseToDelete').val() == "") {
        var id = '963';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("clauseToDelete",-1);
        return false;
    }
    var reason = trim(mod.reasonDel.value);

    if (reason == "") {
        var id = '507';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reasonDel",-1);
        return false;
    }
    if (reason.length > 2000) {

        var id = '518';
        hideErrors();
        addMsgNum(id);
        showScrollErrors("reasonDel",-1);
        return false;
    }
    return true;
}

//Added for CR-127 starts  here
function fnAddMdlClauseValidation(reviseCheck, divName) {
    var mod = document.forms['ChangeRequest1058Form'];
    var chngClaSeqNo = false;
    var reason = trim(mod.reasonMdl.value);
    
    if($('.claSeqNum').length == 0){ //If condition added for CR-130
     		var id = '1035';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonMdl",-1);
	        return false;
    }else{
   	$('.claSeqNum').each(function(i) {
    	if (this.checked) {	    
    			chngClaSeqNo = true;	
	    		$(".changeTypeSeqNo:eq("+i+")").prop('checked','true');
	    	}
    	});
    }	
    	
    if(!chngClaSeqNo){
    	var id = '1032';
       	hideErrors();
       	addMsgNum(id);
       	showScrollErrors("mdlClaSeqNo",-1);
       	return false;
    }
	else {
	    if (reason == "") {
	        var id = '507';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonMdl",-1);
	        return false;
	    }
	    else if (reason.length > 2000) {
	
	        var id = '518';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonMdl",-1);
	        return false;
	    }
	    else
	    	return true;
	}
}
//Added for CR-127 ends  here

//Added for CR-130 starts  here
function fnImpSubSecValidation(reviseCheck, divName) {
    var mod = document.forms['ChangeRequest1058Form'];
    var subSecSeqNo = false;
    var reason = trim(mod.reasonSubSec.value);
     if($('.secSeqNum').length == 0){
     		var id = '1036';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonSubSec",-1);
	        return false;
    }else{
   		$('.secSeqNum').each(function(i) {
    	if (this.checked) {	    
    			subSecSeqNo = true;	
    			$(".secChangeTypeSeqNo:eq("+i+")").prop('checked','true');
	    	}
    	});
    }	
    if(!subSecSeqNo){
    	var id = '1034';
       	hideErrors();
       	addMsgNum(id);
       	showScrollErrors("subSecSeqNo",-1);
       	return false;
    }else {
    	 if (reason == "") {
	        var id = '507';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonSubSec",-1);
	        return false;
	    }
	    else if (reason.length > 2000) {
	
	        var id = '518';
	        hideErrors();
	        addMsgNum(id);
	        showScrollErrors("reasonSubSec",-1);
	        return false;
	    }
	    else
	    	return true;
	}
}
//Added for CR-130 ends  here

function showORhide() {
    $('#violation').slideToggle();
}


function fnLoadClauseVersion()
{	
	if($("#clauseToModify").val()==""){
		var id = '962';
            hideErrors();
            addMsgNum(id);
            showScrollErrors("clauseToModify",-1);
    }
	else{
	var selectedSectionID=$('#sectionSeqNo').val();
	var selectedSectionName=$('#hdnSectionName').val();
	var selectedSubSectionID=$('#subSectionSeqNo').val();
	var selectedSubSectionName=$('#hdnSubSectionName').val();
	var selectedClauseSeqNo=$("#hdnClauseToModifySeqNo").val();
	var selectedModelID =$('#mdlSeqNo').val();
	
	var url="ChangeRequest1058Action.do?method=selectClauseVersions&selectedSectionName="+escape(selectedSectionName)+"&selectedSubSectionName="+escape(selectedSubSectionName)+"&selectedModelID="+selectedModelID+"&selectedSubSectionID="+selectedSubSectionID+"&selectedSectionID="+selectedSectionID+"&selectedClauseID="+selectedClauseSeqNo;
	
	window.open(url,"Clause","location=0,resizable=no,status=0,scrollbars=1,width=800,height=500"); 
	}
	
}

function parentsubmit(versionNo)
{
		var reviseCheck = $("#reviseClauseCheck").val();
		document.forms[0].action="ChangeRequest1058Action.do?method=fetchVersionClause&versionNo="+versionNo+"&reviseCheck="+reviseCheck;
		document.forms[0].submit();
}

function checkVersion(inputDataAtSubmit){
	var matched = true;
    $.each(inputDataAtLoad, function (i, input) {
	    var initialName = input.name;
	    var initialValue = input.value;
	    		    
	  if (initialName == inputDataAtSubmit[i].name) {
	    	if(initialValue != inputDataAtSubmit[i].value){
		    		matched = false;
		    		return false;
	    		}
	   }
	   else{
	   		if(inputDataAtSubmit[i].value != ""){
			    	matched = false;
			    	return false;
	    	}
	    }
	 });
	return matched;
}

//Added for Cr-127 starts here
function fnSeeTableData(){
	var seqNo = document.forms[0].seqNo1058.value;
	document.forms[0].action = "SearchRequest1058Action.do?method=createPDF&seqNo="+seqNo;
    document.forms[0].submit();
}

function fnTinymceCall(){
	if (typeof tinymce != 'undefined')
        {
        	
        	for (var i = tinymce.editors.length - 1 ; i > -1 ; i--) {
	            var ed_id = tinymce.editors[i].id;
	            tinyMCE.execCommand("mceRemoveEditor", true, ed_id);
	        }
        }
}

//Added for Cr-127 ends here