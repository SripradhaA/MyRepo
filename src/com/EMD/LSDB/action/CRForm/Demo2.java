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

public class Demo2 {
	public static void main(String[] args) {

		// int UserID = 0;
		// String projectName = "testproj";
		
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
		CallableStatement  cs = null;
	//	Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@172.18.24.148:1521:orcl/lsdb","lsdb","lsdb");
		ResultSet rs = null;
		Connection con = DriverManager.getConnection("jdbc:mariadb://172.18.24.148:3306/lsdb", "root", "techm");		
				String Total = "call SP_USER_TEST(?,?)";
		      	cs = con.prepareCall(Total);
			//	cs.setString(1,"Fi");
				cs.setDouble(1,2);
                cs.registerOutParameter(2,Types.VARCHAR);
             //   cs.registerOutParameter(4,Types.DOUBLE);
             //   cs.registerOutParameter(5,Types.VARCHAR);
            //    cs.registerOutParameter(6,Types.VARCHAR);
                // cs.setInt(3,1);
	            cs.execute();
           String User = cs.getString(2);
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
          	  }
             }
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			

		}

		
	}
}
