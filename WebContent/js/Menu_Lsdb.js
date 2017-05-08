//CR_106 - Modified the value number of first line menus to 8 to remove the spec supplement menu
var NoOffFirstLineMenus = 9;			// Number of first level items
var LowBgColor = '#97AEC9';			// Background color when mouse is not over
var LowSubBgColor = '#97AEC9';			// Background color when mouse is not over on subs
var HighBgColor = '#003D79';			// Background color when mouse is over
var HighSubBgColor = '#003D79';			// Background color when mouse is over on subs
var FontLowColor = '#FFFF00';			// Font color when mouse is not over
var FontSubLowColor = '#003D79';			// Font color subs when mouse is not over
var FontHighColor = '#FFFFFF';			// Font color when mouse is over
var FontSubHighColor = '#FFFFFF';			// Font color subs when mouse is over
var BorderColor = '';			// Border color
var BorderSubColor = '#63639C';			// Border color for subs
var BorderWidth = 1;				// Border width
var BorderBtwnElmnts = 1;			// Border between elements 1 or 0
var FontFamily = "verdana,arial"	// Font family menu items
var FontSize = 8;				// Font size menu items
var FontBold = 0;				// Bold menu items 1 or 0
var FontItalic = 0;				// Italic menu items 1 or 0
var MenuTextCentered = 'left';			// Item text position 'left', 'center' or 'right'
var MenuCentered = 'center';			// Menu horizontal position 'left', 'center' or 'right'
var MenuVerticalCentered = 'top';		// Menu vertical position 'top', 'middle','bottom' or static
var ChildOverlap = .01;				// horizontal overlap child/ parent
var ChildVerticalOverlap = .2;			// vertical overlap child/ parent
var StartTop = 0;				// Menu offset x coordinate
var StartLeft = 79;				// Menu offset y coordinate 202
var VerCorrect = 0;				// Multiple frames y correction
var HorCorrect = 0;				// Multiple frames x correction
var LeftPaddng = 0;				// Left padding
var TopPaddng = 0;				// Top padding
var FirstLineHorizontal = 1;			// SET TO 1 FOR HORIZONTAL MENU, 0 FOR VERTICAL
var MenuFramesVertical = 1;			// Frames in cols or rows 1 or 0
var DissapearDelay = 1000;			// delay before menu folds in
var TakeOverBgColor = 1;			// Menu frame takes over background color subitem frame
var FirstLineFrame = 'navig';			// Frame where first level appears
var SecLineFrame = 'space';			// Frame where sub levels appear
var DocTargetFrame = 'space';			// Frame where target documents appear
var TargetLoc = '';				// span id for relative positioning
var HideTop = 0;				// Hide first level when loading new document 1 or 0
var MenuWrap = 1;				// enables/ disables menu wrap 1 or 0
var RightToLeft = 0;				// enables/ disables right to left unfold 1 or 0
var UnfoldsOnClick = 0;			// Level 1 unfolds onclick/ onmouseover
var WebMasterCheck = 0;			// menu tree checking on or off 1 or 0
var ShowArrow = 1;				// Uses arrow gifs when 1
var KeepHilite = 1;				// Keep selected path highligthed
var Arrws = ['images/Menu_arrow.gif', 13, 14, '', 10, 5, 'images/trileft.gif', 5, 10];	// Arrow source, width and height

function BeforeStart() {
    return
}
function AfterBuild() {
    return
}
function BeforeFirstOpen() {
    return
}
function AfterCloseAll() {
    return
}


Menu1 = new Array("<center><font color=white>Home</center>", "LogoutAction.do?method=homePage&screenid=24", "", 0, 23, 60);



Menu2 = new Array("<center><font color=white>Spec Maintenance</center>", "", "", 4, 20, 120);
Menu2_1 = new Array("Create Spec", "CreateSpecAction.do?method=initLoad&screenid=1", "", 0, 20, 180);

Menu2_2 = new Array("Modify Spec", "ModifySpecAction.do?method=initLoad&screenid=2", "", 0, 20, 180);

Menu2_3 = new Array("Copy Spec", "CopySpecAction.do?method=initLoad&screenid=3", "", 0, 20, 180);

Menu2_4 = new Array("Delete Spec", "DeleteSpecAction.do?method=initLoad&screenid=26", "", 0, 20, 180);

//Commented to	remove spec supplement
//Added for CR-110
Menu3 = new Array("<center><font color=white>1058</center>", "", "", 3, 20, 60);
/*Added for CR 75 by cm68219*/
Menu3_1 = new Array("Create 1058 Request", "ChangeRequest1058Action.do?method=initLoad&screenid=47", "", 0, 20, 220);
Menu3_2 = new Array("Search/Modify 1058 Requests", "SearchRequest1058Action.do?method=initLoad&screenid=47", "", 0, 20, 220);
Menu3_3 = new Array("Reports", "SearchRequest1058Action.do?method=initLoad&screenid=51", "", 0, 20, 220);
//Added for CR-110 ends here
//Menu3=new Array("<center><font color=white>Spec Supplement</center>","SpecSupplement.do?method=initLoad&screenid=4","",0,20,109);
//CR_106 - Modified the sequence of Menu 

