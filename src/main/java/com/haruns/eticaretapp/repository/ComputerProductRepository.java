package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ComputerProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.view.VwProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComputerProductRepository extends JpaRepository<ComputerProduct,Long> {
	
	List<Product> findAllByStatus(ProductStatus status);
	
//	@Query("SELECT new com.haruns.eticaretapp.view.VwProduct (p.id,p.categoryId,p.name,p.description,p.brand,p.totalRating) FROM Product p WHERE p.status='ACCEPTED'")
//	List<VwProduct> getNeededFields(Pageable pageable);
}