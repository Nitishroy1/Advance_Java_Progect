package com.packone;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbcpro1 {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	void connect() {
		System.out.println("Connecting to the Database");
		try 
		{
			Class.forName(driver);
			Connection con= DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			System.out.println("Connection Created");
			con.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbcpro1 obj=new Jdbcpro1();
		obj.connect();
	}

}
