package com.meowu.starter.common.commons.utils;

import com.meowu.starter.common.commons.security.exception.CloneException;

import java.io.*;
import java.util.Objects;

public class CloneUtils{

    private CloneUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    @SuppressWarnings("unchecked")
    public static <T> T clone(T source){
        ByteArrayOutputStream arrayOutStream  = null;
        ObjectOutputStream    objectOutStream = null;
        ByteArrayInputStream  arrayInStream   = null;
        ObjectInputStream     objectInStream  = null;

        try{
            // write
            arrayOutStream  = new ByteArrayOutputStream();
            objectOutStream = new ObjectOutputStream(arrayOutStream);
            objectOutStream.writeObject(source);

            // read
            arrayInStream  = new ByteArrayInputStream(arrayOutStream.toByteArray());
            objectInStream = new ObjectInputStream(arrayInStream);
            return (T) objectInStream.readObject();
        }catch(IOException | ClassNotFoundException e){
            throw new CloneException(e.getMessage(), e);
        }finally{
            close(arrayOutStream);
            close(objectOutStream);
            close(arrayInStream);
            close(objectInStream);
        }
    }

    private static void close(Closeable closeable){
        try{
            if(Objects.nonNull(closeable)){
                closeable.close();
            }
        }catch(Exception e){
            // ignore exception
        }
    }
}
