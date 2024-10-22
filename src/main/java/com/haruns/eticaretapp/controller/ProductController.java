package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.haruns.eticaretapp.constant.RestApis.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {
	private final ProductService productService;
	
	@PostMapping(ADD_PRODUCT)
	public ResponseEntity<BaseResponse<Boolean>> addProduct(String token, @RequestBody @Valid AddProductRequestDto dto){
		productService.addProduct(token, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .code(200)
				                         .message("Ürün başarıyla eklendi.")
				                         .data(true)
				                         .success(true)
		                                     .build());
	}
	
	@GetMapping(GET_PENDING_PRODUCTS)
	public ResponseEntity<BaseResponse<List<Product>>> getPendingProducts(String token){
		return ResponseEntity.ok(BaseResponse.<List<Product>>builder()
				                         .code(200)
				                         .success(true)
				                         .data(productService.getPendingProducts(token))
				                         .message("Pending ürünler getirildi.")
		                                     .build());
	}
	
	@PatchMapping(CONFIRM_PRODUCT_STATUS)
	public ResponseEntity<BaseResponse<Boolean>> confirmProductStatus(String token, Long productId){
		productService.confirmProductStatus(token, productId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .message("Ürün durumu güncellendi.")
		                                     .code(200)
		                                     .data(true)
		                                     .success(true)
		                                     .build());
	}
}