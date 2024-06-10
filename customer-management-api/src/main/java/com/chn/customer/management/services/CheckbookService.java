package com.chn.customer.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.AccountStatusHist;
import com.chn.customer.management.entities.Check;
import com.chn.customer.management.entities.Checkbook;
import com.chn.customer.management.entities.CheckbookStatusHist;
import com.chn.customer.management.repositories.CheckbookRepository;
import com.chn.customer.management.repositories.CheckbookStatusRepository;

@Service
public class CheckbookService {

	@Autowired
	CheckbookRepository repository;
	@Autowired
	CheckbookStatusRepository statusRepository;
	@Autowired
	CheckService checkService;

	public List<Checkbook> listCheckbooks(int pageNumber, int pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id"))).getContent();
	}

	public Checkbook retrieveCheckbook(String checkbookId) {
		return repository.findById(Long.valueOf(checkbookId)).orElse(null);
	}

	public List<Checkbook> findCheckbookByAccount(String accountId) {
		return repository.findByAccountId(Long.valueOf(accountId), null);
	}

	public Checkbook createCheckbook(Checkbook checkbook) {
		if (checkbook.getCurrentStatus() == null) {
			CheckbookStatusHist current = new CheckbookStatusHist();
			current.setCheckbookStatus(statusRepository.findByCode("activa"));
			current.setCheckbook(checkbook);
			current.setReason("Apertura de cuenta");
			checkbook.setCurrentStatus(current);
		}

		checkbook = repository.saveAndFlush(checkbook);

		for (int i = 1; i <= checkbook.getCheckQuantity(); i++) {
			createCheck(checkbook, Long.valueOf(i));
		}

		return checkbook;
	}

	private Check createCheck(Checkbook checkbook, Long checkNumber) {
		Check check = new Check();
		check.setNumber(checkNumber);
		check.setCheckbook(checkbook);
		return checkService.createCheck(check);
	}

	public Checkbook updateCheckbook(String checkbookId, Checkbook checkbook) {
		Checkbook checkbookEntity = retrieveCheckbook(checkbookId);
		if (checkbookEntity == null)
			throw new RuntimeException("Checkbook doesnt exist");

		checkbook.setId(Long.valueOf(checkbookId));
		return repository.saveAndFlush(checkbook);
	}

	public List<Check> listCheckCheckbooks(String checkbookId) {
		return checkService.listCheckCheckbook(Long.valueOf(checkbookId));
	}

	public Checkbook updateStatus(String checkbookId, String statusCode, String reason) {
		Checkbook checkbook = retrieveCheckbook(checkbookId);
		if (checkbook == null)
			throw new RuntimeException("checkbook doesnt exist");
		if (!checkbook.getCurrentStatus().getCheckbookStatus().getCode().equals(statusCode)) {
			CheckbookStatusHist current = new CheckbookStatusHist();
			current.setCheckbookStatus(statusRepository.findByCode(statusCode));
			current.setCheckbook(checkbook);
			current.setReason(reason);
			checkbook.setCurrentStatus(current);
		}
		repository.saveAndFlush(checkbook);
		return checkbook;

	}
}
