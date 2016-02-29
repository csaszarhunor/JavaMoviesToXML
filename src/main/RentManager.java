package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import businessLevelStuff.*;

public class RentManager {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
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
		
		Buyable matrixToSell = (Buyable) matrix; 
		Buyable inceptionToSell = (Buyable) inception;
		Buyable witcher3ToSell = (Buyable) witcher3; 
		Buyable farCry4ToSell = (Buyable) farCry4; 
		
		List<Buyable> forSale = new ArrayList<Buyable>();
		forSale.add(matrixToSell);
		forSale.add(inceptionToSell);
		forSale.add(witcher3ToSell);
		forSale.add(farCry4ToSell);
		
		// Serialization, networking
		
		Socket clientSocket = null;
        ObjectOutputStream clientOutputSream = null;
        ObjectInputStream clientInputStream = null;

        try {
            clientSocket = new Socket("localhost", 1123);
            clientOutputSream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Client started, waiting for commands...");
        while (true) {
            try {
            	System.out.print("\n\t1. Order server to serialize objects." +
                        "\n\t2. Order server to fetch serialized objects." +
                        "\n\t0. Shutdown server and client." +
                        "\n\tEnter a number for execution: ");
                userInput = scanner.nextLine();


                if (userInput.equals("1")) {
                	clientOutputSream.writeObject(Command.PUT);

                    clientOutputSream.write(0);
                    clientOutputSream.writeObject(keanu);
                    clientOutputSream.write(0);
                    clientOutputSream.writeObject(matrix);
                    clientOutputSream.write(0);
                    clientOutputSream.writeObject(witcher3);
                    clientOutputSream.write(0);
                    clientOutputSream.writeObject(oldManAndTheSea);
                    clientOutputSream.write(0);
                    clientOutputSream.writeObject(flowersForAlgernon);
                    System.out.println("Done.");
                } else if (userInput.equals("2")) {
                	clientOutputSream.writeObject(Command.GET);
                    List<Object> results= new ArrayList<>();
                    results = (List)clientInputStream.readObject();
                    for (Object o:results){
                        System.out.println(o);
                    }
                } else if (userInput.equals("0")) {
                    clientOutputSream.writeObject(Command.EXIT);
                    break;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            clientSocket.close();
            clientOutputSream.close();
            clientInputStream.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static int calculateExpectablePrice(List<Buyable> buyables){
		int result = 0;
		for (Buyable buyable : buyables) {
			result += buyable.getPrice();
		}
		return result;
	}
}
