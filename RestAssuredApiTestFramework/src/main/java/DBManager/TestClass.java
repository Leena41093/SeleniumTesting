package DBManager;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

public class TestClass extends DBManager {
   
	NewDBConnection db;
//	@Test
	public void testBasicAuthentication()
	{
//		db.go();
        JSONObject request = new JSONObject();
        
//	    request.put("competitionEventDate", "1608960600");
//	    request.put("dojoId", """71");
//	    request.put("eventType", "GRADING");
//	    request.put("name", "Grade 1233");
        request.put("username","leena.patil@buzzybrains.com");

        request.put("password","123456");
        
	     RestAssured.baseURI = "https://dojocentral.online";
//	     RestAssured.
	    
		Response code =
				given()
//				auth()
//				.oauth2("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDk1MDAzMzMsImlhdCI6MTYwODIwNDMzM30.4nCZz1HJYxco2d7qucuCw2CfbMvDhF1vDkCJAHHUv5gQBaK1Enfrv7XD6pzdwOLUZFMlK2w5q_RHIgeHuzlySg; refreshToken=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MTA3OTYzMzMsImlhdCI6MTYwODIwNDMzM30.TSonCS5AvMomdBBTN6xKD17ZQw-XzWsqHffdSbJSOEx26O00F_jdYGcWIOX3hV1Jnv4SOa9ikYPUkVG1D8QdNQ")
////                .preemptive()
//                .basic("leena.patil@buzzybrains.com", "123456")
                .header("Accept", ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Content-Type","application/json")
				.body(request.toJSONString())
		.when()
		.post("/api/v1/authenticate");
		System.out.println("Status Code "+code.asPrettyString());
//		System.out.println("")
	}
	
//	@Test
	public void testPostMethod()
	{
		db.go();
		
		given().
		auth()
		.oauth2("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDk0NzQ0MzgsImlhdCI6MTYwODE3ODQzOH0.wZxXvz9u9htKgcoWVgVycpJpakc2on6YAk4vo8WcI69YHyFvtZrtrcVk6TnYo7ILjkY5_TBu8tAQTndWbDa2vw")
//        .preemptive()
//        .basic("leena.patil@buzzybrains.com", "123456")
        .when().get("https://dojocentral.online/api/v1/dojo/member/71").
	      then().log().body();
	
	}
	
	@Test
	public void BearerTokedValidation()
	{
		 db.go();
	     RestAssured.baseURI = "https://dojocentral.online";
	     JSONObject request = new JSONObject();
	     request.put("username","leena.patil@buzzybrains.com");

	     request.put("password","123456");
	        
		 RestAssured.baseURI = "https://dojocentral.online";
		    
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
		    
		   
		    
		    JSONObject req = new JSONObject();
		    req.put("competitionEventDate", "1608960600");
		    req.put("dojoId", "71");
		    req.put("eventType", "GRADING");
		    req.put("name", "Grade 1233");
		    
		  Response response=  given().header("Authorization","Bearer "+generatedToken)
				  .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(req.toJSONString())
		    .when()
		    .post("/api/v1/dojo/competition");
		  response.prettyPrint();

	}
}
