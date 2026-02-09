package com.order_management_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order_management_api.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

	Optional<Customer> findById(Long customerId);

}
