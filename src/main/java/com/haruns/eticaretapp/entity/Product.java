package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.ProductCategory;
import com.haruns.eticaretapp.entity.enums.ProductStatus;
import com.haruns.eticaretapp.entity.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class Product extends BaseEntity {
	String categoryId;
	String name;
	ProductType type;
	String code;
	String description;
	String brand;
	Double totalRating;
	@Enumerated(EnumType.STRING)
	ProductStatus status;
	String sellerId;
	Long stock;
	Double price;
}