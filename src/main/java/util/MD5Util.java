package util;

import java.security.MessageDigest;

/*
 * MD5加密：属于不可逆加密，即加密之后的密文不能再被解密成明文
 */
public class MD5Util {

	//md5加密32位
	public static String encode(String verifyStr) {
		try {
			/*
			 * MessageDigest类为应用程序提供消息摘要算法的功能，如SHA-1或SHA-256。
			 * 消息摘要是安全的单向散列函数，它接受任意大小的数据并输出固定长度的散列值。
			 */
			//1 设置算法为MD5
			MessageDigest digest = MessageDigest.getInstance("md5");
			/*
			 * 使用指定的字节数组对摘要执行最终更新，然后完成摘要计算。
			 * 也就是说，该方法首先调用update（input），将输入数组传递给update方法，然后调用digest()
			 */
			//2 update进行加密编码，digest进行赋值
			byte[] result = digest.digest(verifyStr.getBytes("utf-8"));
			StringBuilder encStr = new StringBuilder();
			for (byte b : result) {
				//按位与操作，即b的二进制和0xFF的二进制按位比较，只有都为1时才返回1
				//0xff=1111 1111
				int number = b & 0xff;
				String hex = Integer.toHexString(number);
				if (hex.length() == 1) {
					encStr.append("0" + hex);
				} else {
					encStr.append(hex);
				}
			}
			//大写
//			String encVerify = encStr.toString().toUpperCase();
			//小写
			String encVerify = encStr.toString().toLowerCase();
			return encVerify;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
//	public static void main(String[] args) {
//		String str = encode("123");
//		
//		System.out.println(str);
//		System.out.println(str.length());
//	}
}
