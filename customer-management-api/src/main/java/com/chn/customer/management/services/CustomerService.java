package com.chn.customer.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.Customer;
import com.chn.customer.management.repositories.AccountRepository;
import com.chn.customer.management.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;
	@Autowired
	AccountRepository accountRepository;

	public List<Customer> listCustomer(int pageNumber, int pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id").descending())).getContent();
	}

	public List<Customer> listActiveCustomers(int pageNumber, int pageSize) {
		return repository.findByDisabledOrDisabledIsNull(0l,
				PageRequest.of(pageNumber, pageSize, Sort.by("id").descending()));
	}

	public Customer createCustomer(Customer customer) {
		return repository.saveAndFlush(customer);
	}

	public Customer retrieveCustomer(String customerId) {
		return repository.findById(Long.valueOf(customerId)).orElse(null);
	}

	public Customer updateCustomer(String customerId, Customer customer) {
		Customer customerEntity = retrieveCustomer(customerId);

		if (customerEntity == null)
			throw new RuntimeException("Customer dont exist");
		customer.setId(Long.valueOf(customerId));
		return repository.saveAndFlush(customer);

	}

	public void deleteCustomer(String customerId) {
		Customer customerEntity = retrieveCustomer(customerId);

		if (customerEntity == null)
			throw new RuntimeException("Customer doesnt exist");
		customerEntity.setDisabled(Long.valueOf(1));
		repository.saveAndFlush(customerEntity);
	}

	public List<Account> listCustomerAccounts(String customerId) {
		return accountRepository.findByCustomerId(Long.valueOf(customerId), null);
	}

}
