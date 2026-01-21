package com.packone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class JdbcPro5 {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	Connection connect() {
		
		Connection con=null;
		try 
		{
			Class.forName(driver);
			con= DriverManager.getConnection(dbUrl,dbUname,dbPwd);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	//patient data
	public void patientOperation() {
		Connection con= connect();
		try(con) 
		     {
			
			String sql = "INSERT INTO  PATIENT VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt1 = con.prepareStatement(sql);
			PreparedStatement pstmt2=con.prepareStatement("select * from patient");
			PreparedStatement pstmt3=con.prepareStatement("select * from patient where pid=?");
			PreparedStatement pstmt4=con.prepareStatement("update patient set age=? where pid=?");
			PreparedStatement pstmt5=con.prepareStatement("delete from patient where pid=?");
			
			while(true) {
				
				IO.println("-----------patient data----------");
				IO.println("1. Addding patient Data: ");
				IO.println("2. View patient Data: ");
				IO.println("3. retrive patient Data: ");
				IO.println("4. Update patient Data: ");
				IO.println("5. delete patient Data: ");
				IO.println("6.  Exit ");
				
				int choice=Integer.parseInt(IO.readln("Enter your choice: "));
				switch(choice) {
				case 1->
				{
				 IO.println("Adding patient data: ");
				 String id=	IO.readln("Enter patent Id: ");
				 String name=IO.readln("Enter patient Name: ");
				 int age=Integer.parseInt(IO.readln("Enter patient age: "));
				 long contactNo=Long.parseLong(IO.readln("Enter patient contact no: "));
				 pstmt1.setString(1, id);
				 pstmt1.setString(2, name);
				 pstmt1.setInt(3, age);
				 pstmt1.setLong(4, contactNo);
				 
				 int rowCount=pstmt1.executeUpdate();
				 if(rowCount<0) {
					 IO.println("No  patient data will be add");
				 }
				 else {
					 IO.println("patient data added succesfully");
				 }
					
				}
				case 2->{
					IO.println("View patient data:");
					ResultSet rs=pstmt2.executeQuery();
					while(rs.next()) {
						IO.println(rs.getObject(1)+" "+ rs.getObject(2)+" "+ rs.getObject(3)+" "+rs.getObject(4));
					}
					IO.println("View the patient data don");
					
				}
				case 3->{
					IO.println("retrive patient data : ");
					String id=IO.readln("Enter the patient id which you want to retrive:");
					pstmt3.setString(1, id);
					ResultSet rs=pstmt3.executeQuery();
					while(rs.next()) {
						IO.println(rs.getObject(1)+" "+ rs.getObject(2)+" "+ rs.getObject(3)+" "+rs.getObject(4));
					}
					
				}
				case 4->{
					int age=Integer.parseInt(IO.readln("Enter patent New Age: "));
					pstmt4.setInt(1, age);
					
					String id=IO.readln("Enter the patient id which you want to Update:");
					pstmt4.setString(2, id);
					
					int rowCount= pstmt4.executeUpdate();
					if(rowCount<0) {
						IO.println("no row will we updated. patient with  pid: "+id+" will not exite");
					}
					else {
						IO.println(rowCount+"Patient with  pid: "+id+" record will be updated");
					}
					
				}
				case 5->{
					String id=IO.readln("Enter the patient id which you want to delete:");
					pstmt5.setString(1, id);
					int rowCount= pstmt5.executeUpdate();
					if(rowCount<0) {
						IO.println("no row will we deleted. patient with  pid: "+id+" will not exite");
					}
					else {
						IO.println(rowCount+"Patient with  pid: "+id+" record will be deleted");
					}
					
				}
				case 6->{
					IO.println("Thanks for vesiting: ");
					System.exit(0);
					
				}
				default ->{
					IO.println(" your choice is wrong , plz select correct choice");
				}
				}
			}
			
		} 
		catch (SQLIntegrityConstraintViolationException e) 
		{
			IO.println("Patient id can't  be dupicate");
		}
		catch (Exception e) 
		{
	       e.printStackTrace();
		}
		
		
		
	}
	void main() {
		JdbcPro5 j= new JdbcPro5();
		j.patientOperation();
	}
	
	
}