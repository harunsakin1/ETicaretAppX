package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.entity.enums.ProductType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntityIdOperator {
	
	public String generateUniqueIdForProducts(ProductType type) {
		StringBuilder id = new StringBuilder();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		id.append(uuid);
		id.append(type.name().toLowerCase());
		return id.toString();
		
	}
	public String generateUniqueIdForOtherEntities() {
		StringBuilder id = new StringBuilder();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		id.append(uuid);
		return id.toString();
	}
	
	public String extractProductTypeFromProductId(String id) {
		return id.substring(32);
	}
	
}