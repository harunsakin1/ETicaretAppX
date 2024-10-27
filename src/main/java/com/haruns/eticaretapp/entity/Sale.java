package com.haruns.eticaretapp.entity;

import com.haruns.eticaretapp.entity.enums.PaymentType;
import com.haruns.eticaretapp.entity.enums.ShipmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tblsale")
public class Sale extends BaseEntity{
	String userId;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "sale_id")
	List<SaleItem> saleItems;
	String billNumber;
	String addressId;
	Double totalPrice;
	PaymentType paymentType;
	Long saleDate;
	ShipmentStatus shipmentStatus;
	Long estimatedDeliveryDate;
	String shipmentTrackingNumber;
}