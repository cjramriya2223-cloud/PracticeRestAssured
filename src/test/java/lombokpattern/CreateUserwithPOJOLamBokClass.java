package lombokpattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.lang.runtime.ObjectMethods;
import java.net.ResponseCache;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserwithPOJOLamBokClass {
	
	
	public String randomemailgeneration() {
		return new String("apiautoamtion"+System.currentTimeMillis()+"@gmail.com");
	}
	
	@Test
	
	public void createuser() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		String email = randomemailgeneration() ;
		
		LombakUserrequest userrequest = new LombakUserrequest("Jamuna", email, "male", "active");
		
		int userid = 
		 given().log().body()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		.body(userrequest)// serialization converting java object to json
		.when()
		.post("/public/v2/users")
		.then().log().body()	
		.assertThat()
		.statusCode(201)
		.and()
			.body("name", equalTo(userrequest.getName()))
			.body("gender", equalTo(userrequest.getGender()))
			.body("email", equalTo(userrequest.getEmail()))
			.body("status", equalTo(userrequest.getStatus()))
			.and()
			.extract()
			.path("id");
		
		
		System.out.println("User id : " +userid);
		
		//RestAssured.baseURI = "https://gorest.co.in";
		
	Response response = given()
		.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		.pathParam("id", userid)
		.when()
		.get("/public/v2/users/{id}");
	
	ObjectMapper mapper = new ObjectMapper();
	
	LombakUserresponse userresponse = mapper.readValue(response.getBody().asString(), LombakUserresponse.class);
	
	Assert.assertEquals(userresponse.getName(), userrequest.getName());	
	Assert.assertEquals(userresponse.getGender(), userrequest.getGender());	
	Assert.assertEquals(userresponse.getStatus(), userrequest.getStatus());	
	Assert.assertEquals(userresponse.getEmail(), userrequest.getEmail());	
	Assert.assertEquals(userresponse.getId(), userid);
	
	
	System.out.println("All assertions passed");
	
		
		
		
	}
	

}