Menu4 = new Array("<center><font color=white>Spec Comparison</center>", "", "", 3, 20, 124);
/*Added for CR 75 by cm68219*/
Menu4_1 = new Array("Clause Comparison", "compareSpecAction.do?method=initLoad&screenid=5", "", 0, 20, 220);
Menu4_2 = new Array("Component Comparison/EDL Report", "compareComponentAction.do?method=initLoad&screenid=6", "", 0, 20, 220);
Menu4_3 = new Array("On Demand Spec Supplement", "OndemandSpecSupplement.do?method=initLoad&screenid=40", "", 0, 20, 220);

Menu5 = new Array("<center><font color=white>Master Maintenance</center>", "", "", 14, 20, 124);
//Added For CR_84
Menu5_1 = new Array("Specification Type Maintenance", "SpecTypeAction.do?method=fetchSpecTypeDetails&screenid=36", "", 0, 20, 200);

Menu5_2 = new Array("Model", "", "", 2, 20, 200);
Menu5_2_1 = new Array("Model Maintenance", "ModelAction.do?method=initLoad&screenid=7", "", 0, 20, 200);

Menu5_2_2 = new Array("Copy Model", "CopyModelAction.do?method=initLoad&screenid=33", "", 0, 20, 200);

Menu5_3 = new Array("Customer Maintenance ", "CustAction.do?method=initLoad&screenid=8", "", 0, 20, 200);

Menu5_4 = new Array("Distributor Maintenance ", "DistAction.do?method=fetchDistributors&screenid=9", "", 0, 20, 200);

Menu5_5 = new Array("Section Maintenance", "SectionMaintenance.do?method=initLoad&screenid=10", "", 0, 20, 200);

Menu5_6 = new Array("SubSection Maintenance", "SubSectionAction.do?method=initLoad&screenid=11", "", 0, 20, 200);

Menu5_7 = new Array("Component Group Maintenance", "CompGroupAction.do?method=initLoadCompGroup&screenid=12", "", 0, 20, 200);

Menu5_8 = new Array("Component Maintenance", "CompAction.do?method=initLoad&screenid=13", "", 0, 20, 200);

Menu5_9 = new Array("Component Mapping Maintenance", "CompMapAction.do?method=initLoad&screenid=14", "", 0, 20, 200);

/***	New SubMenu Added For LSDB_CR-38 **
 **		Added By ps57222*
 **		Added on 14-April-08 **
 **/

Menu5_10 = new Array("Engineering Data Maintenance", "EnggDataMaintenance.do?method=initLoad&screenid=28", "", 0, 20, 200);


/***	New SubMenu Added For LSDB_CR-42 **
 **		Added By vv49326*
 **		Added on 2-May-08 **
 **/

Menu5_11 = new Array("Appendix Image Maintenance", "AppendixMaintenance.do?method=initLoad&screenid=30", "", 0, 20, 200);

Menu5_12 = new Array("Clause Maintenance", "", "", 2, 20, 200);
/* Modified For CR_83 */
Menu5_12_1 = new Array("Add/Modify Clause", "modelAddClauseAction.do?method=initLoad&screenid=15", "", 0, 20, 200);

//Menu5_11_2=new Array("Modify Clause","ModelSelectClauseAction.do?method=initLoad&screenid=16","",0,20,200);
/* Added For CR_81 */
Menu5_12_2 = new Array("Assign EDL# /Manage Links", "ModelAssignEdlAction.do?method=initLoad&screenid=35", "", 0, 20, 200);

Menu5_13 = new Array("General Info Maintenance", "", "", 3, 20, 200);
Menu5_13_1 = new Array("Specification Maintenance", "ModelSpecificationAction.do?method=initLoad&screenid=17", "", 0, 20, 200);

Menu5_13_2 = new Array("General Arrangement", "GenArrByModel.do?method=initLoad&screenid=18", "", 0, 20, 200);
/*Added For CR_106*/
Menu5_13_3 = new Array("General Info Text Maintenance", "GenInfoMaintByModel.do?method=initLoad&screenid=41", "", 0, 20, 200);

Menu5_14 = new Array("Performance Curve Maintenance", "PerfCurveModelAction.do?method=initLoad&screenid=19", "", 0);



/*
 * Added Menu for LSDB_CR-45
 * Starts
 */

Menu6 = new Array("<center><font color=white>CR Form</center>", "", "", 2, 20, 65);
Menu6_1 = new Array("Create Change Request Form", "CreateRequestIDAction.do?method=initLoad&screenid=31", "", 0, 20, 200);
Menu6_2 = new Array("Modify Change Request Form", "ModifyChangeAction.do?method=initLoad&screenid=32", "", 0, 20, 200);

