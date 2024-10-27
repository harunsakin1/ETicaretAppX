package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.dto.request.ProductFilterDto;
import com.haruns.eticaretapp.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductSpecification<T> {
	public Specification<T> getProductsByFilter(ProductFilterDto filterDto) {
		return (root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			
			for (Map.Entry<String, Object> filter : filterDto.getFilters().entrySet()) {
				String columnName = filter.getKey();
				Object columnValue = filter.getValue();
				
				if (columnValue != null) {
					predicate=cb.and(predicate, cb.equal(root.get(columnName), columnValue));
				}
			}
			return predicate;
		};
	}
}