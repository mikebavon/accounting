package com.bavon.app.action;

import com.bavon.app.bean.CustomerBeanI;
import com.bavon.app.model.Customer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customers")
public class CustomerAction extends BaseAction{

    @EJB
    private CustomerBeanI customerBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 3,Customer.class, customerBean.list(new Customer()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        customerBean.addOrUpdate(serializeForm(Customer.class, req.getParameterMap()));

        resp.sendRedirect("./customers");

    }
}
