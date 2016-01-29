package movies;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class MovieManager {

	public static void main(String[] args) {
		
		Person keanu = new Person("Keanu", "Reeves", Gender.MALE, false, false);
		Person carrie = new Person("Carrie-Anne", "Moss", Gender.FEMALE, false, false);
		Person marion = new Person("Marion", "Cotillard", Gender.FEMALE, true, true);
		Person leo = new Person("Leonardo", "Di Caprio", Gender.MALE, false, true);
		
		List<Person> matrixCast = new ArrayList<Person>();
		matrixCast.add(keanu);
		matrixCast.add(carrie);
		Movie matrix = new Movie("The Matrix", Genre.SCI_FI, 140, 5.0, matrixCast);
		List<Person> inceptionCast = new ArrayList<Person>();
		inceptionCast.add(marion);
		inceptionCast.add(leo);
		Movie inception = new Movie("Inception", Genre.SCI_FI, 160, 5.0, inceptionCast);
		
		String matrixXML = matrix.toXMLString();
		String inceptionXML = inception.toXMLString();
		
		try {
			final File moviesXML = new File("movies.xml");
			String moviesXMLContent = Tool.toMoviesXML(matrixXML, inceptionXML);
			
			if (moviesXML.exists()){
				System.out.println("File already exists!");
			} else {
				moviesXML.createNewFile();
				FileWriter fw = new FileWriter(moviesXML.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(moviesXMLContent);
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
