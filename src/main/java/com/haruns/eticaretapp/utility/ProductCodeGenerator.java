package com.haruns.eticaretapp.utility;

import com.auth0.jwt.algorithms.Algorithm;
import com.haruns.eticaretapp.entity.Product;
import com.haruns.eticaretapp.entity.enums.ProductType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductCodeGenerator {
	public static String generateProductCode(Product product)
	{
		StringBuilder productCode = new StringBuilder();
		UUID uuid = UUID.randomUUID();
		productCode
				.append(uuid)
				.append(product.getCategoryId())
				.append(product.getId())
				.append(product.getBrand().toLowerCase().substring(0, 3))
				.append(product.getName().toLowerCase().substring(0, 3));
		return productCode.toString();
	}
	
}