package io.swagger.petstore.petstoreinfo;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.PetPojo;
import jline.internal.TestAccessible;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;

public class PetStoreSteps {
@Step("Create newPet with id :{0},name : {1},photoUrls :{2},tags :{3},status :{4} ")
    public ValidatableResponse createNewPetData(int id,PetPojo.CategoryData categoryData, String name, ArrayList<String> photoUrl, ArrayList<PetPojo.TagData> tags,String status){
    PetPojo petPojo = new PetPojo();

    petPojo.setId(id);
    petPojo.setCategory(categoryData);
    petPojo.setName(name);
    petPojo.setPhotoUrls(photoUrl);
    petPojo.setTags(tags);
    petPojo.setStatus(status);
   return SerenityRest.given().log().all()
           .header("Content-Type", "application/json")
           .body(petPojo)
           .when()
           .post(EndPoints.CREATE_PET)
           .then();


}
@Step("get petdetail by id :{0},name : {1},photoUrls :{2},tags :{3},status :{4}")
    public ValidatableResponse getPetDetailById(int id){
    return SerenityRest.given().log().all()
            .pathParam("id",id)
            .when()
            .header("Connection","keep-alive")
            .get(EndPoints.GET_SINGLE_PET_BY_ID)
            .then();
}
@Step("Update pet by using Id :{0}")
    public ValidatableResponse updatePetById(int id,PetPojo.CategoryData categoryData, String name, ArrayList<String> photoUrl, ArrayList<PetPojo.TagData> tags,String status){
    PetPojo petPojo = new PetPojo();

    petPojo.setId(id);
    petPojo.setCategory(categoryData);
    petPojo.setName(name);
    petPojo.setPhotoUrls(photoUrl);
    petPojo.setTags(tags);
    petPojo.setStatus(status);
    return SerenityRest.given().log().all()
            .header("Content-Type", "application/json")
          //  .pathParam("id",petId)
            .body(petPojo)
            .when()
            .put(EndPoints.CREATE_PET)
            .then().log().all().statusCode(200);
}
@Step("Delete pet by id : {0}")
    public ValidatableResponse deletePetById(int id) {
    return SerenityRest.given().log().all()
            .header("Connection", "keep-alive")
            .pathParam("id", id)
            .when()

            .delete(EndPoints.DELETE_PET_BY_ID)
            .then().log().all();

}



}
