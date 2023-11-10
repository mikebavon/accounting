<%@ page import="com.bavon.app.view.helper.HtmlMenuToolbar" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../style/style.jsp">
            <jsp:param name="pageColor" value="red" />
        </jsp:include>
    </head>

    <body>
        <%= new HtmlMenuToolbar().menu((int)request.getAttribute("activeMenu")) %> <%-- modify to use <jsp:useBean> --%>

        <h3> <%= application.getInitParameter("AppName") %> | Welcome:  <%= session.getAttribute("username") %></h3>
        <br/>
        <%= request.getAttribute("content") %> <%-- modify to use <jsp:useBean> -->
        <a href=\./logout\>Logout</a>
    </body>
</html>