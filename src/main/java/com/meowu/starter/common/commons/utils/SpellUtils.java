package com.meowu.starter.common.commons.utils;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

public class SpellUtils{

    private SpellUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static String toLowerCase(String str){
        return StringUtils.isNotBlank(str) ? str.toLowerCase() : str;
    }

    public static String toUpperCase(String str){
        return StringUtils.isNotBlank(str) ? str.toUpperCase() : str;
    }

    public static String toCamelCase(String str){
        return StringUtils.isNotBlank(str) ? CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str) : str;
    }

    public static String toUnderScore(String str){
        return StringUtils.isNotBlank(str) ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str) : str;
    }

    public static String capitalize(String str){
        return StringUtils.isNotBlank(str) ? StringUtils.capitalize(str) : str;
    }

    public static String uncapitalize(String str){
        return StringUtils.isNotBlank(str) ? StringUtils.uncapitalize(str) : str;
    }
}
