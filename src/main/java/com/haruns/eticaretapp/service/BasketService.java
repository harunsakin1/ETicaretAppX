package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.entity.Basket;
import com.haruns.eticaretapp.entity.BasketItem;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.BasketItemRepository;
import com.haruns.eticaretapp.repository.BasketRepository;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
	private final BasketRepository basketRepository;
	private final BasketItemRepository basketItemRepository;
	private final ProductService productService;
	private final EntityIdOperator entityIdOperator;
	private final JwtManager jwtManager;
	
	public Basket getOrCreateBasket(String token) {
		Optional<String> optionalId = jwtManager.validateToken(token); //TODO: Bu kısım hatalı yarın kontrol et!
		if(optionalId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		String userId = optionalId.get();
		return basketRepository.findByUserId(userId)
		                       .orElseGet(() -> {
			                       Basket basket = Basket.builder()
					                       .id(entityIdOperator.generateUniqueIdForOtherEntities())
					                       .userId(userId)
					                       .totalPrice(0.0)
			                               .itemCount(0)
			                                .build();
			                       return basketRepository.save(basket);
		                       });
	}
	
	@Transactional
	public Basket addProductsToBasket(String token, String productId, Integer quantity) {
		Optional<String> optionalId = jwtManager.validateToken(token);
		if (optionalId.isEmpty()) {
            throw new EticaretException(ErrorType.INVALID_TOKEN);
        }
		Optional<Product> productById = productService.findProductById(productId);
		
        if (productById.isEmpty()) {
            throw new EticaretException(ErrorType.PRODUCT_NOT_FOUND);
        }
		BasketItem basketItem = BasketItem.builder()
				.id(entityIdOperator.generateUniqueIdForOtherEntities())
				.quantity(quantity)
				.productId(productId)
				.unitPrice(productById.get().getPrice())
				.totalPrice(productById.get().getPrice()* quantity)
		        .build();
		Basket usersBasket = getOrCreateBasket(optionalId.get());
		usersBasket.addItem(basketItemRepository.save(basketItem));
		return basketRepository.save(usersBasket);
	}
	
	
	@Transactional
	public Basket removeItemFromBasket(String token, String basketItemId) {
		Optional<String> optionalId = jwtManager.validateToken(token);
		if(optionalId.isEmpty()) {
			throw new EticaretException(ErrorType.INVALID_TOKEN);
		}
		String userId = optionalId.get();
		Basket basket = getOrCreateBasket(userId);
		basket.getBasketItems().removeIf(item -> item.getId().equals(basketItemId));
		basket.updateTotalPriceAndItemCount();
		return basketRepository.save(basket);
	}
	
	@Transactional
	public void updateBasketItemQuantity(String userId, String basketItemId, Integer newQuantity) {
		BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new EticaretException(ErrorType.PRODUCT_NOT_FOUND_IN_BASKET));
        
        if (newQuantity <= 0) {
            removeItemFromBasket(userId, basketItemId);
        }
        
        basketItem.setQuantity(newQuantity);
        basketItem.setTotalPrice(basketItem.getUnitPrice() * newQuantity);
		basketItemRepository.save(basketItem);
		Basket userBasket = getOrCreateBasket(userId);
		userBasket.updateTotalPriceAndItemCount();
        basketRepository.save(userBasket);
	}
	
	@Transactional
	public void clearBasket(String userId) {
		Basket basket = getOrCreateBasket(userId);
		basket.getBasketItems().clear();
		basket.updateTotalPriceAndItemCount();
		basketRepository.save(basket);
	}
}