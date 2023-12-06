package com.bavon.app.view.helper;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HtmlCmpRender implements Serializable {

    public static String table(List<?> dataList, Class<?> dataClass) {

        if (!dataClass.isAnnotationPresent(HtmlTable.class))
            return StringUtils.EMPTY;

        HtmlTable htmlTable = dataClass.getAnnotation(HtmlTable.class);

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<a class=\"link-btn-add\" href=\"")
            .append(htmlTable.addUrl()).append("\">Add</a><br/>")
            .append("<table><tr>");

        Field[] fields = dataClass.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlTableColHeader.class))
                continue;

            trBuilder.append("<th>")
                .append(field.getAnnotation(HtmlTableColHeader.class).header())
                .append("</th>");
        }

        trBuilder.append("</tr>");

        if (dataList != null && !dataList.isEmpty()){

            for (Object data : dataList) {

                trBuilder.append("<tr>");
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(HtmlTableColHeader.class))
                        continue;

                    try {
                        field.setAccessible(true);
                        HtmlTableColHeader colHeader = field.getAnnotation(HtmlTableColHeader.class);

                        Object colData;
                        if (StringUtils.isNotBlank(colHeader.dateFormat()))
                            colData = new SimpleDateFormat(colHeader.dateFormat())
                                .format(Optional.ofNullable((Date) field.get(data)).orElse(new Date()));
                        else if (StringUtils.isNotBlank(colHeader.numberFormat()))
                            colData = new DecimalFormat(colHeader.numberFormat())
                                .format(Optional.ofNullable(field.get(data)).orElse(BigDecimal.ZERO));
                        else
                            colData = field.get(data);

                        trBuilder.append("<td>")
                            .append(Optional.ofNullable(colData).orElse(""))
                            .append("</td>");

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);

                    }
                }

                trBuilder.append("<tr>");

            }
        }

        trBuilder.append("</table>");

        return trBuilder.toString();

    }

    public static String form(Class<?> model){

        HtmlForm htmlFormMarker = null;
        if (model.isAnnotationPresent(HtmlForm.class))
            htmlFormMarker = model.getAnnotation(HtmlForm.class);

        if (htmlFormMarker == null)
            return StringUtils.EMPTY;

        StringBuilder htmlForm = new StringBuilder("<br/><h3>Add " + htmlFormMarker.label() + "</h3><br/>" +
           "<form action=\"" + htmlFormMarker.url() + "\" method=\"" + htmlFormMarker.httpMethod() + "\">" +
           "<div class=\"container\">");

        Field [] fields = model.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlFormField.class))
                continue;

            HtmlFormField formField = field.getAnnotation(HtmlFormField.class);

            String fieldName = field.getName();

            htmlForm
                .append("<label for=\"").append(ifBlank(formField.labelFor(), fieldName)).append("\">")
                .append(ifBlank(formField.label(), fieldName))
                .append(formField.required()?"<span style=\"color:red;\">*</span> ":"")
                .append(":</label><br>");

            if (field.getType().isEnum()) {
                htmlForm.append("<select")
                    .append(" id=\"").append(ifBlank(formField.id(), fieldName))
                    .append("\" name=\"").append(ifBlank(formField.name(), fieldName)).append("\" ")
                    .append(formField.required()?"required" : "")
                    .append(">");

                for (Object enumValue : field.getType().getEnumConstants()){
                    //System.out.println(enumValue);

                    try {
                        Method method = field.getType().getMethod("getName");
                        htmlForm.append("htmlForm.append(<option value=\"")
                            .append(enumValue).append("\">")
                            .append(method.invoke(enumValue)).append("</option>)");
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }

                htmlForm.append("</select>");

            }/* if (StringUtils.isNotBlank(formField.loadList())) {

                List<Object> list = new ArrayList<>(); /// find a way of passing the list of customer

                htmlForm.append("<select")
                    .append(" id=\"").append(ifBlank(formField.id(), fieldName))
                    .append("\" name=\"").append(ifBlank(formField.name(), fieldName)).append("\" ")
                    .append(formField.required()?"required" : "")
                    .append(">");

                for (Object object : list){
                    //System.out.println(enumValue);

                    try {
                        Method method = field.getType().getMethod("getName");
                        htmlForm.append("htmlForm.append(<option value=\"")
                            .append(object.getId()).append("\">")
                            .append(object.getName).append("</option>)");
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }

                htmlForm.append("</select>");

            }*/ else {
                htmlForm.append("<input type=\"")
                    .append(formField.type())
                    .append("\" id=\"").append(ifBlank(formField.id(), fieldName))
                    .append("\" name=\"").append(ifBlank(formField.name(), fieldName)).append("\" ")
                    .append(formField.required()?"required" : "")
                    .append("><br>");
            }

        }

        htmlForm.append("<button type=\"submit\">Submit</button>");
        htmlForm.append("</div>" + "</form>" + "<br/>");

        return htmlForm.toString();

    }

    private static String ifBlank(String target, String alternative){
        return StringUtils.isBlank(target)? alternative : StringUtils.trimToEmpty(target);
    }

}
