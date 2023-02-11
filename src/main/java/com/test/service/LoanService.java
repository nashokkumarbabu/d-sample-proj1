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

import com.test.repo.LoanSpecificationsBuilder;
import com.test.model.Loan;
import com.test.repo.LoanRepo;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepo repo;
	
	public Page<Loan> getAllLoan(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC,sortBy));
		return repo.findAll(paging);
	}

	public Page<Loan> customSearch(String search,int pageNo, int pageSize, String sortBy, String sortOrder){
		LoanSpecificationsBuilder builder = new LoanSpecificationsBuilder();
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
        Specification<Loan> spec = builder.build();
        return repo.findAll(spec, paging);
	}

	public Loan addLoan(Loan Loan) {
		System.out.println("in service");
		return repo.save(Loan);
	}

	public Optional<Loan> getLoanById( id) {
		return repo.findById(id);
	}

	public void deleteLoanById( id) {
		try{
			repo.deleteById(id);
		} catch(Exception e){
			
		}
	}

	public Loan updateLoan(Loan Loan, id){
		Loan o = repo.findById(id).orElse(new Loan());
			
			o.setLoanId(Loan.getLoanId());
			
			o.setType(Loan.getType());
			
			o.setAddress(Loan.getAddress());
			
			o.setContent(Loan.getContent());
		
		return repo.save(o);
	}


}
