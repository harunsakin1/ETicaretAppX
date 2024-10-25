package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ProductSeller extends BaseEntity {
	Long userId;
	Long productId;
	Integer stock;
	Double price;
}