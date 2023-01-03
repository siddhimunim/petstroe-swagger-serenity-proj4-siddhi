package io.swagger.petstore.petstoreinfo;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.UserPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserStoreSteps {

    @Step("Create new user with id:{0},username :{1},firstName :{2}, lastName : {3}, email :{4}, password :{5} phone :{6} userStatus :{7}")

    public ValidatableResponse createNewUser(String username,String firstName,String lastName,String email,String password,String phone,int userStatus){
        UserPojo userPojo = new UserPojo();
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);

       return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
               .then().log().all();
    }
    @Step("Getting the user information with firstName: {0}")
    public ValidatableResponse getUserByUserName(String userName){
        return SerenityRest.given().log().all()
               .pathParam("username",userName)
                .header("Content-Type", "application/json")
                .when()

                .get(EndPoints.GET_SINGLE_USER_BY_USERNAME)
                .then().log().all();


    }
    @Step("Update new user with id :{0},username :{1},firstName :{2}, lastName : {3}, email :{4}, password :{5} phone :{6} userStatus :{7}")
    public ValidatableResponse updateUser(String username,String firstName,String lastName,String email,String password,String phone,int userStatus){
        UserPojo userPojo = new UserPojo();
        userPojo.setUsername(username);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(userStatus);

        return SerenityRest.given()
                .pathParam("username",username)
                .when()
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_USERNAME)
                .then().log().all();
    }
    @Step("Deleting existing user with username :{0}")
    public ValidatableResponse deleteUser(String username){
        return SerenityRest.given()
                .pathParam("username",username)
                .when()
                .delete(EndPoints.DELETE_USER_BY_USERNAME)
                .then().log().all();}

}
