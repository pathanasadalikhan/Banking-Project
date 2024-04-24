<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <style>
        h1{
            text-align: center;
            text-shadow: 2px 1px 2px black;
        }
        .errmsg{
            background: green;
            padding: 10px;
            width: 50%;
            color: white;
            font-weight: bold;
        }
        #ant{
        	height: 60px;
        	width: 600px;
        	background-color: blue;
        	border-radius: 10px;
        	margin: 5px;
        	border: none;
        }
        #anc{
        	font-size: x-large;
        	text-decoration: none;
        	color:white;
        }
        #idiv{
        height:auto;
        background-color: greenyellow;
        padding: 40px
        }
    </style>
    <body>
        <%@include file="Header.jsp"%>
    <center>
        <% if (request.getAttribute("status") != null) {%>
        <h1 class="errmsg"> <%= request.getAttribute("status")%></h1>
        <%}%>
        <br>
        <div>
            <h1>Sookshmas E-Learning Pvt. Ltd</h1>
        </div>
        <% if (session.getAttribute("uname") != null) {%>
        <h1>  Welcome <%= session.getAttribute("uname")%></h1>
        <h1> Email :<%=session.getAttribute("email") %></h1>
        <h1> I'D :<%=session.getAttribute("id") %></h1>
        <div id="idi"><button id="ant"><a href="Deposit.jsp" id="anc">Deposit</a></button><br>
        <button id="ant"><a href="Withdraw.jsp" id="anc">Withdraw</a></button><br>
        <button id="ant"><a href="Balance.jsp" id="anc">Balance</a></button><br>
        <button id="ant"><a href="Transfer.jsp" id="anc">Transfer</a></button></div>
        <%}%>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>
