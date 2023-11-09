package com.bavon.app.action;

import com.bavon.app.model.Account;
import com.bavon.app.view.helper.HtmlCmpRender;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BaseAction extends HttpServlet {

    public void serializeForm(Object bean, Map<String, ? extends Object> requestMap){
        try {
            BeanUtils.populate(bean, requestMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderPage(HttpServletRequest request, HttpServletResponse response, int activeMenu, String content)
            throws ServletException, IOException {
        request.setAttribute("activeMenu", activeMenu);
        request.setAttribute("content", content);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/index.jsp");
        dispatcher.forward(request, response);
    }
}
