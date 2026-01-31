package com.colleable_Statement;

import java.awt.Window.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class Jdbc7Pro {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
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
	
	void meth1() {
		IO.println("Implement callabelStatement");
		try {
			Connection con= connect();
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
	//retrive data 
	 void method2() {
		 IO.println("Implement callabelStatement");
		 try {
			Connection con= connect();
			CallableStatement cstmt=con.prepareCall("{call  RetriveData(?,?,?,?,?)}");
			String e_id=IO.readln("Enter Employee e_id: ");
			cstmt.setString(1, e_id);
			cstmt.registerOutParameter(2,Types.VARCHAR);
			cstmt.registerOutParameter(3,Types.VARCHAR);
			cstmt.registerOutParameter(4,Types.NUMERIC);
			cstmt.registerOutParameter(5,Types.NUMERIC);
			cstmt.execute();
			IO.println("Employee E_ID: "+e_id);
			IO.println("Employee Name: "+cstmt.getString(2));
			IO.println("Employee DESGN: "+cstmt.getString(3));
			IO.println("Employee Basic Sal: "+cstmt.getInt(4));
			IO.println("Employee Total sal: "+cstmt.getInt(5));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }
	 // function
	 void method3() {
		 try {
			Connection con=connect();
			CallableStatement cstmt=con.prepareCall("{call ?:=RetriveTSal(?)}");
			String eid=IO.readln("Enter Employee E_ID: ");
			cstmt.setString(2, eid);
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.execute();
			IO.println("-----Employee detail------");
			IO.println("Employee e_id: "+ eid);
			IO.println("Employee Tsal: "+cstmt.getInt(1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }
	
	void main() {
		Jdbc7Pro p=new Jdbc7Pro();
		//p.meth1();
		//p.method2();
		p.method3();
	}
}
