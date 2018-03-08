package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Mobiledevicemodel {

	private String mobiledevicemodel;

	public String getMobiledevicemodel() {
		return mobiledevicemodel;
	}

	public void setMobiledevicemodel(String mobiledevicemodel) {
		this.mobiledevicemodel = mobiledevicemodel;
	}
	
	
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
}
