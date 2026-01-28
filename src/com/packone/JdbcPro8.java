package com.packone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

public class JdbcPro8 {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	
	String sqlQuery="update TRAINSETAVAILABILITY set AVAILABLE_SEATS=AVAILABLE_SEATS-1 where train_id=?"+
	" and journey_date=? and class=? and AVAILABLE_SEATS>0";
	String sqlQuery2="Insert into BOOKINGDETAILS VALUES(?,?,?,?,?)";
	String sqlQuery3="select  PAYMENT_STATUS from customerpayment where customer_id=?";
	String sqlQuery4="update bookingdetails set status='Confirmed' where CUSTOMER_ID=?";
	 Connection connect() {
		 Connection con=null;
		 try {
			 Class.forName(driver);
			 con= DriverManager.getConnection(dbUrl,dbUname,dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return con;
	 }
	 //void meth()1
	 void meth1() {
		 IO.println("Implementing Transaction Management");
		 Connection con= connect();
		 try {
			
			IO.println("db connected");
			IO.println("before desable Autocommet"+ con.getAutoCommit());
			con.setAutoCommit(false);
			IO.println("After desable Autocommet"+ con.getAutoCommit());
			PreparedStatement pstmt=con.prepareStatement(sqlQuery);
			pstmt.setString(1, "12345");
			pstmt.setString(2, "2024-10-10");
			pstmt.setString(3, "Sleeper");
			int rowCount1=pstmt.executeUpdate();
			if(rowCount1==0) {
				throw new RuntimeException("seats Not available for booking");
			}else {
				IO.println("seat is locked for booking");
				
			}
			
			Savepoint sp=con.setSavepoint();
			
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1, "B101");
			pstmt2.setString(2, "12345");
			pstmt2.setString(3, "C123");
			pstmt2.setInt(4, 1);
			pstmt2.setString(5, "Payment Pending");
		   int rowCount=	pstmt2.executeUpdate();
			if(rowCount==0) {
				throw new RuntimeException("booking record Not created");
			}else {
				IO.println("booking record created\n Awaiting for Payment Confirmation!!");
				
			}
			PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
			pstmt3.setString(1, "C123");
			ResultSet rs=pstmt3.executeQuery();
			String status="Failed";
			if(rs.next()) {
				status=rs.getString(1);
				if(status.equals("Failed")) {
					throw new RuntimeException("Transaction failed at payment portal");
				}
				else {
					PreparedStatement pstmt4=con.prepareStatement(sqlQuery4);
					pstmt4.setString(1, "C123");
					int rowCount4=pstmt4.executeUpdate();
					if(rowCount4==0) {
						throw new RuntimeException("Transaction failed at Payment portal");
					}else {
					IO.println("Ticket Succesfully Booked!!");
					con.commit();
				}
			  }
			}
			else {
				IO.println("Transaction Failed");
			}
			
			
		}
		 catch (RuntimeException e) {
			IO.println(e.getMessage());
		}
		 catch (Exception  e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	 }
	void main() {
		JdbcPro8 p=new JdbcPro8();
		p.meth1();
		
	}

}
