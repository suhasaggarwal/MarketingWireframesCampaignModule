package com.websystique.springmvc.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampaignChannelData {

	public String getAdvertType() {
		return advertType;
	}

	public void setAdvertType(String advertType) {
		this.advertType = advertType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getkPI() {
		return kPI;
	}

	public void setkPI(String kPI) {
		this.kPI = kPI;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getChannelBudget() {
		return channelBudget;
	}

	public void setChannelBudget(String channelBudget) {
		this.channelBudget = channelBudget;
	}

	public String getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}

	String advertType;
	String startDate;
	String endDate;
	String kPI;
	String target;
	String bidmin;

	public String getBidmin() {
		return bidmin;
	}

	public void setBidmin(String bidmin) {
		this.bidmin = bidmin;
	}

	public String getBidmax() {
		return bidmax;
	}

	public void setBidmax(String bidmax) {
		this.bidmax = bidmax;
	}

	String bidmax;
	String channelBudget;
	String channelStatus;
    public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	String channelType;
}
