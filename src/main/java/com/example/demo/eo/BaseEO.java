package com.example.demo.eo;

import java.util.concurrent.TimeUnit;

public class BaseEO {
	
	public void sleep(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
			
		}catch(Exception e) {
			
		}
	}

}
