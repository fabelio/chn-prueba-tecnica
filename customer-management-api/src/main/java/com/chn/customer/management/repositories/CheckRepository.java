package com.chn.customer.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.Check;

public interface CheckRepository extends JpaRepository<Check, Long> {
	public List<Check> findByCheckbookId(Long checkbookId);
}
