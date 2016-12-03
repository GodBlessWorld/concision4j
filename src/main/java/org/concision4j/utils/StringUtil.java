/**
 * 
 */
package org.concision4j.utils;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午2:40:46
 */
public class StringUtil {
	public static boolean isEmpty(String target){
		if (target == null || "".equals(target)) {
			return true;
		}
		return false;
	}
}
