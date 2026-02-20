package petapiwithLombak;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;
import java.util.Locale.Category;

import org.testng.Assert;
import org.testng.annotations.Test;

//import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import petapiwithLombak.Pet.Tag;

public class CreatePetApiWithMoreAssertion {
	
	@Test
	public void petwithmoreassertion() {
	
	
	RestAssured.baseURI= "https://petstore.swagger.io";
	
	//passing category inner class
	
	Pet.Category catergory = new Pet.Category.CategoryBuilder().id(101).name("Jamuna").build();
	
	// photourls array
			
	List<String> photoUrls = Arrays.asList("https://dog1.com", "https://dog2.com","https://Dogs3.com");
	
	//passing tag inner class 
	
	Pet.Tag tag1 = new Pet.Tag.TagBuilder().id(201).name("Black").build();
	
	Pet.Tag tag2 =  new Pet.Tag.TagBuilder().id(301).name("Red").build();
	
	Pet.Tag tag3 = new Pet.Tag.TagBuilder().id(301).name("Red").build();
	
	List<Tag> tags = Arrays.asList(tag1, tag2, tag3);		
			
	Pet pet = new Pet.PetBuilder()
			.id(1)
			.name("Jamuna")
			.status("Available")
			.photoUrls(photoUrls)
			.tags(tags)
			.category(catergory)
			.build();
			

	
	Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(pet)
			.when()
			.post("/v2/pet");
	
	AssertJUnit.assertEquals(response.statusCode(), 200);
	
	JsonPath js = response.jsonPath();
	
	
	//Assert.assertEquals(js.getInt("id"), pet.getId() );
	AssertJUnit.assertEquals(js.getString("name"), pet.getName());
	AssertJUnit.assertEquals(js.getString("status"), pet.getStatus());
	//Assert.assertEquals(js.getInt("category.id"), catergory.getId());
	AssertJUnit.assertEquals(js.getString("category.name"), catergory.getName());
	AssertJUnit.assertEquals(js.getList("photoUrls"),pet.getPhotoUrls());
	
	for(int i = 0 ; i<tags.size(); i++) {
		
		//Assert.assertEquals(js.getInt("tags["+i+"].id"), pet.getTags().get(i).getId());
		//Assert.assertEquals(js.getString("tags["+i+"].name"), pet.getTags().get(i).getName());
	}
	
	

	
	
	
	}

}
