package com.meowu.starter.common.commons.security.exception;

public class RSAException extends RuntimeException{

    public RSAException(){
        super();
    }

    public RSAException(String message){
        super(message);
    }

    public RSAException(Throwable cause){
        super(cause);
    }

    public RSAException(String message, Throwable cause){
        super(message, cause);
    }
}
