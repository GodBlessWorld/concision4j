/**
 * 
 */
package org.concision4j.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-10-22 下午8:21:51
 */
public final class ReflectionUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
	
	public static Object newInstance(Class<?> clazz){
		Object object = null;
		try {
			object = clazz.newInstance();
		} catch (InstantiationException e) {
			logger.error("get Instance error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			logger.error("get Instance error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		} 
		return object;
	}
	
	
	public static Object invokeMethod(Object target,Method method,Object... objects){
		Object object = null;
		try {
			method.setAccessible(true);
			object = method.invoke(target, objects);
		} catch (IllegalAccessException e) {
			logger.error("invoke method error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			logger.error("invoke method error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			logger.error("invoke method error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		}
		return object;
		
	}
	
	
	public static void setFiled(Object target,Field field,Object value){
		try {
			field.setAccessible(true);
			field.set(target, value);
		} catch (IllegalArgumentException e) {
			logger.error("set field error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			logger.error("set field error,message : {}",e.getMessage());
			throw new RuntimeException(e);
		}
		
	}
}
