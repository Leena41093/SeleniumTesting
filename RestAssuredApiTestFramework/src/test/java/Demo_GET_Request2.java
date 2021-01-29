import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;


//import io.restassured.http.Method;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import junit.framework.Assert;

public class Demo_GET_Request2 {

	
	@Test
	   void Test01()
	   {
		  given()
		  .when()
		  .get("https://reqres.in/api/unknown")
		  .then()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .body("data[0].name",equalTo("cerulean"))
		  .and()
		  .header("Content-Type", equalTo("application/json"));
		  
//		  log().all();
		 //specify base URI
//		   RestAssured.baseURI = "https://reqres.in/api/users";
		   
		   //request Object
//		   RequestSpecification httpRequest = RestAssured.given();
		   
		   //response object
		   
//		   Response response = httpRequest.request(Method.GET,"/2");
		   
		   //print response
//		   String responseBody = response.getBody().asString();
//		   
//		   System.out.println(responseBody);
//		   
//		   //status code validation
//		   
//		  int statusCode = response.getStatusCode();
//		  System.out.println(statusCode);
//		  
//		  Assert.assertEquals(statusCode, 200);
//		  
//		  //statusLine verification
//		  String statusLine = response.getStatusLine();
//		  
//		  System.out.println(statusLine);
//		  
//		  Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	   }
}
