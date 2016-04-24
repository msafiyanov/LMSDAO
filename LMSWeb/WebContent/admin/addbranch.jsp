<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please enter Library Branch details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
<button type="submit" onclick="location.href='administrator.jsp'">Back to Admin</button> </br></br>
	<form action="addBranch" method="post">
		Library Branch Name: <input type="text" name="branchName"> <br/> </br>
		Library Branch Address: <input type="text" name="branchAddress"> <br/> </br>
				
		<button type="submit">Add Library Branch</button>
	</form>
</body>
</html>