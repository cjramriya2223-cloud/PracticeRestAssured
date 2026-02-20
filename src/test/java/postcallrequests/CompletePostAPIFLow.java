package postcallrequests;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import responseValidation.Params;
import static org.hamcrest.Matchers.*;

public class CompletePostAPIFLow {
	
	public String getRandomemailid() {
		return "APIUser"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void postCallUsingFile() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String generatedemail = getRandomemailid();
		
		String raw =  new String(Files.readAllBytes(Paths.get("./src/test/resources/Payload/Userdetails.json")));
		String updatedbody=	raw.replace("{{email}}", generatedemail);
		
		System.out.println("------------------------- POST CALL ---------------------------------------");
		int userid = given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.body(updatedbody)
						.pathParams(Params.pathparams())
						.when()
						.post("/{access}/{version}/{resource}")
						.then()
						.assertThat()
						.statusCode(201)
						.and()
						.statusLine("HTTP/1.1 201 Created")
						.extract()
						.path("id");
		
		System.out.println("Id of the user : " + userid);
		
		
		System.out.println("------------------------- GET CALL ---------------------------------------");
		
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
				.pathParams(Params.pathparams())
			.when()
				.get("/{access}/{version}/{resource}/"+userid)
			.then().log().body()
				.assertThat()
				.statusCode(200)
					.and()
				.statusLine("HTTP/1.1 200 OK")
					.body("id" ,equalTo(userid))
					.body("name",equalTo("APITesting"))
					.body("gender",equalTo("female"))
					.body("status", equalTo("active"))
					.body("email", equalTo(generatedemail));
				
}
	
	@Test
	public void postCallUsingPOJOClasswithParameterizedConstructor() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String generatedemail = getRandomemailid();
		
		UserPOJOClass userdetail = new UserPOJOClass("Jamuna", "Female", "active", generatedemail);
		
		System.out.println("------------------------- POST CALL ---------------------------------------");
		int userid = given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.body(userdetail)
						.pathParams(Params.pathparams())
						.when()
						.post("/{access}/{version}/{resource}")
						.then()
						.assertThat()
						.statusCode(201)
						.and()
						.statusLine("HTTP/1.1 201 Created")
						.extract()
						.path("id");
		
		System.out.println("Id of the user : " + userid);
		
		
		System.out.println("------------------------- GET CALL ---------------------------------------");
		
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
				.pathParams(Params.pathparams())
			.when()
				.get("/{access}/{version}/{resource}/"+userid)
			.then().log().body()
				.assertThat()
				.statusCode(200)
					.and()
				.statusLine("HTTP/1.1 200 OK")
					.body("id" ,equalTo(userid))
					.body("name",equalTo("Jamuna"))
					.body("gender",equalTo("female"))
					.body("status", equalTo("active"))
					.body("email", equalTo(generatedemail));
		
		System.out.println("------------------------- DELETE CALL ---------------------------------------");
		
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
				.pathParams(Params.pathparams())
			.when()
				.delete("/{access}/{version}/{resource}/"+userid)
			.then()
				.assertThat()
				.statusCode(204)
					.and()
				.statusLine("HTTP/1.1 204 No Content");
		
		System.out.println("------------------------- GET CALL ---------------------------------------");
		
