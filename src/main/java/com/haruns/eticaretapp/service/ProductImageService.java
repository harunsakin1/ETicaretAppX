package com.haruns.eticaretapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService {
	private final ProductImageRepository productImageRepository;
	private final JwtManager jwtManager;
	private final ProductService productService;
	private final UserService userService;
	private final Cloudinary cloudinary;
	
	public void addImageToProduct(String token, AddImageToProductRequestDto dto,MultipartFile file) throws IOException {
		Optional<Long> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		Optional<User> optUser = userService.findById(optUserId.get());
		if (optUser.isEmpty()) {
			throw new EticaretException(ErrorType.USER_NOT_FOUND);
		}
		if (!productService.existById(dto.productId())){
			throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
		}
		if (!optUser.get().getRole().equals(Role.SELLER)) {
			throw new EticaretException(ErrorType.UNAUTHORIZED);
		}
		
		ProductImage productImage= ProductImage.builder()
		                                        .productId(dto.productId())
												.url(uploadImage(file))
		                                        .build();
		productImageRepository.save(productImage);
	}
	
	public String uploadImage(MultipartFile file) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		return uploadResult.get("url").toString();
	}
}