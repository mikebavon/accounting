<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
    <%@ include file="./style/style.jsp" %>
</style>
</head>
<body>

<h2><%= application.getInitParameter("AppName") %></h2>

<%
    application.setAttribute("pageLink","Home/<a href='index.jsp'>Login</a>/Register");
%>

<%
    if (!session.isNew() && session.getAttribute("loggedInId") != null)
    {
        response.sendRedirect("./home");
    } else {
%>
    <span style="font-weight:bold;font-size:13px;">Login</span>
    <form action="./login" method="post">

      <div class="container">
        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>
      </div>

      <div class="container">
        <span class="psw">Forgot <a href="#">password?</a></span>
      </div>
    </form>

    <span style="font-weight:bold;font-size:13px;color:red;">Dont Have An Account? <a href="register.jsp">Register</a></span>

    </body>
    </html>
<% } %>
