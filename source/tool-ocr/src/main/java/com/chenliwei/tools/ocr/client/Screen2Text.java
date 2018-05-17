package com.chenliwei.tools.ocr.client;

import static org.bytedeco.javacpp.lept.pixRead;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.lept.*;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class Screen2Text {
	private static Rectangle screenRect = new Rectangle(0, 100, 1800, 25);
	private static TessBaseAPI api = new TessBaseAPI();
	private static File temp = new File("source\\temp.png");
	private static DecimalFormat format = new DecimalFormat("[00000000]");
	public static String read() throws AWTException, IOException {
		api.Init(".", "ENG");
		BufferedImage capture = new Robot().createScreenCapture(screenRect);
		ImageIO.write(capture,"png", temp);
		PIX image = pixRead("source\\temp.png");
		api.SetImage(image);
        // Get OCR result
        BytePointer outText = api.GetBoxText(0);
		return outText.getString();
		
	}
	public static void extract() throws AWTException, IOException, ParseException, DecoderException, InterruptedException {
        if (api.Init(".", "ENG") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        
        boolean over = false;
        String encodedStr = "";
        int lineNumber = 0;
        while(!over) {
        	String readStr = read();
        	if(readStr.contains("go in")) {
        		Thread.sleep(1000);
        	} else {
	        	if((int) format.parse(readStr.substring(0, 10)) -1 == lineNumber) {
	        		lineNumber ++;
	        		encodedStr = readStr.substring(10);
	        		Thread.sleep(800);
	        	}
        	}
        	if(readStr.equals("***Send Over")) {
        		over = true;
        		char[] data =encodedStr.toCharArray() ;
				String decodeStr= Hex.decodeHex(data).toString();
				System.out.println(decodeStr);
        	}
        }
        
	}
}
