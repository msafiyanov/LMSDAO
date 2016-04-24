<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<LibraryBranch> branches;
 	try {
	 AdministratorService service = new AdministratorService();
	 branches = service.getAllLibraryBranches();
	 System.out.println(branches.size());
 }
    catch (Exception e) { %>
    	<h2>We are sorry. Database is down. Please try again later...</h2>
    	<%
    	System.out.println("DB Down");
    	 return;    	
    	}      		
    	
    %>
<h2>Welcome to Meirbeks GCIT Library Management System - Librarian</h2>

</head>
${result}
<body>
<button type="submit" onclick="location.href='librarian.jsp'">Back to Librarian</button> </br></br>
<table border="3" id="branchesTable">
	<tr>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Edit</th>
	</tr>
		<%
		if (branches != null && branches.size() > 0)
		for (LibraryBranch b: branches) { %>
		<tr>
			<td>
				<%out.println(b.getBranchName());%>
			</td>
			<td>
				<%out.println(b.getBranchAddress());%>
			</td>
			
			<td><button type="button" onclick="javascript:location.href='editBranch?branchId=<%=b.getBranchId()%>'">EDIT</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>