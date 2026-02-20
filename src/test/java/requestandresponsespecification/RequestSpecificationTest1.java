package requestandresponsespecification;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class RequestSpecificationTest1 {
	
	@Test
	public void getallposts() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/posts")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
	}
	
	@Test
	public void getallcomments() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/comments")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
	}
	
	@Test
	public void getalltodos() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/todos")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
	}
	
	@Test
	public void getallalbums() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/albums")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
	}
	
	@Test
	public void getallphotos() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/photos")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
		
	}
	@Test
	public void getallusers() {
		
		baseURI ="https://jsonplaceholder.typicode.com";
		
		given()
		.header("Content-Type", "application/json")
		.when()
		.get("/users")
		.then().log().body()
		.assertThat()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.time(lessThan(2000L))
		.header("Content-Type", "application/json; charset=utf-8")
		.header("Server", "cloudflare");
	}

}
