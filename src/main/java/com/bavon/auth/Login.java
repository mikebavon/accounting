package com.bavon.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter print = resp.getWriter();

        if (username.equals("Mike") && password.equals("bavon"))
            print.write("Welcome to accounting");
        else
            print.write("<html><body>Invalid login details <a href=\".\"> Login again </a></body></html>");

    }

}
