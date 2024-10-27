package com.haruns.eticaretapp.utility;


import com.haruns.eticaretapp.service.ClothingProductService;
import com.haruns.eticaretapp.service.ComputerProductService;
import com.haruns.eticaretapp.service.MergedService;
import com.haruns.eticaretapp.service.PhoneProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductServiceMapper {
	private final Map<String, MergedService> serviceMap = new HashMap<>();
	
	@Autowired
	public ProductServiceMapper(ComputerProductService computerProductService,
	                            ClothingProductService clothingProductService,
	                            PhoneProductService phoneProductService) {
		serviceMap.put("computer", computerProductService);
		serviceMap.put("clothing", clothingProductService);
		serviceMap.put("phone", phoneProductService);
	}
	
	public MergedService getService(String type) {
		MergedService service = serviceMap.get(type.toLowerCase());
		if (service == null) {
			throw new IllegalArgumentException("Unknown product type: " + type);
		}
		return service;
	}
}