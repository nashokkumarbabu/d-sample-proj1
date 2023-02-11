package com.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.test.model.Address;
import com.test.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=AddressController.class)
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService service;
	
	@InjectMocks
	AddressController controller;

	
	@Test
	public void testAddAddress() throws Exception {
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
		String inputInJson = this.mapToJson(Address);
		
		String URI = "/Address";
		
		Mockito.when(service.addAddress(Mockito.any(Address.class))).thenReturn(Address);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

	@Test
	public void testGetAddressById() throws Exception {
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
		Mockito.when(service.getAddressById()).thenReturn(Optional.of(Address));
		
		String URI = "/Address/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(Address);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void testGetAllAddress() throws Exception {
		Address Address1 = new Address();
		Address1.setAddressId(undefined);
		
		
		Address Address2 = new Address();
		Address2.setAddressId(undefined);
		
		
		List<Address> AddressList = new ArrayList<>();
		AddressList.add(Address1);
		AddressList.add(Address2);
			
		Page<Address> pagedResponse = new PageImpl(AddressList);
		Mockito.when(service.getAllAddress(0,10,"")).thenReturn(pagedResponse);

		String URI = "/Addresss";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(pagedResponse);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}


	@Test
	public void testDeleteAddress() {
	    doThrow(new NullPointerException()).when(service).deleteAddressById();
	    assertThrows(
	      NullPointerException.class,
	      () -> {
	        controller.deleteAddressById();
	      }
	    );
	}


	@Test
    public void testUpdateAddress() throws Exception {
		Address Address = new Address();
		Address.setAddressId(undefined);
		
		
		String inputInJson = this.mapToJson(Address);
		
		String URI = "/Address/1";
		
		Mockito.when(service.updateAddress(Mockito.any(Address.class),Mockito.any())).thenReturn(Address);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}


	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC/Greenwich"));
        return objectMapper.writeValueAsString(object);
	}
	
}
