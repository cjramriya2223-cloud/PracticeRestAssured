package responseValidation;

import java.util.HashMap;
import java.util.Map;

public class Params {

	public static Map<String, String> maleinactive() {
		
		Map<String, String> queryparam = new HashMap<>();
		
		queryparam.put("gender", "male");
		
		queryparam.put("status", "inactive");
		
		return queryparam;
		
	}
	
public static Map<String, String> maleactive() {
		
		Map<String, String> queryparam = new HashMap<>();
		
		queryparam.put("gender", "male");
		
		queryparam.put("status", "active");
		
		return queryparam;
		
	}

public static Map<String, String> femaleinactive() {
	
		Map<String, String> queryparam = new HashMap<>();
	
		queryparam.put("gender", "female");
		
		queryparam.put("status", "inactive");
	
		return queryparam;
	
}

	public static Map<String, String> femaleactive() {
	
		Map<String, String> queryparam = new HashMap<>();
	
		queryparam.put("gender", "female");
	
		queryparam.put("status", "inactive");
	
		return queryparam;
	
}
	
	
	public static Map<String, String> pathparams(){
		
		Map<String, String> pathparam = new HashMap<>();
		
		pathparam.put("access", "public");
	
		pathparam.put("version", "v2");
		
		pathparam.put("resource", "users");
	
		return pathparam;
	}

}
