import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Payload;
import files.Resources;

public class TestPOSTDELGoogle {


	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Gaurav\\eclipse-workspace\\TrainingGETReq\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addDelPlace() {
		// TODO Auto-generated method stub

		RestAssured.baseURI = prop.getProperty("HOST");
		
		//POST: Adding address/location and getting response, and unique place_id
		
		Response response = given().
		queryParam("key", prop.getProperty("KEY")).
		body(Payload.getPostData()).
		when().
		post(Resources.placePostData()).
		then().assertThat().statusCode(200).
		extract().response();
		String responseString = response.asString();
		System.out.println(responseString);
		
		//Extracting place ID to be used in 
		JsonPath jsonPath = new JsonPath(responseString);
		String placeId = jsonPath.getString("place_id");
		System.out.println("Place ID is: " + placeId);
		
		
		//Delete Place_id
		Response delResponse = given().
		queryParam("key", prop.getProperty("KEY")).
		body("{" + 
				"	\"place_id\": \""+placeId+"\"" + 
				"}").
		when().
		post(Resources.placeDeleteData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		System.out.println("Delete response: "+ delResponse.asString());
	}
	
//	@Test
//	public void GetGoogle() {
//		RestAssured.baseURI = "http://216.10.245.166";
//		
//		Response response = given().
//		param("location", "-33.8670522,151.1957362").
//		param("radius", "1500").
//		param("key", "AIzaSyDhWe0rDiBsdYcQgU5AI5ffdOXN3sgQ_4Q").
//		when().
//		get("/maps/api/place/nearbysearch/json").
//		then().assertThat().statusCode(200).contentType(ContentType.JSON).
//		body("results[0].scope", equalTo("GOOGLE")).
//		extract().response();
//		System.out.println("Response: " +response.asString());
//	}

}
