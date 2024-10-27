package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ComputerProduct;
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

public interface ComputerProductRepository extends JpaRepository<ComputerProduct,String>,
                                                   JpaSpecificationExecutor<ComputerProduct> {
	
	List<ComputerProduct> findAllByStatus(ProductStatus status);
	
	@Query("SELECT new com.haruns.eticaretapp.view.VwProductDisplay (p.id,p.name,p.type,p.code,p.description,p.brand,p.totalRating,p.price,p.sellerId,p.categoryId) FROM ComputerProduct p WHERE p.status='ACCEPTED'")
	LinkedList<VwProductDisplay> getNeededFields(Pageable pageable);
	
	@Query("SELECT p.cpu FROM ComputerProduct p WHERE p.id=?1")
	ComputerCPU findCpuById(String id);
	
	@Query("SELECT p.gpu FROM ComputerProduct p WHERE p.id=?1")
	ComputerGPU findGpuById(String id);
	
	@Query("SELECT p.screenSize FROM ComputerProduct p WHERE p.id=?1")
	ComputerScreenSize findScreenById(String id);
	
	@Query("SELECT p.motherboard FROM ComputerProduct p WHERE p.id=?1")
	ComputerMotherboard findMotherboardById(String id);
}