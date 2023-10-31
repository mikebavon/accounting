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