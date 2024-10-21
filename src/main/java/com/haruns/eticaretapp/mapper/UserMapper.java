package com.haruns.eticaretapp.mapper;

import com.haruns.eticaretapp.dto.request.StoreRegisterRequestDto;
import com.haruns.eticaretapp.dto.request.UpdateMyProfileRequestDto;
import com.haruns.eticaretapp.dto.request.UpdateMyStoreProfileRequestDto;
import com.haruns.eticaretapp.dto.request.UserRegisterRequestDto;
import com.haruns.eticaretapp.dto.response.GetMyProfileResponseDto;
import com.haruns.eticaretapp.dto.response.GetMyStoreProfileResponseDto;
import com.haruns.eticaretapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
	
	User fromRegisterDto(final UserRegisterRequestDto dto);
	
	User fromStoreRegisterDto(final StoreRegisterRequestDto dto);
	
	GetMyStoreProfileResponseDto fromUser(final User user);
	
	GetMyProfileResponseDto fromUserEntity(final User user);
	
	User fromUpdateMyProfileDto(final UpdateMyProfileRequestDto dto);
	
	User fromUpdateMyStoreProfileDto(final UpdateMyStoreProfileRequestDto dto);
}