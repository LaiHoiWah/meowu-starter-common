package com.meowu.starter.common.commons.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class AssertUtils{

    private AssertUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static void isTrue(boolean expression, String message){
        if(!expression){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean expression, String message){
        if(expression){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotBlank(String str, String message){
        if(StringUtils.isBlank(str)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isBlank(String str, String message){
        if(StringUtils.isNotBlank(str)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotNull(Object object, String message){
        if(Objects.isNull(object)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, String message){
        if(Objects.nonNull(object)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <E> void isNotEmpty(Collection<E> collection, String message){
        if(CollectionUtils.isEmpty(collection)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <E> void isEmpty(Collection<E> collection, String message){
        if(CollectionUtils.isNotEmpty(collection)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <K, V> void isNotEmpty(Map<K, V> map, String message){
        if(MapUtils.isEmpty(map)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <K, V> void isEmpty(Map<K, V> map, String message){
        if(MapUtils.isNotEmpty(map)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void isNotEmpty(T[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void isEmpty(T[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(char[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(char[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(byte[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(byte[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(short[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(short[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(int[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(int[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(long[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(long[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNotEmpty(boolean[] array, String message){
        if(ArrayUtils.isEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmpty(boolean[] array, String message){
        if(ArrayUtils.isNotEmpty(array)){
            throw new IllegalArgumentException(message);
        }
    }
}
