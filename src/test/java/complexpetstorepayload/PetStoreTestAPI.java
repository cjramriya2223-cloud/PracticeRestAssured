package complexpetstorepayload;

import org.testng.annotations.Test;

import complexpetstorepayload.PetstorePojoClass.Tags;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class PetStoreTestAPI {
	
	@Test
	
	public static void postcallwithoutbuilder() {
		
		baseURI = "https://petstore.swagger.io";
		
		
		PetstorePojoClass.Category category = new PetstorePojoClass.Category(101, "Johnny");
		
		List<String> photoUrls = Arrays.asList("https://dog1.com","https://dog2.com", "https://dog3.com" );
		
		PetstorePojoClass.Tags tag1 = new PetstorePojoClass.Tags(1 , "Black");
		
		PetstorePojoClass.Tags tag2 = new PetstorePojoClass.Tags(2 , "Red");
		
		PetstorePojoClass.Tags tag3 = new PetstorePojoClass.Tags(3 , "Brown");
		
		List<PetstorePojoClass.Tags> tags = Arrays.asList(tag1, tag2, tag3 );
		
		PetstorePojoClass petstorebody = new PetstorePojoClass(1001, "Dogs", "available", category,photoUrls,tags);
		
	
		given().log().all() 
		.contentType(ContentType.JSON)
		.body(petstorebody)
		.when()
		.post("/v2/pet")
		.then().log().all()
		.assertThat()
		.statusCode(200);
	}
	
	
@Test
	
	public static void postcallwithbuilder() {
		
		baseURI = "https://petstore.swagger.io";
		
		
		PetstorePojoClass.Category category = new PetstorePojoClass.Category(101, "Johnny");
		
		List<String> photoUrls = Arrays.asList("https://dog1.com","https://dog2.com", "https://dog3.com" );
		
		PetstorePojoClass.Tags tag1 = new PetstorePojoClass.Tags(1 , "Black");
		
		PetstorePojoClass.Tags tag2 = new PetstorePojoClass.Tags(2 , "Red");
		
		PetstorePojoClass.Tags tag3 = new PetstorePojoClass.Tags(3 , "Brown");
		
		List<PetstorePojoClass.Tags> tags = Arrays.asList(tag1, tag2, tag3 );
		
		PetstorePojoClass petstorebody = new PetstorePojoClass.PetstorePojoClassBuilder()
				.id(1002)
				.name("Cats")
				.status("available")
				.category(category)
				.photoUrls(photoUrls)
				.tags(tags)
				.build();
	
		given().log().all()
		.contentType(ContentType.JSON)
		.body(petstorebody)
		.when()
		.post("/v2/pet")
		.then().log().body()
		.assertThat()
		.statusCode(200);
	}


}
