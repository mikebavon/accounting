package com.bavon.app.action;

import com.bavon.app.bean.JournalBean;
import com.bavon.app.bean.JournalBeanI;
import com.bavon.app.model.Journal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/journals")
public class JournalAction extends BaseAction {

    JournalBeanI journalBean = new JournalBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        renderPage(req, resp, 2, Journal.class, journalBean.list(Journal.class));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getParameterMap().put("date", new String[]{new Date().toString()});
        journalBean.addOrUpdateAccount(serializeForm(Journal.class, req.getParameterMap()));

        resp.sendRedirect("./journals");

    }
}
