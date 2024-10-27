package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblproduct_comment")
public class ProductComment extends BaseEntity {
	
	String userId;
	String productId;
	String comment;
	Integer rating;
}