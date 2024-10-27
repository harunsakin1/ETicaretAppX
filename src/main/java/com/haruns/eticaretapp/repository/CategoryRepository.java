package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,String> {
	
	@Query("SELECT c.name FROM Category c WHERE c.id IN (?1)")
	List<String> findNameByIdIn(List<String> categoryIds);
	
	@Query("SELECT c.id FROM Category c")
	List<String> findAllIds();
}