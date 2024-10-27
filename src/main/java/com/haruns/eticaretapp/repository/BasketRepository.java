package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {
	Optional<Basket> findByUserId(String userId);
}