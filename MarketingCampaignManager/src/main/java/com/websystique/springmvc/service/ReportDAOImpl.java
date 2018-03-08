package com.websystique.springmvc.service;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cuberoot.util.DBConnector;
import com.cuberoot.util.DTOFilter;
import com.cuberoot.util.DTOPopulator;
import com.cuberoot.util.DTOProcessor;
import com.websystique.springmvc.model.Reports;


public class ReportDAOImpl {

	

	private static ReportDAOImpl INSTANCE;

	private static final Logger logger = Logger.getLogger(ReportDAOImpl.class);

	public static ReportDAOImpl getInstance() {
		
		if(INSTANCE == null)
			return new ReportDAOImpl();
		else
		return INSTANCE;
	}

	  //Report codes are defined for Channel based Reports for  selected campaign that ran corresponding to advertiser.
    //For example - campaign ran on DBM, Facebook, Adwords, it gives performance data for how selected campaign faired on each channel.
    //This report will be pulled if campaignId is selected From UI panel.
	
	
	
	public List<Reports> FetchImpressionsData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		List<Reports> obj2 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
		int ReportCode = 1;
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();

			if (connection != null) {
				if(campaignId.equals("all") || campaignId.equals("")){
					QueryString = "Select sum(impression)as impression,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id";
				}
				else{
				QueryString = "Select sum(impression)as impression,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
						+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+") GROUP by date,campaign_id,channel";
				}
				
				System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

			//	int count = 0;

			//	while (rs.next()) {
			//	    ++count;
				    // Get data from the current row and use it
			//	}
				
			//	System.out.println(count);
				
				
				obj1=DTOPopulator.populateDTO(rs);
                obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
                obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				
				//	jo.put("data",obj1);
				
				//Resultset to json converter
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
				
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			
			} 
		
		
		
		
		return obj3;
	}

	public List<Reports> FetchClicksData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj2 = null;
		//	JSONObject jo = new JSONObject();
	    List<Reports> obj3 = null;
		
		
		int ReportCode = 2;
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();

			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals("")){
					QueryString = "Select sum(clicks)as clicks,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id";
				}
				else{
				QueryString = "Select sum(clicks)as clicks,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
					+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+") GROUP by date,campaign_id,channel";
				}
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);
				obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
				obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				// populate the array
			//	jo.put("data",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
			 
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
				return obj3;
	}

	public List<Reports> FetchConversionsData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		List<Reports> obj2 = null;
		//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
	    int ReportCode = 3;
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals("")){
					QueryString = "Select sum(conversions)as conversions,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id";
				}
				else{
				QueryString = "Select sum(conversions)as conversions,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
						+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+") GROUP by date,campaign_id,channel";
				
				}
				
				System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);
				obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
				obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				//jo.put("data",obj1);
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
  
		 finally{
				
			 
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);

				DBUtil.close(connection);
		} 
		
		
		
		
		return obj3;
	}



	public List<Reports> FetchCostData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj2 = null;
		//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
		int ReportCode = 4;
		
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals("")){
					QueryString = "Select sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id";
				}
					else{
				QueryString = "Select sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
						+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+") GROUP by date,campaign_id,channel";
					}
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);
				obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
				obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				
			//	jo.put("data",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
			
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj3;
	}

	public List<Reports> FetchAudienceSegmentImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
				QueryString ="SELECT SUM(impression)AS impression,audience_segment FROM datawarehouse WHERE audience_segment != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP BY audience_segment";
				else
				QueryString ="SELECT SUM(impression)AS impression,campaign_id,audience_segment,channel FROM datawarehouse WHERE audience_segment != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+")  GROUP BY campaign_id,audience_segment";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		 finally{
				
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			
			} 
		
		
		
		return obj1;
	}


	public List<Reports> FetchAudienceSegmentClickData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				QueryString ="SELECT SUM(clicks)AS clicks,date,campaign_id,audience_segment,channel FROM datawarehouse WHERE audience_segment != '' AND campaign_id in ("+campaignId+") GROUP BY date,campaign_id,audience_segment";
				
	            System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("data",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
			
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		return obj1;
	}


	public List<Reports> FetchDeviceImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
					QueryString ="SELECT SUM(impression)AS impression,device_type FROM datawarehouse WHERE device_type != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate +"' GROUP BY device_type";
				else
				QueryString ="SELECT SUM(impression)AS impression,campaign_id,device_type,channel FROM datawarehouse WHERE device_type != '' AND  date between "
							+ "'"+startDate + "' AND '" + endDate +"' AND campaign_id in ("+campaignId+") GROUP BY campaign_id,device_type";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
 
		 
		finally{
			
			DBUtil.close(rs);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj1;
	}


	public List<Reports> FetchCityImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		//JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
					QueryString ="SELECT SUM(impression)AS impression,city FROM datawarehouse WHERE city != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+"' GROUP BY city";	
				else
				QueryString ="SELECT SUM(impression)AS impression,campaign_id,city,channel FROM datawarehouse WHERE city != ''  AND  date between "
							+ "'"+startDate + "' AND '" + endDate+"' AND campaign_id in ("+campaignId+") GROUP BY campaign_id,city";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
 
		 finally{
			
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj1;
	}

	
	
	public List<Reports> FetchOSImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
					QueryString ="SELECT SUM(impression)AS impression,os FROM datawarehouse WHERE os != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+"' GROUP BY os";
				else	
					QueryString ="SELECT SUM(impression)AS impression,campaign_id,os,channel FROM datawarehouse WHERE os != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+ "' AND campaign_id in ("+campaignId+") GROUP BY campaign_id,os";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
			 
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		return obj1;
	}
	
	
	public List<Reports> FetchReachData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
		List<Reports> obj2 = null;
	//	JSONObject jo = new JSONObject();
		List<Reports> obj3 = null;
		int ReportCode = 1;
		
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();

			if (connection != null) {
				if(campaignId.equals("all") || campaignId.equals("")){
					QueryString = "Select sum(reach)as reach,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
							+ "'"+startDate + "' AND '" + endDate + "' GROUP by date,campaign_id";
				}
				else{
				QueryString = "Select sum(reach)as reach,sum(mediacost)as cost,date,campaign_id,channel FROM datawarehouse WHERE date between "
						+ "'"+startDate + "' AND '" + endDate + "' AND campaign_id in ("+campaignId+") GROUP by date,campaign_id,channel";
				}
				
				System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

			//	int count = 0;

			//	while (rs.next()) {
			//	    ++count;
				    // Get data from the current row and use it
			//	}
				
			//	System.out.println(count);
				
				
				obj1=DTOPopulator.populateDTO(rs);
                obj2=DTOProcessor.ProcessReportDTO(obj1, startDate, endDate, ReportCode);
                obj3=DTOFilter.FilterReportDTO(obj2,startDate, endDate, ReportCode); 	
				
				
				//	jo.put("data",obj1);
				
				//Resultset to json converter
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}

		 
		finally{
			
			     DBUtil.close(rs);
			     DBUtil.close(preparedStatement);
				 DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj3;
	}
	
	
	
