package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}