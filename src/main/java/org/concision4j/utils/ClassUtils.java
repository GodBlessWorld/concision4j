package org.concision4j.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Dongdong.Su
 * 
 * @email 13233054232@163.com
 * 2016-10-22 下午4:01:52
 */
public final class ClassUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassUtils.class);
	
	
	/**
	 * get ClassLoader
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * load class by class name
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className,isInitialized,getClassLoader());
		} catch (ClassNotFoundException e) {
			logger.error("load class error,message: {}",e.getMessage());
			throw new RuntimeException(e);
		}
		return clazz;
		
	}
	
	/**
	 * get class on the specified package name
	 */
	public static Set<Class<?>> getClassesByPackageName(String packageName){
		Set<Class<?>> classSets = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			if (urls != null) {
				while (urls.hasMoreElements()) {
					URL url = (URL) urls.nextElement();
					if (url != null) {
						String protocol = url.getProtocol();
						if ("file".equals(protocol)) {
							String packagePath = url.getPath().replaceAll("%20", "");
							addClass(classSets,packagePath,packageName);
						}else if ("jar".equals(protocol)) {
							JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
							if (jarURLConnection != null) {
								JarFile jarFile = jarURLConnection.getJarFile();								
								if (jarFile != null) {
									Enumeration<JarEntry> jarEntrys = jarFile.entries();
									if (jarEntrys != null) {
										while (jarEntrys.hasMoreElements()) {
											JarEntry jarEntry = (JarEntry) jarEntrys
													.nextElement();
											String name = jarEntry.getName();
											if (name.endsWith(".class")) {
												String className = name.substring(0,name.lastIndexOf(".class")).replace("/", ".");
												doAddClass(classSets,className);
											}
											
										}
										
									}
									
								}
							}
						}
					}
					
				}
			}
		} catch (IOException e) {
			logger.error("load class error,message: {}",e.getMessage());
			throw new RuntimeException(e);
		}
		
		return classSets;
	}

	/**
	 * @param classSets
	 * @param packagePath
	 * @param packageName
	 */
	private static void addClass(Set<Class<?>> classSets, String packagePath,
			String packageName) {
		File files[] = new File(packagePath).listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		
		
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isFile()) {
					String className = packageName+"."+(file.getName().substring(0,file.getName().lastIndexOf(".class")));
					doAddClass(classSets, className);
				} else {
					String fileName = file.getName();
					String subPackagePath = packagePath + "/" + fileName;
					String subPackageName = packageName + "." + fileName;
					addClass(classSets, subPackagePath, subPackageName);
				}
			}
		}
		
		
	}

	/**
	 * @param classSets
	 * @param className
	 */
	private static void doAddClass(Set<Class<?>> classSets, String className) {
			Class<?> clazz = loadClass(className, false);
			classSets.add(clazz);
	}
	
}
