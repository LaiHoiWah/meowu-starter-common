package com.meowu.starter.common.commons.utils;

import com.google.common.collect.Maps;
import com.meowu.starter.common.commons.security.exception.RSAException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class RSAUtils{

    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_SIGN = "SHA256withRSA";

    public static final String PADDING_MODE_PKCS_1      = "RSA/ECB/PKCS1Padding";
    public static final String PADDING_MODE_OAEP        = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
    public static final String PADDING_MODE_OAEP_SHA256 = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    public static final int KEY_SIZE_512  = 512;
    public static final int KEY_SIZE_1024 = 1024;
    public static final int KEY_SIZE_2048 = 2048;
    public static final int KEY_SIZE_4096 = 4096;

    public static final String PRIVATE_KEY = "PRIVATE_KEY";
    public static final String PUBLIC_KEY  = "PUBLIC_KEY";

    private RSAUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static Map<String, String> generateKeyPair(int keySize){
        AssertUtils.isTrue(
            keySize == KEY_SIZE_512 || keySize == KEY_SIZE_1024 || keySize == KEY_SIZE_2048 || keySize == KEY_SIZE_4096,
            "RSAUtils: KEY_SIZE can be only 512, 1024, 2048 or 4096"
        );

        try{
            // key generator
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            generator.initialize(keySize, new SecureRandom());

            // key pair
            KeyPair keyPair = generator.generateKeyPair();

            // get key and encode to base64
            RSAPublicKey  publicKey  = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, String> keyMap = Maps.newHashMap();
            keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
            keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
            return keyMap;
        }catch(Exception e){
            throw new RSAException(e.getMessage(), e);
        }
    }

    public static RSAPublicKey toPublicKey(String encode){
        AssertUtils.isNotBlank(encode, "RSAUtils: PUBLIC_KEY base64 encode string must not be null");

        try{
            byte[] keyBytes = Base64.decodeBase64(encode);

            // generate key
            X509EncodedKeySpec keySpec    = new X509EncodedKeySpec(keyBytes);
            KeyFactory         keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);

            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        }catch(Exception e){
            throw new RSAException(e.getMessage(), e);
        }
    }

    public static RSAPrivateKey toPrivateKey(String encode){
        AssertUtils.isNotBlank(encode, "RSAUtils: PRIVATE_KEY base64 encode string must not be null");

        try{
            byte[] keyBytes = Base64.decodeBase64(encode);

            // generate key
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory         keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);

            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        }catch(Exception e){
            throw new RSAException(e.getMessage(), e);
        }
    }

    public static String encryptToBase64String(Key key, int keySize, String paddingMode, String data){
        return Base64.encodeBase64String(encrypt(key, keySize, paddingMode, data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decryptBase64String(Key key, int keySize, String paddingMode, String base64Data){
        return new String(decrypt(key, keySize, paddingMode, Base64.decodeBase64(base64Data)), StandardCharsets.UTF_8);
    }

    private static byte[] encrypt(Key key, int keySize, String paddingMode, byte[] data){
        AssertUtils.isNotNull(key, "RSAUtils: encrypt key must not be null");
        AssertUtils.isNotBlank(paddingMode, "RSAUtils: padding mode must not be null");
        AssertUtils.isNotEmpty(data, "RSAUtils: encrypt data must not be null");

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
            // cipher
            Cipher cipher = Cipher.getInstance(paddingMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            int blockSize  = getEncryptBlockSize(keySize, paddingMode);
            int dataLength = data.length;
            int offset     = 0;

            // encrypt by blocks
            while(dataLength - offset > 0){
                int    length  = Math.min(dataLength - offset, blockSize);
                byte[] encrypt = cipher.doFinal(data, offset, length);

                stream.write(encrypt);
                offset += length;
            }
            // result
            return stream.toByteArray();
        }catch(Exception e){
            throw new RSAException(e.getMessage(), e);
        }
    }

    private static byte[] decrypt(Key key, int keySize, String paddingMode, byte[] data){
        AssertUtils.isNotNull(key, "RSAUtils: decrypt key must not be null");
        AssertUtils.isNotBlank(paddingMode, "RSAUtils: padding mode must not be null");
        AssertUtils.isNotEmpty(data, "RSAUtils: decrypt data must not be null");

        try(ByteArrayOutputStream stream = new ByteArrayOutputStream()){
            // cipher
            Cipher cipher = Cipher.getInstance(paddingMode);
            cipher.init(Cipher.DECRYPT_MODE, key);

            int blockSize  = getDecryptBlockSize(keySize);
            int dataLength = data.length;
            int offset     = 0;

            // encrypt by blocks
            while(dataLength - offset > 0){
                int    length  = Math.min(dataLength - offset, blockSize);
                byte[] decrypt = cipher.doFinal(data, offset, length);

                stream.write(decrypt);
                offset += length;
            }
            // result
            return stream.toByteArray();
        }catch(Exception e){
            throw new RSAException(e.getMessage(), e);
        }
    }

    private static int getEncryptBlockSize(int keySize, String paddingMode){
        AssertUtils.isTrue(
            keySize == KEY_SIZE_512 || keySize == KEY_SIZE_1024 || keySize == KEY_SIZE_2048 || keySize == KEY_SIZE_4096,
            "RSAUtils: KEY_SIZE can be only " + KEY_SIZE_512 + ", " + KEY_SIZE_1024 + ", " + KEY_SIZE_2048 + " or " + KEY_SIZE_4096
        );
        AssertUtils.isTrue(
            PADDING_MODE_PKCS_1.equals(paddingMode) || PADDING_MODE_OAEP.equals(paddingMode) || PADDING_MODE_OAEP_SHA256.equals(paddingMode),
            "RSAUtils: padding mode can be only " + PADDING_MODE_PKCS_1 + ", " + PADDING_MODE_OAEP + " or " + PADDING_MODE_OAEP_SHA256
        );

        return switch(paddingMode){
            case PADDING_MODE_PKCS_1 -> keySize / 8 - 11;
            case PADDING_MODE_OAEP -> keySize / 8 - 42;
            case PADDING_MODE_OAEP_SHA256 -> keySize / 8 - 66;
            default -> throw new IllegalArgumentException("RSAUtils: unknown padding mode");
        };
    }

    private static int getDecryptBlockSize(int keySize){
        AssertUtils.isTrue(
            keySize == KEY_SIZE_512 || keySize == KEY_SIZE_1024 || keySize == KEY_SIZE_2048 || keySize == KEY_SIZE_4096,
            "RSAUtils: KEY_SIZE can be only " + KEY_SIZE_512 + ", " + KEY_SIZE_1024 + ", " + KEY_SIZE_2048 + " or " + KEY_SIZE_4096
        );

        return keySize / 8;
    }
}
