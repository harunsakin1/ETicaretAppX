package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.request.SaleDto;
import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.entity.Sale;
import com.haruns.eticaretapp.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
	private final SaleService saleService;
	
	@PostMapping("/confirm")
    public ResponseEntity<BaseResponse<Boolean>> confirmSale(@RequestBody SaleDto saleDto){
		saleService.saveSale(saleDto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                  .message("Satış başarıyla tamamlandı!")
				                  .data(true)
				                  .success(true)
				                  .code(200)
				                  .build());
	}
}