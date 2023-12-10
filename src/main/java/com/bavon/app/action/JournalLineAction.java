package com.bavon.app.action;

import com.bavon.app.bean.JournalLineBeanI;
import com.bavon.app.model.JournalLine;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/journallines")
public class JournalLineAction extends BaseAction {

    @EJB
    JournalLineBeanI journalLineBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 2, JournalLine.class, journalLineBean.list(new JournalLine()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        journalLineBean.addOrUpdate(serializeForm(JournalLine.class, req.getParameterMap()));

        resp.sendRedirect("./journallines?action=add");

    }
}
