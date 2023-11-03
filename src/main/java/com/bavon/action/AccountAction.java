package com.bavon.action;

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

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {

            Database database = Database.getDbInstance();

            database.getAccounts().add(new Account(req.getParameter("code"),
                req.getParameter("name"), BigDecimal.ZERO));

            resp.sendRedirect("./home");

        } else
            resp.sendRedirect("./");

    }
}
