package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.entity.ProductSeller;
import com.haruns.eticaretapp.repository.ProductSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSellerService {
	private final ProductSellerRepository productSellerRepository;
	
	public void save(ProductSeller productSeller) {
		productSellerRepository.save(productSeller);
	}
}