//Ends

//Modified for CR_101 to add two sub menus.
Menu7 = new Array("<center><font color=white>History</center>", "", "", 2, 20, 55);
Menu7_1 = new Array("Spec History", "HistoryAction.do?method=initLoad&screenid=21", "", 0, 20, 150);
Menu7_2 = new Array("LSDB Revision Changes", "RevisionChangesAction.do?method=fetchRevisions&screenid=39", "", 0, 20, 150);
// CR_101 Ends here

//Added for CR_99 for new Menu "Regenerate Spec for PDFs."
Menu8 = new Array("<center><font color=white>Administration</center>", "", "", 10, 20, 160);
Menu8_1 = new Array("Regenerate Spec for PDFs", "RegenerateSpecAction.do?method=initLoad&screenid=37", "", 0, 20, 190);
//CR_99 Ends here.

Menu8_2 = new Array(" Reset Spec Status", "ResetSpecAction.do?method=initLoad&screenid=34", "", 0, 20, 190);
Menu8_3 = new Array(" User Maintenance", "UserMaintenanceAction.do?method=fetchUsers&screenid=22", "", 0, 20, 190);
//Added for CR-126 for new Menu "Manage Email Subscriptions"
Menu8_4 = new Array("Manage Email Subscriptions", "UserMaintenanceAction.do?method=fetchUsers&screenid=52", "", 0, 20, 190);
//Added for CR-113 for new Menu "Broadcast Emails"
Menu8_5 = new Array(" Broadcast emails", "UserMaintenanceAction.do?method=fetchUsers&screenid=45", "", 0, 20, 190);
//Added for CR_124 for new Menu "Pending/Invalid Emails"
Menu8_6 =new Array("Pending/Invalid Emails","UserMaintenanceAction.do?method=fetchUsers&screenid=50","",0,20,190);
//Added for CR-113 for new Menu "Broadcast Emails" ends here
Menu8_7 = new Array("Reset Password", "ResetPasswordAction.do?method=initLoad&screenid=23", "", 0, 20, 190);
//Added for CR-117
Menu8_8 = new Array("Add 1058 Legacy Report", "SearchRequest1058Action.do?method=initLoad&screenid=48", "", 0, 20, 190);
//Added for CR-117 ends here
//Updated the Menu list and added for CR_100
Menu8_9 = new Array("Suggestions", "SuggestAction.do?method=fetchSuggestions&screenid=38", "", 0, 20, 190);
//CR_100 Changes here 
// Added for CR-128 
Menu8_10 = new Array("Master Maintenance Activity Log", "UserMaintenanceAction.do?method=fetchActivityLog&screenid=54", "", 0, 20, 190);
/**
 * The Contact Us menu is moved into header.jsp file,Reports Menu is added instead of Contact Us
 * Based on the LSDB_CR-47
 * Added on 13-June-08, By ps57222
 */

// Modified for CR_108 to add the sub menu "Order Specific Clause Report"
//Modified for CR_109 to add the sub menu "Clauses By Components Report"
Menu9 = new Array("<center><font color=white>Reports</center>", "", "", 8, 20, 68);
Menu9_1 = new Array("Master Spec By Model", "MasterSpecByMdlAction.do?method=initLoad&screenid=27", "", 0, 20, 200);
//Added for CR-114 by vb106565
Menu9_2 = new Array("Customer Option Catalog Report", "CustOptCatAction.do?method=initLoad&screenid=49", "", 0, 20, 200);
Menu9_3 = new Array("Clauses By Components Report", "ClaByCompsAction.do?method=initLoad&screenid=44", "", 0, 20, 200);
Menu9_4 = new Array("Order Specific Clause Report", "OrderSpecificClauseAction.do?method=initLoad&screenid=43", "", 0, 20, 200);
//method name has changed from fetchCompGrpReport to initLoadSpecTypesAndModels for CR_104
Menu9_5 = new Array("Component Group/Component", "CompGroupAction.do?method=initLoadSpecTypesAndModels&screenid=29", "", 0, 20, 200);
//Added new menu item for CR_108
Menu9_6 = new Array("Component in Orders Report", "CompGroupAction.do?method=initLoadCompInOrder&screenid=42", "", 0, 20, 200);
//Added for CR-113 for Clauses in orders report
Menu9_7 = new Array("Clause Version In Orders Report", "modelAddClauseAction.do?method=initLoad&screenid=46", "", 0, 20, 200);
Menu9_8 = new Array("Master Spec With all Clause Versions", "MasterMaintReportAction.do?method=initLoad&screenid=20", "", 0, 28, 200);

//Commented for CR Form (LSDB_CR-45)
//Menu9=new Array("<center><font color=white>Help</center>","LogoutAction.do?method=help&screenid=24","",0,20,50);


