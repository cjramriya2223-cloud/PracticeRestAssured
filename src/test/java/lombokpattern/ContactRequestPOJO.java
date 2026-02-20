package lombokpattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequestPOJO {

	@JsonInclude(Include.NON_NULL)
	private String _id;
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private String email;
	private String phone;
	private String street1;
	private String street2;
	private String city;
	private String stateProvince;
	private String postalCode;
	private String country;
	
	@JsonInclude(Include.NON_NULL)
	private String owner;
	
	@JsonInclude(Include.NON_NULL)
	private String __v;

}
