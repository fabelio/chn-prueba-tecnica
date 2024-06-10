package com.chn.customer.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chn.customer.management.entities.Account;
import com.chn.customer.management.entities.Check;
import com.chn.customer.management.entities.Checkbook;
import com.chn.customer.management.services.CheckbookService;

@RestController
@RequestMapping("/checkbooks")
@CrossOrigin
public class CheckbookController {

	@Autowired
	CheckbookService service;

	@GetMapping
	public ResponseEntity<List<Checkbook>> listCheckbooks(
			@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		return new ResponseEntity<List<Checkbook>>(service.listCheckbooks(pageNumber, pageSize), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Checkbook> createCheckbook(@RequestBody(required = true) Checkbook checkbook) {
		return new ResponseEntity<Checkbook>(service.createCheckbook(checkbook), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Checkbook> retrieveCheckbook(@PathVariable(name = "id") String id) {
		return new ResponseEntity<Checkbook>(service.retrieveCheckbook(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Checkbook> updateCheckbook(@PathVariable("id") String id,
			@RequestBody(required = true) Checkbook checkbook) {
		return new ResponseEntity<Checkbook>(service.updateCheckbook(id, checkbook), HttpStatus.OK);
	}

	@GetMapping("/{id}/checks")
	public ResponseEntity<List<Check>> retrieveCheckbookChecks(@PathVariable(name = "id") String id) {
		return new ResponseEntity<List<Check>>(service.listCheckCheckbooks(id), HttpStatus.OK);
	}

	@PutMapping("/{id}/status/{statusCode}")
	public ResponseEntity<Checkbook> updateStatusCheckbook(@PathVariable("id") String id,
			@PathVariable("statusCode") String statusCode, @RequestParam(required = false) String reason) {
		return new ResponseEntity<Checkbook>(service.updateStatus(id, statusCode, reason), HttpStatus.OK);
	}
}
