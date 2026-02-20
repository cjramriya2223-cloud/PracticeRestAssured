package fakeUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombak {
	
	private Integer id;
	private String title;
	private Double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	 public static class Rating{
		private Double rate;
		private Integer count ;
	}
	
	
	
	

}
