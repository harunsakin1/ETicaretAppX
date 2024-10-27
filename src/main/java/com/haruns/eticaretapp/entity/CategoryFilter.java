package com.haruns.eticaretapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblcategory_filter")
public class CategoryFilter extends BaseEntity {

}