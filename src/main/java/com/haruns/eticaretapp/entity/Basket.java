package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblbasket")
public class Basket extends BaseEntity {
	private String userId;
	private Double totalPrice;
	private Integer itemCount;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "basket_id")
	@Builder.Default
	private List<BasketItem> basketItems = new ArrayList<>();
	
	public void addItem(BasketItem item) {
		this.basketItems.add(item);
		updateTotalPriceAndItemCount();
	}
	
	public void removeItem(BasketItem item) {
		this.basketItems.remove(item);
		updateTotalPriceAndItemCount();
	}
	
	public void updateTotalPriceAndItemCount() {
		this.totalPrice = basketItems.stream()
		                             .mapToDouble(BasketItem::getTotalPrice)
		                             .sum();
		this.itemCount = basketItems.size(); //TODO: Bu kısım yanlıs degistirilecek!
	}
}