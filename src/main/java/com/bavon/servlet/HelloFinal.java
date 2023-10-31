package com.bavon.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloFinal extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        PrintWriter print = resp.getWriter();
        print.print("This is the final hello world, can only be accessed using get method!!");

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        PrintWriter print = resp.getWriter();
        print.print("This is the final hello world, can only be accessed using post method!!");

    }
}
