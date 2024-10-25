package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ComputerProductRepository;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerProductService {
	private final ComputerProductRepository computerProductRepository;
	public void addProduct(AddProductRequestDto dto, Long sellerId) {
		if(dto.getCategoryId()==1 && dto.getComputerRam()==null || dto.getComputerCPU()==null || dto.getComputerGPU()==null || dto.getComputerMotherboard()==null || dto.getComputerScreenSize()==null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		ComputerProduct computerProduct=ComputerProduct.builder()
		                                               .name(dto.getName())
		                                               .description(dto.getDescription())
		                                               .brand(dto.getBrand())
		                                               .categoryId(dto.getCategoryId())
		                                               .status(ProductStatus.PENDING)
		                                               .sellerId(sellerId)
		                                               .cpu(dto.getComputerCPU())
		                                               .gpu(dto.getComputerGPU())
		                                               .motherboard(dto.getComputerMotherboard())
		                                               .ram(dto.getComputerRam())
		                                               .screenSize(dto.getComputerScreenSize())
		                                               .build();
		computerProductRepository.save(computerProduct);
		computerProduct.setCode(ProductCodeGenerator.generateProductCode(computerProduct));
		
	}
}