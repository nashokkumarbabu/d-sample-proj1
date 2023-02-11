package com.test.model;

import java.util.*;
import java.sql.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Loan {

	private  loanId;

	public  getLoanId(){
		return loanId;
	}

	public void setLoanId( loanId){
		this.loanId = loanId;
	}

	private  type;

	public  getType(){
		return type;
	}

	public void setType( type){
		this.type = type;
	}

	private  type;

	public  getType(){
		return type;
	}

	public void setType( type){
		this.type = type;
	}

	private  type;

	public  getType(){
		return type;
	}

	public void setType( type){
		this.type = type;
	}

	private  type;

	public  getType(){
		return type;
	}

	public void setType( type){
		this.type = type;
	}

	private  Address;

	public  getAddress(){
		return Address;
	}

	public void setAddress( Address){
		this.Address = Address;
	}

	private  Address;

	public  getAddress(){
		return Address;
	}

	public void setAddress( Address){
		this.Address = Address;
	}




	public Loan() {  }

	public Loan( 
        undefined loanId, 
        undefined type, 
        undefined type, 
        undefined type, 
        undefined type, 
        undefined Address, 
        undefined Address
	) {
		this.loanId = loanId;
		this.type = type;
		this.type = type;
		this.type = type;
		this.type = type;
		this.Address = Address;
		this.Address = Address;
		
	}

	public String toString(){
		return loanId + " " + type + " " + type + " " + type + " " + type + " " + Address + " " + Address;
	}

	@Override
	public final int hashCode() {
		int result = 17;
		if (loanId != null) {
			result = 31 * result + loanId.hashCode();
		}
		if (type != null) {
			result = 31 * result + type.hashCode();
		}
		if (type != null) {
			result = 31 * result + type.hashCode();
		}
		if (type != null) {
			result = 31 * result + type.hashCode();
		}
		if (type != null) {
			result = 31 * result + type.hashCode();
		}
		if (Address != null) {
			result = 31 * result + Address.hashCode();
		}
		if (Address != null) {
			result = 31 * result + Address.hashCode();
		}
;
		return result;
	}	
}
