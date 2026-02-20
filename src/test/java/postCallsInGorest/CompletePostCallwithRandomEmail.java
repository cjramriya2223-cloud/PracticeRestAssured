package postCallsInGorest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import responseValidation.Params;

public class CompletePostCallwithRandomEmail {
	
	public String getRandomEmail() {
		return "apitesting"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void postcall() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String email = getRandomEmail();
		
		String raw = new String(Files.readAllBytes(Paths.get("./src/test/resources/Payload/Userdetails.json")));
		String updated = raw.replace("{{email}}", email);
		
		System.out.println("-------------------- POST CALL ------------------------");
		
		int id = given().log().all()
					.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
					.contentType(ContentType.JSON)
					.body(updated)
					.pathParams(Params.pathparams())
				.when()
					.post("/{access}/{version}/{resource}")
				.then().log().all()
					.assertThat()
					.statusCode(201)
					.extract()
					.path("id");
		
		
		System.out.println("-------------------- GET CALL ------------------------");
		
					given().log().all()
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.contentType(ContentType.JSON)
						.body(updated)
					.when() 
						.pathParams(Params.pathparams())
						.pathParam("userid", id)
						.get("/{access}/{version}/{resource}/{userid}")
					.then()
						.assertThat()
						.statusCode(200)
						.body("id", equalTo(id))
						.body("name", equalTo("APITesting"))
						.body("email", equalTo(email))
						.body("gender", equalTo("female"))
						.body("status", equalTo("active"));
			}
	
	@Test
	public void postcallusingPOJO() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String email = getRandomEmail();
		
		String raw = new String(Files.readAllBytes(Paths.get("./src/test/resources/Payload/Userdetails.json")));
		String updated = raw.replace("{{email}}", email);
		
		Users details =  new Users("Jamuna","female",email,"inactive");
		
		System.out.println("-------------------- POST CALL ------------------------");
		
		int id = given().log().all()
					.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
					.contentType(ContentType.JSON)
					.body(details)
					.pathParams(Params.pathparams())
				.when()
					.post("/{access}/{version}/{resource}")
				.then().log().all()
					.assertThat()
					.statusCode(201)
					.extract()
					.path("id");
		
		
		System.out.println("-------------------- GET CALL ------------------------");
		
					given().log().all()
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.contentType(ContentType.JSON)	
					.when() 
						.pathParams(Params.pathparams())
						.get("/{access}/{version}/{resource}/"+id)
					.then()
						.assertThat()
						.statusCode(200)
						.body("id", equalTo(id))
						.body("name", equalTo(details.getName()))
						.body("email", equalTo(email))
						.body("gender", equalTo(details.getGender()))
						.body("status", equalTo(details.getStatus()));
					
					
		System.out.println("-------------------- PUT CALL ------------------------");
		
		details.setStatus("active");
		details.setName("Jamuna cerlin");
		
		
		given().log().all()
			.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
			.contentType(ContentType.JSON)
			.body(details)
		.when() 
			.pathParams(Params.pathparams())
			.put("/{access}/{version}/{resource}/"+id)
		.then()
			.assertThat()
			.statusCode(200)
			.body("id", equalTo(id))
			.body("name", equalTo(details.getName()))
			.body("email", equalTo(email))
			.body("gender", equalTo(details.getGender()))
			.body("status", equalTo(details.getStatus()));					

		System.out.println("-------------------- GET CALL ------------------------");
		
		given().log().all()
			.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
			.contentType(ContentType.JSON)
			.pathParams(Params.pathparams())
		.when() 
			.get("/{access}/{version}/{resource}/"+id)
		.then()
			.assertThat()
			.statusCode(200)
			.body("id", equalTo(id))
			.body("name", equalTo(details.getName()))
			.body("email", equalTo(email))
			.body("gender", equalTo(details.getGender()))
			.body("status", equalTo(details.getStatus()));		
					
					
						
		}

}
