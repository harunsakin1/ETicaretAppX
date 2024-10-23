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
@Entity
@Table(name = "tblproduct")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long categoryId;
	String name;
	String code;
	String description;
	String brand;
	Double totalRating;
	@Enumerated(EnumType.STRING)
	ProductStatus status;
}