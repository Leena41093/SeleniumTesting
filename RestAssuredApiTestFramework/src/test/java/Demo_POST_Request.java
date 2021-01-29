import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Demo_POST_Request {

	@Test
	public void register()
	{
		RestAssured.baseURI = "https://reqres.in/api";
		   
		   //request Object
		   RequestSpecification httpRequest = RestAssured.given();
		   
		   //response object
		   
		   JSONObject requestParams = new JSONObject();
		   
		   requestParams.put("email","eve.holt@reqres.in");
           requestParams.put("password", "pistol");
           
           httpRequest.header("Content-Type","application/json");
           
           httpRequest.body(requestParams.toJSONString());
		   
		   Response response = httpRequest.request(Method.POST,"/register");
		   
		   //print response
		   String responseBody = response.getBody().asString();
		   
		   int statusCode = response.getStatusCode();
		   System.out.println(statusCode);
			  
		   Assert.assertEquals(statusCode, 200);
			  
		   //statusLine verification
		   String statusLine = response.getStatusLine();
		   
		   String successCode = response.jsonPath().get("SuccessCode");
		   
		   Assert.assertEquals(successCode, "");
		   
		   System.out.println(statusLine);
			  
		   Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		   
	}
}
