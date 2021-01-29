package DBManager;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GradingManagement {

	NewDBConnection db ;


    @BeforeClass
    public void setup() {

        RestAssured.baseURI = "https://dojocentral.online";

//        RestAssured.basePath = "/api/v1/dojo/member";

//        RestAssured.authentication =    RestAssured.basic("leena.patil@buzzybrains.com", "123456");
        
        
        db.go();
    }  
	 @Test
	    public void createUser() {
	        Response response = given()
	                .auth()
	                .preemptive()
	                .basic("leena.patil@buzzybrains.com", "123456")
	                .header("Accept", ContentType.JSON.getAcceptHeader())
	                .contentType(ContentType.JSON)
	                .body("{ \"competitionEventDate\":1608960600\"\",\n"
	                		+ "\"dojoId\":\"71\",\n"
	        		 		+ "\"eventType\":\"GRADING\",\n"
	                		+"\name\":\"Grade 12\"}")
	                .post("/gradings")
	                .then().extract().response();
         System.out.println(response.statusCode());
         System.out.println(response.asString());
//	        Assert.assertEquals(2000, response.getStatusCode());
}
}
