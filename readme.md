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
 

    
