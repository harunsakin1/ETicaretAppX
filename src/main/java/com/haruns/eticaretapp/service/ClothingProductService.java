package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ClothingRepository;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingProductService {
	private final ClothingRepository clothingRepository;
	public void addProduct(AddProductRequestDto dto, Long sellerId) {
		if(dto.getCategoryId()==2 && dto.getGender()==null && dto.getColor()==null && dto.getClothingSize()==null && dto.getClothingMaterial()==null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		ClothingProduct clothingProduct=ClothingProduct.builder()
		                                               .name(dto.getName())
		                                               .description(dto.getDescription())
		                                               .brand(dto.getBrand())
		                                               .categoryId(dto.getCategoryId())
		                                               .status(ProductStatus.PENDING)
		                                               .sellerId(sellerId)
		                                               .clothingMaterial(dto.getClothingMaterial())
		                                               .color(dto.getColor())
		                                               .size(dto.getClothingSize())
		                                               .gender(dto.getGender())
		                                               .build();
		clothingRepository.save(clothingProduct);
		clothingProduct.setCode(ProductCodeGenerator.generateProductCode(clothingProduct));
	}
}