package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.AddImageToProductRequestDto;
import com.haruns.eticaretapp.entity.ProductImage;
import com.haruns.eticaretapp.entity.User;
import com.haruns.eticaretapp.entity.enums.Role;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.ProductImageRepository;
import com.haruns.eticaretapp.utility.JwtManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final JwtManager jwtManager;
	private final ProductService productService;
	private final UserService userService;
	
	public void addImageToProduct(String token, AddImageToProductRequestDto dto) {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (productService.existById(dto.productId())){
			throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.SELLER)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		ProductImage productImage= ProductImage.builder()
		                                        .productId(dto.productId())
												.url(dto.url())
		                                        .build();
		productImageRepository.save(productImage);
	}
}