package com.bavon.app.action;

import com.bavon.app.bean.AccountBean;
import com.bavon.app.bean.AccountBeanI;
import com.bavon.app.model.Account;
import com.bavon.app.view.helper.HtmlCmpRender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends BaseAction {

    AccountBeanI accountBean = new AccountBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        renderPage(req, resp, 0, HtmlCmpRender.form(Account.class) + accountBean.chartOfAccounts());
    }

}
