package apitest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import apihelper.Endpoints;
import apihelper.Jsonutilities;
import apihelper.Requestmodel;
import io.restassured.response.Response;

public class APITest {

	@Test
	public void apiGetTest() {
		// API call
		Response responses = given().when().get(Endpoints.employees);
		//responses.getBody().prettyPrint();
	}

	@Test
	public void apiPostTest() {

		Requestmodel reqmodel = new Requestmodel();
		reqmodel.setAge("24");
		reqmodel.setName("john");
		reqmodel.setSalary("65000");

		// API call
		Response responses = given().body(reqmodel).when().post(Endpoints.createRecord);
		//responses.getBody().prettyPrint();
		System.out.println(Jsonutilities.getValueByJPath(responses, "/message"));
		System.out.println(Jsonutilities.getValueByJPath(responses, "/data/id"));

	}

}
