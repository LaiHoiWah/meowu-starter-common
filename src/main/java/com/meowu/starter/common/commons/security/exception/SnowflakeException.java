package com.meowu.starter.common.commons.security.exception;

public class SnowflakeException extends RuntimeException{

    public SnowflakeException(){
        super();
    }

    public SnowflakeException(String message){
        super(message);
    }

    public SnowflakeException(Throwable cause){
        super(cause);
    }

    public SnowflakeException(String message, Throwable cause){
        super(message, cause);
    }
}
