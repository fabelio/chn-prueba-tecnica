package com.chn.customer.management.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findByDisabledOrDisabledIsNull(Long disabled, Pageable pageable);
}
