package com.meowu.starter.common.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurationUtils{

    /**
     * y: year
     * m: month
     * d: day
     * h: hour
     * s: second
     */
    private final static String REGEX = "([-+]?)(\\d+)([ymdhs])";
    private final static String NEGATIVE_SIGN = "-";

    private DurationUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static Duration parse(String str){
        return parse(str, null);
    }

    public static Duration parse(String str, String defaultStr){
        if(StringUtils.isBlank(str) || !str.matches(REGEX)){
            if(StringUtils.isBlank(defaultStr) || !defaultStr.matches(REGEX)){
                throw new IllegalArgumentException("DurationUtils: duration string is valid");
            }
            // using default string
            str = defaultStr;
        }

        // parse
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()){
            String sign   = matcher.group(1);
            String number = matcher.group(2);
            String unit   = matcher.group(3);

            // to duration
            Duration duration = new Duration();
            duration.setAmount((NEGATIVE_SIGN.equals(sign) ? -1 : 1) * Integer.parseInt(number));

            switch(unit){
                case "y" -> duration.setUnit(DurationUnit.YEAR);
                case "m" -> duration.setUnit(DurationUnit.MONTH);
                case "d" -> duration.setUnit(DurationUnit.DAY);
                case "h" -> duration.setUnit(DurationUnit.HOUR);
                case "s" -> duration.setUnit(DurationUnit.SECOND);
            }

            return duration;
        }

        throw new IllegalArgumentException("DurationUtils: duration string is valid");
    }

    @Getter
    @Setter
    public static class Duration{

        private Integer amount;
        private DurationUnit unit;
    }

    public enum DurationUnit{

        YEAR,
        MONTH,
        DAY,
        HOUR,
        SECOND,

        ;
    }
}
