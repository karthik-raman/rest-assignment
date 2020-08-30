package com.test;

import org.apache.log4j.Logger;

import com.test.util.Configure;

public class BaseTest {
	
	protected Logger log = null;
	protected static boolean isInit = false;
	
	public BaseTest() {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void init() throws Exception {
		if(isInit) {
			return;
		}
		Configure configure = new Configure();
		isInit = true;
	}


}
