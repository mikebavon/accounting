package com.bavon.app.action;

import com.bavon.app.model.entity.Journal;
import com.bavon.app.view.html.AppPage;
import com.bavon.app.view.html.HtmlComponent;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("journals")
public class JournalAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession httpSession = req.getSession();

        new AppPage().renderHtml(req, resp, 1,
                HtmlComponent.form(Journal.class) + HtmlComponent.table(new ArrayList<Journal>()));

    }
}
