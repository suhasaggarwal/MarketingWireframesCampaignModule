package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenderObject {

	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
