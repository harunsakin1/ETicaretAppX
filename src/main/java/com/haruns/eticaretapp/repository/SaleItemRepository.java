package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem,String> {
}