package fakeStore;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FSPT {

	
	@Test
	public void userwithBuilder() {
		
		baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
				.contentType(ContentType.JSON)
				.when()
				.get("/products");
		
		JsonPath js = response.jsonPath();
		
		
		js.prettyPrint();
				
		
	}
	
	@Test
	public void fetchminandMaxPrice() {

	    baseURI = "https://fakestoreapi.com";

	    Response response = given().log().all()
	            .contentType(ContentType.JSON)
	            .when()
	            .get("/products");

	    // Extract all prices
	    List<Float> prices = response.jsonPath().getList("price", Float.class);

	    float minPrice = prices.get(0);
	    float maxPrice = prices.get(0);

	    for (float price : prices) {
	        if (price < minPrice) {
	            minPrice = price;
	        }
	        if (price > maxPrice) {
	            maxPrice = price;
	        }
	    }

	    System.out.println("Min Price: " + minPrice);
	    System.out.println("Max Price: " + maxPrice);
	}

	
	
	
}
