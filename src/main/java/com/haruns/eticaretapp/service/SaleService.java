package com.haruns.eticaretapp.service;

import com.haruns.eticaretapp.dto.request.SaleDto;
import com.haruns.eticaretapp.entity.Basket;
import com.haruns.eticaretapp.entity.BasketItem;
import com.haruns.eticaretapp.entity.Sale;
import com.haruns.eticaretapp.entity.SaleItem;
import com.haruns.eticaretapp.entity.enums.ShipmentStatus;
import com.haruns.eticaretapp.exception.ErrorType;
import com.haruns.eticaretapp.exception.EticaretException;
import com.haruns.eticaretapp.repository.AddressRepository;
import com.haruns.eticaretapp.repository.SaleItemRepository;
import com.haruns.eticaretapp.repository.SaleRepository;
import com.haruns.eticaretapp.utility.EntityIdOperator;
import com.haruns.eticaretapp.utility.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleService {
	private final SaleRepository saleRepository;
	private final JwtManager jwtManager;
	private final BasketService basketService;
	private final EntityIdOperator entityIdOperator;
	private final AddressService addressService;
	private final SaleItemRepository saleItemRepository;
	
	public void saveSale(SaleDto saleDto) {
		String token = saleDto.getToken();
		Optional<String> optUserId = jwtManager.validateToken(token);
		if (optUserId.isEmpty()) {
            throw new EticaretException(ErrorType.USER_NOT_FOUND);
        }
		Sale sale = Sale.builder()
				.id(entityIdOperator.generateUniqueIdForOtherEntities())
				.userId(optUserId.get())
				.saleDate(System.currentTimeMillis())
				.addressId(saleDto.getAddressId())
				.billNumber(UUID.randomUUID().toString())
				.estimatedDeliveryDate(System.currentTimeMillis() + 1000*60*60*24*3)
				.shipmentStatus(ShipmentStatus.WAITING)
				.shipmentTrackingNumber(UUID.randomUUID().toString())
				.paymentType(saleDto.getPaymentType())
				.build();
		Basket usersBasket = basketService.getOrCreateBasket(optUserId.get());
		List<SaleItem> saleItems = new ArrayList<>();
		Double totalPrice = 0.0;
		for (BasketItem basketItem:usersBasket.getBasketItems()){
			SaleItem saleItem = SaleItem.builder()
					.id(entityIdOperator.generateUniqueIdForOtherEntities())
                    .productId(basketItem.getProductId())
                    .quantity(basketItem.getQuantity())
                    .unitPrice(basketItem.getUnitPrice())
					.totalPrice(basketItem.getTotalPrice())
                    .build();
			saleItems.add(saleItem);
			totalPrice+= saleItem.getTotalPrice();
            saleItemRepository.save(saleItem);
            basketService.deleteBasketItem(basketItem.getId());
            }
			sale.setSaleItems(saleItems);
			sale.setTotalPrice(totalPrice);
			basketService.clearBasket(usersBasket.getUserId());
			saleRepository.save(sale);
            
		}
}