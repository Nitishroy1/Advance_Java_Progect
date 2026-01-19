package com.packone;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JdbcPro4 {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	Connection connect() {
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(dbUrl,dbUname,dbPwd);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	//1) method Adding the book
	void AddBook() {
		try {
			Connection con= connect();
			java.sql.Statement stat=con.createStatement();
			String bookId=IO.readln("Enter bookId: ");
			String bookName=IO.readln("Enter book Name: ");
			String bookAuthor=IO.readln("Enter book Author: ");
			String bookGenery=IO.readln("Enter book Genere: ");
			String bookCost=IO.readln("Enter book Cost: ");
			int rowCount=stat.executeUpdate("insert into library VALUES('"+bookId+"','"+ bookName +"','"+ bookAuthor+"','"+bookGenery+"','"+bookCost+"')");
			if(rowCount<0) {
				IO.println("No book insert in to the database: ");
				
			}
			else {
				IO.println(rowCount + " Book Insertad in the database ");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			IO.println(e.getMessage());
		}
		
	}
	void viewBook() {
		try {
			Connection con =connect();
			java.sql.Statement stat=con.createStatement();
			ResultSet result= stat.executeQuery("SELECT * FROM LIBRARY");
			
			while(result.next()) {
				
				IO.println(result.getString(1)+" "+ result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+ result.getString(5));
				
			}
			IO.println("View book from library suscesfule");
			
		
		}
		
		catch (Exception e) {
			// TODO: handle exception
			IO.println(e.getMessage());
		}
	}
	void deleteBook() 
	{
		try {
			Connection con =connect();
			java.sql.Statement stat=con.createStatement();
			String deletId=IO.readln("Enter book Id for deleting: ");
			int rowCount=stat.executeUpdate("delete library where BOOKID='"+deletId+"'");
			if(rowCount<0) {
				IO.println("No book will be deleted: ");
				
			}
			else {
				IO.println(rowCount+ "book will be deleted ");
				IO.println("---------------Remaning Book Are-----------");
				viewBook();
			}
		} catch (Exception e) {
			// TODO: handle exception
			IO.println(e.getMessage());
		}
		
	}
	//4
    void getBook() {
    	try {
			Connection con =connect();
			java.sql.Statement stat=con.createStatement();
			String featchId=IO.readln("Enter book Id for fatching: ");
		    ResultSet result =stat.executeQuery("select * from library where bookid='"+featchId+"'");

			while(result.next()) {
				IO.println(result.getString(1)+" "+ result.getString(2)+" "+result.getString(3)+" "+result.getString(4)+" "+ result.getString(5));
				IO.println("fatching book from library suscesfule");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			IO.println(e.getMessage());
		}
    	
    }
	
	void main() {
		while(true) {
			IO.println("--------------------LIST----------------------");
			IO.println("Choose your Optipon");
			IO.println("1. Add book to the Library");
			IO.println("2. retrive book to the Library");
			IO.println("3. delete book from  the Library");
			IO.println("4. view book from Library");
			IO.println("5. Exit from  Library");
			JdbcPro4 jdbc=new JdbcPro4();
			int choice=Integer.parseInt(IO.readln("Enter your choice: "));
			
			switch(choice) {
			case 1->
			{
				jdbc.AddBook();
				
			}
			case 2->{
				jdbc.getBook();
			}
			case 3->{
				jdbc.deleteBook();
			}
			case 4->{
				jdbc.viewBook();
			}
			case 5->{
				IO.println("Thanks for viseat the Library");
				System.exit(0);
			}
			default->{
				IO.println("Please select correct option: ");
			}
			}
			
		}
		
	}

}
