package com.websystique.springmvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TargetingParametersSummary {

	private List<String> cities;
	
	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<String> getCountry() {
		return country;
	}

	public void setCountry(List<String> country) {
		this.country = country;
	}

	public List<String> getGender() {
		return gender;
	}

	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<String> getAgegroup() {
		return agegroup;
	}

	public void setAgegroup(List<String> agegroup) {
		this.agegroup = agegroup;
	}

	public List<String> getIncomelevel() {
		return incomelevel;
	}

	public void setIncomelevel(List<String> incomelevel) {
		this.incomelevel = incomelevel;
	}

	public List<String> getDevices() {
		return devices;
	}

	public void setDevices(List<String> devices) {
		this.devices = devices;
	}

	public List<String> getModelNames() {
		return modelNames;
	}

	public void setModelNames(List<String> modelNames) {
		this.modelNames = modelNames;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

	private List<String>states;
	private List<String>country;
	
	private List<String> gender;
	private List<String> agegroup;
	private List<String> incomelevel;
	

    private List<String> devices;
    private List<String> modelNames;
    
    private List<String> subcategories;
	 
	






}
