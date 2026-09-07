package tests;

import java.util.Map;

import utils.LoggerUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import listeners.Listeners;
import pojo.AddPlaceResponse;
import services.PlaceService;
import testdata.AddPlaceData;

public class AddPlaceTest extends BaseTest {

    @Test(dataProvider = "addPlaceData",
            dataProviderClass = AddPlaceData.class)

    public void addPlace(Map<String, String> data) {
    	
    	ExtentTest test = Listeners.test.get();

        test.info("Executing Add Place API");
        LoggerUtil.info("Starting Add Place API");

        AddPlaceResponse response =
                PlaceService.createPlace(data, test);

        Assert.assertEquals(response.getStatus(), "OK");

        Assert.assertNotNull(response.getPlace_id());

        test.pass("Place created successfully");

        test.info("Generated Place ID: "
                + response.getPlace_id());
    }
}