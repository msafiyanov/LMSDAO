<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Publisher" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script type="text/javascript">
function deletePublisher(publisherId){
	$.ajax({
		  url: "deletePublisher",
		  data:{
			  publisherId: publisherId
		  }
		}).done(function(data) {
		  $('#publishersTable').html(data);
		});
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LMS</title>

 <% List<Publisher> publishers;
 	try {
	 AdministratorService service = new AdministratorService();
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
<table border="3" id="publishersTable">
	<tr>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th>Books</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
		<%
		if (publishers != null && publishers.size() > 0)
		for (Publisher p: publishers) { %>
		<tr>
			<td>
				<%if (p.getPublisherName() != null) 
					out.println(p.getPublisherName());%>
			</td>
			<td>
				<%if (p.getPublisherAddress() != null) 
					out.println(p.getPublisherAddress());%>
			</td>
			<td>
				<%if (p.getPublisherPhone() != null) 
					out.println(p.getPublisherPhone());%>
			</td>
			
			<td> 
				<%List<Book> books = p.getBooks();
				if (books != null && books.size() > 0 )
				for (Book b: books) { 
					out.println(b.getTitle()); 
					if (b != books.get(books.size() - 1)) out.println(", ");
				}%>
			</td>
			<td><button type="button" onclick="javascript:location.href='editPublisher?publisherId=<%=p.getPublisherId()%>'">EDIT</button></td>
			<td><button type="button" onclick="deletePublisher(<%=p.getPublisherId()%>)">DELETE</button></td>
		</tr>
		<% } %>
		
	</table>
</body>
</html>