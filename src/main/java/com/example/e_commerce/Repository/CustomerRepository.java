package com.example.e_commerce.Repository;


import com.example.e_commerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
  Customer findByemailId(String emailId);
}
