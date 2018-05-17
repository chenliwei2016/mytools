package com.chenliwei.tool.ocr;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import com.chenliwei.tools.ocr.client.Screen2Text;

public class Screen2TextTest {
	@Test
	public void test() throws AWTException, IOException, ParseException, DecoderException, InterruptedException {
		System.out.println(Screen2Text.read());//Screen2Text.extract();
	}
}
