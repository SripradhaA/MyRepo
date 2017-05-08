package com.EMD.LSDB.action.CRForm;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

public class Demo4 {
	public static void main(String[] args) {

		// int UserID = 0;
		// String projectName = "testproj";
		
		try {
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		CallableStatement  cs = null;
		//Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@172.18.24.148:1521:orcl","lsdb","lsdb");
		ResultSet rs = null;
		Connection con = DriverManager.getConnection("jdbc:mariadb://172.18.24.148:3306/lsdb", "root", "techm");		
		
		String Giri = "call SP_DELETE_USER(?,?,?)";
                //cs = con1.prepareCall(Giri);		
		      	cs = con.prepareCall(Giri);
			    cs.setString(1,"Gi123");
				cs.setString(2,"Gi1234");
				/*cs.setString(3,"Fi");
				cs.setString(4,"Finland");
				cs.setString(5,"Fizz");
				cs.setString(6,"0000000000");
				cs.setString(7,"Fi123456");
				cs.setString(8,"Sales Manager");
				cs.setString(9,"Fi");
				cs.setString(10,"Fi");*/
                //cs.registerOutParameter(2,Types.VARCHAR);
             cs.registerOutParameter(3,Types.INTEGER);
             /*cs.registerOutParameter(4,Types.VARCHAR);
               cs.registerOutParameter(5,Types.VARCHAR);*/
                 //cs.setInt(3,1);
	            cs.execute();
           String User = cs.getString(1);
           
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
			System.out.println("Print the ERROr----" +e.getMessage());
		} 
		finally {
			

		}

		
	}
}
