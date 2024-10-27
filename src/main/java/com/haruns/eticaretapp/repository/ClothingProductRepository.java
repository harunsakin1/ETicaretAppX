package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ClothingProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClothingProductRepository extends JpaRepository<ClothingProduct, String> {
	
	List<ClothingProduct> findAllByStatus(ProductStatus status);
	
	@Query("SELECT p.id FROM ClothingProduct p")
	List<String> findAllIds();
}