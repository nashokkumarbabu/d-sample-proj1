package com.test.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.test.model.Loan;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan>{

}
