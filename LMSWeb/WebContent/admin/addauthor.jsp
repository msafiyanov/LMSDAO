<%@ include file="/include.html" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.entity.Author" %>
    <%@ page import="com.gcit.lms.entity.Book" %>
    <%@ page import="com.gcit.lms.service.AdministratorService" %>
<%
	AdministratorService service = new AdministratorService();
	List<Book> books;
	Integer bookCount = service.getBookCount();
	Integer pageSize = bookCount;
	try {
		if (request.getAttribute("books") != null) {
			books = (List<Book>) request.getAttribute("books");
		} else {
			books = service.getAllBooks(1, pageSize);
		}
	} catch (Exception e) {
%>
<h2>We are sorry. Database is down. Please try again later...</h2>
<%
	System.out.println("DB Down");
		return;
	}
%>
${result}

	<form action="addAuthor" method="post">
		Author Name: <input type="text" name="authorName"> <br/> </br>
		Associate author to books:<br/>
		<select multiple name="bookId">
			<%for(Book b: books){ %>
			<option value="<%=b.getBookId()%>"><%=b.getTitle() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit">Add Author</button>
	</form>
