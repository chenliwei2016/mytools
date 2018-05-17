package com.chenliwei.tools.ocr.post;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import com.chenliwei.tools.common.FileTools;
import com.chenliwei.tools.common.StringTools;

public class Text2Screen {

	 private static  Label label = new Label();
	 public static void prepare() throws InterruptedException {
		 //Rectangle rec = new Rectangle(new Point(0,26),new Dimension(500,26));
		  Dialog d = new Dialog(((Window)null),"");
		  d.setBounds(0, 26, 1800, 160);
		  //d.setBounds(rec);
		  d.add(label);
		  Font font = new Font("Arial_Bold", Font.BOLD,16);
		  label.setFont(font);
		  d.setVisible(true);
		 }
	 
	 public static void postFileInterval(String filePath, int interval) throws InterruptedException {
		 prepare();
		 label.setText("Will go in 10 seconds!");
		 Thread.sleep(10000);
		 DecimalFormat format = new DecimalFormat("[00000000]");
		 byte[] binData = FileTools.readBinaryFile(filePath);
		 int i=0,len=80,end=binData.length;
		 int times=1;
		 while(i+len<end) {
			 String text = format.format(times);
			 text += Hex.encodeHexString(Arrays.copyOfRange(binData, i, i+len));
			 label.setText(text);
			 times++;
			 Thread.sleep(interval);
			 i += len;
		 }
		 label.setText(Hex.encodeHexString(Arrays.copyOfRange(binData, i, end-1)));
		 Thread.sleep(interval);
		 label.setText("***Send Over");
	 }
	 
}
