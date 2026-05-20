package base;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseBuilder {

    public static ResponseSpecification responseSpecification() {

        ResponseSpecification resSpec =
                new ResponseSpecBuilder()

                        .expectStatusCode(200)

                        .expectContentType("application/json")

                        .build();

        return resSpec;
    }
}