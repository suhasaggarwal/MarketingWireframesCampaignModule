package com.websystique.springmvc.service;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuberoot.util.LoadMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springmvc.model.AgeObject;
import com.websystique.springmvc.model.AudienceSegmentObject;
import com.websystique.springmvc.model.Campaign;
import com.websystique.springmvc.model.CampaignChannelData;
import com.websystique.springmvc.model.CampaignData;
import com.websystique.springmvc.model.DMPTag;
import com.websystique.springmvc.model.DeviceObject;
import com.websystique.springmvc.model.GenderObject;
import com.websystique.springmvc.model.IncomeObject;
import com.websystique.springmvc.model.Language;
import com.websystique.springmvc.model.LocationObject;
import com.websystique.springmvc.model.MobileDeviceModelObject;
import com.websystique.springmvc.model.OperatingSystem;
import com.websystique.springmvc.model.Reports;
import com.websystique.springmvc.model.Segments;
import com.websystique.springmvc.model.Site;
import com.websystique.springmvc.model.Subcategory;
import com.websystique.springmvc.model.TargetingParametersSummary;
import com.websystique.springmvc.model.TargetingParametersSummaryObject;


//Benchmarking data for channel wise performance corresponding to specific campaignId 



@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService{

	
	public static Map<String,String> IABGoogleMapInMarket = new HashMap<String,String>();
    public static Map<String,String> GoogleIABMapInMarket = new HashMap<String,String>();
	
	public static Map<String,String> IABGoogleMapAffinity = new HashMap<String,String>();
	public static Map<String,String> GoogleIABMapAffinity = new HashMap<String,String>();
	
	public static Map<String,String> IABFacebookMap = new HashMap<String,String>();
	public static Map<String,String> FacebookIABMap = new HashMap<String,String>();
	
	
	public static Map<String,String> IABFormatMap1 = new HashMap<String,String>();
    public static Map<String,String> IABFormatMap2 = new HashMap<String,String>();
	
	public static Map<String,String> GoogleInMarketFormatMap1 = new HashMap<String,String>();
	public static Map<String,String> GoogleInMarketFormatMap2 = new HashMap<String,String>();
	
	public static Map<String,String> GoogleAffinityFormatMap1 = new HashMap<String,String>();
	public static Map<String,String> GoogleAffinityFormatMap2 = new HashMap<String,String>();
	
	
	
	public static Map<String,String> FacebookFormatMap1 = new HashMap<String,String>();
	public static Map<String,String> FacebookFormatMap2 = new HashMap<String,String>();
	
	
	public static Map<String,String> FacebookIdMap = new HashMap<String,String>();
	public static Map<String,String> IdFacebookMap = new HashMap<String,String>();
	
	public static Map<String,String> GoogleIdMap = new HashMap<String,String>();
	public static Map<String,String> IdGoogleMap = new HashMap<String,String>();
	
	public static Map<String,String> GoogleIdAffinityMap = new HashMap<String,String>();
	public static Map<String,String> IdGoogleAffinityMap = new HashMap<String,String>();
	
	
	public static Map<String,String> IABIdMap = new HashMap<String,String>();
	public static Map<String,String> IdIABMap = new HashMap<String,String>();
	
	
	static{
		// TODO Auto-generated method stub
		BufferedReader br1 = null;
		FileReader fr1 = null;
        String key1 = null;
        String value1 = null;
		
		
		
			//br = new BufferedReader(new FileReader(FILENAME));
			try {
				fr1 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/MarketingIABv2.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br1 = new BufferedReader(fr1);

			String sCurrentLine;

			int i=0;
			
			try {
				while ((sCurrentLine = br1.readLine()) != null) {

					if(i%2==0)
					key1 = sCurrentLine.trim();
					//System.out.println(key1);
					if(i%2!=0)
					value1 = sCurrentLine.trim();
					//System.out.println(value1);	
					if(i%2!=0){	
					IABGoogleMapInMarket.put(value1,key1);
					GoogleIABMapInMarket.put(key1,value1);
								
					}
             
				  i++;
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				br1.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  	
	
	
			BufferedReader br2 = null;
			FileReader fr2 = null;
	        String key2 = null;
	        String value2 = null;
			
			
				//br = new BufferedReader(new FileReader(FILENAME));
				try {
					fr2 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/AffinityIABv2.txt");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				br2 = new BufferedReader(fr2);

				String sCurrentLine1;

				int i1=0;
				
				try {
					while ((sCurrentLine1 = br2.readLine()) != null) {

						if(i1%2==0)
						key2 = sCurrentLine1.trim();
						//System.out.println(key2);
						if(i1%2!=0)
						value2 = sCurrentLine1.trim();
						//System.out.println(value2);	
						if(i1%2!=0){	
						IABGoogleMapAffinity.put(value2,key2);
						GoogleIABMapAffinity.put(key2,value2);
									
						}
	             
					  i1++;
					
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					br2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  	
				
				for (Map.Entry entry : GoogleIABMapInMarket.entrySet()) {
				    System.out.println(entry.getKey() + ", " + entry.getValue());
				}
				
				for (Map.Entry entry : GoogleIABMapAffinity.entrySet()) {
				    System.out.println(entry.getKey() + ", " + entry.getValue());
				}
	
	
	
				BufferedReader br3 = null;
				FileReader fr3 = null;
		        String key3 = null;
		        String value3 = null;
				
				
				
					//br = new BufferedReader(new FileReader(FILENAME));
					try {
						fr3 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/facebookIABv2.txt");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					br3 = new BufferedReader(fr3);

					String sCurrentLinev3;

					int i3=0;
					
					try {
						while ((sCurrentLinev3 = br3.readLine()) != null) {

							if(i3%2==0)
							key3 = sCurrentLinev3.trim();
							//System.out.println(key1);
							if(i3%2!=0)
							value3 = sCurrentLinev3.trim();
							//System.out.println(value1);	
							if(i3%2!=0){	
							IABFacebookMap.put(value3,key3);
							FacebookIABMap.put(key3,value3);
										
							}
		             
						  i3++;
						
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					try {
						br3.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  	
	
	
	
					BufferedReader br4 = null;
					FileReader fr4 = null;
			        String key4 = null;
			        String value4 = null;
					
					
					
						//br = new BufferedReader(new FileReader(FILENAME));
						try {
							fr4 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/IABMap.txt");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						br4 = new BufferedReader(fr4);

						String sCurrentLinev4;

						int i4=0;
						
						try {
							while ((sCurrentLinev4 = br4.readLine()) != null) {

								String[]parts = sCurrentLinev4.trim().split(";");
								//System.out.println(value1);	
								parts[0]=parts[0].trim();
								parts[1]=parts[1].trim();
								IABFormatMap1.put(parts[1],parts[0]);
								IABFormatMap2.put(parts[0],parts[1]);
											
								
			             
							
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						try {
							br4.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  	
		
		
	
						BufferedReader br5 = null;
						FileReader fr5 = null;
				        String key5 = null;
				        String value5 = null;
						
						
						
							//br = new BufferedReader(new FileReader(FILENAME));
							try {
								fr5 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/GoogleInMarketMap.txt");
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							br5 = new BufferedReader(fr5);

							String sCurrentLinev5;

							int i5=0;
							
							try {
								while ((sCurrentLinev5 = br5.readLine()) != null) {

									    String[] parts = sCurrentLinev5.split(";");
									//System.out.println(value1);	
									    parts[0]=parts[0].trim();
										parts[1]=parts[1].trim();
										GoogleInMarketFormatMap1.put(parts[1],parts[0]);
										GoogleInMarketFormatMap2.put(parts[0],parts[1]);
									
				             
								
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							try {
								br5.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}  	
			
							BufferedReader br6 = null;
							FileReader fr6 = null;
					        String key6 = null;
					        String value6 = null;
							
							
							
								//br = new BufferedReader(new FileReader(FILENAME));
								try {
									fr6 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/GoogleAffinityMap.txt");
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								br6 = new BufferedReader(fr6);

								String sCurrentLinev6;

								int i6=0;
								
								try {
									while ((sCurrentLinev6 = br6.readLine()) != null) {

										
										//System.out.println(value1);	
										String[]parts = sCurrentLinev6.trim().split(";");
										parts[0]=parts[0].trim();
										parts[1]=parts[1].trim();
										
										    GoogleAffinityFormatMap1.put(parts[1].trim(),parts[0]);
											GoogleAffinityFormatMap2.put(parts[0],parts[1].trim());
													
										
									
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
								try {
									br6.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}  	
				
		
								BufferedReader br7 = null;
								FileReader fr7 = null;
						        String key7 = null;
						        String value7 = null;
								
								
								
									//br = new BufferedReader(new FileReader(FILENAME));
									try {
										fr7 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/FacebookMap.txt");
									} catch (FileNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									br7 = new BufferedReader(fr7);

									String sCurrentLinev7;

									int i7=0;
									
									try {
										while ((sCurrentLinev7 = br7.readLine()) != null) {

											String[]parts = sCurrentLinev7.trim().split(";");;
											//System.out.println(value1);	
												
												FacebookFormatMap1.put(parts[1],parts[0]);
												FacebookFormatMap2.put(parts[0],parts[1]);
														
											
						             
										
										
										}
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
									try {
										br7.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}  	
	
	
									BufferedReader br8 = null;
									FileReader fr8 = null;
							        String key8 = null;
							        String value8 = null;
									
									
									
										//br = new BufferedReader(new FileReader(FILENAME));
										try {
											fr8 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/FacebookCodeMap.txt");
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										br8 = new BufferedReader(fr8);

										String sCurrentLinev8;

										int i8=0;
										
										try {
											while ((sCurrentLinev8 = br8.readLine()) != null) {

												String[]parts = sCurrentLinev8.trim().split(";");;
												//System.out.println(value1);	
												parts[0]=parts[0].trim();
												parts[1]=parts[1].trim();
													FacebookIdMap.put(parts[0],parts[1]);
													IdFacebookMap.put(parts[1],parts[0]);
															
												
							             
											  i8++;
											
											}
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										
										try {
											br8.close();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}  	
		
		
                 
										BufferedReader br9 = null;
										FileReader fr9 = null;
								        String key9 = null;
								        String value9 = null;
										
										
										
											//br = new BufferedReader(new FileReader(FILENAME));
											try {
												fr9 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/GoogleInMarketCodeMap.txt");
											} catch (FileNotFoundException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											br9 = new BufferedReader(fr9);

											String sCurrentLinev9;

											int i9=0;
											
											try {
												while ((sCurrentLinev9 = br9.readLine()) != null) {

													String[]parts = sCurrentLinev9.trim().split(";");;
													//System.out.println(value1);	
													parts[0]=parts[0].trim();
													parts[1]=parts[1].trim();
														GoogleIdMap.put(parts[1],parts[0]);
														IdGoogleMap.put(parts[0],parts[1]);
																
													
								             
												  i9++;
												
												}
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											
											try {
												br9.close();
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}  	
			                 
			
											BufferedReader br10 = null;
											FileReader fr10 = null;
									        String key10 = null;
									        String value10 = null;
											
											
											
												//br = new BufferedReader(new FileReader(FILENAME));
												try {
													fr10 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/IABCodeMap.txt");
												} catch (FileNotFoundException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												br10 = new BufferedReader(fr10);

												String sCurrentLinev10;

												int i10=0;
												
												try {
													while ((sCurrentLinev10 = br10.readLine()) != null) {

														String[]parts = sCurrentLinev10.trim().split(";");
														//System.out.println(value1);	
														parts[0]=parts[0].trim();
														parts[1]=parts[1].trim();
															IABIdMap.put(parts[0],parts[1]);
															IdIABMap.put(parts[1],parts[0]);
																	
														
									             
													  i10++;
													
													}
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												
												try {
													br10.close();
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}  	
				
									

												BufferedReader br11 = null;
												FileReader fr11 = null;
										        String key11 = null;
										        String value11 = null;
												
												
												
													//br = new BufferedReader(new FileReader(FILENAME));
													try {
														fr11 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/GoogleAffinityCodeMap.txt");
													} catch (FileNotFoundException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													br11 = new BufferedReader(fr11);

													String sCurrentLinev11;

													int i11=0;
													
													try {
														while ((sCurrentLinev11 = br11.readLine()) != null) {

															String[]parts = sCurrentLinev11.trim().split(";");;
															//System.out.println(value1);	
															parts[0]=parts[0].trim();
															parts[1]=parts[1].trim();
																GoogleIdAffinityMap.put(parts[1],parts[0]);
																IdGoogleAffinityMap.put(parts[0],parts[1]);
																		
															
										             
														  i11++;
														
														}
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													
													
													try {
														br11.close();
													} catch (IOException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}  	
									
									
									
									
									
									
	
	}
	
	
	
	
	
	private static final AtomicLong counter = new AtomicLong();
	
	ReportDAOImpl repDAO = ReportDAOImpl.getInstance();
	
	List<Reports> report= new ArrayList<Reports>();
	
	List<String> campaignIds = new ArrayList<String>();
	
    String campaignId = null;
	
    public List<Reports> extractReports(long id,String dateRange,String campaignId){
	
        String [] dateInterval = dateRange.split(",");
    	
    	
    	if(id == 1){
    	 	report=repDAO.FetchImpressionsData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 2){
    	 	report=repDAO.FetchClicksData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 3){
    	 	report=repDAO.FetchConversionsData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 4){
    	 	report=repDAO.FetchCostData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 5){
    	 	
    		report=repDAO.FetchAudienceSegmentImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 6){
    	 	report=repDAO.FetchCityImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 7){
    	 	report=repDAO.FetchDeviceImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
        
    	if(id == 8){
    	 	report=repDAO.FetchOSImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
       
    

    	if(id == 17){
    	 	report=repDAO.FetchReachData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	
    	
    	if(id == 19){
    	 	report=repDAO.FetchAgeImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
        
    	if(id == 20){
    	 	report=repDAO.FetchGenderImpData(dateInterval[0], dateInterval[1], campaignId);
		    return report;
    	}
    	  	
    	
    	
    	return report;
    

    }
		
//Give List of campaignIds corresponding to date range
    
    public List<String> extractCampaignIds(long id,String dateRange){
      
    	  String [] dateInterval = dateRange.split(",");

          if( id == 16){
           campaignIds = repDAO.extractCampaignIds(dateInterval[0], dateInterval[1]);
           return campaignIds;
           
          }
    
          return campaignIds;
    
    
    }

    
    
    public CampaignChannelData getcampaignChannelData(String campaignId,String channelType,String accessToken) {

    	CampaignChannelData ccdData = new CampaignChannelData();
	    
    	
    	
    	if(channelType.toLowerCase().equals("facebook")){
    		
    		String advertype = "NewsFeed";
    		String startDate = "2017-11-01"; 
    	    String endDate = "2017-12-15";
    	    String kpi = "";
    	    String target = "";
    	    String bid_min = "1000";
    	    String bid_max = "200000";
    	    String channel_budget = "500000";
    	    String channel_status = "true";
    	    
    	   
    	    ccdData.setAdvertType(advertype);
    		ccdData.setStartDate(startDate);
    		ccdData.setEndDate(endDate);
    	    ccdData.setkPI(kpi);
    	    ccdData.setTarget(target);
    	    ccdData.setBidmin(bid_min);
    	    ccdData.setBidmax(bid_max);
    	    ccdData.setChannelBudget(channel_budget);
    	    ccdData.setChannelStatus(channel_status);
    	    ccdData.setChannelType("Facebook");
    	}
    	
        if(channelType.toLowerCase().equals("dbm")){
    		
        	String advertype = "LineItem";
    		String startDate = "2017-11-02"; 
    	    String endDate = "2017-12-17";
    	    String kpi = "";
    	    String target = "";
    	    String bid_min = "2000";
    	    String bid_max = "300000";
    	    String channel_budget = "700000";
    	    String channel_status = "true";
    	    
    	   
    	    ccdData.setAdvertType(advertype);
    		ccdData.setStartDate(startDate);
    		ccdData.setEndDate(endDate);
    	    ccdData.setkPI(kpi);
    	    ccdData.setTarget(target);
    	    ccdData.setBidmin(bid_min);
    	    ccdData.setBidmax(bid_max);
    	    ccdData.setChannelBudget(channel_budget);
    	    ccdData.setChannelStatus(channel_status);
    	    ccdData.setChannelType("DBM");
        	
        	
        	
    	}
    	
        if(channelType.toLowerCase().equals("adwords")){
    		
        	String advertype = "Adwords";
    		String startDate = "2017-11-05"; 
    	    String endDate = "2017-12-22";
    	    String kpi = "";
    	    String target = "";
    	    String bid_min = "3000";
    	    String bid_max = "500000";
    	    String channel_budget = "900000";
    	    String channel_status = "true";
    	    
    	  
    	    
    	    ccdData.setAdvertType(advertype);
    		ccdData.setStartDate(startDate);
    		ccdData.setEndDate(endDate);
    	    ccdData.setkPI(kpi);
    	    ccdData.setTarget(target);
    	    ccdData.setBidmin(bid_min);
    	    ccdData.setBidmax(bid_max);
    	    ccdData.setChannelBudget(channel_budget);
    	    ccdData.setChannelStatus(channel_status);
    	    ccdData.setChannelType("Adwords");
        	
        	
    	}
    	
        if(channelType.toLowerCase().equals("lightning")){
    		
    		
        	String advertype = "Lightning";
    		String startDate = "2017-11-07"; 
    	    String endDate = "2017-12-27";
    	    String kpi = "";
    	    String target = "";
    	    String bid_min = "6000";
    	    String bid_max = "600000";
    	    String channel_budget = "600000";
    	    String channel_status = "true";
    	    
    	    
    	    ccdData.setAdvertType(advertype);
    		ccdData.setStartDate(startDate);
    		ccdData.setEndDate(endDate);
    	    ccdData.setkPI(kpi);
    	    ccdData.setTarget(target);
    	    ccdData.setBidmin(bid_min);
    	    ccdData.setBidmax(bid_max);
    	    ccdData.setChannelBudget(channel_budget);
    	    ccdData.setChannelStatus(channel_status);
        	ccdData.setChannelType("Lightning");
        	
        	
    	}
    
          return ccdData;
    
    }

    
    
    
    
    public DMPTag getcampaignChannelDMPTag(String campaignId) {

    	DMPTag tag = new DMPTag();
    	
    //	String startTag  = "------------------------Start of Tag---------------------------------------------------------------\n\n";
        String middleScript = "<script language=\"JavaScript\"> var STD=\"101\"; var ISD=\"102\"; var pn=\"NS\"; var cn=\""+campaignId+"\""+";var tagparameters = \"ReplaceValue\" </script>"+ "<script src=\"https://dcpub.cuberoot.co/dcode2/dmpbasedc.js\"></script>";
    //    String endTag  = "\n\n------------------------End of Tag---------------------------------------------------------------\n\n";
               
                
        tag.setTag(middleScript);       
       
        return tag; 
    
    }
    
    
    
    public List<Segments> getConvertedCategories(List<String> categoryList, String channelSource, String channelTarget, String categoryType) {

    	
    	
    	List<Segments> returnCategory = new ArrayList<Segments>();
    	
       if(channelSource.equals("Lightning") && channelTarget.equals("Adwords") )
       {
    	   for(int i=0; i < categoryList.size();i++){
    	  
    	   try{
    		   
    	   String categoryv1 = URLDecoder.decode(categoryList.get(i));
    	   categoryv1 = categoryv1.trim();
    	   categoryv1=IdIABMap.get(categoryv1);   
    	   categoryv1=categoryv1.replace("/"," ").replace(".", " ").toLowerCase();
    	   
    	   if(categoryType.equals("affinity"))	{   
    	   categoryv1 = categoryv1.trim();
    	   String googleSegment =  IABGoogleMapAffinity.get(categoryv1.trim());
           googleSegment = googleSegment.trim();
    	   googleSegment = GoogleAffinityFormatMap1.get(googleSegment.trim());
    	   googleSegment = googleSegment.trim();
    	   String code = GoogleIdAffinityMap.get(googleSegment);
           Segments object = new Segments();
           object.setSegments(googleSegment);
    	   object.setId(code);
    	   returnCategory.add(object);
    	   }
    	   
    	   if(categoryType.equals("market"))	{   
        	   categoryv1 = categoryv1.trim();
    		   String googleSegment =  IABGoogleMapInMarket.get(categoryv1);
    		   googleSegment = googleSegment.trim();
    		   googleSegment = GoogleInMarketFormatMap2.get(googleSegment); 
    		   googleSegment = googleSegment.trim();
    		   String code = GoogleIdMap.get(googleSegment);
               Segments object = new Segments();
               object.setSegments(googleSegment);
        	   object.setId(code);
        	   returnCategory.add(object);
           }
        	   
    	   }
    	   catch(Exception e){
    		   continue;
    		   
    	   }
    	   
    	   
    	   }
   
    	   return returnCategory;
       } 
       if(channelSource.equals("Lightning") && channelTarget.equals("DBM") )
       {
    	   for(int i=0; i < categoryList.size();i++){
    	    	
    		   try{
    		   String categoryv1 = URLDecoder.decode(categoryList.get(i));
    		   categoryv1 = categoryv1.trim();
    		   categoryv1=IdIABMap.get(categoryv1);  
    		   categoryv1=categoryv1.replace("/"," ").replace(".", " ").toLowerCase();
    		   
        	   if(categoryType.equals("affinity"))	{   
        		   
        	   categoryv1 = categoryv1.trim();   
        	   String googleSegment =  IABGoogleMapAffinity.get(categoryv1.trim());
        	   googleSegment = googleSegment.trim();
        	   googleSegment = GoogleAffinityFormatMap1.get(googleSegment.trim());
        	   googleSegment = googleSegment.trim();
        	   String code = GoogleIdAffinityMap.get(googleSegment);
               Segments object = new Segments();
               object.setSegments(googleSegment);
        	   object.setId(code);
        	   returnCategory.add(object);
        	   }
        	   
        	   if(categoryType.equals("market"))	{   
        		   categoryv1 = categoryv1.trim();
        		   String googleSegment = IABGoogleMapInMarket.get(categoryv1.trim());
        		   googleSegment = googleSegment.trim();
        		   googleSegment = GoogleInMarketFormatMap2.get(googleSegment.trim());
        		   googleSegment = googleSegment.trim();
        		   String code = GoogleIdMap.get(googleSegment);
                   Segments object = new Segments();
                   object.setSegments(googleSegment);
            	   object.setId(code);
            	   returnCategory.add(object);
               }
            	   
    		   }
    		   catch(Exception e){
    			   
    			   continue;
    		   }
        	   
        	   
        	   }
       
        	   return returnCategory;
    	   
       }
       
       
       
       
       if(channelSource.equals("Adwords") && channelTarget.equals("Lightning"))   
       {
    	   for(int i=0; i < categoryList.size();i++){
 	    	  
    		   try{
    		   
    		   String category = URLDecoder.decode(categoryList.get(i));
    		   category = category.trim();
    		  
    		   
    		   
    		   if(categoryType.equals("affinity"))	{   
        		   
    			   category = IdGoogleAffinityMap.get(category);
    			   
    			   category =category.replace("/"," ").replace(".", " ").toLowerCase();
    			   category = category.trim();
    			   String lightningSegment =  GoogleIABMapAffinity.get(category.trim());
                   lightningSegment = lightningSegment.trim();
    			   lightningSegment = IABFormatMap2.get(lightningSegment.trim());
    			   lightningSegment = lightningSegment.trim();
    			   String code = IABIdMap.get(lightningSegment);
        		   Segments object = new Segments();
                   object.setSegments(lightningSegment);
            	   object.setId(code);
            	   returnCategory.add(object);
        	   }
        	   
        	   if(categoryType.equals("market"))	{   
        		   
        		   category = IdGoogleMap.get(category);
        		   category =category.replace("/"," ").replace(".", " ").toLowerCase();
        		   category = category.trim();
        		   String lightningSegment =  GoogleIABMapInMarket.get(category.trim());
        		   lightningSegment = lightningSegment.trim();
        		   lightningSegment = IABFormatMap2.get(lightningSegment.trim());
        		   lightningSegment = lightningSegment.trim();
        		   String code = IABIdMap.get(lightningSegment);
        		   Segments object = new Segments();
                   object.setSegments(lightningSegment);
            	   object.setId(code);
            	   returnCategory.add(object);
               }
            	
    		   }
    		   catch(Exception e){
    			   
    			   continue;
    		   }
        	   
        	   
        	   
        	   }
       }
    
    
       if(channelSource.equals("DBM") && channelTarget.equals("Lightning"))   
       {
    	  
    	   for(int i=0; i < categoryList.size();i++){
  	    	  
    		   try{
    		   String category = URLDecoder.decode(categoryList.get(i));
    		   category = category.trim();
    		  
    		   
    		   if(categoryType.equals("affinity"))	{   
    			   
    		   category = IdGoogleAffinityMap.get(category);
    		   category =category.replace("/"," ").replace(".", " ").toLowerCase();
    		   category = category.trim();
    		   String lightningSegment =  GoogleIABMapAffinity.get(category.trim());
    		   lightningSegment = lightningSegment.trim();
    		   lightningSegment = IABFormatMap2.get(lightningSegment.trim());
    		   lightningSegment = lightningSegment.trim();
    		   String code = IABIdMap.get(lightningSegment);
    		   Segments object = new Segments();
               object.setSegments(lightningSegment);
        	   object.setId(code);
        	   returnCategory.add(object);
        	   }
        	   
        	   if(categoryType.equals("market"))	{   
        		   
        		   category = IdGoogleMap.get(category);
        		   category =category.replace("/"," ").replace(".", " ").toLowerCase();
        		   category = category.trim();
        		   String lightningSegment =  GoogleIABMapInMarket.get(category.trim());
        		   lightningSegment = lightningSegment.trim();
        		   lightningSegment = IABFormatMap2.get(lightningSegment.trim());
        		   lightningSegment = lightningSegment.trim();
        		   String code = IABIdMap.get(lightningSegment);
        		   Segments object = new Segments();
                   object.setSegments(lightningSegment);
            	   object.setId(code);
            	   returnCategory.add(object);
               }
            	
    		   }
    		   catch(Exception e){
    			   
    			   continue;
    		   }
        	   
        	   
        	   
        	   }
       }
    	   
    
       if(channelSource.equals("Lightning") && channelTarget.equals("Facebook") )
       {
    	   for(int i=0; i < categoryList.size();i++){
    	  try{
    	   String categoryv1 = URLDecoder.decode(categoryList.get(i));
    	   categoryv1 = categoryv1.trim();
    	   categoryv1=IdIABMap.get(categoryv1);  
    	   categoryv1=categoryv1.replace("/"," ").replace(".", " ").toLowerCase();
    	   categoryv1 = categoryv1.trim();
    	     
    	   String facebookSegment =  IABFacebookMap.get(categoryv1.trim());
    	   facebookSegment =  facebookSegment.trim();
    	   facebookSegment = FacebookFormatMap1.get(facebookSegment.trim());
    	   facebookSegment =  facebookSegment.trim();
    	   String code = FacebookIdMap.get(facebookSegment);
    	   Segments object = new Segments();
           object.setSegments(facebookSegment);
    	   object.setId(code);
    	   returnCategory.add(object);
    	  }
    	  catch(Exception e){
    		  continue;
    	  }
    	   
    	   }
   
    	   return returnCategory;
       } 
       
       if(channelSource.equals("Facebook") && channelTarget.equals("Lightning"))
       {
    	   for(int i=0; i < categoryList.size();i++){
    	    
    		   try{
        	   String categoryv1 = URLDecoder.decode(categoryList.get(i));
        	   categoryv1 = categoryv1.trim();
        	   categoryv1=IdFacebookMap.get(categoryv1); 
        	   categoryv1=categoryv1.replace("/"," ").replace(".", " ").toLowerCase();
        	   categoryv1 = categoryv1.trim(); 
        	     
        	   String lightningSegment =  FacebookIABMap.get(categoryv1.trim());
        	   lightningSegment = lightningSegment.trim();
        	   lightningSegment = IABFormatMap2.get(lightningSegment.trim());
        	   lightningSegment = lightningSegment.trim();
        	   String code = IABIdMap.get(lightningSegment);
               Segments object = new Segments();
               object.setSegments(lightningSegment);
        	   object.setId(code);
        	   returnCategory.add(object);      	   
        	   
    		   }
    		   catch(Exception e){
    			   
    			   continue;
    		   }
        	   
        	   }
       
        	   return returnCategory;
       } 
       
       
   
      return returnCategory;
    
    
    }
    
public TargetingParametersSummary getTargetingParameters(String siteId) {

    	
	TargetingParametersSummary data = new TargetingParametersSummary();
    List<LocationObject> locationlist = new ArrayList<LocationObject>(); 	
	List<DeviceObject> devicelist = new ArrayList<DeviceObject>();
    URL url = null;
	try {
		url = new URL("http://205.147.103.82:8080/publisherv1/report/v1/cityOthers?dateRange=2017-01-13_2017-01-15&siteId=1");
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	HttpURLConnection conn = null;
	try {
		conn = (HttpURLConnection) url.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn.setRequestMethod("GET");
	} catch (ProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	conn.setRequestProperty("Accept", "application/json");

	try {
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	BufferedReader br = null;
	try {
		br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String output = null;
	System.out.println("Output from Server .... \n");
	try {
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		    break;
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 	
        
	JSONObject object1 = new JSONObject(output);
	JSONArray object2 = (JSONArray)object1.get("data");
	
	JSONObject object3;
	for (int i = 0; i < object2.length(); i++)
    {
      object3 = object2.getJSONObject(i);
    
	
	ObjectMapper objectMapper = new ObjectMapper();

    //convert json string to object
    try {
		LocationObject loc = objectMapper.readValue(object3.toString(), LocationObject.class);
		locationlist.add(loc);
    } catch (JsonParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (JsonMappingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 		
   }	
	URL url1 = null;
	try {
		url1 = new URL("http://205.147.103.82:8080/publisherv1/report/v1/device?dateRange=2017-01-13_2017-01-15&siteId=1");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	HttpURLConnection conn1 = null;
	try {
		conn1 = (HttpURLConnection) url1.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn1.setRequestMethod("GET");
	} catch (ProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	conn1.setRequestProperty("Accept", "application/json");

	try {
		if (conn1.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn1.getResponseCode());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	BufferedReader br1 = null;
	try {
		br1 = new BufferedReader(new InputStreamReader(
			(conn1.getInputStream())));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String output1 = null;
	System.out.println("Output from Server .... \n");
	try {
		while ((output1 = br1.readLine()) != null) {
			System.out.println(output1);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 	
	
	
	JSONObject objectv1 = new JSONObject(output1);
	JSONArray objectv2 = (JSONArray)objectv1.get("data");
	
	JSONObject objectv3;
	for (int i = 0; i < objectv2.length(); i++)
    {
      objectv3 = objectv2.getJSONObject(i);
    
	
	ObjectMapper objectMapper = new ObjectMapper();

    //convert json string to object
    try {
		DeviceObject device = objectMapper.readValue(objectv3.toString(), DeviceObject.class);
		devicelist.add(device);
    } catch (JsonParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (JsonMappingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 		
   }	
	
	
	
	
	URL url2 = null;
	try {
		url2 = new URL("http://205.147.103.82:8080/publisherv1/report/v1/gender?dateRange=2017-01-13_2017-01-15&siteId=1");
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	HttpURLConnection conn2 = null;
	try {
		conn2 = (HttpURLConnection) url2.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn2.setRequestMethod("GET");
	} catch (ProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	conn2.setRequestProperty("Accept", "application/json");

	try {
		if (conn2.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn2.getResponseCode());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	BufferedReader br2 = null;
	try {
		br2 = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String output2;
	System.out.println("Output from Server .... \n");
	try {
		while ((output2 = br2.readLine()) != null) {
			System.out.println(output2);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 	
	
	
	
	
	
	
	return data;
    
} 
    



public  String getReach(String siteId,String subcategory,String device,String gender,String agegroup,String incomelevel,String city,String state,String country) {

	device = URLDecoder.decode(device);
    device = device.replace(",", "~");
	subcategory = URLDecoder.decode(subcategory);
    subcategory = subcategory.replace(",", "~");
	gender =  URLDecoder.decode(gender);
    gender = gender.replace(",", "~");
	agegroup  = URLDecoder.decode(agegroup);
    agegroup = agegroup.replace(",", "~");
	incomelevel = URLDecoder.decode(incomelevel).replace(",", "~");
    incomelevel = incomelevel.replace(",", "~");
	city = URLDecoder.decode(city);
    city=city.replace(",", "~");
	state = URLDecoder.decode(state).replace(",", "~");
	state = state.replace(",", "~");
    country = URLDecoder.decode(country).replace(",", "~");
	country= country.replace(",", "~");
	
	TargetingParametersSummary data = new TargetingParametersSummary();
    List<LocationObject> locationlist = new ArrayList<LocationObject>(); 	
	List<DeviceObject> devicelist = new ArrayList<DeviceObject>();
    URL url = null;
	try {
		url = new URL("http://205.147.103.82:8080/publisherv1/report/v1/uniqueVisitors?&dateRange=2017-09-01_2017-11-30&"+"&siteId=1"+"&devicetype="+device+"&subcategory="+subcategory+"&gender="+gender+"&agegroup="+agegroup+"&incomelvel="+incomelevel+"&city="+city+"&state="+state+"&country="+country);
		
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	HttpURLConnection conn = null;
	try {
		conn = (HttpURLConnection) url.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		conn.setRequestMethod("GET");
	} catch (ProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	conn.setRequestProperty("Accept", "application/json");

	try {
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	BufferedReader br = null;
	try {
		br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String output = null;
	System.out.println("Output from Server .... \n");
	try {
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		    break;
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 	
        
    output = output.replace("\"channelName\":\"Womanseraindia\",","").replaceFirst(",","");
	return output;
    
} 
    

















public TargetingParametersSummaryObject getTargetingParametersData(String siteId) {

	List<LocationObject> locations = new ArrayList<LocationObject>();
	List<DeviceObject> deviceObject= new ArrayList<DeviceObject>();
	List<GenderObject> genderobj = new ArrayList<GenderObject>();
	List<IncomeObject> incomeobj = new ArrayList<IncomeObject>();
	List<AgeObject> ageobj = new ArrayList<AgeObject>();
	List<AudienceSegmentObject> audiencesegmentobj =  new ArrayList<AudienceSegmentObject>();
	TargetingParametersSummaryObject obj = new TargetingParametersSummaryObject(); 
	List<MobileDeviceModelObject> mobiledeviceobj = new ArrayList<MobileDeviceModelObject>();
	
	
	if(siteId.equals("1"))
	{
		LocationObject object = new LocationObject();
		
		object.setCity("Delhi");
	    object.setState("Delhi");
		object.setCountry("India");
		object.setCityId("7");
		object.setCountryId("IN");
		locations.add(object);
       
		LocationObject object1 = new LocationObject();
		
		object1.setCity("Mumbai");
		object1.setState("Maharashtra");
		object1.setCountry("India");
		object1.setCityId("Geo1663");
		object1.setStateId("16");
		object1.setCountryId("IN");
		locations.add(object1);
		
	
        DeviceObject objectd1 = new DeviceObject();
		
		objectd1.setDevice_type("Mobile");
	
		objectd1.setId("1");
		
		deviceObject.add(objectd1);
       
		DeviceObject objectd2 = new DeviceObject();
			
	    objectd2.setDevice_type("Computer");
		
	    objectd2.setId("2");
	    
	    deviceObject.add(objectd2);
	       
	
        GenderObject objectg1 = new GenderObject();
		
		objectg1.setGender("Female");
	
		objectg1.setId("1");
		genderobj.add(objectg1);
		
        AgeObject objecta1 = new AgeObject();
		objecta1.setAge("25-34");
		objecta1.setId("1");
		ageobj.add(objecta1);
		
		AgeObject objecta2= new AgeObject();
        objecta2.setAge("18-24");
        objecta2.setId("2");
        ageobj.add(objecta2);  
		
	    AgeObject objecta3 = new AgeObject();
		objecta3.setAge("35-44");
		objecta3.setId("3");
		ageobj.add(objecta3);
		
		IncomeObject objecti1 = new IncomeObject();
        objecti1.setIncome("Medium");
        objecti1.setId("1");
        incomeobj.add(objecti1);
        
        
        MobileDeviceModelObject objectmodel1 = new MobileDeviceModelObject();
        objectmodel1.setMobiledevicemodel("Apple iPhone");
        objectmodel1.setId("1");
        mobiledeviceobj.add(objectmodel1);
        
        
        MobileDeviceModelObject objectmodel2 = new MobileDeviceModelObject();
        objectmodel2.setMobiledevicemodel("Samsung Sm J320R4");
        objectmodel2.setId("21");
        mobiledeviceobj.add(objectmodel2);
        
        
        MobileDeviceModelObject objectmodel3 = new MobileDeviceModelObject();
        objectmodel3.setMobiledevicemodel("Samsung Sm J120W");
        objectmodel3.setId("37");
        mobiledeviceobj.add(objectmodel3);
        
        MobileDeviceModelObject objectmodel4 = new MobileDeviceModelObject();
        objectmodel4.setMobiledevicemodel("Samsung Sm J110G");
        objectmodel4.setId("48");
        mobiledeviceobj.add(objectmodel4);
        
        
        MobileDeviceModelObject objectmodel5 = new MobileDeviceModelObject();
        objectmodel5.setMobiledevicemodel("Google Nexus 5");
        objectmodel5.setId("5");
        mobiledeviceobj.add(objectmodel5);
        
        AudienceSegmentObject audienceSegment = new AudienceSegmentObject();
        audienceSegment.setAudienceSegment("/art.and.entertainment/");
        audienceSegment.setId("Seg804");
        List<Subcategory> subcategory = new ArrayList<Subcategory>();
        Subcategory obj1 = new Subcategory();
        obj1.setSubcategory("/art.and.entertainment/movies.and.tv/bollywood/");
        obj1.setId("Seg860");
      
        
        
        Subcategory obj2 = new Subcategory();
        obj2.setSubcategory("/art.and.entertainment/celebrity.fan.and.gossip/");
        obj2.setId("Seg829");
        Subcategory obj3 = new Subcategory();
        obj3.setSubcategory("/art.and.entertainment/movies.and.tv/talk.shows/");
        obj3.setId("Seg877");
        subcategory.add(obj1);
        subcategory.add(obj2);
        subcategory.add(obj3);
        audienceSegment.setSubcategory(subcategory);
        audiencesegmentobj.add(audienceSegment);
        
        AudienceSegmentObject audienceSegmentv1 = new AudienceSegmentObject();
        audienceSegmentv1.setAudienceSegment("/style.and.fashion/");
        audienceSegmentv1.setId("Seg1904");
        List<Subcategory> subcategoryv1 = new ArrayList<Subcategory>();
        
        Subcategory obj4 = new Subcategory();
        obj4.setSubcategory("/family.and.parenting/");
        obj4.setId("Seg1000729");
        Subcategory obj5 = new Subcategory();
        obj5.setSubcategory("/style.and.fashion/accessories/watches/");
        obj5.setId("Seg2035");
        Subcategory obj6 = new Subcategory();
        obj6.setSubcategory("/style.and.fashion/clothing/wedding.dresses/");
        obj6.setId("Seg2302");
      //  subcategoryv1.add(obj4);
        subcategoryv1.add(obj5);
        subcategoryv1.add(obj6);
        
        
        audienceSegmentv1.setSubcategory(subcategoryv1);
        audiencesegmentobj.add(audienceSegmentv1);
        
        AudienceSegmentObject audienceSegmentv2 = new AudienceSegmentObject();
        audienceSegmentv2.setAudienceSegment("society");
        audienceSegmentv2.setId("Segv27887880");
        List<Subcategory> subcategoryv2 = new ArrayList<Subcategory>();
        
        
        Subcategory obj7 = new Subcategory();
        obj7.setSubcategory("/society/social.institution/marriage/common.law.marriage/");
        obj7.setId("Segv17887880");
        Subcategory obj8 = new Subcategory();
        obj8.setSubcategory("/society/crime/personal.offense/kidnapping/");
        obj8.setId("Segv17127120");
        Subcategory obj9 = new Subcategory();
        obj9.setSubcategory("/society/social.institution/marriage/weddings/");
        obj9.setId("Segv17927920");
        subcategoryv2.add(obj7);
      //  subcategoryv2.add(obj8);
        subcategoryv2.add(obj9);
      
       
        
      //  audienceSegmentv2.setSubcategory(subcategoryv2);
     //   audiencesegmentobj.add(audienceSegmentv2);
        
        
        List<OperatingSystem> os = new ArrayList<OperatingSystem>();
        
        String os1 = "iOS 9.3";
        String os2 = "Android 6.0";
        
        String code1 = "21";
        String code2 = "6";
        
        OperatingSystem objv1 = new OperatingSystem();
        objv1.setId(code1);
        objv1.setOs(os1);
        
        
        OperatingSystem objv2 = new OperatingSystem();
        objv2.setId(code2);
        objv2.setOs(os2);
        
        os.add(objv1);
        os.add(objv2);
        
        List<Language> langs = new ArrayList<Language>();
        
        Language lang = new Language();
        lang.setId("1");
        lang.setLanguage("English");
        
        langs.add(lang);
        
	    obj.setLocations(locations);
	    obj.setIncomeobj(incomeobj);
	    obj.setGenderobj(genderobj);
	    obj.setAgeobj(ageobj);
	    obj.setDeviceObject(deviceObject);
	    obj.setAudiencesegmentobj(audiencesegmentobj);
	    obj.setMobiledeviceobj(mobiledeviceobj);
	    obj.setLanguage(langs);
        obj.setOperatingsystem(os);
	
	 }
	
		return obj;
	
} 
    

    public Site getPrivateAudienceMarketPlace() {

       Site siteobj = new Site();
       siteobj.setSiteId("1");
       siteobj.setPrivatemarketplacename("WomanseraAudience");
       return siteobj;
       
    }

    public CampaignData getChannelCampaignId(String channelType,String accessToken) {

    	CampaignData cmpdata = new CampaignData();
    	
    	List<Campaign> cmpList = new ArrayList<Campaign>();
    	
    	if(channelType.toLowerCase().equals("facebook"))
    			{
    		
    		     
    		     Campaign obj = new Campaign();
    		     obj.setCampaignId("61001");
    		     obj.setCampaignName("Facebook Campaign TNT");
    		    
    		     Campaign obj1 = new Campaign();
    		     obj1.setCampaignId("61002");
    		     obj1.setCampaignName("Facebook Campaign Womansera");
    		     
    		     
    		     cmpList.add(obj);
    		     
    		     
    		     cmpList.add(obj1);
    			}

        if(channelType.toLowerCase().equals("adwords"))
        		{
        	 Campaign obj = new Campaign();
		     obj.setCampaignId("78001");
		     obj.setCampaignName("Adwords Campaign TNT");
		    
		     Campaign obj1 = new Campaign();
		     obj1.setCampaignId("78002");
		     obj1.setCampaignName("Adwords Campaign Womansera");
		     
		     
		     cmpList.add(obj);
		     
		     
		     cmpList.add(obj1);
        		}
        		
        if(channelType.toLowerCase().equals("dbm"))		
        		{
        	
        	 Campaign obj = new Campaign();
		     obj.setCampaignId("89001");
		     obj.setCampaignName("DBM Campaign TNT");
		    
		     Campaign obj1 = new Campaign();
		     obj1.setCampaignId("89002");
		     obj1.setCampaignName("DBM Campaign Womansera");
		     
		     
		     cmpList.add(obj);
		     
		     
		     cmpList.add(obj1);
        		
        		}
        
        if(channelType.toLowerCase().equals("lightning"))		
		{
        	Campaign obj = new Campaign();
		     obj.setCampaignId("99001");
		     obj.setCampaignName("Lightning Campaign TNT");
		    
		     Campaign obj1 = new Campaign();
		     obj1.setCampaignId("99002");
		     obj1.setCampaignName("Lightning Campaign Womansera");
		     
		     
		     cmpList.add(obj);
		     
		     
		     cmpList.add(obj1);
		}
        
        cmpdata.setCampaigns(cmpList);
        
      return cmpdata;
    
    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
