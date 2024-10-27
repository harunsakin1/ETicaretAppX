package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.SaleDto;
import com.haruns.eticaretapp.entity.Basket;
import com.haruns.eticaretapp.entity.BasketItem;
import com.haruns.eticaretapp.entity.SaleItem;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final JwtManager jwtManager;
	private final BasketService basketService;
	private final EntityIdOperator entityIdOperator;
	
	public void saveSale(SaleDto saleDto) {
		String token = saleDto.getToken();
		Optional<String> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
            throw new EticaretException(ErrorType.USER_NOT_FOUND);
        }
		Sale
		Basket usersBasket = basketService.getOrCreateBasket(optUserId.get());
		for (BasketItem basketItem:usersBasket.getBasketItems()){
			SaleItem saleItem = SaleItem.builder()
                    .productId(basketItem.getProductId())
                    .quantity(basketItem.getQuantity())
                    .unitPrice(basketItem.getUnitPrice())
					.totalPrice(basketItem.getTotalPrice())
                    .build();
            saleItemRepository.save(saleItem);
            basketItem.delete(basketItem.getId());
            }
			basketService.clearBasket(usersBasket.getUserId());
            basketService.saveBasket(usersBasket);
            
		}
		SaleItem saleItem = SaleItem.builder().build();
		
	}
}