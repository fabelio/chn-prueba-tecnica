package com.chn.customer.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.CheckbookStatus;

public interface CheckbookStatusRepository extends JpaRepository<CheckbookStatus, Long> {
	public CheckbookStatus findByCode(String code);
}
