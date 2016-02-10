package movies;

public abstract class Product {
	
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
