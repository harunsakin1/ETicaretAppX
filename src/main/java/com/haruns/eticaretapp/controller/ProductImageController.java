package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddImageToProductRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.ProductImageService;
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
	
	@PostMapping(value = ADD_IMAGE_TO_PRODUCT,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<BaseResponse<Boolean>> addImageToProduct(String token, @ModelAttribute @Valid AddImageToProductRequestDto dto,MultipartFile file)
			throws IOException {
		productImageService.addImageToProduct(token,dto,file);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .code(200)
				                         .data(true)
				                         .message("Resim eklendi")
		                                     .build());
		
	}
}