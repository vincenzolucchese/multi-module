package com.vince.accessingdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String code;
	private String description;

	protected CategoryModel() {}

	public CategoryModel(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CategoryModel [id=" + id + ", code=" + code + ", description=" + description + "]";
	}


}
