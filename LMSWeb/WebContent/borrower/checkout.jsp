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
function checkOut(bookId, branchId, cardNo){
	$.ajax({
		  url: "checkOut",
		  data:{
			  bookId: bookId,
			  branchId: branchId,
			  cardNo: cardNo
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
<button type="submit" onclick="location.href='borrower.jsp'">Log Out</button> </br></br>

<% 
    	BorrowerService service = new BorrowerService();
    	List<BookCopy> bookCopies = service.getExistingBooks();
    	Borrower bor = new Borrower();
    	System.out.println("CARD NO: " + request.getParameter("cardNo"));
    	bor.setCardNo(Integer.parseInt(request.getParameter("cardNo")));
    	BookLoan bL = new BookLoan();
    	bL.setBorrower(bor);
    	List<BookLoan> bookLoans = service.getBookLoansByBorrower(bor);
%>
    
<table border="3" id="bookCopiesTable">
	<tr>
		<th>Branch Name</th>
		<th>Book Title</th>
		<th>Copies</th>
		<th>Check Out</th>
	</tr>
		<%
		if (bookCopies != null && bookCopies.size() > 0)
		for (BookCopy bc: bookCopies) { 
		BookLoan bookLoan = new BookLoan(bc.getBook(), bc.getLibraryBranch(), bor);
		if ((bc.getNoOfCopies() > 0) && (bookLoans == null || (bookLoans.contains(bookLoan) && bookLoans.get(bookLoans.indexOf(bookLoan)).getDateIn() != null) || (!bookLoans.contains(bookLoan) ))) {
		%>
		<tr>
			<td>
				<%out.println(bc.getLibraryBranch().getBranchName());%>
			</td>
			<td>
				<%out.println(bc.getBook().getTitle());%>
			</td>
			<td>
				<%out.println(bc.getNoOfCopies());%>
			</td>			
			<td><button type="button" onclick="checkOut(<%=bc.getBook().getBookId()%>,<%=bc.getLibraryBranch().getBranchId()%>,<%=Integer.parseInt(request.getParameter("cardNo"))%>)">Check Out</button></td>
		</tr>
		<% } }%>
		
	</table>
</body>
</html>