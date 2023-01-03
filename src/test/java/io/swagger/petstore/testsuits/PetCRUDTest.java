package io.swagger.petstore.testsuits;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.model.PetPojo;
import io.swagger.petstore.petstoreinfo.PetStoreSteps;
import io.swagger.petstore.testbase.TerstBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PetCRUDTest extends TerstBase {
    static int petId;
    static int id = 23;
    static String name ="Dog";
    static String status = "available";


    @Steps
    PetStoreSteps petSteps;
    @Title("Create new pet")
    @Test
    public void test001(){
        ArrayList<String> photoUrl = new ArrayList<>();
        photoUrl.add("netPlanet@co.uk");
        ArrayList<PetPojo.TagData> tags  = new ArrayList<>();
        PetPojo.TagData tagData = new PetPojo.TagData(8,"Bruno");
        tags.add(tagData);
        PetPojo.CategoryData categoryData = new PetPojo.CategoryData(5,"Huskey");
        ValidatableResponse response = petSteps.createNewPetData(id,categoryData,name,photoUrl,tags,status);
        response.log().all().statusCode(200);
        id = response.extract().path("id");
    }
    @Title("Get Pet Detail")
    @Test
    public  void test002(){
        ValidatableResponse response= petSteps.getPetDetailById(id);
        response.log().all().statusCode(200);
    }
    @Title("Update pet Data")
    @Test
    public void test003(){
        name = "Doggie";
        ArrayList<String> photoUrl = new ArrayList<>();
        photoUrl.add("netPlanet@co.uk");
        ArrayList<PetPojo.TagData> tags  = new ArrayList<>();
        PetPojo.TagData tagData = new PetPojo.TagData(8,"Bruno");
        tags.add(tagData);
        PetPojo.CategoryData categoryData = new PetPojo.CategoryData(5,"Huskey");
        ValidatableResponse response = petSteps.updatePetById(id,categoryData,name,photoUrl,tags,status);
        response.log().all().statusCode(200);
        HashMap<String,Object>petUpdate = response.extract().path("");
        Assert.assertThat(petUpdate,hasValue(name));
    }
    @Title("Delete Pet by id")
    @Test
    public void test004() {
        petSteps.deletePetById(id).statusCode(200);
        petSteps.getPetDetailById(id).statusCode(404);
    }

}
