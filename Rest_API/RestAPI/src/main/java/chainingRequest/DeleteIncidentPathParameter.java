package chainingRequest;

import org.testng.annotations.Test;
import io.restassured.response.Response;

public class DeleteIncidentPathParameter extends BaseRequest {

	// Path of the method --> Packagename.classname.methodname

	@Test(dependsOnMethods = "chainingRequest.CreateIncidentWithPostBody.createIncidentWithBody")
	public void deleteIncident() {
		
		Response response = request
				.pathParam("sys_id", sys_id)
				.delete("{sys_id}");

		// Validate Response Code
		System.out.println(response.getStatusCode());

		// Validate content type
		System.out.println(response.contentType());

		// Print the response
		response.prettyPrint();

	}

}
