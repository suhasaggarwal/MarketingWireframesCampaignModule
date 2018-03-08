package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Segments {

	private String segments;
	
	public String getSegments() {
		return segments;
	}

	public void setSegments(String segments) {
		this.segments = segments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String id;
	
}
