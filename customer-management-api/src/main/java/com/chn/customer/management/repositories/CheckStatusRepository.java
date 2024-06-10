package com.chn.customer.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.CheckStatus;

public interface CheckStatusRepository extends JpaRepository<CheckStatus, Long> {
	public CheckStatus findByCode(String code);
}
