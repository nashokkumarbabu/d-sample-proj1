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


import com.test.model.Loan;
import com.test.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=LoanController.class)
public class LoanControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoanService service;
	
	@InjectMocks
	LoanController controller;

	
	@Test
	public void testAddLoan() throws Exception {
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
		String inputInJson = this.mapToJson(Loan);
		
		String URI = "/Loan";
		
		Mockito.when(service.addLoan(Mockito.any(Loan.class))).thenReturn(Loan);
		
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
	public void testGetLoanById() throws Exception {
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
		Mockito.when(service.getLoanById()).thenReturn(Optional.of(Loan));
		
		String URI = "/Loan/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(Loan);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void testGetAllLoan() throws Exception {
		Loan Loan1 = new Loan();
		Loan1.setLoanId(undefined);
		Loan1.setType(undefined);
		Loan1.setType(undefined);
		Loan1.setType(undefined);
		Loan1.setType(undefined);
		Loan1.setAddress(undefined);
		Loan1.setAddress(undefined);
		Loan1.setContent(undefined);
		
		
		Loan Loan2 = new Loan();
		Loan2.setLoanId(undefined);
		Loan2.setType(undefined);
		Loan2.setType(undefined);
		Loan2.setType(undefined);
		Loan2.setType(undefined);
		Loan2.setAddress(undefined);
		Loan2.setAddress(undefined);
		Loan2.setContent(undefined);
		
		
		List<Loan> LoanList = new ArrayList<>();
		LoanList.add(Loan1);
		LoanList.add(Loan2);
			
		Page<Loan> pagedResponse = new PageImpl(LoanList);
		Mockito.when(service.getAllLoan(0,10,"")).thenReturn(pagedResponse);

		String URI = "/Loans";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(pagedResponse);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}


	@Test
	public void testDeleteLoan() {
	    doThrow(new NullPointerException()).when(service).deleteLoanById();
	    assertThrows(
	      NullPointerException.class,
	      () -> {
	        controller.deleteLoanById();
	      }
	    );
	}


	@Test
    public void testUpdateLoan() throws Exception {
		Loan Loan = new Loan();
		Loan.setLoanId(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setType(undefined);
		Loan.setAddress(undefined);
		Loan.setAddress(undefined);
		Loan.setContent(undefined);
		
		
		String inputInJson = this.mapToJson(Loan);
		
		String URI = "/Loan/1";
		
		Mockito.when(service.updateLoan(Mockito.any(Loan.class),Mockito.any())).thenReturn(Loan);
		
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
