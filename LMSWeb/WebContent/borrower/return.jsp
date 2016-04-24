<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.BookCopy" %>
    <%@ page import="com.gcit.lms.entity.BookLoan" %>
    <%@ page import="com.gcit.lms.entity.Borrower" %>
    <%@ page import="com.gcit.lms.service.BorrowerService" %>
    
<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function checkIn(bookId, branchId, cardNo){
	$.ajax({
		  url: "checkIn",
		  data:{
			  bookId: bookId,
			  branchId: branchId,
			  cardNo: cardNo
		  }
		}).done(function(data) {
		  $('#bookLoansTable').html(data);
		});
}
</script>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Borrower</h2>
<h3>Pick a book to Return:</h3>

</head>

<body>
<button type="submit" onclick="location.href='borrower.jsp'">Log Out</button> </br></br>

<% 
    	BorrowerService service = new BorrowerService();
    	//List<BookCopy> bookCopies = service.getExistingBooks();
    	Borrower bor = new Borrower();
    	System.out.println("CARD NO: " + request.getParameter("cardNo"));
    	bor.setCardNo(Integer.parseInt(request.getParameter("cardNo")));
    	//BookLoan bL = new BookLoan();
    	//bL.setBorrower(bor);
    	List<BookLoan> bookLoans = service.getBookLoansByBorrower(bor);
%>
    
<table border="3" id="bookLoansTable">
	<tr>
		<th>Branch Name</th>
		<th>Book Title</th>
		<th>Date Out</th>
		<th>Due Date</th>
		<th>Return</th>
	</tr>
		<%
		if (bookLoans != null && bookLoans.size() > 0)
		for (BookLoan bl: bookLoans) { 
		if (bl.getDateIn() == null) {
		%>
		<tr>
			<td>
				<%out.println(bl.getLibraryBranch().getBranchName());%>
			</td>
			<td>
				<%out.println(bl.getBook().getTitle());%>
			</td>
			<td>
				<%out.println(bl.getDateOut());%>
			</td>
			<td>
				<%out.println(bl.getDueDate());%>
			</td>
		
			<td><button type="button" onclick="checkIn(<%=bl.getBook().getBookId()%>,<%=bl.getLibraryBranch().getBranchId()%>,<%=bor.getCardNo()%>)">Return Now</button></td>
		</tr>
		<% } }%>
		
	</table>
</body>
</html>