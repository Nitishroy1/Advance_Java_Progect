package com.packone;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc7Pro {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	
	
	void meth1() {
		IO.println("Implement callabelStatement");
		try {
			Class.forName(driver);
			Connection con= DriverManager.getConnection(dbUrl);
	         CallableStatement cstmt=con.prepareCall("{call Insertdata(?,?,?,?,?)}");
	         String id=IO.readln("Enter Employee id");
	         String name=IO.readln("Enter Employee Name");
	         String desg=IO.readln("Enter Employee Designation: ");
	         int ebsal=Integer.parseInt(IO.readln("Enter Employee basic salary"));
	         
	         float tsal= ebsal+ (0.35f*ebsal)+ (0.15f*ebsal);
	         cstmt.setString(1,id);
	         cstmt.setString(2,name);
	         cstmt.setString(3,desg);
	         cstmt.setInt(4,ebsal);
	        cstmt.setFloat(5,tsal);
	         cstmt.execute();
	         IO.println("Data Inserted");
	           
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	void main() {
		
	}
}
