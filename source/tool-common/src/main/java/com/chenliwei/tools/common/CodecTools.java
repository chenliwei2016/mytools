package com.chenliwei.tools.common;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class CodecTools {
	
	/**
	 * 二进制数组转换成16进制的字符串
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b){
		return Hex.encodeHexString(b);
	}
	
	/**
	 * 16进制的字符串转换成2进制数组
	 * @param str
	 * @return
	 */
	public static byte[] toByteFromHexString (String str){
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] sha256(byte[] data){
		return DigestUtils.sha256(data);
	}
	
	public static byte[] md5(byte[] data){
		return DigestUtils.md5(data);
	}
	
	

}
