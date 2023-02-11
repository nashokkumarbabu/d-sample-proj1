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


import com.test.model.Address;
import com.test.repo.AddressRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
	
	@Autowired
	private AddressService service;
	
	@MockBean
	private AddressRepo repo;
	

	@Test
	public void testAddAddress(){
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
	    Mockito.when(repo.save(Address)).thenReturn(Address);
	    
	    assertThat(service.addAddress(Address)).isEqualTo(Address);
	}

	
	@Test
	public void testGetAddressById(){
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
	    Mockito.when(repo.findById()).thenReturn(Optional.of(Address));

	    assertThat(service.getAddressById()).isEqualTo(Optional.of(Address));
	}

	
	@Test
	public void testGetAllAddress(){
		Address Address1 = new Address();
		Address1.setAddressId(undefined);
		

		Address Address2 = new Address();
		Address2.setAddressId(undefined);
		
		
		List<Address> AddressList = new ArrayList<>();
		AddressList.add(Address1);
		AddressList.add(Address2);
		
		Page<Address> pagedTasks = new PageImpl(AddressList);

		Mockito.when(repo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pagedTasks);
		
		assertThat(service.getAllAddress(0,10,"")).isEqualTo(pagedTasks);
	}
	

	@Test
	public void testDeleteAddressById(){
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
	    Mockito.when(repo.findById()).thenReturn(Optional.of(Address));
		Mockito.when(repo.existsById(Address.get())).thenReturn(false);
		
	    assertFalse(repo.existsById(Address.get()));
	}

    
	@Test
	public void testUpdateAddress() {
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
		Mockito.when(repo.findById()).thenReturn(Optional.of(Address));
		
		// TO Do...
		// Update the Address Object
		// Address.setFirstName("first1");
		Mockito.when(repo.save(Address)).thenReturn(Address);
		assertThat(service.updateAddress(Address,)).isEqualTo(Address);
	}
}
