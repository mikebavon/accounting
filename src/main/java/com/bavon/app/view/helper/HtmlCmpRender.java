package com.bavon.app.view.helper;

import com.bavon.app.bean.GenericCombo;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.inject.spi.CDI;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
            .append(htmlTable.addUrl()).append("\">Add</a>");

        for (int idx=0; idx<htmlTable.otherLinkBtn().length; idx++) {
            if (StringUtils.isBlank(htmlTable.otherLinkUrl()[idx]) || StringUtils.isBlank(htmlTable.otherLinkBtn()[idx]))
                continue;

            trBuilder
                .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                .append("<a class=\"link-btn-add\" href=\"")
                .append(htmlTable.otherLinkUrl()[idx])
                .append("\">")
                .append(htmlTable.otherLinkBtn()[idx])
                .append("</a>");

        }

        trBuilder.append("<br/><table><tr>");


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

            } else if (StringUtils.isNotBlank(formField.selectList())
                    && StringUtils.isNotBlank(formField.selectValue())
                    && StringUtils.isNotBlank(formField.selectDisplay())) {
                try {

                    htmlForm.append("<select")
                        .append(" id=\"").append(ifBlank(formField.id(), fieldName))
                        .append("\" name=\"").append(ifBlank(formField.name(), fieldName)).append("\" ")
                        .append(formField.required()?"required" : "")
                        .append(">");

                    GenericCombo genericCombo = CDI.current().select(GenericCombo.class).get();

                    Method selectListMethod = GenericCombo.class.getDeclaredMethod(formField.selectList());

                    List<?> options = (List<?>) selectListMethod.invoke(genericCombo);

                    for (Object option : options) {
                        Field valueField = formField.selectValueInSuper()?
                            option.getClass().getSuperclass().getDeclaredField(formField.selectValue()) :
                            option.getClass().getDeclaredField(formField.selectValue());
                        valueField.setAccessible(true);

                        Field displayField = formField.selectDisplayInSuper()?
                            option.getClass().getSuperclass().getDeclaredField(formField.selectDisplay()) :
                            option.getClass().getDeclaredField(formField.selectDisplay());
                        displayField.setAccessible(true);

                        htmlForm.append("htmlForm.append(<option value=\"")
                            .append(valueField.get(option)).append("\">")
                            .append(displayField.get(option)).append("</option>)");
                    }

                    htmlForm.append("</select>");
                } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
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
