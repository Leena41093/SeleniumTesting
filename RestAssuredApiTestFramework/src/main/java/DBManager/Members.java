package DBManager;

	
	import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;
	import io.restassured.RestAssured;
    import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

	public class Members {

        NewDBConnection db ;


	    @BeforeClass
	    public void setup() {

	        RestAssured.baseURI = "https://dojocentral.online";  
	        db.go();

	    }


//	    @Test
	    public void createUser() {
	        Response response = given()
	                .auth()
	                .preemptive()
	                .basic("leena.patil@buzzybrains.com", "123456")
	                .header("Accept", ContentType.JSON.getAcceptHeader())
	                .contentType(ContentType.JSON)
	                .body("{   \"dojoId\":\"79\",\"memberName\":\"Leena Patil\",\n"
	        		 		+ "\"username\":\"Leena P\",\"subscriptionAmount\":\"3\",\n"
	        		 		+ "\"subscriptionMonths\":\"-2\",\n"
	        		 		+ "\"atheleteId\":\"1\",\n"
	                        + "\"primaryBeltLevelId\": \"1\",\n"
	                        + "\"secondaryBeltLevelId\": \"2\",\n"
	                        +"\"date\": \"1593052731,\n"
	                        +"\"gender\": \"Male\",\n"
	                        + "\"address\":\"WAKAD\",\n"
	                        +"\"state\":\"MH\",\n"
	                        + "\"postCode\": \"411057\",\n"
	                        + "\"email\": \"leena.patil@buzzybrains.com\",\n"
	                        + "\"phone\": \"8830634709\",\n"
	                        + "\"signupDate\": \"1607922129\",\n"
	                        + "\"birthday\": \"749710929\","
	                        + "\"emergencyContactPerson\": \"M patil\",\n"
	                        + "\"emergencyContactNumber\": \"6283616616\",\n"
	                        + "\"medicalCondition\": \"None\",\n"
	                        + "\"password\": \"123456\",\n"
	                        + "\"secMartialArtId\": \"1\",\n"
	                        + "\"slotCount\":\"\"}")
	                .post("/api/v1/dojo/member")
	                .then().extract().response();
            System.out.println(response.statusCode());
            System.out.println(response.asString());
//	        Assert.assertEquals(2000, response.getStatusCode());
	    }
	    @Test
	    public void responseBody() throws Exception
	    {

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
			    System.out.println(generatedToken);
			    
	    	 JSONObject member = new JSONObject();
	    	 
	    	 member.put("address","Seoul");
	    	 
			 member.put("date", "1608284275");
			 
			 member.put("dojoId", "71");
			 
			 member.put("email", "leena.patil+25@buzzybrains.com");
			 
			 member.put("emergencyContactNumber", "");
			 
			 member.put("emergencyContactPerson", "");
			 
			 member.put("gender","female");
			 
			 member.put("martialArtId", "25");
			 
			 member.put("medicalCondition","");
			 
			 member.put("memberName", "Mdec11");
			 
			 member.put("password","123456");
			 
			 member.put("phone", "+61 872 827 828");
			 
			 member.put("postCode","411057");
			 
			 member.put("primaryBeltLevelId", "143");
			 
			 member.put("secMartialArtId", "27");
			 
			 member.put("signupDate", "1608284275");
			 
			 member.put("slotCount", "4");
			 
			 member.put("state","Queensland");
			 
			 member.put("subscriptionAmount", "3");
			 
			 member.put("subscriptionMonths","-2");
			 
			 member.put("username","Mdec11");
	    	 
		

	    	 Response response = given()
	    		    	.header("Authorization","Bearer "+generatedToken)
					    .header("Accept", ContentType.JSON.getAcceptHeader())
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.header("Content-Type","application/json")
						.body(member.toJSONString())
						.when()
						.post("/api/v1/dojo/member");
	    	 
	    	 response.asPrettyString();
	    	 
	    	 int statusCode = response.getStatusCode();
	    	 
	    	 Assert.assertEquals(statusCode, 200);

	    }

}
