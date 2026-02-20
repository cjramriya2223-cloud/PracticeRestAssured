package jaywayjsonpath;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;


import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class JaywayExpression {
	
	@Test
	
	public void getProducts() {
		
		baseURI = "https://fakestoreapi.com";
		
		Response response = given()
								.when()
									.get("/products");
		
		//response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
		String body = response.getBody().asString();
		
		ReadContext ctx = JsonPath.parse(body);
		
		System.out.println("--------- Fetching all ids ----------");
		
		List<Integer> allids = ctx.read("$.[*].id");
		
		System.out.println(allids);
		
		System.out.println("--------- Fetching all title ----------");
		
		List<String> alltitle = ctx.read("$.[*].title");
		
		for(String title : alltitle) {
			//System.out.println(title);
		};
		
		
		System.out.println("-------- Contains method -----");
		
		// =~ /.* value .*/i
		
		List<Integer> ids = ctx.read("$.[?(@.title =~ /.*Portable.*/i)].['id']");
		
		System.out.println(ids);
		
		
		System.out.println("-------- Start With method -----");
		
		// =~ /^ value .*/i
		
		List<Integer> startswithid = ctx.read("$.[?(@.title =~ /^Silicon.*/i)].['id']");
		
		System.out.println(startswithid);
		
		
		System.out.println("-------- End With method -----");
		
		// =~ /.* value $/i
		
		List<Integer> endsswithid = ctx.read("$.[?(@.title =~ /.*Princess$/i)].['id']");
		
		System.out.println(endsswithid);
	}
	
@Test
	
	public void getallProducts() {
		
		baseURI = "https://fakestoreapi.com";
		
		Response response = given()
								.when()
									.get("/products");
		
		//response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
		String body = response.getBody().asString();
		
		ReadContext ctx = JsonPath.parse(body);
		
		
		//Fetching single attribute
		
		System.out.println("-------- Fetching single attribute using contains method -----");
		
		// =~ /.* value .*/i
		
		List<Integer> ids = ctx.read("$.[?(@.title =~ /.*Gold.*/i)].['id']");
		
		System.out.println(ids);
		
		
		//Fetching two attribute
		
		System.out.println("-------- Fetching two attribute using contains method -----");
				
		// =~ /.* value .*/i
				
		List<Map<String, Object>> twoattribute = ctx.read("$.[?(@.title =~ /.*Gold.*/i)].['id','title']");
				
		for(Map<String, Object> elements : twoattribute) {
			
			System.out.println("Id : " +elements.get("id"));
			System.out.println("Title : " + elements.get("title"));
			System.out.println("---------------");
		}
		
		
		//Fetching Three attribute
		
				System.out.println("-------- Fetching three attribute using contains method -----");
						
				// =~ /.* value .*/i
						
				List<Map<String, Object>> threeattribute = ctx.read("$.[?(@.title =~ /.*Gold.*/i)].['price','id','title']");
						
				for(Map<String, Object> elements : threeattribute) {
					
					System.out.println("Price : " +elements.get("price"));
					System.out.println("Id : " +elements.get("id"));
					System.out.println("Title : " + elements.get("title"));
					
					System.out.println("---------------");
				}
				
				
				//Fetching four attribute
				
				System.out.println("-------- Fetching three attribute using contains method -----");
						
				// =~ /.* value .*/i
						
				List<Map<String, Object>> fourattribute = ctx.read("$.[?(@.title =~ /.*Gold.*/i)].['price','id','title','category']");
						
				for(Map<String, Object> elements : fourattribute) {
					
					System.out.println("Price : " +elements.get("price"));
					System.out.println("Id : " +elements.get("id"));
					System.out.println("Title : " + elements.get("title"));
					System.out.println("Category:" +elements.get("category"));
					
					System.out.println("---------------");
				}
}

@Test

public void multipleconditions() {
	
	baseURI = "https://fakestoreapi.com";
	
	Response response = given()
							.when()
								.get("/products");
	
	//response.prettyPrint();
	
	Assert.assertEquals(response.statusCode(), 200);
	
	
	String body = response.getBody().asString();
	
	ReadContext ctx = JsonPath.parse(body);
	
	System.out.println(" --------- AND condition -----------");
	
	List<Map<String, Object>> fourattribute = ctx.read("$.[?(@.category==='jewelery' && @.price>100)].['id','title','price','category'])");
			
	for(Map<String, Object> elements : fourattribute) {
		
		System.out.println("Price : " +elements.get("price"));
		System.out.println("Id : " +elements.get("id"));
		System.out.println("Title : " + elements.get("title"));
		System.out.println("Category:" +elements.get("category"));
		
		System.out.println("---------------");
	}
	
System.out.println(" --------- OR condition -----------");
	
	List<Map<String, Object>> orcondition = ctx.read("$.[?(@.category==='jewelery' || @.category ==='electronics')].['id','title','price','category'])");
			
	for(Map<String, Object> elements : orcondition) {
		
		System.out.println("Price : " +elements.get("price"));
		System.out.println("Id : " +elements.get("id"));
		System.out.println("Title : " + elements.get("title"));
		System.out.println("Category:" +elements.get("category"));
		
		System.out.println("---------------");
	}
	
	
	System.out.println(" --------- Combination of OR condition -----------");
	
	List<Map<String, Object>> multipleconditions = ctx.read("$.[?((@.category==='jewelery' || @.category ==='electronics') && (@.price>100 ))].['id','title','price','category'])");
			
	for(Map<String, Object> elements : multipleconditions) {
		
		System.out.println("Price : " +elements.get("price"));
		System.out.println("Id : " +elements.get("id"));
		System.out.println("Title : " + elements.get("title"));
		System.out.println("Category:" +elements.get("category"));
		
		System.out.println("---------------");
	}
	
}
}
