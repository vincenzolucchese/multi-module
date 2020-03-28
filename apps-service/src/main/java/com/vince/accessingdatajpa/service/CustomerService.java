package com.vince.accessingdatajpa.service;

import java.util.List;

import com.vince.accessingdatajpa.entity.CustomerModel;

public interface CustomerService {

	List<CustomerModel> findByLastName(String lastName);

	CustomerModel findById(long id);
}
