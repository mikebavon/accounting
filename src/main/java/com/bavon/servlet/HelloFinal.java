package com.bavon.servlet;

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

@WebServlet("/ivy")
public class HelloFinal extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        PrintWriter print = resp.getWriter();
        print.print("<b>Calculator</b><br/>");

        String numberStr1 = req.getParameter("number1");
        String numberStr2 = req.getParameter("number2");

        BigDecimal number1 = new BigDecimal(numberStr1);
        BigDecimal number2 = new BigDecimal(numberStr2);

        print.print(numberStr1 + " * " + numberStr2 + " = "
            + new DecimalFormat("#,###.##").format(number1.multiply(number2)));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter print = resp.getWriter();
        print.print("<b>Calculator</b><br/>");

        String numberStr1 = req.getParameter("number1");
        String numberStr2 = req.getParameter("number2");

        BigDecimal number1 = new BigDecimal(numberStr1);
        BigDecimal number2 = new BigDecimal(numberStr2);

        print.print(numberStr1 + " / " + numberStr2 + " = "
            + new DecimalFormat("#,###.##").format(number1.divide(number2, RoundingMode.HALF_DOWN)));

    }
}
