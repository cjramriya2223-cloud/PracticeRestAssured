package complexpetstorepayload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetstorePojoClass {
	
	private int id;
	private String name;
	private String status;
	private Category category;
	private List<String> photoUrls;
	private List<Tags> tags;
	

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category{
		private int id;
		private String name;
		
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tags{
		private Integer id;
		private String name;
	}
	
	
	

}
