package com.websystique.springmvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampaignData {

	private List<Campaign> campaigns;

	public List<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	

	
	
	
}
