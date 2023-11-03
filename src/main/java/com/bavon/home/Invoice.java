package com.bavon.home;

import com.bavon.app.bean.AccountBean;
import com.bavon.app.bean.AccountBeanI;
import com.bavon.app.view.html.AppPage;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("invoices")
public class Invoice extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {

            AccountBeanI accountBeanEn = new AccountBean();

            new AppPage().renderHtml(req, resp, 2,
                    "<h2>Invoices </h2> Invoices list/register will go here");

        } else
            resp.sendRedirect("./");
    }
}
