package com.bavon.app.action;

import com.bavon.app.bean.UserBean;
import com.bavon.app.bean.UserBeanI;
import com.bavon.app.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserAction extends BaseAction {

    UserBeanI userBean = new UserBean();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User registerUser = new User();
        serializeForm(registerUser, req.getParameterMap());

        userBean.register(registerUser);

        resp.sendRedirect("./");


    }
}
