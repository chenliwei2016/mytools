package com.chenliwei.tool.ocr;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.chenliwei.tools.common.FileTools;

public class Base64Test {
	
	@Test 
    public void converter() {
    	//把二进制文件首先转化为base64可读字符串
    	byte[] binaryData = FileTools.readBinaryFile("C:\\Users\\leavy\\Downloads\\test.png");
        String encodeStr = Base64.getEncoder().encodeToString(binaryData);
        System.out.println("Base64.encode Result : " + encodeStr);
        
        //考虑到base64有大小写字母，使用16位hex试一下
        System.out.println("Hex.encode Result : " +  Hex.encodeHexString(binaryData));
        
        //再把base64字符串解码为二进制数组，通过文件流写回到文件
        byte[] decodeBytes = Base64.getDecoder().decode(encodeStr);
  		try(OutputStream stream = new FileOutputStream("C:\\Users\\leavy\\Downloads\\test_cp.png")) {

	        stream.write(decodeBytes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        System.out.println("Done.");
        //再次打开文件，发现文件一模一样
    }

}
