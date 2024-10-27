package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddImageToProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.haruns.eticaretapp.constant.RestApis.*;

@RestController
@RequestMapping(PRODUCT_IMAGE)
@RequiredArgsConstructor
public class ProductImageController {
	private final ProductImageService productImageService;
	
	@PostMapping(value = ADD_IMAGE_TO_PRODUCT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> addImageToProduct(
			@RequestParam("productId") String productId,
			@RequestParam("token") String token,
			@RequestPart("file") MultipartFile file) throws IOException {
		
		AddImageToProductRequestDto dto = new AddImageToProductRequestDto(productId, token);
		productImageService.addImageToProduct(dto, file);
		
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .data(true)
		                                     .message("Resim eklendi")
		                                     .build());
	}
	
	
	
}