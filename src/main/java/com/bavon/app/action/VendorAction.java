package com.bavon.app.action;

import com.bavon.app.bean.VendorBean;
import com.bavon.app.bean.VendorBeanI;
import com.bavon.app.model.Vendor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vendors")
public class VendorAction extends BaseAction{

    private final VendorBeanI vendorBean = new VendorBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 5, Vendor.class, vendorBean.list(Vendor.class));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        vendorBean.addOrUpdateAccount(serializeForm(Vendor.class, req.getParameterMap()));

        resp.sendRedirect("./vendors");

    }
}
