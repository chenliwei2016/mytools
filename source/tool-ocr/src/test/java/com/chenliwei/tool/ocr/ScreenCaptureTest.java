package com.chenliwei.tool.ocr;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ScreenCaptureTest {

	@Test
	public void capture() throws IOException, AWTException{
		//首先在屏幕取得一个矩形
		Rectangle screenRect = null;
		//screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		//现在可以把这个矩形变小，固定位置，这个矩形可以由一个起点的x y坐标，再加上长度和宽度，抽象为一个Dimension
		Point p = new Point(0,26);
		Dimension d = new Dimension(250,26);
		screenRect = new Rectangle(p,d);
		BufferedImage capture = new Robot().createScreenCapture(screenRect);
		ImageIO.write(capture, "bmp", new File("C:\\Users\\leavy\\Downloads\\screenshot.png"));
	}
	
}
