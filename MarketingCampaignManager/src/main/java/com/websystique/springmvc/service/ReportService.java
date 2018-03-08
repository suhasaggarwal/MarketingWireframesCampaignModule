package com.websystique.springmvc.service;



import java.util.List;

import com.websystique.springmvc.model.CampaignChannelData;
import com.websystique.springmvc.model.CampaignData;
import com.websystique.springmvc.model.DMPTag;
import com.websystique.springmvc.model.Reports;
import com.websystique.springmvc.model.Segments;
import com.websystique.springmvc.model.Site;
import com.websystique.springmvc.model.TargetingParametersSummary;
import com.websystique.springmvc.model.TargetingParametersSummaryObject;



public interface ReportService {
	
	List<Reports> extractReports(long id, String dateRange, String campaignId);
	 
	List<String> extractCampaignIds(long id,String dateRange);

    CampaignChannelData getcampaignChannelData(String campaignId,String channelType,String accessToken);
	
	DMPTag getcampaignChannelDMPTag(String campaignId);
	
    CampaignData getChannelCampaignId(String channelType,String accessToken);

    List<Segments> getConvertedCategories(List<String> categoryList, String channelSource, String channelTarget, String categoryType); 
   
    TargetingParametersSummary getTargetingParameters(String siteId);
    
    TargetingParametersSummaryObject getTargetingParametersData(String siteId);
    
    Site getPrivateAudienceMarketPlace(); 

    String getReach(String siteId,String subcategory,String device,String gender,String agegroup,String incomelevel,String city,String state,String country);


}
