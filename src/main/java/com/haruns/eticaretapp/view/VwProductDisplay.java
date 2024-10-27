package com.haruns.eticaretapp.view;

import com.haruns.eticaretapp.entity.ProductComment;
import com.haruns.eticaretapp.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwProductDisplay {
	String id;
	String name;
	ProductType type;
	String code;
	String description;
	String brand;
	Double totalRating;
	Double price;
	String sellerId;
	String categoryId;
}