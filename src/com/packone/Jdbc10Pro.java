package com.packone;

import java.sql.Connection;

public class Jdbc10Pro {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	public Jdbc10Pro() {
		// TODO Auto-generated constructor stub
	}void meth1(){
		ConnectionPool cp=new ConnectionPool(driver, dbUrl, dbUname, dbPwd);
		cp.con_Initialization();
		IO.println("------User1--------");
		Connection con1=cp.con_Acquisition();
		IO.println("User1"+ con1);
		IO.println("------User2--------");
		Connection con2=cp.con_Acquisition();
		IO.println("User1"+ con2);
		IO.println("------User3--------");
		Connection con3=cp.con_Acquisition();
		IO.println("User1"+ con3);
		
		
		//relese the Conection
		
		IO.println("============================");
		cp.con_Return(con1);
		cp.con_Return(con2);
		cp.con_Return(con3);
		
		
		
	}
	void main() {
		Jdbc10Pro obj=new Jdbc10Pro();
		obj.meth1();
	}

}
