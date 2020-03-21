package com.vince.accessingdatajpa;

import java.util.List;

import org.springframework.stereotype.Service;

public interface CustomerService {

	List<Customer> findByLastName(String lastName);

	Customer findById(long id);
}
