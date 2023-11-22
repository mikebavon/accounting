package com.bavon.app.action;

import com.bavon.app.bean.VendorBeanI;
import com.bavon.app.model.Vendor;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vendors")
public class VendorAction extends BaseAction{

    @EJB
    private VendorBeanI vendorBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 5, Vendor.class, vendorBean.list(new Vendor()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        vendorBean.addOrUpdate(serializeForm(Vendor.class, req.getParameterMap()));

        resp.sendRedirect("./vendors");

    }
}
