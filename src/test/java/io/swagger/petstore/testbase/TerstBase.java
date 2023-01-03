package io.swagger.petstore.testbase;

import io.swagger.petstore.constants.Path;
import io.swagger.petstore.utils.PropertyReader;
import io.restassured.RestAssured;
import io.swagger.petstore.utils.PropertyReader;
import io.swagger.petstore.utils.TestUtils;
import org.junit.BeforeClass;

public class TerstBase extends TestUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
     //   RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath= Path.PETSTORE;

    }

}
