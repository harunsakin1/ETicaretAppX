package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ComputerProductRepository;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.ProductCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerProductService implements MergedService<ComputerProduct>{
	private final ComputerProductRepository computerProductRepository;
	private final EntityIdOperator entityIdOperator;
	@Override
	public void addProduct(AddProductRequestDto dto, String sellerId) {
		if(dto.getComputerRam()==null || dto.getComputerCPU()==null || dto.getComputerGPU()==null || dto.getComputerMotherboard()==null || dto.getComputerScreenSize()==null)
			throw new EticaretException(ErrorType.MISSING_FIELDS);
		ComputerProduct computerProduct=ComputerProduct.builder()
		                                               .id(entityIdOperator.generateUniqueIdForProducts(dto.getProductType()))
		                                               .name(dto.getName())
		                                               .price(dto.getPrice())
		                                               .stock(dto.getStock())
		                                               .description(dto.getDescription())
		                                               .brand(dto.getBrand())
		                                               .type(dto.getProductType())
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
	@Override
	public boolean existById(String id) {
		return computerProductRepository.existsById(id);
	}
	@Override
	public Optional<ComputerProduct> findById(String id) {
		return computerProductRepository.findById(id);
    }
	@Override
	public List<ComputerProduct> findAllByStatus(ProductStatus status) {
		return computerProductRepository.findAllByStatus(status);
	}
	@Override
	public void save(ComputerProduct product) {
		computerProductRepository.save(product);
	}
	@Override
	public void update(UpdateProductRequestDto dto) {
		ComputerProduct computerProduct = ComputerProduct.builder()
				.id(dto.id())
				.type(dto.productType())
				.name(dto.name())
		        .description(dto.description())
		        .brand(dto.brand())
				.price(dto.price())
				.stock(dto.stock())
				.cpu(dto.computerCPU())
				.gpu(dto.computerGPU())
				.screenSize(dto.computerScreenSize())
				.motherboard(dto.computerMotherboard())
		        .ram(dto.computerRam())
				.build();
		computerProductRepository.save(computerProduct);
	}
	@Override
	public void deleteById(String id) {
        computerProductRepository.deleteById(id);
    }
}