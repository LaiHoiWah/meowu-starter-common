package com.meowu.starter.common.commons.security.exception;

public class DigestException extends RuntimeException{

    public DigestException(){
        super();
    }

    public DigestException(String message){
        super(message);
    }

    public DigestException(Throwable cause){
        super(cause);
    }

    public DigestException(String message, Throwable cause){
        super(message, cause);
    }
}
