import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class GetGoogle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response response = given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "1500").
		param("key", "AIzaSyDhWe0rDiBsdYcQgU5AI5ffdOXN3sgQ_4Q").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).contentType(ContentType.JSON).
		body("results[0].scope", equalTo("GOOGLE")).
		extract().response();
		System.out.println("Response: " +response.asString());
	}

}
