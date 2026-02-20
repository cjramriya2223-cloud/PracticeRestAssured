package petapiwithLombak;

import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import petapiwithLombak.Pet.Tag;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import static petapiwithLombak.Pet.*;

public class CreatePitAPITest {
	
	@Test
	
	public void createPETWithoutBuilderPattern() {
		
		
		RestAssured.baseURI= "https://petstore.swagger.io";
		
		Category catergory = new Category(101, "Dog");
				
		List<String> photoUrls = Arrays.asList("https://dog1.com", "https://dog2.com","https://Dogs3.com");
		
		Tag tag1 = new Tag(201, "Red","active");
		Tag tag2 = new Tag(301, "Black","active");
		Tag tag3 = new Tag(401, "Brown","active");
		
		List<Tag> tags = Arrays.asList(tag1, tag2, tag3);
				
				
		Pet pet = new Pet(1, "Jamuna" , "available", photoUrls, tags, catergory);
		
		
		Response response = given().log().body()
		.contentType(ContentType.JSON)
		.body(pet)
		.when()
		.post("/v2/pet");
		
		System.out.println("Status code : " +response.statusCode());
		System.out.println("Status Line : " + response.statusLine());
		
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		
		System.out.println("id of the user : " +js.getInt("id"));
		
		System.out.println("Name of the user : " +js.getString("name"));
		
		System.out.println("Status of the user : " +js.getString("status"));
		
		System.out.println("Category id : " +js.getInt("category.id"));
		
		System.out.println("Category name : " +js.getString("category.name"));
		
		System.out.println("Photourls : "  + js.getString("photoUrls[0]"));
		System.out.println("Photourls : "  + js.getString("photoUrls[1]"));
		System.out.println("Photourls : "  + js.getString("photoUrls[2]"));
		
		System.out.println("--------tags -----");
		
		System.out.println("Tags : "  + js.getInt("tags[0].id"));
		System.out.println("Tags : "  + js.getString("tags[0].name"));
		System.out.println("Tags : "  + js.getInt("tags[1].id"));
		System.out.println("Tags : "  + js.getString("tags[1].name"));
		System.out.println("Tags : "  + js.getInt("tags[2].id"));
		System.out.println("Tags : "  + js.getString("tags[2].name"));
		
						
						
		
	}
	
@Test
	
	public void createPETWithBuilderPattern() {
		
		
		RestAssured.baseURI= "https://petstore.swagger.io";
		
		
		
			Category catergory	= new Category.CategoryBuilder()
									.id(101)
									.name("Cerlin")
									.build();
		
		List<String> photoUrls 	= Arrays.asList(
									"https://dog1.com", 
									"https://dog2.com",
									"https://Dogs3.com",
									"https://Dogs4.com");
		
						Tag tag1 = new Tag.TagBuilder()
									.id(201)
									.name("Black")
									.status("active")
									.build();
		
						Tag tag2 = new Tag.TagBuilder()
									.id(301)
									.name("Red")
									.status("active")
									.build();
		
						Tag tag3 = new Tag.TagBuilder()
									.id(302)
									.name("BROWN")
									.status("inactive")
									.build();
						
						Tag tag4 = new Tag.TagBuilder()
								.id(401)
								.name("White")
								.status("inactive")
								.build();
		
					List<Tag> tags = Arrays.asList(
										tag1, 
										tag2, 
										tag3,
										tag4);		
				
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
		
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		Assert.assertEquals(response.statusLine(),"HTTP/1.1 200 OK" );
		
		JsonPath js = response.jsonPath();
		
		Assert.assertEquals(js.getInt("id"), pet.getId());
		
		Assert.assertEquals(js.getString("name"), pet.getName());
		
		Assert.assertEquals(js.getString("status"), pet.getStatus());
		
		Assert.assertEquals(js.getInt("category.id"), catergory.getId());
		
		Assert.assertEquals(js.getString("category.name"), catergory.getName());
		
		for(int i = 0; i<photoUrls.size();i++) {
			
			Assert.assertEquals(js.getString("photoUrls["+i+"]"),pet.getPhotoUrls().get(i));
		}
		
		for(int i =0 ; i<tags.size();i++) {
			
			Assert.assertEquals(js.getInt("tags["+i+"].id"),pet.getTags().get(i).getId());
			
			Assert.assertEquals(js.getString("tags["+i+"].name"),pet.getTags().get(i).getName());
			
			Assert.assertEquals(js.getString("tags["+i+"].status"),pet.getTags().get(i).getStatus());
		}
		
		/*Assert.assertEquals(js.getString("photoUrls[0]"),pet.getPhotoUrls().get(0));
		
		Assert.assertEquals(js.getString("photoUrls[1]"),pet.getPhotoUrls().get(1));
		
		Assert.assertEquals(js.getString("photoUrls[2]"),pet.getPhotoUrls().get(2));
		
		Assert.assertEquals(js.getString("photoUrls[3]"),pet.getPhotoUrls().get(3));
		
		
		Assert.assertEquals(js.getInt("tags[0].id"),pet.getTags().get(0).getId());
		
		Assert.assertEquals(js.getString("tags[0].name"),pet.getTags().get(0).getName());
		
		Assert.assertEquals(js.getInt("tags[1].id"),pet.getTags().get(1).getId());
		
		Assert.assertEquals(js.getString("tags[1].name"),pet.getTags().get(1).getName());
		
		Assert.assertEquals(js.getInt("tags[2].id"),pet.getTags().get(2).getId());
		
		Assert.assertEquals(js.getString("tags[2].name"),pet.getTags().get(2).getName());
		
		Assert.assertEquals(js.getInt("tags[3].id"),pet.getTags().get(3).getId());
		
		Assert.assertEquals(js.getString("tags[3].name"),pet.getTags().get(3).getName());*/
		
		
	
		
		System.out.println(" All assertions are passed");
		
						
						
		
	}



}
