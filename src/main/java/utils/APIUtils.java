package utils;

import io.restassured.response.Response;
import resources.HTTPMethod;

import static io.restassured.RestAssured.*;

import java.util.Map;

import com.aventstack.extentreports.ExtentTest;

import base.RequestBuilder;

public class APIUtils {

    public static Response sendRequest(

            String endpoint,

            HTTPMethod method,

            Object payload,

            Map<String, String> queryParams,ExtentTest test) {

        Response response = null;

        switch (method) {

        case POST:

            response = given()

                    .spec(RequestBuilder.requestSpecification())

                    .body(payload)

                    .when()

                    .post(endpoint)

                    .then()

                    .extract()

                    .response();

            break;

        case GET:

            response = given()

                    .spec(RequestBuilder.requestSpecification())

                    .queryParams(queryParams)

                    .when()

                    .get(endpoint)

                    .then()

                    .extract()

                    .response();

            break;

        case PUT:

            response = given()

                    .spec(RequestBuilder.requestSpecification())

                    .body(payload)

                    .when()

                    .put(endpoint)

                    .then()

                    .extract()

                    .response();

            break;
            
        case DELETE:

            response = given()

                    .spec(RequestBuilder.requestSpecification())

                    .body(payload)

                    .when()

                    .delete(endpoint)

                    .then()

                    .extract()

                    .response();

            break;
           

        default:

            throw new IllegalArgumentException("Invalid HTTP Method");
        }

        return response;
    }
}