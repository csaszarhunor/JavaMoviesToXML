package businessLevelStuff;

import java.io.Serializable;

public abstract class Product implements Serializable{
	
	protected String id;
	protected String title;
	protected Person renter;
	
	public String getTitle() {
		return title;
	}
	
	public Person getRenter() {
		return renter;
	}
	
	public abstract long getInvestment();
}
