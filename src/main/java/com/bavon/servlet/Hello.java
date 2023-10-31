package com.bavon.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet("/jonathan")
public class Hello implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        System.out.println("The hello servlet has been created");

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        PrintWriter print = servletResponse.getWriter();
        print.print("<b>Calculator</b><br/>");

        String numberStr1 = servletRequest.getParameter("number1");
        String numberStr2 = servletRequest.getParameter("number2");

        BigDecimal number1 = new BigDecimal(numberStr1);
        BigDecimal number2 = new BigDecimal(numberStr2);

        print.print(numberStr1 + " + " + numberStr2 + " = " + number1.add(number2));
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Shutting down hello class!");
    }
}
