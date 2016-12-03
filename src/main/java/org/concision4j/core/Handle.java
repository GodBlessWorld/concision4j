/**
 * 
 */
package org.concision4j.core;

import java.lang.reflect.Method;


/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午2:19:10
 */
public class Handle {
	private Class<?> controllerClass;
	
	private Method method;

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((controllerClass == null) ? 0 : controllerClass.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Handle other = (Handle) obj;
		if (controllerClass == null) {
			if (other.controllerClass != null)
				return false;
		} else if (!controllerClass.equals(other.controllerClass))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		return true;
	}
	
	
	
}
