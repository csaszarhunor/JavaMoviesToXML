package businessLevelStuff;

import java.util.UUID;

public class IdGenerator {

	private final static String MOVIECODE = "MOV";
	private final static String GAMECODE = "GAM";
	private final static String BOOKCODE = "BOO";
	
	public static String generate(Product product){
		String code = "";
		if (product instanceof Movie){
			code = MOVIECODE;
		}
		else if (product instanceof Game){
			code = GAMECODE;
		}
		else if (product instanceof Book){
			code = BOOKCODE;
		}
		return code + '-' + UUID.randomUUID().toString();
	}

}
