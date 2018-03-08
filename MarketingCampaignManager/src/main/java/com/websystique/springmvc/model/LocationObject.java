package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocationObject {

String share;
public String getShare() {
	return share;
}
public void setShare(String share) {
	this.share = share;
}
public String getChannelName() {
	return channelName;
}
public void setChannelName(String channelName) {
	this.channelName = channelName;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getLocationcode() {
	return locationcode;
}
public void setLocationcode(String locationcode) {
	this.locationcode = locationcode;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getScaledShare() {
	return scaledShare;
}
public void setScaledShare(String scaledShare) {
	this.scaledShare = scaledShare;
}
public String getCitylatlong() {
	return citylatlong;
}
public void setCitylatlong(String citylatlong) {
	this.citylatlong = citylatlong;
}
String channelName;
String city;
String locationcode;
String count;
String scaledShare;
String citylatlong;

private String cityId;


public String getCityId() {
	return cityId;
}
public void setCityId(String cityId) {
	this.cityId = cityId;
}
private String country;

public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
private String state;

private String stateId;

public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
}
public String getCountryId() {
	return countryId;
}
public void setCountryId(String countryId) {
	this.countryId = countryId;
}
private String countryId;


}
