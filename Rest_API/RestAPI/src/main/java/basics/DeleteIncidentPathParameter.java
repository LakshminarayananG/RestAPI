package basics;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteIncidentPathParameter {
	
	

	@Test
	public void deleteIncident() {
		

		// Step 1: Get the endpoint URI
		RestAssured.baseURI = "https://dev90550.service-now.com/api/now/table/incident";

		// Step 2: Authorization
		RestAssured.authentication = RestAssured.oauth2("OAuth Token");

		// Step 3: Construct the request
		Response response = RestAssured
				.given()
				//.contentType("application/json")
				.contentType(ContentType.JSON)	
				.pathParam("sys_id", "4297e7901b93101081dd7596cc4bcb81")
				.when()
				.log()
				.all()
				.delete("{sys_id}");

		// Step 4: Validate Response Code
		System.out.println(response.getStatusCode());

		// Step 5: Validate content type
		System.out.println(response.contentType());

		// Step 6: Print the response
		response.prettyPrint();

		
	}

}