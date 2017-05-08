/*
 *  Name: addMsgNum
 *  Purpose: To add specific messages to be displayed
 *  Author: Satyam Computers
 */
function addMsgNum(msgNum) {

//alert("Entering addMsgNum"+msgNum);
    /*
     PATCH:4
     */
    switch (msgNum) {


        case "1":
            addError('1 -  Model already exists', 'N');
            break;

        case "2":
            addError('2 - Please select a Specification Type', 'N');
            break;

        case  "3":
            addError('3 - Please select Domestic/Export', 'N');
            break;

        case  "4":
            addError('4 - Comments can not exceed more than 2000 characters', 'N');
            break;

        case  "7":
            addError('7 - Please select the Model to modify', 'N');
            break;

        case  "12":
            addError('12 - Please enter a valid Horse Power Rating', 'N');
            break;

        case  "13":
            addError('13 - Please select Domestic', 'N');
            break;

        case  "21":
            addError('21 - Please enter a valid Model', 'N');
            break;


        case  "17":
            addError('17 - Please select a Section to modify', 'N');
            break;

        case  "18":
            addError('18 - Please enter a valid Section', 'N');
            break;

        case  "19":
            addError('19 - Please select a Model', 'N');
            break;

        case  "20":
            addError('20 - Please select a Clause Description', 'N');
            break;



        case  "6":
            addError('6 - Data saved successfully', 'Y');
            break;

        case "16":
            addError('16 - No records are found for selected search criteria', 'N');
            break;

        case "207":
            addError('207 - Your filter criteria has been changed. Please issue a new Search', 'N');
            break;

        case "40":
            addError('40 - Please enter a Title', 'N');
            break;

        case "41":
            addError('41 - Please enter a Description', 'N');
            break;

        case "42":
            addError('42 - Please enter a Value', 'N');
            break;

        case "43":
            addError('43 - Please select the Specification Item to modify', 'N');
            break;

//Added for CR-33

        case "44":
            addError('44 - Please select the Specification Item to delete', 'N');
            break;

//Distributor Maintenance - Start

        case "101":
            addError('101 - Please select the Distributor', 'N');
            break;

        case "161":
            addError('161 - Distributor already exists', 'N');
            break;

        case "162":
            addError('162 - Please select the Distributor to modify', 'N');
            break;

        case "163":
            addError('163 - Please enter a valid Distributor', 'N');
            break;
//Distributor Maintenance - End

//Component Maintenance - Start

        case "123":
            addError('123 - Please select a Component from all required Component Groups which are indicated by a red asterik', 'N');
            break;

        case "172":
            addError('172 - Please select a Component Group/Characteristic Group/Characterization', 'N');
            break;

        case "179":
            addError('179 - Component already exists', 'N');
            break;

        case "180":
            addError('180 - Please select the Component to modify', 'N');
            break;

//added for delete component
        case "864":
            addError('864 - Please select the Component to delete', 'N');
            break;

        case  "723":
            addError('723 - Selected Component should be un mapped from all the Models to  delete', 'N');
            break;
        case "208":
            addError('208 - Please enter a valid Component', 'N');
            break;

        case "209":
            addError('209 - Please enter a valid Component Description', 'N');
            break;
        case "221":
            addError('221 - Please enter a valid Component Identifier', 'N');
            break;
//Component Maintenance - End

//Sub Section Maintenance - Start
        case  "100":
            addError('100 - Please enter a valid Sub Section', 'N');
            break;

        case  "165":
            addError(' 165 - Sub Section already exists', 'N');
            break;

        case  "205":
            addError(' 205 - Please select a Section', 'N');
            break;

        case  "170":
            addError(' 170 - Please select the Sub Section to modify', 'N');
            break;
//Sub Section  Maintenance - End

//Component Group Maintenance- Start
        case "173":
            addError('173 - Please enter a Component Group', 'N');
            break;
        case "174":
            addError('174 - Please select a Component Group Type', 'N'); //Modified for CR_81
            break;
        case "175":
            addError('175 - Component Group already exists', 'N');
            break;
        case "176":
            addError('176 - Please select a Component Group to modify', 'N');
            break;
        case "177":
            addError('177 - Please select a Valid Flag', 'N');
            break;
        case "211":
            addError('211 - Please enter a valid Component Group', 'N');
            break;
        case "212":
            addError('212 - Please select Yes for Characterization', 'N');
            break;
        case "213":
            addError('213 - Please select No for a Component Group', 'N');
            break;
        case "220":
            addError('220 - Please enter a valid Component Group Identifier', 'N');
            break;

        case "222":
            addError('222 - Please select the Spec Status to Publish', 'N');
            break;

        case "223":
            addError('223 - Please select the status other than Final', 'N');
            break;

        case "230":
            addError('230 - Spec has been Successfully published', 'Y');
            break;

        case "231":
            addError('231 - Spec SubLevel Revision has been Successfully published', 'Y');
            break;

        case "232":
            addError('232 - No default component has been selected for this component group.First go to the <br>Component Mapping Maintenance screen and create a default component/value.Next, return to <br>the Component Group Maintenance screen and select "Yes" for validation required for the selected<br> component group.', 'N');
            break;


//Component Group Maintenance- End

//Performance Curve - Start
        case "199":
            addError('199 - Please browse to upload Performance Curve Image', 'N');
            break;

        case "200":
            addError('200 - Please select a valid file type to upload', 'N');
            break;

        case "202":
            addError('202 - Please select a Performance Curve Image', 'N');
            break;

//Create Spec Starts

        case "301":
            addError('301 - Please select the Customer', 'N');
            break;

        case "303":
            addError('303 - Please enter a valid SAP Customer Code', 'N');
            break;

        case "304":
            addError('304 - Please enter a valid Order Number', 'N');
            break;

        case "305":
            addError('305 - Please enter a valid Quantity', 'N');
            break;

        case "307":
            addError('307 - Quantity is a numeric field', 'N');
            break;

        case "308":
            addError('308 - Order Number should be numeric', 'N');
            break;



        case "600":
            addError('600 - Order Number already exists', 'N');
            break;

        case "306":
            addError('306 - Please enter number only', 'N'); //This is for common
            break;


        case  "22":
            addError('22 - Please select the Model View to Modify', 'N');
            break;

        case  "23":
            addError('23 - Please enter a valid General Arrangement Notes', 'N');
            break;

        case  "24":

            addError('24 - Please select a View', 'N');
            break;

        case  "25":

            addError('25 - Please select an Image to upload', 'N');
            break;

        case  "26":

            addError('26 - Section already Exists', 'N');
            break;
        case  "27":

            addError('27 - Please select a valid Image to upload. Image Type should be Jpeg/Tiff/Bmp/Gif/Pdf', 'N');
            break;

        case "28":
            addError('28 - Please enter Component Description', 'N');
            break;


        case "29":
            addError('29 - Please select the Component Description', 'N');
            break;

        case "30":
            addError('30 - Please enter a Valid Component Description', 'N');
            break;

        case "31":
            addError('31 -Please upload an Image', 'N');
            break;

        case "32":
            addError('32 - Notes should not exceed 4000 characters', 'N');
            break;

//Ends

//Select Clause Revision - Start
        case "128":
            addError('128 - Please enter the Reason', 'N');
            break;
        case "129":
            addError('129 - Please enter a valid Reason', 'N');
            break;

        case "219":
            addError('219 - Please select Clause Description to modify', 'N');
            break;
        case "227":
            addError('227 - Please select a Clause  ', 'N');
            break;

//Select Clause Revision - End

        case  "156":

            addError('156 - Please enter a Valid Customer', 'N');
            break;

        case "157":

            addError('157 - Customer already exists', 'N');
            break;

        case  "158":

            addError('158 -  Please select the Customer to modify', 'N');
            break;

//Component Mapping Maintenence - Start

        case "205":
            addError('205 -Please select a Section', 'N');
            break;

        case "182":
            addError('182 - Please select a Sub Section', 'N');
            break;

        case "183":
            addError('183 - Please select a Component Group', 'N');
            break;

        case "184":
            addError('184 - Please select a Component to apply to Model', 'N');
            break;

        case "185":
            addError('185 - Please select a default Component', 'N');
            break;

        case "186":
            addError('186 - Default Component should be one of the Components applied to Model', 'N');
            break;

        case "226":
            addError('226 - Selected Component group already mapped to the Model', 'N');
            break;
//Component Mapping Maintenence - End

//Modify Spec - Starts

        case "106":
            addError('106 - Please enter the Quantity', 'N');
            break;

        case "113":
            addError('113 - Please select an Order to modify', 'N');
            break;

        case "214":
            addError('214 - Quantity should be numeric', 'N');
            break;


//Modify Spec - Ends

//Order Specification --Start
        case "215":
            addError('215 - Please enter a valid Description', 'N');
            break;
        case "216":
            addError('216 - Please enter a valid Title', 'N');
            break;
        case "217":
            addError('217 - Item Description already exists', 'N');
            break;
        case "218":
            addError('218 - Title already exists', 'N');
            break;
//Order Specification --End
//Copy Spec - Starts

        case "102":
            addError('102 - Please select a Customer', 'N');
            break;

        case "104":
            addError('104 - Please enter the SAP Customer Code', 'N');
            break;

        case "105":
            addError('105 - Please enter a valid Order Number', 'N');
            break;

        case "111":
            addError('111 - Please select an Order to copy', 'N');
            break;

        case "112":
            addError('112 - Spec copied sucessfully', 'Y');
            break;
//Copy Spec - Ends
//AddClause -Starts

        case "504":
            addError('504 - Selected Clause is already mapped', 'N');
            break;

        case  "505":
            addError('505 - Please enter atleast one Engineering Data value', 'N');
            break;

        case  "506":
            addError('506 - Please enter Clause Description', 'N');
            break;

        case  "507":
            addError('507 - Please enter the Reason', 'N');
            break;

        case  "508":
            addError('508 - DWO Number is numeric field', 'N');
            break;

        case  "509":
            addError('509 - EDL Number(s) values are to be unique', 'N');
            break;

        case  "510":
            addError('510 - Reference EDL Number(s) values are to be unique', 'N');
            break;

        case  "511":
            addError('511 - No SubSection available for the Section', 'N');
            break;

        case  "512":
            addError('512 - Part Number is a numeric field', 'N');
            break;

        case  "513":
            addError('513 - Price Book Number is a numeric field', 'N');
            break;

        case  "514":
            addError('514 - New EDL Number(s) is a numeric field', 'N');
            break;

        case  "515":
            addError('515 - Reference EDL Number(s) is a numeric field', 'N');
            break;

        case  "516":
            addError('516 - Clause Description should not exceed 4000 characters', 'N');
            break;

        case  "517":
            addError('517 - Comments should not exceed 2000 characters', 'N');
            break;

        case  "518":
            addError('518 - Reason should not exceed 2000 characters', 'N');
            break;


        case  "519":
            addError('519 - No Section Available for the Model selected', 'N');
            break;

        case  "520":
            addError('520 - Please select a Clause', 'N');
            break;

        case  "521":
            addError('521 - No Clauses available for this SubSection', 'N');
            break;

        case  "522":
//Updated For CR_81 by RR68151
            addError('522 -  Please select a Component/Characteristic/Characterization from the list', 'N');
            break;

        case  "523":
//Updated For CR_81 by RR68151
            addError('523 - Please Select a Component/Characteristic/Characterization', 'N');
            break;

        case  "524":
            addError('524 - PartOf field values are to be unique', 'N');
            break;

        case  "525":
            addError('525 - Please enter a valid Comments', 'N');
            break;

        case  "526":
            addError('526 - Please Enter a valid Table Data', 'N');
            break;

        case  "527":
//Updated For CR_81 by RR68151
            addError('527 -  Please issue a search for Component/Characteristic/Characterization', 'N');
            break;

        case  "528":
            addError('528 -  Please Enter a Valid Reference EDL Number(s)', 'N');
            break;

        case  "529":
            addError('529 -  Please Enter a Valid New EDL Number(s)', 'N');
            break;

        case  "530":
            addError('530 -  Please Enter a Valid DWO Number', 'N');
            break;

        case  "531":
            addError('531 -  Please Enter a Valid Part Number', 'N');
            break;

        case  "532":
            addError('532 -  Please Enter a Valid Price Book Number', 'N');
            break;
//AddClause -Ends

//Spec comparison

        case  "401":
            addError('401 - Please select minimum of two Orders', 'N');
            break;

        case  "402":
            addError('402 - Please select maximum of three Orders', 'N');
            break;

            /*
             *   Added For LSDB_CR-06
             *   Added on 15-April-08
             */

        case  "403":
            addError('403 - Please select maximum of two Orders', 'N');
            break;
        case  "404":
            addError('404 - Please select same sections', 'N');
            break;

        case  "407":
            addError('407 - There is no difference in component for the orders selected', 'N');
            break;
        case  "408":
            addError('408 - Please select same Model', 'N');
            break;

        case  "405":
            addError('405 - Please select a unique Section', 'N');
            break;

        case  "406":
            addError('406 - Please select a Section Name', 'N');
            break;

//Spec History
        case "224":
            addError('224 - Please select Spec Status ', 'N');
            break;

//Change Password
        case "148":
            addError('148 - Please enter a valid New Password ', 'N');
            break;
        case "149":
            addError('149 - Please enter a valid Confirm Password ', 'N');
            break;
        case "150":
            addError('150 - Password mismatch. Please enter the correct Password ', 'N');
            break;
        case "151":
            addError('151 - Password Saved Successfully ', 'Y');
            break;
        case "225":
            addError('225 - Password should be eight to twelve characters', 'N');
            break;


//UserMaintenance
        case "139":
            addError('139 - User already exists ', 'N');
            break;



        case "152":
            addError('152 - Please select a User ID ', 'N');
            break;

        case "138":
            addError('138 - Please enter a valid User ID ', 'N');
            break;
        case "143":
            addError('143 - Please enter a valid Email Address ', 'N');
            break;

        case "140":
            addError('140 - Please enter a valid First Name ', 'N');
            break;
        case "141":
            addError('141 - Please enter a valid Last Name ', 'N');
            break;
        case "144":
            addError('144 - Please enter a valid Location ', 'N');
            break;
        case "145":
            addError('145 - Please enter a valid Contact Phone Number ', 'N');
            break;
        case "142":
            addError('142 - Please enter the Password ', 'N');
            break;
        case "146":
            addError('146 - Please select a User Role ', 'N');
            break;
//New Indicator
        case "533":
            addError('533 - The clause selected is child Clause. Please select the parent for this child Clause ', 'N');
            break;

        case "786":
            addError('786 - Component tied to this Clause is not selected in the Order. Please select the desired component and click "save component" first', 'N');
            break;

        case "788":
            addError('788 - Component is already tied to Component Group. Please select a new Component', 'N');
            break;
//Changed for CR-74 VV49326 10-06-09
        case "787":
            addError('787 - Multiple Clauses cannot be tied to the same Lead Component', 'N');
            break;


        case "789":
            addError('789 - Please select a Lead Component', 'N');
            break;

//Delete Spec
        case "790":
            addError('790 - Please select an Order to delete', 'N');
            break;

        case "791":
            addError('791 - Spec deleted Successfully', 'Y');
            break;

//For CR-33
        case "792":
            addError('792 - Data deleted Successfully', 'Y');
            break;


        case "793":
            addError('793 - Please select an Order to view Report', 'N');
            break;



            /*
             *  Added For LSDB_CR-38
             *  Added on 14-April-08
             */

        case "800":
            addError('800 - Engineering Data already Exists', 'N');
            break;


        case "801":
            addError('801 - Please enter valid Engineering Data', 'N');
            break;

        case "802":
            addError('802 - Please select Engineering Data', 'N');
            break;

        case "803":
            addError('803 - Engineering Data tied to Clauses cannot be deleted', 'N');
            break;




//Delete Section CR
        case "804":
            addError('804 - Please select a Section to delete', 'N');
            break;

        case "805":
            addError('805 - Selected Section cannot be deleted as it may be tied to Sub Section(s) or it may exists in working Order(s)', 'N');
            break;

        case "806":
            addError('806 - Section deleted Successfully', 'Y');
            break;

        case "721":
            addError('721 - Please select a Component Group to delete', 'N');
            break;

        case "722":
            addError('722 - Component Group cannot be deleted as it is not unmapped from its subsection and model.', 'N');
            break;

//Delete Sub Section CR
        case "794":
            addError('790 - Please select a Sub Section to delete', 'N');
            break;

        case "795":
            addError('795 - Selected Sub Section cannot be deleted as it may be tied to Component Group/Clause(s)/exists as Part Of for other Clauses/exists in working Order(s)', 'N');
            break;

        case "796":
            addError('796 - Sub Section deleted Successfully', 'Y');
            break;

        case "797":
            addError('797 - Supplemental Information Section cannot be deleted for Locomotive Model', 'N');
            break;


//Added for CR-42 Model Appendix Maintenance

        case "798":
            addError('798 - Please enter valid Appendix Image name', 'N');
            break;


        case "799":
            addError('799 - Image Description should not exceed 2000 characters', 'N');
            break;

        case "780":
            addError('780 - Please browse to upload Appendix Image', 'N');
            break;

        case "781":
            addError('781 - Please select an Appendix Image', 'N');
            break;

        case "807":
            addError('807 - Image Name already exists', 'N');
            break;

        case "808":
            addError('808 - Clause already mapped with Appendix Image', 'N');
            break;

        case "809":
            addError('809 - You must first associate the images in the Appendix section with a clause before the Appendix can be turned on', 'N');
            break;

        case "810":
            addError('810 - Please map clauses to Appendix image', 'N');
            break;

        case "811":
            addError('811 - The Image you tried to upload exceeds the dimensions. Please select an Image with appropriate dimensions', 'N');
            break;

        case "812":
            addError('812 - The respective clause was deleted at Model. Hence a Sub Clause cannot be added and cannot be revised.', 'N');
            break;

        case "813":
            addError('813 - The selected version is Default. Please apply another version of the clause as Default and delete the clause', 'N');
            break;

        case "814":
            addError('814 - The selected clause cannot be updated as it is already used in the Order.', 'N');
            break;

        case "815":
            addError('815 - The Clause cannot be revised,since it was deleted in Model.', 'N');
            break;

        case "816":
            addError('816 - Part Of should be mapped to Clause Number, but not to Sub Section.', 'N');
            break;

            /*
             *	Added for LSDB_CR-46
             *	on 28-Aug-08 by ps57222
             */

        case "817":
            addError('817 - Sections M,S and Z cannot be deleted for PM&I Model', 'N');
            break;

        case "818":
            addError('818 - A new Section cannot be added beyond Section M for PM&I Model', 'N');
            break;

        case "819":
            addError('819 - Distributor cannot be deleted as it is tied to an Order', 'N');
            break;


            /*
             * Added for LSDB_CR-45, Change Request Form
             */

        case "820":
            addError('820 - Selection criteria for  Model/Section/Sub section has been changed', 'N');
            break;

        case "821":
            addError('821 - The status cannot be updated', 'N');
            break;

        case  "822":
            addError('822 - Please enter Component/Value', 'N');
            break;

        case  "823":
            addError('823 - Please select a Component', 'N');
            break;

        case  "824":
//Modified for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
            addError('824 - Please choose Not Required/New option in Component Change Request Form', 'N');
            break;

        case "825":
            addError('825 - Please enter a Short Description', 'N');
            break;

        case "826":
            addError('826 - Reason can not exceed more than 4000 characters', 'N');
            break;

        case  "827":
            addError('827 - Short Description can not exceed more than 2000 characters', 'N');
            break;

            /**For Modify Request ID screen**/
        case  "830":
            addError('830 - Please select atleast one search criteria', 'N');
            break;

        case  "831":
            addError('831 - Please select a valid date period', 'N');
            break;

        case  "832":
            addError('832 - Please enter a valid Last Name', 'N');
            break;

        case  "833":
            addError('833 - Please enter a valid Request ID', 'N');
            break;

        case  "834":
            addError('834 - Please select a Request ID', 'N');
            break;

        case "850":
            addError('850 - Please select Available Component Group(s)', 'N');
            break;

        case  "851":
            addError('851 - Please select Available Component(s)', 'N');
            break;

        case  "852":
            addError('852 - please select Change From : Select Clause', 'N');
            break;


        case  "853":
            addError('853 - Please enter New Clause Number', 'N');
            break;

        case  "854":
            addError('854 - Please enter New Clause Description', 'N');
            break;

        case  "855":
            addError('855 - New Clause Description should not exceed 4000 characters', 'N');
            break;

        case  "856":
            addError('856 - Please select From Date and To Date', 'N');
            break;

        case  "857":
            addError('857 - Please select Change To : Clause Version', 'N');
            break;

        case  "858":
            addError('858 - Please enter Administrator Comments', 'N');
            break;

        case  "859":
            addError('859 - Administrator Comments can not exceed more than 4000 characters', 'N');
            break;

//Added for CR-58 Component Comparison/Report by VV49326 18-Nov-08
        case  "860":
            addError('860 - Selection criteria cannot be compared by All Sections with Single Section', 'N');
            break;

        case  "861":
            addError('861 - Please select an Order', 'N');
            break;


        case  "862":
            addError('862 - Request should be in Approved state to complete the form', 'N');
            break;

        case  "863":
            addError('863 - Please enter PDF Image Name', 'N');
            break;

        case "701":
            addError('701 - The selected Component Group is not mapped to the selected Model', 'N');
            break;

        case "865":
            addError('865 - Please select the Model to copy', 'N');
            break;
        case "750":
            addError('750 - New Component and Clause details cannot be added to Model level', 'N');
            break;


        case "760":
            addError('760 - Please enter the new Compnent Name', 'N');
            break;

        case "761":
            addError('761 - Please select Lead Component Group', 'N');
            break;


//Added for CR-74 VV49326 09-06-09
//Edited for CR-110 for parent modify & delete  clause 
        case "762":
            addError('762 - The Clause Description with a message RESERVED cannot be selected', 'N');
            break;

//Added for CR-74 16-06-09
        case "763":
            addError('763 - The Clause Description with a message RESERVED cannot be mapped to an Appendix Image', 'N');
            break;

//added for LSDB_CR-76
        case "764":
            addError('764 - Please Select a Order Number', 'N');
            break;

        case "765":
            addError('765 - Please Select Prior Spec Status', 'N');
            break;

//Added for CR_80 CR Form Enhancements III on 17-Nov-09 by RR68151
        case  "866":
            addError('866 - Please choose Not Required option in Component Change Request Form', 'N');
            break;

        case  "867":
            addError('867 - Please choose Delete option to show effected items', 'N');
            break;

        case  "868":
            addError('868 - Please choose a different clause version in Change To/Create Section to set as Default', 'N');
            break;

        case  "869":
            addError('869 - Please select a new clause version in Change To/Create section', 'N');
            break;

        case  "870":
            addError('870 - You are requesting to delete single version Clause. Please use delete Clause radio button for this Procedure', 'N');
            break;

        case  "871":
            addError('871 - Please select a clause version to delete in Change From section', 'N');
            break;

        case  "872":
            addError('872 - Please select one Change Request form that needs to be re&ndash;assigned', 'N');
            break;

        case  "873":
            addError('873 - Please select a user to whom the Change Request form needs to be re&ndash;assigned', 'N');
            break;

        case  "874":
            addError('874 - The selected request ID with Submitted/Approved/Rejected/Completed/Not Valid cannot be re&ndash;assigned', 'N');
            break;

        case  "875":
            addError('875 - The selected Request ID with Completed/Not Valid status cannot be re&ndash;assigned', 'N');
            break;

        case  "876":
            addError('876 - The selected Request ID cannot be re&ndash;assigned to the same user', 'N');
            break;

        case  "877":
            addError('877 - The selected Component Group/Component does not contain any clause to delete', 'N');
            break;

        case  "878":
            addError('878 - Please choose an option other than Delete in Component Group/Component Change Request Form to save the Clause details', 'N');
            break;

//Added For CR_81 Locomotive and Power Products Enhancements by RR68151
        case  "879":
            addError('879 - EDL/Reference EDL Number already exists', 'N');
            break;

        case  "880":
            addError('880 - Characteristic Group Combination already exists', 'N');
            break;

        case  "881":
            addError('881 - Please select a Characteristic Component Group', 'N');
            break;

        case  "882":
            addError('882 - Please select a Characteristic Component', 'N');
            break;

        case  "883":
            addError('883 - Please enter a valid EDL/Reference EDL Number', 'N');
            break;

        case  "884":
            addError('884 - Please select a Characteristic Group Combination to Save/Modify', 'N');
            break;

        case  "885":
            addError('885 - Please select a Characteristic Group Combination to Delete', 'N');
            break;

        case  "886":
            addError('886 - Available Characteristic combination EDL number list does not exist', 'N');
            break;

//Added For CR_83 To Merge Add and Modify Clause Screens by RR68151
        case  "887":
            addError('887 - Please Select a Clause to Modify', 'N');
            break;

        case  "888":
            addError('888 - No Clauses found for the selected criteria', 'N');
            break;

        case "889":
            addError('889 - Please select a Clause Version', 'N');
            break;

        case "890":
            addError('890 - Specification Type already exists', 'N');
            break;

        case "891":
            addError('891 - Please enter a valid Specification Type', 'N');
            break;

        case "892":
            addError('892 - Please select the Specification Type to Modify', 'N');
            break;

//CR_85
        case "893":
            addError('893 - Selected clause link already exists', 'N');
            break;

        case "894":
            addError('894 - Please select a Characteristic Group Combination', 'N');
            break;

        case  "895":
            addError('895 - Characteristic combination EDL does not exist', 'N');
            break;

        case "896":
            addError('896 - Please Add a Characteristic Combination Tied To Clause', 'N');
            break;

        case "897":
            addError('897 - Please select a Valid Characteristic Combination Tied To Clause', 'N');
            break;
//CR 88

        case "898":
            addError('898 - Core clauses can not be tied to more than one characteristic group clause', 'N');
            break;

//Added For CR_88 on 08july10 by Sd41630
        case "899":
            addError('899 - Please rearrange at least a single Clause to Save', 'N');
            break;

        case "900":
            addError('900 - Core Clauses linked to Characteristic Component Combination(s) can not be tied to Lead or Optional Components', 'N');
            break;

//Added during testing for CR_91
        case "901":
            addError('901 - Please select a valid Image to upload. Image Type should be Jpeg/Tiff/Bmp/Gif', 'N');
            break;
//Added for CR_92
        case "902":
            addError('902 - Please select vaild  Appendix Image', 'N');
            break;

//Added for CR_92
        case "903":
            addError('903 - The Clause cannot be retrieved, since it was deleted in Model.', 'N');
            break;

        /*case "904":
            addError('904 - There seems to be an error with the characters entered in the Clause Description. Please reduce the length and try Re-inserting.', 'N');
            break;*/
//Added for CR_93
        case "905":
            addError('905 - Please rearrange at least a single Component Description to Save ', 'N');
            break;
//Added for CR_97
        case "906":
            addError('906 - Specs are currently being created for this order. Please wait until Spec generation is complete.', 'N');
            break;
//Added for CR_100
        case "907":
            addError('907 - Please enter a valid Screen Name', 'N');
            break;

        case "908":
            addError('908 - Please enter a valid Suggestion', 'N');
            break;

        case "909":
            addError('909 - Suggestion can not exceed more than 4000 characters', 'N');
            break;

        case "910":
            addError('910 - Screen Name can not exceed more than 100 characters', 'N');
            break;

        case "911":
            addError('911 - Attachment size can not exceed more than 1mb', 'N');
            break;

        case "912":
            addError('912 - Admin Comments can not exceed more than 2000 characters', 'N');
            break;
//Added For CR_101
        case  "913":
            addError('913 - View Name already exists', 'N');
            break;

        case  "916":
            addError('916 - Please enter a valid View Name', 'N');
            break;

        case  "917":
            addError('917 - General Arrangement Views can not exceed more than 7', 'N');
            break;

        case  "918":
            addError('918 - Please browse to upload General Arrangement View', 'N');
            break;

        case  "919":
            addError('919 - General Arrangement notes cannot exceed 4000 characters', 'N');
            break;
//Added For CR_104
        case  "920":
            addError('920 - Please enter valid general information text', 'N');
            break;

        case  "921":
            addError('921 - General information text cannot exceed 4000 characters', 'N');
            break;

        case "922":
            addError('922 - Please enter valid mail body', 'N');
            break;

        case "923":
            addError('923 - Please enter a valid Custom Model Name', 'N');
            break;

        case "924":
            addError('924 - Mail body can not exceed 4000 characters', 'N');
            break;

        case  "925":
            addError('925 - Please select maximum of ten Orders', 'N');
            break;

        case  "926":
            addError('926 - Please enter valid general information text that includes &ltCUSTOMERNAME&gt', 'N');//Edited for CR-131
            break;

        case  "927":
            addError('927 - Please enter valid general information text', 'N');
            break;

//Added for CR_106 - On demand Spec Supplement
        case "930":
            addError('930 - Please select maximum of two spec versions', 'N');
            break;
//Added for CR_106 fix
        case "931":
            addError('931 - Please browse to upload Customer Image', 'N');
            break;
        case "932":
            addError('932 - Please browse to upload Distributor Image', 'N');
            break;
//Added for CR_106 - QA Fix
        case "933":
            addError('933 - Please select two spec versions', 'N');
            break;
//Added for CR-112
        case "934":
            addError('934 - Password reset Successfully. The new password has been sent through email.', 'Y');
            break;
//Added for CR-112 for QA Fix
        case "935":
            addError('935 - Selected UserID is disabled.', 'Y');
            break;
//Added for CR-113
        case "936":
            addError('936 - Please enter a valid Subject.', 'N');
            break;
        case "937":
            addError('937 - Please enter a valid Body.', 'N');
            break;
        case "938":
            addError('938 - Please select the user to send a mail.', 'N');
            break;
        case "939":
            addError('939 - Body of the email cannot exceed 4000 characters', 'N');
            break;
//Added for CR-113 Ends Here
//Added for CR-110
        case "940":
            addError('940 - Please select a System Engineer ', 'N');
            break;
        case "941":
            addError('941 - Please select a Operations Representative ', 'N');
            break;
        case "942":
            addError('942 - Please select a Finance Representative ', 'N');
            break;
        case "943":
            addError('943 - Please select a Program Manager ', 'N');
            break;
        case "944":
            addError('944 - Please select a Proposal Manager ', 'N');
            break;
        case "945":
            addError('945 - Please select a 1058#ID ', 'N');
            break;
        case "946":
            addError('946 - Engineering Data can not exceed more than 4000 characters ', 'N');
            break;
        case "947":
            addError('947 - Reason can not exceed more than 2000 characters', 'N');
            break;
        case "948":
            addError('948 - Specification Section can not exceed more then 100 characters', 'N');
            break;
        case "949":
            addError('949 - Please Select a Specification Change', 'N');
            break;
        case "950":
            addError('950 - Please enter atleast one valid description ', 'N');
            break;
        case "951":
            addError('951 - Requested Specification Change cannot be deleted since already updated to the spec', 'N');
            break;
        case "952":
            addError('952 - Requested Specification Change cannot be updated since already updated to the spec', 'N');
            break;
        case "953":
            addError('953 - Please select Request From', 'N');
            break;

        case "954":
            addError('955 - Please select Request Type', 'N');
            break;

        case "955":
            addError('955 - Please enter General Description', 'N');
            break;

        case "956":
            addError('956 - Please enter Unit Numbers', 'N');
            break;

        case "957":
            addError('957 - Please enter Road Numbers', 'N');
            break;

        case "959":
            addError('959 - Please enter a numeric value in Road Number', 'N');
            break;

        case "960":
            addError('960 - Please Enter MCR Number', 'N');
            break;

        case "961":
            addError('961 - Please enter a numeric value in MCR Number', 'N');
            break;

        case "958":
            addError('958 - Please enter a numeric value in Unit Number', 'N');
            break;

        case "949":
            addError('949 - Please select a Specification Change entry', 'N');
            break;

        case "962":
            addError('962 - Please select a clause to modify', 'N');
            break;

        case "963":
            addError('963 - Please select a clause to delete', 'N');
            break;

        case "964":
            addError('964 - Please select a Section to get the Parent Clauses', 'N');
            break;

        case "965":
            addError('965 - Please select a Sub Section to get the Parent Clauses', 'N');
            break;

        case "966":
            addError('966-Please enter System Engineer Comments', 'N');
            break;

        case "967":
            addError('967-Please enter Part No Added', 'N');
            break;

        case "968":
            addError('968-Please enter Est.Design Hours', 'N');
            break;

        case "969":
            addError('969-Please enter  Part No Deleted', 'N');
            break;

        case "970":
            addError('970-Please enter Est.Drafting Hours', 'N');
            break;
        case "971":
            addError('971-Please select Change Affects WEIGHT', 'N');
            break;
        case "972":
            addError('972-Please select Change Affects CLEARANCE', 'N');
            break;
        case "973":
            addError('973-Please enter Work Order(USD)', 'N');
            break;
        case "974":
            addError('974-Please enter Operations Comments', 'N');
            break;
        case "975":
            addError('975-Please enter Disposition of Surplus Material', 'N');
            break;
        case "976":
            addError('976-Please enter Est. Labour Impact Hours', 'N');
            break;
        case "977":
            addError('977-Please enter Recommended Effective Delivery', 'N');
            break;
        case "978":
            addError('978-Please enter Tooling Cost', 'N');
            break;
        case "979":
            addError('979-Please enter est.Scrap Cost', 'N');
            break;
        case "980":
            addError('980-Please enter Rework Expense Cost', 'N');
            break;
        case "981":
            addError('981-Please enter Finance Comments', 'N');
            break;
        case "982":
            addError('982-Please enter Est. Product Cost Change', 'N');
            break;
        case "983":
            addError('983-Please enter Program Manager Comments', 'N');
            break;
        case "984":
            addError('984-Please enter Proposal Manager Comments', 'N');
            break;
        case "985":
            addError('985-Please enter Sell Price submitted to Customer', 'N');
            break;
        case "986":
            addError('986-Please enter Mark Up', 'N');
            break;
        case "987":
            addError('987-Please select Customer Approval', 'N');
            break;
        case "988":
            addError('988-Please select Customer Decision', 'N');
            break;
        case "989":
            addError('989-Please select the Customer Decision Date', 'N');
            break;
        case "990":
            addError('990-Please enter Actual Sell price', 'N');
            break;
        case "991":
            addError('991-You are not allowed to Approve/Reject the Section ', 'N');
            break;
        case "992":
            addError('992 - Please enter a numeric value in Est.Design Hrs', 'N');
            break;
        case "993":
            addError('993 - Please enter a numeric value in Est.Drafting Hrs', 'N');
            break;
        case "994":
            addError('994 - Please enter a numeric value in Work Order(USD)', 'N');
            break;

        case "995":
            addError('995 - Requested Specification Change already performed on the Spec', 'N');
            break;

        case "996":
            addError('996 - Spec has already been published', 'N');
            break;

        case "997":
            addError('997 - Order Number can not exceed more than 100 characters', 'N');
            break;

        case "998":
            addError('998 - This file extension is not allowed to upload,Please choose another file', 'N');
            break;

        case "999":
            addError('999 - Please upload an attachment less than 5MB', 'N');
            break;

        case "1000":
            addError('1000 - Please upload a attachment', 'N');
            break;

        case "1001":
            addError('1001-Please enter a numeric value in Actual Sell price', 'N');
            break;

        case "1002":
            addError('1002-Please enter a numeric value in Sell Price submitted to Customer', 'N');
            break;

        case "1003":
            addError('1003-Please enter a numeric value in Mark Up', 'N');
            break;

        case "1004":
            addError('1004-Please enter a numeric value in Tooling Cost', 'N');
            break;

        case "1005":
            addError('1005-Please enter a numeric value in est.Scrap Cost', 'N');
            break;

        case "1006":
            addError('1006-Please enter a numeric value in Rework Expense Cost', 'N');
            break;

        case "1007":
            addError('1007 - Please select MCR Number', 'N');
            break;
            
        case "1008":
            addError('1008-Please enter a numeric value in  Est. Labour Impact Hours', 'N');
            break;    
            
        case "1009":
            addError('1009-Please select a New Component', 'N');
            break;
            
        //Added For CR-117    
        case "1010":
            addError('1010 - Please Enter a 1058 Number', 'N');
            break;     
            
        case "1011":
            addError('1011 - Please enter a numeric value in 1058 Number', 'N');
            break;    
            
        case "1012":
            addError('1012 - Please Enter a Customer', 'N');
            break;
                
        case "1013":
            addError('1013 - Please Enter a Model', 'N');
            break;    
            
        case "1014":
            addError('1014 - Please Enter a Specification Revision', 'N');
            break;
            
        case "1015":
            addError('1015 - Please Enter a Order Number', 'N');
            break;       
            
	    case "1016":
            addError('1016 - Please Select a Status', 'N');
            break;
            
        case "1017":
            addError('1017 - 1058 Requests are to be unique', 'N');
            break;
            
       	case "1019":
            addError('1019 - Please upload a PDF file', 'N');
            break;
        case "1020":
            addError('1020 - File name already Exists', 'N');
            break;        
        case "1021":
            addError('1021 - Please save the modified Spec Revision before completing the request', 'N');
            break;
        //Added for CR-118    
        case  "1022":
            addError('1022 - Please select the Model to Hide/Unhide', 'N');
            break;
        case  "1023":
            addError('1023 - Please select a valid Select State', 'N');
            break;    
        case "1024":
            addError('1024 - Please unselect the legacy 1058 request(s) to create PDF', 'N');
            break;      
       //Added for CR-121
       case "1025":
            addError('1025 - Please rearrange at least a single Performance Curve to Save ', 'N');
            break;
       case "1026":
            addError('1026 - User cannot be deleted as he/she is tied to 1058 request(s)', 'N');
            break;
       case "1027":
            addError('1027 - Specs cannot be resetted as one or more revisions is tied to 1058 request(s)', 'N');
            break; 
       //Added for CR_124 Starts
       case "1028" :
       		addError('1028 -  Please select an Email');
       		break;
       case "1029":
            addError('1029 - Please upload an attachment less than 10MB', 'N');
            break;
       //Added for CR_124 ends
       
       //Added for CR-126
       case "1030":
            addError('1030 - Please enter valid hours for email reminders', 'N');
            break;
       case "1031":
            addError('1031 - Please enter a numeric value in Work Order', 'N');
            break;   
            
       //Added for CR-127
       case "1032":
            addError('1032 - Please select clause to import from Model', 'N');
            break;  
            
       //Added for CR-130     
       case "1033":
            addError('1033 - Spec cannot be deleted as it is tied to 1058 request(s)', 'N');
            break; 
       case "1034":
            addError('1034 - Please select Subsection to import from Model', 'N');
            break;
       case "1035":
            addError('1035 - Clauses are already imported to 1058 request', 'N');
            break;     
       case "1036":
            addError('1036 - Subsections are already imported to 1058 request', 'N');
            break;     
       case "1037":
            addError('1037 - Subsections can not be revised', 'N');
            break;
	   case "1038":
            addError('1038 - Please select a Lead Order', 'N');
			break;
		//Added for CR_131
       case "1039":
           addError('1039 - Please select a valid Image to upload. Image Type should be Jpeg/Tiff/Bmp/Gif/Png', 'N');
           break;	     
       //Added for CR-134
       case "1040":
           addError('1040 - Please select a valid From Date', 'N');
           break;
       case "1041":
           addError('1041 - Please select a valid To Date', 'N');
           break;
       //Added for CR-135
       case "1042":
           addError('1041 - Please select only one order', 'N');
           break;
      }
}

/*
 *  Name: addMessage
 *  Purpose: To add specific messages to be displayed
 *  Author: Satyam Computers
 */
function addMessage(errMsg) {

    addError(errMsg, 'N');
}

/*
 *  Name: addValueMessage
 *  Purpose: To add specific messages to be displayed
 *  Author: Satyam Computers
 */
function addValueMessage(errVal, msgNum) {

    var errTemp;
    var errMsg;

    switch (msgNum) {

        case "Mess2":
            errMsg = ' has to be unique';
            break;

        case "Mess3":
            errMsg = ' cannot be deleted a users are associated with this Organization';
            break;

        case "Mess4":
            errMsg = ' Name cannot be blank';
            break;

        case "Mess6":
            errMsg = ' has to be Added. Please click on Add to save the Organization';
            break;

        case "Mess8":
            errMsg = ' is a predefined Organization and hence cannot be deleted';
            break;

        case  "Message51":
            errMsg = ' already exists';
            break;

        case  "Message126":
            errMsg = ' is already active';
            break;

        case  "Message165":
            errMsg = ' cannot be his own escalation';
            break;
    }



    errTemp = errVal + errMsg;

    addError(errTemp);
}

