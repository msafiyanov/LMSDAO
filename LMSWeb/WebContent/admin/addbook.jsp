<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.Author" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.entity.Publisher" %>
    <%@ page import="com.gcit.lms.entity.Genre" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <% 
    	AdministratorService service = new AdministratorService();
    	List<Author> authors = service.getAllAuthors();
    	List<Publisher> publishers = service.getAllPublishers();
    	List<Genre> genres = service.getAllGenres();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please enter Book details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
	<button type="submit" onclick="location.href='administrator.jsp'">Back to Admin</button> </br></br>
	<form action="addBook" method="post">
		Book Title: <input type="text" name="bookTitle"> <br/> </br>
		
		Please pick authors of your book: <br/>
		<select multiple name="authorId">
			<%if (authors != null )
			for(Author a: authors){ %>
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		<br/><br/>
		
		Please pick publisher of your book: <br/>
		<select name="pubId">
			<option value=""></option>
			<%if (publishers != null)
			for(Publisher p: publishers){ %>
			<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		</br></br>
		
		Please pick genres of your book: <br/>
		<select multiple name="genreId">
			<%if (genres != null ) 
			for(Genre g: genres){ %>
			<option value="<%=g.getGenreId()%>"><%=g.getGenreName() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit">Add Book</button>
	</form>
</body>
</html>