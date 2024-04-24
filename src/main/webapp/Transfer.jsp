<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Student"%>
<%@page import="Model.Registration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    </head>
    <body>
        <%@include file="Header.jsp"%>
        <script>
            jQuery(document).ready(function($) {
                $("#transfer").validate({
                    //ignore: [],
                    rules: {
                        phone: {
                            required: true,
                            minlength: 10,
                            maxlength: 10
                        },
                    },
                    messages: {
                        phone: {
                            required: "Please enter the number.",
                            minlength: "Please enter the  10 digit number .",
                            maxlength: "more than 10 digits."
                        },
                    }
                });
            });
        </script>
        <style>
        #fo{
        	font-size: xx-large;
        }
            .jumbotron{
                background-color: white;
            }
            #msg{
            	background-color: green;
            }
            #stat{
            	font-size: x-large;
            	color:black;
            }
        </style>
    <center>
    <% if (request.getAttribute("status") != null) {%>
        <div id="msg">  <h1 id="stat"><%= request.getAttribute("status")%></h1></div>
        <%}%>
        <font color="blue" size="4">
        <h2> Transfer Amount </h2>
        </font>
        <br><br>
        <form action="transfer" method="POST" id="transfer">
            <div class="form-group col-md-4">
                <label >Enter phone number to transfer amount:</label>
                <input type="number" class="form-control" placeholder="enter phone number" name="phone">
            </div>
            <div class="form-group col-md-4">
                <label >Enter the amount:</label>
                <input type="number" name="amt" class="form-control" placeholder="enter the amount that you want to transfer">
            </div>
            <button type="submit" class="btn btn-primary" name="transfer" value="Transfer">Transfer</button>
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>
