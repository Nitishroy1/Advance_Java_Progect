package com.packone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class JdbcPro5 {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	Connection connect() {
		
		Connection con=null;
		try 
		{
			Class.forName(driver);
			con= DriverManager.getConnection(dbUrl,dbUname,dbPwd);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	//patient data
	public void patientOperation() {
		try 
		{
			Connection con= connect();
			String sql = "INSERT INTO  PATIENT VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt1 = con.prepareStatement(sql);
			
			while(true) {
				
				IO.println("-----------patient data----------");
				IO.println("1. Addding patient Data: ");
				IO.println("2. Addding patient Data: ");
				IO.println("1. Addding patient Data: ");
				IO.println("1. Addding patient Data: ");
				IO.println("1. Addding patient Data: ");
				IO.println("1. Addding patient Data: ");
				
				int choice=Integer.parseInt(IO.readln("Enter your choice: "));
				switch(choice) {
				case 1->
				{
				 IO.println("Adding patient data: ");
				 String id=	IO.readln("Enter patent Id: ");
				 String name=IO.readln("Enter patient Name: ");
				 int age=Integer.parseInt(IO.readln("Enter patient age: "));
				 long contactNo=Long.parseLong(IO.readln("Enter patient contact no: "));
				 pstmt1.setString(1, id);
				 pstmt1.setString(2, name);
				 pstmt1.setInt(3, age);
				 pstmt1.setLong(4, contactNo);
				 
				 int rowCount=pstmt1.executeUpdate();
				 if(rowCount<0) {
					 IO.println("No  patient data will be add");
				 }
				 else {
					 IO.println("patient data added succesfully");
				 }
					
				}
				default ->{
					IO.println(" your choice is wrong , plz select correct choice");
				}
				}
			}
			
		} 
		catch (SQLIntegrityConstraintViolationException e) 
		{
			IO.println("Patient id can't  be dupicate");
		}
		catch (Exception e) 
		{
	       e.printStackTrace();
		}
		
		
	}
	void main() {
		JdbcPro5 j= new JdbcPro5();
		j.patientOperation();
	}
	
	
}