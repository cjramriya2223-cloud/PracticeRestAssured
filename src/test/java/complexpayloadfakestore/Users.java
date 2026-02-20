package complexpayloadfakestore;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static complexpayloadfakestore.Userspojo.*;

import static complexpayloadfakestore.Userspojo.Address.*;



public class Users {
	

	@Test
        public void createUsers() {
		
		baseURI = "https://fakestoreapi.com";
		
		Geolocation geolocation = new Geolocation("11.0168", "77.0278");
		
		Address address = 
				new Address("Coimbatore", "Kalapatti", "641012", "Tamil Nadu", "India",geolocation);
		
		Name name = new Name("Jamuna", "Cerlin");
		
		Userspojo users =  new Userspojo("jamuna123@gmail.com", "Jamunatest", "jamuna@123", "897458457", name, address);
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(users)
		.when()
		.post("/users")
		.then()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : "+id);
	
		
		
	}
	

	@Test
        public void createUserswithoutBuilder() {
		
		baseURI = "https://fakestoreapi.com";
		
		Geolocation geolocation = new Geolocation.GeolocationBuilder()
				.longitude("99.102")
				.lat("1023.90")
				.build();
		
		Address address = 
				new Address("Coimbatore", "Kalapatti", "641012", "Tamil Nadu", "India",geolocation);
		
		Name name = new Name.NameBuilder() 
				.firstname("Jamuna")
				.lastname("Cerlin")
				.build();
		
		Userspojo users =  new Userspojo.UserspojoBuilder()
				.email("jamuna123@gmail.com")
				.username("Jamunatest")
				.password("jamuna@123")
				.phone("897458457")
				.name(name)
				.address(address)
				.build();

		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(users)
		.when()
		.post("/users")
		.then()
		.assertThat()
		.statusCode(201)
		.extract()
		.path("id");
		
		System.out.println("Id of the user : "+id);
	
		
		
	}
	

}
