package tests;

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



public class UpdatePlaceTest extends BaseTest{

    @Test(dataProvider = "addPlaceData",
          dataProviderClass = AddPlaceData.class)

    public void updatePlace(Map<String, String> data) {

    	ExtentTest test = Listeners.test.get();

        test.info("Creating place before UPDATE validation");

        // STEP 1 → Create Place
        AddPlaceResponse addResponse =
                PlaceService.createPlace(data, test);

        String placeId = addResponse.getPlace_id();

        test.info("Generated Place ID: " + placeId);

        // STEP 2 → Update Address
        String updatedAddress = "Updated Gurgaon Address";

        test.info("Updating address");

        CommonResponse updateResponse =
                PlaceService.updatePlace(
                        placeId,
                        updatedAddress,
                        test);

        // STEP 3 → Validate Update API Status
        Assert.assertEquals(
                updateResponse.getMsg(),
                "Address successfully updated");

        test.pass("UPDATE API successful");

        // STEP 4 → Fetch Updated Place
        GetPlaceResponse getResponse =
                PlaceService.getPlace(placeId, test);

        // STEP 5 → Validate Updated Address
        Assert.assertEquals(
                getResponse.getAddress(),
                updatedAddress);

        test.pass("Updated address validation successful");
    }
}