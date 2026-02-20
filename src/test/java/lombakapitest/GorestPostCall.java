package lombakapitest;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GorestPostCall {
	
	public String randomemail() {
		return "Apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void postcallusingLombak() {
		
		String email = randomemail();
		
		baseURI = "https://gorest.co.in";//public/v2/users
		
		Gorestlombak usercreation = new Gorestlombak("jamunaapi",email, "active","female");
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
		.body(usercreation)
		.when()
		.post("/public/v2/users")
		.then().log().body()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " +id);
		
		
		System.out.println("------------------------ Get Call----------------------");
		
		baseURI = "https://gorest.co.in";
		given().log().all()
		.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
		.when()
		.get("/public/v2/users/"+id)
		.then().log().body()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test
	public void postcallusingLombakBuilder() {
		
		String email = randomemail();
		
		baseURI = "https://gorest.co.in";//public/v2/users
		
		//Gorestlombak usercreation = new Gorestlombak("jamunaapi",email, "active","female");
		
		Gorestlombak usercreation = new Gorestlombak.GorestlombakBuilder()
				.name("jamunaapitesting")
				.email(email)
				.status("active")
				.gender("female")
				.build();
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
		.body(usercreation)
		.when()
		.post("/public/v2/users")
		.then().log().body()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " +id);
		
		
		System.out.println("------------------------ Get Call----------------------");
		
		baseURI = "https://gorest.co.in";
		given().log().all()
		.header("Authorization","Bearer e80205fe62ee7332e8944083803bc16ca52604ff4e79297fd34d88f8e3851f97")
		.when()
		.get("/public/v2/users/"+id)
		.then().log().body()
		.assertThat()
		.statusCode(200);
		
		
	}

}
