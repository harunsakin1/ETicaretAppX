package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}