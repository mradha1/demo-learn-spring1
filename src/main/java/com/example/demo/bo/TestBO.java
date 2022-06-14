package com.example.demo.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import com.example.demo.eo.BankEO;
import com.example.demo.eo.DatabaseEO;
import com.example.demo.eo.PublishEO;
import com.example.demo.eo.VendorEO;
import com.example.demo.vo.BankRequestVO;
import com.example.demo.vo.VendorReqeustVO;
import com.example.demo.vo.VendorResponseVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class TestBO {
	private final BankEO bankEo;
	private final VendorEO vendorEo;
	private final DatabaseEO databaseEo;
	private final PublishEO publishEo;
	
	public TestBO(BankEO bankEo,VendorEO vendorEo,DatabaseEO databaseEo,PublishEO publishEo) {
		this.bankEo = bankEo;
		this.vendorEo = vendorEo;
		this.databaseEo = databaseEo;
		this.publishEo= publishEo;
	}
	
	public List<VendorResponseVO> invokeProcessing(){
		List<VendorResponseVO> vendorList= new ArrayList<>();
		List<String> accountNumList = getAccountNumList();
		List<CompletableFuture<VendorResponseVO>> voidList = new ArrayList<>();
		
		for(int i=0;i<accountNumList.size();i++) {
			BankRequestVO bankRequest = getBankRequest(accountNumList,i);
			voidList.add(
			bankEo.invokeBankService(bankRequest)
				.thenCompose(bankResponse -> 
					vendorEo.invokeVendorService(bankResponse,vendorList)
				).thenCompose(vendorReponse -> 
					databaseEo.saveData(vendorReponse))
				.exceptionally(throwable -> {
					System.out.println(""+throwable.getMessage());
					return null;
				}));
			
		}
		
		List<VendorResponseVO> vendorResponseList = voidList.stream().map(obj -> obj.join()).collect(Collectors.toList());
		
		
		vendorResponseList.stream().forEach(vendorResponse -> {
			publishEo.publishEvent(vendorResponse);
		});
		
		
		
		return vendorResponseList;
		
	}   
	
	private BankRequestVO getBankRequest(List<String> accountList,int i) {
		
		String s= (String)accountList.get(i);
		BankRequestVO bankRequestVO = new BankRequestVO();
		bankRequestVO.setAccountNumber(s);
		
		return bankRequestVO;
	}
	
	private List<String> getAccountNumList(){
		List<String> vendorCodeList = new ArrayList<>();
		vendorCodeList.add("A");
		vendorCodeList.add("B");
		vendorCodeList.add("C");
		
		return vendorCodeList;
	}


}
