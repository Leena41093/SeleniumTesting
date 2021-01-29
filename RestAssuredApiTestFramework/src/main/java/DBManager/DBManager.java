package DBManager;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

//package mypackage;
import java.sql.*;

import org.testng.annotations.Test;


public class DBManager 
{
      
	        static int lport;
	        static String rhost;
	        static int rport;
	    
	        public static void go(){
	        String user = "root";
	        String password = "6LWbS4W8";
	        String host = "103.4.235.55";
	        int port=22;
	        try
	        {
	            JSch jsch = new JSch();
	            Session session = jsch.getSession(user, host, port);
	            lport = 4321;
	            rhost = "localhost";
	            rport = 3306;
	            session.setPassword(password);
	            session.setConfig("StrictHostKeyChecking", "no");
	            System.out.println("Establishing Connection...");
	            session.connect();
	            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
	         }
	        catch(Exception e)
	        {
	        	System.err.print(e);
	        }
	    }
	    public static void main(String[] args) {
	        try{
	            go();
	        } catch(Exception ex){
	            ex.printStackTrace();
	        }
	          System.out.println("An example for fetching a Row from Mysql Database!");
	          Connection con = null;
	          String driver = "com.mysql.cj.jdbc.Driver";
	          String url = "jdbc:mysql://" + rhost +":" + lport + "/";
	          String db = "dojo";
	          String dbUser = "root";
	          String dbPasswd = "Dojo1@1234";
	          try{
	          Class.forName(driver);
	          con = DriverManager.getConnection(url+db, dbUser, dbPasswd);
	          try{
	        	    Statement stmt = con.createStatement(); 
	        		String query = "select * from classes where class_id=441";
	        		// Get the contents of userinfo table from DB
	        		ResultSet res = stmt.executeQuery(query);
	        		// Print the result untill all the records are printed
	        		// res.next() returns true if there is any next record else returns false
	        		while (res.next())
	        		{
	        	   String fname = res.getString("class_name");
	        		System.out.print(res.getString(1));
	        		System.out.print(" " + res.getString(2));
	        		System.out.print(" " + res.getString(3));
	        		System.out.println(" " + res.getString(4));
	        		
	        		}
	        		}
	        		catch(Exception e)
	        		{
	        		e.printStackTrace();
	        		}

	          }
	          catch (Exception e){
	          e.printStackTrace();
	          }
	         
	          }
	    public void setUp()
	    {
	    	RestAssured.authentication = RestAssured.preemptive().basic("leena.patil@buzzybrains.com", "123456");
	        RestAssured.baseURI = "https://dojocentral.online";

	    }
//	    @Test
//	    public void test(){
//	    	DBManager dbmanager =new DBManager();
//	            dbmanager.go();
//	            given().when().get("https://dojocentral.online/api/v1/dojo/class/71/upcoming").then().log().all();
//		        System.out.println("Execution Successful");
//	    }
	    }
	    
    



	
