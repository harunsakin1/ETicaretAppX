package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddCategoryRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.haruns.eticaretapp.constant.RestApis.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(CATEGORY)
public class CategoryController {
	private final CategoryService categoryService;
	
	@PostMapping(ADD_CATEGORY)
	public ResponseEntity<BaseResponse<Boolean>> addCategory(AddCategoryRequestDto dto){
		categoryService.addCategory(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .message("Kategori eklendi")
				                         .code(200)
				                         .data(true)
				                         .success(true)
				                         .build());
	}
}