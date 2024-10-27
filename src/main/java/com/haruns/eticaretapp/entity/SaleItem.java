package com.haruns.eticaretapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblsaleitem")
public class SaleItem extends BaseEntity {
	private String productId;
	private Integer quantity;
	private Double unitPrice;
	private Double totalPrice;
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.totalPrice = this.unitPrice * this.quantity;
	}
}