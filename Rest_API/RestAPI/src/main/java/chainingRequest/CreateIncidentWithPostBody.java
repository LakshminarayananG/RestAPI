package chainingRequest;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CreateIncidentWithPostBody extends BaseRequest {

	@Test
	public void createIncidentWithBody() {
		
		String requestbody = "{\"short_description\":\"Created from rest\",\"category\":\"Hardware\"}";
		
		Response response = request
				.queryParam("sysparm_fields", "sys_id,number,category")
				.body(requestbody)
				.accept(ContentType.XML).post();

		// Validate Response Code
		System.out.println(response.getStatusCode());

		// Validate content type
		System.out.println(response.contentType());

		// Print the response
		response.prettyPrint();

		// Get Specific detail from the XML
		XmlPath xmlPath = response.xmlPath();
		System.out.println(xmlPath.get("response.result.sys_id"));
		sys_id = xmlPath.get("response.result.sys_id");
	}

}
