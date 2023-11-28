package com.bavon.app.action;

import com.bavon.app.bean.PaymentBeanI;
import com.bavon.app.model.Payment;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payments")
public class PaymentAction  extends BaseAction {

    @EJB
    PaymentBeanI paymentBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 7, Payment.class, paymentBean.list(new Payment()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        paymentBean.addOrUpdate(serializeForm(Payment.class, req.getParameterMap()));

        resp.sendRedirect("./payments");

    }
}
