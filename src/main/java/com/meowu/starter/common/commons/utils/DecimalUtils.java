package com.meowu.starter.common.commons.utils;

public class DecimalUtils{

    private static final char[] CHARACTERS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };

    private static final int MIN_RADIX = 2;
    private static final int MAX_RADIX = CHARACTERS.length;

    private DecimalUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    // to binary
    public static String toBinary(long decimal){
        return toString(decimal, 2);
    }

    // to octal
    public static String toOctal(long decimal){
        return toString(decimal, 8);
    }

    // to decimal
    public static String toDecimal(long decimal){
        return toString(decimal, 10);
    }

    // to hex
    public static String toHex(long decimal){
        return toString(decimal, 16);
    }

    // to base-26
    public static String toBase26(long decimal){
        return toString(decimal, 26);
    }

    // to base-32
    public static String toBase32(long decimal){
        return toString(decimal, 32);
    }

    // to base-36
    public static String toBase36(long decimal){
        return toString(decimal, 36);
    }

    // to base-62
    public static String toBase62(long decimal){
        return toString(decimal, 62);
    }

    // to string
    private static String toString(long decimal, int radix){
        if(radix < MIN_RADIX || radix > MAX_RADIX){
            throw new IllegalArgumentException("Radix must be less than " + MAX_RADIX + " and greater than " + MIN_RADIX);
        }

        if(radix == 10){
            return String.valueOf(decimal);
        }else{
            boolean negative = (decimal < 0);
            // force conversion to positive number
            if(!negative){
                decimal = -decimal;
            }
            // result
            StringBuilder sb = new StringBuilder();

            while(decimal <= -radix){
                sb.insert(0, CHARACTERS[(int) (-(decimal % radix))]);
                decimal = decimal / radix;
            }
            sb.insert(0, CHARACTERS[(int) (-decimal)]);

            if(negative){
                sb.insert(0, '-');
            }

            return sb.toString();
        }
    }
}
