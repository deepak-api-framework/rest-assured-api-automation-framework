package services;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.response.Response;
import payloads.PayloadBuilder;
import pojo.AddPlaceResponse;
import pojo.CommonResponse;
import pojo.DeletePlaceRequest;
import pojo.ErrorResponse;
import pojo.GetPlaceResponse;
import pojo.UpdatePlaceRequest;
import resources.APIResources;
import resources.HTTPMethod;
import utils.APIUtils;
import validators.SchemaValidatorUtil;

public class PlaceService {

	public static AddPlaceResponse createPlace(Map<String, String> data, ExtentTest test) {

		Object payload = PayloadBuilder.addPlacePayload(

				data.get("name"),

				data.get("address"),

				data.get("language"));

		Response response = APIUtils.sendRequest(

				APIResources.ADD_PLACE.getResource(),

				HTTPMethod.POST,

				payload,

				null, test);
		
		SchemaValidatorUtil.validateSchema(response, "addPlaceSchema.json");

		AddPlaceResponse addPlaceResponse = response.as(AddPlaceResponse.class);

		return addPlaceResponse;
	}

	public static GetPlaceResponse getPlace(String placeId, ExtentTest test) {

		Map<String, String> queryParams = new HashMap<>();

		queryParams.put("place_id", placeId);

		Response response = APIUtils.sendRequest(

				APIResources.GET_PLACE.getResource(),

				HTTPMethod.GET,

				null,

				queryParams, test);

		return response.as(GetPlaceResponse.class);
	}

	public static CommonResponse updatePlace(String placeId, String newAddress, ExtentTest test) {

		UpdatePlaceRequest updateRequest = new UpdatePlaceRequest();

		updateRequest.setPlace_id(placeId);

		updateRequest.setAddress(newAddress);

		updateRequest.setKey("qaclick123");

		Response response = APIUtils.sendRequest(

				APIResources.UPDATE_PLACE.getResource(),

				HTTPMethod.PUT,

				updateRequest,

				null,

				test);

		return response.as(CommonResponse.class);
	}

	public static CommonResponse deletePlace(String placeId, ExtentTest test) {

		DeletePlaceRequest deleteRequest = new DeletePlaceRequest();

		deleteRequest.setPlace_id(placeId);

		Response response = APIUtils.sendRequest(

				APIResources.DELETE_PLACE.getResource(),

				HTTPMethod.DELETE,

				deleteRequest,

				null,

				test);

		return response.as(CommonResponse.class);
	}
	
	public static ErrorResponse getDeletedPlace(
	        String placeId,
	        ExtentTest test) {

	    Map<String, String> queryParams = new HashMap<>();

	    queryParams.put("place_id", placeId);

	    Response response = APIUtils.sendRequest(
	            APIResources.GET_PLACE.getResource(),
	            HTTPMethod.GET,
	            null,
	            queryParams,
	            test);

	    return response.as(ErrorResponse.class);
	}
}