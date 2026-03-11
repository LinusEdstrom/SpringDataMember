package com.edstrom.SpringDataMember.repository;

import com.edstrom.SpringDataMember.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
