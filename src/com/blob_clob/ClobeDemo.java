package com.blob_clob;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClobeDemo {
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
	
	//method1
	void meth1() {
		Connection con=connect();
		try (con){
		PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
		pstmt1.setString(1, "101");
		FileReader fl =new FileReader("D:\\nit_java\\text_java.txt");
		pstmt1.setClob(2, fl);
		int rowCount=pstmt1.executeUpdate();
		
		if(rowCount==0) {
		  IO.println("NO data  will be inserted");
		}
		else {
			 IO.println(" text data will be inserted");
		}
		
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//method retrive blob data from the data base;
	void meth2() {
		IO.println("Retrive the text to the db");
		Connection con=connect();
		try(con) {
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1, "101");
			ResultSet rs= pstmt2.executeQuery();
			if(rs.next()) {
				Clob b=rs.getClob(1);
				Reader data=b.getCharacterStream();
				BufferedReader br=new BufferedReader(data);
				FileWriter fb =new FileWriter("D:\\nit_java\\text_java2.txt");
				String line;
				while((line=br.readLine())!=null) {
					fb.write(line);
				}
				br.close();
				fb.close();
				
				IO.println("clob data  retrive");
			}
			else {
				IO.println("clob data  not retrive");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	void main() {
		ClobeDemo cl=new ClobeDemo();
	   // cl.meth1();
		cl.meth2();
		
		
	}

}
