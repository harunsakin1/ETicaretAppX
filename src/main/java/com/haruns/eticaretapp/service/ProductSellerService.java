package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.entity.ProductSeller;
import com.haruns.eticaretapp.repository.ProductSellerRepository;
import com.haruns.eticaretapp.view.VwProductSeller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSellerService {
	private final ProductSellerRepository productSellerRepository;
	
	public void save(ProductSeller productSeller) {
		productSellerRepository.save(productSeller);
	}

	public List<VwProductSeller> getNeededFields(Pageable pageable, List<Long> productIds) {
		return productSellerRepository.getNeededFields(productIds,pageable);
	}
}