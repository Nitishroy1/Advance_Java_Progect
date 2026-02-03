package com.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class BatchDemo1 {
	
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery1="insert into blobdemo2 values(?,?)";
	String sqlQuery2="select CHRADATA   from blobdemo2 where id=?";
	
	
	
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
		IO.println("Implementing batch procesing");
		Connection con=connect();
		try(con) 
		{
		Statement stmt=con.createStatement();	
		 int n=Integer.parseInt(IO.readln("Ente how manay query you add: "));
		for(int i=1;i<=n;i++) {
			IO.println("Enter your "+i+" Query");
			stmt.addBatch(IO.readln());
		}
		IO.println("Query add in the batch");
	    int rowCount[]	=stmt.executeBatch();
	    IO.println("=====>"+ Arrays.toString(rowCount));
	    stmt.clearBatch();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	void main() {
		BatchDemo1 batch=new BatchDemo1();
		batch.meth1();
		
	}

}
