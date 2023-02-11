package com.test.controller;

import java.util.*;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.test.model.SearchEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.model.Loan;
import com.test.service.LoanService;
import io.swagger.annotations.ApiOperation;


@RestController
public class LoanController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoanService service;
	
	@GetMapping("/Loans")
	@ResponseBody
	public ResponseEntity<Page<Loan>> getAllLoan(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
	@RequestParam(value = "sortBy", defaultValue = "") String sortBy){
		log.info("controller get all Loan method");
		return new ResponseEntity<>(service.getAllLoan(pageNo, pageSize, sortBy),HttpStatus.OK);
	}

	@PostMapping("/Loan/search")
	@ApiOperation(value = "Get Loan by custom search")
    @ResponseBody
    public ResponseEntity<Page<Loan>> search(@RequestBody SearchEntity searchEntity) {
		log.info("controller custom search Loan method");
		int pageNo = searchEntity.getPageNo() , pageSize = searchEntity.getPageSize();
		String search = searchEntity.getSearch(),sortBy = searchEntity.getSortBy(), sortOrder = searchEntity.getSortOrder();
		if(pageSize == 0) {
			pageSize = 10;
		}
		if(sortBy == null) {
			sortBy = "";
		}
		if(sortOrder == null) {
			sortOrder = "DESC";
		}
		return new ResponseEntity<>(service.customSearch(search, pageNo, pageSize, sortBy, sortOrder),HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@PostMapping("/Loan")
	@ApiOperation(value = "Post Loan api")
	@ResponseBody
	public Loan addLoan(@RequestBody Loan Loan) {
		log.info("controller create Loan");
		return service.addLoan(Loan);
	}
	
	@GetMapping("/Loan/{id}")
	@ApiOperation(value = "Get Loan by ID api")
	@ResponseBody
	public Optional<Loan> getLoanById(@PathVariable  id) {
		log.info("controller find by Id method");
		return service.getLoanById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@DeleteMapping("/Loan/{id}")
	@ApiOperation(value = "Delete Loan by ID api")
	@ResponseBody
	public Map<String, String> deleteLoanById(@PathVariable  id) {
		log.info("controller delete Loan method");
		service.deleteLoanById(id);
		HashMap<String,String> res = new HashMap<String, String>();
        res.put("message","done");
		return  res;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@PutMapping("/Loan/{id}")
	@ApiOperation(value = "Update Loan api")
	@ResponseBody
	public Loan updateLoan(@RequestBody Loan Loan,@PathVariable  id){
		log.info("controller update Loan method");
		return service.updateLoan(Loan,id);
	}



}
