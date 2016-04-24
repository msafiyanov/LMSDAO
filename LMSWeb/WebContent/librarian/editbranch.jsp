<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.LibraryBranch" %>
    <%
   	 	LibraryBranch lb = null;
    	if (request.getAttribute("branch") != null)
    		lb = (LibraryBranch) request.getAttribute("branch");
		System.out.println("LibraryBranch ID to be edited " + lb.getBranchId());
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Librarian</h2>
<h3>Please edit Library Branch details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
<button type="submit" onclick="location.href='viewbranches.jsp'">Back to View Branches</button> </br></br>
	<form action="updateBranch" method="post">
		Library Branch Name: <input type="text" name="branchName" value="<%=lb.getBranchName()%>"> <br>
		Library Branch Address: <input type="text" name="branchAddress" value="<%=lb.getBranchAddress()%>"> <br>
		
		<input type="hidden" name="branchId" value=<%=lb.getBranchId()%>>
		<button type="submit">Edit Branch</button>
	</form>
</body>
</html>