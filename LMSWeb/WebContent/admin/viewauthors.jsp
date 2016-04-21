<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Author" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deleteAuthor(authorId){
	$.ajax({
		  url: "deleteAuthor",
		  data:{
			  authorId: authorId
		  }
		}).done(function(data) {
		  $('#authorsTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<Author> authors;
 	try {
	 AdministratorService service = new AdministratorService();
	 authors = service.getAllAuthors();
	 System.out.println(authors.size());
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
<button type="submit" onclick="location.href='administrator.html'">Back to Admin</button> </br></br>
<table border="3" id="authorsTable">
	<tr>
		<th>Author Name</th>
		<th>Book Title</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
		<%
		if (authors != null && authors.size() > 0)
		for (Author a: authors) { %>
		<tr>
			<td>
				<%out.println(a.getAuthorName());%>
			</td>
			
			<td> 
				<%List<Book> books = a.getBooks();
				if (books != null && books.size() > 0 )
				for (Book b: books) { 
					out.println(b.getTitle()); 
					if (b != books.get(books.size() - 1))out.println(", ");
				}%>
			</td>
			<td><button type="button" onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">EDIT</button></td>
			<td><button type="button" onclick="deleteAuthor(<%=a.getAuthorId()%>)">DELETE</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>