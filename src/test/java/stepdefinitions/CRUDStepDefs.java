package stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

public class CRUDStepDefs extends BaseClass{

	@Given("I create a user with name {string} and job {string}")
	public void createUser(String name, String job) {
		String payload = String.format("{\"name\":\"%s\",\"job\":\"%s\"}", name, job);
		response = request
				.body(payload)
				.post("/api/users");
	}

	@Then("The response status code should be {int}")
	public void validateStatusCode(int expResponseCode) {
		int actualResponseCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		System.out.println("API Response body..!");
		System.out.println(responseBody);
		assertEquals("Expected status code " + expResponseCode + " but got " +actualResponseCode + "\nResponse Body: "
				+ responseBody, expResponseCode, actualResponseCode);
	}

	@Then("The response should contain name {string}")
	public void validateName(String name) {
		String actualName = response.jsonPath().getString("name");
		assertEquals(name, actualName);
	}

	@Given("I fetch the user list")
	public void getUserList() {
		response = request.get(BASE_URL + "/api/users?page=2");
		System.out.println("Response code is: " + response.getStatusCode());
	}

	@When("I fetch user with id {int}")
	public void i_fetch_user_with_id(Integer int1) {
		response = request.get(BASE_URL + "/api/users/2");
	}

	@When("I update the user with id {int} to name {string} and job {string}")
	public void i_update_the_user_with_id_to_name_and_job(int id, String name, String job) {
		String payload = String.format("{\r\n"
				+ "    \"name\": \"%s\",\r\n"
				+ "    \"job\": \"%s\"\r\n"
				+ "}", name,job);
		response = request.body(payload).put(BASE_URL + "/api/users/" + id);
	}

	@When("I delete the user with id {int}")
	public void i_delete_the_user_with_id(int id) {
		response = request.delete("/api/users/" + id);
	}

	@And("The response should contain page number {string}")
	public void the_response_should_contain_page_number(String expPageNum) {
		String pageNum = response.jsonPath().getString("page");
		assertEquals(expPageNum, pageNum);
	}

	@When("I register a user with email {string} and password {string}")
	public void i_register_a_user_with_email_and_password(String email, String password) {
		String payload = String.format("{\r\n"
				+ "    \"email\": \"%s\",\r\n"
				+ "    \"password\": \"%s\"\r\n"
				+ "}", email,password);

		response = request
				.body(payload)
				.post("/api/register");
		System.out.println("Response code is : " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());

		JsonPath jsonPath = response.jsonPath();
		
		int IDValue = jsonPath.getInt("id");
		String tokenValue = jsonPath.getString("token"); 

		System.out.println("ID is : "+ IDValue);
		System.out.println("Token is : "+ tokenValue);

		//Assertions on json response body
		assertNotNull(jsonPath.getInt("id"), "id is missing in response!");
		org.junit.Assert.assertNotNull(jsonPath.get("token"), "token is missing in response!");

		assertEquals(4, IDValue, "Expected id to be 4");
		assertEquals(tokenValue.startsWith("QpwL"), true, "Token does not start with expected prefix..!");
	}
}
