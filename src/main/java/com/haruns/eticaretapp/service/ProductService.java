package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.ProductSeller;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ProductRepository;
import com.haruns.eticaretapp.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final UserService userService;
	private final JwtManager jwtManager;
	
	public void addProduct(String token, AddProductRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		
		Product product= Product.builder()
		                        .name(dto.name())
		                        .brand(dto.brand())
		                        .description(dto.description())
		                        .build();
		productRepository.save(product);
		
		ProductSeller productSeller= ProductSeller.builder()
				.price(dto.price())
				.productId(product.getId())
				.userId(optUser.get().getId())
				.stock(dto.stock())
		        .build();
		
	}
}