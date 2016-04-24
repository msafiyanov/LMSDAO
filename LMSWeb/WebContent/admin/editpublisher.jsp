<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Publisher" %>
    <%@page import="com.gcit.lms.entity.Book" %>
    <%@page import="com.gcit.lms.service.AdministratorService" %>
    <%
   	 	Publisher publisher = null;
    	AdministratorService service = new AdministratorService();
    	List<Book> books = service.getAllBooks();
    	if (request.getAttribute("publisher") != null)
    		publisher = (Publisher) request.getAttribute("publisher");
		System.out.println("Publisher ID to be edited " + publisher.getPublisherId());
    	List<Book> aBooks = publisher.getBooks();
    	if (aBooks == null || aBooks.size() == 0)
    		aBooks = null;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please edit Publisher details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
<button type="submit" onclick="location.href='viewpublishers.jsp'">Back to View Publishers</button> </br></br>
	<form action="updatePublisher" method="post">
		Publisher Name: <input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"> <br>
		Publisher Address: <input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"> <br>
		Publisher Phone: <input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"> <br>
		
		Associate publisher to books:<br/>
		<select id "book_selection" multiple name="bookId">
			<%for(Book b: books){ %>
			<option value="<%=b.getBookId()%>" <%= (aBooks != null && aBooks.contains(b))?"selected":"" %>><%=b.getTitle() %></option>
			<%} %>
		</select>
		
		<br/>
		<input type="hidden" name="publisherId" value=<%=publisher.getPublisherId()%>>
		<button type="submit">Edit Publisher</button>
	</form>
</body>
</html>