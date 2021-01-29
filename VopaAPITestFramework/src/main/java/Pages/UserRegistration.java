package Pages;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserRegistration {

	@Test
    public void getAuthToken()
    {
		 RestAssured.baseURI = "http://api.vopa.in:8080";

	     JSONObject request = new JSONObject();
	     
	     request.put("mobile","8390977193");

	     request.put("user_type","ADMIN");
	        
		    
		 Response code =
		 
	                given()
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			.when()
			.post("/vopa/api/mobileSignupLogin/");
		     		    
		    String otp = code.getBody().jsonPath().getString("otp");
		    
		    System.out.println(otp);
//		    
//		    
//		    
//		    String otp = JsonPath.from(jsonstring).getString("otp");
		    
//		 String otp = JsonPath.from(jsonstring).getJsonObject("user_id");
		    
		    System.out.println(otp+"  OTP");
		    JSONObject req = new JSONObject();
		    
		     req.put("mobile","8390977193");

		     req.put("otp","\""+otp+"\"");
		     
		     req.put("user_type", "ADMIN");
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
}
}
