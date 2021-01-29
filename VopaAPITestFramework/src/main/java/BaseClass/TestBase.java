package BaseClass;
import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestBase {

	static int lport;
    static String rhost;
    static int rport;
	static Connection con = null;


    public static void go(){
    String user = "dev-ops";
    String password = "Vopa!ops@250620";
    String host = "3.6.252.240";
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
        String db = "vopa_test";
        String dbUser = "root";
        String dbPasswd = "root";
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
    
    @Test
    public void getAuthToken()
    {
//		 RestAssured.baseURI = "http://api.vopa.in";

	     JSONObject request = new JSONObject();
	     
	     request.put("mobile","8390977193");

	     request.put("user_type","SUPERADMIN");
	        
		    
		 Response code =
		 
	                given()
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			.when()
			.post("/vopa/api/mobileSignupLogin/");
		     		    
		    String jsonstring = code.getBody().asString();
		    
		    System.out.println(jsonstring);
		    
		    
		    
		    String otp = JsonPath.from(jsonstring).getString("otp");
		    
		    
		    System.out.println(otp+"  OTP");
		    JSONObject req = new JSONObject();
		    
		     req.put("mobile","8390977193");

		     req.put("otp","\""+otp+"\"");
//		    
		     Response TokenResponse = 
		    		 given()
		                .header("Accept", ContentType.JSON.getAcceptHeader())
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.header("Content-Type","application/json")
						.body(req.toJSONString())
				.when()
				.post("/vopa/api/verfiyOTP/");
		     
		     String actData = TokenResponse.getBody().asPrettyString();
		     
		     System.out.println(actData);
		     
//			return generatedToken;
    }
//  @AfterTest
	public void tearDown() throws Exception 
  {
	// Close DB connection
	if (con != null) {
	con.close();
	}
  }
}
