package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    static ConfigReader configReader = new ConfigReader();

    public static RequestSpecification requestSpecification() {

        RequestSpecification reqSpec = new RequestSpecBuilder()

                .setBaseUri(configReader.getBaseUrl())

                .addQueryParam("key",
                        configReader.getApiKey())

                .addHeader("Content-Type",
                        "application/json")

                .build();

        return given().spec(reqSpec);
    }
}