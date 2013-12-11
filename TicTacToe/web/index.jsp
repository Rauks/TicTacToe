<%-- 
    Document   : index
    Created on : 11 déc. 2013, 14:19:47
    Author     : Karl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tic Tac Toe - Karl Woditsch</title>
    </head>
    <body>
        <h1>Tic Tac Toe</h1>
        <form action="entryServlet" method="post">
            <input type="submit" name="User" value="Je commence..."><br/>
            <input type="submit" name="Computer" value="L'ordinateur commence...">
        </form>
    </body>
</html>
