package org.concision4j.core;

import org.concision4j.constants.ConfigConstant;
import org.concision4j.utils.ConfigUtil;

/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-10-22 下午4:01:52
 */
public final class ConfigManager {
	
	public static void loadConfig(String path){
		ConfigUtil.loadConfig(path);
	}
	
	public static String getJDBCDriver(){
		return ConfigUtil.getString(ConfigConstant.JDBC_DRIVER,ConfigConstant.JDBC_DRIVER_DEFAULT_VALUE);
	}
	
	
	public static String getJDBCUrl(){
		return ConfigUtil.getString(ConfigConstant.JDBC_URL);
	}
	
	
	public static String getJDBCUsername(){
		return ConfigUtil.getString(ConfigConstant.JDBC_USERNAME);
	}
	
	
	public static String getJDBCPassword(){
		return ConfigUtil.getString(ConfigConstant.JDBC_PASSWORD);
	}
	
	
	public static String getBasePackage(){
		return ConfigUtil.getString(ConfigConstant.WEB_BASE_PACKAGE);
	}
	
	
	public static String getJspPath(){
		return ConfigUtil.getString(ConfigConstant.WEB_JSP_PATH,ConfigConstant.WEB_JSP_PATH_DEFAULT_VALUE);
	}
	
	
	public static String getAssetPath(){
		return ConfigUtil.getString(ConfigConstant.WEB_ASSET_PATH,ConfigConstant.WEB_ASSET_PATH_DEFAULT_VALUE);
	}
	
}
