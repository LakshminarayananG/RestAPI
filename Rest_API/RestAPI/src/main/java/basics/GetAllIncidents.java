package basics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllIncidents {
	
	@Test
	public void getAllIncidents() {
		Properties prop = new Properties();

		try {
		    prop.load(new FileInputStream("./Properties/Token.properties"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		String authtoken=prop.getProperty("OAuth2_Token");
		
		
		
		
		//Declaring the query params in a hashmap and passing the hashmap to the request
		HashMap<String,String> querypar=new HashMap<String,String>();
		querypar.put("sysparm_limit", "10");
		querypar.put("sysparm_fields", "number,sys_id,short_description,type,state");
	
		//Step 1: Get the endpoint URI
		RestAssured.baseURI="https://dev90550.service-now.com/api/now/table/incident";
		
		//Step 2: Authorization
		RestAssured.authentication=RestAssured.oauth2(authtoken);
		
		//Ste 3: Set Request Type
		Response response=RestAssured
				/*
				 * .given().queryParam("sysparm_limit", "5")
				 * .given().queryParam("sysparm_fields",
				 * "number,sys_id,short_description,type,state")
				 */
				
				//Can either be given as a hashmap or as stated above. use any of these
				.given().queryParams(querypar)
				.get();
		
		//Step 4: Validate Response Code
		System.out.println(response.getStatusCode());
		
		//Step 5: Validate content type
		System.out.println(response.contentType());
		
		//Step 6: Print the response
		response.prettyPrint();
	}

}
