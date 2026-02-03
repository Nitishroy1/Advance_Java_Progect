package com.blob_clob;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlobInsert_read {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery1="insert into blobdemo values(?,?)";
	String sqlQuery2="select img from blobdemo where id=?";
	
	
	
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
		FileInputStream fl =new FileInputStream("D:\\nit_java\\p1.jpg");
		pstmt1.setBlob(2, fl, fl.available());
		int rowCount=pstmt1.executeUpdate();
		if(rowCount==0) {
		  IO.println("NO data img will be inserted");
		}
		else {
			 IO.println(" img data will be inserted");
		}
		
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//method retrive blob data from the data base;
	void meth2() {
		IO.println("Retrive the img to the db");
		Connection con=connect();
		try(con) {
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1, "101");
			ResultSet rs= pstmt2.executeQuery();
			if(rs.next()) {
				Blob b=rs.getBlob(1);
				byte arr[]=b.getBytes(1, (int)b.length());
				FileOutputStream fb =new FileOutputStream("D:\\nit_java\\p2.jpg");
				fb.write(arr);
				
				IO.println("Img retrive");
			}
			else {
				IO.println("Img not retrive");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void main() {
		BlobInsert_read bl=new BlobInsert_read();
		//bl.meth1();
		bl.meth2();
	}

}
