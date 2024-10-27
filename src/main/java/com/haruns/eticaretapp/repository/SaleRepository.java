package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}