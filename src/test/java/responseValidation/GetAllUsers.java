package responseValidation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsers {
	
	@Test(priority=1)
	public void getAllMaleUserAndAsserting() {
		
		baseURI = "https://gorest.co.in";
		
		Response response = given()
								.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
								.queryParam("gender", "male")
							.when()
								.get("/public/v2/users");
		
		response.prettyPrint();
		
		JsonPath js = response.jsonPath();
		
		List<Integer> listofIds = js.getList("id");
		
		System.out.println(listofIds);
		
		
		
		
	}
	
	@Test(priority=2)
	public void getSingleUserAndValidate() {
		
		baseURI = "https://gorest.co.in";
		
		Response response = given()
								.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
								.pathParam("id", 8289277)
							.when()
								.get("/public/v2/users/{id}");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.statusCode());
		
		System.out.println(response.getStatusLine());
		System.out.println(response.statusLine());
		
		response.prettyPrint();
		
		System.out.println(response.getHeader("Content-Type"));
		
		System.out.println(response.getHeader("Cache-Control"));
		
		JsonPath js = response.jsonPath();
		
		int id  = js.getInt("id");
		
		System.out.println("Id of the user : " + id);
		
		System.out.println("Id of the user : " +js.getInt("id"));
		
		String name  = js.getString("name");
		
		System.out.println("Name of the user : " + name);
		
		String email  = js.getString("email");
		
		System.out.println("email of the user : " + email);
		
		
		String gender  = js.getString("gender");
		
		System.out.println("gender of the user : " + gender);
		
		
		String status  = js.getString("status");
		
		System.out.println("status of the user : " + status);
		
		
		Assert.assertEquals(id, 8289277);
		
		Assert.assertEquals(name, "Jaime Anderson");
		
		Assert.assertEquals(email, "Joseph90@hotmail.com");
		
		Assert.assertEquals(gender, "male");
		
		Assert.assertEquals(status, "active");
		
		System.out.println("All validation passed");
		
    }
	
	@Test(priority=3)
	public void fetchingOnlyID() {
		
		baseURI = "https://gorest.co.in";
		
		String body =  given()
					.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
					.pathParam("id", 8289277)
				.when()
					.get("/public/v2/users/{id}")
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.statusLine("HTTP/1.1 200 OK")
						 .extract()
						 	.contentType();
		
		System.out.println(body);
		
	}
	
	@Test(priority=4)
	public void multiplequeryparam() {
		
		baseURI = "https://gorest.co.in";
		
		Map<String,String> params = new HashMap<>();
		params.put("gender", "male");
		params.put("status", "inactive");	
	
		String body =  given()
					.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
					.queryParams(params)	
				.when()
					.get("/public/v2/users/")
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.statusLine("HTTP/1.1 200 OK")
						 .extract()
						 	.asPrettyString();
		
		System.out.println(body);
		
	}
	
	@Test(enabled = false)
	public void multiplepathparam() {
		
		baseURI = "https://gorest.co.in";
		
		Map<String,Object> pathparams1 = new HashMap<>();
		pathparams1.put("access", "public");
		pathparams1.put("version", "v2");
		pathparams1.put("resource", "users");
		pathparams1.put("id", 8264380);
		
	
		int code =  given()
					.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
					.pathParams(pathparams1)
				.when()
					.get("/{access}/{version}/{resource}/{id}")
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.statusLine("HTTP/1.1 200 OK")
						 .extract()
						 	.statusCode();
		
		System.out.println(code);
		
	}
	
	
	
	

}
