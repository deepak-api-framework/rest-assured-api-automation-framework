package validators;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidatorUtil {

    public static void validateSchema(
            Response response,
            String schemaFileName) {

        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
                        "schemas/" + schemaFileName));
    }
}