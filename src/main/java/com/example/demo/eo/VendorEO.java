package com.example.demo.eo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.vo.BankRequestVO;
import com.example.demo.vo.BankResponseVO;
import com.example.demo.vo.VendorReqeustVO;
import com.example.demo.vo.VendorResponseVO;

@Component
public class VendorEO extends BaseEO{
	
	@Async	
	public CompletableFuture<VendorResponseVO> invokeVendorService(
			BankResponseVO bankResponseVO
			,List<VendorResponseVO> vendorList){
			
			sleep(3);
			System.out.println("Inside invokeVendorService() method using "+ Thread.currentThread().getName());
			VendorResponseVO vendorResponse = new VendorResponseVO();
			vendorResponse.setVendorCode(bankResponseVO.getVendorId());
			vendorList.add(vendorResponse);
			return CompletableFuture.completedFuture(vendorResponse);
		}

}
