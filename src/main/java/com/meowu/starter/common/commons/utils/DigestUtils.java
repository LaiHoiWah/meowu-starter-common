package com.meowu.starter.common.commons.utils;

import com.meowu.starter.common.commons.security.exception.DigestException;
import org.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.HexFormat;

public class DigestUtils{

    public final static String ALGORITHM_MD5      = "MD5";
    public final static String ALGORITHM_SHA_1    = "SHA-1";
    public final static String ALGORITHM_SHA_256  = "SHA-256";
    public final static String ALGORITHM_SHA_384  = "SHA-384";
    public final static String ALGORITHM_SHA_512  = "SHA-512";
    public final static String ALGORITHM_SHA3_256 = "SHA3-256";
    public final static String ALGORITHM_SHA3_512 = "SHA3-512";

    private DigestUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static String digestToHexString(String path, String algorithm){
        AssertUtils.isNotBlank(path, "DigestUtils: PATH must not be null");
        AssertUtils.isTrue(
            algorithm.equals(ALGORITHM_MD5) || algorithm.equals(ALGORITHM_SHA_1)
                || algorithm.equals(ALGORITHM_SHA_256) || algorithm.equals(ALGORITHM_SHA_384)
                || algorithm.equals(ALGORITHM_SHA_512) || algorithm.equals(ALGORITHM_SHA3_256)
                || algorithm.equals(ALGORITHM_SHA3_512),
            "DigestUtils: ALGORITHM[" + algorithm + "] is not support"
        );

        try(FileChannel channel = FileChannel.open(Path.of(path), StandardOpenOption.READ)){
            // digest
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // file information
            long fileLength = channel.size();
            long position   = 0L;
            long maxMapSize = 256 * 1024 * 1024;

            MappedByteBuffer buffer;
            while(position < fileLength){
                long mapSize = Math.min(maxMapSize, fileLength - position);
                // write into buffer
                buffer = channel.map(FileChannel.MapMode.READ_ONLY, position, mapSize);
                if(buffer.hasArray()){
                    digest.update(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
                }else{
                    byte[] byteBuffer = new byte[8192];
                    while(buffer.hasRemaining()){
                        int length = Math.min(buffer.remaining(), byteBuffer.length);
                        buffer.get(byteBuffer, 0, length);
                        digest.update(byteBuffer, 0, length);
                    }
                }
                // next position
                position += mapSize;
            }

            return HexFormat.of().withUpperCase().formatHex(digest.digest());
        }catch(Exception e){
            throw new DigestException(e.getMessage(), e);
        }
    }

    public static String digestToHexString(InputStream inputStream, String algorithm){
        AssertUtils.isNotNull(inputStream, "DigestUtils: INPUT_STREAM must not be null");
        AssertUtils.isTrue(
            algorithm.equals(ALGORITHM_MD5) || algorithm.equals(ALGORITHM_SHA_1)
                || algorithm.equals(ALGORITHM_SHA_256) || algorithm.equals(ALGORITHM_SHA_384)
                || algorithm.equals(ALGORITHM_SHA_512) || algorithm.equals(ALGORITHM_SHA3_256)
                || algorithm.equals(ALGORITHM_SHA3_512),
            "DigestUtils: ALGORITHM[" + algorithm + "] is not support"
        );

        try{
            // digest
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // file information
            byte[] buffer    = new byte[8192];
            int    bytesRead = -1;

            while((bytesRead = inputStream.read(buffer)) != -1){
                digest.update(buffer, 0, bytesRead);
            }

            return HexFormat.of().withUpperCase().formatHex(digest.digest());
        }catch(Exception e){
            throw new DigestException(e.getMessage(), e);
        }
    }
}
