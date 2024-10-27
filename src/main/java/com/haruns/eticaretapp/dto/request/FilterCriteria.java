package com.haruns.eticaretapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FilterCriteria {
	private String operator; //"lt","gt","eq"
	private Object value;
	
}