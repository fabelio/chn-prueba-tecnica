package com.chn.customer.management.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.AccountType;
import com.chn.customer.management.entities.Check;
import com.chn.customer.management.entities.Checkbook;
import com.chn.customer.management.services.AccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	@Autowired
	AccountService service;

	@GetMapping
	public ResponseEntity<List<Account>> listAccounts(
			@RequestParam(required = false, defaultValue = "0") String customerId,
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		return new ResponseEntity<List<Account>>(service.listAccount(pageNumber, pageSize), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody(required = true) Account account) {
		return new ResponseEntity<Account>(service.createAccount(account), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> retrieveAccount(@PathVariable(name = "id") String id) {
		return new ResponseEntity<Account>(service.retrieveAccount(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable("id") String id,
			@RequestBody(required = true) Account account) {
		return new ResponseEntity<Account>(service.updateAccount(id, account), HttpStatus.OK);
	}

	@GetMapping("/types")
	public ResponseEntity<List<AccountType>> listAccountTypes(
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		return new ResponseEntity<List<AccountType>>(service.listAccountType(pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping("/{id}/checkbooks")
	public ResponseEntity<List<Checkbook>> listCustomerAccounts(@PathVariable(name = "id") String id) {
		return new ResponseEntity<List<Checkbook>>(service.listAccountCheckbooks(id), HttpStatus.OK);
	}

	@PutMapping("/{id}/status/{statusCode}")
	public ResponseEntity<Account> updateAccountStatus(@PathVariable("id") String id,
			@PathVariable("statusCode") String statusCode, @RequestParam(required = false) String reason) {
		return new ResponseEntity<Account>(service.updateStatus(id, statusCode, reason), HttpStatus.OK);
	}

	@PutMapping("/{id}/checkbook/{checkbookId}/check/{checkId}/collect")
	public ResponseEntity<Account> collectCheck(@PathVariable("id") String id, @PathVariable("checkId") String checkId,
			@PathVariable("checkbookId") String checkbookId, @RequestParam(required = false) BigDecimal amount) {
		return new ResponseEntity<Account>(service.collectAccountCheck(id, checkId, amount), HttpStatus.OK);
	}
}
