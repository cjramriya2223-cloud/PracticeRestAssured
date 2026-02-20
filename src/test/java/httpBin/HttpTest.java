package httpBin;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import static httpBin.HttpPojo.*;

import static httpBin.HttpPojo.Category.*;

public class HttpTest {
	

	@Test
	public void userwithoutBuilder() {
		
		baseURI ="http://httpbin.org";
		
		List<String> continent 		=  Arrays.asList("Africa","Asia");
		
		Attributes attributes 		= new Attributes(continent,5);
		
		Category category 			= new Category(5001, "Wild Animals",attributes);
		
		List<String> tags 			= Arrays.asList("wild", "predator");
		
		HttpPojo http 				=  new HttpPojo(101,"lion",true,tags,category);
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(http)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.statusLine("HTTP/1.1 200 OK");
}
	
	@Test
	public void userBuilder() {
		
		baseURI ="http://httpbin.org";
		
		List<String> continent 	=   Arrays.asList("India","Us");	
		
		Attributes attributes 	= 	new Attributes.AttributesBuilder()
									.continent(continent)
									.dangerLevel(5)
									.build();
		
		Category category 		= 	new Category.CategoryBuilder()
									.id(5001)
									.name("Wild Animals")
									.attributes(attributes)
									.build();
		
		List<String> tags 		= 	Arrays.asList("wild", "predator");
		
		HttpPojo http 			= 	new HttpPojo.HttpPojoBuilder()
									.id(101)
									.name("Tiger")
									.active(true)
									.tags(tags)
									.category(category)
									.build();
						
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(http)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.statusCode(200);
}
	
	@Test
	public void validatingresponsewithoutBuilder() {
		
		baseURI ="http://httpbin.org";
		
		List<String> continent 		=  Arrays.asList("Africa","Asia");
		
		Attributes attributes 		= new Attributes(continent,5);
		
		Category category 			= new Category(5001, "Wild Animals",attributes);
		
		List<String> tags 			= Arrays.asList("wild", "predator");
		
		HttpPojo http 				=  new HttpPojo(101,"lion",true,tags,category);
		
		Response response = given().log().all()
							.contentType(ContentType.JSON)
							.body(http)
							.when()
							.post("/post");
							
}
	

}
