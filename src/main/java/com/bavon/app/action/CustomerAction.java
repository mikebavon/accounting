package com.bavon.app.action;

import com.bavon.app.model.Customer;
import com.bavon.app.view.helper.HtmlCmpRender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("customers")
public class CustomerAction extends BaseAction{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 2,HtmlCmpRender.form(Customer.class));
        HttpSession httpSession = req.getSession();

    }
}
