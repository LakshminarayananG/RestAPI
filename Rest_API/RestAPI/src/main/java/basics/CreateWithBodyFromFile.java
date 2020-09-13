package basics;

import java.io.File;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CreateWithBodyFromFile {

	@Test
	public void createIncidentWithBody() {
		File file = new File("./Data/CreateIncidentData.json");

		// Step 1: Get the endpoint URI
		RestAssured.baseURI = "https://dev90550.service-now.com/api/now/table/incident";

		// Step 2: Authorization
		RestAssured.authentication = RestAssured.oauth2("OAuth Token");

		// Step 3: Construct the request
		Response response = RestAssured
				.given()
				//.contentType("application/json")
				.contentType(ContentType.JSON)				
				.when()
				.body(file)
				.accept(ContentType.XML)
				.post();

		// Step 4: Validate Response Code
		System.out.println(response.getStatusCode());

		// Step 5: Validate content type
		System.out.println(response.contentType());

		// Step 6: Print the response
		response.prettyPrint();

		//Get Specific detail from the XML
		XmlPath xmlPath = response.xmlPath();
		System.out.println(xmlPath.get("response.result.number"));
	}

}
