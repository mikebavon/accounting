package com.bavon.app.view.helper;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class HtmlCmpRender implements Serializable {

    public static String table(List<? extends Object> models){

        if (models == null || models.isEmpty())
            return StringUtils.EMPTY;

        Field [] fields = models.get(0).getClass().getDeclaredFields();

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<table><tr>");

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlTableColHeader.class))
                continue;

            trBuilder.append("<th>" + field.getAnnotation(HtmlTableColHeader.class).header() + "</th>");
        }

        trBuilder.append("</tr>");

        for (Object model : models){

            trBuilder.append("<tr>");
            for (Field field : fields) {
                if (!field.isAnnotationPresent(HtmlTableColHeader.class))
                    continue;

                try {
                    field.setAccessible(true);
                    trBuilder.append("<td>").append(field.get(model)).append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            trBuilder.append("<tr>");

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

       String htmlForm =  "<h2>" +  htmlFormMarker.label() + "</h2>" +
        "<br/><h3>Add " + htmlFormMarker.label() + "</h3><br/>" +
           "<form action=\"" + htmlFormMarker.url() + "\" method=\"" + htmlFormMarker.httpMethod() + "\">" +
               "<div class=\"container\">";

        Field [] fields = model.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(HtmlFormField.class))
                continue;

            HtmlFormField formField = field.getAnnotation(HtmlFormField.class);

            String fieldName = field.getName();

            htmlForm += "<label for=\""
                    + (StringUtils.isBlank(formField.labelFor())?fieldName : formField.labelFor())
                + "\">"
                    + (StringUtils.isBlank(formField.label())?fieldName : formField.label()) + ":</label><br>";
            htmlForm += "<input type=\"text\" id=\""
                    + (StringUtils.isBlank(formField.id())?fieldName : formField.id())+ "\" name=\""
                    + (StringUtils.isBlank(formField.name())?fieldName : formField.name()) + "\" ><br>";
        }

        htmlForm += "<button type=\"submit\">Submit</button>";
        htmlForm += "</div>" +
            "</form>" +
            "<br/><hr/><br/>";

        return htmlForm;


    }
}
