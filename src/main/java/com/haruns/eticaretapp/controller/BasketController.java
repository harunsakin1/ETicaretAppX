package com.haruns.eticaretapp.controller;

import com.haruns.eticaretapp.dto.response.BaseResponse;
import com.haruns.eticaretapp.entity.Basket;
import com.haruns.eticaretapp.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.haruns.eticaretapp.constant.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {
	private final BasketService basketService;
	
	@PostMapping(ADD_TO_CART)
	public ResponseEntity<BaseResponse<Boolean>> addItemToBasket(
			@RequestParam String token,
			@RequestParam String productId,
			@RequestParam Integer quantity) {
		basketService.addProductsToBasket(token, productId, quantity);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
				                         .success(true)
				                         .code(200)
				                         .data(true)
				                         .message("Ürün başarıyla sepete eklendi.")
		                                 .build());
	}
	
	@DeleteMapping(REMOVE_FROM_CART)
	public ResponseEntity<BaseResponse<Boolean>> removeItemFromBasket(
			@RequestParam String token,
			@RequestParam String basketItemId) {
		basketService.removeItemFromBasket(token, basketItemId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .data(true)
		                                     .message("Ürün başarıyla sepetten silindi.")
		                                     .build());
	}
	
	@GetMapping(GET_CART)
	public ResponseEntity<Basket> getBasketByUserId(@RequestParam String  token) {
		Basket basket = basketService.getOrCreateBasket(token);
		return ResponseEntity.ok(basket);
	}
	
	@DeleteMapping(CLEAR_CART)
	public ResponseEntity<BaseResponse<Boolean>> clearBasket(@RequestParam String userId) {
		basketService.clearBasket(userId);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .data(true)
		                                     .message("Sepet başarıyla temizlendi.")
		                                     .build());
	}
}