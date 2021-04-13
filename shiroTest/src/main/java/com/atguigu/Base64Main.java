package com.atguigu;

import org.apache.shiro.codec.Base64;

public class Base64Main {
    public static void main(String[] args) {
        String password = "AiwoYC1313";
        String encodeToString = Base64.encodeToString(password.getBytes());
        String decodeToString = Base64.decodeToString(encodeToString);
    }
}
