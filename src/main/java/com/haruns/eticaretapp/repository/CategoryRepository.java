package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}