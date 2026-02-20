package lombakapitest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class FakeStoreAPITest {
	
	@Test
	public void postcallusingLombakwithbuilder() {
		
		
		baseURI = "https://fakestoreapi.com";//public/v2/users
		
		FakestoreLombak details = new FakestoreLombak.FakestoreLombakBuilder()
				.title("Apple pro")
				.price(1235467.99)
				.build();
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(details)
		.when()
		.post("/products")
		.then().log().body()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " +id);
		
		
		System.out.println("------------------------ Get Call----------------------");
		
		baseURI = "https://fakestoreapi.com";
		given().log().all()
		.when()
		.get("/products/"+id)
		.then().log().body()
		.assertThat()
		.statusCode(200);
		
		
	}

	
	@Test
	public void postcallusingLombakwithoutbuilder() {
		
		
		baseURI = "https://fakestoreapi.com";//public/v2/users
		
		FakestoreLombak details = new FakestoreLombak("Apple Iphone", 12900.75);
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(details)
		.when()
		.post("/products")
		.then().log().body()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " +id);
		
		
		System.out.println("------------------------ Get Call----------------------");
		
		baseURI = "https://fakestoreapi.com";
		given().log().all()
		.when()
		.get("/products/"+id)
		.then().log().body()
		.assertThat()
		.statusCode(200);
		
		
	}

}


