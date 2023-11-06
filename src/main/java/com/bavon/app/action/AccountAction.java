package com.bavon.app.action;

import com.bavon.app.bean.AccountBean;
import com.bavon.app.bean.AccountBeanI;
import com.bavon.app.model.entity.Account;
import com.bavon.database.Database;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/account")
public class AccountAction extends HttpServlet {

    private AccountBeanI accountBean = new AccountBean();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        accountBean.addOrUpdateAccount(new Account(req.getParameter("code"),
            req.getParameter("name"), BigDecimal.ZERO));

        resp.sendRedirect("./home");


    }
}
