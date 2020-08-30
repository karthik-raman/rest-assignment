package com.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class CommonUtil {
	
	public static Properties loadProperties(String path) throws Exception {
		Properties prop = new Properties();
		FileInputStream fis =new FileInputStream(getPropertyFile(path));
		prop.load(fis);
		fis.close();
		return prop;
	}
	
	
	public static File getPropertyFile(String path) throws Exception {
		URL url = path.getClass().getResource(path);
		 return  new File(url.toURI());
	}

}
