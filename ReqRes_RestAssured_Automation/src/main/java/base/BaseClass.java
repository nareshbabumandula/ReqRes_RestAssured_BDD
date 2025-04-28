package base;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	protected static RequestSpecification request;
	protected static Response response;
	protected static final String BASE_URL = "https://reqres.in";
	
	public void setupRequest() {
		request = given()
				.baseUri(BASE_URL)
				.contentType("application/json")
				.accept("application/json")
				.header("x-api-key", "reqres-free-v1");
	}
}
