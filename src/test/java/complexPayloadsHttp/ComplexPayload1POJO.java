package complexPayloadsHttp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ComplexPayload1POJO {
	
	
	private int id;
	private String name;
	
	@JsonProperty("isActive")
	private boolean active ;
	
	private List<String> tags;
	private Category category;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category{
		
		private int id;
		private String name;
		private Attributes attributes;
	
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		@Builder
		public static class Attributes{
			private List<String> continent;
			private int dangerLevel ;
		}
	}
	
	
	

}
