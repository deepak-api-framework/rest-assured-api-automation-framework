package payloads;

import java.io.IOException;
import java.util.Arrays;

import pojo.AddPlace;
import pojo.Location;
import pojo.UpdatePlaceRequest;
import utils.ConfigReader;

public class PayloadBuilder {

    public static AddPlace addPlacePayload(
            String name,
            String address,
            String language) {

        // Create Location Object
        Location loc = new Location();

        loc.setLat(-38.383494);
        loc.setLng(33.427362);

        // Create Main AddPlace Object
        AddPlace addPlace = new AddPlace();

        addPlace.setLocation(loc);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);

        addPlace.setTypes(
                Arrays.asList("shoe park", "shop"));

        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);

        return addPlace;
    }
    
    public static UpdatePlaceRequest updatePlacePayload(String placeId, String newAddress) throws IOException {
    	
    	ConfigReader configReader = new ConfigReader();

        UpdatePlaceRequest updateRequest = new UpdatePlaceRequest();

        updateRequest.setPlace_id(placeId);

        updateRequest.setAddress(newAddress);

        updateRequest.setKey(configReader.getApiKey());

        return updateRequest;
    }
    
    public static String deletePlacePayload(String placeId) {

        return "{\n"
                + "\"place_id\":\"" + placeId + "\"\n"
                + "}";
    }
}