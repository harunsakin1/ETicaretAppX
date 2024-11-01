package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.*;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.entity.enums.ProductType;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.mapper.ProductMapper;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.JwtManager;
import com.haruns.eticaretapp.utility.ProductServiceMapper;
import com.haruns.eticaretapp.view.VwProduct;
import com.haruns.eticaretapp.view.VwProductDisplay;
import com.haruns.eticaretapp.view.VwProductSeller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final List<MergedService<? extends Product>> productServices;
	private final UserService userService;
	private final JwtManager jwtManager;
	private final CategoryService categoryService;
	private final ProductServiceMapper productServiceMapper;
	private final EntityIdOperator entityIdOperator;
	private final ComputerProductService computerProductService;
	
	public void addProduct(String token, AddProductRequestDto dto) {
		if (!categoryService.existById(dto.getCategoryId())) {
			throw new EticaretException(ErrorType.CATEGORY_NOT_FOUND);
		}
		Optional<String> optionalId = jwtManager.validateToken(token);
		userService.sellerTokenControl(token);
		
		productServiceMapper.getService(dto.getProductType().name().toLowerCase()).addProduct(dto, optionalId.get());
	}
	
	public List<Product> filterProducts(ProductFilterDto filterDto) {
		List<Product> productList=
				productServiceMapper.getService(filterDto.getProductType().name().toLowerCase()).filterProducts(filterDto);
		return productList;
	}
	
	
	
	public Optional<Product> findProductById(String productId) {
		return productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(productId)).findById(productId);
	}
	
	public List<Product> getAllPendingProducts(String token) {
		userService.adminTokenControl(token);
		List<Product> allPendingProducts = new ArrayList<>();
		for (MergedService<? extends Product> service : productServices) {
			allPendingProducts.addAll(service.findAllByStatus(ProductStatus.PENDING));
		}
		System.out.println(allPendingProducts);
		return allPendingProducts;
	}
	
	public Optional<Product> findProductByIdAndType(String productId) {
		return productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(productId)).findById(productId);
	}
	
	
	public void confirmProductStatus(String token, String id) {
		if (!userService.adminTokenControl(token)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		Optional<Product> optionalProduct;
		optionalProduct=productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(id)).findById(id);
			if(optionalProduct.isEmpty()){
				throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		}
		Product product = optionalProduct.get();
		product.setStatus(ProductStatus.ACCEPTED);
		productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(id)).save(product);
	}


	public void updateProduct(String token, UpdateProductRequestDto dto) {
		userService.sellerTokenControl(token);
		if(dto.id()==null) {
			throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		}
		if(!productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(dto.id())).existById(dto.id()))
			throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(dto.id())).update(dto);
	}
	
	public void deleteProduct(String token, String productId) {
		if (!userService.adminTokenControl(token))
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		if (!userService.sellerTokenControl(token))
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		if (!productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(productId)).existById(productId))
			throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		productServiceMapper.getService(entityIdOperator.extractProductTypeFromProductId(productId)).deleteById(productId);
	}
	
	public List<VwProduct> getAllConfirmedProducts(ProductType productType) {
		return productServiceMapper.getService(productType.name().toLowerCase()).getTop10ByStatus();
	}
}