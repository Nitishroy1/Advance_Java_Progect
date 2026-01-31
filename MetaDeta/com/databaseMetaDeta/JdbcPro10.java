package com.databaseMetaDeta;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcPro10 {
	
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	
	String sqlQuery="select * from employee where eid=?";
	
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
		IO.println("Execution metadata programe");
		Connection con=connect();
		try(con) {
		DatabaseMetaData dbmtdt	=con.getMetaData();
		IO.println("getDatabaseProductName "+   dbmtdt.getDatabaseProductName());
		IO.println("getDatabaseProductVersion "+dbmtdt.getDatabaseProductVersion());
		IO.println("supportsStoredProcedures "+dbmtdt.supportsStoredProcedures());
		IO.println("getDriverName "+dbmtdt.getDriverName());
		
	
		
		
		
		//parametter meta data
		IO.println("----------parameterMetadata----------");
		PreparedStatement pstmt=con.prepareStatement(sqlQuery);
		pstmt.setString(1,"101");
		ResultSet rs= pstmt.executeQuery();
		
	     /* ParameterMetaData =pstmt.getParamet;
	   
		
		IO.println(""+pdmtdt.);
		IO.println(""+);
		IO.println(""+);
		IO.println(""+); */
		
		
		//Result set meta data
		
		IO.println("----------parameterMetadata----------");
		
	ResultSetMetaData rsmtdt	=rs.getMetaData();
	
    IO.println("getColumnCount "+	rsmtdt.getColumnCount());
    IO.println("getColumnName"+		rsmtdt.getColumnName(1));
    IO.println("getColumnDisplaySize "+		rsmtdt.getColumnDisplaySize(4));
    IO.println("isAutoIncrement("+		rsmtdt.isAutoIncrement(1));
    
    
    
    
    //RowSet metadata
    
    RowSetFactory rsf= RowSetProvider.newFactory();
    CachedRowSet crs=rsf.createCachedRowSet();
    crs.setUrl(dbUrl);
    crs.setUsername(dbUname);
    
		
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	void main() {
		
	}

}
