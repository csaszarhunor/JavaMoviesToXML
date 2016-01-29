package movies;
import java.util.List;

public class Movie {

	String title;
	Genre genre;
	long duration;
	double rate;
	List<Person> cast;
	
	public Movie(String startTitle, Genre startGenre, long startDuration,
			double startRate, List<Person> startCast){
		title = startTitle;
		genre = startGenre;
		duration = startDuration;
		rate = startRate;
		cast = startCast;
	}
	
	String getTitle(){
		return this.title;
	}
	
	void setTitle(String newTitle){
		this.title = newTitle;
	}
	
	Genre getGenre(){
		return this.genre;
	}
	
	void setGenre(Genre newGenre){
		this.genre = newGenre;
	}
	
	long getDuration(){
		return this.duration;
	}
	
	void setDuration(long newDuration){
		this.duration = newDuration;
	}
	
	double getRate(){
		return this.rate;
	}
	
	void setRate(double newRate){
		this.rate = newRate;
	}
	
	List<Person> getCast(){
		return this.cast;
	}
	
	void setCast(List<Person> newCast){
		this.cast = newCast;
	}
	
	String toXMLString(){
		String titleTag = Tool.toXMLTag("title", this.title);
		String genreTag = Tool.toXMLTag("genre", this.genre.toString());
		String durationTag = Tool.toXMLTag("duration", String.valueOf(this.duration));
		String rateTag = Tool.toXMLTag("rate", String.valueOf(this.rate));
		String personsXMLs = "";
		for (Person person: this.cast){
			String personXML;
			personXML = person.toXMLString();
			personsXMLs += personXML;
			}
		String castTag = Tool.toXMLTag("cast", personsXMLs);
		String movieTag = Tool.toXMLTag("movie", String.join("", titleTag, genreTag, 
				durationTag, rateTag, castTag));
		return movieTag;
	}
}
