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
            jQuery.validator.addMethod("checkemail", function(value, element) {
                return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(value) || /^[0-9]{10}$/.test(value);
            });
            jQuery(document).ready(function($) {
                $("#forget").validate({
                    //ignore: [],
                    rules: {
                        email: {
                            required: true,
                            checkemail: true
                        },
                        phone: {
                            required: true,
                            minlength: 10,
                            maxlength: 10
                        },
                    },
                    messages: {
                        
                        email: {
                            required: "Please enter the email.",
                            email: "Please enter valid email id"
                        },
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
        <h2> Forget Password </h2>
        </font>
        <br><br>
        <% if (session.getAttribute("ema") == null) {%>
        <form action="forget" method="POST" id="forget">
            <div class="form-group col-md-4">
                <label >Enter your email:</label>
                <input type="email" name="email" class="form-control" placeholder="enter your email" id="email">
            </div>
            <div class="form-group col-md-4">
                <label >Enter your phone-Number:</label>
                <input type="number" name="phone" class="form-control" placeholder="enter your Phone-Number">
            </div>
            <button type="submit" class="btn btn-primary" name="forget">Search</button>
        </form>
        <%}else if(session.getAttribute("ema") != null) {%>
				<form action='forget' method='POST' id='forget'>
            	<div class="container ">
                	<div class="jumbotron">
                    	<div class="form-group col-md-4">
                        	<label >Enter Your new Password</label>
                        	<input type="text" class="form-control"  name="pw" id="pw">
                    	</div>
                    	<div class="form-group col-md-4">
                        	<label >Re-enter Your Passord</label>
                        	<input type="text" class="form-control"  name="cp">
                    	</div>
                    	<button type="submit" class="btn btn-primary" name="update">Update</button>
                	</div>
           		</div>
        	</form>
        <%}%>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>
