/**
 * 
 */
package org.concision4j.core;

import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-12-3 下午5:00:22
 */
public class Param {
	private Map<String, Object> valueMap = new HashMap<String, Object>();
	
	/**
	 * 
	 */
	public Param() {
		// TODO Auto-generated constructor stub
	}
	
	public Param(Map<String, Object> valueMap){
		this.valueMap = valueMap;
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}
	
	
}
