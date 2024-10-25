package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.ProductCategory;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class Product extends BaseEntity {
	Long categoryId;
	String name;
	String code;
	String description;
	String brand;
	Double totalRating;
	@Enumerated(EnumType.STRING)
	ProductStatus status;
	Long sellerId;
	Long stock;
	Double price;
}