package getAlluser;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestAPIBDD {
	
	@Test
	public void getSingleUser() {
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
			.header("Authorization", "Bearer f7853558d9ad48de2344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		.when()
			.get("/public/v2/users")
		.then().log().all()			
			.assertThat()
				.statusCode(200)
					.and()
						.statusLine("HTTP/1.1 200 OK");
			}
	
	@Test
	public void authTest() {
		
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "Bearer f7853558d9ad48d442344f7fa5cf1926b16384003cc4696655dc0b123e4cb8a1b")
		.when()
			.get("/public/v2/users")
		.then().log().all()			
			.assertThat()
				.statusCode(401)
					.and()
						.statusLine("HTTP/1.1 401 Unauthorized");
			}
	
	

}
