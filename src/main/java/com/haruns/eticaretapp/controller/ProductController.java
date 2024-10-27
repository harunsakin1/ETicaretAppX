package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.dto.request.UpdateProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductType;
import com.haruns.eticaretapp.service.ProductService;
import com.haruns.eticaretapp.service.UserService;
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
				                         .data(productService.getAllPendingProducts(token))
				                         .message("Pending ürünler getirildi.")
		                                     .build());
	}
	
	@PatchMapping(CONFIRM_PRODUCT_STATUS)
	public ResponseEntity<BaseResponse<Boolean>> confirmProductStatus(String token, String productId){
		productService.confirmProductStatus(token, productId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .message("Ürün durumu güncellendi.")
		                                     .code(200)
		                                     .data(true)
		                                     .success(true)
		                                     .build());
	}
	
	@PutMapping(UPDATE_PRODUCT)
	public ResponseEntity<BaseResponse<Boolean>> updateProduct(String token, @RequestBody @Valid UpdateProductRequestDto dto){
		productService.updateProduct(token, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .code(200)
				                         .message("Ürün başarıyla güncellendi.")
				                         .data(true)
				                         .success(true)
		                                     .build());
	}
	
	@DeleteMapping(DELETE_PRODUCT)
	public ResponseEntity<BaseResponse<Boolean>> deleteProduct(String token,String productId){
		productService.deleteProduct(token,productId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .code(200)
				                         .message("Ürün başarıyla silindi.")
				                         .data(true)
				                         .success(true)
		                                     .build());
	}
	
	@PostMapping(FILTER_PRODUCTS)
	public ResponseEntity<BaseResponse<List<Product>>> filterProducts(@RequestBody ProductFilterDto filterDto){
		System.out.println(filterDto);
		return ResponseEntity.ok(BaseResponse.<List<Product>>builder()
                                         .code(200)
                                         .message("Ürünler filtreleniyor...")
                                         .success(true)
                                         .data(productService.filterProducts(filterDto))
                                             .build());
	}
	
//	@GetMapping(GET_CONFIRMED_PRODUCTS)
//	public ResponseEntity<BaseResponse<List<VwProductDisplay>>> getAllConfirmedProducts(){
//		return ResponseEntity.ok(BaseResponse.<List<VwProductDisplay>>builder()
//				                         .code(200)
//				                         .message("Tüm confirmed ürünler getirildi.")
//				                         .success(true)
//				                         .data(productService.getAllConfirmedProducts())
//		                                     .build());
//
//	}
}