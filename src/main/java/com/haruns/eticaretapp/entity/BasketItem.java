package com.haruns.eticaretapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblbasketitem")
public class BasketItem extends BaseEntity{
	private String productId;
	private Integer quantity;
	private Double unitPrice;
	private Double totalPrice;
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.totalPrice = this.unitPrice * this.quantity;
	}
}