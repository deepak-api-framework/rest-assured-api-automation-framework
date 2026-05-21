package tests;

import java.util.Map;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import listeners.Listeners;
import pojo.AddPlaceResponse;
import pojo.GetPlaceResponse;
import services.PlaceService;
import testdata.AddPlaceData;


public class GetPlaceTest extends BaseTest{

    @Test(dataProvider = "addPlaceData", dataProviderClass = AddPlaceData.class)
    public void getPlace(Map<String, String> data) {
    	
    	ExtentTest test = Listeners.test.get();

        test.info("Creating place before GET validation");

        AddPlaceResponse addResponse =
                PlaceService.createPlace(data, test);

        String placeId = addResponse.getPlace_id();

        test.info("Generated Place ID: " + placeId);

        test.info("Executing GET Place API");

        GetPlaceResponse getResponse =
                PlaceService.getPlace(placeId, test);

        Assert.assertEquals(getResponse.getAddress(), data.get("address"));

        Assert.assertEquals(getResponse.getName(), data.get("name"));

        Assert.assertEquals(getResponse.getLanguage(), data.get("language"));

        test.pass("GET Place validation successful");
    }
}