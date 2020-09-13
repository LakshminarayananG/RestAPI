package basics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIncident {

	
	
	@Test
	public void createIncidentWithoutBody() {
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
		RestAssured.authentication = RestAssured.oauth2(authtoken);

		// Step 3: Construct the request
		Response response = RestAssured
				.given()
				//.contentType("application/json")
				.contentType(ContentType.JSON)
				.post();

		// Step 4: Validate Response Code
		System.out.println(response.getStatusCode());

		// Step 5: Validate content type
		System.out.println(response.contentType());

		// Step 6: Print the response
		//response.prettyPrint();
		
		
		//Step 7: Convert the response object to json for us to parse
		JsonPath jsonpath=response.jsonPath();
		
		//Get sysid from the response
		String sys_id=jsonpath.get("result.sys_id");
		System.out.println(sys_id);
		
	}

}
