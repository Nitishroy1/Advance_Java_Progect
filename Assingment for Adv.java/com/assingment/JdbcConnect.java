package com.assingment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class JdbcConnect {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery="select * from employeedemo";
	List<Employee> employee=new ArrayList<Employee>();
	
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
	
	//retrine the data form the employeedemo
	void retriveData() {
		Connection con= connect();
		try(con) {
			PreparedStatement pstmt=con.prepareStatement(sqlQuery);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				int age=rs.getInt(3);
				double sal=rs.getDouble(4);
				employee.add(new Employee(id,name,age,sal));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//retrive data in forward direction
	void listdata() {
		retriveData();
		employee.forEach(IO::println);
		IO.println("--------------reverse order----------------------");
		List<Employee>emp=employee.reversed();
		emp.forEach(IO::println);
		
		IO.println("--------------3rd record----------------------");
		 IO.println( employee.get(1));
		 IO.println("-------------- last 3rd record from bottom----------------------");
		 int  count=1;
		 
		 for(Employee emp2: emp) {
			
			 if(count<=3) {
				 IO.println(emp2);
			 }
			 count++;
			 
		 }
		 
		 
		 
	}
	
	//list of employee
	void listOfEmployee() {
		retriveData();
		for(Employee emp: employee) {
			if(emp.age<25) {
				IO.println(emp);
			}
			
		}
		
	}
	
	
	
	void main() {
		JdbcConnect con=new JdbcConnect();
		//con.listOfEmployee();
		con.listdata();
		
		
	}

}
