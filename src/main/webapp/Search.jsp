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
        <style>
        #fo{
        	font-size: xx-large;
        }
            .jumbotron{
                background-color: white;
            }
        </style>
    <center>
    <%long l=(long)session.getAttribute("id"); %>
        <% if(l != 0 && l==1) {%>
        <font color="blue" size="4">
        <h2> Search module </h2>
        </font>
        <br><br>
        <form action="search" method="POST">
            <div class="form-group col-md-4">
                <label >Student id:</label>
                <input type="text" name="id" class="form-control" >
            </div>
            <button type="submit" class="btn btn-primary" name="submit">Search</button>
        </form>
        <% if (request.getParameter("id") != null) {%>
        <div class="container ">
            <div class="jumbotron">

                <table class="table">
                    <thead>
                        <tr style="background-color: lightblue;">
                    <br>
                    <th>Slno</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>

                    </tr>
                    </thead>
                    <tbody id="table">
                        <% Registration reg = new Registration(session);
                        	long it=Long.parseLong(request.getParameter("id"));
                            ArrayList<Student> mydata = reg.getUserinfo(it);
                            Iterator<Student> itr = mydata.iterator();
                            if(itr.hasNext()){
                            	while (itr.hasNext()) {
                                	Student s = itr.next();
                        %>
                        <tr>
                            <td><%=s.getId()%></td>
                            <td><%=s.getName()%></td>
                            <td><%=s.getemail()%></td>
                            <td><%=s.getphone()%></td>
                        </tr> 
                        <%}
                            	}
                            	else{
                            	%>
                        <tr>
                        <td style="color:red" colspan="4" id="fo">There is no data present with that id</td>
                        </tr>
                        <%} %>
                        
                    </tbody>
                </table>
            </div>
        </div>
        <%}
            }%> 
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>
