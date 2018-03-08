package com.websystique.springmvc.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.model.CampaignChannelData;
import com.websystique.springmvc.model.CampaignData;
import com.websystique.springmvc.model.DMPTag;
import com.websystique.springmvc.model.Segments;
import com.websystique.springmvc.model.Site;
import com.websystique.springmvc.model.TargetingParametersSummary;
import com.websystique.springmvc.model.TargetingParametersSummaryObject;
import com.websystique.springmvc.service.ReportService;

//Application code - b7

@RestController
public class ReportRestController {

	
	//Controller receives data from Service API and generates a JSON feed to feed into visualisation component.
	//API format <Report/Reportcode/Daterange/CampaignId, as campaignId is specified after daterange, selected campaign is picked and campaign report is shown channel wise.	
	
	
	@Autowired
	ReportService reportService;  //Service which will do all data retrieval/manipulation work

	//-------------------Retrieve Report with Id--------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/campaignChannelDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping(value = "/campaignChannelDetails/{campaign_id}/{channel_type}/{access_token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<CampaignChannelData> getReport(@PathVariable("campaign_id") String campaignId,@PathVariable("channel_type") String channelType,@PathVariable("access_token") String accessToken) {
		//System.out.println("Fetching Report with id " + id);
	public ResponseEntity<CampaignChannelData> getCampaignChannelDetails(@RequestParam("campaign_id") String campaignId,@RequestParam("channel_type") String channelType,@RequestParam("access_token") String accessToken) {	
		
		CampaignChannelData obj = reportService.getcampaignChannelData(campaignId,channelType,accessToken);
		if (obj == null) {
		//	System.out.println("Report with id " + id + " not found");
			return new ResponseEntity<CampaignChannelData>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CampaignChannelData>(obj, HttpStatus.OK);
	}

	//This API gives list of campaignIds active in that date range.
	@CrossOrigin
	@RequestMapping(value = "/getdmpTag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DMPTag> getDmpTagdata(@RequestParam("campaign_id") String campaignId) {
		
		DMPTag dmpTag = reportService.getcampaignChannelDMPTag(campaignId);
		if (dmpTag == null){
		   
			return new ResponseEntity<DMPTag>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DMPTag>(dmpTag, HttpStatus.OK);
}
	
	@CrossOrigin
	@RequestMapping(value = "/getChannelCampaignId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CampaignData> getChannelCampaignId(@RequestParam("channel_type") String channelType,@RequestParam("access_token") String accessToken) {
		
		CampaignData campaigndata = reportService.getChannelCampaignId(channelType,accessToken);
		if (campaigndata  == null){
		   
			return new ResponseEntity<CampaignData>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CampaignData>(campaigndata, HttpStatus.OK);
}
	@Pattern(regexp=".*%[0-9a-fA-F]+|")
	@CrossOrigin
	@RequestMapping(value = "/getChannelPortedCategories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Segments>> getChannelCampaignId(@RequestParam("source_channel") String sourceChannel,@RequestParam("target_channel") String targetChannel, @Valid @RequestParam("categoryList") String categoryList, @RequestParam("categoryType") String categoryType ) {
		
		categoryList = URLDecoder.decode(categoryList);
		String[] categoriesList = categoryList.split(";");
		List<String> categoryv1 = new ArrayList<String>();
		
		for(int i=0;i<categoriesList.length;i++)
		{
			categoryv1.add(categoriesList[i]);
			
		}
		
		List<Segments> portedcategoryList = reportService.getConvertedCategories(categoryv1, sourceChannel,targetChannel,categoryType);
		if (portedcategoryList  == null){
		   
			return new ResponseEntity<List<Segments>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Segments>>(portedcategoryList, HttpStatus.OK);
}
	

	
	@CrossOrigin
	@RequestMapping(value = "/getTargetingSummary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TargetingParametersSummaryObject> getTargetingSummary(@RequestParam("siteId") String siteId){
		
		TargetingParametersSummaryObject targparamsummary =  reportService.getTargetingParametersData(siteId);
		if (targparamsummary  == null){
		   
			return new ResponseEntity<TargetingParametersSummaryObject>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TargetingParametersSummaryObject>(targparamsummary, HttpStatus.OK);
}
	
	
	@CrossOrigin
	@RequestMapping(value = "/getPrivateAudienceMarketplaceList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Site> getPrivateAudienceMarketplace(){
		
		Site sitelist =  reportService.getPrivateAudienceMarketPlace();
		if (sitelist  == null){
		   
			return new ResponseEntity<Site>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Site>(sitelist, HttpStatus.OK);
}
	
	@CrossOrigin
	@RequestMapping(value = "/getReach", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getReach(@RequestParam("siteId") String siteId,@RequestParam("subcategory") String subcategory, @RequestParam("city") String city, @RequestParam("state") String state,@RequestParam("country") String country, @RequestParam("device") String device,  @RequestParam("agegroup") String agegroup,  @RequestParam("gender") String gender, @RequestParam("incomelevel") String incomelevel){
		
		String output =  reportService.getReach(siteId,subcategory,device,gender,agegroup,incomelevel,city,state,country);
		if (output  == null){
		   
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(output, HttpStatus.OK);
}
	
	
	
}
