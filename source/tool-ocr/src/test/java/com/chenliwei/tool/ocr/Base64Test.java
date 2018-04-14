package com.chenliwei.tool.ocr;

import java.util.Base64;

public class Base64Test {
	
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println("test Str : " + str);
        
        
        byte[] binaryData = str.getBytes();
        String encodeStr = Base64.getEncoder().encodeToString(binaryData);
        System.out.println("Base64.encode Result : " + encodeStr);
        
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr);
        String decodeStr = new String(decodeBytes);
        System.out.println("Base64.decode Result : " + decodeStr);
    }

}
