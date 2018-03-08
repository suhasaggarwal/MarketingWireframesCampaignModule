package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Site {

	private String siteId;
	
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getPrivatemarketplacename() {
		return privatemarketplacename;
	}

	public void setPrivatemarketplacename(String privatemarketplacename) {
		this.privatemarketplacename = privatemarketplacename;
	}

	private String privatemarketplacename;

}
