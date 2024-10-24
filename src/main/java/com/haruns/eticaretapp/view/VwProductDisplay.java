package com.haruns.eticaretapp.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwProductDisplay {
	String categoryName;
	String storeName;
	Double price;
	String url;
	VwProduct vwProduct;
}