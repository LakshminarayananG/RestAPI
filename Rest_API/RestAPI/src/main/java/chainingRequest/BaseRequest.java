package chainingRequest;

import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	
	static RequestSpecification request;
	static String sys_id;

	@BeforeSuite
	public void initialize() {
		
		// Step 1: Get the endpoint URI
		RestAssured.baseURI = "https://dev90550.service-now.com/api/now/table/incident";

		// Step 2: Authorization
		RestAssured.authentication = 
				RestAssured.oauth2("OAuth Token");

		// Step 3: Construct the request
		request = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON);

	}

}
