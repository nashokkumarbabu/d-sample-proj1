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

import com.test.model.Address;
import com.test.service.AddressService;
import io.swagger.annotations.ApiOperation;


@RestController
public class AddressController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AddressService service;
	
	@GetMapping("/Addresss")
	@ResponseBody
	public ResponseEntity<Page<Address>> getAllAddress(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
	@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
	@RequestParam(value = "sortBy", defaultValue = "") String sortBy){
		log.info("controller get all Address method");
		return new ResponseEntity<>(service.getAllAddress(pageNo, pageSize, sortBy),HttpStatus.OK);
	}

	@PostMapping("/Address/search")
	@ApiOperation(value = "Get Address by custom search")
    @ResponseBody
    public ResponseEntity<Page<Address>> search(@RequestBody SearchEntity searchEntity) {
		log.info("controller custom search Address method");
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
	@PostMapping("/Address")
	@ApiOperation(value = "Post Address api")
	@ResponseBody
	public Address addAddress(@RequestBody Address Address) {
		log.info("controller create Address");
		return service.addAddress(Address);
	}
	
	@GetMapping("/Address/{id}")
	@ApiOperation(value = "Get Address by ID api")
	@ResponseBody
	public Optional<Address> getAddressById(@PathVariable  id) {
		log.info("controller find by Id method");
		return service.getAddressById(id);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@DeleteMapping("/Address/{id}")
	@ApiOperation(value = "Delete Address by ID api")
	@ResponseBody
	public Map<String, String> deleteAddressById(@PathVariable  id) {
		log.info("controller delete Address method");
		service.deleteAddressById(id);
		HashMap<String,String> res = new HashMap<String, String>();
        res.put("message","done");
		return  res;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@PutMapping("/Address/{id}")
	@ApiOperation(value = "Update Address api")
	@ResponseBody
	public Address updateAddress(@RequestBody Address Address,@PathVariable  id){
		log.info("controller update Address method");
		return service.updateAddress(Address,id);
	}



}
