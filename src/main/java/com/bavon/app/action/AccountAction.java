package com.bavon.app.action;

import com.bavon.app.bean.AccountBean;
import com.bavon.app.bean.AccountBeanI;
import com.bavon.app.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountAction extends BaseAction {

    private Account account = new Account();

    private AccountBeanI accountBean = new AccountBean();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        serializeForm(account, req.getParameterMap());
        accountBean.addOrUpdateAccount(account);

        resp.sendRedirect("./home");


    }
}
