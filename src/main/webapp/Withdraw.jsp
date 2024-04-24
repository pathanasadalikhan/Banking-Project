<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
 	<title>TODO supply a title</title>
 	<meta charset="UTF-8">
 	<meta name="viewport" content="width=device-width">
 	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<style type="text/css">
	#cont{
		background-color: green;
	}
	#fort{
		height: 300px;
		width: 400px;
		background-color: greenyellow;
		padding-top: 30px;
		border-radius: 20px;
		margin-top: 150px;
	}
	#dep{
		height: 30px;
		width: 300px;
		border-radius: 7px;
		border: none;
	}
	#sub{
		height: 30px;
		width:200px;
		background-color: blue;
		border-radius: 5px;
		border: none;
		color:white;
	}
	h2{
		padding-bottom: 40px;
	}
	</style>
</head>
<body>
	<%@include file="Header.jsp"%>
 	<center>
 	<br><div id="cont">
 	<% if (request.getAttribute("status") != null) {%>
 			<h1 class="errmsg"> <%= request.getAttribute("status")%></h1>
 		<%}%></div>
 	<div id="fort"><font color="blue" size="4">
 	<h2> WithDraw </h2>
	 </font>
 		<form action="withdraw" method="post">
 		<input type="number" id="dep" name="amt" placeholder="enter the amount to deposit">
 		<br></br>
 		<input type="submit" name="withdraw" value="Withdraw" id="sub">
 		</form></div>
 	</center>
 	<%@include file="Footer.jsp"%>
</body>
</html>