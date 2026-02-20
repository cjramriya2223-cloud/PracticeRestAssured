package postcallrequests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimplePostCall {
	
	@Test
	
	public void postCallWithStaticPayload() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		String userid = given().log().all()
				.contentType(ContentType.JSON)
				.header("x-api-key", "reqres-free-v1")
				.body(new File("./src/main/resources/postcreationuserforgorest.json"))
		.when()
		.post("/api/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of tbe newly created user :" +userid);
				
		
	}
	
	public String randomEmailid() {
		return new String("apiautomation"+System.currentTimeMillis()+"@gmail.com");
	}
	
	
	@Test
 public void postCallWithdynamicPayload() throws IOException {
		
		String randomemail = randomEmailid();
		
		System.out.println(randomemail);
		
		//Convert Json to String
		
		String rawjson = new String(Files.readAllBytes(Paths.get("./src/main/resources/gorestpostcall.json")));
		
		String updatedemail = rawjson.replace("{{email}}", randomemail);
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		int userid = given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
				.body(updatedemail)
		.when()
		.post("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of tbe newly created user :" +userid);
				
		
	}

}
