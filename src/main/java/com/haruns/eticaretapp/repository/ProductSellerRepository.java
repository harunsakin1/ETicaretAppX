package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ProductSeller;
import com.haruns.eticaretapp.view.VwProductDisplay;
import com.haruns.eticaretapp.view.VwProductSeller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductSellerRepository extends JpaRepository<ProductSeller, Long> {

	@Query("SELECT new com.haruns.eticaretapp.view.VwProductSeller (p.userId,p.price) FROM ProductSeller p WHERE p" +
			".productId IN (?1)")
	List<VwProductSeller> getNeededFields(List<Long> productIds,Pageable pageable);

}