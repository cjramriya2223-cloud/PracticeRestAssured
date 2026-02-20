package requestandresponsespecification;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GoRestRequestandResponseSpecification {
	
	
	RequestSpecification request1, request2;
	
	ResponseSpecification response;
	
	@BeforeTest
	public void setup() {
		
		 request1 =   RestAssured.given().log().all()
				 		.baseUri("https://gorest.co.in")
				 		.header("Content-Type", "application/json")
				 		.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97");
		 
		 
		 request2 =   RestAssured.given().log().all()
			 			.baseUri("https://gorest.co.in")
			 			.header("Content-Type", "application/json")
			 			.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
			 			.queryParams("gender", "female")
			 			.queryParams("status", "active");
		 
		 response = RestAssured.expect().log().all()
				 	.statusCode(200)
				 	.statusLine("HTTP/1.1 200 OK")
					.time(lessThan(2000L))
					.header("Content-Type", "application/json; charset=utf-8")
					.header("Server", "cloudflare");
	}
	
	@Test
	public void getalluser() {
		
		request1
			.get("/public/v2/users")
			.then().log().body()
			.spec(response);
		
	}
	
	@Test
	public void getallmaleactive() {
		
		request2
			
			.get("/public/v2/users")
			.then().log().body()
			.spec(response);
		
	}
	
	@Test
	public void getsingleUser() {
		
		request1
			.get("/public/v2/users/8350659")
			.then().log().body()
			.spec(response)
			.body("name", equalTo("Dinesh Nambeesan"))
			.body("email", equalTo("nambeesan_dinesh@maggio-marks.example"))
			.body("gender", equalTo("male"))
			.body("status", equalTo("active"));
		
	}

}
