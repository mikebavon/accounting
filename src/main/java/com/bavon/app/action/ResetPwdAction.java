package com.bavon.app.action;

import com.bavon.app.bean.UserBeanI;
import com.bavon.app.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changepwd")
public class ResetPwdAction  extends BaseAction {

    @EJB
    UserBeanI userBean;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            userBean.changePwd(serializeForm(User.class, req.getParameterMap()));
        } catch (Exception ex){
            ex.printStackTrace();
        }

        resp.sendRedirect("./");


    }
}
