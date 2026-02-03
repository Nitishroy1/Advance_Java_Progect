package com.AssingmentFood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FoodOrder {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery1="select * from food";
	String sqlQuery2="select food_id, price from food where food_id=?";
	String sqlQuery3="insert into order1 values(?,?,?,?)";
	
	
	
	
	
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
	
	//method1
	void methd1() {
		IO.println("all food list");
		Connection con = connect();
		
		try(con) {
			 PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			  ResultSet rs= pstmt1.executeQuery();
			  while(rs.next()) {
				  IO.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3));
				  
			   }
			  
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// total price
	  void  meth2() {
		IO.println("calculate total price");
		
		Connection con=connect();
		
		try(con) {
			  PreparedStatement pstmt1=con.prepareStatement(sqlQuery2);
			  int foodId=Integer.parseInt(IO.readln("Enter food id: "));
			  pstmt1.setInt(1, foodId);
			  int qty=Integer.parseInt(IO.readln("Enter food quentity: "));
			  
			  ResultSet rs= pstmt1.executeQuery();
			  while(rs.next()) {
				if(rs.getInt(1)==foodId) {
					int tprice= rs.getInt(2) * qty;
					IO.println("your food id : "+foodId+" total price is "+ tprice);
				}
				  
			   }
			  
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//insert to order
	
	void meth3() {
		Connection con= connect();
		try(con) {
			PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
			int orderid=Integer.parseInt(IO.readln("Enter order id: "));
			int foodid=Integer.parseInt(IO.readln("Enter food id: "));
			int quantity=Integer.parseInt(IO.readln("Enter food Quentity : "));
			pstmt3.setInt(1, orderid);
			pstmt3.setInt(2, foodid);
			pstmt3.setInt(3, quantity);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
void main() {
	FoodOrder fod=new FoodOrder();
	//fod.methd1();
	fod.meth2();
	
}
}
