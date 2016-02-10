package movies;

import java.util.List;

public class Game extends Product implements Buyable {

	private final String ID = IdGenerator.generate(this);
	private boolean preOrdered = false;
	private List<Person> staff;
	private int price = 0;
	private final int PREORDEREDDISCOUNT = 20;
	
	public Game(String title, List<Person> staff, int price){
		this.title = title;
		this.staff = staff;
		this.price = price;
		
	}
	
	public String getID() {
		return ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPreOrdered() {
		return preOrdered;
	}

	public void setPreOrdered(boolean preOrdered) {
		this.preOrdered = preOrdered;
	}

	public List<Person> getStaff() {
		return staff;
	}

	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	public int getDiscountedPrice(int price, int discount){
		return price - price * discount / 100;
	}
	
	@Override
	public int getPrice() {
		if (preOrdered == true){
			return getDiscountedPrice(price, PREORDEREDDISCOUNT);
		}
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public long getInvestment() {
		long investment = 0;
		for (Person person : staff) {
			investment += person.getSalary();
		}
		return investment;
	}

	/*
	@Override
	public boolean equals(Object obj) {
		Game game = (Game) obj;
		return title.equals(game.title) && 
				((staff == null && game.staff == null) || staff.equals(game.staff));
	}
	*/
	
}
