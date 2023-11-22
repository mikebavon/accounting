package com.bavon.app.action;

import com.bavon.app.bean.AccountBean;
import com.bavon.app.bean.AccountBeanI;
import com.bavon.app.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accounts")
public class AccountAction extends BaseAction {

    private final AccountBeanI accountBean = new AccountBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        renderPage(req, resp, 1,  Account.class, accountBean.list(new Account()));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        accountBean.addOrUpdate(serializeForm(Account.class, req.getParameterMap()));

        resp.sendRedirect("./accounts");

    }

}
