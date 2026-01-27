package com.meowu.starter.common.commons.utils;


import org.apache.commons.lang3.StringUtils;

public class VersionUtils{

    private VersionUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static boolean equal(String target, String compare){
        if(StringUtils.isBlank(target) || StringUtils.isBlank(compare)){
            throw new IllegalArgumentException("Version string must not be null");
        }

        String[] targetArray  = target.split("\\.");
        String[] compareArray = compare.split("\\.");

        int minLength = Math.min(targetArray.length, compareArray.length);
        for(int i = 0; i < minLength; i++){
            int targetInt  = Integer.parseInt(StringUtils.strip(targetArray[i]));
            int compareInt = Integer.parseInt(StringUtils.strip(compareArray[i]));

            if(targetInt != compareInt){
                return false;
            }
        }

        return targetArray.length == compareArray.length;
    }

    public static boolean greaterThan(String target, String compare){
        if(StringUtils.isBlank(target) || StringUtils.isBlank(compare)){
            throw new IllegalArgumentException("Version string must not be null");
        }

        String[] targetArray  = target.split("\\.");
        String[] compareArray = compare.split("\\.");

        int minLength = Math.min(targetArray.length, compareArray.length);
        for(int i = 0; i < minLength; i++){
            int targetInt  = Integer.parseInt(StringUtils.strip(targetArray[i]));
            int compareInt = Integer.parseInt(StringUtils.strip(compareArray[i]));

            if(targetInt <= compareInt){
                return false;
            }
        }

        return targetArray.length > minLength;
    }

    public static boolean greaterThanOrEqual(String target, String compare){
        if(StringUtils.isBlank(target) || StringUtils.isBlank(compare)){
            throw new IllegalArgumentException("Version string must not be null");
        }

        String[] targetArray  = target.split("\\.");
        String[] compareArray = compare.split("\\.");

        int minLength = Math.min(targetArray.length, compareArray.length);
        for(int i = 0; i < minLength; i++){
            int targetInt  = Integer.parseInt(StringUtils.strip(targetArray[i]));
            int compareInt = Integer.parseInt(StringUtils.strip(compareArray[i]));

            if(targetInt < compareInt){
                return false;
            }
        }

        return targetArray.length >= compareArray.length;
    }

    public static boolean lessThan(String target, String compare){
        return !greaterThanOrEqual(target, compare);
    }

    public static boolean lessThanOrEqual(String target, String compare){
        return !greaterThan(target, compare);
    }
}
