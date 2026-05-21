package base;

import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

import utils.ConfigReader;

public class BaseTest {

    protected ExtentTest test;

    ConfigReader configReader = new ConfigReader();

    @BeforeMethod
    public void setup() {

        RestAssured.baseURI =
                configReader.getBaseUrl();

    }
}