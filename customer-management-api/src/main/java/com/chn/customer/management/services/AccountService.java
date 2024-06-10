package com.chn.customer.management.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.AccountStatusHist;
import com.chn.customer.management.entities.AccountType;
import com.chn.customer.management.entities.Check;
import com.chn.customer.management.entities.CheckStatusHist;
import com.chn.customer.management.entities.Checkbook;
import com.chn.customer.management.repositories.AccountRepository;
import com.chn.customer.management.repositories.AccountStatusRepository;
import com.chn.customer.management.repositories.AccountTypeRepository;
import com.chn.customer.management.repositories.CheckRepository;
import com.chn.customer.management.repositories.CheckbookRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository repository;
	@Autowired
	AccountStatusRepository statusRepository;
	@Autowired
	AccountTypeRepository typeRepository;
	@Autowired
	CheckbookService checkbookService;
	@Autowired
	CheckService checkService;

	public List<Account> listAccount(int pageNumber, int pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id"))).getContent();
	}

	public Account retrieveAccount(String accountId) {
		return repository.findById(Long.valueOf(accountId)).orElse(null);
	}

	public Account createAccount(Account account) {
		if (account.getBalance() == null)
			account.setBalance(account.getOpeningAmount());
		if (account.getCurrentStatus() == null) {
			AccountStatusHist current = new AccountStatusHist();
			current.setAccountStatus(statusRepository.findByCode("activa"));
			current.setAccount(account);
			current.setReason("Apertura de cuenta");
			account.setCurrentStatus(current);
		}

		return repository.saveAndFlush(account);
	}

	public Account updateAccount(String accountId, Account account) {
		Account accountEntity = retrieveAccount(accountId);
		if (accountEntity == null)
			throw new RuntimeException("Account doesnt exist");

		account.setId(Long.valueOf(accountId));
		return repository.saveAndFlush(account);
	}

	public void moveBalance(String accountId, BigDecimal amountToMove) {
		Account account = retrieveAccount(accountId);
		if (account == null)
			throw new RuntimeException("Account doesnt exist");

		account.setBalance(account.getBalance().add(amountToMove));
		repository.saveAndFlush(account);
	}

	public List<AccountType> listAccountType(int pageNumber, int pageSize) {
		return typeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("code").ascending())).getContent();
	}

	public List<Checkbook> listAccountCheckbooks(String accountId) {
		return checkbookService.findCheckbookByAccount(accountId);
	}

	public Account updateStatus(String accountId, String statusCode, String reason) {
		Account account = retrieveAccount(accountId);
		if (account == null)
			throw new RuntimeException("Account doesnt exist");
		if (!account.getCurrentStatus().getAccountStatus().getCode().equals(statusCode)) {
			AccountStatusHist current = new AccountStatusHist();
			current.setAccountStatus(statusRepository.findByCode(statusCode));
			current.setAccount(account);
			current.setReason(reason);
			account.setCurrentStatus(current);
		}
		repository.saveAndFlush(account);
		return account;

	}

	public Account collectAccountCheck(String accountuId, String checkId, BigDecimal amount) {
		Account account = retrieveAccount(accountuId);
	
		if (account == null)
			throw new RuntimeException("No existe la cuenta");
		System.out.println("Montos");
		System.out.println(account.getBalance());
		System.out.println(amount);
		System.out.println(account.getBalance().compareTo(amount));
		if (account.getBalance().compareTo(amount) == -1)
			throw new RuntimeException("Fondos insuficientes");

		checkService.collectCheck(checkId, amount);
		this.moveBalance(accountuId, amount.multiply(new BigDecimal(-1)));

		return account;

	}

}
