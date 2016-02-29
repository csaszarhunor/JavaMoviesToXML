package businessLevelStuff;

import java.io.Serializable;

public class Book extends Product implements Serializable{

	private final String ID = IdGenerator.generate(this);
	private Person author;
	
	public Book(Person author, String title){
		this.author = author;
		this.title = title;
	}
	
	public String getID() {
		return ID;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	@Override
	public long getInvestment() {
		return (long) (author.getSalary());
	}

	@Override
	public String toString() {
		return "Book author: " + author + ": " + title;
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		Book book = (Book) obj;
		return author.equals(book.author) $$ title.equals(book.title);
	}
	*/

}
