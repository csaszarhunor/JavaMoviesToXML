package movies;

import java.util.ArrayList;
import java.util.List;

public class RentManager {

	public static void main(String[] args) {
		
		Person keanu = new Person("Keanu", "Reeves", Gender.MALE, 1100000);
		Person carrie = new Person("Carrie-Anne", "Moss", Gender.FEMALE, 1000000);
		Person marion = new Person("Marion", "Cotillard", Gender.FEMALE, 800000);
		Person leo = new Person("Leonardo", "Di Caprio", Gender.MALE, 900000);
		Person konrad = new Person("Konrad", "Tomaszkiewicz", Gender.MALE, 900000);
		Person mateusz = new Person("Mateusz", "Kanik", Gender.MALE, 900000);
		Person todd = new Person("Todd", "Howard", Gender.MALE, 1000000);
		Person craig = new Person("Craig", "Lafferty", Gender.MALE, 1000000);
		Person ernest = new Person("Ernest", "Hemingway", Gender.MALE, 600);
		Person daniel = new Person("Daniel", "Keyes", Gender.MALE, 600);
		
		List<Person> matrixCast = new ArrayList<Person>();
		matrixCast.add(keanu);
		matrixCast.add(carrie);
		List<Person> inceptionCast = new ArrayList<Person>();
		inceptionCast.add(marion);
		inceptionCast.add(leo);
		List<Person> witcher3Staff = new ArrayList<Person>();
		witcher3Staff.add(konrad);
		witcher3Staff.add(mateusz);
		List<Person> farCry4Staff = new ArrayList<Person>();
		farCry4Staff.add(todd);
		farCry4Staff.add(craig);
		
		Product matrix = new Movie("The Matrix", Genre.SCI_FI, 140, 5.0, matrixCast, 5);
		Product inception = new Movie("Inception", Genre.SCI_FI, 160, 5.0, inceptionCast, 6);
		Product witcher3 = new Game("The Witcher 3", witcher3Staff, 50);
		Product farCry4 = new Game("FarCry 4", farCry4Staff, 50);
		Product oldManAndTheSea = new Book(ernest, "The Old Man and the Sea");
		Product flowersForAlgernon = new Book(daniel, "Flowers for Algernon");
		
		System.out.println(matrix.getInvestment());
		System.out.println(inception.getInvestment());
		System.out.println(witcher3.getInvestment());
		System.out.println(farCry4.getInvestment());
		System.out.println(oldManAndTheSea.getInvestment());
		System.out.println(flowersForAlgernon.getInvestment());
		
		
		Buyable matrixToSell = (Buyable) matrix; 
		Buyable inceptionToSell = (Buyable) inception;
		Buyable witcher3ToSell = (Buyable) witcher3; 
		Buyable farCry4ToSell = (Buyable) farCry4; 
		
		List<Buyable> forSale = new ArrayList<Buyable>();
		forSale.add(matrixToSell);
		forSale.add(inceptionToSell);
		forSale.add(witcher3ToSell);
		forSale.add(farCry4ToSell);
		
		System.out.println(calculateExpectablePrice(forSale));
		
	}

	public static int calculateExpectablePrice(List<Buyable> buyables){
		int result = 0;
		for (Buyable buyable : buyables) {
			result += buyable.getPrice();
		}
		return result;
	}
}
