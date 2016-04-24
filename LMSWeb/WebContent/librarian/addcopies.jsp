<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.BookCopy" %>
    <%@ page import="com.gcit.lms.entity.BookLoan" %>
    <%@ page import="com.gcit.lms.entity.Borrower" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    
<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function addCopies(bookId, branchId){
	$.ajax({
		  url: "addCopies",
		  data:{
			  bookId: bookId,
			  branchId: branchId
		  }
		}).done(function(data) {
		  $('#bookCopiesTable').html(data);
		});
}
</script>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Borrower</h2>
<h3>Pick a book to Check Out:</h3>

</head>

<body>
<button type="submit" onclick="location.href='librarian.jsp'">Back to Librarian</button> </br></br>

<% 
		AdministratorService service = new AdministratorService();
    	List<BookCopy> bookCopies = service.getAllNonExistingBookCopies();
%>
    
<table border="3" id="bookCopiesTable">
	<tr>
		<th>Branch Name</th>
		<th>Book Title</th>
		<th># of Copies</th>
		<th>Copies</th>
		
	</tr>
		<%
		if (bookCopies != null && bookCopies.size() > 0)
		for (BookCopy bc: bookCopies) { 
		%>
		<tr>
			<td>
			<%if (bc.getLibraryBranch().getBranchName() != null)
				out.println(bc.getLibraryBranch().getBranchName());%>
			</td>
			<td>
			<%if (bc.getBook().getTitle() != null)
				out.println(bc.getBook().getTitle());%>
			</td>
			<td>
				<input type="text" name="noOfCopies" id="noOfCopies" value=<%=bc.getNoOfCopies()%>>
			</td>			
			<td><button type="button" onclick="addCopies(<%=bc.getBook().getBookId()%>,<%=bc.getLibraryBranch().getBranchId()%>)">ADD</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>