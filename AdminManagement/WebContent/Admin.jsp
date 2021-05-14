<%@page import="com.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Admin.js"></script>

<meta charset="ISO-8859-1">
<title>Buyer Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Buyer Management</h1>

	<form id="formItem" name="formItem">
		
		 Buyer Name:
		<input id="BuyerName" name="BuyerName" type="text" class="form-control form-control-sm"><br> 
		 NIC:
		<input id="NIC" name="NIC" type="text" class="form-control form-control-sm"><br>
		 Email:
		<input id="Email" name="Email" type="text" class="form-control form-control-sm"><br>
		 Phone Number:
		<input id="PhoneNumber" name="PhoneNumber" type="text" class="form-control form-control-sm"><br>
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	Admin CartObj = new Admin(); 
		 out.print(CartObj.readCart());
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>