		given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
				.pathParams(Params.pathparams())
			.when()
				.get("/{access}/{version}/{resource}/"+userid)
			.then()
				.assertThat()
				.statusCode(404)
					.and()
				.statusLine("HTTP/1.1 404 Not Found")
				.body("message", equalTo("Resource not found"));
					
					
				
}
	
	@Test
	public void postCallUsingPOJOClasswithDefaultConstructor1() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String generatedemail = getRandomemailid();
		
		UserPOJOClass userdetail = new UserPOJOClass();
		
		System.out.println("------------------------- POST CALL ---------------------------------------");
		 Response response = given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.body(userdetail)
						.pathParams(Params.pathparams())
						.when()
						.post("/{access}/{version}/{resource}");
						
		JsonPath js = response.jsonPath();
		
		// First way
		
		System.out.println("----------- First Element in the response array ----------------");
		
		System.out.println("Email field: " + js.getString("[0].field"));
		
		System.out.println("Message field: " + js.getString("[0].message"));
		
		Assert.assertEquals(js.getString("[0].field"), "email");
		
		Assert.assertEquals(js.getString("[0].message"), "can't be blank");
		
		
		
		
		System.out.println("----------- Second Element in the response array ----------------");
		
		System.out.println("Email field: " + js.getString("[1].field"));
		
		System.out.println("Message field: " + js.getString("[1].message"));
		
		Assert.assertEquals(js.getString("[1].field"), "name");
		
		Assert.assertEquals(js.getString("[1].message"), "can't be blank");
		
		
		
		System.out.println("----------- Third Element in the response array ----------------");
		
		System.out.println("Email field: " + js.getString("[2].field"));
		
		System.out.println("Message field: " + js.getString("[2].message"));
		
		Assert.assertEquals(js.getString("[2].field"), "gender");
		
		Assert.assertEquals(js.getString("[2].message"), "can't be blank, can be male of female");
		
		
		
		
		System.out.println("----------- Fourth Element in the response array ----------------");
		
		System.out.println("Email field: " + js.getString("[3].field"));
		
		System.out.println("Message field: " + js.getString("[3].message"));
		
		Assert.assertEquals(js.getString("[3].field"), "status");
		
		Assert.assertEquals(js.getString("[3].message"), "can't be blank");
						
}
	

	
	@Test
	public void postCallUsingPOJOClasswithDefaultConstructor2() throws IOException {
		
		baseURI = "https://gorest.co.in";
		
		String generatedemail = getRandomemailid();
		
		UserPOJOClass userdetail = new UserPOJOClass();
		
		System.out.println("------------------------- POST CALL ---------------------------------------");
		 Response response = given().log().all()
						.contentType(ContentType.JSON)
						.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
						.body(userdetail)
						.pathParams(Params.pathparams())
						.when()
						.post("/{access}/{version}/{resource}");
						
		JsonPath js = response.jsonPath();
		
		
		for(int i = 0; i<4; i++) {
			
			System.out.println("----------- Element of " + i + " index in the response array ----------------");
			
			System.out.println("Field: " + js.getString("["+i+"].field"));
			
			System.out.println("Message : " + js.getString("["+i+"].message"));
			
		}}
		
		
		@Test
		public void postCallUsingPOJOClasswithDefaultConstructor3() throws IOException {
			
			baseURI = "https://gorest.co.in";
			
			String generatedemail = getRandomemailid();
			
			UserPOJOClass userdetail = new UserPOJOClass();
			
			System.out.println("------------------------- POST CALL ---------------------------------------");
			given().log().all()
							.contentType(ContentType.JSON)
							.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
							.body(userdetail)
							.pathParams(Params.pathparams())
							.when()
							.post("/{access}/{version}/{resource}")
							.then().log().body()
							.assertThat()
							.statusCode(422)
							.and()
							.statusLine("HTTP/1.1 422 Unprocessable Entity")
							 .body("[0].field", equalTo("email"))
							 .body("[0].message", equalTo("can't be blank"))

							 .body("[1].field", equalTo("name"))
							 .body("[1].message", equalTo("can't be blank"))

							 .body("[2].field", equalTo("gender"))
							 .body("[2].message", equalTo("can't be blank, can be male of female"))

							 .body("[3].field", equalTo("status"))
							 .body("[3].message", equalTo("can't be blank"));

}
		
		
		@Test
		public void postCallUsingPOJOClasswithDefaultConstructor4() throws IOException {
			
			baseURI = "https://gorest.co.in";
			
			String generatedemail = getRandomemailid();
			
			UserPOJOClass userdetail = new UserPOJOClass();
			
			System.out.println("------------------------- POST CALL ---------------------------------------");
			given().log().all()
							.contentType(ContentType.JSON)
							.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
							.body(userdetail)
							.pathParams(Params.pathparams())
							.when()
							.post("/{access}/{version}/{resource}")
							.then().log().body()
							.assertThat()
							.statusCode(422)
							.and()
							.statusLine("HTTP/1.1 422 Unprocessable Entity")
							.body("field", hasItems("email", "name", "gender", "status", "age"))
							.body("message", hasItems(
					                    "can't be blank",
					                    "can't be blank, can be male of female"
					         ));


							 

}


























}
