<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Author" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
    <%
    	Author author = null;
    	AdministratorService service = new AdministratorService();
    	List<Book> books = service.getAllBooks();
    	if (request.getAttribute("author") != null)
    		author = (Author) request.getAttribute("author");

    	List<Book> aBooks = author.getBooks();
    	if (aBooks == null || aBooks.size() == 0)
    		aBooks = null;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please edit Author details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
	<button type="submit" onclick="location.href='viewauthors.jsp'">Back to View Authors </button> </br></br>
	<form action="updateAuthor" method="post">
		Author Name: <input type="text" name="authorName" value="<%=author.getAuthorName()%>"> <br>
		Associate author to books:<br/>
		<select id "book_selection" multiple name="bookId">
			<%for(Book b: books){ %>
			<option value="<%=b.getBookId()%>" <%= (aBooks != null && aBooks.contains(b))?"selected":"" %>><%=b.getTitle() %></option>
			<%} %>
		</select>
		
		<br/>
		<input type="hidden" name="authorId" value=<%=author.getAuthorId()%>>
		<button type="submit">Edit Author</button>
	</form>
</body>
</html>