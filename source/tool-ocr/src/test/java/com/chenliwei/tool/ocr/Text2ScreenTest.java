package com.chenliwei.tool.ocr;

import org.junit.Test;

import com.chenliwei.tools.ocr.post.Text2Screen;

public class Text2ScreenTest {
	@Test
	public void test() throws InterruptedException{
		Text2Screen.postFileInterval("D:\\projects\\mytools\\source\\tool-ocr\\pom.xml", 1000);
	}
}
