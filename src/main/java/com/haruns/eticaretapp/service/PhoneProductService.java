package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.PhoneProduct;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.PhoneProductRepository;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneProductService {
	private final PhoneProductRepository phoneProductRepository;
	
	public void addProduct(AddProductRequestDto dto, Long sellerId) {
		if (dto.getCategoryId() == 3 && dto.getPhoneCamera() == null && dto.getPhoneStorage() == null && dto.getPhoneScreen() == null  && dto.getPhoneCPU() == null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		PhoneProduct phoneProduct =
				PhoneProduct.builder().name(dto.getName()).description(dto.getDescription()).brand(dto.getBrand())
				            .categoryId(dto.getCategoryId())
				            .status(ProductStatus.PENDING)
				            .sellerId(sellerId)
				            .cpu(dto.getPhoneCPU())
				            .camera(dto.getPhoneCamera())
				            .screen(dto.getPhoneScreen())
				            .storage(dto.getPhoneStorage())
				            .build();
		phoneProductRepository.save(phoneProduct);
		phoneProduct.setCode(ProductCodeGenerator.generateProductCode(phoneProduct));
		
	}
}