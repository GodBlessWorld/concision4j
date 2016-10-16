package org.concision4j;

import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigUtil {
	public static ResourceBundle  resourceBundle = ResourceBundle.getBundle(ConfigConstant.CONFIG_FILE);
	
	public static void loadConfig(String filePath){
		if (!StringUtils.isEmpty(filePath)) {
			resourceBundle = ResourceBundle.getBundle(filePath);
		}
	}
	
	public static String getString(String key,String defaultValue){
		if (resourceBundle.containsKey(key)) {
			return resourceBundle.getString(key);
		}
		return defaultValue;
	}
	
	public static String getString(String key){
		if (resourceBundle.containsKey(key)) {
			return resourceBundle.getString(key);
		}
		return null;
	}
	
}
