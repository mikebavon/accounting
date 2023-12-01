package com.bavon.app.action;

import com.bavon.app.bean.AuthBeanI;
import com.bavon.app.model.User;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginAction extends BaseAction {

    @EJB
    AuthBeanI authBean;

    @Inject
    private double buildNumber;

    @Inject
    private List<String> developers;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId")))
            resp.sendRedirect("./home");
        else
            resp.sendRedirect("./");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        User loginUser  = serializeForm(User.class, req.getParameterMap());

        try {
            User userDetails = authBean.authenticate(loginUser);

            if (userDetails != null && StringUtils.isNotBlank(userDetails.getUsername())) {
                HttpSession httpSession = req.getSession(true);

                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", userDetails.getUsername());
                System.out.println("The build number is " + buildNumber);

                System.out.println("This application developers are:");
                for (String developer : developers)
                    System.out.println(developer);

                resp.sendRedirect("./home");

            }

            PrintWriter print = resp.getWriter();
            print.write("<html><body>Invalid login details <a href=\".\"> Login again </a></body></html>");
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
