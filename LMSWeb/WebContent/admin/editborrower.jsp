<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList" %>
    <%@page import="com.gcit.lms.entity.Borrower" %>
    <%
    Borrower borrower = null;
    	if (request.getAttribute("borrower") != null)
    		borrower = (Borrower) request.getAttribute("borrower");
		System.out.println("Borrower CardNo to be edited " + borrower.getCardNo());
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Admin</h2>
<h3>Please edit Borrower details below:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</head>
${result}
<body>
<button type="submit" onclick="location.href='viewborrowers.jsp'">Back to View Borrowers</button> </br></br>
	<form action="updateBorrower" method="post">
		Borrower Name: <input type="text" name="borrowerName" value="<%=borrower.getBorrowerName()%>"> <br>
		Borrower Address: <input type="text" name="borrowerAddress" value="<%=borrower.getBorrowerAddress()%>"> <br>
		Borrower Phone: <input type="text" name="borrowerPhone" value="<%=borrower.getBorrowerPhone()%>"> <br>
		
		<br/>
		<input type="hidden" name="cardNo" value=<%=borrower.getCardNo()%>>
		<button type="submit">Edit Borrower</button>
	</form>
</body>
</html>