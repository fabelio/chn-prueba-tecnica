package com.chn.customer.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chn.customer.management.entities.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

}
