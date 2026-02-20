package requestandresponsespecification;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

public class WithRequestSpecification {
	
	@Test
	public void getallresources() {
		
	RequestSpecification request =	  RestAssured.given()
										.baseUri("https://jsonplaceholder.typicode.com/")
										.header("Content-Type", "application/json");
	
	
	ResponseSpecification response = RestAssured.expect()
										.statusCode(200)
										.statusLine("HTTP/1.1 200 OK")
										.time(lessThan(2000L))
										.header("Content-Type", "application/json; charset=utf-8")
										.header("Server", "cloudflare");
	
	System.out.println("Get all Posts");
	
	
	
	request.get("posts")
		.then().log().body()
		.spec(response);
	
	System.out.println("Get all comments");
	
	
	request.get("comments")
		.then().log().body()
		.spec(response);
	
	System.out.println("Get all Albums");
	
	request.get("albums")
		.then().log().body()
		.spec(response);
	
	System.out.println("Get all Photos");
	
	request.get("photos")
		.then().log().body()
		.spec(response);
	
	System.out.println("Get all Todos");

	request.get("todos")
		.then().log().body()
		.spec(response);
	
	System.out.println("Get all users");

	request.get("users")
		.then().log().body()
		.spec(response);
	
	
	
	
	
	
	}
		

}
