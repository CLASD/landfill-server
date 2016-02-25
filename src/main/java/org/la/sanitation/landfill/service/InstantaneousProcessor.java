package org.la.sanitation.landfill.service;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Connection;

@Service
public class InstantaneousProcessor {
	
	//@Value("db.pass") 
	private static String dbPass = "";

	public void process(byte[] bytes) {
		String file = new String(bytes);
		System.out.println(file);
		
		//{"data":[444.0],"date":"1/24/2016","etime":"22:46","stime":"22:46","name":"CH4 PPM","id":0,"grid":5}
		//TODO: parse 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection connection = null;
		String myUrl = "jdbc:mysql://localhost/landfill-data";

		try {
			connection = (Connection) DriverManager.getConnection(myUrl,"root", dbPass);

			Calendar calendar = Calendar.getInstance();
			
		    Date startDate = new java.sql.Date(calendar.getTime().getTime());
		    Date endDate = new java.sql.Date(calendar.getTime().getTime()+7);
			if(connection !=null)
			{
				String query = "INSERT INTO InstantaneousData ( LandFillId, InspectorId, "
						+ "StartTime, FinishTime, InstrumentSerial, maxCH, IMEId, gridNo) VALUES "
						+ "( ?, ?, ?, ?, ?, ?, ?, ?)";

					 
				  // create the mysql insert preparedstatement
				  PreparedStatement preparedStmt = connection.prepareStatement(query);
				  
				  preparedStmt.setInt(1, 2);
				  preparedStmt.setInt(2, 1);
				  preparedStmt.setDate(3, startDate);
				  preparedStmt.setDate(4, endDate);
				  preparedStmt.setInt(5, 1);
				  preparedStmt.setDouble(6, 444.0);
				  preparedStmt.setString (7, null);
				  preparedStmt.setInt (8, 5);
			      
				 
				      // execute the preparedstatement
				  preparedStmt.execute();
			}
			
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		
	}

}
