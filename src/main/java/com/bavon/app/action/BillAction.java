package com.bavon.app.action;

import com.bavon.app.bean.BillBeanI;
import com.bavon.app.model.Bill;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("bills")
public class BillAction extends BaseAction {

    @EJB
    private BillBeanI billBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 6, Bill.class, billBean.list(new Bill()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        billBean.addOrUpdate(serializeForm(Bill.class, req.getParameterMap()));

        resp.sendRedirect("./bills");

    }
}
