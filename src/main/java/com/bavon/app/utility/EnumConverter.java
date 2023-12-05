package com.bavon.app.utility;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;

public class EnumConverter extends AbstractConverter {

    @Override
    protected <T> T convertToType(Class<T> aClass, Object o) throws Throwable {
        String enumValName = (String) o;

        System.out.println("enum: " + enumValName);
        if (StringUtils.isBlank(enumValName))
            return null;

        Enum[] enumConstants = (Enum[]) aClass.getEnumConstants();

        for (Enum enumConstant : enumConstants) {
            if (enumConstant.name().equals(enumValName)) {
                return (T) enumConstant;
            }
        }

        throw new ConversionException(String.format("Failed to convert %s value to %s class", enumValName, aClass.toString()));
    }

    @Override
    protected Class<?> getDefaultType() {
        return Enum.class;
    }
}