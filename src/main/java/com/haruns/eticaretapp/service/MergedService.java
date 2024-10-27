package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface MergedService<T> {
	void addProduct(AddProductRequestDto dto, String sellerId);
	List<T> findAllByStatus(ProductStatus status);
	Optional<T> findById(String id);
	boolean existById(String id);
	void save(T product);
	void update(UpdateProductRequestDto dto);
	void deleteById(String id);
	List<T> filterProducts(ProductFilterDto dto);
}