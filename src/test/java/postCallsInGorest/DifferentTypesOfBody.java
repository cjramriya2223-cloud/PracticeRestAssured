package postCallsInGorest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import java.io.File;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DifferentTypesOfBody {
	
	@Test()
	public void XMLbody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType("application/xml; charset=UTF-8")
							.body("<dependency>\n"
									+ "    <groupId>io.rest-assured</groupId>\n"
									+ "    <artifactId>rest-assured</artifactId>\n"
									+ "    <version>6.0.0</version>\n"
									+ "    <scope>test</scope>\n"
									+ "</dependency>")
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}
	
	@Test()
	public void TextBody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType(ContentType.TEXT)
							.body("Hai How r u ")
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}
	
	@Test()
	public void HTMLBody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType(ContentType.HTML)
							.body("<html>\n"
									+ "\n"
									+ "<body>\n"
									+ "\n"
									+ "    <h1>My First Heading</h1>\n"
									+ "\n"
									+ "    <p>My first paragraph.</p>\n"
									+ "\n"
									+ "</body>\n"
									+ "\n"
									+ "</html> ")
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}
	
	@Test()
	public void javaScriptBody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType("application/javascript; charset=UTF-8")
							.body("console.log('Jamuna')")
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}
	
	@Test()
	public void binaryBody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType("application/msword;charset=utf-8")
							.body(new File("/Users/jamuna/Downloads/file-sample_100kB.doc"))
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}
	
	@Test()
	public void formdataBody() {
		
		baseURI = "https://postman-echo.com";
		
		Response response = given()
							.contentType(ContentType.MULTIPART)
							.multiPart("name","Jamuna")
							.multiPart("age","25")
							.multiPart("gender","female")
							.multiPart("marital","married")
							.multiPart("Location","Coimbatore")
							.multiPart(new File("/Users/jamuna/Downloads/file-sample_100kB.doc"))
							.when()
								.post("/post");
		
	System.out.println("Status Code " +response.statusCode());
	System.out.println("Status Line " +response.statusLine());
	response.prettyPrint();
	}

}
