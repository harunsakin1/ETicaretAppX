package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.view.VwProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	List<Product> findAllByStatus(ProductStatus status);
	
}