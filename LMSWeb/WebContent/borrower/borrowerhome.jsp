<%@ include file="/include.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LMS</title>
<h2>Welcome to Meirbeks GCIT Library Management System - ${result}</h2>
<h3>Please login:</h3>

</head>

<body>
<button type="submit" onclick="location.href='borrower.jsp'">Log Out</button> </br></br>
	
	<a href="checkout.jsp?cardNo=${cardNo}">Check out a Book</a> </br></br>
	<a href="return.jsp?cardNo=${cardNo}">Return a Book</a> </br></br>
	
	<b>  Borrower details </b><br>
	<i>Name: ${result}</i> <br>
	<i>Address: ${address}</i> <br>
	<i>Phone: ${phone}</i> <br>
	
	
</body>
</html>