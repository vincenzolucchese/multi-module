package com.vince.accessingdatajpa.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategoryModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String code;
	private String description;
	
	@OneToMany(mappedBy = "codeCategory")
    private Set<DemoAppModel> demoApp;

	protected CategoryModel() {}
	
	public CategoryModel(Long id) {
		this.id = id;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<DemoAppModel> getDemoApp() {
		return demoApp;
	}

	public void setDemoApp(Set<DemoAppModel> demoApp) {
		this.demoApp = demoApp;
	}
	


}
