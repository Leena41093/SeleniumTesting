package DBManager;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class MemberOperations {
    NewDBConnection db ;
    
    @Test
	public void createMember(){
		db.go();
		//Rest API's URL
		 String restAPIUrl = "https://dojocentral.online/api/v1/dojo/member";
		 
		 Map<String,Object> member = new HashMap<String, Object>();
		 member.put("address","Seoul");
			 member.put("date", "1608178408");
			 member.put("dojoId", "72");
			 member.put("email", "leena.patil+25@buzzybrains.com");
			 member.put("emergencyContactNumber", "");
			 member.put("emergencyContactPerson", "");
			 member.put("gender","female");
			 member.put("martialArtId", "25");
			 member.put("medicalCondition","");
			 member.put("memberName", "Mdec7");
			 member.put("password","123456");
			 member.put("phone", "+61 872 827 828");
			 member.put("postCode","411057");
			 member.put("primaryBeltLevelId", "143");
			 member.put("secMartialArtId", "27");
			 member.put("signupDate", "1608178408");
			 member.put("slotCount", "4");
			 member.put("state","Queensland");
			 member.put("subscriptionAmount", "3");
			 member.put("subscriptionMonths","-2");
			 member.put("username","Mdec7");
//			member.put("dojoId", "124");
//			member.put("memberName", "Leena Patil");
//			member.put("username", "Leena P");
//			member.put("subscriptionAmount", "4");
//			member.put("subscriptionMonths", "-2");
//			member.put("atheleteId", "1");
//			member.put("primaryBeltLevelId", "1");
//			member.put("secondaryBeltLevelId", "2");
//			member.put("date", "1607922129");
//			member.put("gender", "Male");
//			member.put("address", "WAKAD");
//			member.put("state", "MH");
//			member.put("postCode", "411057");
//			member.put("email", "leena.patil@buzzybrains.com");
//			member.put("phone", "8830634709");
//			member.put("signupDate", "1607922129");
//			member.put("birthday", "749710929");
//			member.put("emergencyContactPerson", "M patil");
//			member.put("emergencyContactNumber", "6283616616");
//			member.put("medicalCondition", "None");
//			member.put("password", "123456");
//			member.put("secMartialArtId", "1");
//			member.put("slotCount", "5");

			Response response = 
//					given().contentType(ContentType.JSON)
////			.accept("application/json")
//			.with()		
//			.auth()
//        .oauth2("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDkyMTc5MDQsImlhdCI6MTYwNzkyMTkwNH0.5TN2l6_ajxXnjVhX_zJrTWwYzLNOH1GmjGm-NDWkrcMQvNHvgFNKDd_nHkw3ToY0iK6fTw9akOdUduwpvF2-Cg").
		     given().auth().preemptive().basic("leena.patil@buzzybrains.com", "123456")
			.body(member)
			.when().post("https://dojocentral.online/api/v1/dojo/member").then()
			.statusCode(200).extract().response();
			
			
//			.body("empty",equalTo(false));
//			.body("position",lessThan(150));
//		 //API Body
//		 String apiBody = "{   \"dojoId\":\"124\",\"memberName\":\"Leena Patil\","
//		 		+ "\"username\":\"Leena P\",\"subscriptionAmount\":\"4\","
//		 		+ "\"subscriptionMonths\":\"-2\","
//		 		+ "\"atheleteId\":\"1\","
//                + "\"primaryBeltLevelId\": \"1\","
//                + "\"secondaryBeltLevelId\": \"2\","
//                +"\"date\": \"1593052731,"
//                +"\"gender\": \"Male\","
//                + "\"address\":\"WAKAD\","
//                +"\"state\":\"MH\","
//                + "\"postCode\": \"411057\","
//                + "\"email\": \"leena.patil@buzzybrains.com\","
//                + "\"phone\": \"8830634709\","
//                + "\"signupDate\": \"1607922129\","
//                + "\"birthday\": \"749710929\","
//                + "\"emergencyContactPerson\": \"M patil\","
//                + "\"emergencyContactNumber\": \"6283616616\","
//                + "\"medicalCondition\": \"None\","
//                + "\"password\": \"123456\","
//                + "\"secMartialArtId\": \"1\","
//                + "\"slotCount\":\"\"}";
//		 
//		 // Building request by using requestSpecBuilder
//		 RequestSpecBuilder builder = new RequestSpecBuilder();
//		 
//		 //Set API's Body
//		 builder.setBody(apiBody);
//		 
//		 //Setting content type as application/json
//		 builder.setContentType("application/json; charset=UTF-8");
//		 
//		 RequestSpecification requestSpec = builder.build();
//		 
//		 //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
//		 Response response = given().auth().
//				 preemptive().basic("leena.patil@buzzybrains.com","123456").
////		            oauth2("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZWVuYS5wYXRpbEBidXp6eWJyYWlucy5jb20iLCJleHAiOjE2MDkyMTc5MDQsImlhdCI6MTYwNzkyMTkwNH0.5TN2l6_ajxXnjVhX_zJrTWwYzLNOH1GmjGm-NDWkrcMQvNHvgFNKDd_nHkw3ToY0iK6fTw9akOdUduwpvF2-Cg").
//		 spec(requestSpec).when().post(restAPIUrl);
//		 
//		 String body = response.getBody().asString();
//	     System.out.println(body);
//	     
//	     https://dojocentral.online/api/v1/dojo/analytics/members/71
////		 JSONObject JSONResponseBody = new JSONObject(response.body().asString());
////		 
////		 //Get the desired value of a parameter
////		 String result = JSONResponseBody).getString("");
////		 
//		 //Check the Result
////		 Assert.assertEquals(result, "{expectedValue}");
		
	}
}
