package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AgeObject {

   public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

private String age;

private String id;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

}

