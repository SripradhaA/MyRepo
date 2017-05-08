package com.EMD.LSDB.action.CRForm;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

public class Demo3 {
	public static void main(String[] args) {

		// int UserID = 0;
		// String projectName = "testproj";
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		CallableStatement  cs = null;
		Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@172.18.24.148:1521:orcl","lsdb","lsdb");
		ResultSet rs = null;
		//Connection con = DriverManager.getConnection("jdbc:mariadb://172.18.24.148:3306/lsdb", "root", "techm");		
		
		String Total = "call SP_INSERT_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                cs = con1.prepareCall(Total);		
		      	//cs = con.prepareCall(Total);
			    cs.setString(1,"Fi123");
				cs.setString(2,"Hello@gmail.com");
				cs.setString(3,"Fi");
				cs.setString(4,"Finland");
				cs.setString(5,"Fizz");
				cs.setString(6,"0000000000");
				cs.setString(7,"Fi123456");
				cs.setString(8,"Sales Manager");
				cs.setString(9,"Fi");
				cs.setString(10,"Fi");
                //cs.registerOutParameter(2,Types.VARCHAR);
             cs.registerOutParameter(11,OracleTypes.INTEGER);
             cs.registerOutParameter(12,OracleTypes.VARCHAR);
               cs.registerOutParameter(13,OracleTypes.VARCHAR);
                 //cs.setInt(3,1);
	            cs.execute();
           String User = cs.getString(11);
           
           System.out.println(User);
          ResultSet rs1 = cs.executeQuery(User);
          while (rs1.next())
              {
        	  System.out.println(rs1.getString(1));
        	  System.out.println(rs1.getString(2));
        	  System.out.println(rs1.getString(3));
        	  System.out.println(rs1.getString(4));
        	  System.out.println(rs1.getString(5));
        	  System.out.println(rs1.getString(6));
        	  System.out.println(rs1.getString(7));
        	  System.out.println(rs1.getString(8));
        	  System.out.println(rs1.getString(9));
        	  System.out.println(rs1.getString(10));
        	  System.out.println(rs1.getString(11));
        	  System.out.println(rs1.getString(12));
        	  System.out.println(rs1.getString(13));
        	  
          	  }
             }
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			

		}

		
	}
}
