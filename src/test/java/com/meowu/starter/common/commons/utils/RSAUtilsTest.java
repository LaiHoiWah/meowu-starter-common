package com.meowu.starter.common.commons.utils;

import org.junit.Test;

import java.util.Map;

public class RSAUtilsTest{

    @Test
    public void keyPair(){
        Map<String, String> keyMap = RSAUtils.generateKeyPair(RSAUtils.KEY_SIZE_4096);
        System.out.println("PublicKey:\n" + keyMap.get(RSAUtils.PUBLIC_KEY));
        System.out.println("Private:\n" + keyMap.get(RSAUtils.PRIVATE_KEY));
    }

    @Test
    public void encrypt(){
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA2f+Kc4moFkzcvA8amSWRA5MDQEs3AbVtRg6aqTOT/2Qooo+BXpvS0BlFyWZyQ8g9IT4wHQ6hQuxRkg8I0IY7ms5DLqZ1gCfduyouZmFskvOjdvpNv8irFVP2Ud5aTXiQA7seMeURmUrhSn7zxk+e0DkZN6HPkIiDnbFJ1IlpUIN/2tmmvFtbaPNjvbuWM8mdTmT+5AUchK1kA2KB5t5c4VkXfx5UXkAo/F4hiiaQIuvH2nbrgl2KTmmUBt5h7rnyJhyB+67Na/fV6H8qshsD53T1Sbknl9cwyJyqoeX42UZFZbchN9efC6tIihDvA/cbgghURfFuMZPjGCP2+EEw/OJIi8BBWu8UiqjSJ2Prm69qrY3CQTwJQC7CBxz8ZeWcQjqNm487zbl4aVhLCPlPc6iJxHUqSWQMF5fwv3S06dgFf2XwGsLBz6GEj9AFKsp1dHvFMU+avFJfsz0DXdaqTR+XEZIMr5Ps2lE8XDJxGzio/R0xTNIAPZWZpmm9x/ix67QzuOoqRofdS0y5AXrACHUtdFeuF9QxnG3Jc8T0YJ5v/IrpDbw3ElkOqUBig1Ie3MjIARYK5HCbU+dGTgjHZ3dIksR3zshlNGlIl1Od5JDQZGhsj4P2g/qdMRRPdLUk7N7qlbDIGlIySBF/tE7LwuHN6mhsV81Iw8YRlvqo1iUCAwEAAQ==";
        String context = "0829.RM%";

        String encrypt = RSAUtils.encryptToBase64String(
            RSAUtils.toPublicKey(publicKey),
            RSAUtils.KEY_SIZE_4096,
            RSAUtils.PADDING_MODE_OAEP_SHA256,
            context
        );

        System.out.println(encrypt);
    }

    @Test
    public void decrypt(){
        String privateKey = "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";
        String context = "doy2nLXcjfJFUfBr6hCRyCBpD0FKpmM2AP2cnFWyj9U7YWFvz0YoA3dEUSpTSX0ABFFIegKw3YdYfzsvyTLXs8tJ6fbNmb9EMUQFWUqd6hvaU4tedwLsiiHfJ1aMMU69jfTxSGa79strYnRzF5OL0cJ8zhUMv5JU4Hi/bLMezlb+xjqgbJLzXzsSKho0zkV+5wL0SJPUb6pACNGYOLDpAUSHMS4IrRGhVONpoKV32ngWBOB3iaKcIJL0/mDkZXTrxhGSZAvNAU7tfOj0AZQJpU/kjfm1KjVzDYEKtGTynM8igxbANSJBou9yU2Uk4wlpveLSXHKAWrHhoskvjzQtcIs5lEVq+T5GGuCl7RQ0KNRgvHYxOkYa6SlytoSY+pP6g9UrTZbrM7vfL2i4eZBq1hVii6Z98DWR2eF9grQuidQI9Nk4TUBhJ0s5VUexvsdHCaQDtwrJ773ZQ2KrTIAh93xqNKncx/NF9UNwtLEMuHsg6ycRdCmbPxpdNYvC97LYGB5BrKBbRgK8IxtRdCmI0C2LVZQXWFm8JLIEmqJRAV3GBU7TvDMDJ+pOStMJckl5m1VcReQ2DzEXGVSo+LNuf/ESSACqmtSD69DE+D+jjmKfb8O3vimhAmmmf6V0G9BvfojQ6J3exnIjKHjyXmb+idzT4ZDWvOZG3rnnJ9shP08=";

        String decrypt = RSAUtils.decryptBase64String(
            RSAUtils.toPrivateKey(privateKey),
            RSAUtils.KEY_SIZE_4096,
            RSAUtils.PADDING_MODE_OAEP_SHA256,
            context
        );

        System.out.println(decrypt);
    }
}
