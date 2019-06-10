import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class TestGETReqRest {

	@Test
	public static void GetResponse() {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:3000";
		
		Response response = given().
		param("","").
		when().
		get("/TrainingSession").
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		System.out.println(response.asString());
		
	}

}
