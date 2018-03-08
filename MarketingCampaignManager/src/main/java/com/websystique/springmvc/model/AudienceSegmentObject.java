package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AudienceSegmentObject {

	private String audienceSegment;
	public String getAudienceSegment() {
		return audienceSegment;
	}
	public void setAudienceSegment(String audienceSegment) {
		this.audienceSegment = audienceSegment;
	}
	
	private List<Subcategory> subcategory = new ArrayList<Subcategory>();
	
	public List<Subcategory> getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(List<Subcategory> subcategory) {
		this.subcategory = subcategory;
	}

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
