package com.example.demo.eo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.vo.VendorResponseVO;

@Component
public class PublishEO {
	
	@Async
	public void publishEvent(VendorResponseVO vendorResponseVO) {
		
	}

}
