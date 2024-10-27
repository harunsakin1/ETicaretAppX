package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.PhoneProduct;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.*;
import com.haruns.eticaretapp.view.VwProduct;
import com.haruns.eticaretapp.view.VwProductDisplay;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface PhoneProductRepository extends JpaRepository<PhoneProduct, String>, JpaSpecificationExecutor<PhoneProduct> {
	
	List<PhoneProduct> findAllByStatus (ProductStatus status);
	
	@Query("SELECT new com.haruns.eticaretapp.view.VwProductDisplay (p.id,p.name,p.type,p.code,p.description,p.brand,p.totalRating,p.price,p.sellerId,p.categoryId) FROM PhoneProduct p WHERE p.status='ACCEPTED'")
	LinkedList<VwProductDisplay> getNeededFields(Pageable pageable);
	
	@Query("SELECT p.cpu FROM PhoneProduct p WHERE p.id=?1")
	PhoneCPU findCpuById(String id);
	
	@Query("SELECT p.storage FROM PhoneProduct p WHERE p.id=?1")
	PhoneStorage findStorageById(String id);
	
	@Query("SELECT p.screen FROM PhoneProduct p WHERE p.id=?1")
	PhoneScreen findScreenById(String id);
	
	@Query("SELECT p.camera FROM PhoneProduct p WHERE p.id=?1")
	PhoneCamera findCameraById(String id);
}