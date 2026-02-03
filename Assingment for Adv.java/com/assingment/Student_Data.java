package com.assingment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Student_Data {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	
	
	Connection connect() {
		
		Connection con=null;
		try 
		{
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	void meth1() {
		try {
			Connection con=connect();
			CallableStatement pstmt=con.prepareCall("{call insertdata1(?,?,?,?,?,?,?,?,?)}");
			String sid=IO.readln("Enter student ID: ");
			String srol=IO.readln("Enter student rollNO: ");
			String sname=IO.readln("Enter student Name: ");
			String sbranch=IO.readln("Enter student scholl Branch: ");
			String shno=IO.readln("Enter student hous NO: ");
			String city=IO.readln("Enter student City: ");
			int pincode=Integer.parseInt(IO.readln("Enter Student Pincode: "));
			String mid=IO.readln("Enter student Mid: ");
			long phno=Long.parseLong(IO.readln("Enter Student PhNO: "));
			
			pstmt.setString(1, sid);
			pstmt.setString(2, srol);
			pstmt.setString(3, sname);
			pstmt.setString(4, sbranch);
			pstmt.setString(5, shno);
			pstmt.setString(6, city);
			pstmt.setInt(7, pincode);
			pstmt.setString(8, mid);
			pstmt.setLong(9, phno);
			int rowCount=pstmt.executeUpdate();
		if(rowCount==0) {
			IO.println("NO row will be inserted....");
		}
		else {
			IO.println("Row will be inserted succesfully....");
		}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void main() {
		Student_Data sd=new Student_Data();
		sd.meth1();
		
	}

}
