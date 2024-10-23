package com.haruns.eticaretapp.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwProduct {
	String name;
	String description;
	String brand;
	String categoryId;
	String userId;
	String price;
	String rating;
	String url;
}