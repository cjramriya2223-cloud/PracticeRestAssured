package jj;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JJClass {
	
	@Test
	
	public void getproductsAPITest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =   RestAssured.given()
		.when()
		.get("/products");
		
		//response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		String jsonresponse = response.getBody().asString();
		
		ReadContext ctx = JsonPath.parse(jsonresponse);
		
		System.out.println(" ------- Prices greater than 50 -----------");
		
		List<Map<String, Object>> twoattribute = ctx.read("$[?(@.price>50)].['id','price','title','category']");
		
		System.out.println("Total Prices > 50 : " +twoattribute.size());
		
		System.out.println("Total Prices > 50 : " +twoattribute);
		
		for(Map<String, Object> li :twoattribute) {
			
			System.out.println("Id : " +li.get("id"));
			System.out.println("Price : " +li.get("price"));
			System.out.println("Title : " +li.get("title"));
			System.out.println("Category : " +li.get("category"));
			System.out.println("-------------------");
			
		}
		
		System.out.println(" ------- TOtal Ids -----------");
		
		List<Integer> id = ctx.read("$[?(@.price>50)].id");
		
		System.out.println("Ids Prices > 50 : " +id.size());
		
		System.out.println("Ids > 50 : " +id);
		
		for(Integer li :id) {
			
			System.out.println(li);
			
		}
		
		System.out.println(" ------- TOtal rate -----------");
		
		List<Double> rate = ctx.read("$[?(@.price>50)]..rate");
		
		System.out.println("Ids Prices > 50 : " +rate.size());
		
		System.out.println("Ids > 50 : " +rate);
		
		for(Double li :rate) {
			
			System.out.println(li);
			
		}
		
		System.out.println(" ------- TOtal ids from the list -----------");
		
		List<Integer> allid = ctx.read("$[*].id");
		
		System.out.println("Ids : " +allid.size());
		
		System.out.println("IDs : " +allid);
		
		for(Integer li :allid) {
			
			if(li>15) {
				break;
			}
			System.out.println(li);
			
		}
		
		System.out.println(" ------- TOtal Title from the list -----------");
		
		List<String> alltitle = ctx.read("$[*].category");
		
		System.out.println("Ids : " +alltitle.size());
		
		System.out.println("IDs : " +alltitle);
		
		for(String li :alltitle) {

			if(li.equals("men's clothing")) {

				
				
				System.out.println(li);
			}
			
			
		}

		
		
		
		
	}

}
