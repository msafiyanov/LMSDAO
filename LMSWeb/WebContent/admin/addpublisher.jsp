<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.entity.Publisher" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
    <% 
    	AdministratorService service = new AdministratorService();
    	List<Book> books = service.getAllBooks();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please enter Publisher details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
	<button type="submit" onclick="location.href='administrator.jsp'">Back to Admin</button> </br></br>
	<form action="addPublisher" method="post">
		Publisher Name: <input type="text" name="publisherName"> <br/> </br>
		Publisher Address: <input type="text" name="publisherAddress"> <br/> </br>
		Publisher Phone: <input type="text" name="publisherPhone"> <br/> </br>
		
		Please pick books of your publisher: <br/>
		<select multiple name="bookId">
			<%if (books != null )
			for(Book b: books){ %>
			<option value="<%=b.getBookId()%>"><%=b.getTitle() %></option>
			<%} %>
		</select>
		<br/><br/>
		
		<button type="submit">Add Publisher</button>
	</form>
</body>
</html>