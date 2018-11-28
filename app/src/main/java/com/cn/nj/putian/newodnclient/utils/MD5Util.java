package com.cn.nj.putian.newodnclient.utils;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 *
 * @author tfq
 * @datetime 2011-10-13
 */
public final class MD5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		String result = hexValue.toString().substring(8, 24);// md5 16bit
		// System.out.println("加密后的密码："+result);
		return result;

	}

	// 测试主函数
//	public static void main(String args[]) {
//		String s = new String("测试密码");
//		System.out.println("原始：" + s);
//		System.out.println("MD5后：" + string2MD5(s));
//		System.out.println("加密的：" + convertMD5(s));
//	}
}
