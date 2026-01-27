package com.meowu.starter.common.commons.utils;

import com.meowu.starter.common.commons.security.exception.ClassReflectException;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class ClassReflectUtils{

    private ClassReflectUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static Class<?> getClassByName(String className){
        AssertUtils.isNotBlank(className, "Class: CLASS_NAME must not be null");

        try{
            return Class.forName(className);
        }catch(Exception e){
            throw new ClassReflectException(e.getMessage(), e);
        }
    }

    public static <T> T newInstance(Class<T> typeOf){
        return newInstance(typeOf, null, null);
    }

    public static <T> T newInstance(Class<T> typeOf, List<Class<?>> paramTypes, List<Object> args){
        try{
            Class<?>[] paramTypeArray = CollectionUtils.isNotEmpty(paramTypes) ? paramTypes.toArray(Class[]::new) : null;
            Object[]   argArray       = CollectionUtils.isNotEmpty(args) ? args.toArray(Object[]::new) : null;

            return typeOf.getDeclaredConstructor(paramTypeArray).newInstance(argArray);
        }catch(Exception e){
            throw new ClassReflectException(e.getMessage(), e);
        }
    }
}
