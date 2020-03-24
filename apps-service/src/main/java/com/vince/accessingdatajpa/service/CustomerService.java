package com.vince.accessingdatajpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vince.accessingdatajpa.entity.Customer;

public interface CustomerService {

	List<Customer> findByLastName(String lastName);

	Customer findById(long id);
}
