package com.bavon.app.action;

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

@WebServlet("/home")
public class HomeAction extends HttpServlet {

    AccountBeanI accountBean = new AccountBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession httpSession = req.getSession();

        new AppPage().renderHtml(req, resp, 0,
        "<h2>Chart Of Accounts</h2>" +
            "<br/>Add Account<br/><form action=\"./account\" method=\"post\">" +
                "  <label for=\"code\">Account Code:</label><br>" +
                "  <input type=\"text\" id=\"code\" name=\"code\" ><br>" +
                "  <label for=\"name\">Account name:</label><br>" +
                "  <input type=\"text\" id=\"name\" name=\"name\" ><br><br>" +
                "  <input type=\"submit\" value=\"Submit\">" +
            "</form><br/>" + accountBean.chartOfAccounts());

    }

}
