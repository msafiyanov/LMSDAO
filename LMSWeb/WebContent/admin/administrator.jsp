<%@ include file="/include.html" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please choose your task:</h3>

</head>
<body>
<button type="submit" onclick="location.href='../.'">Back to Home</button> </br></br>
	<nav>
		<h4>Authors</h4>
		<ul>
			<li><a href="addauthor.jsp">Add Author</a></li>
			<li><a href="viewauthors.jsp">View Authors</a></li>
		</ul>
	</nav>
	<nav>
		<h4>Books</h4>
		<ul>
			<li><a href="addbook.jsp">Add Book</a></li>
			<li><a href="viewbooks.jsp">View Books</a></li>
		</ul>
	</nav>
	<nav>
		<h4>Publishers</h4>
		<ul>
			<li><a href="addpublisher.jsp">Add Publisher</a></li>
			<li><a href="viewpublishers.jsp">View Publishers</a></li>
		</ul>
	</nav>
	<nav>
		<h4>Library Branches</h4>
		<ul>
			<li><a href="addbranch.jsp">Add Library Branch</a></li>
			<li><a href="viewbranches.jsp">View Library Branches</a></li>
		</ul>
	</nav>
	<nav>
		<h4>Borrowers</h4>
		<ul>
			<li><a href="addborrower.jsp">Add Borrower</a></li>
			<li><a href="viewborrowers.jsp">View Borrowers</a></li>
		</ul>
	</nav>
	<nav>
		<h4>Book Loans</h4>
		<ul>
			<li><a href="updateduedate.jsp">Update Due Date for a Book Loan</a></li>
		</ul>
	</nav>
</body>
</html>