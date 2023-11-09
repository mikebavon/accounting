package com.bavon.app.action;

import com.bavon.app.model.Invoice;
import com.bavon.app.view.helper.HtmlCmpRender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("invoices")
public class InvoiceAction extends BaseAction {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        renderPage(req, resp, 3,
                HtmlCmpRender.form(Invoice.class) + HtmlCmpRender.table(new ArrayList<Invoice>()));
        HttpSession httpSession = req.getSession();

    }
}
