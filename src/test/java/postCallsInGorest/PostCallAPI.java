package postCallsInGorest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import responseValidation.Params;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PostCallAPI {
	
	@Test(priority=1)
	public void createAUser1() {
		
		baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
								.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
								.contentType(ContentType.JSON)
								.body("{\n"
										+ "    \"name\": \"Api Testing\",\n"
										+ "    \"gender\": \"male\",\n"
										+ "    \"email\": \"at5@gmail.com\",\n"
										+ "    \"status\": \"active\"\n"
										+ "}")
								.pathParams(Params.pathparams())
								.when()
								.post("/{access}/{version}/{resource}");
		
		System.out.println("Status Code : " +response.getStatusCode());
		
		System.out.println("Status Line : " +response.getStatusLine());
		
		//response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		System.out.println("Id of the user : " +js.getInt("id"));
		
		System.out.println("Name of the user : " +js.getString("name"));
		
		System.out.println("Email of the user : " +js.getString("email"));
		
		System.out.println("gender of the user : " +js.getString("gender"));
		
		System.out.println("Status of the user : " +js.getString("status"));
		

		Assert.assertEquals(response.getStatusCode(), 201);
		
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		
		Assert.assertEquals(js.getString("name"), "Api Testing");
		
		Assert.assertEquals(js.getString("email"), "at5@gmail.com");
		
		Assert.assertEquals(js.getString("gender"), "male");
		
		Assert.assertEquals(js.getString("status"), "active");
		
	}
	
	
	
	
	@Test(priority=1)
	public void createARandomUser1() {
		
		baseURI = "https://gorest.co.in";
		
		String actualEmail = getRandomemailid();		
		Response response = given().log().all()
								.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
								.contentType(ContentType.JSON)
								.body("{\n"
										+ "    \"name\": \"Api Testing\",\n"
										+ "    \"gender\": \"male\",\n"
										+ "    \""+actualEmail+"\",\n"
										+ "    \"status\": \"active\"\n"
										+ "}")
								.pathParams(Params.pathparams())
								.when()
								.post("/{access}/{version}/{resource}");
		
		System.out.println("Status Code : " +response.getStatusCode());
		
		System.out.println("Status Line : " +response.getStatusLine());
		
		//response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		//System.out.println("Id of the user : " +js.getInt("id"));
		
		System.out.println("Name of the user : " +js.getString("name"));
		
		System.out.println("Email of the user : " +js.getString("email"));
		
		System.out.println("gender of the user : " +js.getString("gender"));
		
		System.out.println("Status of the user : " +js.getString("status"));
		

		Assert.assertEquals(response.getStatusCode(), 201);
		
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		
		Assert.assertEquals(js.getString("name"), "Api Testing");
		
		Assert.assertEquals(js.getString("gender"), "male");
		
		Assert.assertEquals(js.getString("status"), "active");
		
}
	
	public String getRandomemailid() {
		return "at"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test(priority=1)
	public void createAUserusingFIle() throws IOException {
		
		baseURI = "https://gorest.co.in";
	
		String generatedemail = getRandomemailid();
		
		String rawbody = new String(Files.readAllBytes(Paths.get("./src/test/resources/Payload/Userdetails.json")));
		
		String updatedbody = rawbody.replace("{{email}}", generatedemail);
				
		Response response = given().log().all()
								.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
								.contentType(ContentType.JSON)
								.body(updatedbody)
								.pathParams(Params.pathparams())
								.when()
								.post("/{access}/{version}/{resource}");
		
		System.out.println("Status Code : " +response.getStatusCode());
		
		System.out.println("Status Line : " +response.getStatusLine());
		
		Assert.assertEquals(response.getStatusCode(), 201);
		
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		//System.out.println("Id of the user : " +js.getInt("id"));
		
		System.out.println("Name of the user : " +js.getString("name"));
		
		System.out.println("Email of the user : " +js.getString("email"));
		
		System.out.println("gender of the user : " +js.getString("gender"));
		
		System.out.println("Status of the user : " +js.getString("status"));
		
		Assert.assertEquals(js.getString("name"), "Rajesh1");
		
		Assert.assertEquals(js.getString("gender"), "male");
		
		Assert.assertEquals(js.getString("status"), "active");
		
		Assert.assertEquals(js.getString("email"), generatedemail);
		
}
}
