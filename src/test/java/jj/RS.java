package jj;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RS {
	
	@Test
	
	public void reqresTest() {
		
		RequestSpecification requestspec =      
				RestAssured.given()
				.baseUri("https://jsonplaceholder.typicode.com/")
				.contentType(ContentType.JSON);
	

	
	//1. posts
	
		requestspec.get("posts")
			.then().log().body()
			.statusCode(200);
		
	//2. comments
		
		requestspec.get("comments")
			.then().log().body()
			.statusCode(200);
		
	//3. albums
		
		requestspec.get("albums")
		.then().log().body()
		.statusCode(200);
	
	//4. photos
		
		requestspec.get("photos")
		.then().log().body()
		.statusCode(200);
	
	//5. todos
		
		requestspec.get("todos")
		.then().log().body()
		.statusCode(200);
		
	//5. users
		
		requestspec.get("users")
		.then().log().body()
		.statusCode(200);
		
	//6. get single post
		
		requestspec.get("/posts/1")
		.then().log().body()
		.statusCode(200);
		
	//7. get single comment
		
		requestspec.get("/posts/1/comments")
		.then().log().body()
		.statusCode(200);
		
		
	//4.create a post call
		requestspec.body("{\n"
				+ "    \"userId\": 1,\n"
				+ "    \"title\": \"My Title\",\n"
				+ "    \"body\": \"I love API Testing\"\n"
				+ "  }")
		.post("posts")
		.then().log().body()
		.statusCode(201);
		
	//5.
	
}

}
