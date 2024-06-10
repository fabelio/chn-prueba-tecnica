package com.chn.customer.management.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.Checkbook;

public interface CheckbookRepository extends JpaRepository<Checkbook, Long> {
	public List<Checkbook> findByAccountId(Long accountId, Pageable peageable);
}
