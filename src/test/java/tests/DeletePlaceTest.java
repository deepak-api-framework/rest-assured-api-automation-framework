package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import listeners.Listeners;
import pojo.AddPlaceResponse;
import pojo.CommonResponse;
import services.PlaceService;
import testdata.AddPlaceData;

public class DeletePlaceTest extends BaseTest{
	
	@Test(dataProvider = "addPlaceData",
	          dataProviderClass = AddPlaceData.class)
	public void deletePlace(Map<String, String>data) {
		
		ExtentTest test = Listeners.test.get();
		
		test.info("Creating a place before DELETE validation");
		
		AddPlaceResponse addResponse =
                PlaceService.createPlace(data, test);
		
		String placeId = addResponse.getPlace_id();

        test.info("Generated Place ID: " + placeId);
        
        test.info("Deleting Place");
        
        CommonResponse response = PlaceService.deletePlace(placeId, test);
        
        Assert.assertEquals(response.getStatus(),"OK", "DELETE API status validation failed");
        
        test.pass("DELETE API validation successful");
	}

}
