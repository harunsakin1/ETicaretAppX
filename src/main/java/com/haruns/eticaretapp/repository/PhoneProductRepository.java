package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.PhoneProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneProductRepository extends JpaRepository<PhoneProduct, Long> {
}