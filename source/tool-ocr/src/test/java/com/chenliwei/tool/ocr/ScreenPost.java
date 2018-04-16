package com.chenliwei.tool.ocr;

	
	import java.awt.Dialog;
import java.awt.Font;
import java.awt.Label;
	import java.awt.Window;

	public class ScreenPost {

	 public static void main(String[] args) throws InterruptedException {
	  Dialog d = new Dialog(((Window)null),"");
	  d.setBounds(0, 0, 200, 200);
	  Label label = new Label();
	  d.add(label);
	  Font font = new Font("Courier", Font.BOLD,16);
	  label.setFont(font);
	  d.setVisible(true);
	  String s = "Hello World";
	  
	  for(int i =0; i<s.length(); i++){

		  label.setText(Character.toString(s.charAt(i)));
		  Thread.sleep(2000);
	  }
	 }
}