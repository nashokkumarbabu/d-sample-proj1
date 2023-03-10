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

	private  Address;

	public  getAddress(){
		return Address;
	}

	public void setAddress( Address){
		this.Address = Address;
	}

	private  content;

	public  getContent(){
		return content;
	}

	public void setContent( content){
		this.content = content;
	}

	private  category;

	public  getCategory(){
		return category;
	}

	public void setCategory( category){
		this.category = category;
	}




	public Loan() {  }

	public Loan( 
        undefined loanId, 
        undefined type, 
        undefined Address, 
        undefined content, 
        undefined category
	) {
		this.loanId = loanId;
		this.type = type;
		this.Address = Address;
		this.content = content;
		this.category = category;
		
	}

	public String toString(){
		return loanId + " " + type + " " + Address + " " + content + " " + category;
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
		if (Address != null) {
			result = 31 * result + Address.hashCode();
		}
		if (content != null) {
			result = 31 * result + content.hashCode();
		}
		if (category != null) {
			result = 31 * result + category.hashCode();
		}
;
		return result;
	}	
}
