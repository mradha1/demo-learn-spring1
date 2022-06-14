package com.example.demo.eo;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.example.demo.vo.VendorResponseVO;

@Component
public class DatabaseEO extends BaseEO{
	
	public CompletableFuture<VendorResponseVO> saveData(VendorResponseVO vendorResponse){
		
		sleep(3);
		System.out.println("Inside saveData() method using "+ Thread.currentThread().getName());
		System.out.println("successfully saved the data for "
				+ "account number "+ vendorResponse.getVendorCode());
		return CompletableFuture.completedFuture(vendorResponse);
		
	}

}
