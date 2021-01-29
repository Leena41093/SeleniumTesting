package DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

public class NewDBConnection {

	static int lport;
    static String rhost;
    static int rport;
	static Connection con = null;


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
//        System.out.println("An example for fetching a Row from Mysql Database!");
        Connection con = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://" + rhost +":" + lport + "/";
        String db = "dojo";
        String dbUser = "root";
        String dbPasswd = "Dojo1@1234";
        try{
        Class.forName(driver);
        con = DriverManager.getConnection(url+db, dbUser, dbPasswd);
        }
        catch (Exception e){
	          e.printStackTrace();
	          }
     }
    catch(Exception e)
    {
    	System.err.print(e);
    }

    }  
    
    public String getAuthToken()
    {
		 RestAssured.baseURI = "https://dojocentral.online";

	     JSONObject request = new JSONObject();
	     
	     request.put("username","leena.patil@buzzybrains.com");

	     request.put("password","123456");
	        
		    
		 Response code =
		 
	                given()
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			.when()
			.post("/api/v1/authenticate");
		     		    
		    String jsonstring = code.getBody().asString();
		    
		    String generatedToken = JsonPath.from(jsonstring).get("accessToken");
		    
			return generatedToken;
    }
    
//    @Test
    public void get_logs(){
    	    NewDBConnection dbmanager =new NewDBConnection();
            dbmanager.go();
            given().given().queryParam("username" ,"leena.patil@buzzybrains.com")
	           .queryParam("password","123456").when().post("https://dojocentral.online/api/v1/authenticate").then().log().all();
//	        System.out.println("Execution Successful");
    }
    
//    @Test
    public void checkStatus(){
//    	NewDBConnection db = new NewDBConnection();
//    	db.go();
    	String url = "https://dojocentral.online/api/v1/authenticate";
    	given().queryParam("username" ,"leena.patil@buzzybrains.com").
    	      queryParam("password","123456").
    	      when().get("https://dojocentral.online/api/v1/authenticate").
    	      then().log().body();
//    	int statusCode= given().
//    	           auth().
//    	            oauth2("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDg4MTkxNTEsImlhdCI6MTYwNzUyMzE1MX0.9wIZkHnXPYx2y6EOXLOri8kRRJqIQnpXf9C9g2H5vcEnmGpZfuhypIt4IQwcg2JbBsv9fZYbwgpQ1AajvKaCcA")
//    	           .when().get("https://dojocentral.online").getStatusCode();
//    	   System.out.println("The response status is "+statusCode);

//    	   ValidatableResponse Body = given().queryParam("username" ,"leena.patil@buzzybrains.com")
//           .queryParam("password","123456").when().post(url).then().log().body();
//    	   System.out.print(Body.toString());
//    	   Body.toString();
    	
    }
    
//    @Test
    public static void getResponseBody()
    {
    	 
    	given().queryParam("username" ,"leena.patil@buzzybrains.com").
	      queryParam("password","123456").
	      when().get("https://dojocentral.online/api/v1/dojo/member/71").
	      then().log().body();
    }
    
//    @Test
    public static void getResponseHeaders()
    {
    	   System.out.println("The headers in the response "+
    	   get("https://dojocentral.online/").then().extract()
    	   .headers());
    }
    
//    @Test
    public static void getResponseTime(){
    	  System.out.println("The time taken to fetch the response "+get("https://dojocentral.online/")
    	         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
    	}
    
//    @Test
    public static void getResponseContentType()
    {
    	System.out.println("The content type of response "+
        get("https://dojocentral.online/").then().extract()
    	.contentType());
    	}

    
    
//    @Test
    public void getAuthenticationUsingToken() {
            
        given().
            auth().
            oauth2("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDg4MTkxNTEsImlhdCI6MTYwNzUyMzE1MX0.9wIZkHnXPYx2y6EOXLOri8kRRJqIQnpXf9C9g2H5vcEnmGpZfuhypIt4IQwcg2JbBsv9fZYbwgpQ1AajvKaCcA").
        when().
            get("https://dojocentral.online/").
        then().
            assertThat().
            statusCode(200);
    }
    @Test
    public void getAuthenticationUsingUsernameAndPassword() {
            
        given().
            auth().
            preemptive().
            basic("leena.patil@buzzybrains.com", "123456").
        when().
            get("https://dojocentral.online/").
        then().
            assertThat().
            statusCode(200);
       
    }
    
//    @AfterTest
	public void tearDown() throws Exception 
    {
	// Close DB connection
	if (con != null) {
	con.close();
	}
    }
}
