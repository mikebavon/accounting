package com.bavon.app.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("reports")
public class ReportAction extends BaseAction {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 5,"<h2>Reports </h2> Reports will go here");

    }
}
