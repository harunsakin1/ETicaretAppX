package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,String> {
	
	@Query("SELECT p.url FROM ProductImage p WHERE p.productId IN (?1)")
	List<String> findUrlByProductIdIn(List<String> productIds);
	
	@Query("SELECT p.url FROM ProductImage p WHERE p.productId =?1")
	List<String> findUrlByProductId(String productId);
}