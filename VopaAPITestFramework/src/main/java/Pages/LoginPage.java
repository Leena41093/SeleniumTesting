package Pages;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utility.ExcelUtils;
import Utility.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginPage {

	TestUtils testUtil;

	String sheetName = "/home/buzzybrains/my_workspace/VopaAPITestFramework/src/main/java/Utility/ExcelUtils.java";
	String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJleHAiOjE2MTIwMDk3NDEsImlhdCI6MTYwOTQxNzc0MX0.VcrWfqOueA7zCtFeaAhdMsG7zwS9Xk3zXCVO1t5sEbM";   
	@DataProvider
	public Object[][] getSheetData(){
		
		Object data[][] = null;
		try {
			
			data = TestUtils.readExcelFile("Users");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		return data;
	}
	@BeforeClass
	public void setup() 
	{

		 RestAssured.baseURI = "http://api.vopa.in:8080";

	    }
	
//	@Test
	public void adminLogin()
	{
		 JSONObject request = new JSONObject();
	     
	     request.put("mobile","8390977193");

	     request.put("user_type","ADMIN");
	     
//	     request.put("last_name", last_name);
	     
	     Response response =given()
	                .header("Authorization",token)
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			        .when()
	    		    .post("/vopa/api/adminLogin/");
	     
		 String jsonstring = response.getBody().asString();
//		 String cd = JsonPath.from(jsonstring).get("user_id");
		 System.out.print(jsonstring);   
  
	}

 
	@Test
	public void getAdminWList()
	{
//		RestAssured.baseURI ="https://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization",token);

		JSONObject requestParams = new JSONObject();
		requestParams.put("page_number", "1"); // Cast
		requestParams.put("record_per_page", "3");
		requestParams.put("order_column", "created_at");
		requestParams.put("order_type", "1");	
		requestParams.put("user_type",  "ADMIN");
		requestParams.put("searchText","");

		request.body(requestParams.toJSONString());
		Response response = request.post("/vopa/api/adminCWdetailList/");

		int statusCode = response.getStatusCode();
		System.out.println("The status code received: " + statusCode);
		System.out.println("Response body: " + response.body().asString());
	}
	
//	@Test
	
	public void getAdminStatus()
	{
		 JSONObject request = new JSONObject();
	     
	     request.put("user_id","1");

	     request.put("status","INACTIVE");
	     
//	     request.put("last_name", last_name);
	     
	     Response response =given()
	                .header("Authorization",token)
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			        .when()
	    		    .put("/vopa/api/updateAdminCWStatus/");
	     
		 String jsonstring = response.getBody().asString();
//		 String cd = JsonPath.from(jsonstring).get("user_id");
		 System.out.print(jsonstring);   
	}
}
