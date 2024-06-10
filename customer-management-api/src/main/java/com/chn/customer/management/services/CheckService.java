package com.chn.customer.management.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chn.customer.management.entities.Check;
import com.chn.customer.management.entities.CheckStatusHist;
import com.chn.customer.management.entities.Checkbook;
import com.chn.customer.management.entities.CheckbookStatusHist;
import com.chn.customer.management.repositories.CheckRepository;
import com.chn.customer.management.repositories.CheckStatusRepository;

@Service
public class CheckService {

	@Autowired
	CheckRepository repository;
	@Autowired
	CheckStatusRepository statusRepository;

	public List<Check> listChecks(int pageNumber, int pageSize) {
		return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id"))).getContent();
	}

	public Check retrieveCheck(String checkId) {
		return repository.findById(Long.valueOf(checkId)).orElse(null);
	}

	public Check createCheck(Check check) {
		if (check.getCurrentStatus() == null) {
			CheckStatusHist current = new CheckStatusHist();
			current.setCheckStatus(statusRepository.findByCode("activo"));
			current.setCheck(check);
			current.setReason("Creacion de Chequera");
			check.setCurrentStatus(current);
		}
		return repository.saveAndFlush(check);
	}

	public Check updateCheck(String checkId, Check check) {
		Check checkbookEntity = retrieveCheck(checkId);
		if (checkbookEntity == null)
			throw new RuntimeException("Check doesnt exist");

		check.setId(Long.valueOf(checkId));
		return repository.saveAndFlush(check);
	}

	public List<Check> listCheckCheckbook(Long checkbookId) {
		return repository.findByCheckbookId(checkbookId);
	}

	public Check updateStatus(String checkId, String statusCode, String reason) {
		Check check = retrieveCheck(checkId);
		if (check == null)
			throw new RuntimeException("checkbook doesnt exist");
		if (!check.getCurrentStatus().getCheckStatus().getCode().equals(statusCode)) {
			CheckStatusHist current = new CheckStatusHist();
			current.setCheckStatus(statusRepository.findByCode(statusCode));
			current.setCheck(check);
			current.setReason(reason);
			check.setCurrentStatus(current);
		}
		repository.saveAndFlush(check);
		return check;
	}
	
	public Check collectCheck(String checkId, BigDecimal amount) {
		Check check = retrieveCheck(checkId);
		if(check == null)
			throw new RuntimeException("No existe cheque");
		
		check.setAmount(amount);
		
		CheckStatusHist current = new CheckStatusHist();
		current.setCheck(check);
		current.setCheckStatus(statusRepository.findByCode("cobrado"));
		current.setReason("Cheque cobrado");
		
		check.setCurrentStatus(current);
		return repository.saveAndFlush(check);
	}

}
