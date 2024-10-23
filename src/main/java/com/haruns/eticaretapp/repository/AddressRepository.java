package com.haruns.eticaretapp.repository;

import com.haruns.eticaretapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {

}