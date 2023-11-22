package com.bavon.app.action;

import com.bavon.app.bean.ItemBeanI;
import com.bavon.app.model.Item;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/items")
public class ItemAction extends BaseAction{

    @EJB
    private ItemBeanI itemBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 8,  Item.class, itemBean.list(new Item()));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        itemBean.addOrUpdate(serializeForm(Item.class, req.getParameterMap()));

        resp.sendRedirect("./items");

    }
}
