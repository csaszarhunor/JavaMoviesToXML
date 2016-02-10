package movies;
import java.util.List;

public class Movie extends Product implements Buyable{

	private final String ID = IdGenerator.generate(this);
	private Genre genre;
	private long duration;
	private double rate;
	private List<Person> cast;
	private int price;
	
	public Movie(String title, Genre genre, long duration,
			double rate, List<Person> cast, int price){
		this.title = title;
		this.genre = genre;
		this.duration = duration;
		this.rate = rate;
		this.cast = cast;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<Person> getCast() {
		return cast;
	}

	public void setCast(List<Person> cast) {
		this.cast = cast;
	}

	@Override
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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


	@Override
	public long getInvestment() {
		long investment = 0;
		for (Person person : cast) {
			investment += person.getSalary();
		}
		return investment;
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		Movie movie = (Movie) obj;
		return title.equals(movie.title) && 
				((cast == null && movie.cast == null) || cast.equals(movie.cast));
	}
	*/
	
}
