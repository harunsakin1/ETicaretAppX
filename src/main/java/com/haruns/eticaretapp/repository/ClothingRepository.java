package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ClothingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingRepository extends JpaRepository<ClothingProduct, Long> {
}