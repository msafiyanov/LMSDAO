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
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteBook(bookId){
	$.ajax({
		  url: "deleteBook",
		  data:{
			  bookId: bookId
		  }
		}).done(function(data) {
		  $('#booksTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<Book> books;
 	List<Publisher> publishers;
 	try {
	 AdministratorService service = new AdministratorService();
	 books = service.getAllBooks();
	 publishers = service.getAllPublishers();
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
<table border="3" id="booksTable">
	<tr>
		<th>Book Title</th>
		<th>Publisher</th>
		<th>Author Name</th>	
		<th>Genre</th>	
		<th>Edit</th>
		<th>Delete</th>
	</tr>
		<%
		if (books != null && books.size() > 0)
		for (Book b: books) { %>
		<tr>
			<td>
				<%out.println(b.getTitle());%>
			</td>
			<td>
				<%
				if (b.getPublisher() != null)
					for (Publisher p: publishers)
						if (p.getPublisherId() == b.getPublisher().getPublisherId()) {
							out.println(p.getPublisherName());
							if (p.getPublisherAddress() != null) 
								out.println(". " + p.getPublisherAddress());
							if (p.getPublisherPhone() != null) 
								out.println(". " + p.getPublisherPhone());
						}
				%>
			</td>
			<td> 
				<%List<Author> authors = b.getAuthors();
				if (authors != null && authors.size() > 0 )
				for (Author a: authors) { 
					out.println(a.getAuthorName()); 
					if (a != authors.get(authors.size() - 1)) out.println(", ");
				}%>
			</td>
			<td> 
				<%List<Genre> genres = b.getGenres();
				if (genres != null && genres.size() > 0 )
				for (Genre g: genres) { 
					out.println(g.getGenreName()); 
					if (g != genres.get(genres.size() - 1)) out.println(", ");
				}%>
			</td>
			<td><button type="button" onclick="javascript:location.href='editBook?bookId=<%=b.getBookId()%>'">EDIT</button></td>
			<td><button type="button" onclick="deleteBook(<%=b.getBookId()%>)">DELETE</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>