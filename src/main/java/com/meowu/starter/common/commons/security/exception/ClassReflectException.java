package com.meowu.starter.common.commons.security.exception;

public class ClassReflectException extends RuntimeException{

    public ClassReflectException(){
        super();
    }

    public ClassReflectException(String message){
        super(message);
    }

    public ClassReflectException(Throwable cause){
        super(cause);
    }

    public ClassReflectException(String message, Throwable cause){
        super(message, cause);
    }
}
