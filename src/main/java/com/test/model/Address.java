package com.test.model;

import java.util.*;
import java.sql.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Address {

	private  addressId;

	public  getAddressId(){
		return addressId;
	}

	public void setAddressId( addressId){
		this.addressId = addressId;
	}




	public Address() {  }

	public Address( 
        undefined addressId
	) {
		this.addressId = addressId;
		
	}

	public String toString(){
		return addressId;
	}

	@Override
	public final int hashCode() {
		int result = 17;
		if (addressId != null) {
			result = 31 * result + addressId.hashCode();
		}
;
		return result;
	}	
}