public List<Reports> FetchAgeImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
					QueryString ="SELECT SUM(impression)AS impression,age FROM datawarehouse WHERE age != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+ "' GROUP BY age";
				else	
					QueryString ="SELECT SUM(impression)AS impression,campaign_id,age,channel FROM datawarehouse WHERE age != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+ "' AND campaign_id in ("+campaignId+") GROUP BY campaign_id,age";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
 
		 finally{
			
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		return obj1;
	}
	
	
	
	public List<Reports> FetchGenderImpData(String startDate, String endDate, String campaignId) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<Reports> obj1 = null;
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				if(campaignId.equals("all") || campaignId.equals(""))
					QueryString ="SELECT SUM(impression)AS impression,gender FROM datawarehouse WHERE gender != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+ "' GROUP BY gender";
				else	
					QueryString ="SELECT SUM(impression)AS impression,campaign_id,gender,channel FROM datawarehouse WHERE gender != '' AND date between "
							+ "'"+startDate + "' AND '" + endDate+ "' AND campaign_id in ("+campaignId+") GROUP BY campaign_id,gender";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				obj1=DTOPopulator.populateDTO(rs);

			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
 
		 finally{
			 
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj1;
	}
	
	
	
	
	
	
	
	
	
	

	public List<String> extractCampaignIds(String startDate, String endDate) {
		Connection connection = null;
		Statement preparedStatement = null;
		ResultSet rs = null;
		String QueryString = null;
		List<String> obj1 = new ArrayList<String>();
	//	JSONObject jo = new JSONObject();
		
		try {
			DBConnector obj = new DBConnector();
			connection = obj.getPooledConnection();


			if (connection != null) {

				QueryString ="SELECT DISTINCT(campaign_id)AS campIds FROM datawarehouse  WHERE date between "
						+ "'"+startDate + "' AND '" + endDate + "'";
				
                System.out.println(QueryString+"\n");
				
				preparedStatement = connection.createStatement();
				
				rs = preparedStatement.executeQuery(QueryString);

				 while (rs.next()) {
			     String campId = rs.getString("campIds");
			     obj1.add(campId);
				 }
				
			//	jo.put("",obj1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		 finally{
			
			    DBUtil.close(rs);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
				
			} 
		
		
		
		
		return obj1;
	}
	








}
