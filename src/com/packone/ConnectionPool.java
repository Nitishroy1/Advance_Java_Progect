package com.packone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {
	String driver,dbUrl,dbUname,dbPwd;
	
	Vector<Connection> v=new Vector<Connection>();

	public ConnectionPool(String driver, String dbUrl, String dbUname, String dbPwd) {
		
		this.driver = driver;
		this.dbUrl = dbUrl;
		this.dbUname = dbUname;
		this.dbPwd = dbPwd;
	}
	
	public void con_Initialization() {
		IO.println("Collection Is Empty");
		IO.println("There are "+v.size()+" collection Object");
		while(v.size()<5) {
			try {
				Class.forName(driver);
				Connection con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
				v.add(con);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		for(Object o: v) {
			IO.println(o);
			IO.println("There are "+v.size()+" collection Object");
	}
	
	
	

}
	public Connection con_Acquisition() {
		Connection con=v.get(0);
		v.remove(0);
		return con;
	}
	public void con_Return(Connection con) {
		IO.println("Adding the conection object to the connetion pool");
		v.add(con);
		IO.println("-----------------------------------");
		IO.println("There are "+v.size()+" collection Object");
		for(Object o: v) {
			IO.println(o);
			
	}
		
	}
	}
