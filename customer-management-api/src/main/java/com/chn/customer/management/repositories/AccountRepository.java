package com.chn.customer.management.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public List<Account> findByCustomerId(Long customerId, Pageable peageable);
}
