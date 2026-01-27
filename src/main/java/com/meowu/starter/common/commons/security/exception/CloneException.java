package com.meowu.starter.common.commons.security.exception;

public class CloneException extends RuntimeException{

    public CloneException(){
        super();
    }

    public CloneException(String message){
        super(message);
    }

    public CloneException(Throwable cause){
        super(cause);
    }

    public CloneException(String message, Throwable cause){
        super(message, cause);
    }
}
