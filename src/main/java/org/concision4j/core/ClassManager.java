/**
 * 
 */
package org.concision4j.core;

import java.util.HashSet;
import java.util.Set;

import org.concision4j.annotation.Controller;
import org.concision4j.annotation.Service;
import org.concision4j.utils.ClassUtils;


/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-10-22 下午8:02:05
 * 
 * 
 * 该类默认读取classpath下concision4j.properties配置文件中的
 * "concision4j.web.base_package"
 */
public class ClassManager {
	private static Set<Class<?>> classes = new HashSet<Class<?>>();
	
	static {
		String base_package_name = ConfigManager.getBasePackage();
		classes = ClassUtils.getClassesByPackageName(base_package_name);
	}
	
	private static ClassManager instance = null;
	
	private ClassManager(){
		
	}
	
	public static ClassManager getInstance(){
		if (instance == null) {
			synchronized (ClassUtils.class) {
				if (instance == null) {
					instance = new ClassManager();
				}
			}
		}
		return instance;
	}
	
	/**
	 * get all controller 
	 */
	public static Set<Class<?>> getControllerClasses(){
		 Set<Class<?>> result = new HashSet<Class<?>>();
		 if (classes != null && classes.size() > 0) {
			 for (Class<?> item : classes) {
				 if (item.isAnnotationPresent(Controller.class)) {
					 result.add(item);
					
				}
			}
			
		}
		return result;
	}
	
	/**
	 * get all service 
	 */
	public static Set<Class<?>> getServiceClasses(){
		 Set<Class<?>> result = new HashSet<Class<?>>();
		 if (classes != null && classes.size() > 0) {
			 for (Class<?> item : classes) {
				 if (item.isAnnotationPresent(Service.class)) {
					 result.add(item);
					
				}
			}
			
		}
		return result;
	}
	
	
	public static Set<Class<?>> getAllClass(){
		return classes;
	}
}
