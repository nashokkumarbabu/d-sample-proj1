package com.test.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.test.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address>{

}
