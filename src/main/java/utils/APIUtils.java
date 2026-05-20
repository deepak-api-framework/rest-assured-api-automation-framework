package utils;

import io.restassured.response.Response;
import resources.HTTPMethod;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;

import java.util.Map;

import base.RequestBuilder;


public class APIUtils {

	public static Response postRequest(String endpoint, Object payload) {

		return given().spec(RequestBuilder.requestSpecification()).body(payload)

				.when().post(endpoint)

				.then().extract().response();
	}

	public static Response getRequest(String endpoint, String queryParamKey, String queryParamValue) {

		return given()

				.spec(RequestBuilder.requestSpecification())

				.queryParam(queryParamKey, queryParamValue)

				.when()

				.get(endpoint)

				.then()

				.extract()

				.response();
	}

	public static Response sendRequest(

			String endpoint,

			HTTPMethod method,

			Object payload,

			Map<String, String> queryParams,

			ExtentTest test) throws JsonProcessingException {

		Response response = null;

		test.info("HTTP Method: " + method);

		test.info("Endpoint: " + endpoint);

		if (payload != null) {
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonPayload = mapper.writeValueAsString(payload);
		    test.info("Request Payload: " + jsonPayload);
		}

		if (queryParams != null) {

			test.info("Query Params: " + queryParams.toString());
		}

		switch (method) {

		case POST:

			response = given()

					.spec(RequestBuilder.requestSpecification())

					.body(payload)

					.when()

					.post(endpoint)

					.then()

					.extract()

					.response();

			break;

		case GET:

			response = given()

					.spec(RequestBuilder.requestSpecification()).queryParams(queryParams)

					.when()

					.get(endpoint)

					.then()

					.extract()

					.response();
			break;

		default:

			throw new IllegalArgumentException("Invalid HTTP Method");

		}

		if (response != null) {

			test.info("Response Status Code: " + response.getStatusCode());

			test.info("Response Body: " + response.asPrettyString());
		}

		return response;

	}
}