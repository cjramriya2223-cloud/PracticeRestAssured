package lombokpattern;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactTest {
	
@Test
	
	public void createuser() throws JsonMappingException, JsonProcessingException {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
	
		ContactRequestPOJO contactrequest = ContactRequestPOJO.builder()
									._id(null)
									.firstName("Jamuna")
									.lastName("cerlin")
									.birthdate("2025-12-20")
									.email("apitesting2@gmail.com")
									.phone("8956231230")
									.street1("Sathy Rd")
									.street2("Saravanampatti")
									.city("Coimbatore")
									.stateProvince("Tamil Nadu")
									.postalCode("641035")
									.country("India")
									.owner(null)
									.__v(null)
									.build();
		
		
		System.out.println("----------------POSt Call -----------------");
		
		 String id = given().log().body()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTVjOGVjMTA0N2JiNTAwMTU1ODYxMmQiLCJpYXQiOjE3Njc2NzQwMTN9.MbACqzwR1EUWFRF2E71AgB0qDcrDTcuua86AsDCV9FQ")
		.body(contactrequest)// serialization converting java object to json
		.when()
		.post("/contacts")
		.then().log().body()	
		.assertThat()
		.statusCode(201)
		.extract()
		.path("_id");
		 
		 System.out.println("User id : " +id);
			
		 
			System.out.println("----------------Get Call -----------------");
			
			RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
			
		Response response = given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTVjOGVjMTA0N2JiNTAwMTU1ODYxMmQiLCJpYXQiOjE3Njc2NzQwMTN9.MbACqzwR1EUWFRF2E71AgB0qDcrDTcuua86AsDCV9FQ")
			.pathParam("id", id)
			.when()
			.get("/contacts/{id}");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ContactRequestPOJO contactresponse = mapper.readValue(response.getBody().asString(), ContactRequestPOJO.class);
		
		
		
		Assert.assertEquals(contactresponse.get_id(),id );
		System.out.println("Id validated successfully");
		
		Assert.assertEquals(contactresponse.getFirstName(),contactrequest.getFirstName() );
		System.out.println("First validated successfully");
		
		Assert.assertEquals(contactresponse.getLastName(),contactrequest.getLastName() );
		System.out.println("Lastname validated successfully");
		
		Assert.assertEquals(contactresponse.getBirthdate(),contactrequest.getBirthdate() );
		System.out.println("Birth validated successfully");
		
		Assert.assertEquals(contactresponse.getEmail(),contactrequest.getEmail() );
		System.out.println("Email validated successfully");
		
		Assert.assertEquals(contactresponse.getPhone(),contactrequest.getPhone() );
		System.out.println("Phone validated successfully");
		
		Assert.assertEquals(contactresponse.getStreet1(),contactrequest.getStreet1() );
		System.out.println("Street 1 validated successfully");
		
		Assert.assertEquals(contactresponse.getStreet2(),contactrequest.getStreet2() );
		System.out.println("Street 2 validated successfully");
		
		Assert.assertEquals(contactresponse.getCity(),contactrequest.getCity());
		System.out.println("City validated successfully");
		
		Assert.assertEquals(contactresponse.getStateProvince(),contactrequest.getStateProvince()) ;
		System.out.println("State Province validated successfully");
		
		Assert.assertEquals(contactresponse.getPostalCode(),contactrequest.getPostalCode() );
		System.out.println("Postal Code validated successfully");
		
		Assert.assertEquals(contactresponse.getCountry(),contactrequest.getCountry() );
		System.out.println("Country validated successfully");
	
}
}
