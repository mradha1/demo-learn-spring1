package com.example.demo.eo;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.vo.BankResponseVO;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.vo.BankRequestVO;

@Slf4j
@Component
public class BankEO extends BaseEO{
	
@Async	
public CompletableFuture<BankResponseVO> invokeBankService(BankRequestVO bankRequestVO){
		
		sleep(3);
		System.out.println("Inside invokeBankService() method using "+ Thread.currentThread().getName());
		BankResponseVO bankResponseVO = new BankResponseVO();
		bankResponseVO.setVendorId(bankRequestVO.getAccountNumber());
		return CompletableFuture.completedFuture(bankResponseVO);
	}

}
