package com.packone;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcPro2 {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery="select * from employee";
	String sqlInsert="insert into employee VALUES('110','ram','yadav',60000,'hyd')";
	String sqlDelet="delete  employee where eid=103";
	String sqlupdate="update employee set esal=65000 where eid=102";
	
	Connection connect() {
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
		}
		catch (Exception e) {
			//IO.println(e.getMessage());
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	void getEmpData() {
		IO.println("-------------Employee Details-------");
		try {
			Connection con=connect();
			IO.println("Database Connection Succesfully");	
			java.sql.Statement stmt=  con.createStatement();
			 ResultSet rs=stmt.executeQuery(sqlQuery); 
			 while(rs.next()) {
				
				 IO.println(rs.getString(1)+" "+rs.getString(2)+" "+ rs.getString(3)+" " + rs.getString(4));
//			    int sal=rs.getInt(4);
//			    if(sal>=50000) {
//			    	IO.println(sal);
//			    }
			 }
		}
		catch (Exception e) {
			IO.println(e.getMessage());
		}  
	}
	//Insert 
	void getInsert()
	{
		try {
			Connection con = connect();
			java.sql.Statement stmt=con.createStatement();
			int rowCount= stmt.executeUpdate(sqlInsert);
			if(rowCount==0) {
				IO.println("No row Insert ");
			}
			else {
				IO.println(rowCount+ " row is inserted");
				IO.println("_______________-------------________________");
				getEmpData();
			}
			stmt.close();
			con.close();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//delete
	void getDelete() {
		try {
			Connection con= connect();
			IO.println("Database Connection Succesfully");	
			 Statement stmt =con.createStatement();
			 int rowcount = stmt.executeUpdate(sqlDelet);
			 if(rowcount==0) {
					IO.println("No row Delete ");
				}
				else {
					IO.println(rowcount+ " row is deleted");
					IO.println("_______________-------------________________");
					getEmpData();
				}
				stmt.close();
				con.close();
			 
			
			
		} catch (Exception e) {
			// TODO: handle exception
			IO.println(e.getMessage());
		}
	}
	//update table 
	void getUpdate() {
		try 
		{
			Connection con= connect();
			IO.println("Database Connection Succesfully");
			Statement stmt =con.createStatement();
			int rowcount=stmt.executeUpdate(sqlupdate);
			
			if(rowcount==0) {
				IO.println("No row Update");
			}
			else {
				IO.println(rowcount+ " row is updated");
				IO.println("_______________-------------________________");
				getEmpData();
			}
			stmt.close();
			con.close();
		 
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	// Fix: JVM entry point must be public static void main(String[] args)
	public static void main(String[] args) {
		JdbcPro2 jdbc = new JdbcPro2();
	       //jdbc.getEmpData();
		 //jdbc.getInsert();
	     //jdbc.getDelete();
		jdbc.getUpdate();
		
	}

}