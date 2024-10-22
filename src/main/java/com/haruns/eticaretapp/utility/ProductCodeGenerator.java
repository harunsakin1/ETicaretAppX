package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.entity.Product;

import java.util.UUID;

public class ProductCodeGenerator {
	public static String generateProductCode(Product product)
	{
		StringBuilder productCode = new StringBuilder();
		UUID uuid = UUID.randomUUID();
		String subbedUUID = uuid.toString().substring(0, 8);
		productCode
				.append(product.getCategoryId())
				.append(product.getId())
				.append(product.getBrand().toLowerCase().substring(0, 3))
				.append(product.getName().toLowerCase().substring(0, 3))
				.append(subbedUUID);
		return productCode.toString();}
}