package workflow;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import listeners.Listeners;
import pojo.AddPlaceResponse;
import pojo.CommonResponse;
import pojo.GetPlaceResponse;
import services.PlaceService;
import testdata.AddPlaceData;
import pojo.ErrorResponse;

public class PlaceWorkflowTest extends BaseTest {

	@Test(dataProvider = "addPlaceData", dataProviderClass = AddPlaceData.class)

	public void completeFlow(Map<String, String> data) {

		ExtentTest test = Listeners.test.get();

		test.info("Executing Add Place API");

		AddPlaceResponse response = PlaceService.createPlace(data, test);

		Assert.assertEquals(response.getStatus(), "OK");

		Assert.assertNotNull(response.getPlace_id());

		test.pass("Add Place API Executed Successfully");

		String placeID = response.getPlace_id();

		test.info("Generated Place_ID: " + placeID);

		test.info("Executing GET Place API");

		GetPlaceResponse getResponse = PlaceService.getPlace(placeID, test);

		Assert.assertEquals(getResponse.getName(), data.get("name"));

		Assert.assertEquals(getResponse.getAddress(), data.get("address"));

		Assert.assertEquals(getResponse.getLanguage(), data.get("language"));

		test.pass("GET Place validation successful");

		test.info("Executing Update Place API");

		String updatedAddress = "Address successfully updated";

		CommonResponse updateResponse = PlaceService.updatePlace(placeID, updatedAddress, test);

		Assert.assertEquals(updateResponse.getMsg(), "Address successfully updated");

		test.pass("UPDATE API successful Executed");

		GetPlaceResponse getUpdatedResponse = PlaceService.getPlace(placeID, test);

		Assert.assertEquals(getUpdatedResponse.getAddress(), updatedAddress);

		test.info("Address is Updated Successfully");

		test.info("Executing DELETE Place API");

		CommonResponse deleteResponse = PlaceService.deletePlace(placeID, test);

		Assert.assertEquals(deleteResponse.getStatus(), "OK", "DELETE API status validation failed");

		test.pass("DELETE API successful Executed");

		test.info("Executing GET Place API To Check Whether Location Deleted Successfully");

		ErrorResponse errorResponse = PlaceService.getDeletedPlace(placeID, test);

		Assert.assertTrue(errorResponse.getMsg().contains("doesn't exists"));

		test.pass("Complete Workflow Executed Successfully");

	}

}
