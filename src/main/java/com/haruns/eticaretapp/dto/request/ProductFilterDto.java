package com.haruns.eticaretapp.dto.request;

import com.haruns.eticaretapp.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductFilterDto {
	private ProductType productType;
	private Map<String, Object> filters;
	
}