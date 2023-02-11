package com.test.repo;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.test.model.Address;

public class AddressSpecificationsBuilder {
	
	private final List<SearchCriteria> params;
	
	public AddressSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public AddressSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Address> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
          .map(AddressSpecification::new)
          .collect(Collectors.toList());
        
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
              .isOrPredicate()
                ? Specification.where(result)
                  .or(specs.get(i))
                : Specification.where(result)
                  .and(specs.get(i));
        }       
        return result;
    }
}
