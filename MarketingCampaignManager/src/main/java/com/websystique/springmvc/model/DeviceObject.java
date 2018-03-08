package com.websystique.springmvc.model;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.parsing.Location;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeviceObject {

	private String share;
	
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

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
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

	

	private String channelName;
		
	private String device_type;
	
	private String count;
	
	private String scaledShare;
	
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}		








}
