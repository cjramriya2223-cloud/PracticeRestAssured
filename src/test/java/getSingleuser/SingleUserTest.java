package getSingleuser;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class SingleUserTest {
	
	@Test
	
	public void singleuser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given()
					.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
						.when()
							.get("/public/v2/users/8171846");
		
		AssertJUnit.assertEquals(response.statusCode(), 404);
		
		AssertJUnit.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		 System.out.println("Status Code : " + response.statusCode());
		 System.out.println("status Line :" + response.statusLine());
		 response.prettyPrint();
		 String contenttype = response.getHeader("Content-Type");
		 System.out.println(contenttype);
		 
		 JsonPath js = response.jsonPath();
		 
		 System.out.println("Id of the user : " +js.getInt("id"));
		 System.out.println("Name of the user :" +js.getString("name"));
		 System.out.println("Status of the user :" +js.getString("status"));
		 System.out.println("Email of the user :" +js.get("email"));
		 
		 AssertJUnit.assertEquals(js.getInt("id"), 8171846);
		 AssertJUnit.assertEquals(js.getString("name"), "Asha Menon");
		 AssertJUnit.assertEquals(js.getString("status"), "active");
		 
							
		 AssertJUnit.assertEquals(contenttype, "application/json; charset=utf-8");
		
		
	}

}
