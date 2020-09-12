package basics;

import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JiraGetIssues {
	
	@Test
	public void getIssues() {
		
		//Declaring the hashmap and passing the query params to the request as a hashmap
		HashMap<String,String> querypar=new HashMap<String,String>();
		querypar.put("jql", "project=LAK");
		querypar.put("jql", "issuetype=story");
	
		//Step 1: Get the endpoint URI
		RestAssured.baseURI="https://laks7.atlassian.net/rest/api/2/search";
		
		//Step 2: Authorization
		RestAssured.authentication=RestAssured.preemptive().basic("username", "APIKey");
		
		//Ste 3: Set Request Type
		Response response=RestAssured
				/*
				 * .given().queryParam("sysparm_limit", "5")
				 * .given().queryParam("sysparm_fields",
				 * "number,sys_id,short_description,type,state")
				 */
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
