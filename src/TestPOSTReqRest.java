import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TestPOSTReqRest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:3000";
		
		Response response = given().
		body("{"
				+ "\"id\": 3, "
				+ "\"title\": \"Training3\","
				+ "\"author\": \"Training3\""
			+ "}").request().header("Content-Type", "application/json").
		when().
		post("/TrainingSession").
		then().
		assertThat().statusCode(201).
		extract().response();
		
		System.out.println("response: " + response.asString());
	}
}
