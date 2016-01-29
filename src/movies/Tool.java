package movies;
import java.util.HashMap;
import java.util.List;

class Tool {

	public static String toXMLTag(String tagName, String value){
		String Tag = "<" + tagName + ">" + value + "</" + tagName + ">";
		return Tag;
	}
	
	public static String toMoviesXML(String... moviesXMLs){
		String xmlHeader = new String("<?xml version=\"1.0\"?>");
		String content = new String("");
		for (String movie: moviesXMLs){
			content += movie;
		}
		String result = xmlHeader + toXMLTag("movies", content);
		return result; 
	}
	
	public static HashMap<Person, Integer> countMoviesPerPerson(List<Movie> movies){
		HashMap<Person, Integer> numberOfMoviesPerPerson = new HashMap<>();
		for (Movie movie: movies){
			for (Person person: movie.cast){
				if (numberOfMoviesPerPerson.containsKey(person)){
					Integer number = numberOfMoviesPerPerson.get(person);
					numberOfMoviesPerPerson.put(person, number + 1);
				} else {
					numberOfMoviesPerPerson.put(person, 1);
				}
			}
		}
		return numberOfMoviesPerPerson;
	}
	
	public static String[] getMovieTitles(List<Movie> movies){
		
		int numOfMovies = movies.size();
		String[] movieTitles = new String[numOfMovies];
		for (int index = 0; index < numOfMovies; index++){
			movieTitles[index] = movies.get(index).getTitle();
		}
		return movieTitles;
	}

}
