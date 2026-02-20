package fakeStore;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import static fakeStore.FSUserPojo.*;

import static fakeStore.FSUserPojo.Address.*;

public class FSUserTest {
	
	@Test
	public void userwithoutBuilder() {
		
		baseURI = "https://fakestoreapi.com";
		
		Name name =  new Name("Jamuna", "Cerlin");
		
		Geolocation goelocation = 
				new Geolocation("11.0168","77.0278");
		
		Address address = 
				
					new Address("Coimbatore","Kalapatti","641012", "Tamil Nadu","India", goelocation);
		
		FSUserPojo userdetails =  new FSUserPojo("jamuna1@gmail.com", "Jamunatest", "jamuna@123", "897458457", name, address);
		
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(userdetails)
		.when()
		.post("/users")
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.statusLine("HTTP/1.1 201 Created")
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " + id);
				
				
		
	}
	
	@Test
	public void userwithBuilder() {
		
		baseURI = "https://fakestoreapi.com";
		
					Name name =  new Name.NameBuilder()
									.firstname("Rajesh")
									.lastname("Divya")
									.build();
		
		Geolocation goelocation = new Geolocation.GeolocationBuilder()
									.lat("11.0168")
									.longitude("77.0278")
									.build();	
		
		
		Address address = new Address.AddressBuilder()
									.city("Chennai")
									.area("Porur")
									.zipcode("641012")
									.state("Tamil Nadu")
									.country("India")
									.goelocation(goelocation)
									.build();
				
		
		FSUserPojo userdetails =  new FSUserPojo.FSUserPojoBuilder()
									.email("rajesh@gmail.com")
									.username("Rajeshtest")
									.password("Rajesh@123")
									.phone("8974588911")
									.name(name)
									.address(address)
									.build();
		
		
		
		
		
		int id = given().log().all()
		.contentType(ContentType.JSON)
		.body(userdetails)
		.when()
		.post("/users")
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.statusLine("HTTP/1.1 201 Created")
		.extract()
		.path("id");
		
		System.out.println("Id of the user : " + id);
				
				
		
	}
	
	
	
	

}
