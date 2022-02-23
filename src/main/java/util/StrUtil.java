package util;

public class StrUtil {

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if(obj.toString() == "" || obj.toString().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
//		String a = null;
//		a.isEmpty();
//		
//		StrUtil.isEmpty(a);
	}
}
