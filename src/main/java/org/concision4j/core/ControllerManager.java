/**
 * 
 */
package org.concision4j.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.concision4j.annotation.RequestMapping;
import org.concision4j.utils.StringUtil;


/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午2:21:06
 */
public class ControllerManager {
	private static final Map<Request, Handle> requestHandle = new HashMap<Request, Handle>();
	
	static {
		Set<Class<?>> controllerClasses  = ClassManager.getControllerClasses();
		detectRequestMap(controllerClasses);
	}

	/**
	 * get request handler
	 */
	public static Map<Request, Handle> getRequestHandler(){
		return requestHandle;
	}
	
	/**
	 * 
	 */
	private static void detectRequestMap(Set<Class<?>> controllerClasses) {
		if (!CollectionUtils.isEmpty(controllerClasses)){
			Iterator<Class<?>> it = controllerClasses.iterator();
			while (it.hasNext()) {
				Class<?> classItem = it.next();
				StringBuffer methodName = new StringBuffer("");
				StringBuffer path = new StringBuffer("");
				if (classItem.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping requestMappingAnotation = classItem.getAnnotation(RequestMapping.class);
					String methodString = requestMappingAnotation.method();
					String pathString = requestMappingAnotation.value();
					if (!StringUtil.isEmpty(methodString) && checkMethod(methodString)) {
						methodName.append(methodString);
					}
					if (!StringUtil.isEmpty(pathString) && checkPath(pathString)) {
						path.append(pathString);
					}
					detectMethod(classItem,methodName,path);
				}else{
					detectMethod(classItem,methodName,path);
				}
			}
		}
	}

	/**
	 * @param classItem
	 * @param methodName
	 * @param path
	 */
	private static void detectMethod(Class<?> classItem,
			StringBuffer methodName, StringBuffer path) {	//list methods of controller
		Method methods[] = classItem.getMethods();
		if (methods != null && methods.length > 0) {
			for (Method methodItemMethod : methods) {
				if (methodItemMethod.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping requestMappingAnotation1 = methodItemMethod.getAnnotation(RequestMapping.class);
					String methodString1 = requestMappingAnotation1.method();
					String pathString1 = requestMappingAnotation1.value();
					if (!StringUtil.isEmpty(methodString1) && checkMethod(methodString1)) {
						if (methodName.length()<=0) {
							methodName.append(methodString1);
						}
					}
					if (!StringUtil.isEmpty(pathString1) && checkPath(pathString1)) {
						path.append(pathString1);
					}
					if (path.length() > 0) {
						Request request = new Request();
						request.setPath(path.toString());
						if (methodName.length()<=0) {
							request.setMethod("get");
						}
						Handle handle = new Handle();
						handle.setControllerClass(classItem);
						handle.setMethod(methodItemMethod);
						requestHandle.put(request, handle);
					}
				}
			}
		}}

	/**
	 * @param pathString
	 * @return
	 */
	private static boolean checkPath(String pathString) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param methodString
	 * @return
	 */
	private static boolean checkMethod(String methodString) {
		// TODO Auto-generated method stub
		return true;
	}
}
