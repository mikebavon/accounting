package com.bavon.app.action;

import com.bavon.app.bean.AtmBeanI;
import com.bavon.app.bean.CustomerBeanI;
import com.bavon.app.model.Atm;
import com.bavon.app.model.Customer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/atm")
public class AtmAction extends BaseAction{


    @EJB
    private AtmBeanI atmBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 3, Atm.class, atmBean.list(new Atm()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        atmBean.addOrUpdate(serializeForm(Atm.class, req.getParameterMap()));

        resp.sendRedirect("./atm");

    }
}
