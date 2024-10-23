package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.AddAddressRequestDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.haruns.eticaretapp.constant.RestApis.*;

@RestController
@RequestMapping(ADDRESS)
@RequiredArgsConstructor
public class AddressController {
	private final AddressService addressService;
	
	@PostMapping(ADD_ADDRESS)
	public ResponseEntity<BaseResponse<Boolean>> addAddress(String token, @RequestBody @Valid AddAddressRequestDto dto){
		addressService.addAddress(token, dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .data(true)
				                         .code(200)
				                         .message("Adres başarıyla eklendi.")
		                                 .build());
	}
}