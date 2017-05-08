<!DOCTYPE HTML>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/favicon.ico" />
	<link href="css/bootstrap.css" TYPE="text/css" rel="stylesheet"/>
    <link href="css/select2.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/select2-bootstrap.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/datatables.min.css" TYPE="text/css" rel="stylesheet"/>
	<link href="css/EMDCustom.css" TYPE="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="js/Others/jquerylatest.js"></script>
	<script type="text/javascript" src="js/Others/jquerymigrate.js"></script>
    <script type="text/javascript" src="js/Others/ParseDate.js"></script>
	<script type="text/javascript" src="js/Others/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.min.js"></script>
	<script type="text/javascript" src="js/Others/datatables.plugins.js"></script>
    <script type="text/javascript" src="js/Others/select2.js"></script>
	<SCRIPT type="text/javascript" SRC="js/Emd_Lsdb.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error.js"></SCRIPT>
	<SCRIPT type="text/javascript" SRC="js/error_messages.js"></SCRIPT>
	<script type="text/javascript" src="js/Others/jquery.bootbox.min.js"></script>
	
	<TITLE>Electro Motive Diesel - Locomotive Spec Database</TITLE>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation">	
		<div class="container-fluid">		
		<!-- Header Page Information --> <tiles:insert attribute="header" />			
		</div>
	</nav>
	
	<!-- Main Body Information --> <tiles:insert attribute="body" />	
	
	<!-- Footer Information --> <tiles:insert attribute="footer" />
</body>
</html>