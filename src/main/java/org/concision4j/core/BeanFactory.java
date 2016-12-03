/**
 * 
 */
package org.concision4j.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.concision4j.annotation.Injection;
import org.concision4j.utils.ReflectionUtils;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午1:26:12
 */
public class BeanFactory {
	
	/**
	 * bean map 
	 */
	private static Map<Class<?>, Object> BEAN_FACTORY = new HashMap<Class<?>, Object>(); 
	
	static{
		Set<Class<?>> allClasses = ClassManager.getAllClass();
		//instance all class
		for (Class<?> clazz : allClasses) {
			Object obj = ReflectionUtils.newInstance(clazz);
			BEAN_FACTORY.put(clazz, obj);
		}
		//inject dependcy
		injectDependcy();
	}
	
	/**
	 * get bean factory
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_FACTORY;
	}
	
	/**
	 * 
	 */
	private static void injectDependcy() {
		if (BEAN_FACTORY != null && BEAN_FACTORY.size() > 0) {
			for (Entry<Class<?>, Object> entry : BEAN_FACTORY.entrySet()) {
				Class<?> key = entry.getKey();
				Object value = entry.getValue();
				//get fields from class
				Field fields[] = key.getFields();
				if (!ArrayUtils.isEmpty(fields)) {
					for (Field field : fields) {
						if (field.isAnnotationPresent(Injection.class)) {
							Class<?> fieldClass = field.getClass();
							if (BEAN_FACTORY.containsKey(fieldClass)) {
								Object fieldObject = BEAN_FACTORY.get(fieldClass);
								if (fieldObject != null) {
									ReflectionUtils.setFiled(value, field, fieldObject);
								}
							}
						}
					}
				}
			}
			
		}
	}

	/**
	 * get instance by key
	 * @param <T>
	 */
	public static <T> T getInstanceByClass(Class<T> key){
		if (!BEAN_FACTORY.containsKey(key)) {
			throw new RuntimeException("Error: can not find class "+key);
		}
		return (T)BEAN_FACTORY.get(key);
	}
	
	
}
