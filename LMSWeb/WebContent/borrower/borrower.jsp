<%@ include file="/include.html" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - Borrower</h2>
<button type="submit" onclick="location.href='../.'">Back to Home</button> </br></br>
<h3>Please login:</h3>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
${result}
</head>
<body>
	<form action="logIn" method="post">
		Card Number: <input type="text" name="cardNo">
		<button type="submit">Login</button>
	</form>
</body>
</html>