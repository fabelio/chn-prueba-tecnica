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

import com.chn.customer.management.entities.Check;
import com.chn.customer.management.services.CheckService;

@RestController
@RequestMapping("/checks")
@CrossOrigin
public class CheckController {

	@Autowired
	CheckService service;

	@GetMapping
	public ResponseEntity<List<Check>> listChecks(@RequestParam(required = false, defaultValue = "0") int pageNumber,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {
		return new ResponseEntity<List<Check>>(service.listChecks(pageNumber, pageSize), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Check> createCheck(@RequestBody(required = true) Check check) {
		return new ResponseEntity<Check>(service.createCheck(check), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Check> retrieveCheck(@PathVariable(name = "id") String id) {
		return new ResponseEntity<Check>(service.retrieveCheck(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Check> updateCheck(@PathVariable("id") String id, @RequestBody(required = true) Check check) {
		return new ResponseEntity<Check>(service.updateCheck(id, check), HttpStatus.OK);
	}

	@PutMapping("/{id}/status/{statusCode}")
	public ResponseEntity<Check> updateStatusCheck(@PathVariable("id") String id,
			@PathVariable("statusCode") String statusCode, @RequestParam(required = false) String reason) {
		return new ResponseEntity<Check>(service.updateStatus(id, statusCode, reason), HttpStatus.OK);
	}

}
