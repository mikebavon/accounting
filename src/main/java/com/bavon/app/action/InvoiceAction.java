package com.bavon.app.action;

import com.bavon.app.bean.InvoiceBean;
import com.bavon.app.bean.InvoiceBeanI;
import com.bavon.app.model.Account;
import com.bavon.app.model.Invoice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/invoices")
public class InvoiceAction extends BaseAction {

    private final InvoiceBeanI invoiceBean = new InvoiceBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 4, Invoice.class, invoiceBean.list(Invoice.class));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        invoiceBean.addOrUpdate(serializeForm(Invoice.class, req.getParameterMap()));

        resp.sendRedirect("./invoices");

    }
}
