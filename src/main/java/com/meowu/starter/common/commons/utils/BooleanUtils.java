package com.meowu.starter.common.commons.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class BooleanUtils{

    private final static List<String> TRUE_STRING_ARRAY  = Lists.newArrayList("Y", "YES", "TRUE");
    private final static List<String> FALSE_STRING_ARRAY = Lists.newArrayList("N", "NO", "FALSE");

    private BooleanUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static boolean isTrue(Boolean expression){
        return Objects.nonNull(expression) ? expression : false;
    }

    public static boolean isFalse(Boolean expression){
        return Objects.nonNull(expression) && !expression;
    }

    public static Boolean toBoolean(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }

        if(TRUE_STRING_ARRAY.contains(str.toUpperCase())){
            return Boolean.TRUE;
        }else if(FALSE_STRING_ARRAY.contains(str.toUpperCase())){
            return Boolean.FALSE;
        }else{
            return null;
        }
    }

    public static Boolean toTrue(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }

        return TRUE_STRING_ARRAY.contains(str.toUpperCase()) ? Boolean.TRUE : null;
    }

    public static Boolean toFalse(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }

        return FALSE_STRING_ARRAY.contains(str.toUpperCase()) ? Boolean.FALSE : null;
    }
}
