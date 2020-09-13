package chainingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	
	static RequestSpecification request;
	static String sys_id;

	@BeforeSuite
	public void initialize() {
		
		Properties prop = new Properties();

		try {
		    prop.load(new FileInputStream("./Properties/Token.properties"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String authtoken=prop.getProperty("OAuth2_Token");
		
		// Step 1: Get the endpoint URI
		RestAssured.baseURI = "https://dev90550.service-now.com/api/now/table/incident";

		// Step 2: Authorization
		RestAssured.authentication = 
				RestAssured.oauth2(authtoken);

		// Step 3: Construct the request
		request = RestAssured
				.given()
				.log()
				.all()
				.contentType(ContentType.JSON);

	}

}
