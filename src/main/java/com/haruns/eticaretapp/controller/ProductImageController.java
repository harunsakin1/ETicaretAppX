package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddImageToProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.haruns.eticaretapp.constant.RestApis.*;

@RestController
@RequestMapping(PRODUCT_IMAGE)
@RequiredArgsConstructor
public class ProductImageController {
	private final ProductImageService productImageService;
	
	@PostMapping(ADD_IMAGE_TO_PRODUCT)
	public ResponseEntity<BaseResponse<Boolean>> addImageToProduct(String token, @RequestBody @Valid AddImageToProductRequestDto dto){
		productImageService.addImageToProduct(token, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .code(200)
				                         .data(true)
				                         .message("Resim eklendi")
		                                     .build());
		
	}
}