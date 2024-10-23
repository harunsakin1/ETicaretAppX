package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public class BaseEntity {
	@Builder.Default
	Long createdDate=System.currentTimeMillis();
	@Builder.Default
	@Enumerated(EnumType.STRING)
	State state=State.ACTIVE;
}