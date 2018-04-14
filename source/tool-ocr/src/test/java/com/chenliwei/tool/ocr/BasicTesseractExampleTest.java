package com.chenliwei.tool.ocr;

import org.bytedeco.javacpp.*;
import org.junit.Test;

import static org.bytedeco.javacpp.lept.*;
import static org.bytedeco.javacpp.tesseract.*;
import static org.junit.Assert.assertTrue;

public class BasicTesseractExampleTest {
    
    @Test
    public void givenTessBaseApi_whenImageOcrd_thenTextDisplayed() throws Exception {
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(".", "ENG") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        
        //传输文件使用base64把二进制转换为64个可见字符
        
       // pixReadMem();
        
        // Open input image with leptonica library
        PIX image = pixRead("C:\\Users\\leavy\\Downloads\\test.png");
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        String string = outText.getString();
        assertTrue(!string.isEmpty());
        System.out.println("OCR output:\n" + string);

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        api.close();
    }
}