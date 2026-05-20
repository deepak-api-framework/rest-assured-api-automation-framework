package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import resources.APIResources;
import resources.HTTPMethod;
import testdata.AddPlaceData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import pojo.AddPlaceResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloads.PayloadBuilder;

import base.ResponseBuilder;
import utils.APIUtils;
import listeners.Listeners;

public class AddPlaceTest {

	@Test(dataProvider = "addPlaceData", dataProviderClass = AddPlaceData.class)
	public void addPlace(Map<String, String> data) throws IOException {

		Object payload = PayloadBuilder.addPlacePayload(data.get("name"), data.get("address"), data.get("language"));

		Listeners.test.info("Preparing Add Place Payload");

		Response response =

				APIUtils.sendRequest(

						APIResources.ADD_PLACE.getResource(),

						HTTPMethod.POST,

						payload,

						null,
						
						Listeners.test);

		Listeners.test.info("POST API Executed Successfully");

		AddPlaceResponse addPlaceResponse = response.as(AddPlaceResponse.class);

		Assert.assertEquals(addPlaceResponse.getStatus(), "OK");

		Listeners.test.pass("Status Validation Successful");

		String placeId = addPlaceResponse.getPlace_id();

		Listeners.test.info("Generated Place ID: " + placeId);

		Map<String, String> queryParams = new HashMap<>();

		queryParams.put("place_id", placeId);

		Listeners.test.info("Executing GET API");

		Response getResponse =

				APIUtils.sendRequest(

						APIResources.GET_PLACE.getResource(),

						HTTPMethod.GET,

						null,

						queryParams,
						
						Listeners.test);
		
		getResponse.then()

				.log().all()

				.spec(ResponseBuilder.responseSpecification());

		String getPlaceResponse = getResponse.asString();

		System.out.println(getPlaceResponse);

		JsonPath getJson = new JsonPath(getPlaceResponse);

		String actualAddress = getJson.getString("address");

		Assert.assertEquals(actualAddress, data.get("address"));

		Listeners.test.pass("Address Validation Successful");
	}
}