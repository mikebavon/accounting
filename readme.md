Accounting application:

Server details:
Wildfly 26

Servlet Api
-Handles HTTP REQUEST

Main packages of servlet api 
    1. javax.servlet - protocol independent servlet
    2. javax.servlet.http - http servlet

Ways of implementing servlet
 -Implement servlet interface
 -Extend Generic Servlet class
 -Extend HttpServlet Class

GenericServlet
HttpServlet

 service();


1. Implementing servlet (implments interface)

Client (Browser,Mobile) (request) -> Server (Servlet Container - Wildfly) 
    -> Hello -> Service(html content) (response)

2. Extending GenericServlet (extends abstract class)

Client (Browser,Mobile) (request) -> Server (Servlet Container - Wildfly)
    -> Hello -> Service(html content) (response)

3. Extending HTTP servlet 

Client (Browser,Mobile) (request) -> Server (Servlet Container - Wildfly)
-> Hello - Service -> doGet,doPost,doPut(html content) (response)


Servlet Lifecycle
    1 Loading the servlet - point of deployment to the server - servlet container
    2 Servlet instance created - on the first access - servlet container
    3 init() method invoked - called only once
    4 service() - called on any request
    5 destroy() - servlet is shutting down or the server is shutting
        the application is undeployed.

Deployment Descriptor (web.xml) - programming through configuration---later
    programme through convention
    describes how web application should be deployed by the servlet container.

ServletRequest - handles request, get parameters
ServletResponse - enable response, PrintWriter

RequestDispatcher - 

 ServletContext
 HttpSession // Cookies ***Assignment**
 Event & Listeners (Event occurrence of something, listeners is something 
    that waits for and an event to happen so that it can do something else)

 Filters

Assignment *** (20 Marks)
Java Annotations - implement annotations to generate, database table, html form, html table...


JSP - Java Server Pages
JSP - Java code inside HMTL
Servlet - HTML inside JAVA CODE

JSP IS SIMPLY A SERVLET

Jsp Lifecycle

1. Jsp is translated to servlet
    index.jsp->index_jsp.java
2. Compilation (Servlet is compile to bytecode)
   index_jsp.java->index_jsp.class
3. Servlet is loading
4. Instance is created
5. _jspInit() is invoked
6. _jspService() - invoked whenever a request is made
7. _jspDestroy() - whenever the application is undeployed

hello.jsp->hello_jsp.java->hello_jsp.class

JSP SCRIPTING ELEMENTS
<% .... %>
TYPES
COMMENTS <%!-- COMMENT ---%>
DIRECTIVES <%@ directive %>
    page
    include
    tagLib *****
DECLARATION <%! declaration %>
EXPRESSION <%= EXPRESSION %>
SCRIPTLET <% JAVA CODE %>
    
IMPLICIT OBJECTS
1. out = JspWritter
2. request = HttpServletRequest
3. response = HttpServletResponse
4. session = HttpSession
5. application = ServletContext
6. config = ServletConfig
7. exception = jspException
8. page = 
9. pageContext = request, response, session

JSP ACTION TAGS
1. <jsp:forward>
2. <jsp:include>
3. <jsp:useBean>
4. <jsp:param>
