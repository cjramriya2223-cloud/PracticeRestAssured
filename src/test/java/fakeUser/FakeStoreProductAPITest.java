package fakeUser;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class FakeStoreProductAPITest {
	
	@Test
	public void getAllProduct() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
				
				Response response = 
				given()
				.when()
				.get("/products");
				
				ObjectMapper mapper  = new ObjectMapper();
				
				ProductLombak[] listOfProducts = mapper.readValue(response.getBody().asString(), ProductLombak[].class);
				
				System.out.println("Count Of Products : " +listOfProducts.length);
				
				for(ProductLombak productlist : listOfProducts  ) {
					
					if(productlist.getRating().getRate()==4.8){	
						
					System.out.println("Id : " +productlist.getId());
					System.out.println("Title : " +productlist.getTitle());
					System.out.println("Price : " +productlist.getPrice());
					System.out.println("Description : " +productlist.getDescription());
					System.out.println("Category : " +productlist.getCategory());
					System.out.println("Image : " +productlist.getImage());
					System.out.println("Rate : " +productlist.getRating().getRate());
					System.out.println("Count : "+productlist.getRating().getCount());
					System.out.println("-----------------------------------------------------------------");
					
				}}
				
		
	}

}
