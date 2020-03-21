package com.vince.accessingdatajpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void setRepository(CustomerRepository repository) {
		this.repository = repository;
	}
}
