package lombokpattern;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;

public class CreateuserwithPOJOLOmboxBuilderClass {
	
	public String randomemailgeneration() {
		return new String("apiautoamtion"+System.currentTimeMillis()+"@gmail.com");
	}
	
	@Test
	
	public void createuser() {
		
RestAssured.baseURI = "https://gorest.co.in";
		
		String email = randomemailgeneration() ;
		
		//LombakUser user = new LombakUser("Jamuna", email, "male", "active");
		
		//Create object using builder class
		
		LombakUserrequest user = new LombakUserrequest.LombakUserrequestBuilder()
											.name("BuilderClass")
											.status("active")
											.email(email)
											.gender("male")
											.build();
		
		
		 int userid = given()
		 	.contentType(ContentType.JSON)
		 	.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		 	.body(user)
		.when()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
			.statusCode(201)
			.extract()
			.path("id");
		 
		 given().log().all()
		  .header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		  .pathParam("id", userid)
		 .when()
		 .get("/public/v2/users/{id}")
		 .then()
		 .assertThat()
		 .statusCode(200);
	
		 
		 
		 
		 
		 
		 
		 
	}

}
