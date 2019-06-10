import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class TestGETReq {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// URL which need to hit
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/posts/1");
		System.out.println("1: "+ response.getHeader("Content-Type"));
		System.out.println();
		String contentType = response.contentType();
		System.out.println("2: " + contentType);
		System.out.println();
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		System.out.println();
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		System.out.println("Time:" + response.getTimeIn(TimeUnit.MILLISECONDS) + "ms");
		System.out.println("Status Line: " +response.getStatusLine());
		
		//Assertion which is actual validating the response with expected content
		//and Assertion is what important part of automation testing
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(responseBody.contains("Training"), true);
		
		JsonPath jsonPathEval = response.jsonPath();
		String title = jsonPathEval.get("title");
		System.out.println("Title: " +title);
		Assert.assertEquals(title, "Training");
		
	}
}
