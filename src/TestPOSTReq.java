import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestPOSTReq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:3000";
		
		RequestSpecification request = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", 3);
		requestParams.put("title", "Training3");
		requestParams.put("author", "Training3");

		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		Response response = request.post("/posts");
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 201);
		System.out.println(response.body().asString());
		
	}

}
