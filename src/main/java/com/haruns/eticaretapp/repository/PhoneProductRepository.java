package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.PhoneProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhoneProductRepository extends JpaRepository<PhoneProduct, String> {
	
	List<PhoneProduct> findAllByStatus (ProductStatus status);
	
}