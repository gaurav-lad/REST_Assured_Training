import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class TestDELETEReqRest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:3000";
		given().
		param("","").
		when().
		delete("/TrainingSession/3").
		then().
		assertThat().statusCode(200);
	}

}
