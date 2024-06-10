package com.chn.customer.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
	public AccountStatus findByCode(String code);
}
