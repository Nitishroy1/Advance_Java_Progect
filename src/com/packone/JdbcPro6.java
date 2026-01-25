package com.packone;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcPro6 {
	String driver="oracle.jdbc.OracleDriver";
	String dbUrl="jdbc:oracle:thin:@localhost:1521:orcl"; 
	//String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";// the give the error
	String dbUname="MYDB4pm";
	String dbPwd="2004";
	
	void meth1() {
		IO.println("Implement jdbcRowSet");
		try {
			RowSetFactory rsf=RowSetProvider.newFactory();
			
			JdbcRowSet jrs=rsf.createJdbcRowSet();
			jrs.setUrl(dbUrl);
			jrs.setUsername(dbUname);
			jrs.setPassword(dbPwd);
			jrs.setCommand("select * from employee");
			jrs.execute();
			
			while(jrs.next()) {
				IO.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4));
			}
			
			IO.println("------------");
			jrs.afterLast();
			while(jrs.previous()) {
				IO.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getString(4));
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//
	void meth2() {
		IO.println("Implement cachRowSet");
		try 
		{
			RowSetFactory rs=RowSetProvider.newFactory();
		    CachedRowSet cr	=rs.createCachedRowSet();
		    cr.setUrl(dbUrl);
		    cr.setUsername(dbUname);
		    cr.setPassword(dbPwd);
		    cr.setCommand("select eid, efname,esal from employee");
		    cr.execute();
		    while(cr.next()) 
		    {
		    	String e_id=cr.getString(1);
		    	if(e_id.equals("102")) {
		    		cr.updateInt(3, 5000);
		    		cr.updateRow();
		    		IO.println("Data Updated....");
		    	}
	
		    	
		    }
		    cr.acceptChanges();
		    cr.beforeFirst();
		    while(cr.next()) 
		    {
		    	String e_id=cr.getString(1);
		    	if(e_id.equals("102")) {
		    	  IO.println(cr.getString(1)+" "+ cr.getString(2)+" "+ cr.getInt(3));
		    	}
	
		    	
		    }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	void main() {
		JdbcPro6 j=new JdbcPro6();
		j.meth1();
		j.meth2();
	}
}
