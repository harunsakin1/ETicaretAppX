package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.PhoneProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PhoneProductRepository extends JpaRepository<PhoneProduct, String>, JpaSpecificationExecutor<PhoneProduct> {
	
	List<PhoneProduct> findAllByStatus (ProductStatus status);
	
}