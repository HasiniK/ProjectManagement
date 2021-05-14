<%@page import="com.ProjectService" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Link Bootstrap, jQuery, and main.js -->
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/projects.js"></script>
</head>



<body>
<div class="container"><div class="row"><div class="col-6">
	<h1>Project Proposal</h1>
	<form id="formProject" name="formProject">
 		Project Title: 
		<input id="projectTitle" name="projectTitle" type="text" class="form-control form-control-sm">
		<br> Project Short Description: 
		<input id="projectDesc" name="projectDesc" type="text" class="form-control form-control-sm">
		<br> Project Proposal Document Link: 
		<input id="projectDoc" name="projectDoc" type="text" class="form-control form-control-sm">
		<br> Project Video Link: 
		<input id="projectVideo" name="projectVideo" type="text" class="form-control form-control-sm">
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	<br>
	<div id="divItemsGrid">
 		<%
 			ProjectService itemObj = new ProjectService(); 
 			out.print(itemObj.readProject()); 
		 %>
	</div>
</div> </div> </div> 
</body>
</html>





