package com.bavon.app.action;

import com.bavon.app.model.Journal;
import com.bavon.app.view.helper.HtmlCmpRender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("journals")
public class JournalAction extends BaseAction {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        renderPage(req, resp, 1,
                HtmlCmpRender.form(Journal.class) + HtmlCmpRender.table(new ArrayList<Journal>()));

    }
}
