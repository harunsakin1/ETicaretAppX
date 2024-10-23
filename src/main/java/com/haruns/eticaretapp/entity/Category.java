package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblcategory")
public class Category extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	String name;
}