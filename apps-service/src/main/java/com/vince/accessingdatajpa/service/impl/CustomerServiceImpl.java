package com.vince.accessingdatajpa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vince.accessingdatajpa.entity.Customer;
import com.vince.accessingdatajpa.repository.CustomerRepository;
import com.vince.accessingdatajpa.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository repository;

	public List<Customer> findByLastName(String lastName) {
		return repository.findByLastName(lastName);
	}

	public Customer findById(long id) {
		return repository.findById(id);
	}

	public CustomerRepository getRepository() {
		return repository;
	}

}
