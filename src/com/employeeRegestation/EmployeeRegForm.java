package com.employeeRegestation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeRegForm {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	String sqlQuery="insert into employeeinfo values(?,?,?,?,?,?,?)";
	String sqlQuery2="select eid,ename from employeeinfo where eid=? and ename=?";
	String sqlQuery3="select * from employeeinfo";
	String sqlQuery4="update employeeinfo set emailid=?, EPHNO=? where eid=?";
	String sqlQuery5="delete from employeeinfo where esal between ? and ?";
	String sqlQuery6="update employeeinfo set esal=(esal +(esal*(?/100)))";
	
	
	Connection conect() {
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
			
		} catch (Exception e) {
			IO.println(e.getMessage());
			e.printStackTrace();
		}
		return con;
				
	}
	//register form
	boolean registerFrom() {
		Connection con=conect();
		try(con) {
			PreparedStatement pstmt=con.prepareStatement(sqlQuery);
			IO.println("Fill the registation Form: ");
			String eid=IO.readln("Enter Employee ID: ");
			pstmt.setString(1, eid);
			
			String name=IO.readln("Enter Employee Name: ");
			pstmt.setString(2, name);
			
			int sal=Integer.parseInt(IO.readln("Enter Employee Salary: "));
			pstmt.setInt(3, sal);
			
			String fname=IO.readln("Enter Employee First Name: ");
			pstmt.setString(4, fname);
			
			String lname=IO.readln("Enter Employee Last Name: ");
			pstmt.setString(5, lname);
			
			String email=IO.readln("Enter Employee Email ID: ");
			pstmt.setString(6, email);
			
			long phno=Long.parseLong(IO.readln("Enter Employee Phon NO: "));
			pstmt.setLong(7, phno);
			int rowCount=pstmt.executeUpdate();
			if(rowCount==0) {
				IO.println("NO data will be insert");
			}
			else {
				IO.println("Data inserted sucesfully registore");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	//logine form
	boolean logineForm() {
		Connection con=conect();
		try(con) {
		 PreparedStatement	pstmt2=con.prepareStatement(sqlQuery2);
		 String eid=IO.readln("Enter Employee ID: ");
		 pstmt2.setString(1, eid);
		 
		 String name=IO.readln("Enter Employee Name: ");
		 pstmt2.setString(2, name);
		 
		 ResultSet rs=pstmt2.executeQuery();
		 
		 if(rs.next()) {
			 String e_id=rs.getString(1);
			 String eName=rs.getString(2);
			 if(e_id.equalsIgnoreCase(eid) && eName.equalsIgnoreCase(name)) {
				 IO.println("Welcome to "+name);
			 }
			 else {
				 IO.println("Logine faild try Agane");
			 }
		 }
		 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//Show all Employee
	void show() {
		Connection con=conect();
		try(con) {
		PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
		ResultSet rs=pstmt3.executeQuery();
		
		while(rs.next()) {
			IO.println(rs.getString(1)+" "+ rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getLong(7));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//update Employee data
	void update() {
		Connection con=conect();
		try(con) {
		PreparedStatement pstmt4	=con.prepareStatement(sqlQuery4);
		String email=IO.readln("Enter Employee Email ID: ");
		pstmt4.setString(1, email);
		
		long phno=Long.parseLong(IO.readln("Enter Employee Phon NO: "));
		pstmt4.setLong(2, phno);
		
		String eid=IO.readln("Enter Employee ID: ");
		pstmt4.setString(3, eid);
		int rowCount4=pstmt4.executeUpdate();
		if(rowCount4==0) {
			IO.println("NO row will be updated");
		}
		else {
			IO.println("row will be Updated");
		}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//delete data
	void delete() {
		Connection con=conect();
		try(con) {
		PreparedStatement pstmt5=con.prepareStatement(sqlQuery5);
		int sal=Integer.parseInt(IO.readln("Enter Employee Salary rang: "));
		pstmt5.setInt(1, sal);
		int sal2=Integer.parseInt(IO.readln("Enter Employee Salary rang: "));
		pstmt5.setInt(2, sal);
		
		
		int rowCount4=pstmt5.executeUpdate();
		if(rowCount4==0) {
			IO.println("NO row will be deleted");
		}
		else {
			IO.println(rowCount4+"row will be deleted");
		}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	//increase salary by ? %
	void salIncrement() {
		Connection con=conect();
		try(con) {
		PreparedStatement pstmt6=con.prepareStatement(sqlQuery6);
		int salprasentincrement=Integer.parseInt(IO.readln("Enter NO of percentage salary Increase "));
		pstmt6.setInt(1, salprasentincrement);
		
		
		
		int rowCount4=pstmt6.executeUpdate();
		if(rowCount4==0) {
			IO.println("NO row will be updated");
		}
		else {
			IO.println(rowCount4+"row will be updated");
		}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
void main() {
	EmployeeRegForm eform=new EmployeeRegForm();
	boolean isRegister=false;
	boolean isLogin =false;

	String loginchoice=IO.readln("Do you want to Login/Regeister: Login/Regeister: ");
	if(loginchoice.equalsIgnoreCase("Regeister")) {
		isRegister= eform.registerFrom();
	}
	else if(loginchoice.equalsIgnoreCase("Login")) {
	 isLogin=eform.logineForm();
	 isRegister=true;
	}
	
	IO.println("Do you want the see the Employee table data : Yes/No");
	String choice1=IO.readln();
	if(choice1.equalsIgnoreCase("yes")) {
	if(isRegister && isLogin ) {
	
	while(true) {
		IO.println("-------Employee Data-----------");
		IO.println("1. Show All Employee:");
		IO.println("2. Update MailId & Phon NO base on EID:");
		IO.println("3. Delete Employee whose salary b/W the ? and ? range Employee:");
		IO.println("4. Increase ? % salary of each Employee:");
		IO.println("5. Exit...!");
		int choice =Integer.parseInt(IO.readln("Enter the choice: "));
		switch(choice) {
		case 1->{
			IO.println("Show All Employee: ");
			eform.show();
		}
		case 2->{
			eform.update();
		}
		case 3->{
			eform.delete();
			
		}
		case 4->{
			eform.salIncrement();
			
		}
		case 5->{
			IO.println("Thanks for visiting....!!!");
			System.exit(0);
		}
		}
	}
	
}
	else {
		IO.println("first go for regeister/login ");
	}
 }
	else {
		IO.println("Thanks for visiting....!!!");
	}


}
}

