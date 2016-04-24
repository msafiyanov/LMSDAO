<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Author" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.entity.Publisher" %>
    <%@page import="com.gcit.lms.entity.Genre" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
    <%
    	Book book = null;
    	AdministratorService service = new AdministratorService();
    	List<Author> authors = service.getAllAuthors();
    	if (request.getAttribute("book") != null)
    		book = (Book) request.getAttribute("book");

    	List<Author> bAuthors = book.getAuthors();
    	if (bAuthors == null || bAuthors.size() == 0)
    		bAuthors = null;
    	
    	List<Publisher> publishers = service.getAllPublishers();
    	Publisher bPublisher = book.getPublisher();
    	
    	List<Genre> genres = service.getAllGenres();
    	List<Genre> bGenres = book.getGenres();
    	if (bGenres == null || bGenres.size() == 0)
    		bGenres = null;
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
	<button type="submit" onclick="location.href='viewbooks.jsp'">Back to View Books</button> </br></br>
	<form action="updateBook" method="post">
		Book Title: <input type="text" name="bookTitle" value="<%=book.getTitle()%>"> </br></br>
		Associate book to authors:<br/>
		<select id "author_selection" multiple name="authorId">
			<%if (authors != null)
			for(Author a: authors){ %>
			<option value="<%=a.getAuthorId()%>" <%= (bAuthors != null && bAuthors.contains(a))?"selected":"" %>><%=a.getAuthorName() %></option>
			<%} %>
		</select></br></br>
		
		Associate book to publisher: <br/>
		<select name="pubId">
			<option value=""></option>
			<%if (publishers != null)
			for(Publisher p: publishers){ %>
			<option value="<%=p.getPublisherId()%>" <%= (bPublisher != null && bPublisher.equals(p))?"selected":"" %>><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		</br></br>
		
		Associate book to genres:<br/>
		<select id "genre_selection" multiple name="genreId">
			<%if (genres != null)
			for(Genre a: genres){ %>
			<option value="<%=a.getGenreId()%>" <%= (bGenres != null && bGenres.contains(a))?"selected":"" %>><%=a.getGenreName() %></option>
			<%} %>
		</select></br>
		
		<br/>
		<input type="hidden" name="bookId" value=<%=book.getBookId()%>>
		<button type="submit">Edit Book</button>
	</form>
</body>
</html>