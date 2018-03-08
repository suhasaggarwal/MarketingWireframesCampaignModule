package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IncomeObject {

	private String income;

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

    public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	private String Id;


}
