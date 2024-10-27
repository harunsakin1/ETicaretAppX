package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
	
	@Query("SELECT pc FROM ProductComment pc WHERE pc.productId = ?1")
	List<ProductComment> findCommentsByProductId(String productId);
}