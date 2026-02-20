package getAPIWithBDDFormat;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GorestGetAPITest {
	
	@Test
	public void getAllUser() {
		
		baseURI = "https://gorest.co.in/";
				
		given().log().all()
			.headers("Authorization", "Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
		.when()
			.get("public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(200)
					.and()
						.statusLine("HTTP/1.1 200 OK");
				
				
	}
	
	@Test
	public void getmaleinactiveuser() {
		
		baseURI = "https://gorest.co.in/";
				
		Response response = given()
			.headers("Authorization", "Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
			.queryParam("gender", "male")
			.queryParam("status", "inactive")
		.when()
			.get("public/v2/users");
		
		JsonPath path = response.jsonPath();
		
		List<Integer> js = path.getList("id");
		
		for(int i = 0; i<js.size();i++) {
			System.out.println(js.get(i));
		}
		
		for(int id : js) {
			System.out.println(id);
		}
		
		System.out.println();
		
	}
	
	@Test
	public void getsingleuser() {
		
		baseURI = "https://gorest.co.in/";
		
		Map<String,Object> path1 = new HashMap<>();
		path1.put("resource", "users");
		path1.put("id", 8280805);		
				
		Response response = given().log().all()
			.headers("Authorization", "Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
			
			.when().log().all()
			.get("public/v2/users/{id}");
		
		JsonPath path = response.jsonPath();
		
		System.out.println(path.getInt("id"));
		
		Integer id = (Integer)path.get("id");
		
		
	}
	
	@Test
	public void validateToken() {
		
		baseURI = "https://gorest.co.in";
				
		Response body = given()
			.header("Authorization", "Jamuna --")
		.when()
			.get("/public/v2/users/8228807");
		
			
		System.out.println(body.headers().size());
				
	}
	
	
	@Test
	public void getwithoutnonBDD() {
		
		baseURI = "https://gorest.co.in/";
				
		RequestSpecification request = given();
		
		Map<String, Object> path  = new HashMap<>();
		path.put("version", "v2");
		path.put("resource", "users");
		path.put("userid", 8289277);
		
		request.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97");
		request.pathParams(path);   
		
		Response res = request.get("public/{version}/{resource}/{userid}");
		
		//8289277
		
		System.out.println(res.statusCode());
		
		System.out.println(res.statusLine());
		
		//res.prettyPrint();
		
		//System.out.println(res.headers());
		
		JsonPath js = res.jsonPath();
		
		int id = js.getInt("id");
		
		System.out.println(id);
		
		String name = js.getString("name");
		
		System.out.println(name);
		
		String status = js.getString("status");
		
		System.out.println(status);
		
		String email = js.getString("email");
		
		System.out.println(email);
		
		String gender = js.getString("gender");
		
		System.out.println(gender);
		
		
		
		
		
		
		
	}
	

}
