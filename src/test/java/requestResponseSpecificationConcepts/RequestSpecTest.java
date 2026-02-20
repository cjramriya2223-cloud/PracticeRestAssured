package requestResponseSpecificationConcepts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecTest {
	
	@Test
	
	public void reqResSpecficationTest() {
		
		
		
		RequestSpecification requestspec =     RestAssured.given().log().all()
													.baseUri("https://jsonplaceholder.typicode.com/")
														.header("Content-Type","application/json");
		
		
		requestspec.get("/posts")
			.then().log().all()
			.assertThat()
			 	.statusCode(200);
		
		
		requestspec.get("/comments")
		.then().log().all()
		.assertThat()
		 	.statusCode(200);
		
		requestspec
		.body("{\n"
				+ "    \"userId\": 1,\n"
				+ "    \"title\": \"Jamuna Tes\",\n"
				+ "    \"body\": \"I love Api test\"\n"
				+ "  }")
		.post("/posts")
		.then().log().all()
		.assertThat()
		.statusCode(201);
		
		requestspec.get("/posts/1")
		.then().log().all()
		.assertThat()
		 	.statusCode(200);
	
			
		
	}

}
