package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, String> {
}