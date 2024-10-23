package com.haruns.eticaretapp.mapper;

import com.haruns.eticaretapp.dto.request.AddAddressRequestDto;
import com.haruns.eticaretapp.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {
	AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);
	
	Address fromAddAddressDto(final AddAddressRequestDto dto);
}