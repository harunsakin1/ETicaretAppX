package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ClothingProductRepository;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import com.haruns.eticaretapp.utility.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClothingProductService implements MergedService<ClothingProduct>{
	private final ClothingProductRepository clothingProductRepository;
	private final EntityIdOperator entityIdOperator;
	private final ProductSpecification<ClothingProduct> productSpecification;
	
	@Override
	public void addProduct(AddProductRequestDto dto, String sellerId) {
		if(dto.getGender()==null && dto.getColor()==null && dto.getClothingSize()==null && dto.getClothingMaterial()==null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		ClothingProduct clothingProduct=ClothingProduct.builder()
		                                               .id(entityIdOperator.generateUniqueIdForProducts(dto.getProductType()))
		                                               .name(dto.getName())
		                                               .description(dto.getDescription())
		                                               .brand(dto.getBrand())
		                                               .price(dto.getPrice())
		                                               .stock(dto.getStock())
		                                               .type(dto.getProductType())
		                                               .categoryId(dto.getCategoryId())
		                                               .status(ProductStatus.PENDING)
		                                               .sellerId(sellerId)
		                                               .clothingMaterial(dto.getClothingMaterial())
		                                               .color(dto.getColor())
		                                               .size(dto.getClothingSize())
		                                               .gender(dto.getGender())
		                                               .build();
		clothingProductRepository.save(clothingProduct);
		clothingProduct.setCode(ProductCodeGenerator.generateProductCode(clothingProduct));
	}
	@Override
	public List<ClothingProduct> findAllByStatus(ProductStatus status) {
		return clothingProductRepository.findAllByStatus(status);
	}
	@Override
	public Optional<ClothingProduct> findById(String id) {
		return clothingProductRepository.findById(id);
	}
	@Override
	public boolean existById(String id) {
		return clothingProductRepository.existsById(id);
	}
	@Override
	public void save(ClothingProduct product) {
		clothingProductRepository.save(product);
	}
	@Override
	public void update(UpdateProductRequestDto dto) {
		ClothingProduct clothingProduct = ClothingProduct.builder()
		                                                 .id(dto.id())
		                                                 .type(dto.productType())
		                                                 .name(dto.name())
		                                                 .description(dto.description())
		                                                 .brand(dto.brand())
		                                                 .price(dto.price())
		                                                 .stock(dto.stock())
				.size(dto.clothingSize())
				.clothingMaterial(dto.clothingMaterial())
				.gender(dto.gender())
				.color(dto.color())
				.build();
        clothingProductRepository.save(clothingProduct);
    }
	@Override
	public void deleteById(String id) {
        clothingProductRepository.deleteById(id);
    }
	
	public List<ClothingProduct> filterProducts(ProductFilterDto filterDto){
		Specification<ClothingProduct> specification = productSpecification.getProductsByFilter(filterDto);
		return clothingProductRepository.findAll(specification);
	}
}