package responseValidation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NONBDD {
	
	@Test
	public void getAlluser() {
		
		baseURI = "https://gorest.co.in";
		
		RequestSpecification request = given();
		
		request.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97");
		
		Response  response = request.get("/public/v2/users");
		
		System.out.println(response.statusCode());
		
		System.out.println(response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test
	public void getAllMaleUser() {
		
		baseURI = "https://gorest.co.in";
	
		//https://gorest.co.in/public/v2/user?gender=female&status=inactive

		RequestSpecification request = given().log().all();
		
		request.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97");
		
		request.queryParams(Params.femaleinactive());
		
		request.pathParams(Params.pathparams());
		
		Response  response = request.get("/{access}/{version}/{resource}");
		
		System.out.println(response.statusCode());
		
		System.out.println(response.statusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
		//System.out.println(response.asPrettyString());
		
		JsonPath js = response.jsonPath();
		
		List<Integer> ListOfAllid = js.getList("id");
		
		System.out.println("List of Ids: " +ListOfAllid);
		
		List<Integer> ListOfAllname = js.getList("name");
		
		System.out.println("List of Names: " +ListOfAllname);
		
		List<Integer> ListOfAllemail = js.getList("email");
		
		System.out.println("List of emails: " +ListOfAllemail);
		
		List<Integer> ListOfAllstatus = js.getList("status");
		
		System.out.println("List of status: " +ListOfAllstatus);
		
		List<Integer> ListOfAllgender = js.getList("gender");
		
		System.out.println("List of Gender: " +ListOfAllgender);
		
		
		response.prettyPrint();
		
		
	}
	
	
	
	

}
