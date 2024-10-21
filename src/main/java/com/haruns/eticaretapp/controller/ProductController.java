package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.haruns.eticaretapp.constant.RestApis.*;

@RequestMapping(PRODUCT)
@ResponseBody
@RequiredArgsConstructor
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
}