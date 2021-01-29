import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class Demo_GET_Request 
{
   @Test
   void get_list_of_users()
   {
	 //specify base URI
	   RestAssured.baseURI = "https://reqres.in/api/users";
	   
	   //request Object
	   RequestSpecification httpRequest = RestAssured.given();
	   
	   //response object
	   
	   Response response = httpRequest.request(Method.GET,"/2");
	   
	   //print response
	   String responseBody = response.getBody().asString();
	   
	   System.out.println(responseBody);
	   
	   //status code validation
	   
	  int statusCode = response.getStatusCode();
	  System.out.println(statusCode);
	  
	  Assert.assertEquals(statusCode, 200);
	  
	  //statusLine verification
	  String statusLine = response.getStatusLine();
	  
	  System.out.println(statusLine);
	  
	  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
   }
}
