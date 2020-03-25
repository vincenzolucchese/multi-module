package com.vince.accessingdatajpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.vince.accessingdatajpa.entity.CustomerModel;

public interface CustomerRepository extends CrudRepository<CustomerModel, Long> {

	List<CustomerModel> findByLastName(String lastName);

	CustomerModel findById(long id);
}
