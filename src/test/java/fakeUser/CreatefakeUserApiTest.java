package fakeUser;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreatefakeUserApiTest {
	
	@Test
	public void fakestoreusercreationwithBuilder() {
		
		RestAssured.baseURI= "https://fakestoreapi.com/";
		
	FakeUserLombok.Fullname name 					= 	new FakeUserLombok.Fullname.FullnameBuilder()
														.firstname("Jamuna")
														.lastname("Cerlin")
														.build();
		
	FakeUserLombok.Address.GeoLocation geoLocation 	=	new FakeUserLombok.Address.GeoLocation.GeoLocationBuilder()
														.lat("-37.3159")
														.longitude("-73.3159")
														.build();
	
	FakeUserLombok.Address address 					= 	new FakeUserLombok.Address.AddressBuilder().city("Coimbatore")
														.street("Test")
														.number("1712")
														.zipcode("641603")
														.geoLocation(geoLocation)
														.build();
	
	FakeUserLombok user 							= 	new FakeUserLombok.FakeUserLombokBuilder()
														.email("jamuna@gmail.com")
														.username("Jamuna")
														.password("Cerlin")
														.phone("8971148593")
														.name(name)
														.address(address)
														.build();
	
	
	int userid = given().log().all()
	.contentType(ContentType.JSON)
	.body(user)
	.when()
	.post("users")
	.then().log().all()
	.assertThat()
	.statusCode(201)
	.extract()
	.path("id");
	
	
	System.out.println("Id of the user : " +userid);
	
	}
	
	@Test
	public void fakestoreusercreationwithoutBuilder() {
		
	RestAssured.baseURI= "https://fakestoreapi.com/";
		
	FakeUserLombok.Fullname name 					= 	new FakeUserLombok.Fullname("Jamuna","cerlin");
														
		
	FakeUserLombok.Address.GeoLocation geoLocation 	=	new FakeUserLombok.Address.GeoLocation("-37.3159", "-73.3159");
														
	
	/*FakeUserLombok.Address address 					= 	new FakeUserLombok.Address.AddressBuilder().city("Coimbatore")
														.street("Test")
														.number("1712")
														.zipcode("641603")
														.geoLocation(geoLocation)
														.build();*/
	
	FakeUserLombok.Address address 					= 	new FakeUserLombok.Address("Coimbatore","Test","1712","641603",geoLocation);
	
	
			
	
	FakeUserLombok user 							= 	new FakeUserLombok("jamuna@gmail.com","jamuna","cerlin","8971148582",name,address);
														
	
	int userid = given().log().all()
	.contentType(ContentType.JSON)
	.body(user)
	.when()
	.post("users")
	.then().log().all()
	.assertThat()
	.statusCode(201)
	.extract()
	.path("id");
	
	
	System.out.println("Id of the user : " +userid);
	
	}
}
