package base;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseTest {

    ConfigReader configReader = new ConfigReader();

    public void setupAPI() {

        RestAssured.baseURI =
                configReader.getBaseUrl();
    }
}