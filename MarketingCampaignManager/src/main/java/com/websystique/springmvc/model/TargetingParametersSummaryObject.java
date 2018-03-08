package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TargetingParametersSummaryObject {

	
	List<LocationObject> locations = new ArrayList<LocationObject>();
	
	private List<Language> language;
	
	public List<Language> getLanguage() {
		return language;
	}
	public void setLanguage(List<Language> language) {
		this.language = language;
	}
	public List<OperatingSystem> getOperatingsystem() {
		return operatingsystem;
	}
	public void setOperatingsystem(List<OperatingSystem> operatingsystem) {
		this.operatingsystem = operatingsystem;
	}
	List<OperatingSystem> operatingsystem =  new ArrayList<OperatingSystem>();
	
	
	
	
	public List<LocationObject> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationObject> locations) {
		this.locations = locations;
	}
	public List<DeviceObject> getDeviceObject() {
		return deviceObject;
	}
	public void setDeviceObject(List<DeviceObject> deviceObject) {
		this.deviceObject = deviceObject;
	}
	public List<GenderObject> getGenderobj() {
		return genderobj;
	}
	public void setGenderobj(List<GenderObject> genderobj) {
		this.genderobj = genderobj;
	}
	public List<IncomeObject> getIncomeobj() {
		return incomeobj;
	}
	public void setIncomeobj(List<IncomeObject> incomeobj) {
		this.incomeobj = incomeobj;
	}
	public List<AgeObject> getAgeobj() {
		return ageobj;
	}
	public void setAgeobj(List<AgeObject> ageobj) {
		this.ageobj = ageobj;
	}
	List<DeviceObject> deviceObject= new ArrayList<DeviceObject>();
	List<GenderObject> genderobj = new ArrayList<GenderObject>();
	List<IncomeObject> incomeobj = new ArrayList<IncomeObject>();
	List<AgeObject> ageobj = new ArrayList<AgeObject>();
	List<AudienceSegmentObject> audiencesegmentobj = new ArrayList<AudienceSegmentObject>();
	public List<AudienceSegmentObject> getAudiencesegmentobj() {
		return audiencesegmentobj;
	}
	public void setAudiencesegmentobj(List<AudienceSegmentObject> audiencesegmentobj) {
		this.audiencesegmentobj = audiencesegmentobj;
	}
	
	List<MobileDeviceModelObject> mobiledeviceobj = new ArrayList<MobileDeviceModelObject>();
	public List<MobileDeviceModelObject> getMobiledeviceobj() {
		return mobiledeviceobj;
	}
	public void setMobiledeviceobj(List<MobileDeviceModelObject> mobiledeviceobj) {
		this.mobiledeviceobj = mobiledeviceobj;
	}
	
	
}
