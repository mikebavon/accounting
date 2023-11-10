<%@ page import="com.bavon.app.view.helper.HtmlMenuToolbar" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../style/style.jsp" %>
    </head>

    <body>
        <%= new HtmlMenuToolbar().menu((int)request.getAttribute("activeMenu")) %>

        <h3> <%= application.getInitParameter("AppName") %> | Welcome:  <%= session.getAttribute("username") %></h3>
        <br/>
        <%= request.getAttribute("content") %>
        <a href=\./logout\>Logout</a>
    </body>
</html>