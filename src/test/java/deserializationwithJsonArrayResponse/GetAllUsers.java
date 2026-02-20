package deserializationwithJsonArrayResponse;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAllUsers {

	
	
	
	@Test
	
	public void getallUsers() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given()
		.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		.contentType(ContentType.JSON)
		.when()
		.get("/public/v2/users/");
		
		response.prettyPrint();
		
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		LombakUser[] jsonresponse = mapper.readValue(response.getBody().asString(), LombakUser[].class);
		
		System.out.println("Lenght of array : " +jsonresponse.length);
		
		for(LombakUser res : jsonresponse) {
			System.out.println("Id: " +res.getId());
			System.out.println("Name: " +res.getName());
			System.out.println("Gender: " +res.getGender());
			System.out.println("Status : " +res.getStatus());
			System.out.println("Email : " +res.getEmail());
			System.out.println("-------------------------------------------------");
			
		}
		
		
		
		
	}
}
