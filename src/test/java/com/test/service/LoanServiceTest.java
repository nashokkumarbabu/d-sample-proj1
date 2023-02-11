package com.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


import com.test.model.Loan;
import com.test.repo.LoanRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {
	
	@Autowired
	private LoanService service;
	
	@MockBean
	private LoanRepo repo;
	

	@Test
	public void testAddLoan(){
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
	    Mockito.when(repo.save(Loan)).thenReturn(Loan);
	    
	    assertThat(service.addLoan(Loan)).isEqualTo(Loan);
	}

	
	@Test
	public void testGetLoanById(){
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
	    Mockito.when(repo.findById()).thenReturn(Optional.of(Loan));

	    assertThat(service.getLoanById()).isEqualTo(Optional.of(Loan));
	}

	
	@Test
	public void testGetAllLoan(){
		Loan Loan1 = new Loan();
		Loan1.setLoanId(undefined);
		Loan1.setType(undefined);
		Loan1.setAddress(undefined);
		Loan1.setContent(undefined);
		

		Loan Loan2 = new Loan();
		Loan2.setLoanId(undefined);
		Loan2.setType(undefined);
		Loan2.setAddress(undefined);
		Loan2.setContent(undefined);
		
		
		List<Loan> LoanList = new ArrayList<>();
		LoanList.add(Loan1);
		LoanList.add(Loan2);
		
		Page<Loan> pagedTasks = new PageImpl(LoanList);

		Mockito.when(repo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pagedTasks);
		
		assertThat(service.getAllLoan(0,10,"")).isEqualTo(pagedTasks);
	}
	

	@Test
	public void testDeleteLoanById(){
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
	    Mockito.when(repo.findById()).thenReturn(Optional.of(Loan));
		Mockito.when(repo.existsById(Loan.get())).thenReturn(false);
		
	    assertFalse(repo.existsById(Loan.get()));
	}

    
	@Test
	public void testUpdateLoan() {
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
		Mockito.when(repo.findById()).thenReturn(Optional.of(Loan));
		
		// TO Do...
		// Update the Loan Object
		// Loan.setFirstName("first1");
		Mockito.when(repo.save(Loan)).thenReturn(Loan);
		assertThat(service.updateLoan(Loan,)).isEqualTo(Loan);
	}
}
