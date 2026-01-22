package com.employeeRegForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmployeeRegForm {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	Connection connect() {
		Connection con=null;
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			
		} catch (Exception e) {
			IO.println(e.getMessage());
		}
		return con;
	}
	
	//reg form
	void employeeReg()
	
	{
		Connection con=connect();
		try (con){
			
			PreparedStatement pt1=con.prepareStatement("INSERT INTO EMPREG(ename,pword,fname,mid,lname,addr,phn) VALUES(?,?,?,?,?,?,?)");
			String ename=IO.readln("Enter Employee name: ");
			String pwd=IO.readln("Enter Password: ");
			String fname=IO.readln("Enter Employee first name: ");
			String mid=IO.readln("Enter Employee Mid name: ");
			String lname=IO.readln("Enter Employee last name: ");
			String add=IO.readln("Enter Employee Address:  ");
			String phon=IO.readln("Enter Employee phone NO: ");
			pt1.setString(1, ename);
			pt1.setString(2, pwd);
			pt1.setString(3, fname);
			pt1.setString(4, mid);
			pt1.setString(5, lname);
			pt1.setString(6, add);
			pt1.setString(7, phon);
			
			
			int rowCount =pt1.executeUpdate();
			if(rowCount<0) {
				IO.println("your data not store");
			}
			else {
				IO.println("your data store succesfully...");
			}
			
		} 
		catch (Exception e) {
			IO.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	void main() {
		
		EmployeeRegForm emp=new EmployeeRegForm();
		emp.employeeReg();
	}

}
