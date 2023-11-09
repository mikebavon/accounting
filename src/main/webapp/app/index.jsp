<%@ page import="com.bavon.app.view.helper.HtmlMenuToolbar" %>
<!DOCTYPE html>
<html>
    <head>
        <style>

            .topnav {
                overflow: hidden;
                background-color: #333;
            }

            .topnav a {
                float: left;
                color: #f2f2f2;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .topnav a:hover {
                background-color: #ddd;
                color: black;
            }

            .topnav a.active {
                background-color: #04AA6D;
                color: white;
            }

            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }

        </style>
    </head>

    <body>

        <%= new HtmlMenuToolbar().menu((int)request.getAttribute("activeMenu")) %>

        <h3> <%= application.getInitParameter("AppName") %><h3>
        <br/>&nbsp;<br/>
        <h3>Welcome:  <%= session.getAttribute("username") %> </h3> <br/>

        <%= request.getAttribute("content") %>
        <a href=\./logout\>Logout</a>
    </body>
</html>