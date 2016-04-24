<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Borrower" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteBorrower(cardNo){
	$.ajax({
		  url: "deleteBorrower",
		  data:{
			  cardNo: cardNo
		  }
		}).done(function(data) {
		  $('#borrowersTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<Borrower> borrowers;
 	try {
	 AdministratorService service = new AdministratorService();
	 borrowers = service.getAllBorrowers();
 }
    catch (Exception e) { %>
    	<h2>We are sorry. Database is down. Please try again later...</h2>
    	<%
    	System.out.println("DB Down");
    	 return;    	
    	}      		
    	
    %>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>

</head>
${result}
<body>
<button type="submit" onclick="location.href='administrator.jsp'">Back to Admin</button> </br></br>
<table border="3" id="borrowersTable">
	<tr>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
		<%
		if (borrowers != null && borrowers.size() > 0)
		for (Borrower b: borrowers) { %>
		<tr>
			<td>
				<%out.println(b.getBorrowerName());%>
			</td>
			<td>
				<%out.println(b.getBorrowerAddress());%>
			</td>
			<td>
				<%out.println(b.getBorrowerPhone());%>
			</td>
			
			<td><button type="button" onclick="javascript:location.href='editBorrower?cardNo=<%=b.getCardNo()%>'">EDIT</button></td>
			<td><button type="button" onclick="deleteBorrower(<%=b.getCardNo()%>)">DELETE</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>