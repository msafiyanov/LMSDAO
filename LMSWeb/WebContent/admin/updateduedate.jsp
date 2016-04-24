<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.entity.BookLoan" %>
    <%@page import="com.gcit.lms.entity.Borrower" %>
    <%@page import="com.gcit.lms.entity.LibraryBranch" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function updateDueDate(bookId, cardNo, branchId){
	var noOfDays = $('#noOfDays').val();
	$.ajax({
		  url: "updateDueDate",
		  data:{
			  bookId: bookId,
			  cardNo: cardNo,
			  branchId: branchId,
			  noOfDays : noOfDays
		  }
		}).done(function(data) {
		  $('#bookLoansTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<BookLoan> bookLoans;
 	try {
	 AdministratorService service = new AdministratorService();
	 bookLoans = service.getAllBookLoans();
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
<table border="3" id="bookLoansTable">
	<tr>
		<th>Book Title</th>
		<th>Borrower Name</th>
		<th>Library Branch</th>	
		<th>Date Out</th>	
		<th>Due Date</th>
		<th>Extend Up to Week</th>
	</tr>
		<%
		if (bookLoans != null && bookLoans.size() > 0)
		for (BookLoan bl: bookLoans) { %>
		<tr>
			<td>
				<%out.println(bl.getBook().getTitle());%>
			</td>
			<td>
				<%out.println(bl.getBorrower().getBorrowerName());%>
			</td>
			<td>
				<%out.println(bl.getLibraryBranch().getBranchName());%>
			</td>
			<td>
				<%out.println(bl.getDateOut());%>
			</td>
			<td> 
				<%out.println(bl.getDueDate());%>
			</td>			
			<td>
			
				<select name="noOfDays" id="noOfDays"> 
				<%int i = 0;
				for(i = 0; i < 8; i++){ %>
				<option value="<%=i%>">Add <%=i%> days</option>
				<%} %>
				</select>
		 		<button type="button" onclick="updateDueDate(<%=bl.getBook().getBookId()%>,<%=bl.getBorrower().getCardNo()%>,<%=bl.getLibraryBranch().getBranchId()%>)">EXTEND</button>
		 	</td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>