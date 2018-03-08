package com.cuberoot.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadMap {

	public static Map<String,String> IABGoogleMapInMarket = new HashMap<String,String>();
    public static Map<String,String> GoogleIABMapInMarket = new HashMap<String,String>();
	
	public static Map<String,String> IABGoogleMapAffinity = new HashMap<String,String>();
	public static Map<String,String> GoogleIABMapAffinity = new HashMap<String,String>();
	
	static{
		// TODO Auto-generated method stub
		BufferedReader br1 = null;
		FileReader fr1 = null;
        String key1 = null;
        String value1 = null;
		
		
		
			//br = new BufferedReader(new FileReader(FILENAME));
			try {
				fr1 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/MarketingSegmentsIAB.txt");
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
					
					if(i%2!=0)
					value1 = sCurrentLine.trim();
						
					if(i%2!=0){	
					IABGoogleMapInMarket.put(key1,value1);
					GoogleIABMapInMarket.put(value1,key1);
								
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
					fr2 = new FileReader("/media/raptor/Data/campaigninsights/Adapter/AffinityIAB1.txt");
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
						
						if(i1%2!=0)
						value2 = sCurrentLine1.trim();
							
						if(i1%2!=0){	
						IABGoogleMapAffinity.put(key2,value2);
						GoogleIABMapAffinity.put(value2,key2);
									
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
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
