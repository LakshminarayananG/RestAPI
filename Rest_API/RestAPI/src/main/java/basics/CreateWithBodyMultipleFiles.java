package basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CreateWithBodyMultipleFiles {
	
	@DataProvider(name="Data", parallel=true, indices = {0,1})
	private String[] testData() {
		String[] filename=new String[2];
		filename[0]="./Data/FirstFile.json";
		filename[1]="./Data/SecondFile.json";
		return filename;
	}

	@Test(dataProvider = "Data")
	public void createIncidentWithBody(String data) {
		Properties prop = new Properties();

		try {
		    prop.load(new FileInputStream("./Properties/Token.properties"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String authtoken=prop.getProperty("OAuth2_Token");
		
		
		
		
		File file = new File(data);

		// Step 1: Get the endpoint URI
		RestAssured.baseURI = "https://dev90550.service-now.com/api/now/table/incident";

		// Step 2: Authorization
		RestAssured.authentication = RestAssured.oauth2(authtoken);

		// Step 3: Construct the request
		Response response = RestAssured
				.given()
				//.contentType("application/json")
				.contentType(ContentType.JSON)				
				.when()
				.log()
				.all()
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
		System.out.println(xmlPath.get("response.result.sys_id"));
	}

}
