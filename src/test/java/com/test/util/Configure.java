package com.test.util;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

public class Configure {
	
	private static String path;
	private static Properties prop;
	private static boolean isInit = false;
	private static String logPath;
	
	public Configure() throws Exception {
		if(isInit) {
			return;
		}
		init();
	}
	
	
	private void init() throws Exception {
		Configure.path = "/config.properties";
		prop = CommonUtil.loadProperties(path);
		if(prop == null) {
			throw new RuntimeException("Unable to load property file: "+Configure.path);
		}
		initLog4j();
		logPath = "logs"+ File.separator;
		isInit = true;
	}
	
	private static void initLog4j() throws Exception {
		File file = CommonUtil.getPropertyFile("/log4j.xml");
		DOMConfigurator.configure(file.getAbsolutePath());
	}
	
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	

}
