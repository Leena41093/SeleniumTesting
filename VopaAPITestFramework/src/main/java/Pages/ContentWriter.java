package Pages;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

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

public class ContentWriter 
{
	TestUtils testUtil;

	String sheetName = "/home/buzzybrains/my_workspace/VopaAPITestFramework/src/main/java/TestData/testData.xlsx";
	String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJleHAiOjE2MTIwMDk3NDEsImlhdCI6MTYwOTQxNzc0MX0.VcrWfqOueA7zCtFeaAhdMsG7zwS9Xk3zXCVO1t5sEbM";   
	@DataProvider
	public Object[][] getSheetData(){
		
		Object data[][] = null;
		try {
			
			data = TestUtils.readExcelFile("Usersss");
			
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

	
	@Test(priority = 1,dataProvider="getSheetData")
	public void addContentwriter(String first_name,String middle_name,String last_name,String mobile,String email,String user_type) throws Exception
    {
//        ExcelUtils eat = new ExcelUtils(sheetName);


	     JSONObject request = new JSONObject();
	     
	     request.put("first_name",first_name);

	     request.put("middle_name",middle_name);
	     
	     request.put("last_name", last_name);
	     
	     request.put("mobile",mobile);
	     
	     request.put("email",email );
	     
	     request.put("user_type", user_type);
	        
		 Response code =
		             given()
	                .header("Authorization",token)
	                .header("Accept", ContentType.JSON.getAcceptHeader())
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Content-Type","application/json")
					.body(request.toJSONString())
			.when()
			.post("/vopa/api/addAdminContentWriter/");
//			.then().contentType(ContentType.JSON);
		
//		 JsonPath jsn = code.jsonPath();
		
//		 String dt = jsn.get("user_id");
		 
		    String jsonstring = code.getBody().asString();
//		    JsonPath jsonPath = new JsonPath(jsonstring);
//			 String user_id = jsonPath.get("user_id");
//		    ArrayList<Map<String,?>> jsonAsArrayList = JsonPath.from(jsonstring).get("");

		    
//		   String cd = JsonPath.from(jsonstring).get("user_id");
		    
		   System.out.print(jsonstring);

      }
	
//	@Test
	public void viewAdminList(){
		
		Response data = given()
				.header("Authorization",token)
				.header("Accept",ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Content-Type","application/json")
				.get("/vopa/api/adminList/");
		
		String dt = data.asPrettyString();
		
		System.out.println(dt);
	}
	
//	@Test
	public void sendSms(){
		
	    JSONObject request = new JSONObject();
	    
	    request.put("user_id", "19");
         
		Response data = given()
				.header("Authorization",token)
				.header("Accept",ContentType.JSON.getAcceptHeader())
				.contentType(ContentType.JSON)
				.header("Content-Type","application/json")
				.body(request.toJSONString())
				.when()
				.post("/vopa/api/sendWelcomeMsg/");
		
		String dta = data.asPrettyString();
	}
}
