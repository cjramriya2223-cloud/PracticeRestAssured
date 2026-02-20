package complexPayloadsHttp;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

public class ComplexPayload1 {
	
	@Test
	public void httpbin() {
		
		baseURI = "http://httpbin.org";
		
		List<String> continent = Arrays.asList("Africa","Asia");
		
		ComplexPayload1POJO.Category.Attributes attributes = 
				new ComplexPayload1POJO.Category.Attributes(continent, 5);
		
		ComplexPayload1POJO.Category category = 
				new ComplexPayload1POJO.Category(5001, "Wild Animals", attributes);
		
		List<String> tags = Arrays.asList("wild","predator");
		
		
		ComplexPayload1POJO complexpayload =  new ComplexPayload1POJO(101,"Lion",true,tags,category);
		
		Response response = given().log().all()
		.contentType(ContentType.JSON)
		.body(complexpayload)
		.when()
		.post("/post");
		
		
		JsonPath js = response.jsonPath();
				
		System.out.println(js.getInt("json.category.id"));
		System.out.println(js.getString("json.category.name"));
		
		Assert.assertEquals(js.getInt("json.category.id"), category.getId());
		Assert.assertEquals(js.getString("json.category.name") ,category.getName());
		
		Assert.assertEquals(js.getString("json.category.attributes.continent[0]"), attributes.getContinent().get(0));
		Assert.assertEquals(js.getString("json.category.attributes.continent[1]"), attributes.getContinent().get(1));
		System.out.println(js.getList("json.tags"));
		
		Assert.assertEquals(js.getList("json.tags"), complexpayload.getTags());
	}

}
