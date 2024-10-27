package com.haruns.eticaretapp.utility;

import com.haruns.eticaretapp.dto.request.FilterCriteria;
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
			
			for (Map.Entry<String, FilterCriteria> filter : filterDto.getFilters().entrySet()) {
				String columnName = filter.getKey();
				FilterCriteria criteria = filter.getValue();
				
				if (criteria.getValue() != null) {
					switch (criteria.getOperator()){
						case "lt":
							predicate=cb.and(predicate, cb.lt(root.get(columnName),(Number) criteria.getValue()));
							break;
						case "gt":
							predicate=cb.and(predicate, cb.gt(root.get(columnName),(Number) criteria.getValue()));
                            break;
                        case "eq":
							predicate=cb.and(predicate, cb.equal(root.get(columnName),criteria.getValue()));
							break;
						default:
							throw new IllegalArgumentException("Invalid operator: " + criteria.getOperator());
					}
					
				}
			}
			return predicate;
		};
	}
}