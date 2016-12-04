/**
 * 
 */
package org.concision4j.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午3:07:07
 */
public class View {
	/**
	 * view path
	 */
	private String viewPath;
	
	/**
	 * view data
	 */
	private Map<String, Object> model = new HashMap<String, Object>();
	
	public View addModel(String key,Object value){
		this.model.put(key, value);
		return this;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
}
