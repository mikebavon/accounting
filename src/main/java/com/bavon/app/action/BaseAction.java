package com.bavon.app.action;

import com.bavon.app.utility.EnumConverter;
import com.bavon.app.view.helper.HtmlCmpRender;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseAction extends HttpServlet {

    @SuppressWarnings("unchecked")
    public <T> T serializeFormBk(Class<?> clazz, Map<String, String[]> requestMap) {

        T clazzInstance;

        try {
            clazzInstance = (T) clazz.getDeclaredConstructor().newInstance();

            DateConverter converter = new DateConverter( null );
            converter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(converter, Date.class);
            ConvertUtils.register(new EnumConverter(), Date.class);

            BeanUtils.populate(clazzInstance, requestMap);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e ) {
            throw new RuntimeException(e);
        }

        return clazzInstance;
    }

    @SuppressWarnings("unchecked")
    public <T> T serializeForm(Class<?> clazz, Map<String, String[]> requestMap) {

        T clazzInstance;

        try {
            clazzInstance = (T) clazz.getDeclaredConstructor().newInstance();

            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
                @Override
                public Object convert(String value, Class clazz) {
                    if (clazz.isEnum()) {
                        return Enum.valueOf(clazz, value);
                    } else {
                        return super.convert(value, clazz);
                    }
                }
            });

            ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);

            DateConverter converter = new DateConverter( null );
            converter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(converter, Date.class);

            beanUtilsBean.populate(clazzInstance, requestMap);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e ) {
            throw new RuntimeException(e);
        }

        return clazzInstance;
    }

    public void renderPage(HttpServletRequest request, HttpServletResponse response, int activeMenu,
        Class<?> entity, List<?> entityList)
            throws ServletException, IOException {

        request.setAttribute("activeMenu", activeMenu);

        if (StringUtils.trimToEmpty(request.getParameter("action")).equals("add"))
            request.setAttribute("content", HtmlCmpRender.form(entity));
        else
            request.setAttribute("content", HtmlCmpRender.table(entityList, entity));

        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/index.jsp");
        dispatcher.forward(request, response);
    }
}
