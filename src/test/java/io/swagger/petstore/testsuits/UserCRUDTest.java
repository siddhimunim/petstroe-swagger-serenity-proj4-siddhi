package io.swagger.petstore.testsuits;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.petstoreinfo.UserStoreSteps;
import io.swagger.petstore.testbase.TerstBase;
import io.swagger.petstore.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TerstBase {
    static int userId;
    static String username= "JenThak";
    static String firstName = "Jenna";
    static String lastName= "Thaker";
    static String email = "jenthak" + TestUtils.getRandomValue() + "@gmail.com";
    static String password = "katiebruno";
    static String phone = "0125825";
    static int userStatus = 01;


    @Steps
    UserStoreSteps userSteps;
    @Title("Creating new User")
    @Test
    public void test001(){
        ValidatableResponse response = userSteps.createNewUser(username,firstName,lastName,email,password,phone,userStatus);
        response.log().all().statusCode(200);


    }
    @Title("Getting user by firstname")
    @Test
    public void test002(){
   ValidatableResponse response = userSteps.getUserByUserName(username);
        response.log().all().statusCode(200);
         String name = response.extract().path("username");
         Assert.assertTrue(name.matches(username));

    }
    @Title("update user and verify")
    @Test
    public void test003(){
        firstName = "Katie";
      ValidatableResponse response=  userSteps.updateUser(username,firstName,lastName,email,password,phone,userStatus);
       response .log().all().statusCode(200);


    }
    @Title("Delete user")
    @Test
    public void test004(){
        userSteps.deleteUser(username).log().all().statusCode(200);
       userSteps.getUserByUserName(username).log().all().statusCode(200);
    }
}
