package complexpayloadfakestore;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Userspojo {
	
	private String email;
	private String username;
	private String password;
	private String phone;
	private Name name;
	private Address address;
	
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Name{
		private String firstname;
		private String lastname;
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Address{
		private String city;
		private String area;
		private String zipcode;
		private String state;
		private String country;
		private Geolocation geolocation;
		
		
		
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		public static class Geolocation{
			
			private String lat;
			
			@JsonProperty("long")
			private String longitude;
			
		}
		
		
	}
	
	
	
	

}
