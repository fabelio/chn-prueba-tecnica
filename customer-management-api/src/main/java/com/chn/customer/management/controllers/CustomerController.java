package com.chn.customer.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.Customer;
import com.chn.customer.management.services.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {
	@Autowired
	CustomerService service;

	@GetMapping
	public ResponseEntity<List<Customer>> listCustomers(
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		return new ResponseEntity<List<Customer>>(service.listActiveCustomers(pageNumber, pageSize), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody(required = true) Customer customer) {
		return new ResponseEntity<Customer>(service.createCustomer(customer), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> retrieveCustomer(@PathVariable(name = "id") String id) {
		return new ResponseEntity<Customer>(service.retrieveCustomer(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id,
			@RequestBody(required = true) Customer customer) {
		return new ResponseEntity<Customer>(service.updateCustomer(id, customer), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id) {
		service.deleteCustomer(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/{id}/accounts")
	public ResponseEntity<List<Account>> listCustomerAccounts(@PathVariable(name = "id") String id) {
		return new ResponseEntity<List<Account>>(service.listCustomerAccounts(id), HttpStatus.OK);
	}
}
