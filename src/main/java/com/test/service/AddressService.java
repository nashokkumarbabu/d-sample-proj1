package com.test.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.test.repo.AddressSpecificationsBuilder;
import com.test.model.Address;
import com.test.repo.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo repo;
	
	public Page<Address> getAllAddress(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC,sortBy));
		return repo.findAll(paging);
	}

	public Page<Address> customSearch(String search,int pageNo, int pageSize, String sortBy, String sortOrder){
		AddressSpecificationsBuilder builder = new AddressSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
		Pageable paging;
		if (sortOrder == "DESC") {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
		} else {
			paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
		}
        Specification<Address> spec = builder.build();
        return repo.findAll(spec, paging);
	}

	public Address addAddress(Address Address) {
		System.out.println("in service");
		return repo.save(Address);
	}

	public Optional<Address> getAddressById( id) {
		return repo.findById(id);
	}

	public void deleteAddressById( id) {
		try{
			repo.deleteById(id);
		} catch(Exception e){
			
		}
	}

	public Address updateAddress(Address Address, id){
		Address o = repo.findById(id).orElse(new Address());
			
			o.setAddressId(Address.getAddressId());
		
		return repo.save(o);
	}


}
